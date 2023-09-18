package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Korisnik;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class KorisnikDaoSQLImpl extends AbstractDao<Korisnik> implements KorisnikDao {
    private static KorisnikDaoSQLImpl instance=null;
    private KorisnikDaoSQLImpl(){
        super("dbkorisnik");
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
            korisnik.setPrezime(resultSet.getString("prezime"));
            korisnik.setLozinka(resultSet.getString("lozinka"));
            korisnik.setEmail(resultSet.getString("email"));

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
        row.put("prezime", object.getPrezime());
        row.put("email", object.getEmail());
        row.put("lozinka", object.getLozinka());
        return row;
    }
    public Korisnik getByEmail(String email) throws Exception{
        try {
            String query= "SELECT * FROM dbkorisnik WHERE email = ?";
            PreparedStatement preparedStatement= getConnection().prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                Korisnik korisnik=row2object(resultSet);
                korisnik.setLozinka(resultSet.getString("lozinka"));
                return korisnik;
            }
            return null; //nema usera s poslanom emailom
        }catch (SQLException e){
            throw new Exception("Error while fetching user by email: "+ e.getMessage(), e);
        }
    }
}
