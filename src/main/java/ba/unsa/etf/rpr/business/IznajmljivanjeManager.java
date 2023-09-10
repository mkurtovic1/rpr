package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Iznajmljivanje;


import java.util.List;

public class IznajmljivanjeManager {
    public List<Iznajmljivanje> getAll() throws Exception{
        return DaoFactory.iznajmljivanjeDao().getAll();
    }
    public void delete(int id) throws Exception{
        DaoFactory.iznajmljivanjeDao().delete(id);
    }
    public Iznajmljivanje getById(int id) throws Exception{
        return DaoFactory.iznajmljivanjeDao().getById(id);
    }
    public void update(Iznajmljivanje iznajmljivanje) throws Exception{
        DaoFactory.iznajmljivanjeDao().update(iznajmljivanje);
    }
    public Iznajmljivanje add(Iznajmljivanje iznajmljivanje) throws Exception{
        return DaoFactory.iznajmljivanjeDao().add(iznajmljivanje);
    }
}
