// Import necessary libraries
import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.event.*;

public class MorseCodeTranslator {
    // Create GUI that has two tabs, one for English to Morse Code and one for Morse Code to English

    // Use swing to create GUI
    static HashMap<String, String> englishToMorseCodeMap = new HashMap<String, String>();
    static HashMap<String, String> morseCodeToEnglishMap = new HashMap<String, String>();
    private static final String BLANK_SPACE=" ";
    MorseCodeTranslator(){
        // Create GUI
        // Set to use darcula theme
        try{
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch(Exception e){
            System.out.println("Error setting theme");
        }

        // Create JFrame
        JFrame frame = new JFrame("Morse Code Translator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Create JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create JPanel for English to Morse Code
        JPanel englishToMorseCode = new JPanel();
        englishToMorseCode.setLayout(new BorderLayout());

        // Create JPanel for Morse Code to English
        JPanel morseCodeToEnglish = new JPanel();
        morseCodeToEnglish.setLayout(new BorderLayout());

        // Add JPanels to JTabbedPane
        tabbedPane.addTab("English to Morse Code", englishToMorseCode);
        tabbedPane.addTab("Morse Code to English", morseCodeToEnglish);

        // Add JTabbedPane to JFrame
        frame.add(tabbedPane);

        // Create JLabel for English to Morse Code
        JLabel englishToMorseCodeLabel = new JLabel("English to Morse Code");
        englishToMorseCodeLabel.setHorizontalAlignment(JLabel.CENTER);
        englishToMorseCode.add(englishToMorseCodeLabel, BorderLayout.NORTH);

        // Create JLabel for Morse Code to English
        JLabel morseCodeToEnglishLabel = new JLabel("Morse Code to English");
        morseCodeToEnglishLabel.setHorizontalAlignment(JLabel.CENTER);
        morseCodeToEnglish.add(morseCodeToEnglishLabel, BorderLayout.NORTH);

        // Create JTextArea for English to Morse Code
        JTextArea englishToMorseCodeTextArea = new JTextArea();
        englishToMorseCodeTextArea.setLineWrap(true);
        englishToMorseCodeTextArea.setWrapStyleWord(true);
        englishToMorseCode.add(englishToMorseCodeTextArea, BorderLayout.CENTER);

        // Create JTextArea for Morse Code to English
        JTextArea morseCodeToEnglishTextArea = new JTextArea();
        morseCodeToEnglishTextArea.setLineWrap(true);
        morseCodeToEnglishTextArea.setWrapStyleWord(true);
        morseCodeToEnglish.add(morseCodeToEnglishTextArea, BorderLayout.CENTER);

        // Create JButton for English to Morse Code
        JButton englishToMorseCodeButton = new JButton("Translate");
        // Set its position to the bottom of the JPanel
        englishToMorseCode.add(englishToMorseCodeButton, BorderLayout.SOUTH);
        englishToMorseCodeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // Get text from JTextArea
                String englishToMorseCodeText = englishToMorseCodeTextArea.getText();

                // Translate text
                String translatedText = translateEnglishToMorseCode(englishToMorseCodeText);

                // Set text in JTextArea
                morseCodeToEnglishTextArea.setText(translatedText);

                // Display the translated text in a dialog box
                JOptionPane.showMessageDialog(frame, translatedText);
            }
        });

        // Create JButton for Morse Code to English
        JButton morseCodeToEnglishButton = new JButton("Translate");
        // Set its position to the bottom of the JPanel
        morseCodeToEnglish.add(morseCodeToEnglishButton, BorderLayout.SOUTH);
        morseCodeToEnglishButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // Get text from JTextArea
                String morseCodeToEnglishText = morseCodeToEnglishTextArea.getText();

                // Translate text
                String translatedText = translateMorseCodeToEnglish(morseCodeToEnglishText);

                // Set text in JTextArea
                englishToMorseCodeTextArea.setText(translatedText);

