package ba.unsa.etf.rpr.bussines;

import ba.unsa.etf.rpr.business.VoziloManager;
import ba.unsa.etf.rpr.dao.VoziloDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Vozilo;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class VoziloManagerTest {
    private VoziloManager voziloManager;
    private Vozilo vozilo;
    private VoziloDaoSQLImpl voziloDaoSQL;
    private List<Vozilo> voziloList;

    @Test
    public void testAddVozilo() throws Exception{
        Vozilo voziloToAdd=new Vozilo();
        when(voziloDaoSQL.add(voziloToAdd)).thenReturn(voziloToAdd);
        Vozilo addedVozilo=voziloManager.add(voziloToAdd);
        assertNotNull(addedVozilo);
        assertEquals(voziloToAdd, addedVozilo);
        verify(voziloDaoSQL, times(1)).add(voziloToAdd);
    }
}
