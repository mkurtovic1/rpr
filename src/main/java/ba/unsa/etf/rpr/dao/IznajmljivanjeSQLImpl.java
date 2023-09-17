package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Iznajmljivanje;
import ba.unsa.etf.rpr.domain.Vozilo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

public class   IznajmljivanjeSQLImpl extends AbstractDao<Iznajmljivanje> implements IznajmljivanjeDao {
    private static IznajmljivanjeSQLImpl instance=null;
    public IznajmljivanjeSQLImpl() {
        super("dbiznajmljivanje");
    }
    public static IznajmljivanjeSQLImpl getInstance(){
        if(instance==null) instance=new IznajmljivanjeSQLImpl();
        return instance;
    }
    public static void removeInstance(){
        if (instance!=null) instance=null;
    }




    @Override
    public Map<String, Object> object2row(Iznajmljivanje object) {
        return null;
    }



    @Override
    public Iznajmljivanje add(Iznajmljivanje item) throws Exception {
        return null;
    }

    @Override
    public Iznajmljivanje update(Iznajmljivanje item) throws Exception {
        return null;
    }

    @Override
    public Iznajmljivanje row2object(ResultSet resultSet) throws Exception {
        return null;
    }
}
