package com.example.theatrereservationsystem.domain;

public class Seat extends Entity<Integer> {
    private String row;
    private int number;
    private float price;
    private SeatState state;

    public Seat(String row, int number, float price, SeatState state) {
        this.row = row;
        this.number = number;
        this.price = price;
        this.state = state;
    }

    public String getRow() {
        return row;
    }

    public int getNumber() {
        return number;
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
