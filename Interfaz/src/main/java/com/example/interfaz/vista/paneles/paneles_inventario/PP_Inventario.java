package com.example.interfaz.vista.paneles.paneles_inventario;

import com.example.interfaz.ModeloClases.Productos;
import com.example.interfaz.ModeloClases.Usuario;
import com.example.interfaz.modelo.EstilosComponentes;
import com.example.interfaz.servidor.Cliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PP_Inventario extends Pane {

    ComboBox<String> categorias;
    TableView tabla;
    EstilosComponentes estilosComponentes;
    TableColumn<Map, Integer> colClave;
    TableColumn<Map, Integer> colNombre;
    TableColumn<Map, Double> colCantidad;
    TableColumn<Map, Double> colPrecio;

    public PP_Inventario(){
        estilosComponentes = new EstilosComponentes();
        setMinSize(900, 680);
        setBackground(Background.fill(Color.WHITE));
        iniciador();
        Cliente cliente = new com.example.interfaz.servidor.Cliente("");
        cliente.producto("LISTAP", "", null, this);

    }

    private void iniciador(){
        categorias = new ComboBox<>();
        categorias.setMinSize(300, 40);
        categorias.setLayoutX(300);
        categorias.setLayoutY(25);
        categorias.setStyle(estilosComponentes.getComboBox());
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
        colCantidad = new TableColumn<>("Cantidad");
        colCantidad.setCellValueFactory(new MapValueFactory<>("Cantidad"));
        colCantidad.setPrefWidth(200);
        colCantidad.setStyle(estilosComponentes.getColumnStyle());
        colPrecio = new TableColumn<>("$");
        colPrecio.setCellValueFactory(new MapValueFactory<>("$"));
        colPrecio.setPrefWidth(105);
        colPrecio.setStyle(estilosComponentes.getColumnStyle());
        tabla.getColumns().addAll(colClave, colNombre, colCantidad, colPrecio);

        /*ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();

        Map<String, Object> item1 = new HashMap<>();
        item1.put("Clave", 1);
        item1.put("Nombre", "Daniel");
        item1.put("Cantidad", 20);
        item1.put("Total", 50);
        items.add(item1);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("Clave", 1);
        item2.put("Nombre", "Daniel");
        item2.put("Cantidad", 20);
        item2.put("Total", 50);
        items.add(item2);*/

        getChildren().addAll(tabla);
    }

    List<Productos> lista;
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

    public void listaProductos(String productos){
        try{
            Type tipoLista = new TypeToken<ArrayList<Productos>>(){}.getType();
            lista = gson.fromJson(productos, tipoLista);
            llenarTabla(tabla);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    private void llenarTabla(TableView t){
        var items = FXCollections.<Map<String, Object>>observableArrayList();
        for (Productos productos : lista) {
            Map<String, Object> item;
            item = new HashMap<>();
            item.put("Clave", productos.getClave());
            item.put("Nombre", productos.getNombre());
            item.put("Cantidad", productos.getCantidad());
            item.put("$", productos.getPrecio());
            items.add(item);

            System.out.println("---------------------------------");
            System.out.println(productos.getClave());
            System.out.println(productos.getNombre());
            System.out.println("---------------------------------");
        }
        t.getItems().addAll(items);
    }


}
