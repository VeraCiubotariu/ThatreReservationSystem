package com.example.theatrereservationsystem.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.time.LocalDateTime;

@jakarta.persistence.Entity
@Table(name = "shows")
public class ShowDTO extends Entity<Integer> {

    private int soldTickets;
    private String actors;
    private int duration;
    private ShowType genre;
    private String name;
    private LocalDateTime date;

    public ShowDTO(int soldTickets, String actors, int duration, ShowType genre, String name, LocalDateTime date) {
        this.soldTickets = soldTickets;
        this.actors = actors;
        this.duration = duration;
        this.genre = genre;
        this.name = name;
        this.date = date;
    }

    public ShowDTO() {

    }

    @Transient
    public int getSoldTickets() {
        return soldTickets;
    }

    @Column(name = "actors")
    public String getActors() {
        return actors;
    }

    @Column(name = "duration")
    public int getDuration() {
        return duration;
    }

    @Column(name = "genre")
    public ShowType getGenre() {
        return genre;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "date")
    public LocalDateTime getDate() {
        return date;
    }

    public void setSoldTickets(int soldTickets) {
        this.soldTickets = soldTickets;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setGenre(ShowType genre) {
        this.genre = genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
