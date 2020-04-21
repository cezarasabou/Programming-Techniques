package application;

import dataaccess.ConnectionFactory;
import presentation.GUI;

import java.sql.Connection;

public class MainApplication {

    public static void main(String[] args) {
        Connection databaseConnection = ConnectionFactory.getConnection();
        GUI gui = new GUI();
    }
}
