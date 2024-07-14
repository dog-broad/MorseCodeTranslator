import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.github.pervoj.jiconfont.FontAwesomeRegular;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MorseCodeGUI extends JFrame {
    private final JTextArea englishTextArea;
    private final JTextArea morseTextArea;
    private final JButton translateToMorseButton;
    private final JButton translateToEnglishButton;
    private final JButton playMorseButton;
    private final JButton stopMorseButton;
    private final JButton saveTextButton;
    private final JButton saveMorseButton;
    private final JButton switchThemeButton;
    private JSlider speedSlider;
    private JSlider pitchSlider;
    private JSlider volumeSlider;
    private boolean isDarkTheme = true;

    public MorseCodeGUI() {
        IconFontSwing.register(FontAwesomeRegular.getIconFont());

        setTitle("Morse Code Translator");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        englishTextArea = new JTextArea();
        morseTextArea = new JTextArea();
        translateToMorseButton = new JButton("Translate to Morse", IconFontSwing.buildIcon(FontAwesomeRegular.ARROW_ALT_CIRCLE_RIGHT, 18));
        translateToEnglishButton = new JButton("Translate to English", IconFontSwing.buildIcon(FontAwesomeRegular.ARROW_ALT_CIRCLE_LEFT, 18));
        playMorseButton = new JButton("Play Morse Code", IconFontSwing.buildIcon(FontAwesomeRegular.PLAY_CIRCLE, 18));
        stopMorseButton = new JButton("Stop Morse Code", IconFontSwing.buildIcon(FontAwesomeRegular.STOP_CIRCLE, 18));
        saveTextButton = new JButton("Save Text", IconFontSwing.buildIcon(FontAwesomeRegular.SAVE, 18));
        saveMorseButton = new JButton("Save Morse", IconFontSwing.buildIcon(FontAwesomeRegular.SAVE, 18));
        switchThemeButton = new JButton("Switch to Light Theme", IconFontSwing.buildIcon(FontAwesomeRegular.SUN, 18));

        JLabel englishLabel = new JLabel("Plain Text");
        englishTextArea.setFont(new Font("SansSerif", Font.PLAIN, 18));
        JLabel morseLabel = new JLabel("Morse Code");
        morseTextArea.setFont(new Font("Monospaced", Font.PLAIN, 18));

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        addMenuBar(topPanel);
        add(topPanel, BorderLayout.NORTH);

        // Create panels to hold text areas and labels
        JPanel englishPanel = new JPanel(new BorderLayout());
        englishPanel.add(englishLabel, BorderLayout.NORTH);
        englishTextArea.setLineWrap(true);
        englishTextArea.setWrapStyleWord(true);
        englishPanel.add(new JScrollPane(englishTextArea), BorderLayout.CENTER);

        JPanel morsePanel = new JPanel(new BorderLayout());
        morsePanel.add(morseLabel, BorderLayout.NORTH);
        morseTextArea.setLineWrap(true);
        morseTextArea.setWrapStyleWord(true);
        morsePanel.add(new JScrollPane(morseTextArea), BorderLayout.CENTER);

        // Add panels to the main layout
        JPanel textPanel = new JPanel(new GridLayout(1, 2));
        textPanel.add(englishPanel);
        textPanel.add(morsePanel);
        add(textPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
        buttonPanel.add(translateToMorseButton);
        buttonPanel.add(translateToEnglishButton);
        buttonPanel.add(playMorseButton);
        buttonPanel.add(stopMorseButton);
        buttonPanel.add(saveTextButton);
        buttonPanel.add(saveMorseButton);
        buttonPanel.add(switchThemeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        translateToMorseButton.addActionListener(e -> {
            try {
                String englishText = englishTextArea.getText();
                String morseText = MorseCodeTranslator.toMorse(englishText);
                morseTextArea.setText(morseText);
            } catch (Exception ex) {
                showErrorDialog("Error translating to Morse code: " + ex.getMessage());
            }
        });

        translateToEnglishButton.addActionListener(e -> {
            try {
                String morseText = morseTextArea.getText();
                String englishText = MorseCodeTranslator.toEnglish(morseText);
                englishTextArea.setText(englishText);
            } catch (Exception ex) {
                showErrorDialog("Error translating to English: " + ex.getMessage());
            }
        });

        playMorseButton.addActionListener(e -> {
            try {
                new Thread(() -> TranslationHelper.playMorseSound(morseTextArea.getText())).start();
            } catch (Exception ex) {
                showErrorDialog("Error playing Morse code sound: " + ex.getMessage());
            }
        });

        stopMorseButton.addActionListener(e -> {
            try {
                TranslationHelper.stopPlaying();
            } catch (Exception ex) {
                showErrorDialog("Error stopping Morse code sound: " + ex.getMessage());
            }
        });

        saveTextButton.addActionListener(e -> {
            try {
                saveToFile(englishTextArea.getText(), "text.txt");
            } catch (Exception ex) {
                showErrorDialog("Error saving text: " + ex.getMessage());
            }
        });

        saveMorseButton.addActionListener(e -> {
            try {
                saveToFile(morseTextArea.getText(), "morse.txt");
            } catch (Exception ex) {
                showErrorDialog("Error saving Morse code: " + ex.getMessage());
            }
        });

        switchThemeButton.addActionListener(e -> {
            try {
                switchTheme();
            } catch (Exception ex) {
                showErrorDialog("Error switching theme: " + ex.getMessage());
            }
        });

        speedSlider.addChangeListener(e -> {
            try {
                TranslationHelper.setDotDuration(speedSlider.getValue());
            } catch (Exception ex) {
                showErrorDialog("Error setting speed: " + ex.getMessage());
            }
        });

        pitchSlider.addChangeListener(e -> {
            try {
                TranslationHelper.setPitch(pitchSlider.getValue());
            } catch (Exception ex) {
                showErrorDialog("Error setting pitch: " + ex.getMessage());
            }
        });

        volumeSlider.addChangeListener(e -> {
            try {
                TranslationHelper.setVolume(volumeSlider.getValue() / 100f);
            } catch (Exception ex) {
                showErrorDialog("Error setting volume: " + ex.getMessage());
            }
        });

        // Set initial theme
        switchTheme();
    }

    private void createHelpDialog() {
        JDialog helpDialog = new JDialog(this, "Help", true);
        helpDialog.setSize(800, 400);
        helpDialog.setLayout(new BorderLayout());

        // Create a JTextArea to explain features
        JScrollPane scrollPane = getScrollPane();
        helpDialog.add(scrollPane, BorderLayout.CENTER);

        // Add collapsible Text to Morse Table
        String[] columnNames = {"Character", "Morse Code"};
        Object[][] data = {
                {'A', ".-"},
                {'B', "-..."},
                {'C', "-.-."},
                {'D', "-.."},
                {'E', "."},
                {'F', "..-."},
                {'G', "--."},
                {'H', "...."},
                {'I', ".."},
                {'J', ".---"},
                {'K', "-.-"},
                {'L', ".-.."},
                {'M', "--"},
                {'N', "-."},
                {'O', "---"},
                {'P', ".--."},
                {'Q', "--.-"},
                {'R', ".-."},
                {'S', "..."},
                {'T', "-"},
                {'U', "..-"},
                {'V', "...-"},
                {'W', ".--"},
                {'X', "-..-"},
                {'Y', "-.--"},
                {'Z', "--.."},
                {'0', "-----"},
                {'1', ".----"},
                {'2', "..---"},
                {'3', "...--"},
                {'4', "....-"},
                {'5', "....."},
                {'6', "-...."},
                {'7', "--..."},
                {'8', "---.."},
                {'9', "----."},
                {' ', "/"},  // Space in Morse code
                {'.', ".-.-.-"},  // Period
                {',', "--..--"},  // Comma
                {'?', "..--.."},  // Question mark
                {'!', "-.-.--"},  // Exclamation mark
                {'"', ".-..-."},  // Double quote
                {'\'', ".----."},  // Single quote or apostrophe
                {'(', "-.--."},  // Left parenthesis
                {')', "-.--.-"},  // Right parenthesis
                {'&', ".-..."},  // Ampersand
                {':', "---..."},  // Colon
                {';', "-.-.-."},  // Semicolon
                {'=', "-...-"},  // Equal sign
                {'+', ".-.-."},  // Plus sign
                {'-', "-....-"},  // Hyphen or dash
                {'_', "..--.-"},  // Underscore
                {'$', "...-..-"},  // Dollar sign
                {'@', ".--.-."}  // At symbol
        };

        // Create a non-editable table model
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable editing
            }
        };

        JTable table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(100); // Adjusting column width
        table.getColumnModel().getColumn(1).setPreferredWidth(180); // Adjusting column width
        table.setFont(new Font("Arial", Font.PLAIN, 12)); // Setting font size and style
        // Center-align content of column 2
        table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFont(new Font("Monospaced", Font.PLAIN, 12));
                return label;
            }
        });

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Text to Morse Table"));
        tableScrollPane.setPreferredSize(new Dimension(300, 200));
        helpDialog.add(tableScrollPane, BorderLayout.EAST);

        helpDialog.setVisible(true);
    }

    private static JScrollPane getScrollPane() {
        JEditorPane helpText = new JEditorPane();
        helpText.setContentType("text/html");
        helpText.setEditable(false);
        helpText.setText("<html><body style='font-family: Arial, sans-serif; font-size: 14px;'>"
                + "<h1>Welcome to Morse Code Translator!</h1>"
                + "<p>This application allows you to translate between English text and Morse code.</p>"
                + "<h2>Features:</h2>"
                + "<ul>"
                + "<li><b>Translate to Morse:</b> Converts the entered English text into Morse code.</li>"
                + "<li><b>Translate to English:</b> Converts the entered Morse code into English text.</li>"
                + "<li><b>Play Morse Code:</b> Plays the Morse code audio using sound.</li>"
                + "<li><b>Stop Morse Code:</b> Stops the currently playing Morse code audio.</li>"
                + "<li><b>Save Text/Morse:</b> Saves the entered text or Morse code to a text file.</li>"
                + "<li><b>Switch Theme:</b> Toggles between light and dark themes for the application.</li>"
                + "</ul>"
                + "<h2>Usage:</h2>"
                + "<ul>"
                + "<li>Enter text in the 'Plain Text' area and click 'Translate to Morse'.</li>"
                + "<li>Enter Morse code in the 'Morse Code' area and click 'Translate to English'.</li>"
                + "<li>Adjust settings like speed, pitch, and volume from the 'Settings' menu.</li>"
                + "<li>Use the 'Save' buttons to save your work.</li>"
                + "<li>Enjoy translating and learning Morse code!</li>"
                + "</ul>"
                + "</body></html>");

        JScrollPane scrollPane = new JScrollPane(helpText);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        return scrollPane;
    }

    private void addMenuBar(JPanel topPanel) {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveTextMenuItem = new JMenuItem("Save Text");
        JMenuItem saveMorseMenuItem = new JMenuItem("Save Morse");
        fileMenu.add(saveTextMenuItem);
        fileMenu.add(saveMorseMenuItem);
        menuBar.add(fileMenu);

        // Settings menu
        JMenu settingsMenu = new JMenu("Settings");

        speedSlider = createSlider("Speed", 50, 400, 200);
        pitchSlider = createSlider("Pitch", 500, 2000, 1000);
        volumeSlider = createSlider("Volume", 0, 100, 50);

        settingsMenu.add(createSliderPanel("Speed", speedSlider, "ms"));
        settingsMenu.add(createSliderPanel("Pitch", pitchSlider, "Hz"));
        settingsMenu.add(createSliderPanel("Volume", volumeSlider, "%"));
        menuBar.add(settingsMenu);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem helpMenuItem = new JMenuItem("Help Contents");
        helpMenuItem.addActionListener(e -> createHelpDialog());
        helpMenu.add(helpMenuItem);
        menuBar.add(helpMenu);

        topPanel.add(menuBar);
    }


    private JSlider createSlider(String pitch, int min, int max, int value) {
        JSlider slider = new JSlider(min, max, value);
        slider.setMajorTickSpacing((max - min) / 5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }

    private JPanel createSliderPanel(String name, JSlider slider, String unit) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(name), BorderLayout.WEST);
        panel.add(slider, BorderLayout.CENTER);
        panel.add(new JLabel(unit), BorderLayout.EAST);
        return panel;
    }

    private void saveToFile(String content, String defaultFileName) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new java.io.File(defaultFileName));
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File fileToSave = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                writer.write(content);
                JOptionPane.showMessageDialog(this, "File saved: " + fileToSave.getAbsolutePath());
            } catch (IOException e) {
                showErrorDialog("Error saving file: " + e.getMessage());
            }
        }
    }

    private void switchTheme() {
        if (isDarkTheme) {
            FlatLightLaf.setup();
            switchThemeButton.setText("Switch to Dark Theme");
            switchThemeButton.setIcon(IconFontSwing.buildIcon(FontAwesomeRegular.MOON, 18));
            translateToMorseButton.setIcon(IconFontSwing.buildIcon(FontAwesomeRegular.ARROW_ALT_CIRCLE_RIGHT, 18, Color.BLACK));
            translateToEnglishButton.setIcon(IconFontSwing.buildIcon(FontAwesomeRegular.ARROW_ALT_CIRCLE_LEFT, 18, Color.BLACK));
            playMorseButton.setIcon(IconFontSwing.buildIcon(FontAwesomeRegular.PLAY_CIRCLE, 18, Color.BLACK));
            stopMorseButton.setIcon(IconFontSwing.buildIcon(FontAwesomeRegular.STOP_CIRCLE, 18, Color.BLACK));
            saveTextButton.setIcon(IconFontSwing.buildIcon(FontAwesomeRegular.SAVE, 18, Color.BLACK));
            saveMorseButton.setIcon(IconFontSwing.buildIcon(FontAwesomeRegular.SAVE, 18, Color.BLACK));
        } else {
            FlatDarkLaf.setup();
            switchThemeButton.setText("Switch to Light Theme");
            switchThemeButton.setIcon(IconFontSwing.buildIcon(FontAwesomeRegular.SUN, 18, Color.WHITE));
            translateToMorseButton.setIcon(IconFontSwing.buildIcon(FontAwesomeRegular.ARROW_ALT_CIRCLE_RIGHT, 18, Color.WHITE));
            translateToEnglishButton.setIcon(IconFontSwing.buildIcon(FontAwesomeRegular.ARROW_ALT_CIRCLE_LEFT, 18, Color.WHITE));
            playMorseButton.setIcon(IconFontSwing.buildIcon(FontAwesomeRegular.PLAY_CIRCLE, 18, Color.WHITE));
            stopMorseButton.setIcon(IconFontSwing.buildIcon(FontAwesomeRegular.STOP_CIRCLE, 18, Color.WHITE));
            saveTextButton.setIcon(IconFontSwing.buildIcon(FontAwesomeRegular.SAVE, 18, Color.WHITE));
            saveMorseButton.setIcon(IconFontSwing.buildIcon(FontAwesomeRegular.SAVE, 18, Color.WHITE));
        }
        isDarkTheme = !isDarkTheme;
        SwingUtilities.updateComponentTreeUI(this);
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MorseCodeGUI().setVisible(true));
    }
}
