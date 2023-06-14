package com.example.interfaz.vista.paneles.panel_categoria;

import com.example.interfaz.ModeloClases.Categoria;
import com.example.interfaz.ModeloClases.ProveedorTabla;
import com.example.interfaz.controladores.ControladorVCategoria;
import com.example.interfaz.modelo.EstilosComponentes;
import com.example.interfaz.servidor.Cliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
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

public class PanelCategoria extends Pane {

    Label lbClave, lbNombre;
    TextField tfClave, tfNombre;
    Button btnGuardar, btnEliminar, btnEditar;
    EstilosComponentes estilosComponentes;
    TableView tabla;
    Font fuente;
    ScrollPane scrollPane;
    TableColumn<Map, String> columNombre;
    TableColumn<Map, Integer> columClave;
    ControladorVCategoria categoriaControlador;

    public PanelCategoria() {
        setPrefSize(700, 600);
        setBackground(Background.fill(Color.WHITE));
         categoriaControlador = new ControladorVCategoria();
        fuente = new Font("Georgia", 15);
        estilosComponentes = new EstilosComponentes();
        initCompoenent();
        tfClave.setText(String.valueOf(intAleatorio()));
        com.example.interfaz.servidor.Cliente cliente = new Cliente("Hola");
        cliente.Categoria("LLENAR", "HOLA", categoriaControlador, null, null, this);
    }

    private void initCompoenent() {

        tabla = new TableView<>();
        tabla.setMinSize(600, 300);
        tabla.setLayoutX(50);
        tabla.setLayoutY(50);
        tabla.setBackground(Background.fill(Color.WHITE));
        tabla.setBorder(Border.stroke(Color.BLACK));
        columClave = new TableColumn<>("Clave");
        columClave.setCellValueFactory(new MapValueFactory<>("Clave"));
        columClave.setPrefWidth(290);
        columClave.setStyle(estilosComponentes.getColumnStyle());
        columNombre = new TableColumn<>("Nombre");
        columNombre.setCellValueFactory(new MapValueFactory<>("Nombre"));
        columNombre.setPrefWidth(290);
        columNombre.setStyle(estilosComponentes.getColumnStyle());
        tabla.getColumns().addAll(columClave, columNombre);


        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(600, 350);
        scrollPane.setLayoutX(50);
        scrollPane.setLayoutY(50);
        scrollPane.setContent(tabla);
        scrollPane.getStyleClass().clear();

        lbClave = new Label("Clave");
        lbClave.setPrefSize(275, 20);
        lbClave.setLayoutX(50);
        lbClave.setLayoutY(430);
        lbClave.setFont(fuente);

        tfClave = new TextField();
        tfClave.setPromptText("Ingresa la clave");
        tfClave.setPrefSize(275, 40);
        tfClave.setLayoutX(50);
        tfClave.setLayoutY(450);
        tfClave.setFont(fuente);

        lbNombre = new Label("Nombre");
        lbNombre.setPrefSize(275, 20);
        lbNombre.setLayoutX(375);
        lbNombre.setLayoutY(430);
        lbNombre.setFont(fuente);

        tfNombre = new TextField();
        tfNombre.setPromptText("Ingresa el nombre");
        tfNombre.setPrefSize(275, 40);
        tfNombre.setLayoutX(375);
        tfNombre.setLayoutY(450);
        tfNombre.setFont(fuente);

        btnGuardar = new Button("Guardar");
        btnGuardar.setStyle(estilosComponentes.getBotones());
        btnGuardar.setPrefSize(166, 40);
        btnGuardar.setLayoutX(482);
        btnGuardar.setLayoutY(520);
        btnGuardar.setFont(fuente);
        btnGuardar.setOnAction(evt->{
            getDatos();
            tfClave.setText(String.valueOf(intAleatorio()));
            tfNombre.setText("");
        });

        getChildren().addAll(scrollPane, lbClave, tfClave, lbNombre, tfNombre, btnGuardar);
    }

    public void getDatos(){
        if (verificiar()){
            int clave = Integer.parseInt(tfClave.getText());
            String nombre = tfNombre.getText();
            Categoria categoria = new Categoria();
            categoria.setClave(clave);
            categoria.setNombre(nombre);
            categoriaControlador.agregarCategoria(categoria);
            System.out.println(categoriaControlador.categoriatoJSON(categoria));
        }
    }

    private int intAleatorio(){
        int claveA = (int) (Math.random() * 9999999)+1;
        return claveA;
    }

    public boolean verificiar(){
        boolean band = true;
        if (tfClave.getText().strip().length() == 0){
            band = false;
        }
        if (tfNombre.getText().strip().length() != 0){
            band = false;
        }
        return true;
    }

    List<Categoria> lista;
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

    public void listaProveedor(String productos){
        try{
            Type tipoLista = new TypeToken<ArrayList<Categoria>>(){}.getType();
            lista = gson.fromJson(productos, tipoLista);
            llenarTabla();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    private void llenarTabla(){
        var items = FXCollections.<Map<String, Object>>observableArrayList();
        for (Categoria categoria : lista) {
            Map<String, Object> item;
            item = new HashMap<>();
            item.put("Clave", categoria.getClave());
            item.put("Nombre", categoria.getNombre());
            items.add(item);
        }
        tabla.getItems().addAll(items);
    }

}