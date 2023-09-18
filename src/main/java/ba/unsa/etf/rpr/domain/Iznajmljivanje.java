package ba.unsa.etf.rpr.domain;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.util.Objects;

public class Iznajmljivanje implements Idable{
    private int id;
    private int klijentId;
    private int voziloId;
    private int cijena;
    private LocalDate preuzimanje;
    private LocalDate vracanje;

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(int klijentId, int voziloId, int cijena, LocalDate preuzimanje, LocalDate vracanje) {
        this.klijentId = klijentId;
        this.voziloId = voziloId;
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

    public int getKlijentId() {
        return klijentId;
    }

    public void setKlijentId(int klijentId) {
        this.klijentId = klijentId;
    }

    public int getVoziloId() {
        return voziloId;
    }

    public void setVoziloId(int voziloId) {
        this.voziloId = voziloId;
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
        return getId() == that.getId() && getKlijentId() == that.getKlijentId() && getVoziloId() == that.getVoziloId() && getCijena() == that.getCijena() && Objects.equals(getPreuzimanje(), that.getPreuzimanje()) && Objects.equals(getVracanje(), that.getVracanje());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getKlijentId(), getVoziloId(), getCijena(), getPreuzimanje(), getVracanje());
    }

    @Override
    public String toString() {
        return "Iznajmljivanje{" +
                "id=" + id +
                ", klijentId=" + klijentId +
                ", voziloId=" + voziloId +
                ", cijena=" + cijena +
                ", preuzimanje=" + preuzimanje +
                ", vracanje=" + vracanje +
                '}';
    }
}
