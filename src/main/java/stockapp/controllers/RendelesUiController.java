package stockapp.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import stockapp.database.DatabaseTermek;
import stockapp.database.DatabaseTermekek;
import stockapp.model.DataBaseConnection;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.String.*;

/**
 * A program rendelés felvétele képernyőjének megvalósítása.
 */
public class RendelesUiController {

    @FXML
    Label hibaLabel;
    @FXML
    TextField nevbe;
    @FXML
    TextField datumbe;
    @FXML
    ComboBox termekbe;
    @FXML
    Spinner termekdbbe;
    @FXML
    Button mentesBtn;
    @FXML
    Button visszaBtn;
    @FXML
    ComboBox kategoriabe;

    FXMLLoader fxmlLoader;

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private final Date date = new Date(System.currentTimeMillis());

    //System.out.println(formatter.format(date));

    String felhNev;

    public void setFelhNev(String n){
        this.felhNev = n;
    }

    @FXML
    private void initialize() throws SQLException {
        nevbe.setEditable(false);
        nevbe.setText(felhNev);
        DataBaseConnection db = new DataBaseConnection();
        ArrayList<DatabaseKategoria> kategoriak = new ArrayList<>();
        ResultSet result = db.getKategoriaTabel();

        int output = kategoriabe.getSelectionModel().getSelectedIndex();
        System.out.println(output);

        DatabaseKategoria elem = new DatabaseKategoria();

        while(result.next()){
            elem.setKageoriaID(result.getInt("id"));
            elem.setKageoriaNev(result.getString("kategorianev"));
            kategoriak.add(elem);

            elem=new DatabaseKategoria();

        }

        datumbe.setText(formatter.format(date));

        System.out.println("felnév: "+felhNev);

        for (DatabaseKategoria nebular : kategoriak) {
            kategoriabe.getItems().add(nebular.getKategoriaNev());
        }

        kategoriabe.getSelectionModel().selectFirst();

        //DataBaseConnection db2 = new DataBaseConnection();
        ResultSet dolgok = db.getRaktarByTermek(output+2);
        ArrayList<DatabaseTermek> termekeklista = new ArrayList<>();
        DatabaseTermek termekek = new DatabaseTermek();

        // valtozast nézi a listener, csak azokat a termékeket kell lekérni amelyik kategória ki van valasztva    !!!!!!
        // t1 valtozo azt adja vissza melyik kategória van kivalasztva
        kategoriabe.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                System.out.println("Previous Value: "+t);
                System.out.println("Current Value: "+t1);
            }
        });

        while(dolgok.next()){
            termekek.setDatabaseTermekNev(dolgok.getString("megnevezes"));
            termekeklista.add(termekek);

            termekek = new DatabaseTermek();
        }

        for (DatabaseTermek nebular : termekeklista) {
            termekbe.getItems().add(nebular.getDatabaseTermekNev());
        }
        
    }

    @FXML
    public void visszaHandleBtn(ActionEvent event) throws IOException {
        Logger.info("Főképernyő betöltése.");
        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainUI.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<MainUiController>getController().setFelhNev(felhNev);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void mentesHandleBtn(){

        DataBaseConnection db = new DataBaseConnection();
        System.out.println(datumbe.getText());

        int kategoriaRaktar = kategoriabe.getSelectionModel().getSelectedIndex()+1;
        System.out.println(kategoriaRaktar);

        db.insertRendelesekTable(2,(int)termekdbbe.getValue(), datumbe.getText(),1,kategoriaRaktar,nevbe.getText());

        String nev = nevbe.getText();

        int termekdb = (int) termekdbbe.getValue();

    }
}