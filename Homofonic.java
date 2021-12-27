package com.company;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        Map<Character, List<Integer>> map = new HashMap<>();
        List<Integer> listOfAllElements = new ArrayList<>();
        generateAlphabets(map, listOfAllElements);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti un text pentru criptare: ");
        String initialText = scanner.nextLine().toUpperCase();
        String encryptedText = "";
        String decryptedText = "";
        encryptedText = getEncryptedText(map, initialText, encryptedText);
        System.out.println("Textul criptat: " + encryptedText);
        String[] encryptedTextParts = encryptedText.split(",");
        decryptedText = getDecryptedText(map, decryptedText, encryptedTextParts);
        System.out.println("Textul decriptat este: " + decryptedText);
    }

    private static String getDecryptedText(Map<Character, List<Integer>> map, String decryptedText, String[] encryptedTextParts) {
        for (String part : encryptedTextParts) {
            int num = Integer.parseInt(part);
            for (Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
                Character key = entry.getKey();
                List<Integer> localList = map.get(key);
                for (int el : localList) {
                    if (num == el) {
                        decryptedText += String.valueOf(key);
                        break;
                    }
                }
            }
        }
        return decryptedText;
    }

    private static boolean isContained(List<Integer> list, int number) {
        return list.stream().anyMatch(element -> element == number);
    }

    private static String getEncryptedText(Map<Character, List<Integer>> map, String initialText, String encryptedText) {
        for (int i = 0; i < initialText.length(); i++) {
            List<Integer> localList = map.get(initialText.charAt(i));
            int num = localList.get(ThreadLocalRandom.current().nextInt(0,localList.size()));
            if (i != initialText.length() - 1) {
                encryptedText += num + ",";
            } else {
                encryptedText += String.valueOf(num);
            }
        }
        return encryptedText;
    }

    private static void generateAlphabets(Map<Character, List<Integer>> map, List<Integer> listOfAllElements) {
        for (int i = 65;i <= 90; i++) {
            char ch = (char)i;
            int listLength = ThreadLocalRandom.current().nextInt(1,5 + 1);
            List<Integer> localList = new ArrayList<>();
            for (int j = 0;j < listLength; j++) {
                int element = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                if (i == 65 && j == 0) {
                    listOfAllElements.add(element);
                    localList.add(element);
                } else {
                    if (isContained(listOfAllElements,element)) {
                        while (isContained(listOfAllElements, element)) {
                            element = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                        }
                    }
                    listOfAllElements.add(element);
                    localList.add(element);
                }
            }
            map.put(ch, localList);
        }
        int listLength = ThreadLocalRandom.current().nextInt(1,5 + 1);
        List<Integer> list = new ArrayList<>();
        for (int i = 0;i < listLength; i++) {
            int element = ThreadLocalRandom.current().nextInt(1, 100 + 1);
            if (isContained(listOfAllElements, element)) {
                while (isContained(listOfAllElements, element)) {
                    element = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                }
            }
            list.add(element);
        }
        map.put(' ', list);
    }
}
