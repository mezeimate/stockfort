package stockapp.database;

public class DatabaseTermek {

    String megnevezes;

    public DatabaseTermek(){}

    public DatabaseTermek(String megnevezes){
        this.megnevezes = megnevezes;

    }

    public String getDatabaseTermekNev(){
        return megnevezes;
    }

    public void setDatabaseTermekNev(String nev){
        this.megnevezes=nev;

    }




}
