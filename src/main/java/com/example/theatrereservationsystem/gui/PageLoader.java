package com.example.theatrereservationsystem.gui;

import com.example.theatrereservationsystem.domain.Administrator;
import com.example.theatrereservationsystem.domain.Show;
import com.example.theatrereservationsystem.service.TheatreService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PageLoader {
    public static void loadMainScreen(TheatreService service, Stage stage){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PageLoader.class.getResource("/com/example/theatrereservationsystem/views/main-screen.fxml"));
            AnchorPane root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Welcome!");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            MainScreenController mainScreenController = loader.getController();
            mainScreenController.setService(service, dialogStage);

            dialogStage.show();
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadAdminMenu(TheatreService service, Administrator administrator, Stage stage){
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PageLoader.class.getResource("/com/example/theatrereservationsystem/views/admin-menu-view.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Admin Menu --- User: " + administrator.getUsername());
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            AdminMenuController mainScreenController = loader.getController();
            mainScreenController.setService(service, administrator, dialogStage);

            dialogStage.show();
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadAdminLogin(TheatreService service, Stage stage){
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PageLoader.class.getResource("/com/example/theatrereservationsystem/views/admin-login-view.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Admin Login");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            AdminLoginController controller = loader.getController();
            controller.setService(service, dialogStage);

            dialogStage.show();
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadSeatSelection(TheatreService service, Stage stage){
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PageLoader.class.getResource("/com/example/theatrereservationsystem/views/seat_selection_view.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Seat selection");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            SeatSelectionController seatSelectionController = loader.getController();
            seatSelectionController.setService(service, dialogStage);

            dialogStage.show();
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadShowInformation(TheatreService service, Show show, Stage stage){
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PageLoader.class.getResource("/com/example/theatrereservationsystem/views/show_information_view.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Show information");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            ShowInformationController controller = loader.getController();
            controller.setService(service, show, dialogStage);

            dialogStage.show();
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFutureShowsScreen(TheatreService service, Stage stage){
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PageLoader.class.getResource("/com/example/theatrereservationsystem/views/future-shows-view.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Future shows");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            FutureShowsController controller = loader.getController();
            controller.setService(service, dialogStage);

            dialogStage.show();
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadAdminFutureShowsScreen(TheatreService service, Administrator admin, Stage stage){
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PageLoader.class.getResource("/com/example/theatrereservationsystem/views/admin-future-shows-view.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Manage future shows");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            AdminFutureShowsController controller = loader.getController();
            controller.setService(service, admin, dialogStage);

            dialogStage.show();
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadPastShowsScreen(TheatreService service, Administrator admin, Stage stage){
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PageLoader.class.getResource("/com/example/theatrereservationsystem/views/past-shows-view.fxml"));
            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Past shows");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            PastShowsController controller = loader.getController();
            controller.setService(service, admin, dialogStage);

            dialogStage.show();
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
