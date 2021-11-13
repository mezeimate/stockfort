package stockapp.database;


import java.util.Date;

public class DatabaseRendelesek {

    int id;
    int termekid;
    int darab;
    Date datum;
    int felhasznaloid;
    int raktarid;

    public DatabaseRendelesek(){

    }

    public DatabaseRendelesek(int id, int termekid, int darab, Date datum, int felhasznaloid, int raktarid){
        this.id = id;
        this.termekid = termekid;
        this.darab=darab;
        this.datum = datum;
        this.felhasznaloid = felhasznaloid;
        this.raktarid = raktarid;
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

    public Date getDatabaseRendelesekDatum(){
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

    public void setDatabaseRendelesekDatum(Date datum){
        this.datum=datum;
    }

    public void setDatabaseRendelesekFelhasznaloID(int felhasznaloid){
        this.felhasznaloid=felhasznaloid;
    }

    public void setDatabaseRendelesekRaktarID(int raktarid){
        this.raktarid=raktarid;
    }

}
