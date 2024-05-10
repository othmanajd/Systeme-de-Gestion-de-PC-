package com.example.projetjavafx.Model;

import com.example.projetjavafx.fct.ConnectionDb;
import com.example.projetjavafx.fct.Fcts;
import com.example.projetjavafx.fct.Info;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;

public class Pc {
    private int id;
    private int idVendeur;
    private  String puissance;
    private  String nom;
    private  String ecran;
    private  String stockage;
    private  String ram;
    private  String carteGraphoque;
    private  String marque;
    private  String prix;
    private  String dateAjouter;
    private String url;
   private  int idClient;
   private  String status;
   private  String dateModification;
   private Button btnSupprimer;

   private  Button btnEdit;

    public Button getBtnEdit() {
        return btnEdit;
    }

    public void setBtnEdit(Button btnEdit) {
        this.btnEdit = btnEdit;
    }

    public Pc(int id, int idVendeur, String nom, String ecran, String stockage, String ram, String puissance, String carteGraphoque, String marque, String prix , String dateAjouter, String url) {
        this.id = id;
        this.idVendeur = idVendeur;
        this.nom = nom;
        this.ecran = ecran;
        this.stockage = stockage;
        this.ram = ram;
        this.puissance = puissance;
        this.carteGraphoque = carteGraphoque;
        this.marque = marque;
        this.prix = prix;
        this.dateAjouter = dateAjouter;
        this.url = url;
        this.btnSupprimer=new Button("supprimer");
        this.btnSupprimer.setStyle("-fx-background-color: #ff0000;");
        this.btnEdit=new Button(" Edit");
    }

    public Pc(int id, int idVendeur, String nom, String ecran, String stockage, String ram, String puissance, String carteGraphoque, String marque, String prix , String dateAjouter, String url ,String s) {
        this.id = id;
        this.idVendeur = idVendeur;
        this.nom = nom;
        this.ecran = ecran;
        this.stockage = stockage;
        this.ram = ram;
        this.puissance = puissance;
        this.carteGraphoque = carteGraphoque;
        this.marque = marque;
        this.prix = prix;
        this.dateAjouter = dateAjouter;
        this.url = url;
        this.status=s;
        this.btnSupprimer=new Button("supprimer");
        this.btnSupprimer.setStyle("-fx-background-color: rgb(220,53,69);-fx-text-fill: #fff;-fx-font-weight: bold;");
        this.btnSupprimer.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Voulez-vous vraiment supprimer ce PC ?");

            // Ajouter les boutons "OK" et "Annuler" à la boîte de dialogue
            ButtonType btnOK = new ButtonType("OK");
            ButtonType btnCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(btnOK, btnCancel);

            // Attendre la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == btnOK) {
                String query = "DELETE FROM PC WHERE id = ?";
                try  {
                    Connection conn = ConnectionDb.getConnection();
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    preparedStatement.setInt(1, this.id);
                    preparedStatement.executeUpdate();
                    Fcts.change("/com/example/projetjavafx/Vendeur/AccueilVendeur.fxml",btnEdit,"Accuiel Vendeur");

                } catch (Exception e) {
                    System.out.println("Erreur lors de la suppression du PC : " + e.getMessage());
                }
            }
        });

        this.btnEdit=new Button(" Edit");
        this.btnEdit.setStyle("-fx-background-color:rgb(23,162,184);-fx-text-fill: #fff;-fx-font-weight: bold;");
        this.btnEdit.setOnAction(event -> {
            Info.idPc=this.id;
            Fcts.change("/com/example/projetjavafx/Vendeur/EditPc.fxml",btnEdit,"Edit PC");
        });
    }

    public Button getBtnSupprimer() {
        return btnSupprimer;
    }

    public void setBtnSupprimer(Button btnSupprimer) {
        this.btnSupprimer = btnSupprimer;
    }

    public Pc(int id, int idVendeur, String puissance, String nom, String ecran, String stockage, String ram, String carteGraphoque, String marque, String prix, String dateAjouter, String url, int idClient, String status, String dateModification) {
        this.id = id;
        this.idVendeur = idVendeur;
        this.puissance = puissance;
        this.nom = nom;
        this.ecran = ecran;
        this.stockage = stockage;
        this.ram = ram;
        this.carteGraphoque = carteGraphoque;
        this.marque = marque;
        this.prix = prix;
        this.dateAjouter = dateAjouter;
        this.url = url;
        this.idClient = idClient;
        this.status = status;
        this.dateModification = dateModification;
        this.btnSupprimer=new Button("supprimer");
        this.btnSupprimer.setStyle("-fx-background-color: rgb(220,53,69);");
        this.btnSupprimer.setStyle("-fx-text-fill: #fff;");
        this.btnEdit=new Button(" Edit");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVendeur() {
        return idVendeur;
    }

    public void setIdVendeur(int idVendeur) {
        this.idVendeur = idVendeur;
    }

    public String getPuissance() {
        return puissance;
    }

    public void setPuissance(String puissance) {
        this.puissance = puissance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEcran() {
        return ecran;
    }

    public void setEcran(String ecran) {
        this.ecran = ecran;
    }

    public String getStockage() {
        return stockage;
    }

    public void setStockage(String stockage) {
        this.stockage = stockage;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getCarteGraphoque() {
        return carteGraphoque;
    }

    public void setCarteGraphoque(String carteGraphoque) {
        this.carteGraphoque = carteGraphoque;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getDateAjouter() {
        return dateAjouter;
    }

    public void setDateAjouter(String dateAjouter) {
        this.dateAjouter = dateAjouter;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateModification() {
        return dateModification;
    }

    public void setDateModification(String dateModification) {
        this.dateModification = dateModification;
    }
}
