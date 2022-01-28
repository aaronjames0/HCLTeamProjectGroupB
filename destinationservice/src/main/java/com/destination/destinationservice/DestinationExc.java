package com.destination.destinationservice;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class DestinationExc extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public DestinationExc(String message) {
        super(message);
    }

    public DestinationExc(String message, Throwable throwable) {
        super(message, throwable);
    }
}