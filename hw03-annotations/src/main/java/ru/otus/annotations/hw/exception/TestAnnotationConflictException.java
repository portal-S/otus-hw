package ru.otus.annotations.hw.exception;

public class TestAnnotationConflictException extends RuntimeException {

    public TestAnnotationConflictException(String methodName) {
        super(String.format("Test method annotations conflict: there are more than 1 test annotation for test method '%s'", methodName));
    }
}