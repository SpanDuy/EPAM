package com.example.palindrom.contoller;

import com.example.palindrom.entity.Response;
import com.example.palindrom.entity.ResponseStats;
import com.example.palindrom.entity.ResponsesSize;
import com.example.palindrom.exceptions.ValidationCustomerError;
import com.example.palindrom.memory.InMemoryStorage;
import com.example.palindrom.newService.AsyncPalindromicService;
import com.example.palindrom.newService.DataBaseService;
import com.example.palindrom.newService.ServiceWord;
import com.example.palindrom.validators.Validator;
import com.example.palindrom.word.Palindromic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final Validator validator;
    private final ServiceWord service;
    private final InMemoryStorage inMemoryStorage;
    private final DataBaseService dataBaseService;
    private final AsyncPalindromicService asyncPalindromicService;
    @Autowired
    public Controller(Validator validator, ServiceWord service, InMemoryStorage inMemoryStorage,
                      DataBaseService dataBaseService, AsyncPalindromicService asyncPalindromicService) {
        this.validator = validator;
        this.service = service;
        this.inMemoryStorage = inMemoryStorage;
        this.dataBaseService = dataBaseService;
        this.asyncPalindromicService = asyncPalindromicService;
    }
    @GetMapping(value = "/palindromic", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> servicingWord(@RequestParam String word) throws Exception {

        ValidationCustomerError errors = validator.validateParameter(word);
        Palindromic palindromic = new Palindromic();

        Response response;

        if (errors.getErrormessages().size() != 0){
            response = new Response(errors);
            response.getValidationCustomerError().setStatus(HttpStatus.BAD_REQUEST.name());
            logger.error("1. word argument is not valid");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            if (!word.equals("qwerty")){
                palindromic.setWord(word);
                response = new Response(palindromic);
                response.getPalindromic().setIsPalindromic(service.isPalindromic(response.getPalindromic().getWord()));
                inMemoryStorage.saveWordResponse(response.getPalindromic());
                dataBaseService.savePalindromic(palindromic);
                return ResponseEntity.ok(response);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            errors.addErrormessage("Internal Server Error occured");
            response = new Response(errors);
            response.getValidationCustomerError().setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
            logger.error("2. Internal Server Error occured");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/responses")
    public ResponseEntity<Object> getAllWords() {
        return new ResponseEntity<>(inMemoryStorage.getAllSavedWordResponse(), HttpStatus.OK);
    }
    @GetMapping("/responses/size")
    public ResponseEntity<Object> getAllWordSize() {
        return new ResponseEntity<>(new ResponsesSize(inMemoryStorage.size()), HttpStatus.OK);
    }
    /*
    @PostMapping("/bulck")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> calculateBulkParams(@RequestBody List<String> bodyList) {

        List<Response> responseList = new LinkedList<>();

        bodyList.forEach((currentElement) -> {
            ValidationCustomerError errors = validator.validateParameter(currentElement);
            Palindromic palindromic = new Palindromic();
            Response response;

            if (errors.getErrormessages().size() != 0){
                response = new Response(errors);
                response.getValidationCustomerError().setStatus(HttpStatus.BAD_REQUEST.name());
                responseList.add(response);
                return;
            }
            try {
                if (!currentElement.equals("qwerty")){
                    palindromic.setWord(currentElement);
                    response = new Response(palindromic);
                    response.getPalindromic().setIsPalindromic(service.isPalindromic(response.getPalindromic().getWord()));
                    inMemoryStorage.saveWordResponse(response.getPalindromic());
                    responseList.add(response);
                    return;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                response = new Response(errors);
                response.getValidationCustomerError().setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
                responseList.add(response);
                return;
            }
        });
        List<Palindromic> palindromicList = new LinkedList<>();
        responseList.forEach((currentElement) -> {
            if (currentElement.getPalindromic() != null) {
                palindromicList.add(currentElement.getPalindromic());
            }
        });
        ResponseStats responseStats = new ResponseStats(responseList.toArray());
        responseStats.setMax(service.findMaxOfResult(palindromicList));
        responseStats.setMin(service.findMinOfResult(palindromicList));
        responseStats.setAver(service.calculateAverOfResult(palindromicList));

        return new ResponseEntity<>(responseStats, HttpStatus.CREATED);
    }

     */

    @PostMapping("/bulck2")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> calculateBulkParams2(@RequestBody List<String> bodyList) {

        List<Response> responseList = new LinkedList<>();
        List<String> wordList = new LinkedList<>();

        bodyList.forEach((currentElement) -> {
            ValidationCustomerError errors = validator.validateParameter(currentElement);
            Response response;

            if (errors.getErrormessages().size() != 0){
                response = new Response(errors);
                response.getValidationCustomerError().setStatus(HttpStatus.BAD_REQUEST.name());
                responseList.add(response);
                return;
            }
            try {
                if (!currentElement.equals("qwerty")){
                    wordList.add(currentElement);
                    return;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                response = new Response(errors);
                response.getValidationCustomerError().setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
                responseList.add(response);
                return;
            }
        });

        List<Boolean> booleanList = service.isPalindromicList2(wordList);

        for (int i = 0; i < booleanList.size(); i++) {
            Palindromic tempPalindromic = new Palindromic();
            tempPalindromic.setWord(wordList.get(i));
            tempPalindromic.setIsPalindromic(booleanList.get(i));
            responseList.add(new Response(tempPalindromic));
        }

        List<Palindromic> palindromicList = new LinkedList<>();
        responseList.forEach((currentElement) -> {
            if (currentElement.getPalindromic() != null) {
                palindromicList.add(currentElement.getPalindromic());
                inMemoryStorage.saveWordResponse(currentElement.getPalindromic());
            }
        });

        dataBaseService.savePalindroms(palindromicList);
        ResponseStats responseStats = new ResponseStats(responseList.toArray());
        responseStats.setMax(service.findMaxOfResult(palindromicList));
        responseStats.setMin(service.findMinOfResult(palindromicList));
        responseStats.setAver(service.calculateAverOfResult(palindromicList));

        return new ResponseEntity<>(responseStats, HttpStatus.CREATED);
    }
    @GetMapping(value = "/showDataBase", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showDataBase() {
        return new ResponseEntity<>(dataBaseService.getAllPalindromic(), HttpStatus.OK);
    }
    @GetMapping(value = "/getWithId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showDataBase(@RequestParam Long id) {
        return new ResponseEntity<>(dataBaseService.getPalindromic(id), HttpStatus.OK);
    }
    @GetMapping(value = "/async", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> asyncWord(@RequestParam String word) throws Exception {

        ValidationCustomerError errors = validator.validateParameter(word);
        Palindromic palindromic = new Palindromic();

        Response response;

        if (errors.getErrormessages().size() != 0){
            response = new Response(errors);
            response.getValidationCustomerError().setStatus(HttpStatus.BAD_REQUEST.name());
            logger.error("1. word argument is not valid");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            if (!word.equals("qwerty")){
                palindromic.setWord(word);
                response = new Response(palindromic);
                response.getPalindromic().setIsPalindromic(service.isPalindromic(response.getPalindromic().getWord()));
                inMemoryStorage.saveWordResponse(response.getPalindromic());
                dataBaseService.savePalindromic(palindromic);
                return ResponseEntity.ok(response);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            errors.addErrormessage("Internal Server Error occured");
            response = new Response(errors);
            response.getValidationCustomerError().setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
            logger.error("2. Internal Server Error occured");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



