package com.example.projetjavafx.fct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {
    // Informations de connexion à la base de données
    private static final String URL = "jdbc:mysql://localhost:3306/monApp";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";


    // Méthode pour établir la connexion à la base de données
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Méthode pour fermer la connexion à la base de données
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

