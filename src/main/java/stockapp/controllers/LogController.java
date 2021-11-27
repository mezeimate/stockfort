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
import stockapp.database.DatabaseFelhasznalo;
import stockapp.database.DatabaseTermekRaktar;
import stockapp.model.DataBaseConnection;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class LogController {

    @FXML
    Button logbutton;

    @FXML
    TextField lognamein;

    @FXML
    PasswordField passwordin;

    @FXML
    Button bezarasBtn;

    @FXML
    Button regisztracioButton;

    @FXML
    Label loginError;

    private ArrayList<DatabaseFelhasznalo> logg;

    private ResultSet result;

    final private Tooltip tooltip = new Tooltip();

    /**
     * Secure Password requirements
     * - start-of-string
     * - a digit must occur at least once
     * - a lower case letter must occur at least once
     * - an upper case letter must occur at least once
     * - a special character must occur at least once
     * - no whitespace allowed in the entire string
     * - anything, at least eight places though
     * - end-of-string
     */
    private String passwdMatch = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!?@#$%^&+=])(?=\\S+$).{8,}$";

    @FXML
    private void initialize() {
        tooltip.setText("A jelszó csak betűvel kezdődhet!\n" +
                "Tartalmaznia kell kisbetűt, nagybetűt, számot, speciális karaktert!\n" +
                "Nem tartalmazhat whitespace-t!\n" +
                "Legalább 8 karakternek kell lennie!");
        regisztracioButton.setTooltip(tooltip);

        loginError.setVisible(false);
    }

    private void lekerFelh() throws SQLException{
        DataBaseConnection db = new DataBaseConnection();
        logg = new ArrayList<>();
        result = db.getFelhasznaloTabel();
        DatabaseFelhasznalo felhasznalo = new DatabaseFelhasznalo();
        while (result.next()) {
            felhasznalo.setNev(result.getString("nev"));
            felhasznalo.setJelszo(result.getString("jelszo"));
            logg.add(felhasznalo);
            felhasznalo = new DatabaseFelhasznalo();
        }
    }

    private Integer letezik(String n) throws SQLException {
        Integer vissza = null;
        lekerFelh();

        for (int i = 0; i < logg.size(); i++) {
            if(logg.get(i).getFelhasznaloNev().equals(n)){
                vissza = i;
            }
        }

        return vissza;
    }

    // login: admin admin
    @FXML
    private void handlelogButton(ActionEvent event) throws IOException, SQLException {
        loginError.setVisible(false);
        Integer letezike = letezik(lognamein.getText());
        if( letezike != null){
            if (lognamein.getText().equals(logg.get(letezike).getFelhasznaloNev())
                    && logg.get(letezike).getFelhasznaloJelszo().equals(passwdGenerator(passwordin.getText()))) {

                Logger.info("Főképernyő betöltése.");

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainUI.fxml"));
                Parent root = fxmlLoader.load();
                //fxmlLoader.<MainUiController>getController().setFelhNev(lognamein.getText() + "");
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }
            else{
                Logger.warn("Hibás felhasználónév vagy jelszó!");
                loginError.setText("Hibás felhasználónév vagy jelszó!");
                loginError.setVisible(true);
            }
        }
        else{
            Logger.warn("Nem létezik ilyen felhasználó!");
            loginError.setText("Nem létezik ilyen felhasználó!");
            loginError.setVisible(true);
        }
    }

    @FXML
    private void handleRegButt(ActionEvent event) throws SQLException {
        loginError.setVisible(false);
        Integer letezike = letezik(lognamein.getText());
        if(letezike == null && strongpass(passwordin.getText())){

            // INSERT INTO KELL MAJD név és jelszót kurelja bele
            //lognamein.getText()
            //passwdGenerator(passwordin.getText())

            loginError.setText("Sikeres regisztráció!");
            loginError.setVisible(true);
        }
        else{
            Logger.warn("Felhasználó már létezik!");
            loginError.setText("Felhasználó már létezik!");
            loginError.setVisible(true);
        }
    }

    private boolean strongpass(String p){
        boolean strong = true;
        if(!p.matches(passwdMatch))
            strong = false;
        return strong;
    }-

    @FXML
    private void bezarasAction() {
        Logger.info("ALkalmazas bezarasa!");
        Stage stage = (Stage) bezarasBtn.getScene().getWindow();
        stage.close();
    }

    public static String passwdGenerator(String o) {
        String generatedPassword = "";
        try {
            //MessageDigest példány létrehozása
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Jelszó hozzáadása a példányhoz
            md.update(o.getBytes());
            //Lekérjük a hash byteokat
            byte[] bytes = md.digest();
            //Byte típusi tombbe tároljuk a megkapott decimális értéket
            //Konvertálás
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //a kész kód lekérése
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

}