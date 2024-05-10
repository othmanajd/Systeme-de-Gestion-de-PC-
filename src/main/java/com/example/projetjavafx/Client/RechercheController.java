package com.example.projetjavafx.Client;

import com.example.projetjavafx.Model.Pc;
import com.example.projetjavafx.fct.ConnectionDb;
import com.example.projetjavafx.fct.Fcts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RechercheController implements Initializable {

    @FXML
    private GridPane PcContainer;

    @FXML
    private Label notificationconteur;
    @FXML
    private Label username;
    @FXML
    private HBox deconnexion;

    @FXML
    private TextField ecran;

    @FXML
    private VBox graphique;

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
    private TextField marque;

    @FXML
    private TextField carte;
    @FXML
    private Slider prix;

    @FXML
    private TextField puissance;

    private List<Pc> recommender;

    @FXML
    private TextField ram;

    @FXML
    private Button rechercherPc;

    @FXML
    private TextField stockage;

    @FXML
    void chgtoaccueil(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Client/AccueilClient.fxml", hbrecherche, "Accueil Client");
    }

    @FXML
    void chgtonotification(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Client/Notification.fxml", hbrecherche, "Notification");
    }

    @FXML
    void chgtonous(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Client/Nous.fxml", hbrecherche, "À propos de nous");
    }

    @FXML
    void chgtopanier(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Client/Panier.fxml", hbrecherche, "Panier d'achat");
    }

    @FXML
    void chgtoprofil(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Client/Profil.fxml", hbrecherche, "Profil Client");

    }

    @FXML
    void rechercherPcFct(ActionEvent event) {
        PcContainer.getChildren().clear(); // Effacer les PC précédents affichés

        List<Pc> resultatRecherche = pcs(); // Exécuter la recherche

        // Afficher les résultats de la recherche dans PcContainer
        int colums = 0;
        int row = 1;
        try {
            for (Pc pc : resultatRecherche) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/projetjavafx/Client/Pc.fxml"));
                VBox pcBox = fxmlLoader.load();
                PcController pcController = fxmlLoader.getController();
                pcController.updatDataPc(pc);
                if (colums == 4) {
                    colums = 0;
                    row++;
                }
                PcContainer.add(pcBox, colums++, row);
                GridPane.setMargin(pcBox, new Insets(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void deconnecter(ActionEvent event) {
        Fcts.deconiction(deconnexion);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Fcts.affiche(username,notificationconteur);
        recommender = new ArrayList<>(pcs());
        int colums = 0;
        int row = 1;
        try {
            for (Pc pc : recommender) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/projetjavafx/Client/Pc.fxml"));
                VBox bookBox = fxmlLoader.load();
                PcController bookController = fxmlLoader.getController();
                bookController.updatDataPc(pc);
                if (colums == 4) {
                    colums = 0;
                    row++;
                }
                PcContainer.add(bookBox, colums++, row);
                GridPane.setMargin(bookBox, new Insets(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Pc> pcs() {
        List<Pc> l = new ArrayList<>();
        try {

            Connection conn = ConnectionDb.getConnection();
            String puissanceRecherche = "%" + puissance.getText() + "%";
            String ecranRecherche = "%" + ecran.getText() + "%";
            String stockageRecherche = "%" + stockage.getText() + "%";
            String ramRecherche = "%" + ram.getText() + "%";
            String carteRecherche = "%" + carte.getText() + "%";
            String marqueRecherche = "%" + marque.getText() + "%";
            double prixRecherche = prix.getValue();
            String sql = "SELECT * FROM pc WHERE puissance LIKE ? AND marque LIKE ? AND ecran LIKE ? AND stockage LIKE ? AND ram LIKE ? AND carteGraphique LIKE ? AND prix <= ? AND status = 'Disponible'";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, puissanceRecherche);
            statement.setString(2, marqueRecherche);
            statement.setString(3, ecranRecherche);
            statement.setString(4, stockageRecherche);
            statement.setString(5, ramRecherche);
            statement.setString(6, carteRecherche);
            statement.setDouble(7, prixRecherche);
            ResultSet resultSet = statement.executeQuery();
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
                String date = resultSet.getString("dateajouter");
                String url = resultSet.getString("url");
                // Création d'un objet Pc et ajout à la liste
                Pc pc = new Pc(id, idVendeur, nom, ecran , stockage , ram , puissance, carteGraphique, marque, prix , date,url );
                l.add(pc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
}






