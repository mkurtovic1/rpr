package ba.unsa.etf.rpr.domain;

import java.util.Objects;
//samo da ga git pokupi
public class Korisnik implements Idable{
    private int id;
    private String ime;
    private String prezime;
    private String lozinka;
    private String email;
    private int telefon;
    private int brojIznajmljivanja;
    private int brojUnajmljivanja;

    public int getId() {
        return id;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public String getEmail() {
        return email;
    }

    public int getTelefon() {
        return telefon;
    }

    public int getBrojIznajmljivanja() {
        return brojIznajmljivanja;
    }

    public int getBrojUnajmljivanja() {
        return brojUnajmljivanja;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public void setBrojIznajmljivanja(int brojIznajmljivanja) {
        this.brojIznajmljivanja = brojIznajmljivanja;
    }

    public void setBrojUnajmljivanja(int brojUnajmljivanja) {
        this.brojUnajmljivanja = brojUnajmljivanja;
    }

    public Korisnik(String ime, String prezime, String lozinka, String email, int telefon, int brojIznajmljivanja, int brojUnajmljivanja) {
        this.ime=ime;
        this.prezime = prezime;
        this.lozinka = lozinka;
        this.email = email;
        this.telefon = telefon;
        this.brojIznajmljivanja = brojIznajmljivanja;
        this.brojUnajmljivanja = brojUnajmljivanja;
    }

    public Korisnik() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ime, prezime, lozinka, email, telefon, brojIznajmljivanja, brojUnajmljivanja);
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null || getClass()!= obj.getClass()) return false;
        Korisnik korisnik=(Korisnik) obj;
        return id==korisnik.id;
    }
}
