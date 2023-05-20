package com.example.palindrom.contoller;

import com.example.palindrom.entity.Response;
import com.example.palindrom.entity.ResponseStats;
import com.example.palindrom.entity.ResponsesSize;
import com.example.palindrom.exceptions.ValidationCustomerError;
import com.example.palindrom.memory.InMemoryStorage;
import com.example.palindrom.newService.AsyncPalindromicService;
import com.example.palindrom.newService.DataBaseService;
import com.example.palindrom.newService.IncrementService;
import com.example.palindrom.newService.ServiceWord;
import com.example.palindrom.validators.Validator;
import com.example.palindrom.word.Palindromic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ControllerTest {

    private final Validator validator = mock(Validator.class);
    private final ServiceWord serviceWord = mock(ServiceWord.class);
    @Mock
    private final InMemoryStorage inMemoryStorage = mock(InMemoryStorage.class);
    private final ResponseStats responseStats = mock(ResponseStats.class);
    private final DataBaseService dataBaseService = mock(DataBaseService.class);
    private final AsyncPalindromicService asyncPalindromicService = mock(AsyncPalindromicService.class);
    private final IncrementService incrementService = mock(IncrementService.class);
    @InjectMocks
    private Controller controller = new Controller(validator, serviceWord, inMemoryStorage,
            dataBaseService, asyncPalindromicService, incrementService);
    @Test
    void servicingWord() throws Exception {
        String testWord = "qwewq";
        Palindromic palindromic = new Palindromic();

        palindromic.setWord(testWord);
        palindromic.setIsPalindromic(true);

        ValidationCustomerError validationCustomerError = new ValidationCustomerError();

        Response response = new Response(palindromic);
        ResponseEntity responseEntity = new ResponseEntity(response, HttpStatus.OK);

        doNothing().when(inMemoryStorage).saveWordResponse(response.getPalindromic());

        when(serviceWord.isPalindromic(testWord)).thenReturn(Boolean.TRUE);
        when(validator.validateParameter(testWord)).thenReturn(validationCustomerError);

        ResponseEntity result = controller.servicingWord(testWord);
        verify(inMemoryStorage, times(1)).saveWordResponse(response.getPalindromic());
        assertEquals(responseEntity, result);
    }
    @Test
    void servicingWord1() throws Exception {
        String testWord = "qwerty";
        ValidationCustomerError errors = new ValidationCustomerError();

        when(validator.validateParameter(testWord)).thenReturn(errors);

        Response response = new Response(errors);
        ResponseEntity responseEntity = new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);

        doNothing().when(inMemoryStorage).saveWordResponse(response.getPalindromic());
        verify(inMemoryStorage, times(0)).saveWordResponse(response.getPalindromic());

        assertEquals(responseEntity, controller.servicingWord(testWord));
    }
    @Test
    void servicingWord2() throws Exception {
        String testWord = "";
        ValidationCustomerError errors = new ValidationCustomerError();
        errors.addErrormessage("Word is Empty");

        when(validator.validateParameter(testWord)).thenReturn(errors);

        Response response = new Response(errors);
        ResponseEntity responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);

        assertEquals(responseEntity, controller.servicingWord(testWord));
    }
    @Test
    public void testGetAllSavedResults(){
        when(inMemoryStorage.getAllSavedWordResponse()).thenReturn(null);

        ResponseEntity<Object> response = controller.getAllWords();
        Object result = response.getBody();
        Assertions.assertNull(result);

    }
    @Test
    public void testGelAllSavedResultsSize(){
        when(inMemoryStorage.size()).thenReturn(0);

        ResponseEntity<Object> response = controller.getAllWordSize();
        ResponsesSize result = (ResponsesSize) response.getBody();
        assertEquals(new ResponsesSize(0), result);
    }
    @Test
    public void testCalculateBulkParams2(){
        List<String> bodyList = new LinkedList<>();
        bodyList.add("qwewq");

        List<Response> responseList = new LinkedList<>();
        Palindromic palindromic = new Palindromic();

        palindromic.setWord(bodyList.get(0));
        palindromic.setIsPalindromic(true);

        Response response = new Response(palindromic);

        responseList.add(response);

        ValidationCustomerError validationCustomerError = new ValidationCustomerError();

        doNothing().when(inMemoryStorage).saveWordResponse(palindromic);

        List<Boolean> serviceList = new LinkedList<>();
        serviceList.add(true);
        when(serviceWord.isPalindromicList2(bodyList)).thenReturn(serviceList);
        when(validator.validateParameter(bodyList.get(0))).thenReturn(validationCustomerError);

        ResponseStats responseStats = new ResponseStats(responseList.toArray());
        responseStats.setMax(0);
        responseStats.setMin(0);
        responseStats.setAver((double) 0);

        List<Palindromic> palindromicList = new LinkedList<>();
        palindromicList.add(palindromic);
        when(serviceWord.findMaxOfResult(palindromicList)).thenReturn(0);
        when(serviceWord.findMinOfResult(palindromicList)).thenReturn(0);
        when(serviceWord.calculateAverOfResult(palindromicList)).thenReturn((double) 0);

        ResponseEntity responseEntity = new ResponseEntity(responseStats, HttpStatus.CREATED);

        verify(inMemoryStorage, times(1)).saveWordResponse(palindromic);
        assertEquals(responseEntity, controller.calculateBulkParams2(bodyList));
    }
    @Test
    public void testCalculateBulkParams21(){
        List<String> bodyList = new LinkedList<>();
        bodyList.add("");

        List<Response> responseList = new LinkedList<>();
        Palindromic palindromic = new Palindromic();

        palindromic.setWord(bodyList.get(0));
        palindromic.setIsPalindromic(true);

        ValidationCustomerError validationCustomerError = new ValidationCustomerError();
        validationCustomerError.addErrormessage("Word is Empty");

        Response response = new Response(validationCustomerError);

        responseList.add(response);

        when(serviceWord.isPalindromicList2(bodyList)).thenReturn(null);
        when(validator.validateParameter(bodyList.get(0))).thenReturn(validationCustomerError);

        ResponseStats responseStats = new ResponseStats(responseList.toArray());
        responseStats.setMax(0);
        responseStats.setMin(0);
        responseStats.setAver((double) 0);

        List<Palindromic> palindromicList = new LinkedList<>();
        palindromicList.add(palindromic);
        when(serviceWord.findMaxOfResult(palindromicList)).thenReturn(0);
        when(serviceWord.findMinOfResult(palindromicList)).thenReturn(0);
        when(serviceWord.calculateAverOfResult(palindromicList)).thenReturn((double) 0);

        ResponseEntity responseEntity = new ResponseEntity(responseStats, HttpStatus.CREATED);

        assertEquals(responseEntity, controller.calculateBulkParams2(bodyList));
    }
    @Test
    public void testCalculateBulkParams22(){
        List<String> bodyList = new LinkedList<>();
        bodyList.add("qwerty");

        List<Response> responseList = new LinkedList<>();
        Palindromic palindromic = new Palindromic();

        palindromic.setWord(bodyList.get(0));
        palindromic.setIsPalindromic(true);

        ValidationCustomerError validationCustomerError = new ValidationCustomerError();

        Response response = new Response(validationCustomerError);

        responseList.add(response);

        when(serviceWord.isPalindromicList2(bodyList)).thenReturn(null);
        when(validator.validateParameter(bodyList.get(0))).thenReturn(validationCustomerError);

        ResponseStats responseStats = new ResponseStats(responseList.toArray());
        responseStats.setMax(0);
        responseStats.setMin(0);
        responseStats.setAver((double) 0);

        List<Palindromic> palindromicList = new LinkedList<>();
        palindromicList.add(palindromic);
        when(serviceWord.findMaxOfResult(palindromicList)).thenReturn(0);
        when(serviceWord.findMinOfResult(palindromicList)).thenReturn(0);
        when(serviceWord.calculateAverOfResult(palindromicList)).thenReturn((double) 0);

        ResponseEntity responseEntity = new ResponseEntity(responseStats, HttpStatus.CREATED);

        assertEquals(responseEntity, controller.calculateBulkParams2(bodyList));
    }
}