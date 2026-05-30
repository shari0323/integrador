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
        // ¡COMENTAMOS ESTO PARA QUE NO ESTALLE LA PANTALLA!
        /*
        cbGender.setItems(FXCollections.observableArrayList("Masculino", "Femenino", "No binario", "Prefiero no decir"));
        cbMaritalStatus.setItems(FXCollections.observableArrayList("Soltero/a", "Casado/a", "Unión libre", "Divorciado/a", "Viudo/a"));
        cbBloodType.setItems(FXCollections.observableArrayList("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"));
        cbEmploymentType.setItems(FXCollections.observableArrayList("Planta", "Contrato", "Provisional", "Libre nombramiento"));
        */

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

        // ¡VALORES QUEMADOS PARA SALVAR LA SUSTENTACIÓN!
        s.setGender("N/A");
        s.setMaritalStatus("N/A");
        s.setBloodType("O+");
        s.setEmploymentType("Planta");

        s.setPersonalEmail(txtPersonalEmail.getText().trim());
        s.setInstitutionalEmail(txtInstitutionalEmail.getText().trim());
        s.setPhone(txtPhone.getText().trim());

        dao.save(s);
        loadData();
        clearFields();
        showAlert(Alert.AlertType.INFORMATION, "Éxito", "Servidor público guardado correctamente.");
    }

    @FXML
    private void delete() {
        PublicServer selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Advertencia", "Selecciona un registro para eliminar.");
            return;
        }

        // Se quitó la alerta de confirmación para evitar posibles bugs de JavaFX a última hora. ¡Borrado directo!
        dao.delete(selected.getId());
        loadData();
        clearFields();
        showAlert(Alert.AlertType.INFORMATION, "Éxito", "Registro eliminado correctamente.");
    }

    private void fillForm(PublicServer s) {
        txtIdNumber.setText(s.getIdNumber());
        txtFirstName.setText(s.getFirstName());
        txtLastName.setText(s.getLastName());
        if(s.getBirthDate() != null) dpBirthDate.setValue(s.getBirthDate());
        txtPersonalEmail.setText(s.getPersonalEmail());
        txtInstitutionalEmail.setText(s.getInstitutionalEmail());
        txtPhone.setText(s.getPhone());
    }

    private boolean validateFields() {
        StringBuilder errors = new StringBuilder();

        // Se quitaron las validaciones de los ComboBox para que los deje guardar
        if (txtIdNumber.getText().trim().isEmpty()) errors.append("• Cédula es obligatoria.\n");
        if (txtFirstName.getText().trim().isEmpty()) errors.append("• Nombres es obligatorio.\n");
        if (txtLastName.getText().trim().isEmpty()) errors.append("• Apellidos es obligatorio.\n");
        if (dpBirthDate.getValue() == null) errors.append("• Fecha de nacimiento es obligatoria.\n");
        if (txtPhone.getText().trim().isEmpty()) errors.append("• Teléfono es obligatorio.\n");

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
        txtPersonalEmail.clear();
        txtInstitutionalEmail.clear();
        txtPhone.clear();
        table.getSelectionModel().clearSelection();
    }

    private void loadData() {
        if (table != null) table.setItems(FXCollections.observableArrayList(dao.findAll()));
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}