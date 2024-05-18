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
        Administrator admin = new Administrator("clau", "abcd1234", "Claudia",
                "Popescu");
        admin.setId(1);

        /*Show show = new Show(LocalDateTime.of(2024, 5, 22, 20, 0), "Equus",
                "Dai Bradley, Mel Churcher, Michael Jayston, Edward Jewesbury, Mary Macleod, Pip Miller," +
                        "Louie Ramsay, Peter Schofield, Jane Wenham", ShowType.THRILLER, "Inspired by a true " +
                "story, Peter Shaffer’s gripping psychological thriller, EQUUS, explores the complex relationships " +
                "between devotion, myth and sexuality.", 120, "John Dexter", admin,
                new Image("/com/example/theatrereservationsystem/images/Equus-1973a.jpg"));*/

        Show show = new Show(LocalDateTime.of(2024, 5, 18, 20, 0),
                "Hedda Gabler", "Richard Bovnoczki, Marius Manole, Ana Ciontea, Raluca Aprodu, " +
                "Crina Semciuc, Alexandru Potocean", ShowType.DRAMA, "In Thomas Ostermeier's view, Hedda " +
                "Gabler is a product of the digital age, a woman used to getting exactly what she wants. But this " +
                "Hedda realizes that there is a big difference between what she thinks she wants and what she really " +
                "needs. In Ostermeier's vision, the brilliant Hedda is a combination of femme fatale and spoiled " +
                "brat who has grown bored of her new toys and amuses herself by playing dangerous games.", 115,
                "Thomas Ostermeier", admin, new Image("/com/example/theatrereservationsystem/images/large_Afis_Hedda_3.jpg"));
        /*Show show = new Show(LocalDateTime.of(2022, 10, 21, 19, 0),
                "The Titanic Orchestra", "Claudiu Bleont, Mihai Calin, Tania Popa, Mihai Constantin, " +
                "Richard Bovnoczki", ShowType.COMEDY, "A philosophical and absurd comedy about the desire " +
                "to escape into illusion. In a decommissioned railroad, four outsiders, four burlesque characters " +
                "are waiting for a train that never stops.", 90, "Felix Alexa", admin,
                new Image("/com/example/theatrereservationsystem/images/large_Afis_Orchestra_Titanic.jpg"));*/

        /*Show show = new Show(LocalDateTime.of(2024, 10, 20, 17, 0), "UFO",
                "Ada Gales, Alexandru Potocean, Mihai Calin, Medeea Marinescu, Istvan Teglas, Ciprian Nicula, Raluca Aprodu, Ion, Caramitru",
                ShowType.ROMANCE, "What are UFOs like? Do they have a certain shape, do they communicate in a certain way? No, there is nothing concrete, visible. It is a moment of loss of consciousness, a detachment from the body, from the sensory world we are accustomed to, it is an illumination, a loss of oneself, an inexplicable emotion, a momentary disturbance. To come out of oneself, to break the barriers of daily perception, to penetrate into a completely unknown dimension.",
                80, "Bobi Pricop", admin, new Image("/com/example/theatrereservationsystem/images/large_UFO_NOU_2018_web-1.jpg"));*/
        /*Show show = new Show(LocalDateTime.of(2024, 5, 15, 21, 0), "Exile",
                "Ada Gales, Cosmina Olariu, Crina Semciuc, Ana Ciontea, Diana Dumbrava, Vitalie Bichir, Emilian Oprea, Florin Calbajos, Liviu Lucaci",
                ShowType.DRAMA, "\"About the exile and the relationship of anger and love with Romania\" - in the new premiere at NTB, by Alexandra Badea\nExile, a show about recent history, also inspired by the author-director's own biography!",
                160, "Alexandra Badea", admin, new Image("/com/example/theatrereservationsystem/images/large_Afis_Facebook_2.jpg"));*/
        /*Show show = new Show(LocalDateTime.of(2024, 7, 10, 18, 0), "The Forest of the Hanged",
                "Alexandru Potocean, Richard Bovonzki, Raluca Aprodu, Vitalie Bichir, Ada Gales, Alexandra Salaceanu, Vlad Galer, Ciprian Nicula, Emilian Marnea",
                ShowType.PSYCHOLOGICAL, "Far from any form of nationalism, the show provokes a lucid reflection on the state of the nation, a clear confrontation with our historical past, a dialogue between the voice of the present and the voice of history.",
                190, "Radu Afrim", admin, new Image("/com/example/theatrereservationsystem/images/large_Afis_final_bun_Padurea.jpg"));
*/
        /*try{
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
