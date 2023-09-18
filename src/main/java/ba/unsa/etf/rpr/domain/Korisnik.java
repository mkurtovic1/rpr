package ba.unsa.etf.rpr.domain;

import java.util.Objects;
//samo da ga git pokupi
public class Korisnik implements Idable{
    private int id;
    private String ime;
    private String prezime;
    private String lozinka;
    private String email;



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



    public Korisnik(String ime, String prezime, String lozinka, String email, int telefon, int brojIznajmljivanja, int brojUnajmljivanja) {
        this.ime=ime;
        this.prezime = prezime;
        this.lozinka = lozinka;
        this.email = email;

    }

    public Korisnik() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ime, prezime, lozinka, email);
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null || getClass()!= obj.getClass()) return false;
        Korisnik korisnik=(Korisnik) obj;
        return id==korisnik.id;
    }
}
