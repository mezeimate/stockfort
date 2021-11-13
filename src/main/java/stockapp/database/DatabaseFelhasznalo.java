package stockapp.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseFelhasznalo {
    int id;
    String nev;
    String jelszo;

    public DatabaseFelhasznalo(){
        this.id = 1;
        this.nev = "";
        this.jelszo = "";
    }

    public DatabaseFelhasznalo(ResultSet result) throws SQLException {
        this.id = result.getInt("id");
        this.nev = result.getString("nev");
        this.jelszo = result.getString("jelszo");

    }

    public int getFelhasznaloID(){
        return id;
    }

    public String getFelhasznaloNev(){
        return nev;
    }

    public String getFelhasznaloJelszo(){
        return jelszo;
    }



}
