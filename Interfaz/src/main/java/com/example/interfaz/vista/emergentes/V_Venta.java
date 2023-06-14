package com.example.interfaz.vista.emergentes;

import com.example.interfaz.controladores.generadorPDF.Generador;
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

public class V_Venta extends BorderPane {

    Pane paneT, paneC;
    Label lbCobro, lbCambio, lbTTotal, lbTitulo, lbImagen;
    TextField tfEfectivo;
    Font fuente;
    EstilosComponentes ec;
    Button btnPagar;

    public V_Venta(){
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
        lbTTotal = new Label("Cantidad en Efectivo: ");
        lbTTotal.setFont(fuente);
        lbTTotal.setMinSize(200, 40);
        lbTTotal.setLayoutX(150);
        lbTTotal.setLayoutY(50);
        tfEfectivo = new TextField();
        tfEfectivo.setAlignment(Pos.CENTER);
        tfEfectivo.setPromptText("Cantidad");
        tfEfectivo.setStyle(ec.getTextfield());
        tfEfectivo.setMinSize(200, 40);
        tfEfectivo.setLayoutX(150);
        tfEfectivo.setLayoutY(100);
        tfEfectivo.setFont(fuente);
        lbCobro = new Label("Cambio:");
        lbCobro.setLayoutX(150);
        lbCobro.setLayoutY(150);
        lbCobro.setMinSize(200,40);
        lbCobro.setFont(fuente);
        lbCambio = new Label("$ 0.00");
        lbCambio.setLayoutX(150);
        lbCambio.setLayoutY(200);
        lbCambio.setFont(fuente);
        lbCambio.setMinSize(200, 40);
        lbCambio.setAlignment(Pos.CENTER);
        btnPagar = new Button("Pagar");
        btnPagar.setStyle(ec.getBotones());
        btnPagar.setMinSize(150, 35);
        btnPagar.setLayoutX(175);
        btnPagar.setLayoutY(300);
        btnPagar.setFont(fuente);
        btnPagar.setOnMouseExited(evt->{
            btnPagar.setStyle(ec.getBotones());
        });
        btnPagar.setOnMouseEntered(evt->{
            btnPagar.setStyle(ec.getBotonesOk());
        });
        btnPagar.setOnAction(evt->{
            Generador pdf = new Generador("Ticket Venta");
        });
        paneC.getChildren().addAll(lbTTotal, tfEfectivo, lbCobro, lbCambio, btnPagar);
        return paneC;

    }

}
