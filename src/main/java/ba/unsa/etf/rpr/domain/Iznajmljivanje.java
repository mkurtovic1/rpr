package ba.unsa.etf.rpr.domain;



import java.time.LocalDate;
import java.util.Objects;
/**
 * List of possible categories for Iznajmljivanje
 *
 * @author MKurtovic
 */
public class Iznajmljivanje implements Idable{
    private int id;
    private int idvozila;
    private int cijena;
    private LocalDate preuzimanje;
    private LocalDate vracanje;

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(int id, int idvozila, int cijena, LocalDate preuzimanje, LocalDate vracanje) {
        this.id = id;
        this.idvozila = idvozila;
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

    public int getIdvozila() {
        return idvozila;
    }

    public void setIdvozila(int idvozila) {
        this.idvozila = idvozila;
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
        return id == that.id && idvozila == that.idvozila && cijena == that.cijena && Objects.equals(preuzimanje, that.preuzimanje) && Objects.equals(vracanje, that.vracanje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idvozila, cijena, preuzimanje, vracanje);
    }

    @Override
    public String toString() {
        return "Iznajmljivanje{" +
                "id=" + id +
                ", idvozila=" + idvozila +
                ", cijena=" + cijena +
                ", preuzimanje=" + preuzimanje +
                ", vracanje=" + vracanje +
                '}';
    }
}
