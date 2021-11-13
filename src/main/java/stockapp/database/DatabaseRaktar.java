package stockapp.database;

public class DatabaseRaktar {
    int id;
    int termekid;
    int darab;

    public DatabaseRaktar(){

    }

    public DatabaseRaktar(int id, int termekid,int darab){
        this.id=id;
        this.termekid = termekid;
        this.darab=darab;
    }

    public int getDatabaseRaktarID(){
        return id;
    }

    public int getDatabaseRaktarTermekID(){
        return termekid;
    }

    public int getDatabaseRaktarDarab(){
        return darab;
    }

    public void setDatabaseRaktarID(int id){
        this.id = id;
    }

    public void setDatabaseRaktarTermekID(int termekid){
       this.termekid=termekid;
    }

    public void setDatabaseRaktarDarab(int darab){
        this.darab=darab;
    }



}
