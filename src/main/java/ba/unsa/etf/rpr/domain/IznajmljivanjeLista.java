package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class IznajmljivanjeLista implements Idable{
    private int id;
    private Iznajmljivanje iznajmljivanje;

    public IznajmljivanjeLista(int id, Iznajmljivanje iznajmljivanje) {
        this.id = id;
        this.iznajmljivanje = iznajmljivanje;
    }

    public IznajmljivanjeLista() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IznajmljivanjeLista that = (IznajmljivanjeLista) o;
        return id == that.id && Objects.equals(iznajmljivanje, that.iznajmljivanje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iznajmljivanje);
    }

    @Override
    public String toString() {
        return "IznajmljivanjeLista{" +
                "id=" + id +
                ", iznajmljivanje=" + iznajmljivanje +
                '}';
    }
}
