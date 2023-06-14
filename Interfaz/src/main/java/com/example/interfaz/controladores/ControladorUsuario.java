package com.example.interfaz.controladores;

import com.example.interfaz.ModeloClases.Categoria;
import com.example.interfaz.ModeloClases.Direccion;
import com.example.interfaz.ModeloClases.Persona;
import com.example.interfaz.ModeloClases.Usuario;
import com.example.interfaz.servidor.Cliente;
import com.example.interfaz.vista.paneles.paneles_usuarios.PU_Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ControladorUsuario {

    Gson gson;
    public ControladorUsuario() {gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();}

    public boolean agregarUsuario(Persona persona, Direccion direccion, Usuario usuario){
        try{
            String personaJSON = gson.toJson(persona);
            String direccionJSON = gson.toJson(direccion);
            String usuarioJSON = gson.toJson(usuario);
            String OJCompleto = personaJSON + "}," + direccionJSON + "}," + usuarioJSON;
            Cliente cliente = new Cliente("Hola");
            cliente.Usuario("AGREGAR", OJCompleto, null, null);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean editarUsuario(Persona persona, Direccion direccion, Usuario usuario){
        try{
            String personaJSON = gson.toJson(persona);
            String direccionJSON = gson.toJson(direccion);
            String usuarioJSON = gson.toJson(usuario);
            String OJCompleto = personaJSON + "}," + direccionJSON + "}," + usuarioJSON;
            Cliente cliente = new Cliente("");
            cliente.Usuario("EDITAR", OJCompleto, null, null);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    List<Usuario> listaUsuarios = new ArrayList<>();
    PU_Usuario USUARIOS;
    public void llenarTabla(String listaJSON, PU_Usuario usuario) {
        this.USUARIOS = usuario;
        try{
            Type tipoLista = new TypeToken<ArrayList<Usuario>>(){}.getType();
            listaUsuarios = gson.fromJson(listaJSON, tipoLista);
            llenarTabla();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void llenarTabla(){
        List<Usuario> listaUsuario = listaUsuarios;
        ObservableList<Usuario> lista = FXCollections.emptyObservableList();
        for (Usuario usario : listaUsuario){
            lista.add(usario);
        }

    }

}
