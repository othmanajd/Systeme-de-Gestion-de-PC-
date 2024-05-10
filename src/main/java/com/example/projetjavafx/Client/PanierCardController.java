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

public class PanierCardController {

    @FXML
    private Button btninfo;

    @FXML
    private Label idpc;
    @FXML
    private Label puissance;

    @FXML
    private ImageView imagepc;

    @FXML
    private Label nompc;

    @FXML
    private Label ram;

    @FXML
    private Label status;

    @FXML
    private Label stockage;

    @FXML
    void plusinfo(ActionEvent event) {
        Info.idPc= Integer.parseInt(idpc.getText());
        Fcts.change("/com/example/projetjavafx/Client/infoPc.fxml", btninfo , "Plus d´information");
    }

    public void updatDataPc(Pc p){
        Image img=new Image(getClass().getResourceAsStream(p.getUrl()));
        imagepc.setImage(img);
        nompc.setText(p.getNom());
        puissance.setText(p.getPuissance());
        ram.setText(p.getRam());
        stockage.setText(p.getStockage());
        idpc.setText(String.valueOf(p.getId()));
        status.setText(p.getStatus());
    }

}
