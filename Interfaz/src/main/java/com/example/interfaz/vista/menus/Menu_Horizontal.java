package com.example.interfaz.vista.menus;

import com.example.interfaz.modelo.EstilosComponentes;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Menu_Horizontal extends HBox {

    Button[] botones;
    Label lb;
    EstilosComponentes estilosComponentes;

    public Menu_Horizontal() {
        setPrefSize(900, 75);
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(10);
        setBackground(Background.fill(Color.rgb(2, 143, 200, .9)));
        setPadding(new Insets(0, 10, 0, 0));
        lb = new Label();
        var ult = getClass().getResource("/img/Logo.png");
        var imange = new Image(ult.toString(), 50, 50, true, true);
        lb.setGraphic(new ImageView(imange));
        lb.setText("La Canasta");
        lb.setAlignment(Pos.CENTER);
        lb.setFont(Font.font("Georgia", 20));
        lb.setStyle("-fx-text-fill: #fff;");
        lb.setPadding(new Insets(0, 0, 0, 20));
        lb.setMinSize(300, 70);
        lb.setTextAlignment(TextAlignment.CENTER);
        getChildren().add(lb);
    }

    public void agregar2(String t1, String t2, String[] toltips) {
        getChildren().clear();
        getChildren().add(lb);
        botones = new Button[]{
                new Button(), new Button()
        };
        botones[0].setText(t1);
        botones[0].setTooltip(new Tooltip(toltips[0]));
        botones[1].setText(t2);
        botones[1].setTooltip(new Tooltip(toltips[1]));
        for (Button button : botones) {
            button.setMinSize(250, 40);
            button.setAlignment(Pos.CENTER);
            button.setFont(Font.font("Georgia", 18));
            button.setStyle("-fx-border-color: #fff;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-radius: 30;" +
                    "-fx-background-radius: 30;" +
                    "-fx-background-color: rgba(2, 143, 200, .9);" +
                    "-fx-text-fill: #fff;");
            button.setOnMouseEntered(evt -> {
                button.setStyle("-fx-border-color: #fff;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 30;" +
                        "-fx-background-radius: 30;" +
                        "-fx-background-color: rgb(9, 211, 234);" +
                        "-fx-text-fill: #fff;");
            });

            button.setOnMouseExited(evt -> {
                button.setStyle("-fx-border-color: #fff;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 30;" +
                        "-fx-background-radius: 30;" +
                        "-fx-background-color: rgba(2, 143, 200, .9);" +
                        "-fx-text-fill: #fff;");
            });


            setMargin(button, new Insets(0, 10, 0, 0));
            getChildren().addAll(button);
        }
        setMargin(botones[0], new Insets(0, 100, 0, 100));
    }


    public void agregar3(String t1, String t2, String t3, String[] toltips) {
        getChildren().clear();
        getChildren().add(lb);
        botones = new Button[]{
                new Button(), new Button(), new Button()
        };
        botones[0].setText(t1);
        botones[0].setTooltip(new Tooltip(toltips[0]));
        botones[1].setText(t2);
        botones[1].setTooltip(new Tooltip(toltips[1]));
        botones[2].setText(t3);
        botones[2].setTooltip(new Tooltip(toltips[2]));
        for (Button button : botones) {
            button.setMinSize(250, 40);
            button.setAlignment(Pos.CENTER);
            button.setFont(Font.font("Georgia", 18));
            button.setStyle("-fx-border-color: #fff;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-radius: 30;" +
                    "-fx-background-radius: 30;" +
                    "-fx-background-color: rgba(2, 143, 200, .9);" +
                    "-fx-text-fill: #fff;");
            button.setOnMouseEntered(evt -> {
                button.setStyle("-fx-border-color: #fff;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 30;" +
                        "-fx-background-radius: 30;" +
                        "-fx-background-color: rgb(9, 211, 234);" +
                        "-fx-text-fill: #fff;");
            });

            button.setOnMouseExited(evt -> {
                button.setStyle("-fx-border-color: #fff;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 30;" +
                        "-fx-background-radius: 30;" +
                        "-fx-background-color: rgba(2, 143, 200, .9);" +
                        "-fx-text-fill: #fff;");
            });

            setMargin(button, new Insets(0, 30, 0, 0));
            getChildren().addAll(button);
        }
        setMargin(lb, new Insets(0, 30, 0, 0));
    }

    public Button[] getBotones() {
        return botones;
    }

    public void agregar1(String s, String[] toltips) {
        getChildren().clear();
        getChildren().add(lb);
        estilosComponentes = new EstilosComponentes();
        botones = new Button[]{
                new Button()
        };
        botones[0].setText(s);
        botones[0].setTooltip(new Tooltip(toltips[0]));
        for (Button button : botones) {
            button.setMinSize(250, 40);
            button.setAlignment(Pos.CENTER);
            button.setFont(Font.font("Georgia", 18));
            button.setStyle(estilosComponentes.getBotones());
            button.setOnMouseEntered(evt -> {
                button.setStyle(estilosComponentes.getBotonesOk());
            });
            button.setOnMouseExited(evt -> {
                button.setStyle(estilosComponentes.getBotones());
            });
            setMargin(button, new Insets(0, 0, 0, 325));
            getChildren().addAll(button);
        }
        setMargin(lb, new Insets(0, 30, 0, 0));
    }
}
