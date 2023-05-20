package com.example.palindrom.memory;
import com.example.palindrom.word.Palindromic;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InMemoryStorage
{
    private Map<String, Palindromic> dataStorage = new HashMap<String, Palindromic>();
    public void saveWordResponse(Palindromic response) {
        dataStorage.put(response.getWord(), response);
    }
    public Palindromic getSavedWordResponse(String id)
    {
        return dataStorage.get(id);
    }
    public Integer size()
    {
        return dataStorage.size();
    }
    public Collection<Palindromic> getAllSavedWordResponse() {
        return dataStorage.values();
    }
}