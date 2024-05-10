package com.example.projetjavafx.Vendeur;

import com.example.projetjavafx.fct.Fcts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class NousController {

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
    private Label notificationconteur;

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
    void chgtorecherche(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/rechercheVendeur.fxml", hbprofil, "Recherche avancée");

    }

    @FXML
    void chgtostatistique(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/Statistique.fxml", hbprofil, "Statistique");

    }
    @FXML
    void chgtoprofil(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/ProfilVendeur.fxml",hbaccueil , "Profil vendeur");

    }


    @FXML
    void deconnecter(ActionEvent event) {
        Fcts.deconiction(hbaccueil);
    }

}
