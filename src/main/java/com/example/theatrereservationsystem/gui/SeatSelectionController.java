package com.example.theatrereservationsystem.gui;

import com.example.theatrereservationsystem.service.TheatreService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SeatSelectionController {
    private TheatreService service;
    private Stage stage;

    @FXML
    public void handleSeatSelection(){

    }

    public void setService(TheatreService service, Stage stage){
        this.service = service;
        this.stage = stage;
    }

    public void handleNext(ActionEvent actionEvent) {
    }

    public void handleGoBack(ActionEvent actionEvent) {
        PageLoader.loadMainScreen(service, stage);
    }
}
