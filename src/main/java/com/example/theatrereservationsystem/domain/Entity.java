package com.example.theatrereservationsystem.domain;

public class Entity<T> {
    private T id;

    public void setId(T id) {
        this.id = id;
    }
    public T getId(){
        return this.id;
    }
}
