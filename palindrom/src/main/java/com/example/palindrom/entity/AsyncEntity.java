package com.example.palindrom.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "new_table_async")
public class AsyncEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "program_id")
    private Integer programId;
    @Column(name = "word")
    private String word;
    @Column(name = "is_palindromic")
    private Boolean isPalindromic;
    //public AsyncEntity(Long id, String word, Boolean isPalindromic) {
    //    this.id = id;
    //    this.word = word;
    //    this.isPalindromic = isPalindromic;
   // }
    public AsyncEntity(Integer programId, String word, Boolean isPalindromic) {
        this.programId = programId;
        this.word = word;
        this.isPalindromic = isPalindromic;
    }
    //public AsyncEntity(String word, Boolean isPalindromic) {
    //    this.word = word;
    //    this.isPalindromic = isPalindromic;
    //}
    public AsyncEntity() {
    }
    public Long getId() {
        return id;
    }
    public Integer getProgramIdId() {
        return programId;
    }

    public String getWord() {
        return word;
    }
    public Boolean getIsPalindromic() {
        return isPalindromic;
    }
}
