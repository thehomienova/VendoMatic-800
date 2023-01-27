package com.techelevator.exceptions;

public class DataFileNotFoundException extends RuntimeException{
    public DataFileNotFoundException(String message) {
        super(message);
    }
}
