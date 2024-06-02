package com.example.theatrereservationsystem.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@jakarta.persistence.Entity
@Table(name = "tickets")
public class Ticket extends Entity<Integer> {
    private LocalDateTime saleDate;
    private String clientFirstName;
    private String clientLastName;
    private String clientEmail;
    private String clientPhoneNumber;
    private Show show;
    private Set<Seat> seats;

    public Ticket(LocalDateTime saleDate, String clientFirstName, String clientLastName, String clientEmail, String clientPhoneNumber, Show show, Set<Seat> seats) {
        this.saleDate = saleDate;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientEmail = clientEmail;
        this.clientPhoneNumber = clientPhoneNumber;
        this.show = show;
        this.seats = seats;
    }

    public Ticket() {

    }

    @Column(name = "sale_date")
    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    @Column(name = "client_first_name")
    public String getClientFirstName() {
        return clientFirstName;
    }

    @Column(name = "client_last_name")
    public String getClientLastName() {
        return clientLastName;
    }

    @Column(name = "client_email")
    public String getClientEmail() {
        return clientEmail;
    }

    @Column(name = "client_phone_number")
    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    @ManyToOne
    @JoinColumn(name = "show_id")
    public Show getShow() {
        return show;
    }

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "tickets_seats",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id"))
    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }
}
