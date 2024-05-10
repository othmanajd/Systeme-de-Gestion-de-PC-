package com.example.projetjavafx.fct;

import com.example.projetjavafx.Model.Pc;
import com.example.projetjavafx.Model.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Fcts {
    public static void change(String url, HBox hb , String titre){
        FXMLLoader loader = new FXMLLoader(Fcts.class.getResource(url));
        BorderPane loginPage = null;
        try {
            loginPage = loader.load();
            Scene currentScene = hb.getScene();
            Stage primaryStage = (Stage) currentScene.getWindow();
            primaryStage.close();
            Stage newStage = new Stage();
            Scene newScene = new Scene(loginPage,1280 , 650);
            newStage.setTitle(titre);
            newStage.setScene(newScene);
            newStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void change(String url, Button hb , String titre){
        FXMLLoader loader = new FXMLLoader(Fcts.class.getResource(url));
        BorderPane loginPage = null;
        try {
            loginPage = loader.load();
            Scene currentScene = hb.getScene();
            Stage primaryStage = (Stage) currentScene.getWindow();
            primaryStage.close();
            Stage newStage = new Stage();
            Scene newScene = new Scene(loginPage,1280 , 650);
            newStage.setTitle(titre);
            newStage.setScene(newScene);
            newStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Utilisateur getUtilisateur(){
        Utilisateur P = null;
        try {
            Connection conn = ConnectionDb.getConnection();
            String sql = "SELECT * FROM utilisateur WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, Info.idutilisateur);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String email = resultSet.getString("email");
                String tele = resultSet.getString("tele");
                String role = resultSet.getString("role");
                P = new Utilisateur(id, nom, email, tele, role);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return P;
    }


    public  static void like(int idpc,int idclient){
        Connection conn = null;
        try {
            conn = ConnectionDb.getConnection();
            String selectLikeSql = "SELECT id FROM likee WHERE idpc = ? AND idclient = ?";
            PreparedStatement selectLikeStatement = conn.prepareStatement(selectLikeSql);
            selectLikeStatement.setInt(1, idpc);
            selectLikeStatement.setInt(2, idclient);
            ResultSet likeResultSet = selectLikeStatement.executeQuery();


            // S'il y a déjà un enregistrement dans la table 'like' pour cet utilisateur et cet élément
            if (likeResultSet.next()) {
                int idlike=likeResultSet.getInt("id");
                String deleteLikeSql = "DELETE FROM likee WHERE id = ?";
                PreparedStatement deleteLikeStatement = conn.prepareStatement(deleteLikeSql);
                deleteLikeStatement.setInt(1, likeResultSet.getInt(idlike));
                deleteLikeStatement.executeUpdate();

            } else {
                String insertLikeSql = "INSERT INTO likee (idpc, idclient) VALUES (?, ?)";
                PreparedStatement insertLikeStatement = conn.prepareStatement(insertLikeSql);
                insertLikeStatement.setInt(1, idpc);
                insertLikeStatement.setInt(2, idclient);
                insertLikeStatement.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updatInfoUser(String n,String t ,String e){
        try {
            Connection conn = ConnectionDb.getConnection();
            String sql = "UPDATE utilisateur SET nom = ?, tele = ?, email = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, n);
            statement.setString(2, t);
            statement.setString(3, e);
            statement.setInt(4, Info.idutilisateur);
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (Exception ec ) {
            ec.printStackTrace();
        }
    }
    public static void deconiction(HBox hb ){
        FXMLLoader loader = new FXMLLoader(Fcts.class.getResource("/com/example/projetjavafx/Login.fxml"));
        StackPane loginPage = null;
        try {
            loginPage = loader.load();
            Scene currentScene = hb.getScene();
            Stage primaryStage = (Stage) currentScene.getWindow();
            primaryStage.close();
            Stage newStage = new Stage();
            Scene newScene = new Scene(loginPage,330, 400);
            newStage.setTitle("Connexion");
            newStage.setScene(newScene);
            newStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void affiche(Label nam ,Label noti){
        nam.setText(Info.name);
        String sql = "SELECT notification FROM utilisateur WHERE id="+Info.idutilisateur;
        try {
            Connection connection = ConnectionDb.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getInt("notification")>0){
                    noti.setText(String.valueOf(resultSet.getInt("notification")));
                    noti.setVisible(true);
                }
                else {
                    noti.setVisible(false);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public static int getNombreLikes(int idpc){
        Connection conn = null;
        int nombreLikes = 0;
        try {
            conn = ConnectionDb.getConnection();
            String countLikesSql = "SELECT COUNT(*) AS total_likes FROM likee WHERE idpc = ?";
            PreparedStatement countLikesStatement = conn.prepareStatement(countLikesSql);
            countLikesStatement.setInt(1, idpc);
            ResultSet countLikesResultSet = countLikesStatement.executeQuery();
            if (countLikesResultSet.next()) {
                nombreLikes = countLikesResultSet.getInt("total_likes");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nombreLikes;
    }


}
