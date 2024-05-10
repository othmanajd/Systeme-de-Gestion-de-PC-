package com.example.projetjavafx.Vendeur;

import com.example.projetjavafx.fct.ConnectionDb;
import com.example.projetjavafx.fct.Info;
import com.example.projetjavafx.fct.Fcts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AjouterPcController implements Initializable {
    @FXML
    private TextField ecran;

    @FXML
    private TextField graphique;

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
    private TextField marque;

    @FXML
    private TextField namePC;

    @FXML
    private Label notificationconteur;

    @FXML
    private TextField prix;

    @FXML
    private TextField puissance;

    @FXML
    private TextField ram;

    @FXML
    private TextField stockage;

    @FXML
    private TextField url;

    @FXML
    private Label username;


    @FXML
    void ajouter(ActionEvent event) {
        try {
            Connection conn = ConnectionDb.getConnection();
            String insertSql = "INSERT INTO pc (idVendeur, puissance, nom, ecran, stockage, ram, carteGraphique, marque, prix,dateAjouter, url,status ) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = conn.prepareStatement(insertSql);
            insertStatement.setInt(1, Info.idutilisateur); // Remplacez idVendeur par l'ID du vendeur du PC
            insertStatement.setString(2, puissance.getText()); // Puissance du PC
            insertStatement.setString(3, namePC.getText()); // Nom du PC
            insertStatement.setString(4, ecran.getText()); // Taille de l'écran du PC
            insertStatement.setString(5, stockage.getText()); // Capacité de stockage du PC
            insertStatement.setString(6, ram.getText()); // Quantité de RAM du PC
            insertStatement.setString(7, graphique.getText()); // Carte graphique du PC
            insertStatement.setString(8, marque.getText()); // Marque du PC
            insertStatement.setString(9, prix.getText()); // Prix du PC
            insertStatement.setString(10, String.valueOf(LocalDate.now())); // Date d'ajout du PC
            insertStatement.setString(11, url.getText()); // URL de l'image du PC
            insertStatement.setString(12,"Disponible");
            insertStatement.executeUpdate();
            insertStatement.close();
            conn.close();
            Fcts.change("/com/example/projetjavafx/Vendeur/AccueilVendeur.fxml",hbprofil , "Accuiel Vendeur");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Fcts.affiche(username,notificationconteur);
    }

    @FXML
    void chgtoaccueil(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/AccueilVendeur.fxml",hbprofil , "Accuiel Vendeur");

    }

    @FXML
    void chgtonotification(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/Notification.fxml",hbaccueil , "Notification");

    }

    @FXML
    void chgtonous(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/Nous.fxml",hbaccueil , "À propos de nous");
    }

    @FXML
    void chgtoprofil(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/ProfilVendeur.fxml",hbaccueil , "Profil vendeur");
    }

    @FXML
    void chgtorecherche(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/rechercheVendeur.fxml",hbaccueil , "Recherche avancée");

    }

    @FXML
    void chgtostatistique(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/Statistique.fxml",hbaccueil , "Statistique");

    }

    @FXML
    void deconnecter(ActionEvent event) {
        Fcts.deconiction(hbaccueil);
    }


}
