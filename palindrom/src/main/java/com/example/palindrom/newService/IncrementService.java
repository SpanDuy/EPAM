package com.example.palindrom.newService;

import com.example.palindrom.DataBase.IncrementRepository;
import com.example.palindrom.entity.IncrementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IncrementService {
    @Autowired
    private IncrementRepository incrementRepository;
    public void saveIncrement() {
        IncrementEntity incrementEntity = new IncrementEntity();
        incrementRepository.save(incrementEntity);
    }
    //public List<PalindromicEntity> getAllPalindromic() {
    //    return repository.findAll();
    //}
    public IncrementEntity getIncrement() {
        List<IncrementEntity> entityList = new LinkedList<>();
        entityList = incrementRepository.findAll();
        //IncrementEntity = new IncrementEntity(); entityList.stream().max(Comparator.comparingLong(IncrementEntity::getId));
        Collections.sort(entityList, (e1, e2) -> Long.compare(e2.getId(), e1.getId()));
        IncrementEntity incrementEntity = entityList.get(0);

        return incrementEntity;
    }
}
