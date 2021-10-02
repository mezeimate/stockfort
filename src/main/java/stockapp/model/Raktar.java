package stockapp.model;

/**
 * A Raktar osztály valósítja meg a raktár megjelenítéséhez szükséges táblázat adatszerkezetét.
 */
public class Raktar {

    /**
     * A Raktar osztály konstruktora.
     */

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
