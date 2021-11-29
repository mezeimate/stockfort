package stockapp.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.tinylog.Logger;
import stockapp.database.DatabaseFelhaszRendelTermek;
import stockapp.database.DatabaseTermekRaktar;
import stockapp.model.DataBaseConnection;
import stockapp.model.Raktar;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A program rendelések képernyőjének megvalósítása.
 */
public class RendelesekUiController {

    @FXML
    Button rendFelBtn;
    @FXML
    Button visszaBtn;
    @FXML
    private TableView<DatabaseFelhaszRendelTermek> rendelesekTab;
    @FXML
    private TableColumn<DatabaseFelhaszRendelTermek, String> nevOszlop;
    @FXML
    private TableColumn<DatabaseFelhaszRendelTermek, String> termekOszlop;
    @FXML
    private TableColumn<DatabaseFelhaszRendelTermek, Integer> mennyisegOszlop;
    @FXML
    private TableColumn<DatabaseFelhaszRendelTermek, Date> datumOszlop;

    FXMLLoader fxmlLoader;

    String felhNev;

    int felhasznaloID;

    public void setFelhNev(String n){
        this.felhNev = n;
    }

    @FXML
    public void visszaAction(ActionEvent event) throws IOException {
        Logger.info("Főképernyő betöltése.");
        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainUI.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<MainUiController>getController().setFelhNev(felhNev);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    ObservableList<DatabaseFelhaszRendelTermek> data;
    ArrayList<DatabaseFelhaszRendelTermek> adatok;

    @FXML
    private void initialize() throws SQLException {
        nevOszlop.setCellValueFactory(new PropertyValueFactory<>("nevOszlop"));
        termekOszlop.setCellValueFactory(new PropertyValueFactory<>("termekOszlop"));
        mennyisegOszlop.setCellValueFactory(new PropertyValueFactory<>("mennyisegOszlop"));
        datumOszlop.setCellValueFactory(new PropertyValueFactory<>("datumOszlop"));
    }

    @FXML
    public void handleRendFelBtn(ActionEvent event) throws IOException {
        Logger.info("Rendelések felvétele oldal betöltése.");
        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/rendelesUI.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<RendelesUiController>getController().setFelhNev(felhNev);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void felhasznaloIDBack() throws SQLException {
        DataBaseConnection db = new DataBaseConnection();
        int getFelhID = -1;
        ResultSet id = db.getFelhasznaloID(felhNev);
        while(id.next()){
            getFelhID = id.getInt("id");
        }
        felhasznaloID = getFelhID;
    }

    public void DatabaseLoad() throws SQLException {
        felhasznaloIDBack();
        DataBaseConnection db = new DataBaseConnection();
        adatok = new ArrayList<>();
        data = FXCollections.observableArrayList();
        System.out.println(felhasznaloID +" !!!!!!!!!!");
        ResultSet result = db.getFelhaszRendelTermek(felhasznaloID);

        DatabaseFelhaszRendelTermek k = new DatabaseFelhaszRendelTermek();
        while(result.next()){
            k.setNevOszlop(result.getString("nev"));
            k.setTermekOszlop(result.getString("megnevezes"));
            k.setMennyisegOszlop(result.getInt("darab"));
            k.setDatumOszlop(result.getString("datum"));
            adatok.add(k);
            k = new DatabaseFelhaszRendelTermek();
        }
        for (int i = 0; i < adatok.size(); i++) {
            System.out.println(adatok.get(i).toString());
        }

        rendelesekTab.getItems().clear();
        data.addAll(FXCollections.observableArrayList(adatok));
        rendelesekTab.setItems(data);

    }
}