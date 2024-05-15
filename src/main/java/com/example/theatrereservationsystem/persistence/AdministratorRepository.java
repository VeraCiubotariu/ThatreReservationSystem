package com.example.theatrereservationsystem.persistence;

import com.example.theatrereservationsystem.domain.Administrator;
import com.example.theatrereservationsystem.domain.Show;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AdministratorRepository extends DBRepository{
    public AdministratorRepository(String url, String username, String password) {
        super(url, username, password);
    }

    public Optional<Administrator> get(int adminID){
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from administrators where id=?"))
        {
            statement.setInt(1, adminID);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                Administrator administrator = new Administrator(username, password, firstName, lastName);
                administrator.setId(adminID);

                return Optional.of(administrator);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public Optional<Administrator> get(String username){
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from administrators where username=?"))
        {
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int adminID = resultSet.getInt("id");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                Administrator administrator = new Administrator(username, password, firstName, lastName);
                administrator.setId(adminID);

                return Optional.of(administrator);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}
