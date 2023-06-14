package com.example.interfaz;

import com.example.interfaz.controladores.Controlador_Ventanas;
import com.example.interfaz.servidor.Cliente;
import com.example.interfaz.vista.ventanas.Login;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Iniciador extends Application {
    Controlador_Ventanas cv = new Controlador_Ventanas();
    @Override
    public void start(Stage stage) throws IOException {
        Login login = new Login();
        Scene scene = new Scene(login, 1200, 750);
        stage.getIcons().add(cv.getFoto());
        stage.setTitle("La Canasta");
        stage.setScene(scene);
        stage.setOnCloseRequest(evt->{
            cv.cerrar();
            evt.consume();
        });
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}