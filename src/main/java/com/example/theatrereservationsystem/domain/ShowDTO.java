package com.example.theatrereservationsystem.domain;

import javafx.scene.image.Image;

import java.time.LocalDateTime;

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

    public int getSoldTickets() {
        return soldTickets;
    }

    public String getActors() {
        return actors;
    }

    public int getDuration() {
        return duration;
    }

    public ShowType getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
