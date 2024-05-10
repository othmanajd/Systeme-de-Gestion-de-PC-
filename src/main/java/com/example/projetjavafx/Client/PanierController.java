package com.example.projetjavafx.Client;

import com.example.projetjavafx.Model.Pc;
import com.example.projetjavafx.fct.ConnectionDb;
import com.example.projetjavafx.fct.Info;
import com.example.projetjavafx.fct.Fcts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
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

public class PanierController implements Initializable {

    @FXML
    private GridPane PcContainer;

    @FXML
    private HBox deconnexion;

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
    private Label notificationconteur;
    @FXML
    private Label username;
    private List<Pc> recommender;

    @FXML
    void chgtoaccueil(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Client/AccueilClient.fxml", hbpanier ,"Accueil Client");
    }
    @FXML
    void chgtonotification(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Client/Notification.fxml",hbpanier , "Notification");
    }

    @FXML
    void chgtonous(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Client/Nous.fxml", hbpanier , "À propos de nous");
    }
    @FXML
    void chgtorecherche(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Client/Recherche.fxml",hbpanier , "Recherche avancée");
    }


    @FXML
    void chgtoprofil(ActionEvent event) {
        Fcts.change("/com/example/projetjavafx/Client/Profil.fxml", hbrecherche ,"Profil Client");

    }

    @FXML
    void deconnecter(ActionEvent event) {
        Fcts.deconiction(deconnexion);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Fcts.affiche(username,notificationconteur);
        recommender =new ArrayList<>(pcs());
        int colums=0;
        int row=1;
        try {
            for(Pc pc : recommender){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/projetjavafx/Client/PanierCard.fxml"));
                VBox pcBox = fxmlLoader.load();
                PanierCardController bookController = fxmlLoader.getController();
                bookController.updatDataPc(pc);
                if(colums==4){
                    colums=0;
                    row++;
                }
                PcContainer.add(pcBox,colums++,row);
                GridPane.setMargin(pcBox,new Insets(10));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Pc> pcs(){
        List<Pc> l = new ArrayList<>();
        try  {
            Connection conn= ConnectionDb.getConnection();
            String sql = "SELECT * FROM pc where idClient="+ Info.idutilisateur;
            PreparedStatement statement = conn.prepareStatement(sql);
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
                String url=resultSet.getString("url");
                String status=resultSet.getString("status");
                // Création d'un objet Pc et ajout à la liste
                Pc pc = new Pc(id, idVendeur, nom, ecran , stockage , ram , puissance, carteGraphique, marque, prix , date,url ,status);
                l.add(pc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

}

