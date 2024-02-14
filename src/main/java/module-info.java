module com.registro.registro {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    requires java.sql;
    requires java.desktop;
    requires javax.mail.api;
    requires com.google.api.client;
    requires com.google.api.client.json.jackson2;
    requires com.google.api.client.auth;
    requires google.api.client;


    opens com.registro.registro to javafx.fxml;
    opens com.registro.registro.BaseDeDatos to javafx.base;

    exports com.registro.registro;
    exports com.registro.registro.BaseDeDatos;

}