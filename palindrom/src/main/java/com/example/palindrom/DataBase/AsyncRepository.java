package com.example.palindrom.DataBase;

import com.example.palindrom.entity.AsyncEntity;
import com.example.palindrom.entity.PalindromicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AsyncRepository extends JpaRepository<AsyncEntity, Long> {
}
