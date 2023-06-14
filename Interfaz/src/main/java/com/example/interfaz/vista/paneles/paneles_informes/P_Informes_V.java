package com.example.interfaz.vista.paneles.paneles_informes;

import com.example.interfaz.controladores.ControladorInformes;
import com.example.interfaz.controladores.generadorPDF.Generador;
import com.example.interfaz.modelo.EstilosComponentes;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.embed.swing.SwingFXUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Date;
import java.time.LocalDate;

public class P_Informes_V extends Pane {

    ScrollPane panel_Img;
    DatePicker fechaI, fechaF;
    Label lba, lbb, lbTitulo;
    Button btnImprimir, btnGenera, btnSig, btnAnt;
    EstilosComponentes estilosComponentes;
    ImageView[] imageViews;

    public P_Informes_V() {
        estilosComponentes = new EstilosComponentes();
        initComponent();
    }

    private void initComponent() {

        lba = new Label("Fecha Inicio");
        lba.setAlignment(Pos.CENTER);
        lba.setMinSize(250, 20);
        lba.setFont(Font.font("Georgia", 16));
        lba.setLayoutX(175);
        lba.setLayoutY(10);

        fechaI = new DatePicker();
        fechaI.setMinSize(250, 40);
        LocalDate fecha_actual = LocalDate.now();
        fechaI.setValue(fecha_actual.minusMonths(1));
        fechaI.setLayoutX(175);
        fechaI.setLayoutY(30);
        fechaI.setStyle(estilosComponentes.getDatePickerCampos());

        lbb = new Label("Fecha Fin");
        lbb.setAlignment(Pos.CENTER);
        lbb.setMinSize(250, 20);
        lbb.setFont(Font.font("Georgia", 16));
        lbb.setLayoutX(475);
        lbb.setLayoutY(10);

        fechaF = new DatePicker();
        fechaF.setMinSize(250, 40);
        fechaF.setValue(fecha_actual);
        fechaF.setLayoutX(475);
        fechaF.setLayoutY(30);
        fechaF.setStyle(estilosComponentes.getDatePickerCampos());

        panel_Img = new ScrollPane();
        panel_Img.setMinSize(750, 500);
        panel_Img.setPrefSize(750, 500);
        panel_Img.setLayoutX(75);
        panel_Img.setLayoutY(90);
        panel_Img.getStyleClass().clear();
        panel_Img.setBackground(Background.fill(Color.WHITE));
        BorderStroke borderStroke = new BorderStroke(Color.rgb(2, 143, 200, .9), BorderStrokeStyle.SOLID,
                new CornerRadii(5), new BorderWidths(2));
        Border border = new Border(borderStroke);
        panel_Img.setBorder(border);
        lbTitulo = new Label("Vista Previa");
        //lbTitulo.setAlignment(Pos.CENTER);
        lbTitulo.setMinSize(400, 50);
        lbTitulo.setFont(Font.font("Georgia", 40));
        lbTitulo.setLayoutX(175);
        lbTitulo.setLayoutY(225);
        panel_Img.setContent(lbTitulo);

        btnGenera = new Button("Generar");
        btnGenera.setTooltip(new Tooltip("Genera el Informe"));
        btnGenera.setMinSize(200, 40);
        btnGenera.getStyleClass().clear();
        btnGenera.setFont(Font.font("Georgia", 18));
        btnGenera.setStyle(estilosComponentes.getBotones());
        btnGenera.setAlignment(Pos.CENTER);
        btnGenera.setLayoutX(225);
        btnGenera.setLayoutY(610);
        btnGenera.setOnMouseEntered(evt -> {
            btnGenera.setStyle(estilosComponentes.getBotonesOk());
        });
        btnGenera.setOnMouseExited(evt -> {
            btnGenera.setStyle(estilosComponentes.getBotones());
        });
        btnGenera.setOnAction(evt -> {
            obtenerFechas();
        });

        btnImprimir = new Button("Imprimir");
        btnImprimir.setTooltip(new Tooltip("Imprime el Informe"));
        btnImprimir.setMinSize(200, 40);
        btnImprimir.getStyleClass().clear();
        btnImprimir.setFont(Font.font("Georgia", 18));
        btnImprimir.setStyle(estilosComponentes.getBotones());
        btnImprimir.setAlignment(Pos.CENTER);
        btnImprimir.setLayoutX(475);
        btnImprimir.setLayoutY(610);
        btnImprimir.setOnMouseEntered(evt -> {
            btnImprimir.setStyle(estilosComponentes.getBotonesOk());
        });
        btnImprimir.setOnMouseExited(evt -> {
            btnImprimir.setStyle(estilosComponentes.getBotones());
        });
        btnImprimir.setOnAction(evt -> {
            Generador pdf = new Generador("Informe de Compra & Venta");
            pdf.generar();
        });

        btnSig = new Button("Sig");
        btnSig.setMinSize(50, 40);
        btnSig.setLayoutX(795);
        btnSig.setLayoutY(610);
        btnSig.setStyle(estilosComponentes.getBotones());
        btnSig.setVisible(false);
        btnSig.setOnAction(evt -> {
            cambioHojaA();
        });

        btnAnt = new Button("Ant");
        btnAnt.setMinSize(50, 40);
        btnAnt.setLayoutX(730);
        btnAnt.setLayoutY(610);
        btnAnt.setStyle(estilosComponentes.getBotones());
        btnAnt.setVisible(false);
        btnAnt.setOnAction(evt -> {
            cambioHojaD();
        });

        getChildren().addAll(lba, lbb, fechaI, fechaF, panel_Img, btnImprimir, btnGenera, btnSig, btnAnt);
    }

