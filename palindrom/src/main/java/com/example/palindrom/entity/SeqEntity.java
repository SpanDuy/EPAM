package com.example.palindrom.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "increment_id")
public class SeqEntity {
    @Id
    @Column(name = "program_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long programId;
    public SeqEntity(Long programId) {
        this.programId = programId;
    }
    public SeqEntity() {
    }
    public Long getProgramIdId() {
        return programId;
    }

}
