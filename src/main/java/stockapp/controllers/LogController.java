package stockapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    Button bezarasBtn;

    @FXML
    private void handlelogButton(ActionEvent event) throws IOException {
        Logger.info("Főképernyő betöltése.");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainUI.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<MainUiController>getController().setFelhNev(lognamein.getText()+"");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void bezarasAction() {
        // TO DO close the application
    }
}