package com.example.theatrereservationsystem;

import com.example.theatrereservationsystem.domain.Administrator;
import com.example.theatrereservationsystem.domain.Show;
import com.example.theatrereservationsystem.domain.ShowType;
import com.example.theatrereservationsystem.domain.validation.ShowValidator;
import com.example.theatrereservationsystem.domain.validation.TicketValidator;
import com.example.theatrereservationsystem.gui.MainScreenController;
import com.example.theatrereservationsystem.persistence.AdministratorRepository;
import com.example.theatrereservationsystem.persistence.SeatRepository;
import com.example.theatrereservationsystem.persistence.ShowRepository;
import com.example.theatrereservationsystem.persistence.TicketRepository;
import com.example.theatrereservationsystem.service.TheatreService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

public class MainApplication extends Application {
    String url = "jdbc:postgresql://localhost:5432/theatre";
    String username = "postgres";
    String password = "Geani19011978";

    ShowRepository showRepository = new ShowRepository(url, username, password);
    TicketRepository ticketRepository = new TicketRepository(url, username, password);
    SeatRepository seatRepository = new SeatRepository(url, username, password);
    AdministratorRepository administratorRepository = new AdministratorRepository(url, username, password);
    TheatreService service = new TheatreService(administratorRepository, seatRepository, showRepository,
            ticketRepository, new ShowValidator(), new TicketValidator());


    @Override
    public void start(Stage stage) throws Exception {
        initView(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void initView(Stage primaryStage) throws IOException {
        /*Administrator admin = new Administrator("clau", "abcd1234", "Claudia",
                "Popescu");
        admin.setId(1);

        Show show = new Show(LocalDateTime.of(2024, 5, 14, 20, 0), "Equus",
                "Dai Bradley, Mel Churcher, Michael Jayston, Edward Jewesbury, Mary Macleod, Pip Miller," +
                        "Louie Ramsay, Peter Schofield, Jane Wenham", ShowType.THRILLER, "Inspired by a true " +
                "story, Peter Shaffer’s gripping psychological thriller, EQUUS, explores the complex relationships " +
                "between devotion, myth and sexuality.", 120, "John Dexter", admin,
                new Image("/com/example/theatrereservationsystem/images/Equus-1973a.jpg"));

        try{
            service.saveShow(show);
        } catch (Exception ex){
            System.out.println("Eroare salvare:" + ex.getMessage());
        }*/

        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(getClass().getResource("/com/example/theatrereservationsystem/views/main-screen.fxml"));
        AnchorPane userLayout = mainLoader.load();
        primaryStage.setScene(new Scene(userLayout));
        primaryStage.setTitle("Welcome!");

        MainScreenController mainController = mainLoader.getController();
        mainController.setService(service, primaryStage);
    }
}
