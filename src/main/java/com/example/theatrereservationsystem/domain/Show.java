package com.example.theatrereservationsystem.domain;

import javafx.scene.image.Image;

import java.time.LocalDateTime;

public class Show extends Entity<Integer> {
    private LocalDateTime date;
    private String name;
    private String actors;
    private ShowType genre;
    private String description;
    private int duration;
    private String director;
    private Administrator admin;
    private Image poster;

    public Show(LocalDateTime date, String name, String actors, ShowType genre, String description, int duration, String director, Administrator admin, Image poster) {
        this.date = date;
        this.name = name;
        this.actors = actors;
        this.genre = genre;
        this.description = description;
        this.duration = duration;
        this.director = director;
        this.admin = admin;
        this.poster = poster;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getActors() {
        return actors;
    }

    public ShowType getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public String getDirector() {
        return director;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public Image getPoster() {
        return poster;
    }
}
