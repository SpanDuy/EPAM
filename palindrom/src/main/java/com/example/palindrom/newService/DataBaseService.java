package com.example.palindrom.newService;

import com.example.palindrom.DataBase.PalindromsRepository;
import com.example.palindrom.entity.PalindromicEntity;
import com.example.palindrom.word.Palindromic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataBaseService {
    @Autowired
    private PalindromsRepository repository;
    public void savePalindroms(List<Palindromic> palindromicList) {
        palindromicList.forEach(palindromic -> savePalindromic(palindromic));
    }
    public void savePalindromic(Palindromic palindromic) {
        PalindromicEntity entity = new PalindromicEntity(palindromic.getWord(), palindromic.getIsPalindromic());
        repository.save(entity);
    }
    public List<PalindromicEntity> getAllPalindromic() {
        return repository.findAll();
    }
    public Optional<PalindromicEntity> getPalindromic(Long id) {
        return repository.findById(id);
    }
}
