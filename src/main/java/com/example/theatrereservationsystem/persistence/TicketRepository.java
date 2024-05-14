package com.example.theatrereservationsystem.persistence;

import com.example.theatrereservationsystem.domain.Ticket;

import java.sql.*;
import java.util.Optional;

public class TicketRepository extends DBRepository{
    public TicketRepository(String url, String username, String password) {
        super(url, username, password);
    }

    public Optional<Ticket> saveTicket(Ticket entity) {
        try(Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = con.prepareStatement("insert into tickets(sale_date, client_first_name, " +
                    "client_last_name, client_email, client_phone_number, show_id) values (?,?,?,?,?,?)")){
            statement.setTimestamp(1, Timestamp.valueOf(entity.getSaleDate()));
            statement.setString(2, entity.getClientFirstName());
            statement.setString(3, entity.getClientLastName());
            statement.setString(4, entity.getClientEmail());
            statement.setString(5, entity.getClientPhoneNumber());
            statement.setInt(6, entity.getShow().getId());

            int response = statement.executeUpdate();
            return response == 0? Optional.of(entity) :Optional.empty();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int saveTicketSeat(int ticketID, int seatID) {
        try(Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = con.prepareStatement("insert into tickets_seats(ticket_id, seat_id) " +
                    "values (?,?)")){
            statement.setInt(1, ticketID);
            statement.setInt(2, seatID);

            return statement.executeUpdate();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getLastID(){
        try(Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = con.prepareStatement("select id from tickets order by id desc limit 1")){
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                return resultSet.getInt("id");
            }

        } catch(Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
