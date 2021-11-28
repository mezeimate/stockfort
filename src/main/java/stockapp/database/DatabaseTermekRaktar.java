package stockapp.database;

import java.sql.ResultSet;
import java.sql.SQLException;

@lombok.Data
public class DatabaseTermekRaktar {
    String termekMegnevezes;
    Integer raktarDarab;

    public DatabaseTermekRaktar(){
        this.termekMegnevezes= "";
        this.raktarDarab=1;
    }

    public DatabaseTermekRaktar(ResultSet result) throws SQLException {
        while(result.next()){
            this.termekMegnevezes = result.getString("megnevezes");
            this.raktarDarab = result.getInt("darab");

        }
    }

    public String getDatabaseTermekRaktarMegnevezes(){
        return termekMegnevezes;
    }

    public int getDatabaseTermekRaktarDarab(){
        return raktarDarab;
    }

    public void setDatabaseTermekRaktarMegnevezes(String termeknev){
        this.termekMegnevezes = termeknev;
    }

    public void setDatabaseTermekRaktarDarab(int darab){
        this.raktarDarab=darab;
    }

    @Override
    public String toString() {
        return "DatabaseTermekRaktar{" +
                "termekMegnevezes='" + termekMegnevezes + '\'' +
                ", raktarDarab=" + raktarDarab +
                '}';
    }
}
