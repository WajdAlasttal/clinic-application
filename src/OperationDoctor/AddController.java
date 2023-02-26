/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OperationDoctor;

import finalproject.DBConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bob
 */
public class AddController implements Initializable {

    @FXML
    private TextField textfieldID;
    @FXML
    private TextField textfieldName;
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
    private Button ButtonAdd;
    @FXML
    private Button ButtonBack;
    ToggleGroup toggleGroup;
    Statement statement;
    String gender;
    @FXML
    private Label successLabel;
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

    }

    @FXML
    private void ButtonAddHandel(ActionEvent event) throws SQLException {
         if (validateFields()) {
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
            PreparedStatement ps = DBConnection.connection.prepareStatement("insert into patient(id,name,age,gender,image,problem,entranceDate,doctor_id) values(?,?,?,?,?,?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, gender);
            ps.setString(5, image);
            ps.setString(6, problem);
            ps.setDate(7, date);
            ps.setInt(8, doctorID);
            int rs = ps.executeUpdate();
            successLabel.setText("Successfully Added...");

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

    @FXML
    private void ButtonBackHandel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/finalproject/doctorPanel.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void getFromFile(MouseEvent event) {
        Stage stage = (Stage) ButtonAdd.getScene().getWindow();
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
