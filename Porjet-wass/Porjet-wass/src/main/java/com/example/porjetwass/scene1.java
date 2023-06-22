package com.example.porjetwass;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class scene1 {
    @FXML
    private TextField usernameId;
    @FXML
    private PasswordField passwordId;
    @FXML
    private TextField signUpUsernameTextField;
    @FXML
    private TextField signUpEmailTextField;
    @FXML
    private PasswordField signUpPasswordPasswordField;

    @FXML
    protected void onLogin() throws IOException {
        if (usernameId.getText().equals("admin") && passwordId.getText().equals("admin")) {
            Stage s = (Stage) usernameId.getScene().getWindow();
            FXMLLoader fx = new FXMLLoader(HelloApplication.class.getResource("Scene2.fxml"));
            Scene sc2 = new Scene(fx.load());
            s.setScene(sc2);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Authentification Error");
            alert.setHeaderText("Incorrect Username or Password");
            alert.setContentText("try another Password or username");
            alert.show();
        }

    }

}

