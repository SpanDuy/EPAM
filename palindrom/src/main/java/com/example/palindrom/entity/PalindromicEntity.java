package com.example.palindrom.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "new_table")
public class PalindromicEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "word")
    private String word;
    @Column(name = "is_palindromic")
    private Boolean isPalindromic;
    public PalindromicEntity(Long id, String word, Boolean isPalindromic) {
        this.id = id;
        this.word = word;
        this.isPalindromic = isPalindromic;
    }
    public PalindromicEntity(String word, Boolean isPalindromic) {
        this.word = word;
        this.isPalindromic = isPalindromic;
    }
    public PalindromicEntity() {
    }
    public Long getId() {
        return id;
    }

    public String getWord() {
        return word;
    }
    public Boolean getIsPalindromic() {
        return isPalindromic;
    }
}
