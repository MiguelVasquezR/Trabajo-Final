package com.example.interfaz.controladores;

import com.example.interfaz.ModeloClases.Usuario;
import com.example.interfaz.contenedores.ContenedorVentana;
import com.example.interfaz.servidor.Cliente;
import com.example.interfaz.vista.emergentes.Alerta;
import com.google.gson.Gson;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControladorLogin {
    Usuario usuario;
    Gson gson;
    Pane pane;
    public ControladorLogin() {
        gson = new Gson();
    }

    Cliente cliente;

    public void enviarUsuario(){
        try{
            String usuarioJSON = gson.toJson(usuario);
            cliente = new Cliente("Hola");
            cliente.usuario(usuarioJSON, this);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setPane(Pane pane){
        this.pane = pane;
    }

    public void entrar(){
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
        ContenedorVentana cv = new ContenedorVentana();
        cv.show();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
