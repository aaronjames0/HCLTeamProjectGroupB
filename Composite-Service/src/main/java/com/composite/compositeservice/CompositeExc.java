package com.composite.compositeservice;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CompositeExc extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public CompositeExc(String message) {
        super(message);
    }
}