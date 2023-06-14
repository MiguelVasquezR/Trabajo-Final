package com.example.interfaz.vista.emergentes;

import com.example.interfaz.controladores.generadorPDF.Generador;
import com.example.interfaz.modelo.EstilosComponentes;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class V_Compra extends BorderPane {

    Pane paneT, paneC, P_Vista;
    Label lbTTotal, lbTitulo, lbImagen;
    Font fuente;
    EstilosComponentes ec;
    Button btnPagar;

    public V_Compra(){
        setMinSize(500, 600);
        setPrefSize(500, 600);
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
        paneC.setMinSize(500, 500);
        lbTTotal = new Label("Vista de Factura");
        lbTTotal.setFont(fuente);
        lbTTotal.setMinSize(200, 40);
        lbTTotal.setLayoutX(150);
        lbTTotal.setLayoutY(10);
        lbTTotal.setAlignment(Pos.CENTER);

        P_Vista = new Pane();
        P_Vista.setMinSize(400, 350);
        P_Vista.setStyle("-fx-background-color: #eee; -fx-border-color: #000");
        P_Vista.setLayoutX(50);
        P_Vista.setLayoutY(60);

        btnPagar = new Button("Pagar");
        btnPagar.setStyle(ec.getBotones());
        btnPagar.setMinSize(150, 35);
        btnPagar.setLayoutX(175);
        btnPagar.setLayoutY(450);
        btnPagar.setFont(fuente);
        btnPagar.setOnMouseExited(evt->{
            btnPagar.setStyle(ec.getBotones());
        });
        btnPagar.setOnMouseEntered(evt->{
            btnPagar.setStyle(ec.getBotonesOk());
        });
        btnPagar.setOnAction(evt->{
            Generador pdf = new Generador("Ticket Compra");
        });
        paneC.getChildren().addAll(lbTTotal, P_Vista, btnPagar);
        return paneC;

    }


}
