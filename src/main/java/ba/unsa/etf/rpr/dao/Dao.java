package ba.unsa.etf.rpr.dao;
import java .util.List;
/**
 * Root interface for all DAO classes
 *
 * @author MKurtovic
 */
public interface Dao<T> {
    T getById(int id) throws Exception;
    T add(T item) throws Exception;
    T update(T item) throws Exception;
    void delete(int id) throws Exception;
    List<T> getAll() throws Exception;

}
