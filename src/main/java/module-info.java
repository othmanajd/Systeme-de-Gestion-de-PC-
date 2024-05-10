module com.example.projetjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.projetjavafx to javafx.fxml;
    opens com.example.projetjavafx.Model to javafx.base;
    exports com.example.projetjavafx;
    exports com.example.projetjavafx.Client;
    opens com.example.projetjavafx.Client to javafx.fxml;
    exports com.example.projetjavafx.Vendeur;
    opens com.example.projetjavafx.Vendeur to javafx.fxml;
    exports com.example.projetjavafx.fct;
    opens com.example.projetjavafx.fct to javafx.fxml;
}
