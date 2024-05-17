package com.example.theatrereservationsystem.gui;

import com.example.theatrereservationsystem.service.TheatreService;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class OrderCompleteController {
    private TheatreService service;
    private Stage stage;

    public void handleExit(ActionEvent actionEvent) {
        PageLoader.loadMainScreen(service, stage);
    }

    public void setService(TheatreService service, Stage stage) {
        this.service = service;
        this.stage = stage;
    }
}
