package com.example.theatrereservationsystem.gui;

import com.example.theatrereservationsystem.domain.Seat;
import com.example.theatrereservationsystem.domain.Show;
import com.example.theatrereservationsystem.gui.utils.PageLoader;
import com.example.theatrereservationsystem.service.TheatreService;
import com.example.theatrereservationsystem.service.observer.Observer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.*;

public class SeatSelectionController implements Observer {
    public Label messageLabel;
    public GridPane seatsGridPane;
    private TheatreService service;
    private Stage stage;
    private Show show;
    private final List<String> selectedSeats = new ArrayList<>();

    @FXML
    public void handleSeatSelection(MouseEvent actionEvent){
        ImageView clickedSeat = (ImageView) actionEvent.getSource();
        Image freeSeat = new Image("/com/example/theatrereservationsystem/images/free_seat.png");
        Image selectedSeat = new Image("/com/example/theatrereservationsystem/images/selected_seat.png");

        String[] imgComp = clickedSeat.getImage().getUrl().split("/");
        String imgName = imgComp[imgComp.length-1];

        if(Objects.equals(imgName, "free_seat.png")){
            clickedSeat.setImage(selectedSeat);
            selectedSeats.add(clickedSeat.getId());
        }

        if(Objects.equals(imgName, "selected_seat.png")){
            clickedSeat.setImage(freeSeat);
            selectedSeats.remove(clickedSeat.getId());
        }
    }

    public void setService(TheatreService service, Stage stage, Show show){
        this.service = service;
        this.stage = stage;
        this.show = show;
        this.service.addObserver(this);

        initSeats(service.getAllOccupiedSeats());
    }

    private void initSeats(List<String> occupiedSeats) {
        Image occupiedSeat = new Image("/com/example/theatrereservationsystem/images/occupied_seat.png");

        for(Node node: seatsGridPane.getChildren()){
            if(node instanceof ImageView){
                for(String seatID: occupiedSeats){
                    if(Objects.equals(seatID, node.getId())){
                        ((ImageView) node).setImage(occupiedSeat);
                    }
                }
            }
        }
    }

    public void handleNext() {
        if(selectedSeats.isEmpty()){
            messageLabel.setText("Please select your desired seat(s).");
            return;
        }

        Set<Seat> desiredSeats = new HashSet<>();

        for(String seatID: selectedSeats){
            Optional<Seat> seat = service.getSeat(seatID);

            seat.ifPresent(desiredSeats::add);
        }

        PageLoader.loadOrderSummaryScreen(service, stage, show, desiredSeats);
    }

    public void handleGoBack() {
        PageLoader.loadMainScreen(service, stage);
    }

    @Override
    public void update() {
        initSeats(service.getAllOccupiedSeats());
    }
}
