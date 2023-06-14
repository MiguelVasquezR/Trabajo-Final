package com.example.interfaz.controladores;

import com.example.interfaz.ModeloClases.Categoria;
import com.example.interfaz.servidor.Cliente;
import com.example.interfaz.vista.paneles.paneles_inventario.PP_Agregar;
import com.example.interfaz.vista.paneles.paneles_inventario.PP_Editar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ControladorVCategoria {

    Gson gson;
    List<Categoria> listaCategoria;

    public ControladorVCategoria() {
        gson = new GsonBuilder().create();
    }

    public String categoriatoJSON(Categoria categoria){
        try{
            String categoriaJson = gson.toJson(categoria);
            return categoriaJson;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Categoria jsontoCategoria(String categoriaJSON){
        try{
            Categoria categoria = gson.fromJson(categoriaJSON, Categoria.class);
            return categoria;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void listaCategorias(String categoriasJSON){
        try{
            Type tipoLista = new TypeToken<ArrayList<Categoria>>(){}.getType();
            listaCategoria = gson.fromJson(categoriasJSON, tipoLista);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Categoria> listaCategorias(){
        if (listaCategoria.size() == 0){
            return null;
        }else{
            return listaCategoria;
        }
    }

    public void agregarCategoria(Categoria categoria) {
        try{
            String datos = gson.toJson(categoria);
            Cliente cliente = new Cliente("Hola");
            cliente.Categoria("AGREGAR", datos, null, null, null, null);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    PP_Agregar ppAgregar;
    List<Categoria> getListaCategoria;

    public void listaProveedores(String objetosJSON, PP_Agregar ppAgregar){
        this.ppAgregar = ppAgregar;
        try{
            Type tipoLista = new TypeToken<ArrayList<Categoria>>(){}.getType();
            getListaCategoria = gson.fromJson(objetosJSON, tipoLista);
            ppAgregar.llenarCBCategoria(getListaCategoria);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void listaProveedores(String objetosJSON, PP_Editar ppEditar){
        try{
            Type tipoLista = new TypeToken<ArrayList<Categoria>>(){}.getType();
            getListaCategoria = gson.fromJson(objetosJSON, tipoLista);
            ppEditar.llenarCBCategoria(getListaCategoria);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Categoria> listaCategoria(){
        if (getListaCategoria!=null){
            return getListaCategoria;
        }else{
            return null;
        }
    }

}
