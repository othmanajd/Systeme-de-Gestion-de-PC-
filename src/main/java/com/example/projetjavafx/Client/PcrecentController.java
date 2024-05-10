package com.example.projetjavafx.Client;

import com.example.projetjavafx.Model.Pc;
import com.example.projetjavafx.fct.Info;
import com.example.projetjavafx.fct.Fcts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PcrecentController {

    @FXML
    private Button btninfo;

    @FXML
    private Button btnlike;

    @FXML
    private Label idpc;

    @FXML
    private ImageView imgpc;

    @FXML
    private Label nbrlikr;

    @FXML
    private Label nompc;

    @FXML
    private Label puissance;

    @FXML
    private Label ram;

    @FXML
    private Label stockage;

    @FXML
    void like(ActionEvent event) {
        Fcts.like(Integer.parseInt(idpc.getText()),Info.idutilisateur);

    }

    @FXML
    void plusinfo(ActionEvent event) {
        Info.idPc= Integer.parseInt(idpc.getText());
        Fcts.change("/com/example/projetjavafx/Client/infoPc.fxml", btninfo , "Plus d´information");

    }
    public void updateDataRecent(Pc p){
        Image img =new Image(getClass().getResourceAsStream(p.getUrl()));
        imgpc.setImage(img);
        puissance.setText(p.getPuissance());
        stockage.setText(p.getStockage());
        ram.setText(p.getRam());
        nbrlikr.setText(String.valueOf(Fcts.getNombreLikes(p.getId())));
        nompc.setText(p.getNom());
        idpc.setText(String.valueOf(p.getId()));
    }

}
