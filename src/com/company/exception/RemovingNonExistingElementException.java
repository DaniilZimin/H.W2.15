package com.company.exception;

public class RemovingNonExistingElementException extends RuntimeException{
    public RemovingNonExistingElementException(String message) {
        super(message);
    }
}
