package com.example.interfaz.vista.ventanas;

import com.example.interfaz.controladores.Controlador_Ventanas;
import com.example.interfaz.vista.emergentes.V_Password;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Ventana_Password extends Stage {
    Controlador_Ventanas cv = new Controlador_Ventanas();
    public Ventana_Password() {
        inicializarComponentes();
    }
    private void inicializarComponentes() {
        V_Password root = new V_Password();
        Scene escena = new Scene(root,500, 500);
        setScene(escena);
        setTitle("Cobro");
        getIcons().add(cv.getFoto());
        setResizable(false);
        setAlwaysOnTop(true);
    }
}
