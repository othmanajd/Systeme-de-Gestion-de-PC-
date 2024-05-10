package com.example.projetjavafx.Client;

import com.example.projetjavafx.fct.ConnectionDb;
import com.example.projetjavafx.fct.Info;
import com.example.projetjavafx.fct.Fcts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PlusInfoController implements Initializable {

    @FXML
    private Button btnAcheter;

    @FXML
    private Button btnlike;

    @FXML
    private HBox deconnexion;

    @FXML
    private Label ecran;

    @FXML
    private Label graphique;

    @FXML
    private HBox hbaccueil;

    @FXML
    private HBox hbnotification;

    @FXML
    private HBox hbnous;

    @FXML
    private HBox hbpanier;

    @FXML
    private HBox hbprofil;

    @FXML
    private HBox hbrecherche;

    @FXML
    private Label idpc;

    @FXML
    private ImageView imgPc;

    @FXML
    private Label notificationconteur;
    @FXML
    private Label username;

    @FXML
    private Label marque;

    @FXML
    private Label nbrlikr;

    @FXML
    private Label nom;

    @FXML
    private Label prix;

    @FXML
    private Label puissance;

    @FXML
    private Label stockage;

    @FXML
    void acheter(ActionEvent event) {
        int nbrNotification=0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sqll= "SELECT idVendeur FROM pc WHERE id = ?";
        String sql = "UPDATE pc SET status = ?, dateModification = ?, idClient = ? WHERE id = ?";

        try {
            connection = ConnectionDb.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"En attente" );
            preparedStatement.setInt(3, Info.idutilisateur);
            preparedStatement.setString(2, String.valueOf(LocalDate.now()));
            preparedStatement.setInt(4,Info.idPc);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                PreparedStatement stat = connection.prepareStatement(sqll);
                stat.setInt(1, Info.idPc);
                ResultSet resultSet = stat.executeQuery();
                if (resultSet.next()) {
                    int idVendeur = resultSet.getInt("idVendeur");
                    String sq = "SELECT notification FROM utilisateur WHERE id= ?";
                    PreparedStatement st = connection.prepareStatement(sq);
                    st.setInt(1, idVendeur);
                    ResultSet result = st.executeQuery();
                    if (result.next()) {
                        nbrNotification = result.getInt("notification");
                        nbrNotification++;
                        String updateSql = "UPDATE utilisateur SET notification = ? WHERE id = ?";
                        PreparedStatement updateStatement = connection.prepareStatement(updateSql);
                        updateStatement.setInt(1,nbrNotification );
                        updateStatement.setInt(2, idVendeur);
                        updateStatement.executeUpdate();
                    }

                }
                Fcts.change("/com/example/projetjavafx/Client/AccueilClient.fxml",btnAcheter,"Accueil Client");
            } else {
                System.out.println("Erreur.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void chgtoaccueil(ActionEvent event) {

        Fcts.change("/com/example/projetjavafx/Client/AccueilClient.fxml", hbprofil ,"Accueil Client");
    }
    @FXML
    void chgtonotification(ActionEvent event) {

        Fcts.change("/com/example/projetjavafx/Client/Notification.fxml",  hbprofil , "Notification");
    }


    @FXML
    void chgtonous(ActionEvent event) {

        Fcts.change("/com/example/projetjavafx/Client/Nous.fxml",  hbprofil , "À propos de nous");
    }

    @FXML
    void chgtopanier(ActionEvent event) {

        Fcts.change("/com/example/projetjavafx/Client/Panier.fxml", hbprofil , "Panier d'achat");
    }

    @FXML
    void chgtorecherche(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Client/Recherche.fxml",  hbprofil , "Recherche avancée");
    }
    @FXML
    void chgtoprofil(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Client/Profil.fxml", hbrecherche ,"Profil Client");

    }
    @FXML
    void deconnecter(ActionEvent event) {
        Fcts.deconiction(deconnexion);
    }

    @FXML
    void like(ActionEvent event) {
        Fcts.like(Integer.parseInt(idpc.getText()),Info.idutilisateur);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Fcts.affiche(username,notificationconteur);
    }
}
