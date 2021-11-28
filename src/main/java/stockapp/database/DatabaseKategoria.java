package stockapp.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseKategoria {

    int id;
    String kategorianev;

    public DatabaseKategoria(){
        this.id=0;
        this.kategorianev="semmi";
    }

    public DatabaseKategoria(ResultSet result) throws SQLException {
        while(result.next()){
            this.id= result.getInt("id");
            this.kategorianev = result.getString("kategorianev");
        }
    }

    public int getKategoriadID(){
        return id;
    }

    public String getKategoriaNev(){
        return kategorianev;
    }

    public void setKageoriaNev(String katnev){
        this.kategorianev = katnev;
    }

    public void setKageoriaID(int id){
        this.id = id;
    }


}
