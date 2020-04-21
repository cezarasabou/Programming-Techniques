package dataaccess;
/**
 * this class is the one that proccesses the data from the data base in ordet to obtain the list of objects
 */

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class GeneralRepository<T> {
    private Connection connection;
    private List<Object> objectList = new ArrayList<Object>();
    private String tableName;
    private Class genericClass;
    protected static final Logger LOGGER = Logger.getLogger(GeneralRepository.class.getName());

    private final  Class<T> type;

    /**
     * this constructor will obtain the class type
     * @param myClass
     */
    public GeneralRepository(Class<T> myClass) {
        this.type = myClass;
    }

    /**
     * select query
     * @param field selects based on a field
     * @return returns the query in a string form
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * general select query
     * @return query in strng form
     */
    private String createSelect(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        System.out.println("Query is : " + sb.toString()+ "-----------------------");
        return sb.toString();
    }

    /**
     * generic list that iterates through the unkown table in the database, and adds the objects from the addObjects
     * method
     * @return a list of objects
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelect();
        List<T> objects = new ArrayList<T>();
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            objects = createObjects(resultSet);

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return objects;
    }

    /**
     * This methods returns an object from the database with the given id, and having generic type T
     *
     * @param id the id of the product
     * @return the product identified by the given id
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(type.getName().toLowerCase().substring(6)+"Id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        }
        return null;
    }

    /**
     * method that iterates through all elements and fields and createates an instance which is added to the object list
     * @param resultSet the set of values in the database table grouped in a set
     * @return a list of generic type
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();

        try {
            while (resultSet.next()) {
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * insert query
     * @param t generic object
     * @return string
     */
    private String createInsertQuery(T t){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ");
        sb.append(" INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");
        Field[] declaredFields = t.getClass().getDeclaredFields();
        for(int i = 1; i<declaredFields.length; i++){
            Field field = declaredFields[i];
            if(i == t.getClass().getDeclaredFields().length-1){
                field.setAccessible(true);
                sb.append(field.getName());
                break;
            }
            sb.append(field.getName()).append(", ");
        }
        sb.append(") ");
        sb.append("VALUES ");
        sb.append("(");

        for(int i = 1; i<t.getClass().getDeclaredFields().length; i++){
            Field field = declaredFields[i];
            try {
                if(i == t.getClass().getDeclaredFields().length-1){
                    field.setAccessible(true);
                    sb.append("'").append(field.get(t)).append("'");
                    break;
                }
                field.setAccessible(true);
                sb.append("'").append(field.get(t)).append("', ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.append(")");
        System.out.println(sb.toString());
        return sb.toString();

    }

    /**
     * inserts the element in the database based on the query obtained from createInsertQuery
     * @param t generic object
     * @return
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "insert" + e.getMessage());
        }
        return null;

    }

    /**
     * method that obtains the id of a generic object
     * @param t
     * @return id value
     */
    private Object getId(T t){
        Field[] declaredFields = t.getClass().getDeclaredFields();
        PropertyDescriptor propertyDescriptor = null;
        try {
            propertyDescriptor = new PropertyDescriptor(declaredFields[0].getName(),type);
            Method method = propertyDescriptor.getReadMethod();
            Object id = method.invoke(t).toString();
            return id;
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * creates a query that should update a table
     * @param t generic object
     * @return string
     */
    private String createUpdateQueryByField(T t){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(t.getClass().getSimpleName());
        sb.append(" SET ");
        Field[] declaredFields = t.getClass().getDeclaredFields();
        for(int i = 1; i<t.getClass().getDeclaredFields().length; i++){
            Field field = declaredFields[i];
            try {
                if(i == t.getClass().getDeclaredFields().length-1){
                    field.setAccessible(true);
                    sb.append(field.getName()).append("=");
                    sb.append("'").append(field.get(t)).append("'");
                    break;
                }
                field.setAccessible(true);
                sb.append(field.getName()).append("=");
                sb.append("'").append(field.get(t)).append("', ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.append(" WHERE ").append(declaredFields[0].getName()).append(" = " + getId(t));

        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * updates the element from the database using the query obtained from createUpdateByField
     * @param t
     * @return a generic object
     */
    public T updateById(T t) {

        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQueryByField(t);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "update" + e.getMessage());
        }
        return null;

    }

    /**
     * creates a delete query
     * @param t
     * @return string
     */
    public String createDeleteQuery(T t){
        Field[] declaredFields = t.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ").append("FROM ").append(t.getClass().getSimpleName()).append(" WHERE ").append(declaredFields[0].getName()).append(" = ").append(getId(t));
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * deletes element from table using the query obtained from createDeleteQuery
     * @param t
     */
    public void delete(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.execute();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "update" + e.getMessage());
        }
    }
}
