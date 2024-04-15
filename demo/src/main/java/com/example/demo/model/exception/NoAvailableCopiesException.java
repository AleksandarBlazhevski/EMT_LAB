package com.example.demo.model.exception;

public class NoAvailableCopiesException extends RuntimeException{

    public NoAvailableCopiesException(String bookName) {
        super(String.format("No available copies for %s exception", bookName));
    }
}
