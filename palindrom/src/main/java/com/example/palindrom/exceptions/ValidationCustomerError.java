package com.example.palindrom.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.LinkedList;
import java.util.List;
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ValidationCustomerError {
    private final List<String> errormessages = new LinkedList<>();
    private String status;

    public List<String> getErrormessages(){
        return errormessages;
    }
    public void addErrormessage(String errormessage){
        this.errormessages.add(errormessage);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonIgnore
    public boolean isEmpty() {
        if(errormessages.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
