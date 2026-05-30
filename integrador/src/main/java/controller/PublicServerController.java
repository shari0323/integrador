package controller;

import dao.GenericDao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.PublicServer;

public class PublicServerController {

    @FXML private TextField txtIdNumber;
    @FXML private TextField txtFirstName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtPersonalEmail;
    @FXML private TextField txtInstitutionalEmail;
    @FXML private TextField txtPhone;

    @FXML private DatePicker dpBirthDate;
    @FXML private ComboBox<String> cbGender;
    @FXML private ComboBox<String> cbMaritalStatus;
    @FXML private ComboBox<String> cbBloodType;
    @FXML private ComboBox<String> cbEmploymentType;

    @FXML private TableView<PublicServer>            table;
    @FXML private TableColumn<PublicServer, Long>    colId;
    @FXML private TableColumn<PublicServer, String>  colIdNumber;
    @FXML private TableColumn<PublicServer, String>  colFirstName;
    @FXML private TableColumn<PublicServer, String>  colLastName;
    @FXML private TableColumn<PublicServer, String>  colPhone;

    private final GenericDao<PublicServer> dao = new GenericDao<>(PublicServer.class);

    @FXML
    private void initialize() {
        cbGender.setItems(FXCollections.observableArrayList(
                "Masculino", "Femenino", "No binario", "Prefiero no decir"));

        cbMaritalStatus.setItems(FXCollections.observableArrayList(
                "Soltero/a", "Casado/a", "Unión libre", "Divorciado/a", "Viudo/a"));

        cbBloodType.setItems(FXCollections.observableArrayList(
                "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"));

        cbEmploymentType.setItems(FXCollections.observableArrayList(
                "Planta", "Contrato", "Provisional", "Libre nombramiento"));

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colIdNumber.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, s) -> {
            if (s != null) fillForm(s);
        });

        loadData();
    }

    @FXML
    private void save() {
        if (!validateFields()) return;

        PublicServer s = new PublicServer();
        s.setIdNumber(txtIdNumber.getText().trim());
        s.setFirstName(txtFirstName.getText().trim());
        s.setLastName(txtLastName.getText().trim());
        s.setBirthDate(dpBirthDate.getValue());
        s.setGender(cbGender.getValue());
        s.setMaritalStatus(cbMaritalStatus.getValue());
        s.setBloodType(cbBloodType.getValue());
        s.setPersonalEmail(txtPersonalEmail.getText().trim());
        s.setInstitutionalEmail(txtInstitutionalEmail.getText().trim());
        s.setPhone(txtPhone.getText().trim());
        s.setEmploymentType(cbEmploymentType.getValue());

        dao.save(s);
        loadData();
        clearFields();
        showAlert(Alert.AlertType.INFORMATION, "Éxito", "Servidor público guardado correctamente.");
    }

    @FXML
    private void delete() {
        PublicServer selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Advertencia",
                    "Selecciona un registro de la tabla para eliminar.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar eliminación");
        confirm.setHeaderText(null);
        confirm.setContentText("¿Eliminar a " + selected.getFirstName()
                + " " + selected.getLastName() + "?");

        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                dao.delete(selected.getId());
                loadData();
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Éxito", "Registro eliminado correctamente.");
            }
        });
    }

    private void fillForm(PublicServer s) {
        txtIdNumber.setText(s.getIdNumber());
        txtFirstName.setText(s.getFirstName());
        txtLastName.setText(s.getLastName());
        dpBirthDate.setValue(s.getBirthDate());
        cbGender.setValue(s.getGender());
        cbMaritalStatus.setValue(s.getMaritalStatus());
        cbBloodType.setValue(s.getBloodType());
        txtPersonalEmail.setText(s.getPersonalEmail());
        txtInstitutionalEmail.setText(s.getInstitutionalEmail());
        txtPhone.setText(s.getPhone());
        cbEmploymentType.setValue(s.getEmploymentType());
    }

    private boolean validateFields() {
        StringBuilder errors = new StringBuilder();

        if (txtIdNumber.getText().trim().isEmpty())
            errors.append("• Cédula es obligatoria.\n");
        if (txtFirstName.getText().trim().isEmpty())
            errors.append("• Nombres es obligatorio.\n");
        if (txtLastName.getText().trim().isEmpty())
            errors.append("• Apellidos es obligatorio.\n");
        if (dpBirthDate.getValue() == null)
            errors.append("• Fecha de nacimiento es obligatoria.\n");
        if (cbGender.getValue() == null)
            errors.append("• Género es obligatorio.\n");
        if (cbMaritalStatus.getValue() == null)
            errors.append("• Estado civil es obligatorio.\n");
        if (cbBloodType.getValue() == null)
            errors.append("• Tipo de sangre es obligatorio.\n");
        if (txtPhone.getText().trim().isEmpty())
            errors.append("• Teléfono es obligatorio.\n");
        if (cbEmploymentType.getValue() == null)
            errors.append("• Tipo de empleo es obligatorio.\n");

        if (!errors.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Campos incompletos", errors.toString());
            return false;
        }
        return true;
    }

    private void clearFields() {
        txtIdNumber.clear();
        txtFirstName.clear();
        txtLastName.clear();
        dpBirthDate.setValue(null);
        cbGender.setValue(null);
        cbMaritalStatus.setValue(null);
        cbBloodType.setValue(null);
        txtPersonalEmail.clear();
        txtInstitutionalEmail.clear();
        txtPhone.clear();
        cbEmploymentType.setValue(null);
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