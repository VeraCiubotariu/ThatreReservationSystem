package com.example.theatrereservationsystem.domain;

public class Seat extends Entity<String> {
    private float price;
    private SeatState state;

    public Seat(float price, SeatState state) {
        this.price = price;
        this.state = state;
    }

    public float getPrice() {
        return price;
    }

    public SeatState getState() {
        return state;
    }

    public void setState(SeatState state) {
        this.state = state;
    }
}
