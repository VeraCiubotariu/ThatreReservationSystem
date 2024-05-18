package com.example.theatrereservationsystem.domain;

public class Seat extends Entity<String> {
    private float price;

    public Seat(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }
}
