package stockapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

public class LogController {

    @FXML
    Button logbutton;

    @FXML
    TextField lognamein;

    @FXML
    PasswordField passwordin;

    @FXML
    Button bezarasBtn;

    /**
     *  Secure Password requirements
     * - start-of-string
     * - a digit must occur at least once
     * - a lower case letter must occur at least once
     * - an upper case letter must occur at least once
     * - a special character must occur at least once
     * - no whitespace allowed in the entire string
     * - anything, at least eight places though
     * - end-of-string
     */
    private String passwdMatch = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!?@#$%^&+=])(?=\\S+$).{8,}$";

    // login: admin admin
    @FXML
    private void handlelogButton(ActionEvent event) throws IOException {

        if (lognamein.getText().equals("admin") && passwordin.getText().equals("admin")) {

            Logger.info("Főképernyő betöltése.");

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainUI.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<MainUiController>getController().setFelhNev(lognamein.getText()+"");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else{
            Logger.warn("Hibas felhasznalonev vagy jelszo!");
        }
    }

    @FXML
    private void bezarasAction() {
        // TO DO close the application
    }
}