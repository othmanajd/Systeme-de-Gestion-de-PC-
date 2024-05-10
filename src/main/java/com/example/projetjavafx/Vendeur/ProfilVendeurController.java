package com.example.projetjavafx.Vendeur;

import com.example.projetjavafx.Model.Utilisateur;
import com.example.projetjavafx.fct.Fcts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfilVendeurController implements Initializable {
    @FXML
    private Button btn;

    @FXML
    private Label emailp;

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
    private Label namep;

    @FXML
    private Label notificationconteur;

    @FXML
    private Label rolep;

    @FXML
    private Label telep;

    @FXML
    private Label username;

    @FXML
    void chgtoaccueil(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/AccueilVendeur.fxml", hbprofil, "Accuiel Vendeur");
    }


    @FXML
    void chgtonotification(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/Notification.fxml", hbprofil, "Notification");

    }

    @FXML
    void chgtomtrinfoVnedeur(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/MetreajourinfoVendeur.fxml", hbprofil, "Met à jour les informations");
    }

    @FXML
    void chgtonous(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/Nous.fxml", hbprofil, "À propos de nous");
    }


    @FXML
    void chgtorecherche(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/rechercheVendeur.fxml", hbprofil, "Recherche avancée");

    }

    @FXML
    void chgtostatistique(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/Statistique.fxml", hbprofil, "Statistique");

    }

    @FXML
    void deconnecter(ActionEvent event) {
        Fcts.deconiction(hbaccueil);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Fcts.affiche(username, notificationconteur);
        Utilisateur u= Fcts.getUtilisateur();
        namep.setText(u.getNom());
        telep.setText(u.getTele());
        emailp.setText(u.getEmail());
        rolep.setText(u.getRole());

    }

}

