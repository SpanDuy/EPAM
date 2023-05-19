package com.example.palindrom.counters;

import org.springframework.stereotype.Component;

@Component
public class CounterService {
    private Integer count = 0;
    private Integer simpleCount = 0;
    public synchronized void increment() {
        count++;
    }
    public Integer getCount() {
        return count;
    }
    public void incrementSimpleCount() {
        simpleCount++;
    }
    public Integer getSimpleCount() {
        return simpleCount;
    }
    public void cleanCountService() {
        simpleCount = 0;
        count = 0;
    }
}
