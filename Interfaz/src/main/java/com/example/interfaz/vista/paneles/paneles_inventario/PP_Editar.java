package com.example.interfaz.vista.paneles.paneles_inventario;

import com.example.interfaz.ModeloClases.Categoria;
import com.example.interfaz.ModeloClases.Productos;
import com.example.interfaz.ModeloClases.Proveedor;
import com.example.interfaz.controladores.ControladorProducto;
import com.example.interfaz.modelo.EstilosComponentes;
import com.example.interfaz.servidor.Cliente;
import com.example.interfaz.vista.emergentes.Alerta;
import com.example.interfaz.vista.emergentes.V_Actualizar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.util.List;

public class PP_Editar extends Pane {

    Label[] labels;
    TextField tfNombre, tfPrecio, tfCantidad, tfClave, tfBuscar;
    ComboBox cbOpciones;
    ComboBox<Proveedor> cbProveedor;
    ComboBox<Categoria> cbCategoria;
    Font fuente;
    Button btnGuuardar, btnBuscar, btnActualizar;
    StringBuilder sb;
    EstilosComponentes estilosComponentes;

    public PP_Editar(){
        estilosComponentes = new EstilosComponentes();
        fuente = new Font("Georgia", 18);
        iniciar();
        Cliente cliente = new Cliente("Hola");
        cliente.Categoria("LISTAEDITAR", "", null, null, this, null);
        Cliente cliente1 = new Cliente("");
        cliente1.proveedor("LISTAEDITAR", "", null, null, this, null);
    }
    private void iniciarLabels(){
        labels = new Label[]{
                new Label(), new Label(), new Label(), new Label(), new Label(), new Label()
        };

        labels[0]= new Label();
        labels[0].setText("Clave");
        labels[0].setLayoutX(100);
        labels[0].setLayoutY(147.5);

        labels[1]= new Label();
        labels[1].setText("Nombre");
        labels[1].setLayoutX(500);
        labels[1].setLayoutY(147.5);

        labels[2]= new Label();
        labels[2].setText("Cantidad");
        labels[2].setLayoutX(100);
        labels[2].setLayoutY(275);

        labels[3]= new Label();
        labels[3].setText("Precio");
        labels[3].setLayoutX(500);
        labels[3].setLayoutY(275);

        labels[4]= new Label();
        labels[4].setText("Proveedor");
        labels[4].setLayoutX(100);
        labels[4].setLayoutY(402.5);

        labels[5]= new Label();
        labels[5].setText("Categoría");
        labels[5].setLayoutX(500);
        labels[5].setLayoutY(402.5);

        for (Label label : labels){
            label.setMinSize(150, 20);
            label.setFont(Font.font("Georgia", 15));
            getChildren().addAll(label);
        }
    }
    private void iniciar() {

        tfBuscar = new TextField();
        tfBuscar.setPromptText("Ingresa la clave del producto");
        tfBuscar.setMinSize(300, 40);
        tfBuscar.getStyleClass().clear();
        tfBuscar.setStyle(estilosComponentes.getTextfield());
        tfBuscar.setPadding(new Insets(0, 10, 0, 10));
        tfBuscar.setFont(fuente);
        tfBuscar.setLayoutX(187.5);
        tfBuscar.setLayoutY(40);

        btnBuscar = new Button();
        btnBuscar.setText("Buscar");
        btnBuscar.setMinSize(200, 30);
        btnBuscar.setFont(fuente);
        btnBuscar.setTooltip(new Tooltip("Buscar Producto"));
        btnBuscar.setLayoutX(522.5);
        btnBuscar.setLayoutY(35);
        btnBuscar.setAlignment(Pos.CENTER);
        btnBuscar.setStyle(estilosComponentes.getBotones());
        btnBuscar.setOnMouseEntered(evt->{
            btnBuscar.setStyle(estilosComponentes.getBotonesOk());
        });
        btnBuscar.setOnMouseExited(evt->{
            btnBuscar.setStyle(estilosComponentes.getBotones());
        });
        btnBuscar.setOnAction(evt->{
            buscarProductos();
        });

        btnActualizar = new Button();
        var urlImg = getClass().getResource("/img/Logos/actualizar.png");
        var imgA = new Image(urlImg.toString(), 24, 24, true, true);
        btnActualizar.setGraphic(new ImageView(imgA));
        btnActualizar.setMinSize(24, 24);
        btnActualizar.setTooltip(new Tooltip("Actualizar la Cantidad"));
        btnActualizar.setFont(fuente);
        btnActualizar.setLayoutX(742.5);
        btnActualizar.setLayoutY(35);
        btnActualizar.setAlignment(Pos.CENTER);
        btnActualizar.setStyle(estilosComponentes.getBotones());
        btnActualizar.setOnMouseEntered(evt->{
            btnActualizar.setStyle(estilosComponentes.getBotonesOk());
        });
        btnActualizar.setOnMouseExited(evt->{
            btnActualizar.setStyle(estilosComponentes.getBotones());
        });
        btnActualizar.setOnAction(evt->{
            V_Actualizar Vent_Actua = new V_Actualizar();
            Vent_Actua.show();
        });

        iniciarLabels();
        tfClave = new TextField();
        tfClave.setPromptText("Clave");
        tfClave.setMinSize(300, 40);
        tfClave.getStyleClass().clear();
        tfClave.setStyle(estilosComponentes.getTextfield());
        tfClave.setPadding(new Insets(0, 10, 0, 10));
        tfClave.setFont(fuente);
        tfClave.setLayoutX(100);
        tfClave.setLayoutY(167.5);
        tfClave.setEditable(false);

        tfNombre = new TextField();
        tfNombre.setPromptText("Nombre");
        tfNombre.setMinSize(300, 40);
        tfNombre.getStyleClass().clear();
        tfNombre.setStyle(estilosComponentes.getTextfield());
        tfNombre.setPadding(new Insets(0, 10, 0, 10));
        tfNombre.setFont(fuente);
        tfNombre.setLayoutX(500);
        tfNombre.setLayoutY(167.5);

        tfPrecio = new TextField();
        tfPrecio.setPromptText("Precio");
        tfPrecio.setMinSize(300, 40);
        tfPrecio.getStyleClass().clear();
        tfPrecio.setStyle(estilosComponentes.getTextfield());
        tfPrecio.setPadding(new Insets(0, 10, 0, 10));
        tfPrecio.setFont(fuente);
        tfPrecio.setLayoutX(500);
        tfPrecio.setLayoutY(295);

        tfCantidad = new TextField();
        tfCantidad.setPromptText("Cantidad");
        tfCantidad.setMinSize(150, 40);
        tfCantidad.setPrefSize(150, 40);
        tfCantidad.getStyleClass().clear();
        tfCantidad.setStyle(estilosComponentes.getTextfield());
        tfCantidad.setPadding(new Insets(0, 10, 0, 10));
        tfCantidad.setFont(fuente);
        tfCantidad.setLayoutX(100);
        tfCantidad.setLayoutY(295);

        cbOpciones = new ComboBox();
        cbOpciones.setMinSize(150, 40);
        cbOpciones.setPadding(new Insets(0, 10, 0, 10));
        cbOpciones.setStyle(estilosComponentes.getComboBox());
        cbOpciones.setLayoutX(270);
        cbOpciones.setLayoutY(295);
        cbOpciones.getItems().addAll("Elegir","Piezas", "Kilos");
        cbOpciones.getSelectionModel().selectFirst();

        cbCategoria = new ComboBox();
        cbCategoria.setMinSize(300, 40);
        cbCategoria.setPadding(new Insets(0, 10, 0, 10));
        cbCategoria.setStyle(estilosComponentes.getComboBox());
        cbCategoria.setLayoutX(500);
        cbCategoria.setLayoutY(432.5);

        cbProveedor = new ComboBox();
        cbProveedor.setMinSize(300, 40);
        cbProveedor.setPadding(new Insets(0, 10, 0, 10));
        cbProveedor.setStyle(estilosComponentes.getComboBox());
        cbProveedor.setLayoutX(100);
        cbProveedor.setLayoutY(432.5);

        btnGuuardar = new Button();
        btnGuuardar.setText("Guardar");
        btnGuuardar.setMinSize(300, 50);
        btnGuuardar.setFont(fuente);
        btnGuuardar.setLayoutX(300);
        btnGuuardar.setAlignment(Pos.CENTER);
        btnGuuardar.setLayoutY(550);
        btnGuuardar.setStyle(estilosComponentes.getBotones());
        btnGuuardar.setOnMouseEntered(evt->{
            btnGuuardar.setStyle(estilosComponentes.getBotonesOk());
        });
        btnGuuardar.setOnMouseExited(evt->{
            btnGuuardar.setStyle(estilosComponentes.getBotones());
        });
        btnGuuardar.setOnAction(evt->{
            guardarDatos();
        });

        getChildren().addAll(tfBuscar, btnBuscar,tfClave,tfNombre, tfCantidad, tfPrecio, cbCategoria, cbProveedor, btnGuuardar, cbOpciones, btnActualizar);
    }
    private boolean esNumero(String texto) {
        if (texto == null || texto.isEmpty()) {
            return false;
        }
        for (int i = 0; i < texto.length(); i++) {
            if (!Character.isDigit(texto.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    Alerta alerta;
    private void guardarDatos(){
        if(verificacion()){
            Productos productos = new Productos();
            productos.setClave(tfClave.getText());
            productos.setNombre(tfNombre.getText());
            productos.setCantidad(Double.parseDouble(tfCantidad.getText()));
            productos.setPrecio(Double.parseDouble(tfPrecio.getText()));
            productos.setTipo(cbOpciones.getSelectionModel().getSelectedItem().toString());
            Proveedor proveedor = cbProveedor.getValue();
            Categoria categoria = cbCategoria.getValue();
            ControladorProducto controladorProducto = new ControladorProducto();
            if (controladorProducto.editarProducto(productos, proveedor, categoria)){
                alerta = new Alerta(Alert.AlertType.CONFIRMATION, "Se ha editado");
                alerta.show();
            }else{
                alerta = new Alerta(Alert.AlertType.ERROR, "No se ha editado");
                alerta.show();
            }
        }else{
            alerta = new Alerta(Alert.AlertType.ERROR, "Llenar todos los campos");
            alerta.show();
        }
    }
    private boolean verificacion(){
        boolean band = true;
        if(tfClave.getText().strip().length() == 0){
            tfClave.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }
        if(tfNombre.getText().strip().length() == 0){
            tfNombre.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }

        if (tfPrecio.getText().strip().length() == 0){
            tfPrecio.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }
        if(esNumero(tfPrecio.getText())){
            tfPrecio.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }

        if (tfCantidad.getText().strip().length() == 0){
            tfCantidad.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }
        if(!esNumero(tfCantidad.getText())){
            tfCantidad.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }

        if (cbOpciones.getSelectionModel().getSelectedIndex() < 0){
            cbOpciones.getSelectionModel().select(0);
            band = false;
        }
        /*
        if (cbCategoria.getSelectionModel().getSelectedIndex() < 0){
            cbCategoria.getSelectionModel().select(0);
            band = false;
        }
        if (cbProveedor.getSelectionModel().getSelectedIndex() < 0){
            cbProveedor.getSelectionModel().select(0);
            band = false;
        }
         */

        return band;
    }

    public void llenarCBProveedor(List<Proveedor> proveedor){
        try{
            if (proveedor!=null){
                for (Proveedor p : proveedor){
                    cbProveedor.getItems().addAll(p);
                }
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void llenarCBCategoria(List<Categoria> lista){
        try{
            if (lista!=null){
                for (Categoria categoria : lista){
                    cbCategoria.getItems().addAll(categoria);
                }
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private void buscarProductos(){
        int clave = Integer.parseInt(tfBuscar.getText());
        ControladorProducto controladorProducto = new ControladorProducto();
        controladorProducto.buscarProductoEditar(clave, this);
    }

    public void lenarCampos(String objetos){
        Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
        String[] JSON = objetos.split("},");
        System.out.println(objetos);
        Productos productoJSON = gson.fromJson(JSON[0], Productos.class);
        Proveedor provedorJSON = gson.fromJson(JSON[1], Proveedor.class);
        Categoria categoriaJSON = gson.fromJson(JSON[2], Categoria.class);
        tfClave.setText(productoJSON.getClave());
        tfNombre.setText(productoJSON.getNombre());
        tfCantidad.setText(String.valueOf(productoJSON.getCantidad()));
        tfPrecio.setText(String.valueOf(productoJSON.getPrecio()));

        if (productoJSON.getTipo().equals("Piezas")){
            cbOpciones.getSelectionModel().select(1);
        }else{
            cbOpciones.getSelectionModel().select(2);
        }

        cbProveedor.getSelectionModel().select(provedorJSON);
        cbCategoria.getSelectionModel().select(categoriaJSON);
    }

}
