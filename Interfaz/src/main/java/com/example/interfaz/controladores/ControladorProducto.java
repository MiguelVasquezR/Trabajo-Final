package com.example.interfaz.controladores;

import com.example.interfaz.ModeloClases.*;
import com.example.interfaz.servidor.Cliente;
import com.example.interfaz.vista.paneles.paneles_inventario.PP_Editar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ControladorProducto {

    Gson gson;
    public ControladorProducto() {gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();}

    public boolean agregarProducto(Productos producto, Proveedor proveedor, Categoria categoria){
        try{
            String productoJSON = gson.toJson(producto);
            String proveedorJSON = gson.toJson(proveedor);
            String categoriaJSON = gson.toJson(categoria);
            String objetoCompleto = productoJSON + "}," + proveedorJSON + "}," + categoriaJSON;
            Cliente cliente = new com.example.interfaz.servidor.Cliente("");
            cliente.producto("AGREGAR", objetoCompleto, null, null);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean editarProducto(Productos producto, Proveedor proveedor, Categoria categoria){
        try{
            String productoJSON = gson.toJson(producto);
            String proveedorJSON = gson.toJson(proveedor);
            String categoriaJSON = gson.toJson(categoria);
            String objetoCompleto = productoJSON + "}," + proveedorJSON + "}," + categoriaJSON;
            Cliente cliente = new Cliente("");
            cliente.producto("NUEVOSDATOS", objetoCompleto, null, null);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void convertirObjeto(String objetos){
        try {
            String[] objetoJSON = objetos.split("},");
            Persona persona = gson.fromJson(objetoJSON[0], Persona.class);
            Direccion direccion = gson.fromJson(objetoJSON[1], Direccion.class);
            Usuario usuario = gson.fromJson(objetoJSON[2], Usuario.class);
            System.out.println(persona.getNombre());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public List<Proveedor> listaProveedores(String datos){
        try{
            Type tipoLista = new TypeToken<ArrayList<Proveedor>>(){}.getType();
            List<Proveedor> lista = gson.fromJson(datos, tipoLista);
            return lista;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void buscarProductoEditar(int clave, PP_Editar ppEditar) {
        try{
            Cliente cliente = new Cliente("");
            cliente.producto("BUSCAREDITAR", String.valueOf(clave),   ppEditar, null);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
