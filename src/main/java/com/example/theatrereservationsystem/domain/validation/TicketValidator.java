package com.example.theatrereservationsystem.domain.validation;

import com.example.theatrereservationsystem.domain.Ticket;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicketValidator implements Validator<Ticket> {
    @Override
    public void validate(Ticket entity) throws ValidationException {
        if(entity.getClientFirstName() == ""){
            throw new ValidationException("Invalid client's first name!");
        }

        if(entity.getClientLastName() == ""){
            throw new ValidationException("Invalid client's last name!");
        }

        validateEmail(entity.getClientEmail());
        validatePhoneNumber(entity.getClientPhoneNumber());
    }

    private void validateEmail(String email){
        String emailPattern =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);

        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            throw new ValidationException("Invalid client's email address!");
        }
    }

    private void validatePhoneNumber(String phoneNumber){
        String phoneNumberPattern = "^[0-9]{10}$";

        Pattern pattern = Pattern.compile(phoneNumberPattern);

        Matcher matcher = pattern.matcher(phoneNumber);
        if(!matcher.matches()){
            throw new ValidationException("Invalid client's email address!");
        }
    }
}
