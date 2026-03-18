package com.ty.exception;

public class InvalidRectangleException extends RuntimeException {
    public InvalidRectangleException() {
        super();
    }

    public InvalidRectangleException(String s) {
        super(s);
    }
}
