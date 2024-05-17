package com.example.theatrereservationsystem.domain;

import java.time.LocalDate;

public class CreditCard {
    private String number;
    private String holder;
    private String cvv;
    private LocalDate expiryDate;

    public CreditCard(String number, String holder, String cvv, LocalDate expiryDate) {
        this.number = number;
        this.holder = holder;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    public String getNumber() {
        return number;
    }

    public String getHolder() {
        return holder;
    }

    public String getCvv() {
        return cvv;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
