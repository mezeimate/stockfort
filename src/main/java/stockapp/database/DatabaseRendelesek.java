package stockapp.database;


import java.util.Date;

public class DatabaseRendelesek {

    int id;
    int termekid;
    int darab;
    String datum;
    int felhasznaloid;
    int raktarid;
    String nev;

    public DatabaseRendelesek(){

    }

    public DatabaseRendelesek(int id, int termekid, int darab, String datum, int felhasznaloid, int raktarid, String nev){
        this.id = id;
        this.termekid = termekid;
        this.darab=darab;
        this.datum = datum;
        this.felhasznaloid = felhasznaloid;
        this.raktarid = raktarid;
        this.nev = nev;
    }

    public int getDatabaseRendelesekID(){
        return id;
    }

    public int getDatabaseRendelesekTermekID(){
        return termekid;
    }

    public int getDatabaseRendelesekDarab(){
        return darab;
    }

    public String getDatabaseRendelesekDatum(){
        return datum;
    }

    public int getDatabaseRendelesekFelhasznaloID(){
        return felhasznaloid;
    }

    public int getDatabaseRendelesekRaktarID(){
        return raktarid;
    }

    public void setDatabaseRendelesekID(int id){
       this.id = id;
    }

    public void setDatabaseRendelesekTermekID(int termekid){
        this.termekid = termekid;
    }

    public void setDatabaseRendelesekDarab(int darab){
        this.darab=darab;
    }

    public void setDatabaseRendelesekDatum(String datum){
        this.datum=datum;
    }

    public void setDatabaseRendelesekFelhasznaloID(int felhasznaloid){
        this.felhasznaloid=felhasznaloid;
    }

    public void setDatabaseRendelesekRaktarID(int raktarid){
        this.raktarid=raktarid;
    }

    public void setDatabaseRendelesekFelhasznaloNev(String nev){
        this.nev=nev;
    }

    public String getDatabaseRendelesekRaktarNev(){
        return nev;
    }


}
