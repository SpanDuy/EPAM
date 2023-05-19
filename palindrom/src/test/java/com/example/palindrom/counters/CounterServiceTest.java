package com.example.palindrom.counters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CounterServiceTest {
    CounterService counterService = new CounterService();
    @Test
    public void testIncrement() throws InterruptedException{
        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                counterService.increment();
            }
        };
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        assertEquals(200, counterService.getCount().intValue());
    }
}
