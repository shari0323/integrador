package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

// 1. Ya NO dice "extends Stage"
public class MainController {

    @FXML private Button btnServers;
    @FXML private Button btnPositions;

    // ¡EL CONSTRUCTOR FUE COMPLETAMENTE ELIMINADO PARA ROMPER EL BUCLE!

    @FXML
    private void initialize() {
        btnServers.setOnAction(e -> openWindow(
                "/view/PublicServerWindow.fxml",
                "Gestión de Servidores Públicos", 1150, 680));

        btnPositions.setOnAction(e -> openWindow(
                "/view/PositionWindow.fxml",
                "Gestión de Cargos", 800, 550));
    }

    private void openWindow(String fxmlPath, String title, int width, int height) {
        try {
            URL url = getClass().getResource(fxmlPath);
            if (url == null) {
                System.err.println("No se encontró: " + fxmlPath);
                return;
            }
            FXMLLoader loader = new FXMLLoader(url);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), width, height));
            stage.setTitle(title);
            stage.setMinWidth(width - 150);
            stage.setMinHeight(height - 130);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}