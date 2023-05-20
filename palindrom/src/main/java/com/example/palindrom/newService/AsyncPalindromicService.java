package com.example.palindrom.newService;

import com.example.palindrom.DataBase.AsyncRepository;
import com.example.palindrom.DataBase.PalindromsRepository;
import com.example.palindrom.contoller.Controller;
import com.example.palindrom.entity.AsyncEntity;
import com.example.palindrom.entity.PalindromicEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
@Service
@EnableAsync
public class AsyncPalindromicService {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final ServiceWord serviceWord;
    private final AsyncRepository asyncRepository;
    @Autowired
    public AsyncPalindromicService(ServiceWord serviceWord, AsyncRepository asyncRepository) {
        this.serviceWord = serviceWord;
        this.asyncRepository = asyncRepository;
    }
    public void makeAsyncCall(Integer programId, String word) {
        logger.info("Start makeAsyncCall");
        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                logger.info("run calculation palindromic");
                Boolean isPalindromic = serviceWord.isPalindromic(word);
                AsyncEntity asyncEntity = new AsyncEntity(programId, word, isPalindromic);
                logger.info("save entity");
                asyncRepository.save(asyncEntity);
            }
        });
    }
    public Optional<AsyncEntity> getAsyncPalindromic(Long id) {
        return asyncRepository.findById(id);
    }
}
