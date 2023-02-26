/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OperationDoctor;

import classes.Patient;
import finalproject.DBConnection;
import finalproject.LoginDoctorController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bob
 */
public class ViewINFOController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxViewINFO;
    @FXML
    private TextField textfieldView;
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
    private TableView<Patient> tableView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DBConnection.getConnection();
        comboBoxViewINFO.getItems().add("ID");
        comboBoxViewINFO.getItems().add("Name");
        comboBoxViewINFO.getItems().add("Age");
        comboBoxViewINFO.getItems().add("Gender");
        comboBoxViewINFO.getItems().add("Image");
        comboBoxViewINFO.getItems().add("Problem");
        comboBoxViewINFO.getItems().add("Entrance Date");
        comboBoxViewINFO.getItems().add("Doctor ID");
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        tcGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tcImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        tcProblem.setCellValueFactory(new PropertyValueFactory<>("problem"));
        tcDate.setCellValueFactory(new PropertyValueFactory<>("entranceDate"));
        tcDoctorID.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
    }

    @FXML
    private void BackBtnHandler(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ViewID.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ViewINFObtnHandler(ActionEvent event) throws SQLException {
        String selected = comboBoxViewINFO.getSelectionModel().getSelectedItem();
        if (selected.equalsIgnoreCase("ID")) {
            PreparedStatement selectPatients = DBConnection.connection.prepareStatement("select * from patient where id = '" + textfieldView.getText()+"'");
            ResultSet rs = selectPatients.executeQuery();
            while (rs.next()) {
                if (true) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String gender = rs.getString("gender");
                    String image = rs.getString("image");
                    String problem = rs.getString("problem");
                    Date date = rs.getDate("entranceDate");
                    int doctorid = rs.getInt("doctor_id");
                    Patient patient = new Patient(id, name, age, gender, image, problem, date, doctorid);
                    tableView.getItems().add(patient);
                }
            }
        }else if(selected.equalsIgnoreCase("Name")) {
            PreparedStatement selectPatients = DBConnection.connection.prepareStatement("select * from patient where name = '" + textfieldView.getText()+"'");
            ResultSet rs = selectPatients.executeQuery();
            while (rs.next()) {
                if (true) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String gender = rs.getString("gender");
                    String image = rs.getString("image");
                    String problem = rs.getString("problem");
                    Date date = rs.getDate("entranceDate");
                    int doctorid = rs.getInt("doctor_id");
                    Patient patient = new Patient(id, name, age, gender, image, problem, date, doctorid);
                    tableView.getItems().add(patient);
                }
            }
        }else if(selected.equalsIgnoreCase("Age")) {
            PreparedStatement selectPatients = DBConnection.connection.prepareStatement("select * from patient where age = '" + textfieldView.getText()+"'");
            ResultSet rs = selectPatients.executeQuery();
            while (rs.next()) {
                if (true) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String gender = rs.getString("gender");
                    String image = rs.getString("image");
                    String problem = rs.getString("problem");
                    Date date = rs.getDate("entranceDate");
                    int doctorid = rs.getInt("doctor_id");
                    Patient patient = new Patient(id, name, age, gender, image, problem, date, doctorid);
                    tableView.getItems().add(patient);
                    
                }
            }
        }else if(selected.equalsIgnoreCase("Gender")) {
            PreparedStatement selectPatients = DBConnection.connection.prepareStatement("select * from patient where gender = '" + textfieldView.getText()+"'");
            ResultSet rs = selectPatients.executeQuery();
            while (rs.next()) {
                if (true) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String gender = rs.getString("gender");
                    String image = rs.getString("image");
                    String problem = rs.getString("problem");
                    Date date = rs.getDate("entranceDate");
                    int doctorid = rs.getInt("doctor_id");
                    Patient patient = new Patient(id, name, age, gender, image, problem, date, doctorid);
                    tableView.getItems().add(patient);
                } 
            }
        }else if(selected.equalsIgnoreCase("Image")) {
            PreparedStatement selectPatients = DBConnection.connection.prepareStatement("select * from patient where image = '" + textfieldView.getText()+"'");
            ResultSet rs = selectPatients.executeQuery();
            while (rs.next()) {
                if (true) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String gender = rs.getString("gender");
                    String image = rs.getString("image");
                    String problem = rs.getString("problem");
                    Date date = rs.getDate("entranceDate");
                    int doctorid = rs.getInt("doctor_id");
                    Patient patient = new Patient(id, name, age, gender, image, problem, date, doctorid);
                    tableView.getItems().add(patient);
                } 
            }
        }else if(selected.equalsIgnoreCase("Problem")) {
            PreparedStatement selectPatients = DBConnection.connection.prepareStatement("select * from patient where problem = '" + textfieldView.getText()+"'");
            ResultSet rs = selectPatients.executeQuery();
            while (rs.next()) {
                if (true) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String gender = rs.getString("gender");
                    String image = rs.getString("image");
                    String problem = rs.getString("problem");
                    Date date = rs.getDate("entranceDate");
                    int doctorid = rs.getInt("doctor_id");
                    Patient patient = new Patient(id, name, age, gender, image, problem, date, doctorid);
                    tableView.getItems().add(patient);
                }
            }
        }else if(selected.equalsIgnoreCase("Entrance Date")) {
            PreparedStatement selectPatients = DBConnection.connection.prepareStatement("select * from patient where entranceDate = '" + textfieldView.getText()+"'");
            ResultSet rs = selectPatients.executeQuery();
            while (rs.next()) {
                if (true) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String gender = rs.getString("gender");
                    String image = rs.getString("image");
                    String problem = rs.getString("problem");
                    Date date = rs.getDate("entranceDate");
                    int doctorid = rs.getInt("doctor_id");
                    Patient patient = new Patient(id, name, age, gender, image, problem, date, doctorid);
                    tableView.getItems().add(patient);

                } 
            }
        }else if(selected.equalsIgnoreCase("Doctor ID")) {
            PreparedStatement selectPatients = DBConnection.connection.prepareStatement("select * from patient where doctor_id = '" + textfieldView.getText()+"'");
            ResultSet rs = selectPatients.executeQuery();
            while (rs.next()) {
                if (true) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String gender = rs.getString("gender");
                    String image = rs.getString("image");
                    String problem = rs.getString("problem");
                    Date date = rs.getDate("entranceDate");
                    int doctorid = rs.getInt("doctor_id");
                    Patient patient = new Patient(id, name, age, gender, image, problem, date, doctorid);
                    tableView.getItems().add(patient);
                    
                }
            }
        
        }
    }


}
