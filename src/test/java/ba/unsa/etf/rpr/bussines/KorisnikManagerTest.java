package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.business.KorisnikManager;
import ba.unsa.etf.rpr.dao.KorisnikDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Korisnik;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KorisnikManagerTest {
    private KorisnikManager korisnikManager;
    private Korisnik korisnik;
    private KorisnikDaoSQLImpl korisnikDaoSQL;
    private List<Korisnik> korisnici;
    //before each test
    @BeforeEach
    public void initializeObjesWeNeed(){
        korisnikManager= Mockito.mock(KorisnikManager.class);
        korisnik=new Korisnik();
        korisnik.setEmail("mkmk@gmail.com");
        korisnik.setId(3);
        korisnik.setLozinka("147258369");
        korisnik.setIme("MK");
        korisnik.setPrezime("k");

        korisnikDaoSQL=Mockito.mock(KorisnikDaoSQLImpl.class);
        korisnici=new ArrayList<>();
        korisnici.addAll(Arrays.asList(new Korisnik("mirela", "kur", "253988", "df@gmail.com" )));
    }

}
