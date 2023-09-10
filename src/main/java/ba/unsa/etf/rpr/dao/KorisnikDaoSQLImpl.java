package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Korisnik;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class KorisnikDaoSQLImpl extends AbstractDao<Korisnik> implements KorisnikDao {
    private static KorisnikDaoSQLImpl instance=null;
    private KorisnikDaoSQLImpl(){
        super("korisnici");
    }
    public static KorisnikDaoSQLImpl getInstance(){
        if(instance==null){
            instance=new KorisnikDaoSQLImpl();
        }
        return instance;
    }
    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }
    @Override
    public Korisnik row2object(ResultSet resultSet) throws Exception {
        try {
            Korisnik korisnik=new Korisnik();
            korisnik.setId(resultSet.getInt("id"));
            korisnik.setIme(resultSet.getString("ime"));
            return korisnik;
        } catch (SQLException e){
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Korisnik object) {
        Map<String, Object> row=new TreeMap<>();
        row.put("id", object.getId());
        row.put("ime", object.getIme());
        return row;
    }
}
