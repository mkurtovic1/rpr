package ba.unsa.etf.rpr.domain;



import java.time.LocalDate;
import java.util.Objects;

public class Iznajmljivanje implements Idable{
    private int id;
    private Korisnik korisnik;
    private Vozilo vozilo;
    private int cijena;
    private LocalDate preuzimanje;
    private LocalDate vracanje;

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(int id, Korisnik korisnik, Vozilo vozilo, int cijena, LocalDate preuzimanje, LocalDate vracanje) {
        this.id=id;
        this.korisnik=korisnik;
        this.vozilo=vozilo;
        this.cijena = cijena;
        this.preuzimanje = preuzimanje;
        this.vracanje = vracanje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }

    public int getCijena() {
        return cijena;
    }

    public void setCijena(int cijena) {
        this.cijena = cijena;
    }

    public LocalDate getPreuzimanje() {
        return preuzimanje;
    }

    public void setPreuzimanje(LocalDate preuzimanje) {
        this.preuzimanje = preuzimanje;
    }

    public LocalDate getVracanje() {
        return vracanje;
    }

    public void setVracanje(LocalDate vracanje) {
        this.vracanje = vracanje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Iznajmljivanje that = (Iznajmljivanje) o;
        return id == that.id && cijena == that.cijena && Objects.equals(korisnik, that.korisnik) && Objects.equals(vozilo, that.vozilo) && Objects.equals(preuzimanje, that.preuzimanje) && Objects.equals(vracanje, that.vracanje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, korisnik, vozilo, cijena, preuzimanje, vracanje);
    }

    @Override
    public String toString() {
        return "Iznajmljivanje{" +
                "id=" + id +
                ", korisnik=" + korisnik +
                ", vozilo=" + vozilo +
                ", cijena=" + cijena +
                ", preuzimanje=" + preuzimanje +
                ", vracanje=" + vracanje +
                '}';
    }
}
