package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class MainController extends Stage {

    @FXML private Button btnServers;
    @FXML private Button btnPositions;

    public MainController() {
        try {
            URL fxmlUrl = getClass().getResource("/view/MainWindow.fxml");
            if (fxmlUrl == null) throw new RuntimeException("No se encontró MainWindow.fxml");

            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            loader.setController(this);

            Scene scene = new Scene(loader.load(), 500, 400);
            setTitle("Sistema Talento Humano");
            setScene(scene);
            setMinWidth(520);
            setMinHeight(420);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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