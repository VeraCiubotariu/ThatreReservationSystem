package com.example.theatrereservationsystem.gui;

import com.example.theatrereservationsystem.domain.Administrator;
import com.example.theatrereservationsystem.domain.Show;
import com.example.theatrereservationsystem.domain.ShowType;
import com.example.theatrereservationsystem.gui.utils.PageLoader;
import com.example.theatrereservationsystem.service.TheatreService;
import com.example.theatrereservationsystem.service.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

public class AdminFutureShowsController implements Observer {
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
    public Button imageUploadButton;
    private byte[] uploadedPoster = null;

    private final ObservableList<Show> showsModel = FXCollections.observableArrayList();

    private TheatreService service;
    private Administrator admin;
    private Stage stage;

    @FXML
    public void initialize(){
        showsListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Show> call(ListView<Show> param) {
                return new ListCell<>() {
                    private final ImageView imageView = new ImageView();

                    @Override
                    protected void updateItem(Show item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setGraphic(null);
                        } else {
                            imageView.setImage(item.getPosterImage());
                            imageView.setFitHeight(150);
                            imageView.setFitWidth(200);
                            imageView.setPickOnBounds(true);
                            imageView.setPreserveRatio(true);
                            setGraphic(imageView);

                            imageUploadButton.setText("Upload Image");
                            uploadedPoster = item.getPoster();
                        }
                    }
                };
            }
        });

        showsListView.setItems(showsModel);
        showsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> fillShowInformation(newValue));

        genreComboBox.setItems(FXCollections.observableArrayList(ShowType.values()));
        timeComboBox.setItems(FXCollections.observableArrayList(LocalTime.of(16, 0),
                LocalTime.of(17, 0), LocalTime.of(18, 0), LocalTime.of(19, 0),
                LocalTime.of(20, 0), LocalTime.of(21, 0)));

        durationSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 500));

        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
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

        this.service.addObserver(this);
    }

    public void handleSearch() {
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
            System.out.println("Setting shows...");
            showsModel.setAll(service.getFutureShows(start.atStartOfDay(), end.atTime(23, 59)));
            messageLabel.setText("");
        } catch (Exception ex){
            messageLabel.setText(ex.getMessage());
        }
    }

    public void handleGoBack() {
        PageLoader.loadAdminMenu(service, admin, stage);
    }

    FileChooser fileChooser = new FileChooser();

    public void handleImageUpload() {
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                BufferedImage bImage = ImageIO.read(new FileInputStream(file));
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ImageIO.write(bImage, "jpg", bos );
                uploadedPoster = bos.toByteArray();
                imageUploadButton.setText(file.getName());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void handleDelete() {
        if(showsListView.getSelectionModel().getSelectedItem() == null){
            messageLabel.setText("Please select a show.");
            return;
        }

        messageLabel.setText("");
        Show selected = showsListView.getSelectionModel().getSelectedItem();

        try{
            Optional<Show> deleted = service.deleteShow(selected.getId());

            if(deleted.isEmpty()){
                messageLabel.setText("Couldn't delete show.");
            }

            else{
                messageLabel.setText("Show deleted successfully!");
            }
        } catch (Exception ex){
            messageLabel.setText(ex.getMessage());
        }
    }

    public void handleModify() {
        if(showsListView.getSelectionModel().getSelectedItem() == null){
            messageLabel.setText("Please select a show.");
            return;
        }

        messageLabel.setText("");

        try{
            Show show = this.getShow();
            show.setId(showsListView.getSelectionModel().getSelectedItem().getId());
            Optional<Show> modified = service.updateShow(show);

            if(modified.isPresent()){
                messageLabel.setText("Couldn't update show.");
            }

            else{
                messageLabel.setText("Show updated successfully!");
            }
        } catch (Exception ex){
            messageLabel.setText(ex.getMessage());
        }
    }

    public void handleAdd() {
        messageLabel.setText("");

        try{
            Optional<Show> added = service.saveShow(this.getShow());

            if(added.isPresent()){
                messageLabel.setText("Couldn't save show.");
            }

            else{
                messageLabel.setText("Show saved successfully!");
            }
        } catch (Exception ex){
            messageLabel.setText(ex.getMessage());
        }
    }

    private Show getShow(){
        String name = nameTextField.getText();
        String actors = actorsTextField.getText();
        String description = descriptionTextArea.getText();
        LocalDate date = datePicker.getValue();
        LocalTime time = timeComboBox.getValue();
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        ShowType genre = genreComboBox.getValue();
        int duration = durationSpinner.getValue();
        String director = directorTextField.getText();

        return new Show(dateTime, name, actors, genre, description, duration, director, this.admin,
                        uploadedPoster);
    }

    @Override
    public void update() {
        handleSearch();
    }
}
