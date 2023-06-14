package com.example.interfaz.vista.emergentes;

import com.example.interfaz.controladores.Controlador_Ventanas;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Alerta extends Alert{

    public Alerta(AlertType alertType, String tipo) {
        super(alertType);
        setHeight(300);
        setWidth(400);
        Label titulo = new Label("La Canasta");
        titulo.setFont(Font.font("Georgia", 40));
        setTitle(titulo.getText());
        setHeaderText(null);
        setContentText(tipo);
        var url = getClass().getResource("/img/Logo.png");
        var imagen = new Image(url.toString(), 50, 50, true, true);
        setGraphic(new ImageView(imagen));
        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.getIcons().add(imagen);
    }
}
