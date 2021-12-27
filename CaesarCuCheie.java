package com.company;


import java.util.*;

public class Main2 {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<String> allFourLettersWords = new ArrayList<>();
        List<String> existingFourLettersWordsDatabase = new ArrayList<>();
        String encryptedText = "VSBGJFYSTXBT";
        String finalDecryptedText = "";
        String decryptionKey = "";
        String decryptionAlphabet = "";
        boolean isTextDecrypted = false;
        int k = 17;

        generateAllFourLettersWords(allFourLettersWords);
        populateDatabase(existingFourLettersWordsDatabase);

        boolean flag = true;
        while (flag) {
            for (String word : allFourLettersWords) {
                if (isWordContained(existingFourLettersWordsDatabase, word)) {
                    Map<Character, Integer> alphabet = new HashMap<>();
                    Map<Integer, Character> invertedAlphabet = new HashMap<>();
                    String key = word;
                    String decryptedText = "";
                    for (int i = 65;i <= 90; i++) {
                        key = key.contains(String.valueOf((char)i)) ? key : key + String.valueOf((char)i);
                    }
                    for (int i = 0;i < key.length(); i++) {
                        alphabet.put(key.charAt(i),i);
                        invertedAlphabet.put(i, key.charAt(i));
                    }
                    for (int i = 0;i < encryptedText.length(); i++) {
                        int characterIndex = findIndex(alphabet, encryptedText.charAt(i));
                        int difference = characterIndex - k;
                        int Dk = difference > 0 ? (characterIndex - k) % 26 : (difference + 26) % 26;
                        decryptedText += String.valueOf(findLetter(invertedAlphabet, Dk));
                    }
                    System.out.print("Este textul decriptat: " + decryptedText + " un text clar?y/n: ");
                    String response = scanner.next();
                    if (response.equals("y")) {
                        finalDecryptedText = decryptedText;
                        isTextDecrypted = true;
                        decryptionAlphabet = key;
                        decryptionKey = key.substring(0,4);
                        flag = false;
                        break;
                    }
                }
            }
        }
        if (isTextDecrypted) {
            System.out.println("Textul clar decriptat este: " + finalDecryptedText + ", " +
                    "cheia este: " + decryptionKey  + ", alfabetul este: " + decryptionAlphabet);
        } else {
            System.out.println("Textul " + encryptedText + " nu poate fi decriptat!");
        }
    }

    private static void generateAllFourLettersWords(List<String> list) {
        for (int i = 65; i <= 90; i++) {
            for (int j = 65; j <= 90; j++) {
                for (int k = 65; k <= 90; k++) {
                    for (int l = 65; l <= 90; l++) {
                        if (i != j && i != k && i != l && j != k && j != l && k != l) {
                            String auxStr = String.valueOf((char) i) + String.valueOf((char) j) +
                                    String.valueOf((char) k) + String.valueOf((char) l);
                            list.add(auxStr);
                        }
                    }
                }
            }
        }
    }

    private static void populateDatabase(List<String> listOfWords) {
        listOfWords.add("ABUR"); listOfWords.add("ACRU"); listOfWords.add("ABUR"); listOfWords.add("ALES");
        listOfWords.add("ABIL"); listOfWords.add("ADIO"); listOfWords.add("AFIS"); listOfWords.add("APOI");
        listOfWords.add("ARIE"); listOfWords.add("AVIZ"); listOfWords.add("AZIL"); listOfWords.add("BASM");
        listOfWords.add("BILA"); listOfWords.add("BINE"); listOfWords.add("BLOC"); listOfWords.add("BRAD");
        listOfWords.add("BRAV"); listOfWords.add("BUZA"); listOfWords.add("CALD"); listOfWords.add("CALE");
        listOfWords.add("CEAS"); listOfWords.add("CERB"); listOfWords.add("CENT"); listOfWords.add("CHEL");
        listOfWords.add("CINA"); listOfWords.add("CHEF"); listOfWords.add("CHIP"); listOfWords.add("CHIN");
        listOfWords.add("CORP"); listOfWords.add("CONT"); listOfWords.add("DANS"); listOfWords.add("DEAL");
        listOfWords.add("DISC"); listOfWords.add("DOMN"); listOfWords.add("DRAG"); listOfWords.add("DRUM");
        listOfWords.add("DUEL"); listOfWords.add("FIER"); listOfWords.add("FAPT"); listOfWords.add("FALS");
        listOfWords.add("FLUX"); listOfWords.add("FURT"); listOfWords.add("FRIG"); listOfWords.add("FUGA");
    }

    private static boolean isWordContained(List<String> listToCheck, String wordToCheck) {
        for (String word : listToCheck) {
            if (word.equals(wordToCheck)) {
                return true;
            }
        }
        return false;
    }

    private static int findIndex(Map<Character, Integer> map, char character) {
        return map.get(character);
    }

    private static char findLetter(Map<Integer, Character> map, int index) {
        return map.get(index);
    }

}
