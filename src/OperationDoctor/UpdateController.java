/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OperationDoctor;

import classes.Patient;
import finalproject.DBConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bob
 */
public class UpdateController implements Initializable {

    @FXML
    private Button ButtonBack;
    @FXML
    private TextField textfieldID;
    @FXML
    private RadioButton radioButtonMaleID;
    @FXML
    private RadioButton radioButtonFemaleiD;
    @FXML
    private TextField textfieldAge;
    @FXML
    private TextField textfieldImage;
    @FXML
    private TextField textfieldProblem;
    @FXML
    private DatePicker datePickerDate;
    @FXML
    private ComboBox<String> comboBoxDoctorID;
    @FXML
    private TextField textfieldName;
    @FXML
    private TableView<Patient> tableView;
    @FXML
    private TableColumn<Patient, Integer> tcID;
    @FXML
    private TableColumn<Patient, String> tcName;
    @FXML
    private TableColumn<Patient, Integer> tcAge;
    @FXML
    private TableColumn<Patient, String> tcGender;
    @FXML
    private TableColumn<Patient, String> tcImage;
    @FXML
    private TableColumn<Patient, String> tcProblem;
    @FXML
    private TableColumn<Patient, Date> tcDate;
    @FXML
    private TableColumn<Patient, Integer> tcDoctorID;
    @FXML
    private Button ButtonUpdate;
    @FXML
    private Label successLabel;
    ToggleGroup toggleGroup;
    String gender;
    private File file;
    private FileChooser fileChooser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DBConnection.getConnection();
        toggleGroup = new ToggleGroup();
        comboBoxDoctorID.getItems().addAll("1", "2");
        radioButtonMaleID.setToggleGroup(toggleGroup);
        radioButtonFemaleiD.setToggleGroup(toggleGroup);
        ObservableList<Patient> PatientList = DBConnection.selectAllPatients();
        //add list to the tableview
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        tcGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tcImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        tcProblem.setCellValueFactory(new PropertyValueFactory<>("problem"));
        tcDate.setCellValueFactory(new PropertyValueFactory<>("entranceDate"));
        tcDoctorID.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        tableView.setItems(PatientList);
        tableView.getSelectionModel().selectedItemProperty().addListener(
                event -> showSelectedPatient());
    }

    @FXML
    private void ButtonBackHandel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/finalproject/doctorPanel.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ButtonUpdateHandel(ActionEvent event) throws SQLException {
        if (validateFields()) {

            Patient patient = tableView.getSelectionModel().getSelectedItem();
            int index = tableView.getSelectionModel().getSelectedIndex();
            patient.setId(Integer.parseInt(textfieldID.getText()));
            patient.setName(textfieldName.getText());
            patient.setAge(Integer.parseInt(textfieldAge.getText()));
            if (radioButtonMaleID.isSelected()) {
                patient.setGender("male");
            } else if (radioButtonFemaleiD.isSelected()) {
                patient.setGender("female");
            }
            patient.setImage(textfieldImage.getText());
            patient.setProblem(textfieldProblem.getText());
            patient.setEntranceDate(Date.valueOf(datePickerDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            String doctorIDD = comboBoxDoctorID.getSelectionModel().getSelectedItem();
            if (comboBoxDoctorID.getSelectionModel().getSelectedItem().equalsIgnoreCase("1")) {
                patient.setDoctorId(Integer.parseInt(doctorIDD));
            } else if (comboBoxDoctorID.getSelectionModel().getSelectedItem().equalsIgnoreCase("2")) {
                patient.setDoctorId(Integer.parseInt(doctorIDD));
            }
            tableView.getItems().set(index, patient);

            Integer id = Integer.parseInt(textfieldID.getText());
            String name = textfieldName.getText();
            Integer age = Integer.parseInt(textfieldAge.getText());
            if (radioButtonMaleID.isSelected()) {
                gender = "male";
            } else if (radioButtonFemaleiD.isSelected()) {
                gender = "female";
            }
            String image = textfieldImage.getText();
            String problem = textfieldProblem.getText();
            Date date = Date.valueOf(datePickerDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            Integer doctorID = Integer.parseInt(comboBoxDoctorID.getSelectionModel().getSelectedItem());
            PreparedStatement update
                    = DBConnection.connection.prepareStatement("Update patient set name = ?, age = ?, gender = ?, image = ?, problem = ?, entranceDate = ?, doctor_id = ? where id = ?");
            update.setString(1, name);
            update.setInt(2, age);
            update.setString(3, gender);
            update.setString(4, image);
            update.setString(5, problem);
            update.setDate(6, date);
            update.setInt(7, doctorID);
            update.setInt(8, id);

            int rs = update.executeUpdate();

            successLabel.setText("Successfully Updated...");

            textfieldID.setText("");
            textfieldName.setText("");
            textfieldAge.setText("");
            textfieldImage.setText("");
            textfieldProblem.setText("");
            datePickerDate.setValue(null);
            radioButtonFemaleiD.selectedProperty().setValue(false);
            radioButtonMaleID.selectedProperty().setValue(false);
            comboBoxDoctorID.getSelectionModel().clearSelection();
        } else {
            successLabel.setText("You must enter the data . . !");
        }

    }

    private void showSelectedPatient() {
        Patient patient = tableView.getSelectionModel().getSelectedItem();
        if (patient != null) {
            textfieldID.setText(String.valueOf(patient.getId()));
            textfieldName.setText(patient.getName());
            textfieldAge.setText(String.valueOf(patient.getAge()));
            if (patient.getGender().equalsIgnoreCase("male")) {
                radioButtonMaleID.setSelected(true);
                radioButtonFemaleiD.setSelected(false);
            } else if (patient.getGender().equalsIgnoreCase("female")) {
                radioButtonFemaleiD.setSelected(true);
                radioButtonMaleID.setSelected(false);
            }
            textfieldImage.setText(patient.getImage());
            textfieldProblem.setText(patient.getProblem());
            LocalDate date = convertToLocalDateViaSqlDate(patient.getEntranceDate());
            datePickerDate.setValue(date);
            if (patient.getDoctorId() == 1) {
                comboBoxDoctorID.getSelectionModel().select(0);
            } else if (patient.getDoctorId() == 2) {
                comboBoxDoctorID.getSelectionModel().select(1);
            }
        }
    }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    @FXML
    private void getFromFile(ActionEvent event) {
        Stage stage = (Stage) ButtonUpdate.getScene().getWindow();
        fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(stage);
        textfieldImage.setText(file.getName());
    }
        private boolean validateFields(){
        if (textfieldID.getText() != null && textfieldName.getText() != null && textfieldAge != null
                && radioButtonFemaleiD.isSelected() || radioButtonMaleID.isSelected()
                && textfieldImage != null && textfieldProblem != null
                && datePickerDate != null && comboBoxDoctorID.getSelectionModel().getSelectedItem() != null) {
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate fields");
            alert.setHeaderText(null);
            alert.setContentText("Please enter into the fields");
            alert.show();
            return false;
        }
    }

}
