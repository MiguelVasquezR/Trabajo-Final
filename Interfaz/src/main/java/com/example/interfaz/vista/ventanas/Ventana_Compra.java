package com.example.interfaz.vista.ventanas;

import com.example.interfaz.controladores.Controlador_Ventanas;
import com.example.interfaz.vista.emergentes.V_Compra;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Ventana_Compra extends Stage {
    Controlador_Ventanas cv = new Controlador_Ventanas();
    public Ventana_Compra() {
        inicializarComponentes();
    }
    private void inicializarComponentes() {
        V_Compra VC = new V_Compra();
        Scene escena = new Scene(VC,900, 600);
        setScene(escena);
        setTitle("Cobro");
        getIcons().add(cv.getFoto());
        setAlwaysOnTop(true);
        setResizable(false);
    }
}
