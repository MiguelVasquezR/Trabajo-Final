package com.example.interfaz.vista.menus;

import com.example.interfaz.contenedores.ContenedorLogin;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Menu_Izquierda extends VBox {

    Button[] buttons;
    Animation entrada, salida;
    Button btnCerrar;

    public Menu_Izquierda(){
        setPrefSize(300, 750);
        setAlignment(Pos.CENTER);
        setBackground(Background.fill(Color.rgb(2, 143, 200, .9)));
        setSpacing(10);
        setPadding(new Insets(0, 0, 10, 0));
        iniciarComponentes();
    }

    private void iniciarComponentes(){
        buttons = new Button[]{
          new Button(),new Button(),new Button(),new Button(),new Button(),new Button(),new Button()
        };

        buttons[0].setText("Inventario");
        var ULRProductos = getClass().getResource("/img/Logos/productos.png");
        var imgProductos = new Image(ULRProductos.toString(), 32, 32, true, true);
        buttons[0].setGraphic(new ImageView(imgProductos));
        buttons[0].setTooltip(new Tooltip("Gestión de Inventario"));

        buttons[1].setText("Compra");
        var ULRCompras = getClass().getResource("/img/Logos/ventas.png");
        var imgCompras = new Image(ULRCompras.toString(), 32, 32, true, true);
        buttons[1].setGraphic(new ImageView(imgCompras));
        buttons[1].setTooltip(new Tooltip("Gestión de Compras y Ventas"));

        buttons[2].setText("Clientes");
        var ULRSeg = getClass().getResource("/img/Logos/usuarios.png");
        var imgSeg = new Image(ULRSeg.toString(), 32, 32, true, true);
        buttons[2].setGraphic(new ImageView(imgSeg));
        buttons[2].setTooltip(new Tooltip("Seguimiento de Clientes"));

        buttons[3].setText("Proveedor");
        var ULRProv = getClass().getResource("/img/Logos/proveedor.png");
        var imgProv = new Image(ULRProv.toString(), 32, 32, true, true);
        buttons[3].setGraphic(new ImageView(imgProv));
        buttons[3].setTooltip(new Tooltip("Gestión de Proveedores"));

        buttons[4].setText("Informes");
        var ULRInfo = getClass().getResource("/img/Logos/informes.png");
        var imgInfo = new Image(ULRInfo.toString(), 32, 32, true, true);
        buttons[4].setGraphic(new ImageView(imgInfo));
        buttons[4].setTooltip(new Tooltip("Generador de Informes"));


        buttons[5].setText("Usuarios");
        var ULRUsu = getClass().getResource("/img/Logos/empleado.png");
        var imgUsu = new Image(ULRUsu.toString(), 32, 32, true, true);
        buttons[5].setGraphic(new ImageView(imgUsu));
        buttons[5].setTooltip(new Tooltip("Gestión de Usuarios"));

        buttons[6].setText("Estadísticas");
        var ULREst = getClass().getResource("/img/Logos/estadística.png");
        var imgEst = new Image(ULREst.toString(), 32, 32, true, true);
        buttons[6].setGraphic(new ImageView(imgEst));
        buttons[6].setTooltip(new Tooltip("Estadísticas de la Tienda"));



        for (Button button : buttons) {
            button.setMinSize(150, 50);
            button.setAlignment(Pos.CENTER);
            button.setFont(Font.font("Georgia", 15));
            button.setStyle("-fx-border-color: #fff;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-radius: 30;" +
                    "-fx-background-radius: 30;" +
                    "-fx-background-color: rgba(2, 143, 200, .9);" +
                    "-fx-text-fill: #fff;");
            button.setOnMouseEntered(evt -> {
                crecerBTN(button);
                button.setStyle("-fx-border-color: #fff;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 30;" +
                        "-fx-background-radius: 30;" +
                        "-fx-background-color: rgba(2, 237, 247, .7);" +
                        "-fx-text-fill: #fff;");

            });
            button.setOnMouseExited(evt -> {
                disminuirBTN(button);
                button.setStyle("-fx-border-color: #fff;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 30;" +
                        "-fx-background-radius: 30;" +
                        "-fx-background-color: rgba(2, 143, 200, .9);" +
                        "-fx-text-fill: #fff;");
            });
            setMargin(button, new Insets(0, 0, 10, 0));
            getChildren().addAll(button);
        }

        btnCerrar = new Button("Cerrar Sesión");
        var ULRCCS = getClass().getResource("/img/Logos/CS.png");
        var imgCS = new Image(ULRCCS.toString(), 32, 32, true, true);
        btnCerrar.setGraphic(new ImageView(imgCS));
        btnCerrar.setFont(Font.font("Georgia", 15));
        btnCerrar.setMinSize(150, 50);
        btnCerrar.setStyle("-fx-border-radius: 30;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: #fff;" +
                "-fx-background-color: rgb(2, 143, 200);" +
                "-fx-background-radius: 30;" +
                "-fx-text-fill: #fff;");

        btnCerrar.setOnMouseEntered(evt->{
            crecerBTN(btnCerrar);
            btnCerrar.setStyle("-fx-border-radius: 30;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-color: #fff;" +
                    "-fx-background-color: rgb(224, 96, 121, .88);" +
                    "-fx-background-radius: 30;" +
                    "-fx-text-fill: #fff;");
        });
        btnCerrar.setOnMouseExited(evt->{
            disminuirBTN(btnCerrar);
            btnCerrar.setStyle("-fx-border-radius: 30;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-color: #fff;" +
                    "-fx-background-color: rgb(2, 143, 200);" +
                    "-fx-background-radius: 30;" +
                    "-fx-text-fill: #fff;");
        });
        btnCerrar.setOnAction(evt->{
            Stage stage = (Stage) getScene().getWindow();
            stage.close();
            ContenedorLogin cl = new ContenedorLogin();
            cl.show();
        });

        setMargin(btnCerrar, new Insets(30, 0, 0, 0));
        getChildren().add(btnCerrar);
    }

    private void crecerBTN(Button boton) {
        entrada = new Transition() {
            {
                setCycleDuration(Duration.millis(10));
            }

            protected void interpolate(double frac) {
                var ancho = boton.getWidth();
                var X = boton.getLayoutX();
                if (boton.getWidth() <= 200 && boton.getLayoutX() >= 0) {
                    boton.setMinWidth(ancho + 50);
                    boton.setLayoutX(X + 50);
                }else{
                    entrada.stop();
                }
            }
        };
        entrada.play();
    }

    private void disminuirBTN(Button boton) {
        salida = new Transition() {
            {
                setCycleDuration(Duration.millis(10));
            }
            protected void interpolate(double frac) {
                var ancho = boton.getWidth();
                var X = boton.getLayoutX();
                if (boton.getWidth() >= 150 && boton.getLayoutX() <= 50) {
                    boton.setMinWidth(ancho - 50);
                    boton.setLayoutX(X - 50);
                }else{
                    salida.stop();
                }
            }
        };
        salida.play();
    }

    public Button[] getButtons(){
        return buttons;
    }

}
