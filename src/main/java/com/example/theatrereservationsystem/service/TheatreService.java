package com.example.theatrereservationsystem.service;

import com.example.theatrereservationsystem.domain.Administrator;
import com.example.theatrereservationsystem.domain.Show;
import com.example.theatrereservationsystem.domain.validation.ShowValidator;
import com.example.theatrereservationsystem.domain.validation.TicketValidator;
import com.example.theatrereservationsystem.persistence.AdministratorRepository;
import com.example.theatrereservationsystem.persistence.SeatRepository;
import com.example.theatrereservationsystem.persistence.ShowRepository;
import com.example.theatrereservationsystem.persistence.TicketRepository;

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
}
