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
import stockapp.database.DatabaseRaktar;
import stockapp.database.DatabaseTermekRaktar;
import stockapp.model.DataBaseConnection;
import stockapp.model.Raktar;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A program raktár képernyőjének megvalósítása.
 */
public class RaktarUiController {

    @FXML private TableView<Raktar> raktarTab;
    @FXML private TableColumn<Raktar, String> nevOszlop;
    @FXML private TableColumn<Raktar, String> mennyisegOszlop;
    @FXML private TableColumn<Raktar, Integer> datumOszlop;
    @FXML Button visszaBtn;

    String felhNev;

    public void setFelhNev(String n){
        this.felhNev = n;
    }

    @FXML
    public void visszaAction(ActionEvent event) throws IOException {
        Logger.info("Főképernyő betöltése.");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainUI.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<MainUiController>getController().setFelhNev(felhNev);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void initialize() throws SQLException {
        DataBaseConnection db = new DataBaseConnection();
        ArrayList<DatabaseTermekRaktar> raktar = new ArrayList<>();
        ResultSet result = db.getRaktarTermekekTable();

        DatabaseTermekRaktar k = new DatabaseTermekRaktar();


        while(result.next()){
            k.setDatabaseTermekRaktarDarab(result.getInt("darab"));
            k.setDatabaseTermekRaktarMegnevezes(result.getString("megnevezes"));
            raktar.add(k);

            k=new DatabaseTermekRaktar();

        }

        for(int i=0; i<raktar.size();i++){
            System.out.println(raktar.get(i).getDatabaseTermekRaktarMegnevezes()+" | "+raktar.get(i).getDatabaseTermekRaktarDarab());
        }

    }

    /*
    ObservableList<Raktar> data;
    ArrayList<Raktar> adatok;
    SimpleDateFormat sdf;

    @FXML
    private void initialize() {
        sdf = new SimpleDateFormat("dd-MM-yyyy");
        data = FXCollections.observableArrayList();
        adatok = new ArrayList<>();

        mennyisegbe.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    mennyisegbe.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        nevOszlop.setCellValueFactory(new PropertyValueFactory<>("nevOszlop"));
        termekOszlop.setCellValueFactory(new PropertyValueFactory<>("termekOszlop"));
        mennyisegOszlop.setCellValueFactory(new PropertyValueFactory<>("mennyisegOszlop"));
        datumOszlop.setCellValueFactory(new PropertyValueFactory<>("datumOszlop"));

        Logger.info("Adattabla létrehozva.");
    }

    @FXML
    public void handleFelvetelButt() throws ParseException{
        if(nevbe.getText() != null && termekbe.getText() != null && mennyisegbe.getText() != null && datumbe.getValue() != null){
            hibaLabel.setVisible(false);
            String nev = nevbe.getText();
            String termek = termekbe.getText();
            int db = Integer.parseInt(mennyisegbe.getText()+"");
            LocalDate ld = datumbe.getValue();
            Calendar c =  Calendar.getInstance();
            c.set(ld.getYear(), ld.getMonthValue()-1, ld.getDayOfMonth());
            Date date1 = c.getTime();

            adatok.add(new Raktar(nev,termek,db,date1));
            Logger.info(nev+", "+termek+", "+db+", "+date1+", adatsor felvéve a tablaba.");
            tablaFrissito(data);

            nevbe.setText(null);
            termekbe.setText(null);
            mennyisegbe.setText("");
            datumbe.setValue(null);
        }
        else{
            hibaLabel.setVisible(true);
        }
    }

    @FXML
    public void handleTorlesButt(){
        hibaLabel.setVisible(false);
        Raktar tmp = raktarTab.getSelectionModel().getSelectedItem();
        for (int i = 0; i < adatok.size(); i++) {
            if(adatok.get(i) == tmp){
                adatok.remove(i);
                Logger.info(adatok.get(i).toString()+", adatsor törölve!");
            }
        }
        tablaFrissito(data);
    }

    private void tablaFrissito(ObservableList<Raktar> data){
        hibaLabel.setVisible(false);
        raktarTab.getItems().clear();
        data.addAll(FXCollections.observableArrayList(adatok));
        raktarTab.setItems(data);
        Logger.info("Tabla frissítve.");
    }

    @FXML
    public void handleFrisitButt(){
        tablaFrissito(data);
    }*/
}