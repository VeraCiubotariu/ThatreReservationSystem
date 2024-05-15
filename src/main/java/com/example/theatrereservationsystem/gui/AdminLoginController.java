package com.example.theatrereservationsystem.gui;

import com.example.theatrereservationsystem.domain.Administrator;
import com.example.theatrereservationsystem.service.TheatreService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AdminLoginController {
    public Label messageLabel;
    private TheatreService service;
    private Stage stage;
    public PasswordField passwordField;
    public TextField usernameTextField;

    public void setService(TheatreService service, Stage stage){
        this.service = service;
        this.stage = stage;
    }

    public void handleGoBack(ActionEvent actionEvent) {
        PageLoader.loadMainScreen(service, stage);
    }

    public void handleLogin(ActionEvent actionEvent) {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        try{
            Optional<Administrator> admin = service.login(username, password);

            if(admin.isPresent()){
                PageLoader.loadAdminMenu(service, admin.get(), stage);
            }

            else {
                messageLabel.setText("Admin with given username doesn't exist!");
            }
        } catch (Exception ex){
            messageLabel.setText(ex.getMessage());
        }
    }
}
