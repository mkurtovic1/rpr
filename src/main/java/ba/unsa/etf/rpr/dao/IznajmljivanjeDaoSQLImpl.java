package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Iznajmljivanje;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class IznajmljivanjeDaoSQLImpl extends AbstractDao<Iznajmljivanje> implements IznajmljivanjeDao {
    private static IznajmljivanjeDaoSQLImpl instance=null;
    public IznajmljivanjeDaoSQLImpl() {
        super("dbiznajmljivanje");
    }
    public static IznajmljivanjeDaoSQLImpl getInstance(){
        if(instance==null) instance=new IznajmljivanjeDaoSQLImpl();
        return instance;
    }
    public static void removeInstance(){
        if (instance!=null) instance=null;
    }

    @Override
    public Map<String, Object> object2row(Iznajmljivanje object) {
        Map<String, Object> row=new TreeMap<>();
        row.put("id", object.getId());
        row.put("idkorisnik", object.getKlijentId());
        row.put("idvozilo", object.getVoziloId());
        row.put("cijena", object.getCijena());
        row.put("preuzimanje", object.getPreuzimanje());
        row.put("vracanje", object.getVracanje());
        return row;
    }

    @Override
    public Iznajmljivanje row2object(ResultSet resultSet) throws Exception {
        try {
            Iznajmljivanje iznajmljivanje=new Iznajmljivanje();
            iznajmljivanje.setKlijentId(resultSet.getInt("idkorisnik"));
            iznajmljivanje.setId(resultSet.getInt("id"));
            iznajmljivanje.setVoziloId(resultSet.getInt("idvozilo"));
            iznajmljivanje.setCijena(resultSet.getInt("cijena"));
            iznajmljivanje.setPreuzimanje(LocalDate.parse(resultSet.getString("preuzimanje")));
            iznajmljivanje.setVracanje(LocalDate.parse(resultSet.getString("vracanje")));
            return iznajmljivanje;

        } catch (SQLException e){
            throw new Exception(e.getMessage(), e);
        }
    }
}
