package com.example.interfaz.controladores;

import com.example.interfaz.ModeloClases.Cliente;
import com.example.interfaz.ModeloClases.Direccion;
import com.example.interfaz.ModeloClases.Persona;
import com.example.interfaz.vista.paneles.paneles_clientes.PC_Editar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ControladorCliete {

    Gson gson;

    public ControladorCliete() { gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    }


    public void llenarLista(){
        try{
            System.out.println("Llenado de lista");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean agregarCliente(Persona persona, Direccion direccion, Cliente cliente){
        try{
            String personaJSON = gson.toJson(persona);
            String direccionJSON = gson.toJson(direccion);
            String clienteJSON = gson.toJson(cliente);
            String objetoJSON = personaJSON + "}," + direccionJSON + "}," + clienteJSON;
            com.example.interfaz.servidor.Cliente clienteS = new com.example.interfaz.servidor.Cliente("Hola");
            clienteS.Cliente("AGREGAR", objetoJSON, null, null);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void editarCliente(Persona persona, Direccion direccion, Cliente cliente){
        try{
            String personaJSON = gson.toJson(persona);
            String direccionJSON = gson.toJson(direccion);
            String clienteJSON = gson.toJson(cliente);
            String objetoJSON = personaJSON + "}," + direccionJSON + "}," + clienteJSON;
            com.example.interfaz.servidor.Cliente clienteS = new com.example.interfaz.servidor.Cliente("Hola");
            clienteS.Cliente("NUEVOSDATOS", objetoJSON, null, null);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void llenarCampos(String datos, PC_Editar puEditar) {
        try {
            String[] ob = datos.split("},");
            Persona persona = gson.fromJson(ob[0], Persona.class);
            Direccion direccion = gson.fromJson(ob[1], Direccion.class);
            Cliente cliente = gson.fromJson(ob[2], Cliente.class);
            puEditar.llenarCampos(persona, direccion, cliente);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
