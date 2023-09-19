package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.business.IznajmljivanjeManager;
import ba.unsa.etf.rpr.business.KorisnikManager;
import ba.unsa.etf.rpr.dao.IznajmljivanjeDaoSQLImpl;
import ba.unsa.etf.rpr.dao.KorisnikDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Iznajmljivanje;
import ba.unsa.etf.rpr.domain.Korisnik;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class IznajmljivanjeManagerTest {
    private IznajmljivanjeManager  iznajmljivanjeManager;
    private Iznajmljivanje iznajmljivanje;
    private IznajmljivanjeDaoSQLImpl iznajmljivanjeDaoSQL;
    private List<Iznajmljivanje> iznajmljivanjeList;

    @BeforeEach
    public void initializeObjestsWeNeed(){
        iznajmljivanjeManager= Mockito.mock(IznajmljivanjeManager.class);
        iznajmljivanje=new Iznajmljivanje();
        iznajmljivanje.setId(2);
        iznajmljivanje.setIdvozila(2);
        iznajmljivanje.setCijena(50);
        iznajmljivanje.setPreuzimanje(LocalDate.ofEpochDay(2023-10-15));
        iznajmljivanje.setVracanje(LocalDate.ofEpochDay(2023-11-01));

        iznajmljivanjeDaoSQL=Mockito.mock(iznajmljivanjeDaoSQL.getClass());
        iznajmljivanjeList=new ArrayList<>();

    }
    @Test
    public void testAddIznajmljivanje() throws Exception {

        Iznajmljivanje iznajmljivanjeToAdd = new Iznajmljivanje();
        when(iznajmljivanjeDaoSQL.add(iznajmljivanjeToAdd)).thenReturn(iznajmljivanjeToAdd);

        Iznajmljivanje addedIznajmljivanje = iznajmljivanjeManager.add(iznajmljivanjeToAdd);

        assertNotNull(addedIznajmljivanje);
        assertEquals(iznajmljivanjeToAdd, addedIznajmljivanje);
        verify(iznajmljivanjeDaoSQL, times(1)).add(iznajmljivanjeToAdd);
    }

}
