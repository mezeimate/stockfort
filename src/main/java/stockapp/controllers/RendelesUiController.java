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
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.tinylog.Logger;
import stockapp.database.DatabaseKategoria;
import stockapp.database.DatabaseTermek;
import stockapp.database.DatabaseTermekRaktar;
import stockapp.model.DataBaseConnection;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * A program rendelés felvétele képernyőjének megvalósítása.
 */
public class RendelesUiController {
    @FXML
    TextField nevbe;
    @FXML
    TextField datumbe;
    @FXML
    ComboBox termekbe;
    @FXML
    Spinner<Integer> termekdbbe;
    @FXML
    Button mentesBtn;
    @FXML
    Button visszaBtn;
    @FXML
    ComboBox kategoriabe;

    @FXML
    Label infolabel;

    FXMLLoader fxmlLoader;

    int felhasznaloID;

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private final Date date = new Date(System.currentTimeMillis());

    String felhNev;

    ObservableList<DatabaseTermekRaktar> data;
    ArrayList<DatabaseTermekRaktar> raktar;

    public void setFelhNev(String n){
        this.felhNev = n;
    }

    @FXML
    private void initialize() throws SQLException {
        infolabel.setVisible(false);
        DataBaseConnection db = new DataBaseConnection();
        ArrayList<DatabaseKategoria> kategoriak = new ArrayList<>();
        ResultSet result = db.getKategoriaTabel();

        DatabaseKategoria elem = new DatabaseKategoria();
        while(result.next()){
            elem.setKageoriaID(result.getInt("id"));
            elem.setKageoriaNev(result.getString("kategorianev"));
            kategoriak.add(elem);
            elem = new DatabaseKategoria();

        }
        datumbe.setText(formatter.format(date));

        for (DatabaseKategoria nebular : kategoriak) {
            kategoriabe.getItems().add(nebular.getKategoriaNev());
        }
        kategoriabe.getSelectionModel().selectFirst();
        termeketFrissit(kategoriabe.getValue().toString());

        kategoriabe.valueProperty().addListener(new ChangeListener<String>() {
            @SneakyThrows
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                //Previous Value: t
                //Current Value: t1
                termeketFrissit(t1);
            }
        });

        /*
        nevbe.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    nevbe.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        */
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

    public void termeketFrissit(String t1) throws SQLException {
        DataBaseConnection db = new DataBaseConnection();
        ResultSet termekekRes = db.getRaktarByTermek(t1);
        ArrayList<DatabaseTermek> termekeklista = new ArrayList<>();
        DatabaseTermek termekek = new DatabaseTermek();

        while(termekekRes.next()){
            termekek.setDatabaseTermekNev(termekekRes.getString("megnevezes"));
            termekeklista.add(termekek);
            termekek = new DatabaseTermek();
        }
        termekbe.getItems().clear();
        for (DatabaseTermek nebular : termekeklista) {
            termekbe.getItems().add(nebular.getDatabaseTermekNev());
        }
        termekbe.getSelectionModel().selectFirst();
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
    public void mentesHandleBtn() throws SQLException {
        infolabel.setVisible(false);
        if(!Objects.equals(nevbe.getText(), "")){

            if(vaneRaktaron((String)termekbe.getValue(), (Integer)termekdbbe.getValue())){

                DataBaseConnection db = new DataBaseConnection();
                int aktTerm = 0;
                ResultSet termid = db.getTermekID((String)termekbe.getValue());
                while(termid.next()){
                    aktTerm = termid.getInt("id");
                }
                db.insertRendelesekTable(aktTerm, (Integer)termekdbbe.getValue(), datumbe.getText(), felhasznaloID, nevbe.getText());
                infolabel.setText("Sikeres adatbevitel!");
                Logger.info("Sikeres adatfelvétel!");
                infolabel.setVisible(true);
                nevbe.setText("");
                kategoriabe.getSelectionModel().selectFirst();
                termekbe.getSelectionModel().selectFirst();

                db.updateRaktarTable(aktTerm, termekdbbe.getValue());
            }
            else {
                infolabel.setText("Nincs raktáron a kért termék!");
                infolabel.setVisible(true);
            }
        }
        else{
            infolabel.setVisible(true);
        }
    }

    public boolean vaneRaktaron(String megn, int db) throws SQLException {
        boolean vissza = false;
        DataBaseConnection adatb = new DataBaseConnection();

        raktar = new ArrayList<>();
        data = FXCollections.observableArrayList();
        ResultSet result = adatb.getRaktarTermekekTable();

        DatabaseTermekRaktar k = new DatabaseTermekRaktar();
        while(result.next()){
            k.setDatabaseTermekRaktarMegnevezes(result.getString("megnevezes"));
            k.setDatabaseTermekRaktarDarab(result.getInt("darab"));
            raktar.add(k);
            k = new DatabaseTermekRaktar();
        }

        for (int i = 0; i < raktar.size(); i++) {
            if(raktar.get(i).getTermekMegnevezes().equals(megn) && raktar.get(i).getRaktarDarab() >= db){
                vissza = true;
            }
        }

        return vissza;
    }
}