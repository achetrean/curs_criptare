package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final int k = 3;
    private static final int z = 26;
    private static final int[][] key = {
            { 5,  8,  4},
            { 1,  7, 12},
            {25, 13, 19}
    };
    private static final int[][] invertedKey = {
            { 1, 10, 14},
            { 7,  7, 16},
            {24, 19,  9}
    };

    public static void main(String[] args) {
        List<String> groups = new ArrayList<>();
        List<String> encryptedGroups = new ArrayList<>();
        List<Integer> encryptedGroupsIndexes = new ArrayList<>();
        List<Integer> decryptedGroupsIndexes = new ArrayList<>();
        Map<Character, Integer> alphabet = new HashMap<>();
        Map<Integer, Character> invertedAlphabet = new HashMap<>();
        generateAlphabet(alphabet, invertedAlphabet);
        String textToEncrypt = "OTORINOLARINGOLOGIE".toUpperCase();
        String encryptedText = "";
        String decryptedText = "";
        textToEncrypt = normalizeTextToEncrypt(textToEncrypt);
        System.out.println("Textul spre criptare: " + textToEncrypt);
        groupText(groups, textToEncrypt);
        for (String group : groups) {
            encryptedGroupsIndexes.addAll(getLettersIndexesToEncrypt(group, alphabet));
        }
        encryptedText = getEncryptedText(encryptedGroupsIndexes, invertedAlphabet);
        groupText(encryptedGroups, encryptedText);
        for (String group : encryptedGroups) {
            decryptedGroupsIndexes.addAll(getLettersIndexesToDecrypt(group,alphabet));
        }
        decryptedText = getDecryptedText(decryptedGroupsIndexes, invertedAlphabet);
        System.out.println("Textul criptat: " + encryptedText);
        System.out.println("Textul decriptat: " + decryptedText);
    }

    private static void groupText(List<String> groups, String textToEncrypt) {
        for (int i = 0; i < textToEncrypt.length() / k; i++) {
            groups.add(textToEncrypt.substring(k * i, k * i  + k));
        }
    }

    private static String getEncryptedText(List<Integer> encryptedGroupsIndexes, Map<Integer, Character> invertedAlphabet) {
        String encryptedText = "";
        for (int letterIndex : encryptedGroupsIndexes) {
            encryptedText += String.valueOf(getCharacter(invertedAlphabet, letterIndex));
        }
        return encryptedText;
    }

    private static String getDecryptedText(List<Integer> encryptedGroupsIndexes, Map<Integer, Character> invertedAlphabet) {
        String decryptedText = "";
        for (int letterIndex : encryptedGroupsIndexes) {
            decryptedText += String.valueOf(getCharacter(invertedAlphabet, letterIndex));
        }
        return decryptedText;
    }

    private static List<Integer> getLettersIndexesToEncrypt(String group, Map<Character, Integer> alphabet) {
        List<Integer> encryptedGroups = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0;i < group.length(); i++) {
            indexes.add(getIndex(alphabet, group.charAt(i)));
        }
        for (int j = 0;j < k; j++) {
            int sum = 0;
            for (int i = 0;i < indexes.size(); i++) {
                sum += indexes.get(i) * key[i][j];
            }
            int encryptedLetterIndex = sum % z;
            encryptedGroups.add(encryptedLetterIndex);
        }
        return encryptedGroups;
    }

    private static List<Integer> getLettersIndexesToDecrypt(String group, Map<Character, Integer> alphabet) {
        List<Integer> encryptedGroups = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0;i < group.length(); i++) {
            indexes.add(getIndex(alphabet, group.charAt(i)));
        }
        for (int j = 0;j < k; j++) {
            int sum = 0;
            for (int i = 0;i < indexes.size(); i++) {
                sum += indexes.get(i) * invertedKey[i][j];
            }
            int encryptedLetterIndex = sum % z;
            encryptedGroups.add(encryptedLetterIndex);
        }
        return encryptedGroups;
    }

    private static String normalizeTextToEncrypt(String textToEncrypt) {
        if (textToEncrypt.length() % k != 0) {
            int numberOfLettersToAppend = textToEncrypt.length() % k;
            while (numberOfLettersToAppend < k) {
                textToEncrypt += "Q";
                numberOfLettersToAppend++;
            }
        }
        return textToEncrypt;
    }

    private static void generateAlphabet(Map<Character, Integer> alphabet,
                                         Map<Integer, Character> invertedAlphabet) {
        int index = 0;
        for (int i = 65;i <= 90; i++) {
            alphabet.put((char)i, index);
            invertedAlphabet.put(index, (char)i);
            index++;
        }
    }

    private static int getIndex(Map<Character, Integer> map, char character) {
        return map.get(character);
    }

    private static char getCharacter(Map<Integer, Character> map, int index) {
        return map.get(index);
    }

}
