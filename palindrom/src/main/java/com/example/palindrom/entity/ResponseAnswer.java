package com.example.palindrom.entity;

public class ResponseAnswer {
    private String word;
    private Boolean answer;
    public ResponseAnswer(String word, Boolean answer) {
        this.word = word;
        this.answer = answer;
    }
    public String getWord() { return word; }
    public void setWord(String word) { this.word = word; }
    public Boolean getAnswer() { return answer; }
    public void setAnswer(Boolean answer) { this.answer = answer; }
}
