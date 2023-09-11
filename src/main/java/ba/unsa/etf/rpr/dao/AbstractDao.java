package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;

import java.sql.*;
import java.util.*;

public abstract class AbstractDao<T extends Idable> implements Dao<T> {

    private static Connection connection=null;
    private String tableName;

    public AbstractDao(String tableName){
        this.tableName=tableName;
        createConnection();
    }

    private static void createConnection() {
        if(AbstractDao.connection==null){
            try {
                AbstractDao.connection= DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_IDCardGeneratorDB?sessionVariables=WAIT_TIMEOUT=28800", "freedb_mkurtovic1","B#B$2QPH$!KDWqb");
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                Runtime.getRuntime().addShutdownHook(new Thread(){
                    @Override
                    public void run(){
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
    public static Connection getConnection(){
        return AbstractDao.connection;
    }

    public abstract T row2object(ResultSet resultSet) throws Exception;
    public abstract Map<String, Object> object2row(T object);

    public T getById(int id) throws Exception {
        return executeQueryUnique("SELECT * FROM "+this.tableName+" WHERE id=?", new Object[]{id});
    }

    public List<T> getAll() throws Exception {
        return executeQuery("SELECT*FROM "+tableName, null);
    }
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM "+tableName+" WHERE id=?";
        try {
            PreparedStatement statement=getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setObject(1, id);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new Exception(e.getMessage(), e);
        }
    }


    public T add(T item) throws Exception {
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            // bind params. IMPORTANT treeMap is used to keep columns sorted so params are bind correctly
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back */

            return item;
        }catch (SQLException e){
            throw new Exception(e.getMessage(), e);
        }
    }
    public T update(T item) throws  Exception {
        Map<String, Object> row = object2row(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE id = ?");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            throw new Exception(e.getMessage(), e);
        }
    }

    public T executeQueryUnique(String s, Object[] objects) throws Exception {
        List<T> result=executeQuery(s, objects);
        if(result!=null && result.size()==1) return result.get(0);
        else 
            throw new Exception("Object not found");
    }

    public List<T> executeQuery(String query, Object[] params) throws Exception{
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            if (params != null){
                for(int i = 1; i <= params.length; i++){
                    stmt.setObject(i, params[i-1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<T> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(row2object(rs));
            }
            return resultList;
        } catch (SQLException e) {
            throw new Exception(e.getMessage(), e);
        }
    }




    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip insertion of id due autoincrement
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<>(columns.toString(), questions.toString());
    }
    private String prepareUpdateParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip update of id due where clause
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != counter) {
                columns.append(",");
            }
        }
        return columns.toString();
    }

}
