package stockapp.model;

import java.util.Date;

/**
 * A Raktar osztály valósítja meg a raktár megjelenítéséhez szükséges táblázat adatszerkezetét.
 */
@lombok.Data
public class Raktar {
    String nevOszlop;
    String termekOszlop;
    int mennyisegOszlop;
    Date datumOszlop;

    /**
     * A Raktar osztály konstruktora.
     */
    public Raktar(String nevOszlop, String termekOszlop, int mennyisegOszlop, Date datumOszlop) {
        //this.nevOszlop = new SimpleStringProperty(nevOszlop);
        //this.termekOszlop = new SimpleStringProperty(termekOszlop);
        //this.mennyisegOszlop = new SimpleIntegerProperty(mennyisegOszlop);
        //this.datumOszlop = new SimpleObjectProperty<Date>(datumOszlop);
        this.nevOszlop = nevOszlop;
        this.termekOszlop = termekOszlop;
        this.mennyisegOszlop = mennyisegOszlop;
        this.datumOszlop = datumOszlop;
    }

    /**
     * A Raktar osztály plédányait kiíró metódusa.
     */
    @Override
    public String toString() {
        return "tmp{" +
                "nev='" + nevOszlop + '\'' +
                ", termek='" + termekOszlop + '\'' +
                ", mennyiseg=" + mennyisegOszlop +
                ", datum=" + datumOszlop +
                '}'+"\r\n";
    }
}
