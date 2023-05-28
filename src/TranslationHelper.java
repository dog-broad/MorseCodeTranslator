import java.util.HashMap;

public class TranslationHelper {
    public static String translateEnglishToMorseCode(String englishToMorseCodeText, HashMap<String, String> englishToMorseCodeMap) {
        StringBuilder translatedText = new StringBuilder();

        // Loop through each character in the text area and translate it to Morse Code
        for (int i = 0; i < englishToMorseCodeText.length(); i++) {
            String currentCharacter = englishToMorseCodeText.substring(i, i + 1);
            // Get the Morse Code translation for the current character
            String morseCodeTranslation = englishToMorseCodeMap.get(currentCharacter.toLowerCase());

            if (currentCharacter.equals(" ")) {
                translatedText.append("/ ");
            } else {
                translatedText.append(morseCodeTranslation).append(" ");
            }
        }

        return translatedText.toString();
    }

    public static String translateMorseCodeToEnglish(String morseCodeToEnglishText, HashMap<String, String> morseCodeToEnglishMap) {
        StringBuilder translatedText = new StringBuilder();
        // Split the text area by spaces
        String[] words = morseCodeToEnglishText.split(" ");

        // Loop through each word in the text area and translate it to English
        for (String currentWord : words) {
            if (currentWord.equals("/")) {
                translatedText.append(" ");
            } else {
                String englishTranslation = morseCodeToEnglishMap.get(currentWord);
                translatedText.append(englishTranslation);
            }
        }

        return translatedText.toString();
    }
}
