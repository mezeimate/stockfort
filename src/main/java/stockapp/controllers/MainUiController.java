package stockapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.tinylog.Logger;
import stockapp.database.DatabaseKategoria;
import stockapp.model.DataBaseConnection;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    private void initialize() throws SQLException {
        //Adatbázis teszteléshez
       /* DataBaseConnection db = new DataBaseConnection();
        ResultSet result = db.getKategoriaTabel();
        ArrayList<DatabaseKategoria> kategoriak = new ArrayList<>();
        DatabaseKategoria k = new DatabaseKategoria();

        while(result.next()){
            //System.out.println(result.getInt("id")+" | "+result.getString("kategorianev"));
             k.setKageoriaID(result.getInt("id"));
             k.setKageoriaNev(result.getString("kategorianev"));
             kategoriak.add(k);

             k=new DatabaseKategoria();

        }

        for(int i=0; i<kategoriak.size();i++){
            System.out.println(kategoriak.get(i).getKategoriadID()+" | "+kategoriak.get(i).getKategoriaNev());
        }*/


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