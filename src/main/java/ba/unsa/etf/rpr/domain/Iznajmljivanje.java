package ba.unsa.etf.rpr.domain;



import java.time.LocalDate;
import java.util.Objects;

public class Iznajmljivanje implements Idable{
    private int id;
    private int idvozilo;
    private int cijena;
    private LocalDate preuzimanje;
    private LocalDate vracanje;

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(int id, int idvozilo, int cijena, LocalDate preuzimanje, LocalDate vracanje) {
        this.id = id;
        this.idvozilo = idvozilo;
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

    public int getIdvozilo() {
        return idvozilo;
    }

    public void setIdvozilo(int idvozilo) {
        this.idvozilo = idvozilo;
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
        return id == that.id && idvozilo == that.idvozilo && cijena == that.cijena && Objects.equals(preuzimanje, that.preuzimanje) && Objects.equals(vracanje, that.vracanje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idvozilo, cijena, preuzimanje, vracanje);
    }

    @Override
    public String toString() {
        return "Iznajmljivanje{" +
                "id=" + id +
                ", idvozilo=" + idvozilo +
                ", cijena=" + cijena +
                ", preuzimanje=" + preuzimanje +
                ", vracanje=" + vracanje +
                '}';
    }
}
