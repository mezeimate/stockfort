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
import lombok.SneakyThrows;
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

    String felhNev;

    public void setFelhNev(String n){
        this.felhNev = n;
    }

    @FXML
    private void initialize() throws SQLException {

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