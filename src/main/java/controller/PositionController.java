package controller;

import dao.GenericDao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Position;

public class PositionController {

    @FXML private TextField txtName;
    @FXML private TextField txtCode;
    @FXML private TextField txtGrade;

    @FXML private TableView<Position>           table;
    @FXML private TableColumn<Position, Long>   colId;
    @FXML private TableColumn<Position, String> colName;
    @FXML private TableColumn<Position, String> colCode;
    @FXML private TableColumn<Position, String> colGrade;

    private final GenericDao<Position> dao = new GenericDao<>(Position.class);

    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, s) -> {
            if (s != null) fillForm(s);
        });

        loadData();
    }

    @FXML
    private void save() {
        if (!validateFields()) return;

        Position p = new Position();
        p.setName(txtName.getText().trim());
        p.setCode(txtCode.getText().trim());
        p.setGrade(txtGrade.getText().trim());

        dao.save(p);
        loadData();
        clearFields();
        showAlert(Alert.AlertType.INFORMATION, "Éxito", "Cargo guardado correctamente.");
    }

    @FXML
    private void delete() {
        Position selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Advertencia",
                    "Selecciona un cargo de la tabla para eliminar.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar eliminación");
        confirm.setHeaderText(null);
        confirm.setContentText("¿Eliminar el cargo \"" + selected.getName() + "\"?");

        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                dao.delete(selected.getId());
                loadData();
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Éxito", "Cargo eliminado correctamente.");
            }
        });
    }

    private void fillForm(Position p) {
        txtName.setText(p.getName());
        txtCode.setText(p.getCode());
        txtGrade.setText(p.getGrade());
    }

    private boolean validateFields() {
        StringBuilder errors = new StringBuilder();

        if (txtName.getText().trim().isEmpty())  errors.append("• Nombre es obligatorio.\n");
        if (txtCode.getText().trim().isEmpty())  errors.append("• Código es obligatorio.\n");
        if (txtGrade.getText().trim().isEmpty()) errors.append("• Grado es obligatorio.\n");

        if (!errors.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campos incompletos", errors.toString());
            return false;
        }
        return true;
    }

    private void clearFields() {
        txtName.clear();
        txtCode.clear();
        txtGrade.clear();
        table.getSelectionModel().clearSelection();
    }

    private void loadData() {
        if (table != null)
            table.setItems(FXCollections.observableArrayList(dao.findAll()));
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}