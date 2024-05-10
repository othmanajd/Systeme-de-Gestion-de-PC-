package com.example.projetjavafx.Vendeur;

import com.example.projetjavafx.Model.Utilisateur;
import com.example.projetjavafx.fct.Fcts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class metreajoursinfoVendeurController implements Initializable {

    @FXML
    private TextField email;

    @FXML
    private HBox hbaccueil;

    @FXML
    private HBox hbnotification;

    @FXML
    private HBox hbnotification1;

    @FXML
    private HBox hbnous;

    @FXML
    private HBox hbprofil;

    @FXML
    private HBox hbrecherche;

    @FXML
    private HBox hbstatistique;

    @FXML
    private Button inscrirer;

    @FXML
    private AnchorPane login;

    @FXML
    private TextField nom;

    @FXML
    private Label notificationconteur;

    @FXML
    private TextField tele;

    @FXML
    private Label username;


    @FXML
    void chgtoaccueil(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/AccueilVendeur.fxml",hbprofil , "Accuiel Vendeur");
    }



    @FXML
    void chgtonotification(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/Notification.fxml",hbprofil , "Notification");

    }
    @FXML
    void chgtomtrinfoVnedeur(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/MetreajourinfoVendeur.fxml",hbprofil , "Notification");
    }

    @FXML
    void chgtonous(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/Nous.fxml",hbprofil , "À propos de nous");
    }


    @FXML
    void chgtorecherche(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/rechercheVendeur.fxml",hbprofil , "Recherche avancée");

    }

    @FXML
    void chgtostatistique(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/Statistique.fxml",hbprofil , "Statistique");

    }

    @FXML
    void deconnecter(ActionEvent event) {
        Fcts.deconiction(hbaccueil);
    }

    @FXML
    void metreajrinfoVendeur(ActionEvent event) {
        Fcts.updatInfoUser(nom.getText(),tele.getText(),email.getText());
        Fcts.change("/com/example/projetjavafx/Vendeur/ProfilVendeur.fxml",hbaccueil , "Profil vendeur");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Fcts.affiche(username,notificationconteur);
        Utilisateur u= Fcts.getUtilisateur();
        nom.setText(u.getNom());
        tele.setText(u.getTele());
        email.setText(u.getEmail());
    }

}

