package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.VoziloDao;
import ba.unsa.etf.rpr.domain.Vozilo;

import java.util.List;

public class VoziloManager {
    public List<Vozilo> getAll() throws Exception{
        return DaoFactory.voziloDao().getAll();
    }
    public void delete(int id) throws Exception{
        DaoFactory.voziloDao().delete(id);
    }
    public Vozilo getById(int id) throws Exception{
        return DaoFactory.voziloDao().getById(id);
    }
    public void update(Vozilo vozilo) throws Exception{
        DaoFactory.voziloDao().update(vozilo);
    }
    public Vozilo add(Vozilo vozilo) throws Exception{
        return DaoFactory.voziloDao().add(vozilo);
    }
}
