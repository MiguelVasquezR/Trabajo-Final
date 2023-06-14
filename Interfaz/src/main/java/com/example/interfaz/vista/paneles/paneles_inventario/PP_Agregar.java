package com.example.interfaz.vista.paneles.paneles_inventario;

import com.example.interfaz.ModeloClases.Categoria;
import com.example.interfaz.ModeloClases.Productos;
import com.example.interfaz.ModeloClases.Proveedor;
import com.example.interfaz.controladores.ControladorProducto;
import com.example.interfaz.modelo.EstilosComponentes;
import com.example.interfaz.servidor.Cliente;
import com.example.interfaz.vista.emergentes.Alerta;
import com.example.interfaz.vista.ventanas.Ventana_categoria;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.util.List;

public class PP_Agregar extends Pane {

    Label[] labels;
    TextField tfNombre, tfPrecio, tfCantidad, tfClave;
    ComboBox<Categoria> cbCategoria;
    ComboBox<Proveedor> cbProveedor;
    ComboBox<String> cbOpciones;
    Font fuente;
    Button btnGuuardar;
    StringBuilder sb;
    EstilosComponentes estilosComponentes;

    public PP_Agregar(){
        estilosComponentes = new EstilosComponentes();
        fuente = new Font("Georgia", 18);
        iniciar();
        Cliente cliente = new Cliente("Hola");
        cliente.Categoria("LISTA", "", null, this, null, null);
        Cliente cliente1 = new Cliente("");
        cliente1.proveedor("LISTA", "", null, this, null, null);
    }

    private void iniciarLabels(){
        labels = new Label[]{
                new Label(), new Label(), new Label(), new Label(), new Label(), new Label()
        };

        labels[0]= new Label();
        labels[0].setText("Clave");
        labels[0].setLayoutX(100);
        labels[0].setLayoutY(50);

        labels[1]= new Label();
        labels[1].setText("Nombre");
        labels[1].setLayoutX(500);
        labels[1].setLayoutY(50);

        labels[2]= new Label();
        labels[2].setText("Cantidad");
        labels[2].setLayoutX(100);
        labels[2].setLayoutY(205);

        labels[3]= new Label();
        labels[3].setText("Precio");
        labels[3].setLayoutX(500);
        labels[3].setLayoutY(205);

        labels[4]= new Label();
        labels[4].setText("Proveedor");
        labels[4].setLayoutX(100);
        labels[4].setLayoutY(375);

        labels[5]= new Label();
        labels[5].setText("Categoría");
        labels[5].setLayoutX(500);
        labels[5].setLayoutY(375);

        for (Label label : labels){
            label.setMinSize(150, 20);
            label.setFont(Font.font("Georgia", 15));
            getChildren().addAll(label);
        }
    }

