package com.example.theatrereservationsystem.persistence.hibernate;

import com.example.theatrereservationsystem.domain.Seat;
import com.example.theatrereservationsystem.domain.Ticket;
import com.example.theatrereservationsystem.persistence.utils.HibernateUtils;

import java.util.Optional;

public class TicketRepository {
    public Optional<Ticket> saveTicket(Ticket ticket) {
        for(Seat seat: ticket.getSeats()){
            seat.getTickets().add(ticket);
        }

        HibernateUtils.getSessionFactory().inTransaction(session -> session.persist(ticket));
        return Optional.empty();
    }
}
