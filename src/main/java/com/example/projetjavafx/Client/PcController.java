package com.example.projetjavafx.Client;

import com.example.projetjavafx.Model.Pc;
import com.example.projetjavafx.fct.Info;
import com.example.projetjavafx.fct.Fcts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.example.projetjavafx.fct.Fcts.getNombreLikes;

public class PcController {

    @FXML
    private Button btninfo;

    @FXML
    private Button btnlike;

    @FXML
    private Label idpc;

    @FXML
    private ImageView imagepc;

    @FXML
    private Label nbrlikr;

    @FXML
    private Label nompc;

    @FXML
    private Label pussance;

    @FXML
    private Label ram;

    @FXML
    private Label stockage;

    @FXML
    void like(ActionEvent event) {
        Fcts.like(Integer.parseInt(idpc.getText()),Info.idutilisateur);
    }
    public void updatDataPc(Pc p){
        Image img=new Image(getClass().getResourceAsStream(p.getUrl()));
        imagepc.setImage(img);
        nompc.setText(p.getNom());
        pussance.setText(p.getPuissance());
        ram.setText(p.getRam());
        stockage.setText(p.getStockage());
        nbrlikr.setText(String.valueOf(Fcts.getNombreLikes(p.getId())));
        idpc.setText(String.valueOf(p.getId()));
    }

    @FXML
    void plusinfo(ActionEvent event) {
        Info.idPc= Integer.parseInt(idpc.getText());
        Fcts.change("/com/example/projetjavafx/Client/infoPc.fxml", btninfo , "Plus d´information");
    }
}

