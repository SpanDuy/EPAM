package com.example.palindrom.newService;

import com.example.palindrom.counters.CounterService;
import com.example.palindrom.word.Palindromic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ServiceWord {

    private final CounterService counterService;

    @Autowired
    public ServiceWord(CounterService service)
    {
        this.counterService = service;
    }

    //public ServiceWord() {}

    public Boolean isPalindromic(String word) {
        counterService.incrementSimpleCount();
        counterService.increment();

        for (int i = 0; i < word.length() >> 1; i++){
            if (word.charAt(i) != word.charAt(word.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }/*
    public List<Palindromic> isPalindromicList(List<String> wordList) {
        List<Palindromic> answers = new LinkedList<>();
        wordList.forEach((currentElement) -> {
            Palindromic temp = new Palindromic();
            temp.setWord(currentElement);
            temp.setIsPalindromic(isPalindromic(temp.getWord()));
            answers.add(temp);
        });
        return answers;
    }*/


    public List<Boolean> isPalindromicList2(List<String> wordList) {
        List<Boolean> answers = new LinkedList<>();
        wordList.forEach((currentElement) -> {
            answers.add(isPalindromic(currentElement));
        });
        return answers;
    }
    public Double calculateAverOfResult(List<Palindromic> resultList) {
        Double aver = 0.0;
        if (!resultList.isEmpty()) {
            aver = resultList.stream()
                    .map(Palindromic::getWord)
                    .mapToInt(String::length)
                    .average()
                    .orElse(0.0);
        }
        return aver;
    }

    public Integer findMinOfResult(List<Palindromic> resultList) {
        int min = 0;
        if (!resultList.isEmpty()) {
            min = resultList.stream()
                    .map(Palindromic::getWord)
                    .map(String::length)
                    .min(Integer::compareTo)
                    .orElse(0);
        }
        return min;
    }

    public Integer findMaxOfResult(List<Palindromic> resultList) {
        int max = 0;
        if (!resultList.isEmpty()) {
            max = resultList.stream()
                    .map(Palindromic::getWord)
                    .map(String::length)
                    .max(Integer::compareTo)
                    .orElse(0);
        }
        return max;
    }
}
