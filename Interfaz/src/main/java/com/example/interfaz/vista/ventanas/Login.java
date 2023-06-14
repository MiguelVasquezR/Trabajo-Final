package com.example.interfaz.vista.ventanas;

import com.example.interfaz.ModeloClases.Usuario;
import com.example.interfaz.contenedores.ContenedorVentana;
import com.example.interfaz.controladores.ControladorLogin;
import com.example.interfaz.modelo.EstilosComponentes;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Login extends Pane{

    Label lbImagenusu, lbImagencontra, lbTitulo, lbImagenLogo, lbFondo, lbFondo2;
    TextField tfUsuario;
    PasswordField pfPassword;
    String fuente = "Georgia";
    Button btnIniciar;
    EstilosComponentes estilosComponentes;

    public Login(){
        estilosComponentes = new EstilosComponentes();
        asignarFondo();
        iniciador();
    }

    private void asignarFondo(){
        var ULR = getClass().getResource("/img/Fondo.jpg");
        Image imagen = new Image(ULR.toString(), 1200, 750, true, true);
        BackgroundImage bi = new BackgroundImage(imagen, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                null, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        Background background = new Background(bi);
        setBackground(background);

        lbFondo = new Label();
        lbFondo.setBackground(Background.fill(Color.rgb(0, 0, 0, .7)));
        lbFondo.setMinSize(1200, 750);
        lbFondo.setLayoutX(0);
        lbFondo.setLayoutY(0);
        getChildren().add(lbFondo);
    }

    private void iniciador(){

        lbFondo2 = new Label();
        lbFondo2.setBackground(Background.fill(Color.rgb(2, 143, 200, .8)));
        lbFondo2.setMinSize(500, 600);
        lbFondo2.setLayoutX(350);
        lbFondo2.setLayoutY(75);


        var URLIMG3 = getClass().getResource("/img/Logo.png");
        var imagen3 = new Image(URLIMG3.toString(), 100, 100, true, true);
        lbImagenLogo = new Label();
        lbImagenLogo.setGraphic(new ImageView(imagen3));
        lbImagenLogo.setMinSize(100, 100);
        lbImagenLogo.setLayoutX(550);
        lbImagenLogo.setLayoutY(140);

        lbTitulo = new Label();
        lbTitulo.setText("La Canasta");
        lbTitulo.setFont(Font.font(fuente, 25));
        lbTitulo.setStyle("-fx-text-fill: #fff;");
        lbTitulo.setAlignment(Pos.CENTER);
        lbTitulo.setMinSize(250, 50);
        lbTitulo.setLayoutX(475);
        lbTitulo.setLayoutY(240);

        var URLIMG1 = getClass().getResource("/img/Logos/usuariosI.png");
        var imagen1 = new Image(URLIMG1.toString(), 50, 50, true, true);
        lbImagenusu = new Label();
        lbImagenusu.setGraphic(new ImageView(imagen1));
        lbImagenusu.setMinSize(50, 50);
        lbImagenusu.setLayoutX(440);
        lbImagenusu.setLayoutY(340);

        var URLIMG2 = getClass().getResource("/img/Logos/contrasena.png");
        var imagen2 = new Image(URLIMG2.toString(), 50, 50, true, true);
        lbImagencontra = new Label();
        lbImagencontra.setGraphic(new ImageView(imagen2));
        lbImagencontra.setMinSize(50, 50);
        lbImagencontra.setLayoutX(440);
        lbImagencontra.setLayoutY(440);

        tfUsuario = new TextField();
        tfUsuario.setPromptText("Ingresa tu Usuario");
        tfUsuario.setMinSize(250, 50);
        tfUsuario.setFont(Font.font(fuente, 17));
        tfUsuario.getStyleClass().clear();
        tfUsuario.setStyle(estilosComponentes.getTextfieldSesion());
        tfUsuario.setPadding(new Insets(10));
        tfUsuario.setLayoutX(500);
        tfUsuario.setLayoutY(340);
        tfUsuario.setOnMouseClicked(evt->{
            tfUsuario.setStyle(estilosComponentes.getTextfieldSesion());
        });


        pfPassword = new PasswordField();
        pfPassword.setPromptText("Ingresa tu Contraseña");
        pfPassword.setMinSize(250, 50);
        pfPassword.setFont(Font.font(fuente, 17));
        pfPassword.getStyleClass().clear();
        pfPassword.setStyle(estilosComponentes.getTextfieldSesion());
        pfPassword.setPadding(new Insets(10));
        pfPassword.setLayoutX(500);
        pfPassword.setLayoutY(440);
        pfPassword.setOnKeyPressed(evt->{
            if(evt.getCode().equals(KeyCode.ENTER)){
                iniciarSesión();
            }
        });
        pfPassword.setOnMouseClicked(evt->{
            pfPassword.setStyle(estilosComponentes.getTextfieldSesion());
        });
        pfPassword.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue) {
                pfPassword.setStyle(estilosComponentes.getTextfieldSesion());
            }
        });

        btnIniciar = new Button();
        btnIniciar.setTooltip(new Tooltip("Iniciar Sesión"));
        btnIniciar.setText("Iniciar Sesión");
        btnIniciar.setFont(Font.font(fuente, 18));
        btnIniciar.setMinSize(200, 50);
        btnIniciar.setLayoutX(500);
        btnIniciar.setLayoutY(540);
        btnIniciar.setStyle(estilosComponentes.getBotones());
        btnIniciar.setOnMouseEntered(evt->{
            btnIniciar.setStyle(estilosComponentes.getBotonesOk());
        });
        btnIniciar.setOnMouseExited(evt->{
            btnIniciar.setStyle(estilosComponentes.getBotones());
        });
        btnIniciar.setOnAction(evt->{
            iniciarSesión();
        });
        getChildren().addAll(lbFondo2,lbImagenLogo,lbTitulo, lbImagenusu, lbImagencontra, tfUsuario, pfPassword, btnIniciar);
    }

    private Boolean obtenerDatos(){
        boolean band = true;
        if(tfUsuario.getText().strip().length() != 0){
            if(pfPassword.getText().strip().length() == 0){
                band = false;
            }
        }else{
            band = false;
        }
        return band;
    }

    private void iniciarSesión(){
        ControladorLogin controladorLogin = new ControladorLogin();
        if (verificacion()){
            Usuario usuario = new Usuario();
            usuario.setUsuario(tfUsuario.getText());
            usuario.setContrasena(pfPassword.getText());
            controladorLogin.setPane(this);
            controladorLogin.setUsuario(usuario);
            controladorLogin.enviarUsuario();
        }
    }

    private boolean verificacion(){
        boolean band=true;
        if (tfUsuario.getText().strip().length()==0){
            tfUsuario.setStyle(estilosComponentes.getTextFieldError());
            band  = false;
        }
        if (pfPassword.getText().strip().length() == 0){
            pfPassword.setStyle(estilosComponentes.getTextFieldError());
            band  = false;
        }
        return band;
    }


}
