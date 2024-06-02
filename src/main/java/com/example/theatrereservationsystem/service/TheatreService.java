package com.example.theatrereservationsystem.service;

import com.example.theatrereservationsystem.domain.*;
import com.example.theatrereservationsystem.domain.validation.ShowValidator;
import com.example.theatrereservationsystem.domain.validation.TicketValidator;
import com.example.theatrereservationsystem.persistence.hibernate.AdministratorRepository;
import com.example.theatrereservationsystem.persistence.hibernate.SeatRepository;
import com.example.theatrereservationsystem.persistence.hibernate.ShowRepository;
import com.example.theatrereservationsystem.persistence.hibernate.TicketRepository;
import com.example.theatrereservationsystem.service.observer.Observable;
import com.example.theatrereservationsystem.service.observer.Observer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TheatreService implements Observable {
    private final AdministratorRepository administratorRepository;
    private final SeatRepository seatRepository;
    private final ShowRepository showRepository;
    private final TicketRepository ticketRepository;
    private final ShowValidator showValidator;
    private final TicketValidator ticketValidator;
    private final List<Observer> observers = new ArrayList<>();

    public TheatreService(AdministratorRepository administratorRepository,
                          SeatRepository seatRepository, ShowRepository showRepository,
                          TicketRepository ticketRepository, ShowValidator showValidator,
                          TicketValidator ticketValidator) {
        this.seatRepository = seatRepository;
        this.showRepository = showRepository;
        this.ticketRepository = ticketRepository;
        this.showValidator = showValidator;
        this.ticketValidator = ticketValidator;
        this.administratorRepository = administratorRepository;
    }

    public Optional<Show> getTodayShow(){
        return showRepository.getTodayShow();
    }

    public Optional<Show> saveShow(Show show){
        if(show == null){
            throw new IllegalArgumentException("The show cannot be null");
        }

        showValidator.validate(show);
        Optional<Show> saved = showRepository.save(show);

        if(saved.isEmpty()){
            notifyObservers();
        }

        return saved;
    }

    public Optional<Show> updateShow(Show show){
        if(show == null){
            throw new IllegalArgumentException("The show cannot be null");
        }

        showValidator.validate(show);
        Optional<Show> updated = showRepository.update(show);

        if(updated.isEmpty()){
            notifyObservers();
        }

        return updated;
    }

    public Optional<Show> deleteShow(int showID){
        Optional<Show> deleted = showRepository.delete(showID);

        if(deleted.isPresent()){
            notifyObservers();
        }

        return deleted;
    }

    public Optional<Administrator> login(String username, String password){
        if(username == null){
            throw new IllegalArgumentException("Username cannot be null!");
        }

        if(password == null){
            throw new IllegalArgumentException("Password cannot be null!");
        }

        Optional<Administrator> admin = administratorRepository.get(username);

        if(admin.isPresent()){
            if(!Objects.equals(admin.get().getPassword(), password)){
                throw new ServiceException("Incorrect password!");
            }
        }

        return admin;
    }

    public List<ShowDTO> getPastShows(TimeFrame timeFrame){
        if(timeFrame == null){
            throw new IllegalArgumentException("Time frame cannot be null!");
        }

        LocalDateTime start = null;
        LocalDateTime now = LocalDateTime.now();

        switch (timeFrame){
            case LAST_WEEK -> start = now.minusWeeks(1);
            case LAST_MONTH -> start = now.minusMonths(1);
            case LAST_6_MONTHS -> start = now.minusMonths(6);
            case LAST_YEAR -> start = now.minusYears(1);
            case ALL_TIME -> start = LocalDateTime.of(0, 1, 1, 0, 0);
        }

        return showRepository.getPastShows(start);
    }

    public int getTotalTickets(List<ShowDTO> shows){
        int result = 0;

        for(ShowDTO show: shows){
            result += show.getSoldTickets();
        }

        return result;
    }

    public List<Show> getFutureShows(LocalDateTime start, LocalDateTime end){
        if(start == null){
            throw new IllegalArgumentException("Start date cannot be null!");
        }

        if(end == null){
            throw new IllegalArgumentException("End date cannot be null!");
        }

        if(start.isBefore(LocalDateTime.now())){
            throw new ServiceException("Start date cannot be before current date!");
        }

        if(end.isBefore(start)){
            throw new ServiceException("End date cannot be before start date!");
        }

        return showRepository.getAll(start, end);
    }

    public List<String> getAllOccupiedSeats(){
        return seatRepository.getAllOccupiedIDs();
    }

    public Optional<Seat> getSeat(String seatID){
        return seatRepository.get(seatID);
    }

    public Optional<Ticket> saveTicket(Ticket ticket){
        ticketValidator.validate(ticket);
        Optional<Ticket> response = ticketRepository.saveTicket(ticket);

        if(response.isEmpty()){
            System.out.println("Notifying observers...");
            notifyObservers();
        }

        return response;
    }

    @Override
    public void addObserver(Observer e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }
}
