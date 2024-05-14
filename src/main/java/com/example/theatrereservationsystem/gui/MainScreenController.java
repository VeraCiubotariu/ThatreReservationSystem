package com.example.theatrereservationsystem.gui;

import com.example.theatrereservationsystem.domain.Show;
import com.example.theatrereservationsystem.service.TheatreService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

public class MainScreenController {
    @FXML
    public ImageView posterImageView;
    @FXML
    Label messageLabel;

    private TheatreService service;
    private Stage stage;
    private Show show;

    @FXML
    public void initialize(){

    }

    public void setPosterImage(){
        try{
            Optional<Show> todayShow = service.getTodayShow();
            if(todayShow.isPresent()){
                show = todayShow.get();
                posterImageView.setImage(show.getPoster());
            }

            else{
                show = null;
                posterImageView.setImage(new Image("/com/example/theatrereservationsystem/images/no_shows.png"));
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void setService(TheatreService service, Stage stage){
        this.stage = stage;
        this.service = service;
        setPosterImage();
    }

    public void handleReservation(MouseEvent mouseEvent) {
        if(show == null){
            messageLabel.setText("There isn't any show for today!");
        }

        else if(show.getDate().plusMinutes(show.getDuration()).isBefore(LocalDateTime.now())){
            messageLabel.setText("The show has just ended!");
        }

        else{
            loadSeatSelection();
        }
    }

    private void loadSeatSelection(){
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/theatrereservationsystem/views/seat_selection_view.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Seat selection");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            SeatSelectionController seatSelectionController = loader.getController();
            seatSelectionController.setService(service, dialogStage);

            dialogStage.show();
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleShowInformation(MouseEvent mouseEvent) {
        if(show == null){
            messageLabel.setText("There isn't any show for today!");
        }

        else{
            loadShowInformation();
        }
    }

    private void loadShowInformation() {
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/theatrereservationsystem/views/show_information_view.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Seat selection");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            ShowInformationController controller = loader.getController();
            controller.setService(service, show, dialogStage);

            dialogStage.show();
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleFutureShowsUser(MouseEvent mouseEvent) {
    }

    public void handleAdminLogin(MouseEvent mouseEvent) {
    }
}
