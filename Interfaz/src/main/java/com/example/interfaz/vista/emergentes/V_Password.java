package com.example.interfaz.vista.emergentes;

import com.example.interfaz.modelo.EstilosComponentes;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class V_Password extends BorderPane {

    Pane paneT, paneC;
    Label lbCambio, lbTTotal, lbTitulo, lbImagen;
    TextField tfNueva, tf_Actual;
    Font fuente;
    EstilosComponentes ec;
    Button btnCambiar;

    public V_Password(){
        setMinSize(500, 500);
        ec = new EstilosComponentes();
        setTop(top());
        setCenter(center());
    }


    private Pane top(){
        paneT = new Pane();
        paneT.setMinSize(500, 70);
        paneT.setBackground(Background.fill(Color.rgb(26, 153, 205)));
        var URL = getClass().getResource("/img/Logo.png");
        var img = new Image(URL.toString(), 40,40, true, true);
        lbImagen = new Label();
        lbImagen.setGraphic(new ImageView(img));
        lbImagen.setMinSize(40, 40);
        lbImagen.setLayoutX(10);
        lbImagen.setLayoutY(15);
        lbTitulo = new Label("La Canasta");
        lbTitulo.setFont(fuente = new Font("Georgia", 18));
        lbTitulo.setLayoutX(70);
        lbTitulo.setLayoutY(10);
        lbTitulo.setStyle("-fx-text-fill: #fff;");
        lbTitulo.setMinSize(100, 50);
        paneT.getChildren().addAll(lbImagen, lbTitulo);
        return paneT;
    }

    private Pane center(){
        paneC = new Pane();
        paneC.setMinSize(500, 430);
        lbTTotal = new Label("Ingresa");
        lbTTotal.setFont(fuente);
        lbTTotal.setMinSize(200, 40);
        lbTTotal.setLayoutX(150);
        lbTTotal.setLayoutY(50);

        tf_Actual = new TextField();
        tf_Actual.setAlignment(Pos.CENTER);
        tf_Actual.setPromptText("Contraseña Actual");
        tf_Actual.setStyle(ec.getTextfield());
        tf_Actual.setMinSize(200, 40);
        tf_Actual.setLayoutX(150);
        tf_Actual.setLayoutY(100);
        tf_Actual.setFont(fuente);

        lbCambio = new Label("Ingresa");
        lbCambio.setLayoutX(150);
        lbCambio.setLayoutY(200);
        lbCambio.setFont(fuente);
        lbCambio.setMinSize(200, 40);

        tfNueva = new TextField();
        tfNueva.setAlignment(Pos.CENTER);
        tfNueva.setPromptText("Nueva Contraseña");
        tfNueva.setStyle(ec.getTextfield());
        tfNueva.setMinSize(200, 40);
        tfNueva.setLayoutX(150);
        tfNueva.setLayoutY(250);
        tfNueva.setFont(fuente);

        btnCambiar = new Button("Cambiar Contraseña");
        btnCambiar.setStyle(ec.getBotones());
        btnCambiar.setMinSize(250, 40);
        btnCambiar.setLayoutX(125);
        btnCambiar.setLayoutY(350);
        btnCambiar.setFont(fuente);
        btnCambiar.setOnMouseExited(evt->{
            btnCambiar.setStyle(ec.getBotones());
        });
        btnCambiar.setOnMouseEntered(evt->{
            btnCambiar.setStyle(ec.getBotonesOk());
        });
        paneC.getChildren().addAll(lbTTotal, tf_Actual, tfNueva, lbCambio, btnCambiar);
        return paneC;

    }

}
