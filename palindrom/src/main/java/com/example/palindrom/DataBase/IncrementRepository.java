package com.example.palindrom.DataBase;

import com.example.palindrom.entity.AsyncEntity;
import com.example.palindrom.entity.IncrementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncrementRepository extends JpaRepository<IncrementEntity, Long> {
}
