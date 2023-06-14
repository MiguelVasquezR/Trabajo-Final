package com.example.interfaz.vista.paneles.paneles_estadisticas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

public class PE_CompraVenta extends Pane {

    public PE_CompraVenta(){
        iniciador();
    }

    private void iniciador() {
        graficaIngresosEgresos();
        graficaMCompras();
        graficaMVentas();
    }

    private void graficaMVentas(){
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
        barChart.setTitle("Mayores Ventas");
        barChart.getData().add(new XYChart.Series<>(data));
        barChart.setPrefSize(300, 250);
        barChart.setLayoutX(100);
        barChart.setLayoutY(40);

        getChildren().add(barChart);

    }

    private void graficaMCompras(){
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
        barChart.setTitle("Mayores Compras");
        barChart.getData().add(new XYChart.Series<>(data));
        barChart.setPrefSize(300, 250);
        barChart.setLayoutX(500);
        barChart.setLayoutY(40);

        getChildren().add(barChart);

    }

    private void graficaIngresosEgresos(){
        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
        int i;
        String[] depa = new String[]{"Sala", "Comedor"};
        int[] da = new int[]{1, 5};

        for (int j = 0; j < 2; j++) {
            data.add(new XYChart.Data<>(depa[j], da[j]));
        }

        // Crear el eje X y el eje Y del gráfico
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Crear el gráfico de barras y establecer sus ejes y datos
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Ingresos & Egresos");
        barChart.getData().add(new XYChart.Series<>(data));
        barChart.setPrefSize(700, 300);
        barChart.setLayoutX(100);
        barChart.setLayoutY(340);

        getChildren().add(barChart);

    }
}
