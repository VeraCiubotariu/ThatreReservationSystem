package com.example.theatrereservationsystem;
import com.example.theatrereservationsystem.domain.validation.ShowValidator;
import com.example.theatrereservationsystem.domain.validation.TicketValidator;
import com.example.theatrereservationsystem.gui.MainScreenController;
import com.example.theatrereservationsystem.persistence.hibernate.AdministratorRepository;
import com.example.theatrereservationsystem.persistence.hibernate.SeatRepository;
import com.example.theatrereservationsystem.persistence.hibernate.ShowRepository;
import com.example.theatrereservationsystem.persistence.hibernate.TicketRepository;
import com.example.theatrereservationsystem.service.TheatreService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApplication extends Application {
    ShowRepository showRepository = new ShowRepository();
    TicketRepository ticketRepository = new TicketRepository();
    SeatRepository seatRepository = new SeatRepository();
    AdministratorRepository administratorRepository = new AdministratorRepository();
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
        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(getClass().getResource("/com/example/theatrereservationsystem/views/main-screen.fxml"));
        AnchorPane userLayout = mainLoader.load();
        primaryStage.setScene(new Scene(userLayout));
        primaryStage.setTitle("Welcome!");

        MainScreenController mainController = mainLoader.getController();
        mainController.setService(service, primaryStage);
    }
}
