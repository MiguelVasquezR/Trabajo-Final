module com.example.interfaz {
    requires javafx.controls;
    requires javafx.fxml;
    requires itext;
    requires org.apache.pdfbox;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires ae.awt;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.swing;
    requires com.google.gson;
    requires java.sql;
    requires jbcrypt;

    opens com.example.interfaz to javafx.fxml;
    exports com.example.interfaz;
    opens com.example.interfaz.ModeloClases to com.google.gson;
}