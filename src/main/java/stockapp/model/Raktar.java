package stockapp.model;

import java.util.Date;

/**
 * A Raktar osztály valósítja meg a raktár megjelenítéséhez szükséges táblázat adatszerkezetét.
 */
@lombok.Data
public class Raktar {
    String termekOszlop;
    int mennyisegOszlop;

    /**
     * A Raktar osztály konstruktora.
     */
    public Raktar(String termekOszlop, int mennyisegOszlop) {
        //this.nevOszlop = new SimpleStringProperty(nevOszlop);
        //this.termekOszlop = new SimpleStringProperty(termekOszlop);
        //this.mennyisegOszlop = new SimpleIntegerProperty(mennyisegOszlop);
        //this.datumOszlop = new SimpleObjectProperty<Date>(datumOszlop);
        this.termekOszlop = termekOszlop;
        this.mennyisegOszlop = mennyisegOszlop;
    }

    /**
     * A Raktar osztály plédányait kiíró metódusa.
     */
    @Override
    public String toString() {
        return "tmp{" +
                ", termek='" + termekOszlop + '\'' +
                ", mennyiseg=" + mennyisegOszlop +
                '}'+"\r\n";
    }
}
