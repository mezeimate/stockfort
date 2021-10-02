package stockapp.controllers;

import javafx.event.ActionEvent;

import java.io.IOException;

public class LogController {

    @FXML
    Button logbutton;

    @FXML
    TextField lognamein;

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
}