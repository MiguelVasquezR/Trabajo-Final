package com.example.interfaz.vista.paneles.paneles_estadisticas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PE_Clientes extends Pane {

    public PE_Clientes(){
        iniciador();
    }

    private void iniciador() {
        graficaMComprasCliente();
        graficaMVisitas();
        graficaMClientes();
    }

    private void graficaMClientes(){
        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
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
        barChart.setTitle("Clientes más compradores");
        barChart.getData().add(new XYChart.Series<>(data));
        barChart.setPrefSize(300, 250);
        barChart.setLayoutX(100);
        barChart.setLayoutY(40);

        getChildren().add(barChart);

    }

    private void graficaMVisitas(){
        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
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
        barChart.setTitle("Clientes con más Visitas");
        barChart.getData().add(new XYChart.Series<>(data));
        barChart.setPrefSize(300, 250);
        barChart.setLayoutX(500);
        barChart.setLayoutY(40);

        getChildren().add(barChart);
    }

    private void graficaMComprasCliente(){
        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
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
        barChart.setTitle("Más compras de un cliente");
        barChart.getData().add(new XYChart.Series<>(data));
        barChart.setPrefSize(700, 300);
        barChart.setLayoutX(100);
        barChart.setLayoutY(340);

        getChildren().add(barChart);

    }
}
