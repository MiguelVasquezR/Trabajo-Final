package com.example.interfaz.vista.paneles.paneles_clientes;

import com.example.interfaz.ModeloClases.ClienteTabla;
import com.example.interfaz.ModeloClases.Productos;
import com.example.interfaz.controladores.ControladorCliete;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PC_Clientes extends Pane {

    TextField categorias;
    Button btnBuscar;
    TableView tabla;
    EstilosComponentes estilosComponentes;
    TableColumn<Map, Integer> colClave;
    TableColumn<Map, String> colNombre;
    TableColumn<Map, Integer> colTelefono;
    TableColumn<Map, Double> colEmpresa;
    TableColumn<Map, String> colTamano;

    ControladorCliete controladorCliete;

    public PC_Clientes(){
        estilosComponentes = new EstilosComponentes();
        setMinSize(900, 680);
        setBackground(Background.fill(Color.WHITE));
        iniciador();
        Cliente cliente = new com.example.interfaz.servidor.Cliente("");
        cliente.Cliente("LISTAP", "", null, this);
    }

    private void iniciador(){
        categorias = new TextField();
        categorias.setFont(Font.font("Georgia", 17));
        categorias.setPromptText("Clave del Cliente");
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
        tabla.setMinSize(700, 500);
        tabla.setLayoutX(100);
        tabla.setLayoutY(90);
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
        colEmpresa = new TableColumn<>("Empresa");
        colEmpresa.setCellValueFactory(new MapValueFactory<>("Empresa"));
        colEmpresa.setPrefWidth(100);
        colEmpresa.setStyle(estilosComponentes.getColumnStyle());
        colTamano = new TableColumn<>("Tamaño");
        colTamano.setCellValueFactory(new MapValueFactory<>("Tamaño"));
        colTamano.setPrefWidth(100);
        colTamano.setStyle(estilosComponentes.getColumnStyle());
        tabla.getColumns().addAll(colClave, colNombre, colTelefono, colEmpresa, colTamano);


        getChildren().addAll(tabla);
    }

    List<ClienteTabla> lista;
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

    public void listaCliente(String productos){
        try{
            Type tipoLista = new TypeToken<ArrayList<ClienteTabla>>(){}.getType();
            lista = gson.fromJson(productos, tipoLista);
            llenarTabla();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    private void llenarTabla() {
        var items = FXCollections.<Map<String, Object>>observableArrayList();
        for (ClienteTabla productos : lista) {
            Map<String, Object> item;
            item = new HashMap<>();
            item.put("Clave", productos.getClave());
            item.put("Nombre", productos.getNombre());
            item.put("Teléfono", productos.getTelefono());
            item.put("Empresa", productos.getEmpresa());
            item.put("Tamaño", productos.getTamano());
            items.add(item);
        }
        tabla.getItems().addAll(items);
    }

}
