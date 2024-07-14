import java.util.HashMap;
import java.util.Map;

public class MorseCodeTranslator {
    private static final Map<Character, String> englishToMorse = new HashMap<>();
    private static final Map<String, Character> morseToEnglish = new HashMap<>();

    static {
        // Initialize the mappings
        String[] english = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.,?!'()&:;=+-_$@".split("");
        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
                "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
                "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", ".-.-.-", "--..--",
                "..--..", "-.-.--", ".-..-.", ".----.", "-.--.", "-.--.-", ".-...", "---...", "-.-.-.", "-...-",
                ".-.-.", "-....-", "..--.-", "...-..-", ".--.-."};

        for (int i = 0; i < english.length; i++) {
            englishToMorse.put(english[i].charAt(0), morse[i]);
            morseToEnglish.put(morse[i], english[i].charAt(0));
        }
    }

    public static String toMorse(String englishText) {
        StringBuilder morseText = new StringBuilder();
        for (char c : englishText.toUpperCase().toCharArray()) {
            if (englishToMorse.containsKey(c)) {
                morseText.append(englishToMorse.get(c)).append(" ");
            } else {
                morseText.append(" ");
            }
        }
        return morseText.toString().trim();
    }

    public static String toEnglish(String morseText) {
        StringBuilder englishText = new StringBuilder();
        for (String morseChar : morseText.split(" ")) {
            if (morseToEnglish.containsKey(morseChar)) {
                englishText.append(morseToEnglish.get(morseChar));
            } else {
                englishText.append(" ");
            }
        }
        return englishText.toString().trim();
    }
}
