package com.example.palindrom.word;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Palindromic {
    private String word;
    private Boolean isPalindromic;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
    public Boolean getIsPalindromic() {
        return isPalindromic;
    }
    public void setIsPalindromic(Boolean isPalindromic) {
        this.isPalindromic = isPalindromic;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Palindromic that = (Palindromic) o;
        return Objects.equals(word, that.word) && Objects.equals(isPalindromic, that.isPalindromic);
    }
}
