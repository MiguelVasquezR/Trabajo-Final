package com.example.interfaz.contenedores;

import com.example.interfaz.controladores.Controlador_Ventanas;
import com.example.interfaz.vista.ventanas.Ventana_Inicial;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ContenedorVentana extends Stage {
    Controlador_Ventanas cv = new Controlador_Ventanas();
    public ContenedorVentana(){
        Ventana_Inicial vi = new Ventana_Inicial();
        Scene scene = new Scene(vi, 1200, 750);
        getIcons().add(cv.getFoto());
        setOnCloseRequest(evt->{
            cv.cerrar();
            evt.consume();
        });
        setTitle("La Canasta");
        setScene(scene);
    }

}
