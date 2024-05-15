package com.example.theatrereservationsystem.service;

import com.example.theatrereservationsystem.domain.Administrator;
import com.example.theatrereservationsystem.domain.Show;
import com.example.theatrereservationsystem.domain.ShowDTO;
import com.example.theatrereservationsystem.domain.TimeFrame;
import com.example.theatrereservationsystem.domain.validation.ShowValidator;
import com.example.theatrereservationsystem.domain.validation.TicketValidator;
import com.example.theatrereservationsystem.persistence.AdministratorRepository;
import com.example.theatrereservationsystem.persistence.SeatRepository;
import com.example.theatrereservationsystem.persistence.ShowRepository;
import com.example.theatrereservationsystem.persistence.TicketRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TheatreService {
    private AdministratorRepository administratorRepository;
    private SeatRepository seatRepository;
    private ShowRepository showRepository;
    private TicketRepository ticketRepository;
    private ShowValidator showValidator;
    private TicketValidator ticketValidator;

    public TheatreService(AdministratorRepository administratorRepository,
                          SeatRepository seatRepository, ShowRepository showRepository,
                          TicketRepository ticketRepository, ShowValidator showValidator,
                          TicketValidator ticketValidator) {
        this.administratorRepository = administratorRepository;
        this.seatRepository = seatRepository;
        this.showRepository = showRepository;
        this.ticketRepository = ticketRepository;
        this.showValidator = showValidator;
        this.ticketValidator = ticketValidator;
    }

    public Optional<Show> getTodayShow(){
        return showRepository.getTodayShow();
    }

    public Optional<Show> saveShow(Show show){
        if(show == null){
            throw new IllegalArgumentException("The show cannot be null");
        }

        showValidator.validate(show);
        return showRepository.save(show);
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
            case LAST_WEEK -> {
                start = now.minusWeeks(1);
            }
            case LAST_MONTH -> {
                start = now.minusMonths(1);
            }
            case LAST_6_MONTHS -> {
                start = now.minusMonths(6);
            }
            case LAST_YEAR -> {
                start = now.minusYears(1);
            }
            case ALL_TIME -> {
                start = LocalDateTime.of(0, 1, 1, 0, 0);
            }
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
}
