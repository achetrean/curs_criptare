package com.company;


import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) throws IOException {
        File file = new File("text.txt");
        Scanner scanner = new Scanner(file);
        String textClar = "";
        while (scanner.hasNextLine()) {
            textClar += scanner.nextLine();
        }
        scanner.close();
        Scanner secondScanner = new Scanner(System.in);
        //System.out.print("Introduceti dimensiunea cheii: ");

        //int n = secondScanner.nextInt();
        int n = (int) Math.sqrt(textClar.length());
        n = n % 2 == 0 ? n : n + 1;


        int[][] a = new int[n][n];
        for (int i = 0;i < n; i++) {
            for (int j = 0;j < n; j++) {
                a[i][j] = 2;
            }
        }
        int k = 1;
        int limita = n / 2;
        limita *= limita;
        Random random = new Random();
        while (k <= limita) {
            int i = random.nextInt(n);
            int j = random.nextInt(n);
            boolean q = a[i][j] == 2 && a[j][n - i - 1] == 2;
            boolean w = a[n - j - 1][i] == 2 && a[n - i - 1][n - j - 1] == 2;
            if (q && w) {
                a[i][j] = 0;
                a[j][n - i - 1] = 1;
                a[n - j - 1][i] = 1;
                a[n - i - 1][n - j - 1] = 1;
                k++;
            }
        }
        for (int i = 0;i < n; i++) {
            for (int j = 0;j < n; j++) {
                System.out.printf("%4d",a[i][j]);
            }
            System.out.println("");
        }
        char[][] charArr = new char[n][n];
        int counter = 0;
        for (int i = 0;i < n; i++) {
            for (int j = 0;j < n; j++) {
                if (counter < textClar.length()) {
                    charArr[i][j] = textClar.charAt(counter);
                    counter++;
                } else {
                    charArr[i][j] = '*';
                }
            }
        }
        System.out.println("\n==================================");
        for (int i = 0;i < n; i++) {
            for (int j = 0;j < n; j++) {
                System.out.printf("%4c",charArr[i][j]);
            }
            System.out.println("");
        }
        String textCript = "";
        for (int i = 0;i < n; i++) {
            for (int j = 0;j < n; j++) {
                if (a[i][j] == 0) {
                    textCript += String.valueOf(charArr[i][j]);
                    System.out.print(charArr[i][j]);
                }
            }
        }
        System.out.println("");
        for (int i = 0;i < n / 2; i++) {
            for (int j = i;j < n - i - 1;j++) {
                int temp = a[i][j];
                a[i][j] = a[n - 1 - j][i];
                a[n - 1 - j][i] = a[n - 1 -i][n - 1 -j];
                a[n - 1 - i][n - 1 - j] = a[j][n - 1 - i];
                a[j][n - 1 - i] = temp;
            }
        }
        System.out.println("");
        for (int i = 0;i < n; i++) {
            for (int j = 0;j < n; j++) {
                System.out.printf("%4d",a[i][j]);
            }
            System.out.println("");
        }

        for (int i = 0;i < n; i++) {
            for (int j = 0;j < n; j++) {
                if (a[i][j] == 0) {
                    textCript += String.valueOf(charArr[i][j]);
                    System.out.print(charArr[i][j]);
                }
            }
        }
        System.out.println("");
        for (int i = 0;i < n / 2; i++) {
            for (int j = i;j < n - i - 1;j++) {
                int temp = a[i][j];
                a[i][j] = a[n - 1 - j][i];
                a[n - 1 - j][i] = a[n - 1 -i][n - 1 -j];
                a[n - 1 - i][n - 1 - j] = a[j][n - 1 - i];
                a[j][n - 1 - i] = temp;
            }
        }
        System.out.println("");
        for (int i = 0;i < n; i++) {
            for (int j = 0;j < n; j++) {
                System.out.printf("%4d",a[i][j]);
            }
            System.out.println("");
        }

        for (int i = 0;i < n; i++) {
            for (int j = 0;j < n; j++) {
                if (a[i][j] == 0) {
                    textCript += String.valueOf(charArr[i][j]);
                    System.out.print(charArr[i][j]);
                }
            }
        }
        System.out.println("");
        for (int i = 0;i < n / 2; i++) {
            for (int j = i;j < n - i - 1;j++) {
                int temp = a[i][j];
                a[i][j] = a[n - 1 - j][i];
                a[n - 1 - j][i] = a[n - 1 -i][n - 1 -j];
                a[n - 1 - i][n - 1 - j] = a[j][n - 1 - i];
                a[j][n - 1 - i] = temp;
            }
        }
        System.out.println("");
        for (int i = 0;i < n; i++) {
            for (int j = 0;j < n; j++) {
                System.out.printf("%4d",a[i][j]);
            }
            System.out.println("");
        }

        for (int i = 0;i < n; i++) {
            for (int j = 0;j < n; j++) {
                if (a[i][j] == 0) {
                    textCript += String.valueOf(charArr[i][j]);
                    System.out.print(charArr[i][j]);
                }
            }
        }
        for (int i = 0;i < n / 2; i++) {
            for (int j = i;j < n - i - 1;j++) {
                int temp = a[i][j];
                a[i][j] = a[n - 1 - j][i];
                a[n - 1 - j][i] = a[n - 1 -i][n - 1 -j];
                a[n - 1 - i][n - 1 - j] = a[j][n - 1 - i];
                a[j][n - 1 - i] = temp;
            }
        }
        System.out.println("");

        char[][] finalArr = new char[n][n];
        int secondCounter = 0;
        String textDecript = "";
        for (int i = 0;i < n; i++) {
            for (int j = 0;j < n; j++) {
                if (a[i][j] == 0) {
                    finalArr[i][j] = textCript.charAt(secondCounter);
                    secondCounter++;
                }
            }
        }
        for (int i = 0;i < n / 2; i++) {
            for (int j = i;j < n - i - 1;j++) {
                int temp = a[i][j];
                a[i][j] = a[n - 1 - j][i];
                a[n - 1 - j][i] = a[n - 1 -i][n - 1 -j];
                a[n - 1 - i][n - 1 - j] = a[j][n - 1 - i];
                a[j][n - 1 - i] = temp;
            }
        }

        for (int i = 0;i < n; i++) {
            for (int j = 0;j < n; j++) {
                if (a[i][j] == 0) {
                    finalArr[i][j] = textCript.charAt(secondCounter);
                    secondCounter++;
                }
            }
        }
        for (int i = 0;i < n / 2; i++) {
            for (int j = i;j < n - i - 1;j++) {
                int temp = a[i][j];
                a[i][j] = a[n - 1 - j][i];
                a[n - 1 - j][i] = a[n - 1 -i][n - 1 -j];
                a[n - 1 - i][n - 1 - j] = a[j][n - 1 - i];
                a[j][n - 1 - i] = temp;
            }
        }

        for (int i = 0;i < n; i++) {
            for (int j = 0;j < n; j++) {
                if (a[i][j] == 0) {
                    finalArr[i][j] = textCript.charAt(secondCounter);
                    secondCounter++;
                }
            }
        }
        for (int i = 0;i < n / 2; i++) {
            for (int j = i;j < n - i - 1;j++) {
                int temp = a[i][j];
                a[i][j] = a[n - 1 - j][i];
                a[n - 1 - j][i] = a[n - 1 -i][n - 1 -j];
                a[n - 1 - i][n - 1 - j] = a[j][n - 1 - i];
                a[j][n - 1 - i] = temp;
            }
        }

        for (int i = 0;i < n; i++) {
            for (int j = 0;j < n; j++) {
                if (a[i][j] == 0) {
                    finalArr[i][j] = textCript.charAt(secondCounter);
                    secondCounter++;
                }
            }
        }
        for (int i = 0;i < n / 2; i++) {
            for (int j = i;j < n - i - 1;j++) {
                int temp = a[i][j];
                a[i][j] = a[n - 1 - j][i];
                a[n - 1 - j][i] = a[n - 1 -i][n - 1 -j];
                a[n - 1 - i][n - 1 - j] = a[j][n - 1 - i];
                a[j][n - 1 - i] = temp;
            }
        }

        for (int i = 0;i < n; i++) {
            for (int j = 0;j < n; j++) {
                if (finalArr[i][j] != '*') {
                    textDecript += String.valueOf(finalArr[i][j]);
                }
            }
        }
        System.out.println("\n===================================================\nTextul initial: " + textClar);
        System.out.println("Textul criptat: " + textCript);
        System.out.println("Textul decriptat:" + textDecript);
    }
}
