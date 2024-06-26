package com.dewitt.petshopapi.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyRegisteredException extends RuntimeException {
    public AlreadyRegisteredException() {
        super("Already registered");
    }
}