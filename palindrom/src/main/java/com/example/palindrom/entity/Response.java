package com.example.palindrom.entity;

import com.example.palindrom.exceptions.ValidationCustomerError;
import com.example.palindrom.word.Palindromic;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private ValidationCustomerError validationCustomerError;
    private Palindromic palindromic;
    //private ResponseStats responseStats;
    public Response(ValidationCustomerError validationCustomerError, Palindromic palindromic) {
        this.validationCustomerError = validationCustomerError;
        this.palindromic = palindromic;
    }
    public Response(ValidationCustomerError validationCustomerError) {
        this.validationCustomerError = validationCustomerError;
    }
    public Response(Palindromic palindromic) {
        this.palindromic = palindromic;
    }
    public Palindromic getPalindromic() {
        return palindromic;
    }
    public ValidationCustomerError getValidationCustomerError() {
        return validationCustomerError;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Response)) return false;
        Response response = (Response) o;
        return Objects.equals(validationCustomerError, response.validationCustomerError) && Objects.equals(palindromic, response.palindromic);
    }
    //public ResponseStats getResponseStats() { return responseStats; }
    //public void setResponseStats(ResponseStats responseStats) { this.responseStats = responseStats; }
}
