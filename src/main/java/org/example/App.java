package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args) throws IOException {

        fillList(new BufferedReader((new FileReader ("words.txt"))), new TreeMap<String, Integer>())
        .forEach((key, value)-> System.out.println(key + " = " + value ));

    }

    public static Map<String, Integer> fillList(BufferedReader reader, Map<String, Integer> wordsList) throws IOException {
        readLine(reader, wordsList, reader.readLine());
        return wordsList;
    }
    public static Map<String, Integer> readLine(BufferedReader reader, Map<String, Integer> wordsList, String line) throws IOException {
        if (line != null) {
            readWordsFromLine(wordsList, new StringTokenizer(line));
            return fillList(reader, wordsList);
        }
        reader.close();
        return wordsList;
    }

    public static void readWordsFromLine(Map<String, Integer> wordsList, StringTokenizer line) {
        while (line.hasMoreTokens()) {
            checkIfWordExists(cleanWord(line.nextToken()), wordsList);
        }
    }

    public static void checkIfWordExists(String word, Map<String, Integer> wordList) {
        if (wordList.containsKey(word)) {
            wordList.put(word, wordList.get(word) + 1);
        } else {
            wordList.put(word, 1);
        }
    }

    public static String cleanWord(String word) {
        return removeLastCharacter (
                removeFirstCharacter(word.replaceAll("[`,.;!\"]", "").toLowerCase(), '\''),
                '\''
        );
    }

    public static String removeFirstCharacter(String word, char specialChar) {
        if (word.charAt(0) == specialChar) {
            return word.substring(1);
        }

        return word;
    }

    public static String removeLastCharacter(String word, char specialChar) {
        if (word.charAt(word.length() - 1) == specialChar) {
            return word.substring(0, word.length() - 1);
        }

        return word;
    }
}
