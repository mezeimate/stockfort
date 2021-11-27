package stockapp.model;

import org.tinylog.Logger;

import java.sql.*;
import java.util.Date;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class DataBaseConnection {

    Connection conn;
    Statement statement;
    ResultSet result;

   /* private String databaseURL="jdbc:sqlserver://sql303.epizy.com:3306";
    private String databaseAcc="epiz_30461573";
    private String databasePass="afX17N82KY";
    private String databaseName= "epiz_30461573_stockfort";
    */


    String connectionUrl = "jdbc:sqlserver://web199.sunwell.hu:1433;databaseName=c65matyaka;";

    public DataBaseConnection() {
       // String url = "jdbc:mariadb://localhost:3306/stockfort";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            DriverManager.registerDriver(new SQLServerDriver());

            conn = DriverManager.getConnection(connectionUrl,"c65matyaka","vgcsR@aKWGB5");

            statement = conn.createStatement();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getRaktarTermekekTable(){
        try {

            //conn = DriverManager.getConnection(connectionUrl,"c65matyaka","vgcsR@aKWGB5");
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT raktar.darab,termekek.megnevezes FROM raktar INNER JOIN termekek ON raktar.termekid=termekek.id");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
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

    public void insertKategoriaTable(String kategorianeve){

        try{
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM kategoria");
            boolean vane = true;
            String k;
            while (result.next()) {
                k = result.getString("kategorianev");
                if (k.equals(kategorianeve)) {
                   Logger.info("Van ilyen kategória");
                   vane = false;
                }
                k="";

            }

            if(vane == true){
                statement.executeUpdate("INSERT INTO kategoria (kategorianev)"+" VALUES ('"+kategorianeve+"')", Statement.RETURN_GENERATED_KEYS);
            }



        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

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

    public void insertFelhasznaloTable(String nev, String jelszo){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM felhasznalo");
            boolean vane=true;

            while (result.next()) {
                    String n = result.getString("nev");
                    if (n.equals(nev)) {
                        Logger.info("Van ilyen nevű felhasználó");
                        vane = false;
                    }
            }

            if(vane){
                statement.executeUpdate("INSERT INTO felhasznalo (nev,jelszo)"+" VALUES ("+nev+","+jelszo+")",Statement.RETURN_GENERATED_KEYS);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

    public void insertRaktarTable(int termekid,int darab){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM raktar");
            boolean vane=true;

            while (result.next()) {
                    int n = result.getInt("termekid");
                    if (n == termekid) {
                        Logger.info("Van ilyen termék ID");
                        vane = false;
                    }
            }

            if(vane){
                statement.executeUpdate("INSERT INTO raktar (termekid,darab)"+" VALUES ("+termekid+","+darab+")",Statement.RETURN_GENERATED_KEYS);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateRaktarTable(int termekid,int darab){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM raktar");

            while (result.next()) {
                int n = result.getInt("termekid");
                if (n == termekid) {
                    Logger.info("Van ilyen termék ID");
                    String query = "UPDATE raktar set darab = ? WHERE termekid = ?";

                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setInt   (1, darab);
                    preparedStmt.setInt(2, termekid);

                    preparedStmt.executeUpdate();

                }
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

    public void insertRendelesekTable(int termekid, int darab, Date datum,int felhasznaloid,int raktarid){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM rendelesek");
            boolean vane=true;

            while (result.next()) {
                int n = result.getInt("termekid");
                if (n == termekid) {
                    Logger.info("Van ilyen termék ID");
                    vane = false;
                }
            }

            if(vane){
                statement.executeUpdate("INSERT INTO rendelesek (termekid,darab,datum,felhasznaloid,raktarid)"+" VALUES ("+termekid+","+darab+")",Statement.RETURN_GENERATED_KEYS);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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


    public void insertTermekekTable(String megnevezes,int kategoriaid){
        try {
            statement = conn.createStatement();
            result = statement.executeQuery("SELECT * FROM termekek");
            boolean vane=true;

            while (result.next()) {
                String n = result.getString("megnevezes");
                if (n.equals(megnevezes)) {
                    Logger.info("Van ilyen termék");
                    vane = false;
                }
            }

            if(vane){
                statement.executeUpdate("INSERT INTO termekek (megnevezes,termekid)"+" VALUES ("+megnevezes+","+kategoriaid+")",Statement.RETURN_GENERATED_KEYS);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }




}