    int cont = 0;
    private void cambioHojaA() {
        int tot = imageViews.length;
        if (cont < tot) {
            try{
                cont++;
                panel_Img.setContent(imageViews[cont]);
            }catch (Exception e){}
        }
    }

    private void cambioHojaD() {
        int tot = imageViews.length;
        if (cont < tot && cont > 0) {
            try {
                cont--;
                panel_Img.setContent(imageViews[cont]);
            }catch (Exception e){}
        }
    }

    private ScrollPane generar() {
        ScrollPane scrollPane = null;
        try {
            Generador generador = new Generador("Reporte compra-venta");
            generador.generarPDF();
            File arch = new File("Reporte.pdf");
            PDDocument document = PDDocument.load(arch);
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            int pageCount = document.getNumberOfPages();
            imageViews = new ImageView[pageCount];
            System.out.println("Total de paginas: " + pageCount);
            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = pdfRenderer.renderImage(i);
                Image fxImage = SwingFXUtils.toFXImage(image, null);
                ImageView imageView = new ImageView(fxImage);
                imageViews[i] = imageView;
            }
            document.close();
            scrollPane = new ScrollPane();
            scrollPane.setContent(imageViews[0]);
            scrollPane.setFitToWidth(true);
            scrollPane.getStyleClass().clear();
            if(pageCount>1){
                btnSig.setVisible(true);
                btnAnt.setVisible(true);
            }
            scrollPane.viewportBoundsProperty().addListener((observable, oldValue, newValue) -> {
                for (ImageView imageView : imageViews) {
                    imageView.setFitWidth(newValue.getWidth());
                    imageView.setFitHeight(newValue.getHeight());
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }
        return scrollPane;
    }

    ControladorInformes controladorInformes;

    private void obtenerFechas(){
        controladorInformes = new ControladorInformes();
        LocalDate inicioLC = fechaI.getValue();
        Date inicio = Date.valueOf(inicioLC);
        LocalDate finLC = fechaF.getValue();
        Date fin = Date.valueOf(finLC);
        controladorInformes.llenarReporte(inicio, fin);
        panel_Img.setContent(generar());
    }

}
