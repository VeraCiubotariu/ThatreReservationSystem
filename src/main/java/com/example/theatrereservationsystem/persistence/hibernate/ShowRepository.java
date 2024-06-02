package com.example.theatrereservationsystem.persistence.hibernate;
import com.example.theatrereservationsystem.domain.Show;
import com.example.theatrereservationsystem.domain.ShowDTO;
import com.example.theatrereservationsystem.domain.ShowType;
import com.example.theatrereservationsystem.persistence.utils.HibernateUtils;
import jakarta.persistence.Tuple;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ShowRepository {

    public Optional<Show> get(int showID) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            return Optional.ofNullable(session.createSelectionQuery("from Show where id = :idS", Show.class)
                    .setParameter("idS", showID)
                    .getSingleResultOrNull());
        }
    }

    public Optional<Show> getTodayShow(){
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            return Optional.ofNullable(session.createSelectionQuery("from Show where extract(year from date)" +
                            " = extract(year from current_date) and extract(month from date) = " +
                            "extract(month from current_date) and extract(day from date) = " +
                            "extract(day from current_date) ", Show.class)
                    .getSingleResultOrNull());
        }
    }

    public List<Show> getAll(LocalDateTime start, LocalDateTime end) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            return session.createQuery("from Show where date >= :start and date <= :end order by date",
                            Show.class)
                    .setParameter("start", start)
                    .setParameter("end", end)
                    .getResultList();
        }
    }

    public List<ShowDTO> getPastShows(LocalDateTime start){
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            NativeQuery<Tuple> query = session.createNativeQuery(
                            "select s.id, s.date, s.name, s.actors, s.duration, s.genre, count(*) as sold_tickets " +
                                    "from shows s inner join tickets t on s.id = t.show_id " +
                                    "group by (s.id, s.date, s.name, s.actors, s.duration, s.genre) " +
                                    "having s.date >= :start", Tuple.class)
                    .setParameter("start", start);

            List<Tuple> results = query.getResultList();
            return results.stream()
                    .map(tuple -> {
                        Long soldTicketsLong = tuple.get("sold_tickets", Long.class);
                        int soldTickets = soldTicketsLong != null ? soldTicketsLong.intValue() : 0;

                        ShowDTO showDTO = new ShowDTO(
                                soldTickets,
                                tuple.get("actors", String.class),
                                tuple.get("duration", Integer.class),
                                ShowType.valueOf(tuple.get("genre", String.class)),
                                tuple.get("name", String.class),
                                tuple.get("date", Timestamp.class).toLocalDateTime()
                        );
                        session.detach(showDTO);
                        return showDTO;
                    })
                    .collect(Collectors.toList());
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return new ArrayList<>();
    }

    public Optional<Show> save(Show entity){
        try{
            HibernateUtils.getSessionFactory().inTransaction(session -> session.persist(entity));
            return Optional.empty();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return Optional.of(entity);
        }
    }

    public Optional<Show> update(Show newEntity){
        try{
            HibernateUtils.getSessionFactory().inTransaction(session -> {
                session.merge(newEntity);
                session.flush();
            });
            return Optional.empty();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return Optional.of(newEntity);
        }
    }

    public Optional<Show> delete(int showID){
        try{
            Optional<Show> show = get(showID);

            if(show.isEmpty()){
                return Optional.empty();
            }

            HibernateUtils.getSessionFactory().inTransaction(session -> {
                session.remove(show.get());
                session.flush();
            });

            return show;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return Optional.empty();
        }
    }
}
