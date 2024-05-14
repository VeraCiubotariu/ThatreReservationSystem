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
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/theatrereservationsystem/views/main-screen.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Welcome!");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            MainScreenController mainScreenController = loader.getController();
            mainScreenController.setService(service, dialogStage);

            dialogStage.show();
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
