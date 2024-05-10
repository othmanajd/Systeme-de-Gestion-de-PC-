package com.example.projetjavafx.fct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBase {
    // Constructeur de la classe
    public DataBase() {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            // Établissement de la connexion à la base de données
            connexion = ConnectionDb.getConnection();

            // Requête SQL pour créer la table utilisateur
            String createTableUtilisateurSQL = "CREATE TABLE IF NOT EXISTS utilisateur ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "nom VARCHAR(255) NOT NULL,"
                    + "email VARCHAR(255) NOT NULL,"
                    + "tele VARCHAR(20),"
                    +"role VARCHAR(20),"
                    +"pwd VARCHAR(20),"
                    + "notification INT)";

            // Exécution de la requête pour créer la table utilisateur
            preparedStatement = connexion.prepareStatement(createTableUtilisateurSQL);
            preparedStatement.execute();

            // Requête SQL pour créer la table Pc
            String createTablePcSQL = "CREATE TABLE IF NOT EXISTS pc ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "idVendeur INT,"
                    + "idClient INT,"
                    + "puissance VARCHAR(255),"
                    + "url VARCHAR(255),"
                    + "nom VARCHAR(255),"
                    + "ecran VARCHAR(255),"
                    + "stockage VARCHAR(255),"
                    + "ram VARCHAR(255),"
                    + "carteGraphique VARCHAR(255),"
                    + "marque VARCHAR(255),"
                    + "prix Double,"
                    + "status VARCHAR(255),"
                    + "dateModification DATE ,"
                    + "dateajouter DATE)";

            preparedStatement = connexion.prepareStatement(createTablePcSQL);
            preparedStatement.execute();

            String createTableLikeSQL = "CREATE TABLE IF NOT EXISTS likee ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "idpc INT,"
                    + "idclient INT)";

            // Exécution de la requête pour créer la table like
            preparedStatement = connexion.prepareStatement(createTableLikeSQL);
            preparedStatement.execute();
            


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new DataBase();
    }
}
