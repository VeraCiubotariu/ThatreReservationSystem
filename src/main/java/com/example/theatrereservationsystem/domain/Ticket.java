package com.example.theatrereservationsystem.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Ticket extends Entity<Integer> {
    private LocalDateTime saleDate;
    private String clientFirstName;
    private String clientLastName;
    private String clientEmail;
    private String clientPhoneNumber;
    private Show show;
    private List<Seat> seats;

    public Ticket(LocalDateTime saleDate, String clientFirstName, String clientLastName, String clientEmail, String clientPhoneNumber, Show show, List<Seat> seats) {
        this.saleDate = saleDate;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientEmail = clientEmail;
        this.clientPhoneNumber = clientPhoneNumber;
        this.show = show;
        this.seats = seats;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
