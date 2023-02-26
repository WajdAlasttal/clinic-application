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
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bob
 */
public class ViewIDController implements Initializable {

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
        Parent root = FXMLLoader.load(getClass().getResource("/finalproject/doctorPanel.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ViewbtnHandler(ActionEvent event) throws SQLException {
        PreparedStatement selectPatients = DBConnection.connection.prepareStatement("select * from patient where doctor_id = " + LoginDoctorController.id);
        ObservableList<Patient> olist = FXCollections.observableArrayList();
        Statement statement = null;
        Connection connection = DBConnection.connection;
        statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("select * from patient WHERE doctor_id=" + LoginDoctorController.id);
        while (rs.next()) {
            Patient s = new Patient();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setAge(rs.getInt("age"));
            s.setGender(rs.getString("gender"));
            s.setImage(rs.getString("image"));
            s.setProblem(rs.getString("problem"));
            s.setEntranceDate(rs.getDate("entranceDate"));
            s.setDoctorId(rs.getInt("doctor_id"));
            olist.add(s);

        }
        ObservableList<Patient> list2 = olist
                .stream()
                .sorted(Comparator.comparing(Patient::getName))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        tableView.setItems(list2);
        tableView.setItems(olist);

    }

    @FXML
    private void ViewINFObtnHandler(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ViewINFO.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("ViewINFO Screen");
        stage.setScene(scene);
        stage.show();
    }

}
