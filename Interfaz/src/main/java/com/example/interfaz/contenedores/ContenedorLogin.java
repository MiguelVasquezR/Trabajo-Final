package com.example.interfaz.contenedores;

import com.example.interfaz.controladores.Controlador_Ventanas;
import com.example.interfaz.vista.ventanas.Login;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ContenedorLogin extends Stage {
    Controlador_Ventanas cv = new Controlador_Ventanas();
    public ContenedorLogin(){
        Login login = new Login();
        Scene scene = new Scene(login, 1200, 750);
        getIcons().add(cv.getFoto());
        setTitle("La Canasta");
        setOnCloseRequest(evt->{
            cv.cerrar();
            evt.consume();
        });
        setScene(scene);
    }
}
