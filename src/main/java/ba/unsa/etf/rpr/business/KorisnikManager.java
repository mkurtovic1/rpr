package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Korisnik;

import java.util.List;

public class KorisnikManager {
    public void validateKorisnikName(String name) throws Exception {
        if(name==null || name.length()>15 || name.length()<3)
            throw new Exception("Ime mora biti izmedu 3 i 15 karaktera");
    }
    public Korisnik add(Korisnik korisnik) throws Exception {
        if(korisnik.getId()!=0) throw new Exception("ne moze se dodati id");
        validateKorisnikName(korisnik.getIme());
        try {
            return DaoFactory.korisnikDao().add(korisnik);
        }catch (Exception exception){
            if(exception.getMessage().contains("UQ_NAME")){
                    throw new Exception("Ime već postoji");
            }
            throw exception;
        }
    }
    public void delete(int id) throws Exception {
        try {
            DaoFactory.korisnikDao().delete(id);
        }catch (Exception e){
            throw e;
        }
    }
    public Korisnik update(Korisnik korisnik) throws Exception {
        validateKorisnikName(korisnik.getIme());
        return DaoFactory.korisnikDao().update(korisnik);
    }
    public List<Korisnik> getAll() throws Exception{
        return DaoFactory.korisnikDao().getAll();
    }
}
