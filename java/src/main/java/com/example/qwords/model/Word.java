package com.example.qwords.model;

import java.util.Arrays;

public class Word {
    private String word;
    private char[] characters;

    public Word(String word) {
        this.word = word;
        this.characters = word.toCharArray();
    }

    public String getWord() {
        return word;
    }

    public boolean contains(char c) {
        for (char ch : characters) {
            if (ch == c) {
                return true;
            }
        }
        return false;
    }

    public boolean isCorrect(char[] guess) {
        return Arrays.equals(characters, guess);
    }

    public String getInfo(String guess) {
        char[] guessArray = guess.toCharArray();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < guessArray.length; i++) {
            char currentGuess = guessArray[i];
            if (contains(currentGuess)) {
                if (characters[i] == currentGuess) {
                    result.append('+');
                } else {
                    result.append('?');
                }
            } else {
                result.append('X');
            }
        }

        return result.toString();
    }
}

