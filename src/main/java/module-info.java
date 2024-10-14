module es.juliogtrenard.gestionarpersonas {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.juliogtrenard.gestionarpersonas to javafx.fxml;
    exports es.juliogtrenard.gestionarpersonas;
    exports es.juliogtrenard.gestionarpersonas.controladores;
    exports es.juliogtrenard.gestionarpersonas.modelos;
    opens es.juliogtrenard.gestionarpersonas.controladores to javafx.fxml;
    opens es.juliogtrenard.gestionarpersonas.modelos to javafx.fxml;
}