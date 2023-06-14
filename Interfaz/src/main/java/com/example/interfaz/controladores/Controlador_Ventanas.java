package com.example.interfaz.controladores;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Controlador_Ventanas {

    Image imagen;

    public Controlador_Ventanas(){}

    public void cerrar(){
        Alert dialogoSalir = new Alert(Alert.AlertType.CONFIRMATION);
        dialogoSalir.setHeaderText("Advertencia");
        dialogoSalir.setTitle("Salir");
        dialogoSalir.setContentText("\n¿Quiere salir de la aplicación\n");

        ImageView mv = new ImageView(imagen);
        mv.setFitHeight(50);
        mv.setFitWidth(50);
        dialogoSalir.getDialogPane().setGraphic(mv);
        ((Stage)dialogoSalir.getDialogPane().getScene().getWindow()).getIcons().add(imagen);

        var opcion = dialogoSalir.showAndWait().get();
        if (opcion == ButtonType.OK) {
            System.exit(0);
        }
    }

    public Image getFoto(){
        var URL = getClass().getResource("/img/Logo.png");
        imagen = new Image(URL.toString());
        return imagen;
    }

}
