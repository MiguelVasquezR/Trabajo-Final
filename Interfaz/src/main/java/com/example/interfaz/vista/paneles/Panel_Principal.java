package com.example.interfaz.vista.paneles;

import com.example.interfaz.servidor.Cliente;
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

public class Panel_Principal extends Pane {

    private Label lbImagen, lbTititulo, lbHora, lbFecha;
    public Label lbUsuario;
    Image image;
    String titulo;
    public Panel_Principal(Image imagen, String titulo) {
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
        lbImagen.setLayoutY(125);

        lbTititulo = new Label();
        lbTititulo.setText(titulo);
        lbTititulo.setMinSize(250, 50);
        lbTititulo.setFont(new Font("Georgia", 50));
        lbTititulo.setLayoutX(325);
        lbTititulo.setAlignment(Pos.CENTER);
        lbTititulo.setLayoutY(275);
        lbTititulo.setStyle("-fx-text-fill: #016a94;");

        lbUsuario = new Label("Bienvenido");
        lbUsuario.setMinSize(250, 50);
        lbUsuario.setFont(Font.font("Georgia", 30));
        lbUsuario.setLayoutX(325);
        lbUsuario.setAlignment(Pos.CENTER);
        lbUsuario.setLayoutY(350);


        LocalDate fechaL = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha = fechaL.format(formato);
        lbFecha = new Label();
        lbFecha.setText(fecha);
        lbFecha.setMinSize(250, 50);
        lbFecha.setFont(Font.font("Georgia", 30));
        lbFecha.setLayoutX(325);
        lbFecha.setAlignment(Pos.CENTER);
        lbFecha.setLayoutY(400);

        lbHora = new Label();
        lbHora.setMinSize(250, 50);
        lbHora.setFont(Font.font("Georgia", 30));
        lbHora.setLayoutX(325);
        lbHora.setAlignment(Pos.CENTER);
        lbHora.setLayoutY(430);
        hora();


        getChildren().addAll(lbImagen, lbTititulo, lbUsuario ,lbFecha, lbHora);
    }

    private void hora(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    LocalTime time = LocalTime.now();
                    String formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                    lbHora.setText(formattedTime);
                });
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

}
