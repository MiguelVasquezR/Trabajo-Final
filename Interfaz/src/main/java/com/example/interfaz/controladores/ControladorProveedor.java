package com.example.interfaz.controladores;

import com.example.interfaz.ModeloClases.Categoria;
import com.example.interfaz.ModeloClases.Direccion;
import com.example.interfaz.ModeloClases.Persona;
import com.example.interfaz.ModeloClases.Proveedor;
import com.example.interfaz.servidor.Cliente;
import com.example.interfaz.vista.paneles.paneles_inventario.PP_Agregar;
import com.example.interfaz.vista.paneles.paneles_inventario.PP_Editar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ControladorProveedor {

    Gson gson;
    List<Proveedor> listaProveedor;
    PP_Agregar ppAgregar;

    public ControladorProveedor() {gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();}

    public boolean agregarProveedor(Persona persona, Direccion direccion, Proveedor proveedor){
        try{
            String personaJSON = gson.toJson(persona);
            String direccionJSON = gson.toJson(direccion);
            String proveedorJSON = gson.toJson(proveedor);
            String OJproveedor = personaJSON + "}," + direccionJSON + "}," + proveedorJSON;
            Cliente cliente = new Cliente("Hola");
            cliente.proveedor("AGREGAR", OJproveedor, null, null, null, null);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean editarProveedor(Persona persona, Direccion direccion, Proveedor proveedor){
        try{
            gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
            String personaJSON = gson.toJson(persona);
            String direccionJSON = gson.toJson(direccion);
            String proveedorJSON = gson.toJson(proveedor);
            String OJproveedor = personaJSON + "}," + direccionJSON + "}," + proveedorJSON;
            Cliente cliente = new Cliente("hola");
            cliente.proveedor("EDITAR", OJproveedor, null, null, null, null);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void listaProveedores(String objetosJSON, PP_Agregar ppAgregar){
        this.ppAgregar = ppAgregar;
        try{
            System.out.println(objetosJSON);
            Type tipoLista = new TypeToken<ArrayList<Proveedor>>(){}.getType();
            listaProveedor = gson.fromJson(objetosJSON, tipoLista);
            ppAgregar.llenarCBProveedor(listaProveedor);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    PP_Editar ppEditar;

    public void listaProveedores(String objetosJSON, PP_Editar ppEditar){
        this.ppEditar = ppEditar;
        try{
            Type tipoLista = new TypeToken<ArrayList<Proveedor>>(){}.getType();
            listaProveedor = gson.fromJson(objetosJSON, tipoLista);
            ppEditar.llenarCBProveedor(listaProveedor);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Proveedor> listaProveedores(){
        if (listaProveedor!=null){
            return listaProveedor;
        }else{
            return null;
        }
    }


}
