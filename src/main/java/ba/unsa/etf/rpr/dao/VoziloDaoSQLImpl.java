package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Vozilo;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class VoziloDaoSQLImpl extends AbstractDao<Vozilo> implements VoziloDao {
    private static VoziloDaoSQLImpl instance=null;
    public VoziloDaoSQLImpl() {
        super("vozila");
    }
    public static VoziloDaoSQLImpl getInstance(){
        if(instance==null)
            instance=new VoziloDaoSQLImpl();
        return instance;
    }
    public static void removeInstance(){
        if(instance!=null)
            instance=null;
    }

    @Override
    public Vozilo row2object(ResultSet resultSet) throws Exception {
        try {
            Vozilo vozilo=new Vozilo();
            vozilo.setId(resultSet.getInt("id"));
            vozilo.setNaziv(resultSet.getString("naziv"));
            vozilo.setGorivo(resultSet.getString("gorivo"));
            vozilo.setMjenjac(resultSet.getString("mjenjac"));
            vozilo.setMaxbrputnika(resultSet.getInt("brojputnika"));
            vozilo.setCijenapodanu(resultSet.getInt("cijena_po_danu"));
            vozilo.setBrojregtablica(resultSet.getString("registracijske_tablice"));
            vozilo.setTip(resultSet.getString("tip"));
            return vozilo;
        }catch (Exception e){
            throw new Exception(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Vozilo object) {
        Map<String, Object> item=new TreeMap<>();
        item.put("id", object.getId());
        item.put("naziv", object.getNaziv());
        item.put("gorivo", object.getGorivo());
        item.put("mjenjac", object.getMjenjac());
        item.put("maxbrputnika", object.getMaxbrputnika());
        item.put("cijena po danu", object.getCijenapodanu());
        item.put("registracijske tablice", object.getBrojregtablica());
        item.put("tip", object.getTip());
        return item;
    }
    @Override
    public List<Vozilo> searchByText(String text) throws Exception{
        return executeQuery("SELECT * FROM vozilo WHERE naziv LIKE concat('%', ?, '%')", new Object[]{text});
    }
}