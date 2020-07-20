package com.jeansemolini.libraryapi.api.exception;

import com.jeansemolini.libraryapi.exception.BusinnessException;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiErrors {
    private List<String> errors;

    public ApiErrors(BindingResult bindingResult) {
        this.errors = new ArrayList<>();
        bindingResult.getAllErrors().forEach( error -> errors.add(error.getDefaultMessage()) );
    }

    public ApiErrors(BusinnessException ex) {
        this.errors = Arrays.asList(ex.getMessage());
    }

    public List<String> getErrors() {
        return errors;
    }
}
