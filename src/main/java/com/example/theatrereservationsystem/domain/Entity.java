package com.example.theatrereservationsystem.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

import static jakarta.persistence.GenerationType.IDENTITY;

@MappedSuperclass
public class Entity<T extends Serializable> {
    private T id;

    public void setId(T id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy=IDENTITY)
    public T getId(){
        return this.id;
    }
}
