package com.example.theatrereservationsystem.gui;

import com.example.theatrereservationsystem.domain.CreditCard;
import com.example.theatrereservationsystem.domain.Ticket;
import com.example.theatrereservationsystem.domain.validation.CreditCardValidator;
import com.example.theatrereservationsystem.domain.validation.ValidationException;
import com.example.theatrereservationsystem.service.TheatreService;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class CardPaymentController {
    public TextField cardNumberTextField;
    public TextField cardHolderTextField;
    public TextField cvvTextField;
    public DatePicker expiryDatePicker;
    public Label messageLabel;

    private TheatreService service;
    private Stage stage;
    private Ticket ticket;

    public void handlePayNow(ActionEvent actionEvent) {
        String holder = cardHolderTextField.getText();
        String number = cardNumberTextField.getText();
        String cvv = cvvTextField.getText();
        LocalDate date = expiryDatePicker.getValue();

        if(date == null){
            messageLabel.setText("Please select an expiry date.");
            return;
        }

        CreditCard card = new CreditCard(number, holder, cvv, date);
        CreditCardValidator validator = new CreditCardValidator();

        try{
            validator.validate(card);
        } catch (ValidationException ex){
            messageLabel.setText(ex.getMessage());
            return;
        }

        service.saveTicket(ticket);
        PageLoader.loadOrderCompleteScreen(service, stage);
    }

    public void handleCancel(ActionEvent actionEvent) {
        PageLoader.loadMainScreen(service, stage);
    }

    public void setService(TheatreService service, Stage stage, Ticket ticket) {
        this.service = service;
        this.stage = stage;
        this.ticket = ticket;
    }
}