                // Display the translated text in a dialog box
                JOptionPane.showMessageDialog(frame, translatedText);
            }
        });

        // Set up JFrame and make visible
        frame.setVisible(true);
    }



    // Method to translate English to Morse Code
    private String translateEnglishToMorseCode(String englishToMorseCodeText){
        // Create string to store translated text
        StringBuilder translatedText = new StringBuilder();

        // Loop through each character in the text
        for(int i = 0; i < englishToMorseCodeText.length(); i++){
            // Get the character at the current index
            String currentCharacter = englishToMorseCodeText.substring(i, i + 1);

            // Get the Morse Code translation of the character
            String morseCodeTranslation = englishToMorseCodeMap.get(currentCharacter.toLowerCase());

            // If the character is a space, add a "/" to the translated text
            if(currentCharacter.equals(" ")){
                translatedText.append("/ ");
            }
            // If the character is not a space, add the Morse Code translation to the translated text
            else{
                translatedText.append(morseCodeTranslation).append(" ");
            }
        }

        return translatedText.toString();
    }

    // Method to translate Morse Code to English
    private String translateMorseCodeToEnglish(String morseCodeToEnglishText){

        // Create string to store translated text
        StringBuilder translatedText = new StringBuilder();

        // Split the text into an array of words
        String[] words = morseCodeToEnglishText.split(" ");

        // Loop through each word in the array
        for (String currentWord : words) {
            // Get the current word
            // If the current word is a space, add a "/" to the translated text
            if (currentWord.equals("/")) {
                translatedText.append(BLANK_SPACE);
            }
            // If the current word is not a space, get the English translation of the word
            else {
                String englishTranslation = morseCodeToEnglishMap.get(currentWord);
                // Add the English translation to the translated text
                translatedText.append(englishTranslation);
            }
        }

        return translatedText.toString();
    }

    // Main method
    public static void main(String[] args){
        // Generate HashMap to store English to Morse Code translations
        englishToMorseCodeMap.put("a", ".-");
        englishToMorseCodeMap.put("b", "-...");
        englishToMorseCodeMap.put("c", "-.-.");
        englishToMorseCodeMap.put("d", "-..");
        englishToMorseCodeMap.put("e", ".");
        englishToMorseCodeMap.put("f", "..-.");
        englishToMorseCodeMap.put("g", "--.");
        englishToMorseCodeMap.put("h", "....");
        englishToMorseCodeMap.put("i", "..");
        englishToMorseCodeMap.put("j", ".---");
        englishToMorseCodeMap.put("k", "-.-");
        englishToMorseCodeMap.put("l", ".-..");
        englishToMorseCodeMap.put("m", "--");
        englishToMorseCodeMap.put("n", "-.");
        englishToMorseCodeMap.put("o", "---");
        englishToMorseCodeMap.put("p", ".--.");
        englishToMorseCodeMap.put("q", "--.-");
        englishToMorseCodeMap.put("r", ".-.");
        englishToMorseCodeMap.put("s", "...");
        englishToMorseCodeMap.put("t", "-");
        englishToMorseCodeMap.put("u", "..-");
        englishToMorseCodeMap.put("v", "...-");
        englishToMorseCodeMap.put("w", ".--");
        englishToMorseCodeMap.put("x", "-..-");
        englishToMorseCodeMap.put("y", "-.--");
        englishToMorseCodeMap.put("z", "--..");
        englishToMorseCodeMap.put("1", ".----");
        englishToMorseCodeMap.put("2", "..---");
        englishToMorseCodeMap.put("3", "...--");
        englishToMorseCodeMap.put("4", "....-");
        englishToMorseCodeMap.put("5", ".....");
        englishToMorseCodeMap.put("6", "-....");
        englishToMorseCodeMap.put("7", "--...");
        englishToMorseCodeMap.put("8", "---..");
        englishToMorseCodeMap.put("9", "----.");
        englishToMorseCodeMap.put("0", "-----");
        englishToMorseCodeMap.put(".", ".-.-.-");
        englishToMorseCodeMap.put(",", "--..--");
        englishToMorseCodeMap.put("?", "..--..");
        englishToMorseCodeMap.put("!", "-.-.--");
        englishToMorseCodeMap.put("/", "-..-.");
        englishToMorseCodeMap.put("-", "-....-");
        englishToMorseCodeMap.put("(", "-.--.");
        englishToMorseCodeMap.put(")", "-.--.-");
        englishToMorseCodeMap.put(" ", "/");

        // Invert HashMap to store Morse Code to English translations
        for(String key : englishToMorseCodeMap.keySet()){
            morseCodeToEnglishMap.put(englishToMorseCodeMap.get(key), key);
        }

        // Create new instance of MorseCodeTranslator
        new MorseCodeTranslator();
    }
}
