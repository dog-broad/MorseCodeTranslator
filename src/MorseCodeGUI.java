import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

public class MorseCodeGUI {
    // JFrame is a top-level container that contains all the components of a GUI
    private final JFrame frame;

    // HashMap is a data structure that stores key-value pairs
    private final HashMap<String, String> englishToMorseCodeMap;
    private final HashMap<String, String> morseCodeToEnglishMap;

    // JTextArea is a multi-line text component
    // Generate JTextAreas for English to Morse Code and Morse Code to English
    JTextArea englishToMorseCodeTextArea = new JTextArea();
    JTextArea morseCodeToEnglishTextArea = new JTextArea();

    // Constructor
    public MorseCodeGUI(HashMap<String, String> englishToMorseCodeMap, HashMap<String, String> morseCodeToEnglishMap) {
        // Initialize HashMaps
        this.englishToMorseCodeMap = englishToMorseCodeMap;
        this.morseCodeToEnglishMap = morseCodeToEnglishMap;

        // Set theme to Flat Dark
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.out.println("Error setting theme");
        }

        // Initialize JFrame
        frame = new JFrame("Morse Code Translator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Create JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();
        createEnglishToMorseCodePanel(tabbedPane);
        createMorseCodeToEnglishPanel(tabbedPane);

        // Add JTabbedPane to JFrame
        frame.add(tabbedPane);
    }

    private void createEnglishToMorseCodePanel(JTabbedPane tabbedPane) {
        // Create JPanel with BorderLayout (top, bottom, left, right, center)
        JPanel englishToMorseCode = new JPanel();
        englishToMorseCode.setLayout(new BorderLayout());

        // Create JLabel with centered text
        JLabel englishToMorseCodeLabel = new JLabel("English to Morse Code");
        englishToMorseCodeLabel.setHorizontalAlignment(JLabel.CENTER);
        englishToMorseCode.add(englishToMorseCodeLabel, BorderLayout.NORTH);

        // Set JTextArea to wrap text
        englishToMorseCodeTextArea.setLineWrap(true);
        englishToMorseCodeTextArea.setWrapStyleWord(true);
        englishToMorseCode.add(englishToMorseCodeTextArea, BorderLayout.CENTER);

        // Create JButton with text "Translate"
        JButton englishToMorseCodeButton = new JButton("Translate");
        englishToMorseCode.add(englishToMorseCodeButton, BorderLayout.SOUTH);

        // Add ActionListener to JButton
        englishToMorseCodeButton.addActionListener(e -> {
            // Get text from englishToMorseCodeTextArea and translate it to Morse Code
            String englishToMorseCodeText = englishToMorseCodeTextArea.getText();
            String translatedText = TranslationHelper.translateEnglishToMorseCode(englishToMorseCodeText, englishToMorseCodeMap);
            // Set morse code text area to translated text
            morseCodeToEnglishTextArea.setText(translatedText);
            showTranslationDialog(translatedText);
        });

        // Add JPanel to JTabbedPane
        tabbedPane.addTab("English to Morse Code", englishToMorseCode);
    }

    private void createMorseCodeToEnglishPanel(JTabbedPane tabbedPane) {
        // Create JPanel with BorderLayout (top, bottom, left, right, center)
        JPanel morseCodeToEnglish = new JPanel();
        morseCodeToEnglish.setLayout(new BorderLayout());

        // Create JLabel with centered text
        JLabel morseCodeToEnglishLabel = new JLabel("Morse Code to English");
        morseCodeToEnglishLabel.setHorizontalAlignment(JLabel.CENTER);
        morseCodeToEnglish.add(morseCodeToEnglishLabel, BorderLayout.NORTH);

        // Set JTextArea to wrap text
        morseCodeToEnglishTextArea.setLineWrap(true);
        morseCodeToEnglishTextArea.setWrapStyleWord(true);
        morseCodeToEnglish.add(morseCodeToEnglishTextArea, BorderLayout.CENTER);

        // Create JButton with text "Translate"
        JButton morseCodeToEnglishButton = new JButton("Translate");
        morseCodeToEnglish.add(morseCodeToEnglishButton, BorderLayout.SOUTH);

        // Add ActionListener to JButton
        morseCodeToEnglishButton.addActionListener(e -> {
            // Get text from morseCodeToEnglishTextArea and translate it to English
            String morseCodeToEnglishText = morseCodeToEnglishTextArea.getText();
            String translatedText = TranslationHelper.translateMorseCodeToEnglish(morseCodeToEnglishText, morseCodeToEnglishMap);
            // set english text area to translated text
            englishToMorseCodeTextArea.setText(translatedText);
            showTranslationDialog(translatedText);
        });

        // Add JPanel to JTabbedPane
        tabbedPane.addTab("Morse Code to English", morseCodeToEnglish);
    }

    private void showTranslationDialog(String translatedText) {
        // Show dialog with translated text
        JOptionPane.showMessageDialog(frame, translatedText);
    }

    public void show() {
        frame.setVisible(true);
    }
}
