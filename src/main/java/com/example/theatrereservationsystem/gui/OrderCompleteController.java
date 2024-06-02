package com.example.theatrereservationsystem.gui;

import com.example.theatrereservationsystem.gui.utils.PageLoader;
import com.example.theatrereservationsystem.service.TheatreService;
import javafx.stage.Stage;

public class OrderCompleteController {
    private TheatreService service;
    private Stage stage;

    public void handleExit() {
        PageLoader.loadMainScreen(service, stage);
    }

    public void setService(TheatreService service, Stage stage) {
        this.service = service;
        this.stage = stage;
    }
}
