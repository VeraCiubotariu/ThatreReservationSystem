package com.example.theatrereservationsystem.persistence.utils;

import com.example.theatrereservationsystem.domain.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if ((sessionFactory==null)||(sessionFactory.isClosed()))
            sessionFactory=createNewSessionFactory();
        return sessionFactory;
    }

    private static  SessionFactory createNewSessionFactory(){
        sessionFactory = new Configuration()
                .addAnnotatedClass(Administrator.class)
                .addAnnotatedClass(Show.class)
                .addAnnotatedClass(ShowDTO.class)
                .addAnnotatedClass(Seat.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();
        return sessionFactory;
    }

    public static  void closeSessionFactory(){
        if (sessionFactory!=null)
            sessionFactory.close();
    }
}
