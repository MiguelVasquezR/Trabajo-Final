package com.example.interfaz.vista.paneles.paneles_usuarios;

import com.example.interfaz.ModeloClases.Persona;
import com.example.interfaz.ModeloClases.ProveedorTabla;
import com.example.interfaz.ModeloClases.Usuario;
import com.example.interfaz.ModeloClases.UsuarioTabla;
import com.example.interfaz.controladores.ControladorUsuario;
import com.example.interfaz.modelo.EstilosComponentes;
import com.example.interfaz.servidor.Cliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class PU_Usuario extends Pane {

    TextField categorias;
    Button btnBuscar;
    TableView tabla;
    EstilosComponentes estilosComponentes;
    TableColumn<Map, Integer> colClave;
    TableColumn<Map, String> colNombre;
    TableColumn<Map, String> colTelefono;
    TableColumn<Map, String> colUsuario;

    public PU_Usuario(){
        estilosComponentes = new EstilosComponentes();
        setMinSize(900, 680);
        setBackground(Background.fill(Color.WHITE));
        iniciador();
        Cliente cliente = new Cliente("Hola");
        cliente.Usuario("LLENAR", "", null, this);
    }

    private void iniciador(){
        categorias = new TextField();
        categorias.setFont(Font.font("Georgia", 17));
        categorias.setPromptText("Clave del Usuario");
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
        colUsuario = new TableColumn<>("Usuario");
        colUsuario.setCellValueFactory(new MapValueFactory<>("Usuario"));
        colUsuario.setPrefWidth(150);
        colUsuario.setStyle(estilosComponentes.getColumnStyle());
        tabla.getColumns().addAll(colClave, colNombre, colTelefono, colUsuario);
        getChildren().addAll(tabla);
    }

    List<UsuarioTabla> lista;
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

    public void listaProveedor(String productos){
        try{
            Type tipoLista = new TypeToken<ArrayList<UsuarioTabla>>(){}.getType();
            lista = gson.fromJson(productos, tipoLista);
            llenarTabla();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    private void llenarTabla(){
        var items = FXCollections.<Map<String, Object>>observableArrayList();
        for (UsuarioTabla productos : lista) {
            Map<String, Object> item;
            item = new HashMap<>();
            item.put("Clave", productos.getClave());
            item.put("Nombre", productos.getNombre());
            item.put("Teléfono", productos.getTelefono());
            item.put("Usuario", productos.getUsuario());
            items.add(item);
        }
        tabla.getItems().addAll(items);
    }
}
