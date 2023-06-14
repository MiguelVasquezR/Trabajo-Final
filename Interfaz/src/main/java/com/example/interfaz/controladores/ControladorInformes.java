package com.example.interfaz.controladores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

public class ControladorInformes {

    Gson gson;
    String fechas;

    public ControladorInformes(){gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();}

    public void llenarReporte(Date inicio, Date fin){
        try{
            String inicioJSON = gson.toJson(inicio);
            String finJSON = gson.toJson(fin);
            fechas = inicioJSON+","+finJSON;
            System.out.println(fechas);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String sendDates(){
        return fechas;
    }




}
