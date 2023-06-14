package com.example.interfaz.vista.ventanas;

import com.example.interfaz.controladores.Controlador_Ventanas;
import com.example.interfaz.vista.emergentes.V_Venta;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Ventana_Venta extends Stage {
    Controlador_Ventanas cv = new Controlador_Ventanas();
    public Ventana_Venta(){
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        V_Venta root = new V_Venta();
        Scene escena = new Scene(root,500, 500);
        setScene(escena);
        getIcons().add(cv.getFoto());
        setTitle("Cobro");
        setResizable(false);
        setAlwaysOnTop(true);
    }

}
