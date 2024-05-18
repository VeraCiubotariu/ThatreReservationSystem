package com.example.theatrereservationsystem.gui;

import com.example.theatrereservationsystem.domain.Administrator;
import com.example.theatrereservationsystem.domain.Show;
import com.example.theatrereservationsystem.domain.ShowType;
import com.example.theatrereservationsystem.gui.utils.PageLoader;
import com.example.theatrereservationsystem.service.TheatreService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.LocalTime;

public class AdminFutureShowsController {
    public Label messageLabel;
    public ListView<Show> showsListView;
    public DatePicker endDatePicker;
    public DatePicker startDatePicker;
    public TextArea descriptionTextArea;
    public TextArea actorsTextField;
    public TextField directorTextField;
    public ComboBox<ShowType> genreComboBox;
    public Spinner<Integer> durationSpinner;
    public ChoiceBox<LocalTime> timeComboBox;
    public DatePicker datePicker;
    public TextField nameTextField;

    private ObservableList<Show> showsModel = FXCollections.observableArrayList();

    private TheatreService service;
    private Administrator admin;
    private Stage stage;

    @FXML
    public void initialize(){
        showsListView.setCellFactory(new Callback<ListView<Show>, ListCell<Show>>() {
            @Override
            public ListCell<Show> call(ListView<Show> param) {
                return new ListCell<>(){
                    private final ImageView imageView = new ImageView();

                    @Override
                    protected void updateItem(Show item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setGraphic(null);
                        } else {
                            imageView.setImage(item.getPoster());
                            imageView.setFitHeight(150);
                            imageView.setFitWidth(200);
                            imageView.setPickOnBounds(true);
                            imageView.setPreserveRatio(true);
                            setGraphic(imageView);
                        }
                    }
                };
            }
        });

        showsListView.setItems(showsModel);
        showsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            fillShowInformation(newValue);
        });

        genreComboBox.setItems(FXCollections.observableArrayList(ShowType.values()));
        timeComboBox.setItems(FXCollections.observableArrayList(LocalTime.of(16, 0),
                LocalTime.of(17, 0), LocalTime.of(18, 0), LocalTime.of(19, 0),
                LocalTime.of(20, 0), LocalTime.of(21, 0)));

        durationSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 500));
    }

    private void fillShowInformation(Show show) {
        if(show == null){
            return;
        }

        nameTextField.setText(show.getName());
        datePicker.setValue(show.getDate().toLocalDate());
        descriptionTextArea.setText(show.getDescription());
        actorsTextField.setText(show.getActors());
        genreComboBox.setValue(show.getGenre());
        timeComboBox.setValue(show.getDate().toLocalTime());
        durationSpinner.getValueFactory().setValue(show.getDuration());
        directorTextField.setText(show.getDirector());
    }

    public void setService(TheatreService service, Administrator admin, Stage stage){
        this.service = service;
        this.admin = admin;
        this.stage = stage;
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

        try{
            showsModel.setAll(service.getFutureShows(start.atStartOfDay(), end.atTime(23, 59)));
            messageLabel.setText("");
        } catch (Exception ex){
            messageLabel.setText(ex.getMessage());
        }
    }

    public void handleGoBack(ActionEvent actionEvent) {
        PageLoader.loadAdminMenu(service, admin, stage);
    }

    public void handleImageUpload(ActionEvent actionEvent) {
    }

    public void handleDelete(ActionEvent actionEvent) {
    }

    public void handleModify(ActionEvent actionEvent) {
    }

    public void handleAdd(ActionEvent actionEvent) {
    }
}
