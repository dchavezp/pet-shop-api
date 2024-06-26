package com.dewitt.petshopapi.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Resource not found");
    }
}