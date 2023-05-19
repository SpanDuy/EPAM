package com.example.palindrom.validators;

import com.example.palindrom.contoller.Controller;
import com.example.palindrom.exceptions.ValidationCustomerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    public ValidationCustomerError validateParameter(String palindromic){
        ValidationCustomerError response = new ValidationCustomerError();
        WordValidator validator = new WordValidator();

        if (palindromic.isEmpty()) {
            logger.info("Word is Empty");
            //response = new ValidationCustomerError();
            response.addErrormessage("Word is Empty");
        }
        if (validator.maxLengthError(palindromic)) {
            logger.info("Word can't be longer than 20 symbols");
            response.addErrormessage("Word can't be longer than 20 symbols");
        }
        if (validator.isNotNumerical(palindromic)) {
            logger.info("Word can't be Numerical");
            response.addErrormessage("Word can't be Numerical");
        }

        return response;
    }

    /*
    public Response returnEntity() {

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
     */
}
