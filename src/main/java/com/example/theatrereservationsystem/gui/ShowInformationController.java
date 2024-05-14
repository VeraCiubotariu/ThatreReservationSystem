package com.example.theatrereservationsystem.gui;

import com.example.theatrereservationsystem.domain.Show;
import com.example.theatrereservationsystem.service.TheatreService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class ShowInformationController {
    public Label descriptionLabel;
    public Label actorsLabel;
    public Label directorLabel;
    public Label durationLabel;
    public Label genreLabel;
    public Label nameLabel;
    private Show show;
    private Stage stage;
    private TheatreService service;

    public void setService(TheatreService service, Show show, Stage stage){
        this.show = show;
        this.stage = stage;
        this.service = service;
        initView();
    }

    private void initView(){
        nameLabel.setText(show.getName());
        actorsLabel.setText(show.getActors());
        directorLabel.setText(show.getDirector());
        durationLabel.setText(show.getDuration() + " min");
        genreLabel.setText(show.getGenre().toString());
        descriptionLabel.setText(show.getDescription());
    }

    public void handleGoBack(ActionEvent actionEvent) {
        try{
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
