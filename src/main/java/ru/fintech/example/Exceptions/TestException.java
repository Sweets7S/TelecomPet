package ru.fintech.example.Exceptions;

public class TestException extends Exception{

    private long faultCode;

    public TestException(long faultCode, String message) {
        super(message);
        this.faultCode = faultCode;
    }

    public long getFaultCode() {
        return faultCode;
    }
}
