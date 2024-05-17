package com.example.theatrereservationsystem.gui;

import com.example.theatrereservationsystem.domain.Seat;
import com.example.theatrereservationsystem.domain.Show;
import com.example.theatrereservationsystem.domain.Ticket;
import com.example.theatrereservationsystem.domain.validation.TicketValidator;
import com.example.theatrereservationsystem.domain.validation.ValidationException;
import com.example.theatrereservationsystem.service.TheatreService;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.List;

public class OrderInformationController {
    public TextField firstNameTextField;
    public TextField lastNameTextField;
    public TextField phoneNumberTextField;
    public TextField emailTextField;
    public CheckBox cashCheckBox;
    public CheckBox cardCheckBox;
    public Label messageLabel;

    private TheatreService service;
    private Stage stage;
    private Show show;
    private List<Seat> seats;
    private TicketValidator ticketValidator;

    public void handlePrevious(ActionEvent actionEvent) {
        PageLoader.loadOrderSummaryScreen(service, stage, show, seats);
    }

    public void handleNext(ActionEvent actionEvent) {
        if(cardCheckBox.isSelected() && cashCheckBox.isSelected()){
            messageLabel.setText("Please select a single payment method.");
            return;
        }

        else if(!cardCheckBox.isSelected() && !cashCheckBox.isSelected()){
            messageLabel.setText("Please select a payment method.");
            return;
        }

        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String email = emailTextField.getText();

        Ticket ticket = new Ticket(LocalDateTime.now(), firstName,
                lastName, email, phoneNumber, show, seats);

        try{
            ticketValidator.validate(ticket);
        } catch (ValidationException ex){
            messageLabel.setText(ex.getMessage());
            return;
        }

        if(cashCheckBox.isSelected()){
            PageLoader.loadCashPaymentScreen(service, stage, ticket);
        }

        if(cardCheckBox.isSelected()){
            PageLoader.loadCardPaymentScreen(service, stage, ticket);
        }
    }

    public void setService(TheatreService service, Stage stage, Show show, List<Seat> seats) {
        this.seats = seats;
        this.service = service;
        this.show = show;
        this.stage = stage;
        this.ticketValidator = new TicketValidator();
    }
}
