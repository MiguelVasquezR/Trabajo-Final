package com.example.interfaz.vista.paneles.paneles_ventas;

import com.example.interfaz.ModeloClases.Productos;
import com.example.interfaz.modelo.EstilosComponentes;
import com.example.interfaz.vista.ventanas.Ventana_Compra;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.HashMap;
import java.util.Map;

public class P_Compra extends Pane {

    ComboBox <String> cbProvedor;
    ComboBox<String> cbProducto;
    Button btnPagar, btnAgregar;
    Label lbPrecio;
    Font fuente;
    EstilosComponentes estilosComponentes;
    TableView tabla;
    TableColumn<Map, Integer> colClave;
    TableColumn<Map, Integer> colNombre;
    TableColumn<Map, Double> colCantidad;
    TableColumn<Map, Double> colPrecio;

    public P_Compra(){
        estilosComponentes = new EstilosComponentes();
        fuente = new Font("Georgia", 18);
        initComponent();
    }

    private void initComponent() {

        cbProvedor = new ComboBox<>();
        cbProvedor.setMinSize(250, 40);
        cbProvedor.setLayoutX(150);
        cbProvedor.setLayoutY(30);
        cbProvedor.setStyle(estilosComponentes.getComboBox());

        cbProducto = new ComboBox<>();
        cbProducto.setMinSize(250, 40);
        cbProducto.setLayoutX(450);
        cbProducto.setLayoutY(30);
        cbProducto.setStyle(estilosComponentes.getComboBox());

        var URLCar = getClass().getResource("/img/Logos/Carrito.png");
        var imgCar = new Image(URLCar.toString(),32, 32, true, true);
        btnAgregar = new Button();
        btnAgregar.setGraphic(new ImageView(imgCar));
        btnAgregar.setPrefSize(50, 40);
        btnAgregar.setLayoutX(750);
        btnAgregar.setTooltip(new Tooltip("Agregar Producto"));
        btnAgregar.setLayoutY(30);
        btnAgregar.setStyle(estilosComponentes.getBotones());

        iniciador();

        lbPrecio = new Label();
        lbPrecio.setText("Total: $ 0.00");
        lbPrecio.setFont(fuente);
        lbPrecio.setMinSize(250, 40);
        lbPrecio.setLayoutX(200);
        lbPrecio.setLayoutY(610);
        lbPrecio.setAlignment(Pos.CENTER);

        btnPagar = new Button();
        btnPagar.setText("Pagar");
        btnPagar.setMinSize(200, 40);
        btnPagar.setLayoutX(500);
        btnPagar.setLayoutY(610);
        btnPagar.setAlignment(Pos.CENTER);
        btnPagar.getStyleClass().clear();
        btnPagar.setStyle(estilosComponentes.getBotones());
        btnPagar.setOnMouseEntered(evt->{
            btnPagar.setStyle(estilosComponentes.getBotonesOk());
        });
        btnPagar.setOnMouseExited(evt->{
            btnPagar.setStyle(estilosComponentes.getBotones());
        });
        btnPagar.setFont(fuente);
        btnPagar.setTooltip(new Tooltip("Abre ventana para Pagar"));
        btnPagar.setOnAction(evt->{
            Ventana_Compra vc = new Ventana_Compra();
            vc.show();
        });
        getChildren().addAll(cbProvedor, cbProducto, lbPrecio, btnPagar, btnAgregar);
    }


    private void iniciador(){
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
        colNombre.setPrefWidth(300);
        colNombre.setStyle(estilosComponentes.getColumnStyle());
        colCantidad = new TableColumn<>("Cantidad");
        colCantidad.setCellValueFactory(new MapValueFactory<>("Cantidad"));
        colCantidad.setPrefWidth(150);
        colCantidad.setStyle(estilosComponentes.getColumnStyle());
        colPrecio = new TableColumn<>("Total");
        colPrecio.setCellValueFactory(new MapValueFactory<>("Total"));
        colPrecio.setPrefWidth(105);
        colPrecio.setStyle(estilosComponentes.getColumnStyle());
        tabla.getColumns().addAll(colClave, colNombre, colCantidad, colPrecio);

        ObservableList<Map<String, Object>> items = FXCollections.<Map<String, Object>>observableArrayList();

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
        items.add(item2);



        tabla.getItems().addAll(items);
        getChildren().addAll(tabla);
    }

}
