package com.example.palindrom.validators;

public class WordValidator {
    public Boolean maxLengthError(String word) {
        if (word.length() > 20) {
            return true;
        }
        return false;
    }
    public Boolean isNotNumerical(String word) {
        for (int i = 0; i < word.length(); i++)
            if (word.charAt(i) < 'a' || word.charAt(i) > 'z') {
                return true;
        }
        return false;
    }
}
