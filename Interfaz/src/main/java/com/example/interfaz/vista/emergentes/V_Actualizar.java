package com.example.interfaz.vista.emergentes;

import com.example.interfaz.vista.paneles.paneles_inventario.PP_actualizar;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class V_Actualizar extends Stage {
    public V_Actualizar(){
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        PP_actualizar pa = new PP_actualizar();
        Scene escena = new Scene(pa,500, 500);
        setScene(escena);
        var url = getClass().getResource("/img/Logo.png");
        var img = new Image(url.toString());
        getIcons().add(img);
        setTitle("Cobro");
        setResizable(false);
        setAlwaysOnTop(true);
    }

}
