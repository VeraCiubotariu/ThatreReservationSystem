package com.example.theatrereservationsystem.persistence.hibernate;

import com.example.theatrereservationsystem.domain.Seat;
import com.example.theatrereservationsystem.persistence.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class SeatRepository {
    public List<String> getAllOccupiedIDs() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            return session.createNativeQuery("select ts.seat_id from shows s inner join administrators a on s.admin_id = a.id " +
                            "inner join tickets t on t.show_id = s.id " +
                            "inner join tickets_seats ts on t.id = ts.ticket_id " +
                            "where extract(year from date) = extract(year from current_date) and extract(month from date) = extract(month from current_date) " +
                            "and extract(day from date) = extract(day from current_date) ", String.class)
                    .getResultList();
        }
    }

    public Optional<Seat> get(String seatID) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            return Optional.ofNullable(session.createSelectionQuery("from Seat where id = :idS", Seat.class)
                    .setParameter("idS", seatID)
                    .getSingleResultOrNull());
        }
    }
}
