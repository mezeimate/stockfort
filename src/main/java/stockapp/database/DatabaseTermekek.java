package stockapp.database;

public class DatabaseTermekek {

    int id;
    String megnevezes;
    int kategoriaid;

    public DatabaseTermekek(){

    }

    public DatabaseTermekek(String megnevezes){
        this.megnevezes = megnevezes;
    }

    public DatabaseTermekek(int id,String megnevezes,int kategoriaid){
        this.id=id;
        this.megnevezes=megnevezes;
        this.kategoriaid=kategoriaid;
    }

    public int getDatabaseTermekekID(){
        return id;
    }

    public String getDatabaseTermekekMegnevezes(){
        return megnevezes;
    }

    public int getDatabaseTermekekKategoriaID(){
        return kategoriaid;
    }

    public void setDatabaseTermekekID(int id){
        this.id=id;
    }

    public void setDatabaseTermekekMegnev(String megnevezes){
        this.megnevezes=megnevezes;
    }

    public void setDatabaseTermekekKategoriaID(int kategoriaid){
        this.kategoriaid=kategoriaid;
    }



}
