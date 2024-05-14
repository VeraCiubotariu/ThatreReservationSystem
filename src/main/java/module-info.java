module com.example.theatrereservationsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires java.sql.rowset;


    opens com.example.theatrereservationsystem to javafx.fxml;
    exports com.example.theatrereservationsystem;

    opens com.example.theatrereservationsystem.domain to javafx.fxml;
    exports com.example.theatrereservationsystem.domain;

    opens com.example.theatrereservationsystem.gui to javafx.fxml;
    exports com.example.theatrereservationsystem.gui;

    opens com.example.theatrereservationsystem.persistence to javafx.fxml;
    exports com.example.theatrereservationsystem.persistence;

    opens com.example.theatrereservationsystem.service to javafx.fxml;
    exports com.example.theatrereservationsystem.service;
    exports com.example.theatrereservationsystem.domain.validation;
    opens com.example.theatrereservationsystem.domain.validation to javafx.fxml;
}