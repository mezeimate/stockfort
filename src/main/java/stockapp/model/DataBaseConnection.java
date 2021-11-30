package stockapp.model;

import org.tinylog.Logger;

import java.sql.*;

public class DataBaseConnection {

    Connection conn;
    Statement statement;
    ResultSet result;

    public DataBaseConnection() {
        String url = "jdbc:mariadb://localhost:3306/stockfort";
        try {
            conn = DriverManager.getConnection(url,"root","");
            statement = conn.createStatement();
        } catch (SQLException throwables) {throwables.printStackTrace();}
    }

    public ResultSet getFelhaszRendelTermek(int felhasznaloID){
        try{
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT felhasznalo.id,rendelesek.nev,termekek.megnevezes,rendelesek.darab,rendelesek.datum FROM felhasznalo INNER JOIN rendelesek ON felhasznalo.id = rendelesek.felhasznaloid INNER JOIN termekek ON termekek.id=rendelesek.termekid WHERE felhasznalo.id ="+felhasznaloID+";");
        }catch (SQLException throwables){throwables.printStackTrace();}
        return  result;
    }

    public ResultSet getFelhasznaloID(String nevt){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT id FROM felhasznalo WHERE nev = \""+nevt+"\" ;");
        } catch (SQLException throwables) {throwables.printStackTrace();}
        return result;
    }

    public ResultSet getTermekID(String n){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT id FROM `termekek` WHERE megnevezes = \""+n+"\";");
        } catch (SQLException throwables) {throwables.printStackTrace();}
        return result;
    }

    public ResultSet getRaktarTermekekTable(){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT raktar.darab,termekek.megnevezes FROM raktar INNER JOIN termekek ON raktar.termekid=termekek.id");
        } catch (SQLException throwables) {throwables.printStackTrace();}
        return result;
    }

    public ResultSet getKategoriaTabel(){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM kategoria");
        } catch (SQLException throwables) {throwables.printStackTrace();}
        return result;
    }

    public ResultSet getFelhasznaloTabel(){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM felhasznalo");

        } catch (SQLException throwables) {throwables.printStackTrace();}
        return result;
    }

    public void insertFelhasznaloTable(String nev, String jelszo){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM felhasznalo");
            boolean vane=true;

            while (result.next()) {
                    String n = result.getString("nev");
                    if (n.equals(nev)) {
                        vane = false;
                    }
            }

            if(vane){
                statement.executeUpdate("INSERT INTO felhasznalo (nev,jelszo)"+" VALUES (\""+nev+"\",\""+jelszo+"\")",Statement.RETURN_GENERATED_KEYS);
            }
        } catch (SQLException throwables) {throwables.printStackTrace();}
    }

    public ResultSet getRaktarByTermek(String katnev){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT termekek.megnevezes FROM termekek, kategoria WHERE termekek.kategoriaid = kategoria.id AND kategoria.kategorianev = \""+katnev+"\";");

        } catch (SQLException throwables) {throwables.printStackTrace();}
        return result;
    }

    public void updateRaktarTable(int termekid,int darab){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM raktar");

            while (result.next()) {
                int n = result.getInt("termekid");
                if (n == termekid) {
                    String query = "UPDATE raktar SET darab = darab - ? WHERE termekid = ?;";

                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setInt(1, darab);
                    preparedStmt.setInt(2, termekid);

                    preparedStmt.executeUpdate();
                }
            }
        } catch (SQLException throwables) {throwables.printStackTrace();}
    }

    public void insertRendelesekTable(int termekid, int darab, String datum, int felhasznaloid, String nev){
        try {
            statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO rendelesek (termekid, darab, datum, felhasznaloid, nev)"+" VALUES (" + termekid+", " + darab + ", \"" + datum + "\", "+ felhasznaloid + ", \"" + nev + "\");");
            Logger.info("INSERT INTO rendelesek (termekid, darab, datum, felhasznaloid, nev)"+" VALUES (" + termekid+", " + darab + ", \"" + datum + "\", "+ felhasznaloid + ", \"" + nev + "\");");
        } catch (SQLException throwables) {throwables.printStackTrace();}
    }
}
