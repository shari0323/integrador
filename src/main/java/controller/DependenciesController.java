package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class DependenciesController {

    @FXML
    private PieChart chartDependencies;

    @FXML
    private void initialize() {

        loadChart();
    }

    private void loadChart() {

        chartDependencies.setData(
                FXCollections.observableArrayList(

                        new PieChart.Data(
                                "Secretaría de Salud",
                                20
                        ),

                        new PieChart.Data(
                                "Secretaría de Educación",
                                35
                        ),

                        new PieChart.Data(
                                "Gobierno",
                                15
                        ),

                        new PieChart.Data(
                                "Infraestructura",
                                30
                        )
                )
        );
    }
}