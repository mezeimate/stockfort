package stockapp.database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
@lombok.Data
public class DatabaseFelhaszRendelTermek {

    String nevOszlop;
    String termekOszlop;
    int mennyisegOszlop;
    Date datumOszlop;

    public DatabaseFelhaszRendelTermek(){
        this.nevOszlop = "";
        this.termekOszlop = "";
        this.mennyisegOszlop = 1;
        this.datumOszlop = new Date(System.currentTimeMillis());
    }

    public DatabaseFelhaszRendelTermek(ResultSet result) throws SQLException {
        while(result.next()){
            this.nevOszlop= result.getString("nev");
            this.termekOszlop = result.getString("megnevezes");
            this.mennyisegOszlop = result.getInt("darab");
            this.datumOszlop = result.getDate("datum");
        }
    }

    @Override
    public String toString() {
        return "DatabaseFelhaszRendelTermek{" +
                "nevOszlop='" + nevOszlop + '\'' +
                ", termekOszlop='" + termekOszlop + '\'' +
                ", mennyisegOszlop=" + mennyisegOszlop +
                ", datumOszlop=" + datumOszlop +
                '}';
    }
}
