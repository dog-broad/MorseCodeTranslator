import java.util.HashMap;

public class MorseCodeTranslator {
    public static void main(String[] args) {
        // Generate HashMap to store English to Morse Code translations
        HashMap<String, String> englishToMorseCodeMap = new HashMap<>();
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
        HashMap<String, String> morseCodeToEnglishMap = new HashMap<>();
        for (String key : englishToMorseCodeMap.keySet()) {
            morseCodeToEnglishMap.put(englishToMorseCodeMap.get(key), key);
        }

        MorseCodeGUI gui = new MorseCodeGUI(englishToMorseCodeMap, morseCodeToEnglishMap);
        gui.show();
    }
}
