package stockapp.model;

import java.sql.*;

public class DataBaseConnection {

    Connection conn;
    Statement statement;
    ResultSet result;

    public DataBaseConnection() {
        String url = "jdbc:mariadb://localhost:3306/stockfort";
        try {
            conn = DriverManager.getConnection(url, "root", "");

            statement = conn.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public ResultSet getKategoriaTabel(){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM kategoria");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public ResultSet getFelhasznaloTabel(){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM felhasznalo");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;

    }

    public ResultSet getRaktarTabel(){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM raktar");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public ResultSet getRendelesekTabel(){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM rendelesek");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;

    }

    public ResultSet getTermekekTabel(){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM raktar");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }




}
