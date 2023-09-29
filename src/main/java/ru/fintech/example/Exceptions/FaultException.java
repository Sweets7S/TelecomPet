package ru.fintech.example.Exceptions;

public class FaultException extends Exception{

    private long faultCode;

    public FaultException(long faultCode, String message) {
        super(message);
        this.faultCode = faultCode;
    }

    public long getFaultCode() {
        return faultCode;
    }
}
