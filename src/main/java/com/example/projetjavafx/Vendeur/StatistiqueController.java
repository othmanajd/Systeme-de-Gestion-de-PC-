package com.example.projetjavafx.Vendeur;

import com.example.projetjavafx.fct.ConnectionDb;
import com.example.projetjavafx.fct.Fcts;
import com.example.projetjavafx.fct.Info;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class StatistiqueController implements Initializable {

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private HBox hbaccueil;

    @FXML
    private HBox hbnotification;

    @FXML
    private HBox hbnotification1;

    @FXML
    private HBox hbnous;

    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;
    @FXML
    private HBox hbprofil;

    @FXML
    private HBox hbrecherche;

    @FXML
    private HBox hbstatistique;

    @FXML
    private Label notificationconteur;

    @FXML
    private PieChart piechart;

    @FXML
    private Label username;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Fcts.affiche(username,notificationconteur);
        int D=5,L=3,R=0 ,E=4;
        String sqlD="SELECT COUNT(*) FROM pc WHERE id = ? AND status = ?";
        String sqlL="SELECT COUNT(*) FROM pc WHERE id = ? AND status = ?";
        String sqlR="SELECT COUNT(*) FROM pc WHERE id = ? AND status = ?";
        String sqlE="SELECT COUNT(*) FROM pc WHERE id = ? AND status = ?";
        try {
            Connection conn = ConnectionDb.getConnection();

            PreparedStatement countStatementD = conn.prepareStatement(sqlD);
            PreparedStatement countStatementL = conn.prepareStatement(sqlL);
            PreparedStatement countStatementR = conn.prepareStatement(sqlR);
            PreparedStatement countStatementE = conn.prepareStatement(sqlR);

            countStatementD.setInt(1, Info.idutilisateur);
            countStatementD.setString(2, "Disponible");
            ResultSet resultSetD = countStatementD.executeQuery();
            if (resultSetD.next()) {
                D += resultSetD.getInt(1);
            }

            countStatementL.setInt(1, Info.idutilisateur);
            countStatementL.setString(2, "Livré");
            ResultSet resultSetL = countStatementL.executeQuery();
            if (resultSetL.next()) {
                L += resultSetL.getInt(1);
            }

            countStatementR.setInt(1, Info.idutilisateur);
            countStatementR.setString(2, "Retour");
            ResultSet resultSetR = countStatementR.executeQuery();
            if (resultSetR.next()) {
                R += resultSetR.getInt(1);
            }
            countStatementE.setInt(1, Info.idutilisateur);
            countStatementE.setString(2, "En attente");
            ResultSet resultSetE = countStatementL.executeQuery();
            if (resultSetE.next()) {
                E += resultSetE.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        XYChart.Series set1= new  XYChart.Series<>();
        set1.getData().add(new XYChart.Data("Disponible",D));
        set1.getData().add(new XYChart.Data("Livré",L));
        set1.getData().add(new XYChart.Data("Retour",R));
        set1.getData().add(new XYChart.Data("En attente",E));
        barChart.getData().addAll(set1);

        ObservableList<PieChart.Data> pieChartData=
                FXCollections.observableArrayList(
                        new PieChart.Data("Disponible",D),
                        new PieChart.Data("En attente",E),
                        new PieChart.Data("Livré",L),
                        new PieChart.Data("Retour",R)
                );
        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName()," :", data.pieValueProperty()
                        )
                ));
        piechart.getData().addAll(pieChartData);
    }

}
