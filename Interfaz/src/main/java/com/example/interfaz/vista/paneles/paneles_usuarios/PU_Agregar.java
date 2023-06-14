package com.example.interfaz.vista.paneles.paneles_usuarios;

import com.example.interfaz.ModeloClases.Direccion;
import com.example.interfaz.ModeloClases.Persona;
import com.example.interfaz.ModeloClases.Usuario;
import com.example.interfaz.controladores.ControladorUsuario;
import com.example.interfaz.modelo.EstilosComponentes;
import com.example.interfaz.vista.emergentes.Alerta;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javax.crypto.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.charset.StandardCharsets;

public class PU_Agregar extends Pane {

    Label[] labels;
    TextField tfClave, tfNombre, tfApellido, tfCorreo, tfNúmero, tfCalle, tfColonia, tfNumeroDir, tfCiudad, tfUsuario;
    PasswordField psContrase;
    DatePicker fc_nac;
    Font fuente;
    Button btnGuuardar;
    EstilosComponentes estilosComponentes;
    Alerta alerta;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+.[com]+$";

    public PU_Agregar(){
        estilosComponentes = new EstilosComponentes();
        fuente = new Font("Georgia", 18);
        setMinSize(900, 1300);
        iniciar();
        tfClave.setText(String.valueOf(claveAuto()));
        tfClave.setEditable(false);
        LocalDate localDate = LocalDate.now();
        fc_nac.setValue(localDate);
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

        labels[11].setText("Datos Usuario");
        labels[11].setLayoutX(150);
        labels[11].setLayoutY(890);

        labels[12].setText("Usuario");
        labels[12].setLayoutX(100);
        labels[12].setLayoutY(976);

        labels[13].setText("Contraseña");
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

        var url3 = getClass().getResource("/img/logosPaneles/user.png");
        var img3 = new Image(url3.toString(), 40, 40, true, true);
        labels[17].setGraphic(new ImageView(img3));
        labels[17].setLayoutX(100);
        labels[17].setLayoutY(890);
        labels[17].setMinSize(40, 40);


        for (Label label : labels){
            label.setMinSize(150, 40);
            label.setFont(fuente);
            label.setAlignment(Pos.CENTER_LEFT);
            getChildren().addAll(label);
        }

    }

    private void iniciar() {
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
            limpiarCampos();
        });
        getChildren().add(btnGuuardar);
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

        getChildren().addAll(tfClave, fc_nac ,tfNombre, tfApellido, tfCorreo, tfNúmero);
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

        getChildren().addAll(tfCalle, tfNumeroDir, tfColonia, tfCiudad);
    }

    private void datosUsu(){
        tfUsuario = new TextField();
        tfUsuario.setPromptText("Usuario");
        tfUsuario.setMinSize(300, 40);
        tfUsuario.getStyleClass().clear();
        tfUsuario.setStyle(estilosComponentes.getTextfield());
        tfUsuario.setPadding(new Insets(0, 10, 0, 10));
        tfUsuario.setFont(fuente);
        tfUsuario.setLayoutX(100);
        tfUsuario.setLayoutY(1026);

        psContrase = new PasswordField();
        psContrase.setPromptText("Contraseña");
        psContrase.setMinSize(300, 40);
        psContrase.getStyleClass().clear();
        psContrase.setStyle(estilosComponentes.getTextfield());
        psContrase.setPadding(new Insets(0, 10, 0, 10));
        psContrase.setFont(fuente);
        psContrase.setLayoutX(500);
        psContrase.setLayoutY(1026);

        getChildren().addAll(tfUsuario, psContrase);
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
            persona.setFechaNac(getfecha(fc_nac.getValue().toString()));
            persona.setTelefono(tfNúmero.getText());
            persona.setCorreo(tfCorreo.getText());
            Direccion direccion = new Direccion();
            direccion.setCalle(tfCalle.getText());
            direccion.setNumero(Integer.parseInt(tfNumeroDir.getText()));
            direccion.setColonia(tfColonia.getText());
            direccion.setCiudad(tfCiudad.getText());
            Usuario usuario = new Usuario();
            usuario.setUsuario(tfUsuario.getText());
            usuario.setContrasena(psContrase.getText());
            ControladorUsuario controladorUsuario = new ControladorUsuario();
            if (controladorUsuario.agregarUsuario(persona, direccion, usuario)){
                alerta = new Alerta(Alert.AlertType.CONFIRMATION, "Se ha guardado");
                alerta.show();
            }else{
                alerta = new Alerta(Alert.AlertType.ERROR, "No se ha guardado");
                alerta.show();
            }
        }else{
            addOyentesCampos();
            alerta = new Alerta(Alert.AlertType.ERROR, "Llenar todos los campos");
            alerta.show();
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

        tfUsuario.setOnMouseClicked(evt->{
            tfUsuario.setStyle(estilosComponentes.getTextfield());
        });
        tfUsuario.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue) {
                tfUsuario.setStyle(estilosComponentes.getTextfield());
            }
        });

        psContrase.setOnMouseClicked(evt->{
            psContrase.setStyle(estilosComponentes.getTextfield());
        });
        psContrase.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue) {
                psContrase.setStyle(estilosComponentes.getTextfield());
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
        if(fc_nac.getValue() == null){
            fc_nac.setStyle(estilosComponentes.getDatePickerCamposE());
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
        if (tfNúmero.getText().strip().length() == 0 && tfNúmero.getText().length() != 10){
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
        if(tfUsuario.getText().strip().length() == 0){
            tfUsuario.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }
        if(psContrase.getText().strip().length() == 0){
            psContrase.setStyle(estilosComponentes.getTextFieldErrorC());
            band = false;
        }

        return band;
    }

    private boolean esCorreo(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private int claveAuto(){
        int clave = (int) (Math.random() * 999999999) + 1;
        return clave;
    }

    private void limpiarCampos(){
        tfCalle.setText(String.valueOf(claveAuto()));
        LocalDate localDate = LocalDate.now();
        fc_nac.setValue(localDate);
        tfNombre.setText("");
        tfApellido.setText("");
        tfCorreo.setText("");
        tfNúmero.setText("");
        tfCalle.setText("");
        tfNumeroDir.setText("");
        tfColonia.setText("");
        tfCiudad.setText("");
        tfUsuario.setText("");
        psContrase.setText("");
    }

}