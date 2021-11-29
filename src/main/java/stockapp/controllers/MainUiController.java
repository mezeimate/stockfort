package stockapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;
import java.sql.SQLException;

/**
 * A program fő képernyőjének megvalósítása.
 */
public class MainUiController {

    @FXML
    Label udvLabel;
    @FXML
    Button rendFelvBtn;
    @FXML
    Button rendBtn;
    @FXML
    Button raktarBtn;
    @FXML
    Button kijelentkezesBtn;

    String felhnev;

    FXMLLoader fxmlLoader;

    public void setFelhNev(String nev) {
        felhnev = nev;
        udvLabel.setText("Üdvözöllek, "+nev+"!");
    }

    @FXML
    private void initialize(){
    }

    @FXML
    public void loadRendelesFelvetele(ActionEvent event) throws IOException {
        Logger.info("Rendelések felvétele oldal betöltése.");
        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/rendelesUI.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<RendelesUiController>getController().setFelhNev(felhnev);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void loadRendelesek(ActionEvent event) throws IOException, SQLException {
        Logger.info("Rendelések oldal betöltése.");
        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/rendelesekUI.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<RendelesekUiController>getController().setFelhNev(felhnev);
        fxmlLoader.<RendelesekUiController>getController().felhasznaloIDBack();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void loadRaktarkeszlet(ActionEvent event) throws IOException {
        Logger.info("Raktár oldal betöltése.");
        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/raktarUI.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<RaktarUiController>getController().setFelhNev(felhnev);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void kijelentkezes(ActionEvent event) throws IOException {
        Logger.info("Bejelentkező oldal betöltése.");
        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/logUI.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}