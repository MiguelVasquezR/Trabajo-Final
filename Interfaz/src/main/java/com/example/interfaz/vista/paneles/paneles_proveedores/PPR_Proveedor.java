package com.example.interfaz.vista.paneles.paneles_proveedores;

import com.example.interfaz.ModeloClases.Productos;
import com.example.interfaz.ModeloClases.ProveedorTabla;
import com.example.interfaz.modelo.EstilosComponentes;
import com.example.interfaz.servidor.Cliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PPR_Proveedor extends Pane {

    TextField categorias;
    Button btnBuscar;
    TableView tabla;
    EstilosComponentes estilosComponentes;
    TableColumn<Map, Integer> colClave;
    TableColumn<Map, Integer> colNombre;
    TableColumn<Map, Double> colTelefono;
    TableColumn<Map, Double> colCompania;
    ScrollPane barra;

    public PPR_Proveedor(){
        barra = new ScrollPane();
        estilosComponentes = new EstilosComponentes();
        setMinSize(900, 680);
        setBackground(Background.fill(Color.WHITE));
        iniciador();
        Cliente cliente = new com.example.interfaz.servidor.Cliente("");
        cliente.proveedor("LISTAP", "", null, null, null, this);
    }

    private void iniciador(){
        barra.setPrefSize(700, 500);
        barra.setLayoutX(100);
        barra.setLayoutY(90);
        categorias = new TextField();
        categorias.setFont(Font.font("Georgia", 17));
        categorias.setPromptText("Clave del Proveedor");
        categorias.setMinSize(300, 40);
        categorias.setLayoutX(200);
        categorias.setLayoutY(25);
        categorias.setStyle(estilosComponentes.getTextfield());
        btnBuscar = new Button("Buscar");
        btnBuscar.setFont(Font.font("Georgia", 17));
        btnBuscar.setPrefSize(150, 40);
        btnBuscar.setLayoutX(550);
        btnBuscar.setLayoutY(25);
        btnBuscar.setStyle(estilosComponentes.getBotones());
        tabla = new TableView<>();
        tabla.setPrefHeight(500);
        tabla.setBackground(Background.fill(Color.WHITE));
        tabla.setBorder(Border.stroke(Color.BLACK));
        colClave = new TableColumn<>("Clave");
        colClave.setCellValueFactory(new MapValueFactory<>("Clave"));
        colClave.setPrefWidth(150);
        colClave.setStyle(estilosComponentes.getColumnStyle());
        colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new MapValueFactory<>("Nombre"));
        colNombre.setPrefWidth(250);
        colNombre.setStyle(estilosComponentes.getColumnStyle());
        colTelefono = new TableColumn<>("Teléfono");
        colTelefono.setCellValueFactory(new MapValueFactory<>("Teléfono"));
        colTelefono.setPrefWidth(150);
        colTelefono.setStyle(estilosComponentes.getColumnStyle());
        colCompania = new TableColumn<>("Compañía");
        colCompania.setCellValueFactory(new MapValueFactory<>("Compañía"));
        colCompania.setPrefWidth(150);
        colCompania.setStyle(estilosComponentes.getColumnStyle());
        tabla.getColumns().addAll(colClave, colNombre, colTelefono, colCompania);
        barra.setContent(tabla);
        getChildren().addAll(barra);
    }

    List<ProveedorTabla> lista;
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

    public void listaProveedor(String productos){
        try{
            Type tipoLista = new TypeToken<ArrayList<ProveedorTabla>>(){}.getType();
            lista = gson.fromJson(productos, tipoLista);
            llenarTabla();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    private void llenarTabla(){
        var items = FXCollections.<Map<String, Object>>observableArrayList();
        for (ProveedorTabla productos : lista) {
            Map<String, Object> item;
            item = new HashMap<>();
            item.put("Clave", productos.getClave());
            item.put("Nombre", productos.getNombre());
            item.put("Teléfono", productos.getTelefono());
            item.put("Compañía", productos.getCompania());
            items.add(item);
        }
        tabla.getItems().addAll(items);
    }

}
