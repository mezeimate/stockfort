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

        for(int i=0; i<kategoriak.size();i++){
            System.out.println(kategoriak.get(i).getKategoriadID()+" | "+kategoriak.get(i).getKategoriaNev());
        }


        datumbe.setText(formatter.format(date));

        System.out.println("felnév: "+felhNev);

        for (DatabaseKategoria nebular : kategoriak)
        {
            kategoriabe.getItems().add(nebular.getKategoriaNev());
        }


        kategoriabe.getSelectionModel().selectFirst();

        DataBaseConnection db2 = new DataBaseConnection();
        ResultSet dolgok = db2.getRaktarByTermek(output+2);
        ArrayList<DatabaseTermek> termekeklista = new ArrayList<>();
        DatabaseTermek termekek = new DatabaseTermek();

        while(dolgok.next()){
            termekek.setDatabaseTermekNev(dolgok.getString("megnevezes"));
            termekeklista.add(termekek);

            termekek=new DatabaseTermek();

        }

        for(int i=0; i<termekeklista.size();i++){
            System.out.println(termekeklista.get(i).getDatabaseTermekNev());
        }

        for (DatabaseTermek nebular : termekeklista)
        {
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

        db.insertRendelesekTable(2,(int)termekdbbe.getValue(), datumbe.getText(),1,kategoriaRaktar);

        String nev = nevbe.getText();

        int termekdb = (int) termekdbbe.getValue();



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