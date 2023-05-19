package com.example.palindrom.newService;

import com.example.palindrom.DataBase.PalindromsRepository;
import com.example.palindrom.contoller.Controller;
import com.example.palindrom.entity.PalindromicEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
@Service
@EnableAsync
public class AsyncPalindromicService {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final ServiceWord serviceWord;
    private final PalindromsRepository palindromsRepository;
    @Autowired
    public AsyncPalindromicService(ServiceWord serviceWord, PalindromsRepository palindromsRepository) {
        this.serviceWord = serviceWord;
        this.palindromsRepository = palindromsRepository;
    }
    public void makeAsyncCall(Integer programId, String word) {
        logger.info("Start makeAsyncCall");
        CompletableFuture<Void> futere = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                logger.info("run calculation palindromic");
                Boolean isPalindromic = serviceWord.isPalindromic(word);
                PalindromicEntity palindromicEntity = new PalindromicEntity(programId, word, isPalindromic);
                logger.info("save entity");
                palindromsRepository.save(palindromicEntity);
            }
        });
    }
}
