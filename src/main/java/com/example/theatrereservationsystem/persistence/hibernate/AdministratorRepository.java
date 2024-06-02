package com.example.theatrereservationsystem.persistence.hibernate;

import com.example.theatrereservationsystem.domain.Administrator;
import com.example.theatrereservationsystem.persistence.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.Optional;

public class AdministratorRepository {
    public Optional<Administrator> get(String username) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            return Optional.ofNullable(session.createSelectionQuery("from Administrator where username = :user", Administrator.class)
                    .setParameter("user", username)
                    .getSingleResultOrNull());
        }
    }
}
