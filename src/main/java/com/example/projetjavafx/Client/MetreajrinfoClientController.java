package com.example.projetjavafx.Client;

import com.example.projetjavafx.Model.Utilisateur;
import com.example.projetjavafx.fct.Fcts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MetreajrinfoClientController implements Initializable {

    @FXML
    private HBox deconnexion;

    @FXML
    private TextField email;

    @FXML
    private HBox hbaccueil;

    @FXML
    private HBox hbnotification;

    @FXML
    private Label notificationconteur;
    @FXML
    private Label username;

    @FXML
    private HBox hbnous;

    @FXML
    private HBox hbpanier;

    @FXML
    private HBox hbprofil;

    @FXML
    private HBox hbrecherche;

    @FXML
    private Button inscrirer;

    @FXML
    private AnchorPane login;

    @FXML
    private TextField nom;

    @FXML
    private TextField tele;

    @FXML
    void chgtoaccueil(ActionEvent event) {

        Fcts.change("/com/example/projetjavafx/Client/AccueilClient.fxml", hbprofil ,"Accueil Client");
    }
    @FXML
    void chgtonotification(ActionEvent event) {

        Fcts.change("/com/example/projetjavafx/Client/Notification.fxml",  hbprofil , "Notification");
    }

    @FXML
    void chgtomtreajrinfoVendeur(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Client/MetreajourinfoClient.fxml",  hbprofil , "Met à jour les informations");
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
    void deconnecter(ActionEvent event) {
        Fcts.deconiction(deconnexion);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Fcts.affiche(username,notificationconteur);
        Utilisateur u= Fcts.getUtilisateur();
        nom.setText(u.getNom());
        tele.setText(u.getTele());
        email.setText(u.getEmail());
    }

    @FXML
    void metreajrinfoClient(ActionEvent event) {
        Fcts.updatInfoUser(nom.getText(),tele.getText(),email.getText());
        Fcts.change("/com/example/projetjavafx/Client/Profil.fxml",hbaccueil ,"Profil Client");
    }

}
