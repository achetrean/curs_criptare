package com.company;

import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Map<Character, Integer> alphabet = new HashMap<>();
        Map<Integer, Character> invertedAlphabet = new HashMap<>();
        List<String> groups = new ArrayList<>();
        List<Integer> groupsIndexes = new ArrayList<>();
        List<Integer> encryptedGroupsIndexes = new ArrayList<>();
        List<Integer> decryptedGroupsIndexes = new ArrayList<>();
        List<Integer> listOfPs = new ArrayList<>();
        List<Integer> listOfEs = new ArrayList<>();
        String textToEncrypt = "GRUPA";
        System.out.println("Textul introdus este: " + textToEncrypt);
        String encryptedText = "";
        String decryptedText = "";
        if (textToEncrypt.length() % 2 == 1) {
            textToEncrypt += "Q";
            System.out.println("Textul spre criptare este: " + textToEncrypt);
        }
        generateAlphabet(alphabet, invertedAlphabet);
        groupInitialText(alphabet, groups, groupsIndexes, textToEncrypt);
        for (int group : groupsIndexes) {
            encryptEachGroup(encryptedGroupsIndexes, listOfPs, listOfEs, group);
        }
        encryptedText = getEncryptedText(encryptedGroupsIndexes);
        System.out.println("Textul criptat este: " + encryptedText);
        for (int i = 0; i < encryptedGroupsIndexes.size(); i++) {
            if (decryptEachGroup(encryptedGroupsIndexes, decryptedGroupsIndexes,
                    listOfPs, i, listOfEs, encryptedText)) { break; }
        }
        decryptedText = getDecryptedText(invertedAlphabet, decryptedGroupsIndexes);
        System.out.println("Textul decriptat este: " + decryptedText);
    }

    private static String getEncryptedText(List<Integer> encryptedGroupsIndexes) {
        String encryptedText = "";
        for (int group : encryptedGroupsIndexes) {
            int firstIndex = group / 100;
            int secondIndex = group - firstIndex * 100;
            encryptedText += firstIndex + "" +secondIndex + " ";
        }
        return encryptedText;
    }

    private static String getDecryptedText(Map<Integer, Character> invertedAlphabet, List<Integer> decryptedGroupsIndexes) {
        String decryptedText = "";
        for (int group : decryptedGroupsIndexes) {
            int firstIndex;
            int secondIndex;
            if (group < 30) {
                firstIndex = 0;
                secondIndex = group;
            } else {
                firstIndex = group / 100;
                secondIndex = group - firstIndex * 100;
            }
            decryptedText += String.valueOf(getCharacter(invertedAlphabet,firstIndex)) +
                    String.valueOf(getCharacter(invertedAlphabet,secondIndex));
        }
        return decryptedText;
    }

    private static boolean decryptEachGroup(List<Integer> encryptedGroupsIndexes, List<Integer> decryptedGroupsIndexes,
                                            List<Integer> listOfPs, int i, List<Integer> listOfEs, String encryptedText) {
        int e = encryptedGroupsIndexes.get(i);
        int e2 = listOfEs.get(i);
        int p = listOfPs.get(i);
        int a = 1;
        if (moduloInverse(e2,p - 1) != 1) {
            a = moduloInverse(e2,p - 1);
        } else {
            System.out.println("Mesajul " + encryptedText + " nu poate fi decriptat");
            return true;
        }
        List<Integer> binaryRepresentation = convertToBinary(a);
        int z = 1;
        for (int digit : binaryRepresentation) {
            z = ((int)Math.pow(z, 2)) % p;
            if (digit == 1) {
                z = z * e % p;
            }
        }
        decryptedGroupsIndexes.add(z);
        return false;
    }

    private static void encryptEachGroup(List<Integer> encryptedGroupsIndexes, List<Integer> listOfPs,
                                         List<Integer> listOfEs, int group) {
        System.out.println("Introduceti perechea de numere (e,p) pentru care se respecta conditiile, " +
                "p PRIM mai mare decat " + group + " si (e,p - 1) = 1, adica e si p - 1 sunt reciproc prime");
        System.out.print("e = ");
        int e = scanner.nextInt();
        System.out.print("p = ");
        int p = scanner.nextInt();
        if (isInputWrong(e , p - 1, group)) {
            while(isInputWrong(e, p - 1, group)) {
                if (group >= p) {
                    System.out.println("Valoare lui p: " + p + ", nu este mai mare ca valoarea lui M: " + group);
                } else {
                    System.out.println("Numerele " + e + " si " + (p - 1) + " nu sunt reciproc prime. Reintroduceti valorile.");
                }
                System.out.print("e = ");
                e = scanner.nextInt();
                System.out.print("p = ");
                p = scanner.nextInt();
            }
        }
        listOfPs.add(p);
        listOfEs.add(e);
        List<Integer> binaryRepresentation = convertToBinary(e);
        int z = 1;
        for (int digit : binaryRepresentation) {
            z = ((int)Math.pow(z, 2)) % p;
            if (digit == 1) {
                z = z * group % p;
            }
        }
        encryptedGroupsIndexes.add(z);
    }

    private static List<Integer> convertToBinary(int e) {
        List<Integer> binaryRepresentation = new ArrayList<>();
        while (e != 0) {
            binaryRepresentation.add(e % 2);
            e /= 2;
        }
        Collections.reverse(binaryRepresentation);
        return binaryRepresentation;
    }

    private static void generateAlphabet(Map<Character, Integer> alphabet, Map<Integer, Character> invertedAlphabet) {
        int index = 0;
        for (int i = 65;i <= 90; i++) {
            alphabet.put((char)i, index);
            invertedAlphabet.put(index, (char)i);
            index++;
        }
    }

    private static void groupInitialText(Map<Character, Integer> alphabet, List<String> groups, List<Integer> groupsIndexes, String encryptedText) {
        for (int i = 0; i < encryptedText.length() - 1; i += 2) {
            String group = String.valueOf(encryptedText.charAt(i)) + String.valueOf(encryptedText.charAt(i + 1));
            int firstElementIndex = getIndex(alphabet, encryptedText.charAt(i));
            int secondElementIndex = getIndex(alphabet, encryptedText.charAt(i + 1));
            int groupIndex = firstElementIndex * 100 + secondElementIndex;
            groups.add(group);
            groupsIndexes.add(groupIndex);
        }
    }

    private static int getIndex(Map<Character, Integer> map, char character) {
        return map.get(character);
    }

    private static char getCharacter(Map<Integer, Character> map, int index) {
        return map.get(index);
    }

    private static boolean isInputWrong(int firstNumber, int secondNumber, int thirdNumber) {
        if (thirdNumber >= secondNumber) {
            return true;
        }
        for (int d = 2;d < Math.max(firstNumber, secondNumber); d++) {
            if (firstNumber % d == 0 && secondNumber % d == 0) {
                return true;
            }
        }
        return false;
    }

    private static int moduloInverse(int e, int p) {
        for (int i = 1; i < p; i++)
            if (((e%p) * (i%p)) % p == 1)
                return i;
        return 1;
    }
}
