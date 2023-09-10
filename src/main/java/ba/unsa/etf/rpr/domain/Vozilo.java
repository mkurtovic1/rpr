package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Vozilo implements Idable{
    private int id;
    private String naziv;
    private String gorivo;
    private String mjenjac;
    private int maxbrputnika;
    private int cijenapodanu;
    private String brojregtablica;
    private String tip ;

    public Vozilo() {
    }

    public Vozilo(int id, String naziv, String gorivo, String mjenjac, int maxbrputnika, int cijenapodanu, String brojregtablica, String tip) {
        this.id = id;
        this.naziv = naziv;
        this.gorivo = gorivo;
        this.mjenjac = mjenjac;
        this.maxbrputnika = maxbrputnika;
        this.cijenapodanu = cijenapodanu;
        this.brojregtablica = brojregtablica;
        this.tip = tip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getMaxbrputnika() {
        return maxbrputnika;
    }

    public void setMaxbrputnika(int maxbrputnika) {
        this.maxbrputnika = maxbrputnika;
    }

    public int getCijenapodanu() {
        return cijenapodanu;
    }

    public void setCijenapodanu(int cijenapodanu) {
        this.cijenapodanu = cijenapodanu;
    }

    public String getBrojregtablica() {
        return brojregtablica;
    }

    public void setBrojregtablica(String brojregtablica) {
        this.brojregtablica = brojregtablica;
    }

    public String getGorivo() {
        return gorivo;
    }

    public void setGorivo(String gorivo) {
        this.gorivo = gorivo;
    }

    public String getMjenjac() {
        return mjenjac;
    }

    public void setMjenjac(String mjenjac) {
        this.mjenjac = mjenjac;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vozilo vozilo = (Vozilo) o;
        return id == vozilo.id && maxbrputnika == vozilo.maxbrputnika && cijenapodanu == vozilo.cijenapodanu && Objects.equals(naziv, vozilo.naziv) && Objects.equals(gorivo, vozilo.gorivo) && Objects.equals(mjenjac, vozilo.mjenjac) && Objects.equals(brojregtablica, vozilo.brojregtablica) && Objects.equals(tip, vozilo.tip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv, gorivo, mjenjac, maxbrputnika, cijenapodanu, brojregtablica, tip);
    }

    @Override
    public String toString() {
        return "Vozilo{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", gorivo='" + gorivo + '\'' +
                ", mjenjac='" + mjenjac + '\'' +
                ", maxbrputnika=" + maxbrputnika +
                ", cijenapodanu=" + cijenapodanu +
                ", brojregtablica='" + brojregtablica + '\'' +
                ", tip='" + tip + '\'' +
                '}';
    }
}
