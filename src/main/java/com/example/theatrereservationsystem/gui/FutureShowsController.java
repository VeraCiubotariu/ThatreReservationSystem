package com.example.theatrereservationsystem.gui;

import com.example.theatrereservationsystem.domain.Show;
import com.example.theatrereservationsystem.service.TheatreService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FutureShowsController {
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public GridPane showsGridPane;
    public Label messageLabel;
    public ScrollPane scrollPane;
    private TheatreService service;
    private Stage stage;


    public void setService(TheatreService service, Stage stage){
        this.service = service;
        this.stage = stage;
    }

    @FXML
    public void initialize(){
    }

    public void handleGoBack(ActionEvent actionEvent) {
        PageLoader.loadMainScreen(service, stage);
    }

    public void handleSearch(ActionEvent actionEvent) {
        LocalDate start = startDatePicker.getValue();
        LocalDate end = endDatePicker.getValue();

        if(start == null){
            messageLabel.setText("Please select a start date.");
            return;
        }

        if(end == null){
            messageLabel.setText("Please select an end date.");
            return;
        }

        List<Show> shows = new ArrayList<>();

        try{
            shows = service.getFutureShows(start.atStartOfDay(), end.atTime(23, 59));
            messageLabel.setText("");
            showsGridPane.getChildren().clear();
        } catch (Exception ex){
            messageLabel.setText(ex.getMessage());
            return;
        }

        int rows = shows.size()/2 + shows.size()%2;
        int index = 0;

        for(int i=0;i<rows;i++){
            for(int j=0;j<2;j++){
                try{
                    showsGridPane.add(createCell(shows.get(index)), j, i);
                    index += 1;
                } catch (Exception ex){
                    break;
                }
            }
        }
    }

    private VBox createCell(Show show){
        ImageView imageView = new ImageView(show.getPoster());
        imageView.setFitHeight(150);
        imageView.setFitWidth(200);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        Label dateLabel = new Label(show.getDate().toString());
        dateLabel.setFont(Font.font("Franklin Gothic Demi Cond"));
        dateLabel.setTextFill(Color.WHITE);

        VBox vBox = new VBox();

        vBox.getChildren().add(imageView);
        vBox.getChildren().add(dateLabel);

        return vBox;
    }
}
