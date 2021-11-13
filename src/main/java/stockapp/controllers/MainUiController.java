package stockapp.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import lombok.extern.java.Log;
import org.tinylog.Logger;
import stockapp.model.DataBaseConnection;
import stockapp.model.Raktar;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A program fő képernyőjének megvalósítása.
 */
public class MainUiController {

    @FXML Label udvLabel;
    @FXML Button rendFelvBtn;
    @FXML Button rendBtn;
    @FXML Button raktarBtn;
    @FXML Button kijelentkezesBtn;

    FXMLLoader fxmlLoader;

    public void setFelhNev(String nev) {
        udvLabel.setText("Üdvözöllek, "+nev+"!");
    }

    @FXML
    private void initialize() {
        //Adatbázis teszteléshez
        /*DataBaseConnection db = new DataBaseConnection();
        db.getKategoriaTabel();*/

    }

    @FXML
    public void loadRendelesFelvetele(ActionEvent event) throws IOException {
        Logger.info("Rendelések felvétele oldal betöltése.");
        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/rendelesUI.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void loadRendelesek(ActionEvent event) throws IOException {
        Logger.info("Rendelések oldal betöltése.");
        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/rendelesekUI.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void loadRaktarkeszlet(ActionEvent event) throws IOException {
        Logger.info("Raktár oldal betöltése.");
        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/raktarUI.fxml"));
        Parent root = fxmlLoader.load();
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