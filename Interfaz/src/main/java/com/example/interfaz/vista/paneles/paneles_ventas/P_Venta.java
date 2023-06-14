package com.example.interfaz.vista.paneles.paneles_ventas;

import com.example.interfaz.modelo.EstilosComponentes;
import com.example.interfaz.vista.ventanas.Ventana_Venta;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class P_Venta extends Pane {

    TextField tfAgregar;
    Button btnAgregar, btnPagar;
    TilePane panel_productos;
    Label lbPrecio;
    Font fuente;
    EstilosComponentes estilosComponentes;


    public P_Venta(){
        estilosComponentes = new EstilosComponentes();
        fuente = new Font("Georgia", 18);
        initComponent();
    }

    private void initComponent() {
        tfAgregar = new TextField();
        tfAgregar.setMinSize(300, 40);
        tfAgregar.setLayoutX(260);
        tfAgregar.setLayoutY(30);
        tfAgregar.setAlignment(Pos.CENTER);
        tfAgregar.getStyleClass().clear();
        tfAgregar.setStyle(estilosComponentes.getTextfield());
        tfAgregar.setFont(fuente);
        tfAgregar.setPromptText("Ingresa Código");
        tfAgregar.setTooltip(new Tooltip("Ingresa el Código del Producto"));
        btnAgregar = new Button();
        btnAgregar.setText("Agregar");
        btnAgregar.setMinSize(200, 40);
        btnAgregar.setLayoutX(580);
        btnAgregar.setLayoutY(30);
        btnAgregar.setAlignment(Pos.CENTER);
        btnAgregar.getStyleClass().clear();
        btnAgregar.setStyle(estilosComponentes.getBotones());
        btnAgregar.setOnMouseEntered(evt->{
            btnAgregar.setStyle(estilosComponentes.getBotonesOk());
        });
        btnAgregar.setOnMouseExited(evt->{
            btnAgregar.setStyle(estilosComponentes.getBotones());
        });
        btnAgregar.setFont(fuente);
        btnAgregar.setTooltip(new Tooltip("Agrega Producto a la Ventana"));

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
        btnPagar.setOnAction(evt->{
            Ventana_Venta vv = new Ventana_Venta();
            vv.show();
        });

        btnPagar.setFont(fuente);
        btnPagar.setTooltip(new Tooltip("Abre ventana para Pagar"));
        getChildren().addAll(tfAgregar, btnAgregar, lbPrecio, btnPagar);
    }

    TableView<String> tabla;
    TableColumn<String, Integer> colClave;
    TableColumn<String, Integer> colNombre;
    TableColumn<String, Double> colCantidad;
    TableColumn<String, Double> colPrecio;

    private void iniciador(){
        tabla = new TableView<>();
        tabla.setMinSize(700, 500);
        tabla.setLayoutX(100);
        tabla.setLayoutY(90);
        tabla.setBackground(Background.fill(Color.WHITE));
        tabla.setBorder(Border.stroke(Color.BLACK));
        colClave = new TableColumn<>("Clave");
        colClave.setPrefWidth(150);
        colClave.setStyle(estilosComponentes.getColumnStyle());
        colNombre = new TableColumn<>("Nombre");
        colNombre.setPrefWidth(300);
        colNombre.setStyle(estilosComponentes.getColumnStyle());
        colCantidad = new TableColumn<>("Cantidad");
        colCantidad.setPrefWidth(150);
        colCantidad.setStyle(estilosComponentes.getColumnStyle());
        colPrecio = new TableColumn<>("Total");
        colPrecio.setPrefWidth(105);
        colPrecio.setStyle(estilosComponentes.getColumnStyle());
        tabla.getColumns().addAll(colClave, colNombre, colCantidad, colPrecio);
        getChildren().addAll(tabla);
    }



}
