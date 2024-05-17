package com.example.theatrereservationsystem.persistence;

import com.example.theatrereservationsystem.domain.Seat;
import com.example.theatrereservationsystem.domain.SeatState;
import com.example.theatrereservationsystem.domain.Show;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SeatRepository extends DBRepository{
    public SeatRepository(String url, String username, String password) {
        super(url, username, password);
    }

    public List<Seat> getAll(){
        List<Seat> seats = new ArrayList<>();

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from seats"))
        {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String seatID = resultSet.getString("id");
                float price = resultSet.getFloat("price");
                SeatState state = SeatState.valueOf(resultSet.getString("state"));

                Seat seat = new Seat(price, state);
                seat.setId(seatID);

                seats.add(seat);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return seats;
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

    public Optional<Seat> update(Seat entity) {
        try(Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = con.prepareStatement("update seats set price=?, state=? " +
                    " where id=?")){
            statement.setFloat(1, entity.getPrice());
            statement.setString(2, entity.getState().toString());
            statement.setString(3, entity.getId());

            int response = statement.executeUpdate();
            return response == 0? Optional.empty() : Optional.of(entity);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
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
                SeatState state = SeatState.valueOf(resultSet.getString("state"));

                Seat seat = new Seat(price, state);
                seat.setId(seatID);

                return Optional.of(seat);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}
