package com.example.theatrereservationsystem.gui;

import com.example.theatrereservationsystem.domain.Administrator;
import com.example.theatrereservationsystem.domain.ShowDTO;
import com.example.theatrereservationsystem.domain.ShowType;
import com.example.theatrereservationsystem.domain.TimeFrame;
import com.example.theatrereservationsystem.gui.utils.PageLoader;
import com.example.theatrereservationsystem.service.TheatreService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PastShowsController {
    public ComboBox<TimeFrame> timeFrameComboBox;
    public TableColumn<ShowDTO, Integer> soldTicketsColumn;
    public TableColumn<ShowDTO, ShowType> genreColumn;
    public TableColumn<ShowDTO, Integer> durationColumn;
    public TableColumn<ShowDTO, LocalDateTime> dateColumn;
    public TableColumn<ShowDTO, String> nameColumn;
    public TableView<ShowDTO> showsTableView;
    public Label messageLabel;
    public Label totalTicketsLabel;

    private ObservableList<ShowDTO> showsModel = FXCollections.observableArrayList();
    private ObservableList<TimeFrame> timeFrameModel = FXCollections.observableArrayList();

    private TheatreService service;
    private Stage stage;
    private Administrator admin;

    @FXML
    public void initialize(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        soldTicketsColumn.setCellValueFactory(new PropertyValueFactory<>("soldTickets"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        showsTableView.setItems(showsModel);
        timeFrameComboBox.setItems(timeFrameModel);
    }

    public void setService(TheatreService service, Administrator admin, Stage stage){
        this.service = service;
        this.admin = admin;
        this.stage = stage;
        initModel();
    }

    private void initModel() {
        timeFrameModel.setAll(TimeFrame.values());
    }

    public void handleGoBack(ActionEvent actionEvent) {
        PageLoader.loadAdminMenu(service, admin, stage);
    }

    public void handleSearch(ActionEvent actionEvent) {
        TimeFrame selectedTimeFrame = timeFrameComboBox.getValue();

        if (selectedTimeFrame == null){
            messageLabel.setText("Please select a time frame.");
            totalTicketsLabel.setText("0");
            return;
        }

        List<ShowDTO> shows = new ArrayList<>();

        try {
            shows = service.getPastShows(selectedTimeFrame);
            messageLabel.setText("");
            totalTicketsLabel.setText(String.valueOf(service.getTotalTickets(shows)));
        } catch (Exception ex){
            messageLabel.setText(ex.getMessage());
        }

        showsModel.setAll(shows);
    }
}
