package com.example.theatrereservationsystem.gui;

import com.example.theatrereservationsystem.domain.Show;
import com.example.theatrereservationsystem.gui.utils.PageLoader;
import com.example.theatrereservationsystem.service.TheatreService;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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

    public void handleGoBack() {
        PageLoader.loadMainScreen(service, stage);
    }
}
