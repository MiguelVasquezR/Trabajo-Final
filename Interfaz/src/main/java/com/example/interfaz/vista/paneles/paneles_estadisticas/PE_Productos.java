package com.example.interfaz.vista.paneles.paneles_estadisticas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PE_Productos extends Pane{

    public PE_Productos(){
        iniciador();
    }

    private void iniciador() {
        graficaMDemanda();
        graficaMEVendido();
        graficaMVendido();
    }

    private void graficaMVendido(){
        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
        int i;
        String[] depa = new String[]{"Sala", "Comedor", "Cocina"};
        int[] da = new int[]{1, 2, 3};

        for (int j = 0; j < 3; j++) {
            data.add(new XYChart.Data<>(depa[j], da[j]));
        }

        // Crear el eje X y el eje Y del gráfico
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Crear el gráfico de barras y establecer sus ejes y datos
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Mayor Venta");
        barChart.getData().add(new XYChart.Series<>(data));
        barChart.setPrefSize(300, 250);
        barChart.setLayoutX(100);
        barChart.setLayoutY(40);

        getChildren().add(barChart);

    }

    private void graficaMEVendido(){
        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
        int i;
        String[] depa = new String[]{"Sala", "Comedor", "Cocina"};
        int[] da = new int[]{7, 3, 9};

        for (int j = 0; j < 3; j++) {
            data.add(new XYChart.Data<>(depa[j], da[j]));
        }

        // Crear el eje X y el eje Y del gráfico
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Crear el gráfico de barras y establecer sus ejes y datos
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Menor Venta");
        barChart.getData().add(new XYChart.Series<>(data));
        barChart.setPrefSize(300, 250);
        barChart.setLayoutX(500);
        barChart.setLayoutY(40);

        getChildren().add(barChart);

    }

    private void graficaMDemanda(){
        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
        int i;
        String[] depa = new String[]{"Sala", "Comedor", "Cocina"};
        int[] da = new int[]{1, 5, 10};

        for (int j = 0; j < 3; j++) {
            data.add(new XYChart.Data<>(depa[j], da[j]));
        }

        // Crear el eje X y el eje Y del gráfico
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Crear el gráfico de barras y establecer sus ejes y datos
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Mayor Demanda");
        barChart.getData().add(new XYChart.Series<>(data));
        barChart.setPrefSize(700, 300);
        barChart.setLayoutX(100);
        barChart.setLayoutY(340);

        getChildren().add(barChart);

    }
}
