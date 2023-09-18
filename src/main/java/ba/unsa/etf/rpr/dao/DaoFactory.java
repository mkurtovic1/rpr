package ba.unsa.etf.rpr.dao;

public class DaoFactory {
    private static  final KorisnikDao korisnikDao=KorisnikDaoSQLImpl.getInstance();
    private static  final VoziloDao voziloDao=VoziloDaoSQLImpl.getInstance();
    private static final IznajmljivanjeDao iznajmljivanjeDao= IznajmljivanjeDaoSQLImpl.getInstance();
    private DaoFactory(){}

    public static KorisnikDao korisnikDao(){
        return korisnikDao;
    }
    public static VoziloDao voziloDao(){
        return voziloDao;
    }
    public static IznajmljivanjeDao iznajmljivanjeDao(){
        return iznajmljivanjeDao;
    }
}