    private void iniciar() {

        iniciarLabels();
        tfClave = new TextField();
        tfClave.setPromptText("Ingresa la clave");
        tfClave.setMinSize(300, 40);
        tfClave.getStyleClass().clear();
        tfClave.setStyle(estilosComponentes.getTextfield());
        tfClave.setPadding(new Insets(0, 10, 0, 10));
        tfClave.setFont(fuente);
        tfClave.setLayoutX(100);
        tfClave.setLayoutY(80);
        int claveA = generarClaveAle();
        tfClave.setText(String.valueOf(claveA));
        tfClave.setEditable(false);

        tfNombre = new TextField();
        tfNombre.setPromptText("Ingresa el nombre");
        tfNombre.setMinSize(300, 40);
        tfNombre.getStyleClass().clear();
        tfNombre.setStyle(estilosComponentes.getTextfield());
        tfNombre.setPadding(new Insets(0, 10, 0, 10));
        tfNombre.setFont(fuente);
        tfNombre.setLayoutX(500);
        tfNombre.setLayoutY(80);

        tfPrecio = new TextField();
        tfPrecio.setPromptText("Ingresa el Precio");
        tfPrecio.setMinSize(300, 40);
        tfPrecio.getStyleClass().clear();
        tfPrecio.setStyle(estilosComponentes.getTextfield());
        tfPrecio.setPadding(new Insets(0, 10, 0, 10));
        tfPrecio.setFont(fuente);
        tfPrecio.setLayoutX(500);
        tfPrecio.setLayoutY(235);

        tfCantidad = new TextField();
        tfCantidad.setPromptText("Cantidad");
        tfCantidad.setMinSize(150, 40);
        tfCantidad.setPrefSize(150, 40);
        tfCantidad.getStyleClass().clear();
        tfCantidad.setStyle(estilosComponentes.getTextfield());
        tfCantidad.setPadding(new Insets(0, 10, 0, 10));
        tfCantidad.setFont(fuente);
        tfCantidad.setLayoutX(100);
        tfCantidad.setLayoutY(235);

        cbOpciones = new ComboBox();
        cbOpciones.setMinSize(150, 40);
        cbOpciones.setPadding(new Insets(0, 10, 0, 10));
        cbOpciones.setStyle(estilosComponentes.getComboBox());
        cbOpciones.setLayoutX(270);
        cbOpciones.setLayoutY(235);
        cbOpciones.getItems().addAll("Elegir","Piezas", "Kilos");
        cbOpciones.getSelectionModel().selectFirst();
        cbOpciones.setOnMouseClicked(evt->{
            cbOpciones.setStyle(estilosComponentes.getComboBox());
        });

        cbCategoria = new ComboBox();
        cbCategoria.setMinSize(300, 40);
        cbCategoria.setPadding(new Insets(0, 10, 0, 10));
        cbCategoria.setStyle(estilosComponentes.getComboBox());
        cbCategoria.setLayoutX(500);
        cbCategoria.setLayoutY(405);

        Button btnAgregarCat = new Button();
        btnAgregarCat.setMinSize(40, 40);
        btnAgregarCat.setPadding(new Insets(0, 10, 0, 10));
        btnAgregarCat.setStyle(estilosComponentes.getBotones());
        btnAgregarCat.setLayoutX(820);
        btnAgregarCat.setLayoutY(405);
        btnAgregarCat.setTooltip(new Tooltip("Agregar Categoria"));
        var urlImg = getClass().getResource("/img/Logos/AgregarCat.png");
        var imgA = new Image(urlImg.toString(), 32, 32, true, true);
        btnAgregarCat.setGraphic(new ImageView(imgA));
        btnAgregarCat.setOnAction(evt->{
            Ventana_categoria ventanaCategoria = new Ventana_categoria();
            ventanaCategoria.show();
        });

        cbProveedor = new ComboBox();
        cbProveedor.setMinSize(300, 40);
        cbProveedor.setPadding(new Insets(0, 10, 0, 10));
        cbProveedor.setStyle(estilosComponentes.getComboBox());
        cbProveedor.setLayoutX(100);
        cbProveedor.setLayoutY(405);

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

        getChildren().addAll(tfClave,tfNombre, tfCantidad, tfPrecio, cbCategoria, btnAgregarCat, cbProveedor, btnGuuardar, cbOpciones);
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
            if (controladorProducto.agregarProducto(productos, proveedor, categoria)){
                limpiarCampos();
                alerta = new Alerta(Alert.AlertType.CONFIRMATION, "Se ha guardado");
                alerta.show();
            }else{
                alerta = new Alerta(Alert.AlertType.ERROR, "No se guardo");
                alerta.show();
            }
        }else{
            Alerta alerta = new Alerta(Alert.AlertType.ERROR, "Llenar todos los campos");
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

        if (cbOpciones.getSelectionModel().getSelectedIndex() <= 0){
            cbOpciones.getSelectionModel().select(0);
            cbOpciones.setStyle(estilosComponentes.getComboBoxE());
            band = false;
        }

        if (cbCategoria.getSelectionModel().getSelectedIndex() < 0){
            cbCategoria.getSelectionModel().select(0);
            cbCategoria.setStyle(estilosComponentes.getComboBoxE());
            band = false;
        }
        if (cbProveedor.getSelectionModel().getSelectedIndex() < 0){
            cbProveedor.getSelectionModel().select(0);
            cbProveedor.setStyle(estilosComponentes.getComboBoxE());
            band = false;
        }

        return band;
    }

    private void limpiarCampos(){
        int clave = generarClaveAle();
        tfClave.setText(String.valueOf(clave));
        tfNombre.setText("");
        tfCantidad.setText("");
        tfPrecio.setText("");
        cbOpciones.getSelectionModel().selectFirst();
        cbProveedor.getSelectionModel().selectFirst();
        cbCategoria.getSelectionModel().selectFirst();
    }

    private int generarClaveAle(){
        int claveAl = (int) (Math.random() * 999999999) + 1;
        return claveAl;
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

}
