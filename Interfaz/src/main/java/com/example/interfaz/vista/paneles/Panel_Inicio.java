package com.example.interfaz.vista.paneles;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class Panel_Inicio extends Pane {

    private Label lbImagen, lbTititulo;
    Image image;
    String titulo;
    public Panel_Inicio(Image imagen, String titulo) {
        this.image = imagen;
        this.titulo = titulo;
        setMinSize(900, 680);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        lbImagen = new Label();
        lbImagen.setGraphic(new ImageView(image));
        lbImagen.setMinSize(150, 150);
        lbImagen.setLayoutX(375);
        lbImagen.setLayoutY(215);

        lbTititulo = new Label();
        lbTititulo.setText(titulo);
        lbTititulo.setMinSize(350, 50);
        lbTititulo.setFont(new Font("Georgia", 30));
        lbTititulo.setLayoutX(275);
        lbTititulo.setAlignment(Pos.CENTER);
        lbTititulo.setLayoutY(415);


        getChildren().addAll(lbImagen, lbTititulo);
    }

}
