package stockapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    final private Tooltip tooltip = new Tooltip();

    /**
     *  Secure Password requirements
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
    private void initialize(){
        tooltip.setText("A jelszó csak betűvel kezdődhet!\n" +
                "Tartalmaznia kell kisbetűt, nagybetűt, számot, speciális karaktert!\n" +
                "Nem tartalmazhat whitespace-t!\n" +
                "Legalább 8 karakternek kell lennie!");
        regisztracioButton.setTooltip(tooltip);
    }

    // login: admin admin
    @FXML
    private void handlelogButton(ActionEvent event) throws IOException {

        if (lognamein.getText().equals("admin") && passwordin.getText().equals("admin")) {

            Logger.info("Főképernyő betöltése.");

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainUI.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<MainUiController>getController().setFelhNev(lognamein.getText()+"");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else{
            Logger.warn("Hibas felhasznalonev vagy jelszo!");
        }
    }
    
    @FXML
    private void handleRegButt(ActionEvent event) {

    }

    @FXML
    private void bezarasAction() {
        Logger.info("ALkalmazas bezarasa!");
        Stage stage = (Stage) bezarasBtn.getScene().getWindow();
        stage.close();
    }

    public String passwdGenerator(String o){
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
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //a kész kód lekérése
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e){e.printStackTrace();}
        return generatedPassword;
    }
}