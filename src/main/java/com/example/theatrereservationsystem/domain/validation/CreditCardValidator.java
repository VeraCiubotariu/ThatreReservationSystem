package com.example.theatrereservationsystem.domain.validation;

import com.example.theatrereservationsystem.domain.CreditCard;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCardValidator implements Validator<CreditCard> {
    @Override
    public void validate(CreditCard entity) throws ValidationException {
        if(entity.getHolder() == ""){
            throw new ValidationException("Invalid card holder's name!");
        }

        if(entity.getExpiryDate().isBefore(LocalDate.now())){
            throw new ValidationException("Expired card!");
        }

        validateCardNumber(entity.getNumber());
        validateCVV(entity.getCvv());
    }

    private void validateCardNumber(String number){
        String numberPattern = "^[0-9]{16}$";

        Pattern pattern = Pattern.compile(numberPattern);

        Matcher matcher = pattern.matcher(number);
        if(!matcher.matches()){
            throw new ValidationException("Invalid card number!");
        }
    }

    private void validateCVV(String cvv){
        String cvvPattern = "^[0-9]{3}$";

        Pattern pattern = Pattern.compile(cvvPattern);

        Matcher matcher = pattern.matcher(cvv);
        if(!matcher.matches()){
            throw new ValidationException("Invalid card number!");
        }
    }
}
