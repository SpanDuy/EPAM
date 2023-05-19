package com.example.palindrom.contoller;

import com.example.palindrom.counters.CounterService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RequestCounterControllerTest {
    private final CounterService counterService = mock(CounterService.class);
    @InjectMocks
    private RequestCounterController controller = new RequestCounterController(counterService);
    @Test
    void getCounter() throws Exception {
        when(counterService.getCount()).thenReturn(100);
        when(counterService.getSimpleCount()).thenReturn(98);

        ResponseEntity responseEntity = new ResponseEntity<>(counterService, HttpStatus.OK);

        assertEquals(responseEntity, controller.getCounter());
    }
    @Test
    void resetCounter() {
        doNothing().when(counterService).cleanCountService();

        assertEquals("Reseted", controller.resetCounter());
    }
}