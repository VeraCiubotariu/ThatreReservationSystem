package com.example.theatrereservationsystem.domain.validation;

import com.example.theatrereservationsystem.domain.Show;

import java.time.LocalDateTime;

public class ShowValidator implements Validator<Show> {
    @Override
    public void validate(Show entity) throws ValidationException {
        if(entity.getActors() == ""){
            throw new ValidationException("Invalid actors!");
        }

        if(entity.getDate().isBefore(LocalDateTime.now())){
            throw new ValidationException("Date must be after current date!");
        }

        if(entity.getDescription() == ""){
            throw new ValidationException("Invalid description!");
        }

        if(entity.getDirector() == ""){
            throw new ValidationException("Invalid director!");
        }

        if(entity.getName() == ""){
            throw new ValidationException("Invalid name!");
        }

        if(entity.getDuration() <= 0){
            throw new ValidationException("Duration must be grater than 0!");
        }
    }
}
