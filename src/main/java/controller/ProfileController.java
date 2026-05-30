package controller;

import dao.GenericDao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Position;

public class ProfileController {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtGrade;

    @FXML
    private TableView<Position> table;

    @FXML
    private TableColumn<Position, Long> colId;

    @FXML
    private TableColumn<Position, String> colName;

    @FXML
    private TableColumn<Position, String> colCode;

    @FXML
    private TableColumn<Position, String> colGrade;

    private final GenericDao<Position> dao =
            new GenericDao<>(Position.class);

    @FXML
    private void initialize() {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        colName.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        colCode.setCellValueFactory(
                new PropertyValueFactory<>("code")
        );

        colGrade.setCellValueFactory(
                new PropertyValueFactory<>("grade")
        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN
        );

        table.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, s) -> {

                    if (s != null) {

                        fillForm(s);
                    }
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

        showAlert(
                Alert.AlertType.INFORMATION,
                "Éxito",
                "Perfil guardado correctamente."
        );
    }

    @FXML
    private void delete() {

        Position selected =
                table.getSelectionModel().getSelectedItem();

        if (selected == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Advertencia",
                    "Selecciona un registro."
            );

            return;
        }

        dao.delete(selected.getId());

        loadData();

        clearFields();

        showAlert(
                Alert.AlertType.INFORMATION,
                "Éxito",
                "Registro eliminado correctamente."
        );
    }

    private void fillForm(Position p) {

        txtName.setText(p.getName());

        txtCode.setText(p.getCode());

        txtGrade.setText(p.getGrade());
    }

    private boolean validateFields() {

        if (txtName.getText().trim().isEmpty()
                || txtCode.getText().trim().isEmpty()
                || txtGrade.getText().trim().isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Completa todos los campos."
            );

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

        table.setItems(
                FXCollections.observableArrayList(
                        dao.findAll()
                )
        );
    }

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message
    ) {

        Alert alert = new Alert(type);

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }
}