package com.example.theatrereservationsystem.service;

public class ServiceException extends RuntimeException {
    public ServiceException(String message){
        super(message);
    }
}
