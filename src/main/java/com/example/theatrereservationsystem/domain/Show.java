package com.example.theatrereservationsystem.domain;

import jakarta.persistence.*;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;

@jakarta.persistence.Entity
@Table(name = "shows")
public class Show extends Entity<Integer> {
    private LocalDateTime date;
    private String name;
    private String actors;
    private ShowType genre;
    private String description;
    private int duration;
    private String director;

    private Administrator admin;
    private byte[] poster;

    public Show(LocalDateTime date, String name, String actors, ShowType genre, String description, int duration, String director, Administrator admin, byte[] poster) {
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

    public Show() {

    }

    @Column(name = "date")
    public LocalDateTime getDate() {
        return date;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "actors")
    public String getActors() {
        return actors;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    public ShowType getGenre() {
        return genre;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "duration")
    public int getDuration() {
        return duration;
    }

    @Column(name = "director")
    public String getDirector() {
        return director;
    }

    @ManyToOne
    @JoinColumn(name = "admin_id")
    public Administrator getAdmin() {
        return admin;
    }

    @Lob
    @Column(name = "poster")
    public byte[] getPoster() {
        return poster;
    }

    @Transient
    public Image getPosterImage() {
        if (poster != null) {
            InputStream is = new ByteArrayInputStream(poster);
            return new Image(is);
        }
        return null;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setGenre(ShowType genre) {
        this.genre = genre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    public void setPoster(byte[] poster) {
        this.poster = poster;
    }
}
