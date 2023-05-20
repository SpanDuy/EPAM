
package com.example.palindrom.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "futer_id")
public class IncrementEntity {
    @Id
    @Column(name = "prodram_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public IncrementEntity() {
    }
    public IncrementEntity(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
}
