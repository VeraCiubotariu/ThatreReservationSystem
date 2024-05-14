package com.example.theatrereservationsystem.persistence;

import com.example.theatrereservationsystem.domain.Seat;
import com.example.theatrereservationsystem.domain.SeatState;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SeatRepository extends DBRepository{
    public SeatRepository(String url, String username, String password) {
        super(url, username, password);
    }

    private List<Seat> getAll(){
        List<Seat> seats = new ArrayList<>();

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from seats"))
        {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int seatID = resultSet.getInt("id");
                String row = resultSet.getString("row");
                int number = resultSet.getInt("number");
                float price = resultSet.getFloat("price");
                SeatState state = SeatState.valueOf(resultSet.getString("state"));

                Seat seat = new Seat(row, number, price, state);
                seat.setId(seatID);

                seats.add(seat);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return seats;
    }

    public Optional<Seat> update(Seat entity) {
        try(Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = con.prepareStatement("update seats set row=?, number=?, price=?, state=? " +
                    " where id=?")){
            statement.setString(1, entity.getRow());
            statement.setInt(2, entity.getNumber());
            statement.setFloat(3, entity.getPrice());
            statement.setString(4, entity.getState().toString());
            statement.setInt(5, entity.getId());

            int response = statement.executeUpdate();
            return response == 0? Optional.empty() : Optional.of(entity);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
