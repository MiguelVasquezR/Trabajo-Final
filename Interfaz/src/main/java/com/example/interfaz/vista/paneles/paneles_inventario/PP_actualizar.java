package com.example.interfaz.vista.paneles.paneles_inventario;

import com.example.interfaz.modelo.EstilosComponentes;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class PP_actualizar extends Pane {


    TextField tfCantidadAct, tfCantidadNueva;
    Label lbCantidadAct, lbCantidadNueva;
    Button btnActualiar;
    Font fuente;
    EstilosComponentes estilos;

    public PP_actualizar(){
        fuente = new Font("Georgia", 18);
        estilos = new EstilosComponentes();
        initComponent();
    }

    private void initComponent() {

        lbCantidadAct = new Label();
        lbCantidadAct.setText("Cantidad Actual: ");
        lbCantidadAct.setFont(fuente);
        lbCantidadAct.setPrefSize(300, 40);
        lbCantidadAct.setLayoutX(100);
        lbCantidadAct.setLayoutY(50);

        tfCantidadAct = new TextField();
        tfCantidadAct.setPrefSize(300, 40);
        tfCantidadAct.setStyle(estilos.getTextfield());
        tfCantidadAct.setLayoutX(100);
        tfCantidadAct.setLayoutY(100);
        tfCantidadAct.setFont(fuente);
        tfCantidadAct.setEditable(false);
        tfCantidadAct.setAlignment(Pos.CENTER);

        lbCantidadNueva = new Label();
        lbCantidadNueva.setText("Cantidad Nueva: ");
        lbCantidadNueva.setFont(fuente);
        lbCantidadNueva.setPrefSize(300, 40);
        lbCantidadNueva.setLayoutX(100);
        lbCantidadNueva.setLayoutY(230);

        tfCantidadNueva = new TextField();
        tfCantidadNueva.setPrefSize(300, 40);
        tfCantidadNueva.setStyle(estilos.getTextfield());
        tfCantidadNueva.setFont(fuente);
        tfCantidadNueva.setLayoutX(100);
        tfCantidadNueva.setLayoutY(280);
        tfCantidadNueva.setAlignment(Pos.CENTER);

        btnActualiar = new Button();
        btnActualiar.setText("Actualizar");
        btnActualiar.setFont(fuente);
        btnActualiar.setTooltip(new Tooltip("Actualizar Cantidad"));
        btnActualiar.setPrefSize(250, 40);
        btnActualiar.setLayoutX(125);
        btnActualiar.setLayoutY(410);
        btnActualiar.setStyle(estilos.getBotones());
        btnActualiar.setOnAction(evt->{
            if (esNumero(tfCantidadNueva.getText())){
                System.out.println("Listo");
            }else{
                tfCantidadNueva.setStyle(estilos.getTextFieldErrorC());
            }
        });
        btnActualiar.setOnMouseEntered(evt->{
            btnActualiar.setStyle(estilos.getBotonesOk());
        });
        btnActualiar.setOnMouseExited(evt->{
            btnActualiar.setStyle(estilos.getBotones());
        });

        getChildren().addAll(lbCantidadAct, tfCantidadAct, lbCantidadNueva, tfCantidadNueva, btnActualiar);
    }

    private boolean esNumero(String texto) {
        if (texto == null || texto.isEmpty()) {
            return false;
        }

        for (int i = 0; i < texto.length(); i++) {
            if (!Character.isDigit(texto.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
