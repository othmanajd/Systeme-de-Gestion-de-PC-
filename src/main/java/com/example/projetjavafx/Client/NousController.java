package com.example.projetjavafx.Client;

import com.example.projetjavafx.fct.Fcts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class NousController implements Initializable {

    @FXML
    private HBox deconnexion;

    @FXML
    private HBox hbaccueil;
    @FXML
    private Label notificationconteur;
    @FXML
    private Label username;

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
    void chgtoaccueil(ActionEvent event) {

        Fcts.change("/com/example/projetjavafx/Client/AccueilClient.fxml", hbnous ,"Accueil Client");
    }
    @FXML
    void chgtonotification(ActionEvent event) {

        Fcts.change("/com/example/projetjavafx/Client/Notification.fxml",  hbnous , "Notification");
    }




    @FXML
    void chgtoprofil(ActionEvent event) {

        Fcts.change("/com/example/projetjavafx/Client/Profil.fxml",  hbnous , "Profil Client");
    }

    @FXML
    void chgtopanier(ActionEvent event) {

        Fcts.change("/com/example/projetjavafx/Client/Panier.fxml", hbnous , "Panier d'achat");
    }

    @FXML
    void chgtorecherche(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Client/Recherche.fxml",  hbnous , "Recherche avancée");
    }

    @FXML
    void deconnecter(ActionEvent event) {
        Fcts.deconiction(deconnexion);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Fcts.affiche(username,notificationconteur);
    }
}
