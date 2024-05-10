package com.example.projetjavafx.Vendeur;

import com.example.projetjavafx.Model.Pc;
import com.example.projetjavafx.Model.Utilisateur;
import com.example.projetjavafx.fct.ConnectionDb;
import com.example.projetjavafx.fct.Fcts;
import com.example.projetjavafx.fct.Info;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AccueilVendeurController implements Initializable {


    @FXML
    private TableColumn<Pc, Integer> cledit;

    @FXML
    private TableColumn<Pc, String> clmarque;

    @FXML
    private TableColumn<Pc, String> clnom;

    @FXML
    private TableColumn<Pc, String> clprix;

    @FXML
    private TableColumn<Pc, String> clpuissance;

    @FXML
    private TableColumn<Pc, String> clram;

    @FXML
    private TableColumn<Pc, String> clstarus;

    @FXML
    private TableColumn<Pc, String> clstockage;

    @FXML
    private TableColumn<Pc, String> clsupprimer;

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
    private TableView<Pc> tablePc;

    @FXML
    private Label username;


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





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Fcts.affiche(username, notificationconteur);
        ObservableList<Pc> pcList = getAllPcVendeur();

        // Lier les données à la TableView
        tablePc.setItems(pcList);

        // Configurer les colonnes pour afficher les propriétés des PC
        clmarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        clnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        clpuissance.setCellValueFactory(new PropertyValueFactory<>("puissance"));
        clram.setCellValueFactory(new PropertyValueFactory<>("ram"));
        clstockage.setCellValueFactory(new PropertyValueFactory<>("stockage"));
        clprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        clstarus.setCellValueFactory(new PropertyValueFactory<>("status"));
        clsupprimer.setCellValueFactory(new PropertyValueFactory<>("btnSupprimer"));
        cledit.setCellValueFactory(new PropertyValueFactory<>("btnEdit"));
    }






        @FXML
    void allerAjouterPcPage(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/AjouterPc.fxml",hbaccueil , "Ajouter Pc");
    }

    public  ObservableList<Pc> getAllPcVendeur() {
        ObservableList<Pc> pcList = FXCollections.observableArrayList();

        String query = "SELECT * FROM PC WHERE idVendeur= ?";

        try  {
            Connection conn = ConnectionDb.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, Info.idutilisateur);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idVendeur = resultSet.getInt("idVendeur");
                String puissance = resultSet.getString("puissance");
                String nom = resultSet.getString("nom");
                String ecran = resultSet.getString("ecran");
                String stockage = resultSet.getString("stockage");
                String ram = resultSet.getString("ram");
                String carteGraphique = resultSet.getString("carteGraphique");
                String marque = resultSet.getString("marque");
                String prix = resultSet.getString("prix");
                String dateAjouter = resultSet.getString("dateAjouter");
                String url = resultSet.getString("url");
                String status = resultSet.getString("status");

                Pc pc = new Pc(id, idVendeur,  nom,ecran,stockage,ram, puissance, carteGraphique, marque, prix , dateAjouter, url , status);
                pcList.add(pc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pcList;
    }
}

