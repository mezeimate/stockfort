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
import stockapp.database.DatabaseTermekRaktar;
import stockapp.model.DataBaseConnection;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A program raktár képernyőjének megvalósítása.
 */
public class RaktarUiController {

    @FXML
    private TableView<DatabaseTermekRaktar> raktarTab;
    @FXML
    private TableColumn<DatabaseTermekRaktar, String> termekMegnevezes;
    @FXML
    private TableColumn<DatabaseTermekRaktar, Integer> raktarDarab;

    @FXML
    Button visszaBtn;

    String felhNev;

    ObservableList<DatabaseTermekRaktar> data;
    ArrayList<DatabaseTermekRaktar> raktar;

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
        termekMegnevezes.setCellValueFactory(new PropertyValueFactory<>("termekMegnevezes"));
        raktarDarab.setCellValueFactory(new PropertyValueFactory<>("raktarDarab"));

        DataBaseConnection db = new DataBaseConnection();
        raktar = new ArrayList<>();
        data = FXCollections.observableArrayList();
        ResultSet result = db.getRaktarTermekekTable();

        DatabaseTermekRaktar k = new DatabaseTermekRaktar();
        while(result.next()){
            k.setDatabaseTermekRaktarMegnevezes(result.getString("megnevezes"));
            k.setDatabaseTermekRaktarDarab(result.getInt("darab"));
            raktar.add(k);
            k = new DatabaseTermekRaktar();
        }

        raktarTab.getItems().clear();
        data.addAll(FXCollections.observableArrayList(raktar));
        raktarTab.setItems(data);

    }

    /*
    @FXML
    private void initialize() {
        mennyisegbe.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    mennyisegbe.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    */
}