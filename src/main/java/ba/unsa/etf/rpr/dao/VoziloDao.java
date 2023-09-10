package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Vozilo;

import java.util.List;

public interface VoziloDao extends Dao<Vozilo> {
    List<Vozilo> searchByText(String text) throws Exception;
}
