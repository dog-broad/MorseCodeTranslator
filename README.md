# Morse Code Translator

The Morse Code Translator is a Java program that translates text messages between English and Morse code. The program provides a graphical user interface (GUI) with two tabs—one for English to Morse Code translation and another for Morse Code to English translation.

## Dependencies
The program uses the following dependencies:
- `com.formdev.flatlaf.FlatDarkLaf`: A library for applying the FlatDarkLaf theme to the GUI.  
- The necessary jar file can be found [here](https://jar-download.com/artifact-search/flatlaf).

## Running the Morse Code Translator Program
To run the Morse Code Translator program, follow these steps:

1. Set up the Java Development Environment: Make sure you have Java Development Kit (JDK) installed on your computer. If not, download and install the latest JDK from the official Oracle website.

2. Download the Source Code: Obtain the source code for the Morse Code Translator program and save it with a `.java` extension (e.g., `MorseCodeTranslator.java`). Or you can clone this repo using the following command:
    ```
    git clone https://github.com/dog-broad/MorseCodeTranslator
    ```

3. Compile the Java File: Open a command prompt or terminal and navigate to the directory where the Java file is saved. Compile the Java file by running the following command:

   ```
   javac MorseCodeTranslator.java
   ```

   This command will compile the Java source code and generate the corresponding bytecode.

4. Run the Program: After successful compilation, run the program by executing the following command:

   ```
   java MorseCodeTranslator
   ```

   The Java Virtual Machine (JVM) will execute the bytecode, and the Morse Code Translator GUI will appear.

5. Use the Morse Code Translator: Follow the instructions provided in the README to utilize the English to Morse Code and Morse Code to English translation functionalities of the program.

Note: Make sure you have a compatible Java version installed on your system, and the necessary dependencies are available in the classpath while compiling and running the program.

## Usage
To use the Morse Code Translator, follow these steps:

1. Launch the program by running the `main` method in the `MorseCodeTranslator` class.
2. The GUI will appear with two tabs—English to Morse Code and Morse Code to English.
3. In the English to Morse Code tab, enter the text you want to translate into the provided `JTextArea`.
4. Click the "Translate" button. The program will convert the text to Morse code and display the translated text in the Morse Code to English `JTextArea`.
5. In the Morse Code to English tab, enter the Morse code you want to translate into the provided `JTextArea`.
6. Click the "Translate" button. The program will convert the Morse code to English and display the translated text in the English to Morse Code `JTextArea`.
7. A dialog box will also appear with the translated text.

## Additional Information
- The program uses two `HashMap` objects: `englishToMorseCodeMap` and `morseCodeToEnglishMap`. These maps store the translations between English characters and Morse code.
- The GUI is created using Swing components, including `JFrame`, `JTabbedPane`, `JPanel`, `JLabel`, `JTextArea`, and `JButton`.
- The program applies the FlatDarkLaf theme to the GUI, providing a sleek and modern appearance.

## Customization
- You can modify the translations in the `englishToMorseCodeMap` `HashMap` to add or change English to Morse code translations.
- If desired, you can customize the GUI further by adjusting the layout, appearance, or adding additional components.

## Note
- The program assumes standard Morse code conventions where a space is represented by a forward slash ("/").
- The translations are case-insensitive, and special characters such as punctuation marks are supported.

Enjoy using the Morse Code Translator!
