package dataaccess;

import java.sql.*;
import java.util.logging.Logger;

public class ConnectionFactory {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/warehousedb";
    private static final String USER = "root";
    private static final String PASS = "cezi1998";

    private static ConnectionFactory connectionFactory = new ConnectionFactory();
    private static Connection databaseConnection;

    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.info("Could not load the driver");
            e.printStackTrace();
        }
    }
    private static void createConnection() {
        try {
            databaseConnection =  DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.info("Could not create database connection");
            e.printStackTrace();
        }
    }

    public static Connection getConnection()
    {
        if(databaseConnection == null) {
            createConnection();
        }

        return databaseConnection;
    }

    public static void close(ResultSet resultSet) {

    }
    public static void closePreparedStatement(PreparedStatement preparedStatement){
        try{
            preparedStatement.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
