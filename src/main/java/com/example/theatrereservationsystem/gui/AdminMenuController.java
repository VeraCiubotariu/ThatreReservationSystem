package com.example.theatrereservationsystem.gui;

import com.example.theatrereservationsystem.domain.Administrator;
import com.example.theatrereservationsystem.gui.utils.PageLoader;
import com.example.theatrereservationsystem.service.TheatreService;
import javafx.stage.Stage;

public class AdminMenuController {
    private Administrator admin;
    private TheatreService service;
    private Stage stage;

    public void setService(TheatreService service, Administrator admin, Stage stage){
        this.service = service;
        this.admin = admin;
        this.stage = stage;
    }

    public void handlePastShows() {
        PageLoader.loadPastShowsScreen(service, admin, stage);
    }

    public void handleFutureShows() {
        PageLoader.loadAdminFutureShowsScreen(service, admin, stage);
    }

    public void handleExit() {
        PageLoader.loadMainScreen(service, stage);
    }
}
