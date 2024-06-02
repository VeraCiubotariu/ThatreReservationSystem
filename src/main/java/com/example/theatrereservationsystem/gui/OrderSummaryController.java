package com.example.theatrereservationsystem.gui;

import com.example.theatrereservationsystem.domain.Seat;
import com.example.theatrereservationsystem.domain.Show;
import com.example.theatrereservationsystem.gui.utils.PageLoader;
import com.example.theatrereservationsystem.service.TheatreService;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Set;

public class OrderSummaryController {
    public Label showNameLabel;
    public Label showDateLabel;
    public Label showTimeLabel;
    public Label seatsLabel;
    public Label totalPriceLabel;

    private TheatreService service;
    private Stage stage;
    private Show show;
    private Set<Seat> seats;

    public void handlePrevious() {
        PageLoader.loadSeatSelection(service, stage, show);
    }

    public void handleNext() {
        PageLoader.loadOrderInformationScreen(service, stage, show, seats);
    }

    public void setService(TheatreService service, Stage stage, Show show, Set<Seat> seats) {
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
        StringBuilder seatInfo = new StringBuilder();

        for(Seat seat:seats){
            totalPrice += seat.getPrice();
            seatInfo.append(seat.getId()).append(" - ").append(seat.getPrice()).append("\n");
        }

        seatsLabel.setText(seatInfo.toString());
        totalPriceLabel.setText(String.valueOf(totalPrice));
    }
}
