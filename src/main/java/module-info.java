module com.registro.registro {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;


    opens com.registro.registro to javafx.fxml;
    opens com.registro.registro.BaseDeDatos to javafx.base;

    exports com.registro.registro;
    exports com.registro.registro.BaseDeDatos;

}