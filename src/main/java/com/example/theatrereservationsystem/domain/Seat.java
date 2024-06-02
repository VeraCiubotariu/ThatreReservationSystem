package com.example.theatrereservationsystem.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@jakarta.persistence.Entity
@Table(name = "seats")
public class Seat extends Entity<String> {
    private float price;
    private Set<Ticket> tickets = new HashSet<>();

    public Seat(float price) {
        this.price = price;
    }

    public Seat() {

    }

    @Column(name = "price")
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @ManyToMany(mappedBy = "seats", fetch = FetchType.EAGER)
    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
