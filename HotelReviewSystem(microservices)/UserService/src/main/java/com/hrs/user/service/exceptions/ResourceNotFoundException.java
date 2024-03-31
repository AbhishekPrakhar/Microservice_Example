package com.hrs.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    // extra properties can be added
    public ResourceNotFoundException() {
        super("Resource not found on server !!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
