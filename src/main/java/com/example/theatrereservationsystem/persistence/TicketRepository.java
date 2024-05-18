package com.example.theatrereservationsystem.persistence;

import com.example.theatrereservationsystem.domain.Seat;
import com.example.theatrereservationsystem.domain.Ticket;

import java.sql.*;
import java.util.Optional;

public class TicketRepository extends DBRepository{
    public TicketRepository(String url, String username, String password) {
        super(url, username, password);
    }

    public Optional<Ticket> saveTicket(Ticket entity) {
        Connection con = null;

        try{
            con = DriverManager.getConnection(url, username, password);
            con.setAutoCommit(false);

            //Save the ticket information
            PreparedStatement statement = con.prepareStatement("insert into tickets(sale_date, client_first_name, " +
                    "client_last_name, client_email, client_phone_number, show_id) values (?,?,?,?,?,?)");

            statement.setTimestamp(1, Timestamp.valueOf(entity.getSaleDate()));
            statement.setString(2, entity.getClientFirstName());
            statement.setString(3, entity.getClientLastName());
            statement.setString(4, entity.getClientEmail());
            statement.setString(5, entity.getClientPhoneNumber());
            statement.setInt(6, entity.getShow().getId());

            int response = statement.executeUpdate();

            if(response == 0){
                con.rollback();
                return Optional.of(entity);
            }

            int ticketID = getLastID(con);
            System.out.println(ticketID);

            //Save each seat
            for(Seat seat: entity.getSeats()){
                statement = con.prepareStatement("insert into tickets_seats(ticket_id, seat_id) values (?,?)");
                statement.setInt(1, ticketID);
                statement.setString(2, seat.getId());

                response = statement.executeUpdate();

                if(response == 0){
                    con.rollback();
                    return Optional.of(entity);
                }
            }

            con.commit();
            return Optional.empty();

        } catch(Exception e) {
            throw new RuntimeException(e);

        } finally {
            if(con != null){
                try{
                    con.close();
                } catch (SQLException ignored){

                }
            }
        }
    }

    private Integer getLastID(Connection con) throws SQLException {
        PreparedStatement statement = con.prepareStatement("select id from tickets order by id desc limit 1");
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt("id");
        }

        return null;
    }
}
