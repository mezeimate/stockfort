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

    public void getKategoriaTabel(){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM kategoria");
            while(result.next()){
                System.out.println(result.getInt("id")+" | "+result.getString("kategorianev"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void getFelhasznaloTabel(){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM felhasznalo");
            while(result.next()){
                System.out.println(result.getInt("id")+" | "+result.getString("nev")+" | "+result.getString("jelszo"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void getRaktarTabel(){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM raktar");
            while(result.next()){
                System.out.println(result.getInt("id")+" | "+result.getInt("termekid")+" | "+result.getInt("darab"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void getRendelesekTabel(){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM rendelesek");
            while(result.next()){
                System.out.println(result.getInt("id")+" | "+result.getInt("termekid")+" | "+result.getInt("darab")+" | "+result.getDate("datum")+" | "+result.getInt("felhasznaloid")+" | "+result.getInt("raktarid"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void getTermekekTabel(){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM raktar");
            while(result.next()){
                System.out.println(result.getInt("id")+" | "+result.getString("megnevezes")+" | "+result.getInt("kategoriaid"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }




}
