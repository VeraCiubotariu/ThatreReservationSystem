package com.example.theatrereservationsystem.domain;

import javafx.scene.image.Image;

import java.time.LocalDateTime;

public class ShowDTO extends Show{
    private int noTicketsSold;

    public ShowDTO(LocalDateTime date, String name, String actors, ShowType genre, String description, int duration,
                   String director, Administrator admin, Image poster, int noTicketsSold) {
        super(date, name, actors, genre, description, duration, director, admin, poster);
        this.noTicketsSold = noTicketsSold;
    }

    public ShowDTO(Show show, int noTicketsSold){
        super(show.getDate(), show.getName(), show.getActors(), show.getGenre(), show.getDescription(),
                show.getDuration(), show.getDirector(), show.getAdmin(), show.getPoster());
        this.noTicketsSold = noTicketsSold;
    }

    public int getNoTicketsSold() {
        return noTicketsSold;
    }
}
