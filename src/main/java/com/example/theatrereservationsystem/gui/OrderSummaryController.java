package com.example.theatrereservationsystem.gui;

import com.example.theatrereservationsystem.domain.Seat;
import com.example.theatrereservationsystem.domain.Show;
import com.example.theatrereservationsystem.gui.utils.PageLoader;
import com.example.theatrereservationsystem.service.TheatreService;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.List;

public class OrderSummaryController {
    public Label showNameLabel;
    public Label showDateLabel;
    public Label showTimeLabel;
    public Label seatsLabel;
    public Label totalPriceLabel;

    private TheatreService service;
    private Stage stage;
    private Show show;
    private List<Seat> seats;

    public void handlePrevious(ActionEvent actionEvent) {
        PageLoader.loadSeatSelection(service, stage, show);
    }

    public void handleNext(ActionEvent actionEvent) {
        PageLoader.loadOrderInformationScreen(service, stage, show, seats);
    }

    public void setService(TheatreService service, Stage stage, Show show, List<Seat> seats) {
        this.seats = seats;
        this.service = service;
        this.show = show;
        this.stage = stage;

        initView();
    }

    private void initView() {
        showNameLabel.setText(show.getName());
        showTimeLabel.setText(show.getDate().toLocalTime().toString());
        showDateLabel.setText(show.getDate().toLocalDate().toString());

        float totalPrice = 0;
        String seatInfo = "";

        for(Seat seat:seats){
            totalPrice += seat.getPrice();
            seatInfo += seat.getId() + " - " + String.valueOf(seat.getPrice()) + "\n";
        }

        seatsLabel.setText(seatInfo);
        totalPriceLabel.setText(String.valueOf(totalPrice));
    }
}
