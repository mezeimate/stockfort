package stockapp.database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseFelhaszRendelTermek {

    String nev;
    String termeknev;
    int darab;
    Date datum;

    public DatabaseFelhaszRendelTermek(ResultSet result) throws SQLException {
        while(result.next()){
            this.nev= result.getString("nev");
            this.termeknev = result.getString("megnevezes");
            this.darab = result.getInt("darab");
            this.datum = result.getDate("datum");
        }
    }

    public String getRendeloNeve(){
        return nev;
    }

    public String getRendeltTermekNev(){
        return termeknev;
    }

    public int getDarab(){
        return darab;
    }

    public Date getDatum(){
        return datum;
    }

    public void setRendeloNeve(String nev){
        this.nev=nev;
    }

    public void setRendeltTermekNev(String termeknev){
        this.termeknev=termeknev;
    }

    public void setDarab(int darab){
        this.darab=darab;
    }

    public void setDatum(Date datum){
        this.datum=datum;
    }

}
