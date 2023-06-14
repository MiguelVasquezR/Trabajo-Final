package com.example.interfaz.vista.paneles.paneles_clientes;

import com.example.interfaz.ModeloClases.Cliente;
import com.example.interfaz.ModeloClases.Direccion;
import com.example.interfaz.ModeloClases.Persona;
import com.example.interfaz.controladores.ControladorCliete;
import com.example.interfaz.modelo.EstilosComponentes;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.IllegalFormatCodePointException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PC_Editar extends BorderPane{
    Label[] labels;
    TextField tfClave, tfNombre, tfApellido, tfCorreo, tfNúmero, tfCalle, tfColonia, tfNumeroDir, tfCiudad, tfNomEmpresa, tfBuscar;
    ComboBox<String> cbTamano;
    DatePicker fc_nac;
    Font fuente;
    Button btnGuuardar, btnBuscar;
    EstilosComponentes estilosComponentes;
    Pane p, p1;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[com]+$";

    public PC_Editar(){
        estilosComponentes = new EstilosComponentes();
        fuente = new Font("Georgia", 18);
        setMinSize(900, 1400);
        setTop(barraSuperior());
        setCenter(iniciar());
    }
    private Pane barraSuperior(){
        p1 = new Pane();
        p1.setMinSize(900, 100);
        tfBuscar = new TextField();
        tfBuscar.setPromptText("Nombre o Clave del Cliente");
        tfBuscar.getStyleClass().clear();
        tfBuscar.setStyle(estilosComponentes.getTextfield());
        tfBuscar.setMinSize(300, 35);
        tfBuscar.setFont(Font.font("Georgia", 18));
        tfBuscar.setLayoutX(175);
        tfBuscar.setLayoutY(30);

        btnBuscar = new Button();
        btnBuscar.setText("Buscar");
        btnBuscar.getStyleClass().clear();
        btnBuscar.setStyle(estilosComponentes.getBotones());
        btnBuscar.setAlignment(Pos.CENTER);
        btnBuscar.setMinSize(200, 35);
        btnBuscar.setLayoutX(525);
        btnBuscar.setLayoutY(30);
        btnBuscar.setFont(Font.font("Georgia", 18));
        btnBuscar.setOnMouseEntered(evt->{
            btnBuscar.setStyle(estilosComponentes.getBotonesOk());
        });
        btnBuscar.setOnMouseExited(evt->{
            btnBuscar.setStyle(estilosComponentes.getBotones());
        });
        btnBuscar.setOnAction(evt->{
            buscar();
        });
        p1.getChildren().addAll(tfBuscar, btnBuscar);
        return p1;
    }
    private void iniciarLabels(){
        labels = new Label[]{
                new Label(), new Label(), new Label(), new Label(), new Label(), new Label(),
                new Label(), new Label(), new Label(), new Label(), new Label(), new Label(),
                new Label(), new Label(), new Label(), new Label(), new Label(), new Label()
        };

        labels[0].setText("Clave");
        labels[0].setLayoutX(100);
        labels[0].setLayoutY(130);

        labels[1].setText("Fecha Nacimiento");
        labels[1].setLayoutX(500);
        labels[1].setLayoutY(130);

        labels[2].setText("Nombre");
        labels[2].setLayoutX(100);
        labels[2].setLayoutY(240);

        labels[3].setText("Apellido");
        labels[3].setLayoutX(500);
        labels[3].setLayoutY(240);

        labels[4].setText("Correo Electrónico");
        labels[4].setLayoutX(500);
        labels[4].setLayoutY(340);

        labels[5].setText("Teléfono");
        labels[5].setLayoutX(100);
        labels[5].setLayoutY(340);

        labels[6].setText("Datos Dirección");
        labels[6].setLayoutX(150);
        labels[6].setLayoutY(518);

        labels[7].setText("Calle");
        labels[7].setLayoutX(100);
        labels[7].setLayoutY(610);

        labels[8].setText("Número");
        labels[8].setLayoutX(500);
        labels[8].setLayoutY(610);

        labels[9].setText("Colonia");
        labels[9].setLayoutX(100);
        labels[9].setLayoutY(710);

        labels[10].setText("Ciudad");
        labels[10].setLayoutX(500);
        labels[10].setLayoutY(710);

        labels[11].setText("Datos Empresa");
        labels[11].setLayoutX(150);
        labels[11].setLayoutY(890);

        labels[12].setText("Nombre Empresa");
        labels[12].setLayoutX(100);
        labels[12].setLayoutY(976);

        labels[13].setText("Tamaño Empresa");
        labels[13].setLayoutX(500);
        labels[13].setLayoutY(976);

        labels[14].setText("Datos Personales");
        labels[14].setLayoutX(150);
        labels[14].setLayoutY(40);

        var url1 = getClass().getResource("/img/logosPaneles/datos.png");
        var img1 = new Image(url1.toString(), 40, 40, true, true);
        labels[15].setGraphic(new ImageView(img1));
        labels[15].setLayoutX(100);
        labels[15].setLayoutY(40);
        labels[15].setMinSize(40, 40);

        var url2 = getClass().getResource("/img/logosPaneles/ubi.png");
        var img2 = new Image(url2.toString(), 40, 40, true, true);
        labels[16].setGraphic(new ImageView(img2));
        labels[16].setLayoutX(100);
        labels[16].setLayoutY(518);
        labels[16].setMinSize(40, 40);

        var url3 = getClass().getResource("/img/logosPaneles/compañia.png");
        var img3 = new Image(url3.toString(), 40, 40, true, true);
        labels[17].setGraphic(new ImageView(img3));
        labels[17].setLayoutX(100);
        labels[17].setLayoutY(890);
        labels[17].setMinSize(40, 40);


        for (Label label : labels){
            label.setMinSize(150, 40);
            label.setFont(fuente);
            label.setAlignment(Pos.CENTER_LEFT);
            p.getChildren().addAll(label);
        }

    }
    private Pane iniciar() {
        p = new Pane();
        iniciarLabels();
        datosPer();
        datosDir();
        datosUsu();

        btnGuuardar = new Button();
        btnGuuardar.setText("Guardar");
        btnGuuardar.setMinSize(300, 50);
        btnGuuardar.setFont(fuente);
        btnGuuardar.setLayoutX(300);
        btnGuuardar.setAlignment(Pos.CENTER);
        btnGuuardar.setLayoutY(1166);
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
        p.getChildren().add(btnGuuardar);
        return p;
    }
    private void datosPer(){
        tfClave = new TextField();
        tfClave.setPromptText("Clave");
        tfClave.setMinSize(300, 40);
        tfClave.getStyleClass().clear();
        tfClave.setStyle(estilosComponentes.getTextfield());
        tfClave.setPadding(new Insets(0, 10, 0, 10));
        tfClave.setFont(fuente);
        tfClave.setLayoutX(100);
        tfClave.setLayoutY(180);

        fc_nac = new DatePicker();
        fc_nac.setMinSize(300, 40);
        fc_nac.setPadding(new Insets(0, 10, 0, 10));
        fc_nac.setLayoutX(500);
        fc_nac.setLayoutY(180);
        fc_nac.setStyle(estilosComponentes.getDatePickerCampos());

        tfNombre = new TextField();
        tfNombre.setPromptText("Nombre");
        tfNombre.setMinSize(300, 40);
        tfNombre.getStyleClass().clear();
        tfNombre.setStyle(estilosComponentes.getTextfield());
        tfNombre.setPadding(new Insets(0, 10, 0, 10));
        tfNombre.setFont(fuente);
        tfNombre.setLayoutX(100);
        tfNombre.setLayoutY(290);

        tfApellido = new TextField();
        tfApellido.setPromptText("Apellido");
        tfApellido.setMinSize(300, 40);
        tfApellido.getStyleClass().clear();
        tfApellido.setStyle(estilosComponentes.getTextfield());
        tfApellido.setPadding(new Insets(0, 10, 0, 10));
        tfApellido.setFont(fuente);
        tfApellido.setLayoutX(500);
        tfApellido.setLayoutY(290);

        tfCorreo = new TextField();
        tfCorreo.setPromptText("Correo");
        tfCorreo.setMinSize(300, 40);
        tfCorreo.getStyleClass().clear();
        tfCorreo.setStyle(estilosComponentes.getTextfield());
        tfCorreo.setPadding(new Insets(0, 10, 0, 10));
        tfCorreo.setFont(fuente);
        tfCorreo.setLayoutX(500);
        tfCorreo.setLayoutY(390);

        tfNúmero = new TextField();
        tfNúmero.setPromptText("Número Teléfono");
        tfNúmero.setMinSize(300, 40);
        tfNúmero.getStyleClass().clear();
        tfNúmero.setStyle(estilosComponentes.getTextfield());
        tfNúmero.setPadding(new Insets(0, 10, 0, 10));
        tfNúmero.setFont(fuente);
        tfNúmero.setLayoutX(100);
        tfNúmero.setLayoutY(390);

        p.getChildren().addAll(tfClave, fc_nac ,tfNombre, tfApellido, tfNúmero, tfCorreo);
    }
    private void datosDir(){
        tfCalle = new TextField();
        tfCalle.setPromptText("Calle");
        tfCalle.setMinSize(300, 40);
        tfCalle.getStyleClass().clear();
        tfCalle.setStyle(estilosComponentes.getTextfield());
        tfCalle.setPadding(new Insets(0, 10, 0, 10));
        tfCalle.setFont(fuente);
        tfCalle.setLayoutX(100);
        tfCalle.setLayoutY(660);

        tfColonia = new TextField();
        tfColonia.setPromptText("Colonia");
        tfColonia.setMinSize(300, 40);
        tfColonia.getStyleClass().clear();
        tfColonia.setStyle(estilosComponentes.getTextfield());
        tfColonia.setPadding(new Insets(0, 10, 0, 10));
        tfColonia.setFont(fuente);
        tfColonia.setLayoutX(100);
        tfColonia.setLayoutY(760);

        tfCiudad = new TextField();
        tfCiudad.setPromptText("Ciudad");
        tfCiudad.setMinSize(300, 40);
        tfCiudad.getStyleClass().clear();
        tfCiudad.setStyle(estilosComponentes.getTextfield());
        tfCiudad.setPadding(new Insets(0, 10, 0, 10));
        tfCiudad.setFont(fuente);
        tfCiudad.setLayoutX(500);
        tfCiudad.setLayoutY(760);

        tfNumeroDir = new TextField();
        tfNumeroDir.setPromptText("Número Dirección");
        tfNumeroDir.setMinSize(300, 40);
        tfNumeroDir.getStyleClass().clear();
        tfNumeroDir.setStyle(estilosComponentes.getTextfield());
        tfNumeroDir.setPadding(new Insets(0, 10, 0, 10));
        tfNumeroDir.setFont(fuente);
        tfNumeroDir.setLayoutX(500);
        tfNumeroDir.setLayoutY(660);

        p.getChildren().addAll(tfCalle, tfNumeroDir, tfColonia, tfCiudad);
    }
    private void datosUsu(){
        tfNomEmpresa = new TextField();
        tfNomEmpresa.setPromptText("Nombre Empresa");
        tfNomEmpresa.setMinSize(300, 40);
        tfNomEmpresa.getStyleClass().clear();
        tfNomEmpresa.setStyle(estilosComponentes.getTextfield());
        tfNomEmpresa.setPadding(new Insets(0, 10, 0, 10));
        tfNomEmpresa.setFont(fuente);
        tfNomEmpresa.setLayoutX(100);
        tfNomEmpresa.setLayoutY(1026);

        cbTamano = new ComboBox<String>();
        cbTamano.setMinSize(300, 40);
        cbTamano.setStyle(estilosComponentes.getComboBox());
        cbTamano.setPadding(new Insets(0, 10, 0, 10));
        cbTamano.setLayoutX(500);
        cbTamano.setLayoutY(1026);
        cbTamano.setPromptText("Tamaño Empresa");
        cbTamano.getItems().add("Pequeño");
        cbTamano.getItems().add("Mediano");
        cbTamano.getItems().add("Grande");

        p.getChildren().addAll(tfNomEmpresa, cbTamano);
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
    private void guardarDatos(){
        if(verificacion()){
            Persona persona = new Persona();
            persona.setClave(Integer.parseInt(tfClave.getText()));
            persona.setNombre(tfNombre.getText());
            persona.setApellido(tfApellido.getText());
            persona.setCorreo(tfCorreo.getText());
            persona.setTelefono(tfNúmero.getText());
            persona.setFechaNac(getfecha(fc_nac.getValue().toString()));

            Direccion direccion = new Direccion();
            direccion.setCalle(tfCalle.getText());
            direccion.setNumero(Integer.parseInt(tfNumeroDir.getText()));
            direccion.setColonia(tfColonia.getText());
            direccion.setCiudad(tfCiudad.getText());

            Cliente cliente = new Cliente();
            cliente.setTamano(cbTamano.getSelectionModel().getSelectedItem());
            cliente.setNomEmpresa(tfNomEmpresa.getText());

            ControladorCliete controladorCliete = new ControladorCliete();
            controladorCliete.editarCliente(persona, direccion, cliente);
        }else{
            addOyentesCampos();
        }
    }
    private java.sql.Date getfecha(String fecha){
        try{
            SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fu = f1.parse(fecha);
            java.sql.Date fk = new java.sql.Date(fu.getTime());
            return fk;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    private void addOyentesCampos(){
        tfNúmero.setOnMouseClicked(evt->{
            tfNúmero.setStyle(estilosComponentes.getTextfield());
        });
        tfNúmero.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue) {
                tfNúmero.setStyle(estilosComponentes.getTextfield());
            }
        });

        tfCorreo.setOnMouseClicked(evt->{
            tfCorreo.setStyle(estilosComponentes.getTextfield());
        });
        tfCorreo.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue) {
                tfCorreo.setStyle(estilosComponentes.getTextfield());
            }
        });

        tfClave.setOnMouseClicked(evt->{
            tfClave.setStyle(estilosComponentes.getTextfield());
        });
        tfClave.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue) {
                tfClave.setStyle(estilosComponentes.getTextfield());
            }
        });

        tfApellido.setOnMouseClicked(evt->{
            tfApellido.setStyle(estilosComponentes.getTextfield());
        });
        tfApellido.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue) {
                tfApellido.setStyle(estilosComponentes.getTextfield());
            }
        });

        fc_nac.setOnMouseClicked(evt->{
            fc_nac.setStyle(estilosComponentes.getDatePickerCampos());
        });
        fc_nac.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue) {
                fc_nac.setStyle(estilosComponentes.getTextfield());
            }
        });

        tfCalle.setOnMouseClicked(evt->{
            tfCalle.setStyle(estilosComponentes.getTextfield());
        });
        tfCalle.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue) {
                tfCalle.setStyle(estilosComponentes.getTextfield());
            }
        });

        tfColonia.setOnMouseClicked(evt->{
            tfColonia.setStyle(estilosComponentes.getTextfield());
        });
        tfColonia.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue) {
                tfColonia.setStyle(estilosComponentes.getTextfield());
            }
        });

        tfCiudad.setOnMouseClicked(evt->{
            tfCiudad.setStyle(estilosComponentes.getTextfield());
        });
        tfCiudad.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue) {
                tfCiudad.setStyle(estilosComponentes.getTextfield());
            }
        });

        tfNumeroDir.setOnMouseClicked(evt->{
            tfNumeroDir.setStyle(estilosComponentes.getTextfield());
        });
        tfNumeroDir.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue) {
                tfNumeroDir.setStyle(estilosComponentes.getTextfield());
            }
        });

        tfNomEmpresa.setOnMouseClicked(evt->{
            tfNomEmpresa.setStyle(estilosComponentes.getTextfield());
        });
        tfNomEmpresa.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue) {
                tfNomEmpresa.setStyle(estilosComponentes.getTextfield());
            }
        });

        cbTamano.setOnMouseClicked(evt->{
            cbTamano.setStyle(estilosComponentes.getComboBox());
        });
        cbTamano.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue) {
                cbTamano.setStyle(estilosComponentes.getComboBox());
            }
        });

        tfNombre.setOnMouseClicked(evt->{
            tfNombre.setStyle(estilosComponentes.getTextfield());
        });
        tfNombre.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue) {
                tfNombre.setStyle(estilosComponentes.getTextfield());
            }
        });

    }
    private boolean verificacion(){
        boolean band = true;

        if (tfClave.getText().strip().length() == 0){
            tfClave.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }
        if (!esNumero(tfClave.getText())){
            tfClave.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }
        if (tfNombre.getText().strip().length() == 0){
            tfNombre.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }
        if (tfApellido.getText().strip().length() == 0){
            tfApellido.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }
        if (tfCorreo.getText().strip().length() == 0){
            tfCorreo.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }
        if (!esCorreo(tfCorreo.getText())){
            tfCorreo.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }
        if (fc_nac.getValue() == null){
            fc_nac.setStyle(estilosComponentes.getDatePickerCamposE());
        }

        if (tfNúmero.getText().strip().length() == 0 || tfNúmero.getText().length() != 10){
            tfNúmero.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }
        if(!esNumero(tfNúmero.getText())){
            tfNúmero.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }

        if (tfCalle.getText().strip().length() == 0){
            tfCalle.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }

        if(tfColonia.getText().strip().length() == 0){
            tfColonia.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }

        if (tfNumeroDir.getText().strip().length() == 0){
            tfNumeroDir.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }

        if(!esNumero(tfNumeroDir.getText())){
            tfNumeroDir.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }

        if (tfCiudad.getText().strip().length() == 0){
            tfCiudad.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }

        if(tfNomEmpresa.getText().strip().length() == 0){
            tfNomEmpresa.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }

        if(cbTamano.getSelectionModel().getSelectedIndex() <= 0){
            cbTamano.setStyle(estilosComponentes.getComboBoxE());
            band = false;
        }

        return band;
    }
    private boolean esCorreo(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private void buscar(){
        int clave = Integer.parseInt(tfBuscar.getText());
        System.out.println(clave);
        com.example.interfaz.servidor.Cliente cliente = new com.example.interfaz.servidor.Cliente("");
        cliente.Cliente("BUSCAR", String.valueOf(clave), this, null);
    }
    public void llenarCampos(Persona persona, Direccion direccion, Cliente cliente){

        tfClave.setText(persona.getClave().toString());
        LocalDate fecha = persona.getFechaNac().toLocalDate();
        fc_nac.setValue(fecha);
        tfNombre.setText(persona.getNombre());
        tfApellido.setText(persona.getApellido());
        tfCorreo.setText(persona.getCorreo());
        tfNúmero.setText(persona.getTelefono());

        tfCalle.setText(direccion.getCalle());
        tfNumeroDir.setText(direccion.getNumero().toString());
        tfColonia.setText(direccion.getColonia());
        tfCiudad.setText(direccion.getCiudad());

        tfNomEmpresa.setText(cliente.getNomEmpresa());
        if (cliente.getTamano().equals("Pequeño")){
            cbTamano.getSelectionModel().select(1);
        }else  if (cliente.getTamano().equals("Mediano")){
            cbTamano.getSelectionModel().select(2);
        }else{
            cbTamano.getSelectionModel().select(3);
        }

    }
    private void limpiarCampos(){
        tfBuscar.setText("");
        tfNombre.setText("");
        tfApellido.setText("");
        tfCorreo.setText("");
        tfNúmero.setText("");
        LocalDate localDate = LocalDate.now();
        fc_nac.setValue(localDate);
        tfCalle.setText("");
        tfNumeroDir.setText("");
        tfColonia.setText("");
        tfCiudad.setText("");
        tfNomEmpresa.setText("");
        cbTamano.getSelectionModel().selectFirst();
    }
}