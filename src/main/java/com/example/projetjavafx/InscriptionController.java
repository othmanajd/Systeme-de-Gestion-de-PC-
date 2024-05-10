package com.example.projetjavafx;

import com.example.projetjavafx.fct.ConnectionDb;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class InscriptionController  implements Initializable {

    @FXML
    private TextField ConfirmeShowPwd;

    @FXML
    private PasswordField Confirmepwd;

    @FXML
    private TextField email;

    @FXML
    private Label erreur;

    @FXML
    private Button inscrirer;

    @FXML
    private Hyperlink link;

    @FXML
    private AnchorPane login;

    @FXML
    private TextField nom;

    @FXML
    private PasswordField pwd;

    @FXML
    private AnchorPane register;

    @FXML

    private ChoiceBox<String> role;
    private String[] roles={"Client","Vendeur"};
    @FXML
    private CheckBox show;

    @FXML
    private TextField showPwd;

    @FXML
    private TextField tele;



    @FXML
    void AllerPageLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            StackPane loginPage = loader.load();

            // Obtenir la scène actuelle à partir du bouton "clique ici"
            Scene currentScene = link.getScene();

            // Fermer la fenêtre principale associée à la scène actuelle
            Stage primaryStage = (Stage) currentScene.getWindow();
            primaryStage.close();

            // Créer une nouvelle fenêtre principale avec la nouvelle scène
            Stage newStage = new Stage();
            Scene newScene = new Scene(loginPage, 330, 400);
            newStage.setTitle("Connexion");
            newStage.setScene(newScene);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void AffichePwd(ActionEvent event) {
        if(show.isSelected()){
            showPwd.setText(pwd.getText());
            pwd.setVisible(false);
            showPwd.setVisible(true);

            ConfirmeShowPwd.setText(Confirmepwd.getText());
            Confirmepwd.setVisible(false);
            ConfirmeShowPwd.setVisible(true);
        }else{
            pwd.setText(showPwd.getText());
            pwd.setVisible(true);
            showPwd.setVisible(false);

            Confirmepwd.setText(ConfirmeShowPwd.getText());
            Confirmepwd.setVisible(true);
            ConfirmeShowPwd.setVisible(false);
        }

    }

    @FXML
    void Inscrire(ActionEvent event) {
        if (show.isSelected()) {
            pwd.setText(showPwd.getText());
            Confirmepwd.setText(ConfirmeShowPwd.getText());
        }
        if (email.getText().isEmpty() || role.getValue().isEmpty() || pwd.getText().isEmpty() || Confirmepwd.getText().isEmpty() || tele.getText().isEmpty()) {
            erreur.setText("Veuillez remplir tous les champs !");
            erreur.setVisible(true);
        } else if (!Confirmepwd.getText().equals(pwd.getText()))  {
            erreur.setText("Le mot de passe et sa confirmation ne correspondent pas !");
            erreur.setVisible(true);
        } else {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            String sql = "INSERT INTO utilisateur (nom, email, pwd , tele , role , notification ) VALUES (?, ?, ? , ? , ? ,?)";

            try {
                connection = ConnectionDb.getConnection();
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, nom.getText());
                preparedStatement.setString(2, email.getText());
                preparedStatement.setString(3, pwd.getText());
                preparedStatement.setString(4, tele.getText());
                preparedStatement.setString(5, role.getValue());
                preparedStatement.setInt(6,0);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                    StackPane loginPage = loader.load();
                    Scene currentScene = inscrirer.getScene();
                    Stage primaryStage = (Stage) currentScene.getWindow();
                    primaryStage.close();
                    Stage newStage = new Stage();
                    Scene newScene = new Scene(loginPage, 330, 400);
                    newStage.setTitle("Connexion");
                    newStage.setScene(newScene);
                    newStage.show();
                    System.out.println("Utilisateur ajouté avec succès !");
                } else {
                    System.out.println("Erreur lors de l'ajout de l'utilisateur.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void initialize() {
        // Définir l'effet hover sur le bouton connecter
        inscrirer.setOnMouseEntered(e -> inscrirer.setStyle(
                "-fx-background-color: #fff; " +
                        "-fx-text-fill:linear-gradient(to bottom right , #188ba7,#306090);"+
                        "-fx-border-radius:5px;"
        ));
        inscrirer.setOnMouseExited(e -> inscrirer.setStyle(
                "-fx-background-color: linear-gradient(to bottom right , #188ba7,#306090); " + // Remettre la couleur initiale du bouton
                        "-fx-text-fill: #fff;" // Remettre la couleur initiale du texte
        ));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        role.getItems().addAll(roles);

    }
}

