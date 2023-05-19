package com.example.palindrom.DataBase;

import com.example.palindrom.entity.PalindromicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalindromsRepository extends JpaRepository<PalindromicEntity, Long> {

}
