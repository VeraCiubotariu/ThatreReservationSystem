package com.example.theatrereservationsystem.gui;

import com.example.theatrereservationsystem.domain.Ticket;
import com.example.theatrereservationsystem.gui.utils.PageLoader;
import com.example.theatrereservationsystem.service.TheatreService;
import javafx.animation.PauseTransition;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CashPaymentController {
    private TheatreService service;
    private Stage stage;
    private Ticket ticket;

    public void setService(TheatreService service, Stage stage, Ticket ticket) {
        this.service = service;
        this.stage = stage;
        this.ticket = ticket;

        initView();
    }

    private void initView() {
        try{
            service.saveTicket(ticket);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event -> {
            stage.close();
            PageLoader.loadOrderCompleteScreen(service, stage);
        });

        pause.play();
    }
}
