package com.example.interfaz.vista.ventanas;

import com.example.interfaz.controladores.Controlador_Ventanas;
import com.example.interfaz.vista.emergentes.V_Compra;
import com.example.interfaz.vista.paneles.panel_categoria.PanelCategoria;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Ventana_categoria extends Stage {

    Controlador_Ventanas cv = new Controlador_Ventanas();
    public Ventana_categoria() {
        inicializarComponentes();
    }
    private void inicializarComponentes() {
        PanelCategoria panelCategoria = new PanelCategoria();
        Scene escena = new Scene(panelCategoria,700, 600);
        setScene(escena);
        setTitle("Categoría");
        getIcons().add(cv.getFoto());
        setAlwaysOnTop(true);
        setResizable(false);
    }

}
