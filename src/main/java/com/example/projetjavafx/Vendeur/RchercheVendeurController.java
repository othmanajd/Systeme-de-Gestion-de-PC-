package com.example.projetjavafx.Vendeur;

import com.example.projetjavafx.Model.Pc;
import com.example.projetjavafx.fct.ConnectionDb;
import com.example.projetjavafx.fct.Fcts;
import com.example.projetjavafx.fct.Info;
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

public class RchercheVendeurController implements Initializable {
    @FXML
    private TextField puissance;

    @FXML
    private TextField ecran;

    @FXML
    private TextField stockage;

    @FXML
    private TextField ram;

    @FXML
    private TextField carte;

    @FXML
    private TextField marque;

    @FXML
    private Slider prix;

    @FXML
    private TableColumn<Pc, Integer> cledit;
    @FXML
    private TableView<Pc> tablePc;

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
    private HBox hbnous;

    @FXML
    private HBox hbprofil;

    @FXML
    private HBox hbrecherche;

    @FXML
    private Label notificationconteur;

    @FXML
    private Label username;

    @FXML
    private HBox hbstatistique;

    @FXML
    void chgtoaccueil(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/AccueilVendeur.fxml", hbrecherche, "Accuiel Vendeur");
    }

    @FXML
    void chgtonotification(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/Notification.fxml",hbrecherche , "Notification");

    }

    @FXML
    void chgtonous(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/Nous.fxml",hbrecherche, "À propos de nous");
    }

    @FXML
    void chgtoprofil(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/ProfilVendeur.fxml",hbrecherche , "Profil vendeur");
    }
    @FXML
    void chgtostatistique(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Vendeur/Statistique.fxml",hbrecherche , "Statistique");

    }
    @FXML
    void rechercherPcFct(ActionEvent event) {
        initialize(null,null);
    }


    @FXML
    void deconnecter(ActionEvent event) {
        Fcts.deconiction(hbaccueil);
    }

    public  ObservableList<Pc> getPcsChercher() {
        ObservableList<Pc> pcList = FXCollections.observableArrayList();
        String puissanceRecherche = "%" + puissance.getText() + "%";
        String ecranRecherche = "%" + ecran.getText() + "%";
        String stockageRecherche = "%" + stockage.getText() + "%";
        String ramRecherche = "%" + ram.getText() + "%";
        String carteRecherche = "%" + carte.getText() + "%";
        String marqueRecherche = "%" + marque.getText() + "%";
        double prixRecherche = prix.getValue();
        String sql = "SELECT * FROM pc WHERE puissance LIKE ? AND marque LIKE ? AND ecran LIKE ? AND stockage LIKE ? AND ram LIKE ? AND carteGraphique LIKE ? AND prix <= ? AND idVendeur= ?";

        try  {
            Connection conn = ConnectionDb.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, puissanceRecherche);
            preparedStatement.setString(2, marqueRecherche);
            preparedStatement.setString(3, ecranRecherche);
            preparedStatement.setString(4, stockageRecherche);
            preparedStatement.setString(5, ramRecherche);
            preparedStatement.setString(6, carteRecherche);
            preparedStatement.setDouble(7, prixRecherche);
            preparedStatement.setInt(8, Info.idutilisateur);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Fcts.affiche(username,notificationconteur);
        ObservableList<Pc> pcList = getPcsChercher();

            // Configurer les colonnes pour afficher les propriétés des PC
        tablePc.setItems(pcList);
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

}


