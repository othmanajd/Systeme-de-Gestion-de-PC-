package com.example.projetjavafx;

import com.example.projetjavafx.fct.ConnectionDb;
import com.example.projetjavafx.fct.Info;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




public class LoginController {

    @FXML
    private Button connecter;

    @FXML
    private TextField email;

    @FXML
    private Label erreur;

    @FXML
    private Hyperlink link;

    @FXML
    private AnchorPane login;

    @FXML
    private PasswordField pwd;

    @FXML
    private CheckBox show;

    @FXML
    private TextField showpwd;



    @FXML
    void AllerPageInscription(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Inscription.fxml"));
            StackPane loginPage = loader.load();

            // Obtenir la scène actuelle à partir du bouton "clique ici"
            Scene currentScene = link.getScene();

            // Fermer la fenêtre principale associée à la scène actuelle
            Stage primaryStage = (Stage) currentScene.getWindow();
            primaryStage.close();

            // Créer une nouvelle fenêtre principale avec la nouvelle scène
            Stage newStage = new Stage();
            Scene newScene = new Scene(loginPage, 330, 620);
            newStage.setTitle("Inscription");
            newStage.setScene(newScene);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    @FXML
    void AffichePwd(ActionEvent event) {
        if(show.isSelected()){
            showpwd.setText(pwd.getText());
            pwd.setVisible(false);
            showpwd.setVisible(true);
        }else{
            pwd.setText(showpwd.getText());
            pwd.setVisible(true);
            showpwd.setVisible(false);
        }
    }
    @FXML
    void Connecter(ActionEvent event) {
        if(show.isSelected()){
            pwd.setText(showpwd.getText());
        }
        if(pwd.getText().isEmpty() || email.getText().isEmpty()){
            erreur.setText("Veuillez remplir tous les champs !");
            erreur.setVisible(true);
        }
        else{
            String sql = "SELECT * FROM utilisateur WHERE email = ? AND pwd = ?";
            try  {
                Connection connection= ConnectionDb.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                // Paramétrage des valeurs des paramètres dans la requête
                preparedStatement.setString(1, email.getText());
                preparedStatement.setString(2,pwd.getText());

                // Exécution de la requête et récupération du résultat
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Vérification s'il y a un résultat
                    if (resultSet.next()) {
                        Info.idutilisateur=resultSet.getInt("id");
                        Info.name=resultSet.getString("nom");
                        if(resultSet.getString("role").equals("Client")){
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Client/AccueilClient.fxml"));
                            BorderPane loginPage = loader.load();
                            Scene currentScene = connecter.getScene();
                            Stage primaryStage = (Stage) currentScene.getWindow();
                            primaryStage.close();
                            Stage newStage = new Stage();
                            Scene newScene = new Scene(loginPage, 1280, 650);
                            newStage.setTitle("Accueil Client");
                            newStage.setScene(newScene);
                            newStage.show();
                        }
                        else{
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Vendeur/AccueilVendeur.fxml"));
                            BorderPane loginPage = loader.load();
                            Scene currentScene = connecter.getScene();
                            Stage primaryStage = (Stage) currentScene.getWindow();
                            primaryStage.close();
                            Stage newStage = new Stage();
                            Scene newScene = new Scene(loginPage, 1280, 650);
                            newStage.setTitle("Accueil Vendeurs");
                            newStage.setScene(newScene);
                            newStage.show();

                        }
                        System.out.println("Utilisateur trouvé avec l'id : ");
                    } else {
                        erreur.setText("Email ou mot de passe incorrect !");
                        erreur.setVisible(true);
                    }
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    @FXML
    public void initialize() {
        // Définir l'effet hover sur le bouton connecter
        connecter.setOnMouseEntered(e -> connecter.setStyle(
                "-fx-background-color: #fff; " +
                        "-fx-text-fill:linear-gradient(to bottom right , #188ba7,#306090);"+
                        "-fx-border-radius:5px;"
        ));
        connecter.setOnMouseExited(e -> connecter.setStyle(
                "-fx-background-color: linear-gradient(to bottom right , #188ba7,#306090); " + // Remettre la couleur initiale du bouton
                        "-fx-text-fill: #fff;" // Remettre la couleur initiale du texte
        ));

    }


    }
