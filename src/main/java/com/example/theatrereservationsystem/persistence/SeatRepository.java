package com.example.theatrereservationsystem.persistence;

import com.example.theatrereservationsystem.domain.Seat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SeatRepository extends DBRepository{
    public SeatRepository(String url, String username, String password) {
        super(url, username, password);
    }

    public List<String> getAllOccupiedIDs(){
        List<String> seatIDs = new ArrayList<>();

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("select ts.seat_id from shows s inner join administrators a on s.admin_id = a.id " +
                    "inner join tickets t on t.show_id = s.id " +
                    "inner join tickets_seats ts on t.id = ts.ticket_id " +
                    "where extract(year from date) = extract(year from current_date) and extract(month from date) = extract(month from current_date) " +
                    "and extract(day from date) = extract(day from current_date) "))
        {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String seatID = resultSet.getString("seat_id");
                seatIDs.add(seatID);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return seatIDs;
    }

    public Optional<Seat> get(String seatID){
        try{
            Connection connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("select * from seats " +
                    "where id=?");
            statement.setString(1, seatID);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                float price = resultSet.getFloat("price");

                Seat seat = new Seat(price);
                seat.setId(seatID);

                return Optional.of(seat);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}
