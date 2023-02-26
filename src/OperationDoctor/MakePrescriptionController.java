/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OperationDoctor;

import finalproject.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WajdAlAsttal
 */
public class MakePrescriptionController implements Initializable {

    @FXML
    private Label SuccessLabel;
    @FXML
    private ComboBox<Integer> PatientCombo;
    @FXML
    private ComboBox<Integer> DoctorCombo;
    @FXML
    private ComboBox<Integer> MedicineCombo;
    @FXML
    private DatePicker datePick;
    Statement statement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DBConnection.getConnection();
        
        PreparedStatement ps;
        
        try {
            ps = DBConnection.connection.prepareStatement("select id from doctors");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int doctorId = rs.getInt("id");
                DoctorCombo.getItems().add(doctorId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MakePrescriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ps = DBConnection.connection.prepareStatement("select id from medicine");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int medicineId = rs.getInt("id");
                MedicineCombo.getItems().add(medicineId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MakePrescriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ps = DBConnection.connection.prepareStatement("select id from patient");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int patientId = rs.getInt("id");
                PatientCombo.getItems().add(patientId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MakePrescriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
                     
    }    

    @FXML
    private void MakePrescriptionBtnHandler(ActionEvent event) {
        int patientId = PatientCombo.getSelectionModel().getSelectedItem();
        int doctorId = DoctorCombo.getSelectionModel().getSelectedItem();
        int medicineId= MedicineCombo.getSelectionModel().getSelectedItem();
        Date date = Date.valueOf(datePick.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        try { 
            PreparedStatement ps = DBConnection.connection.prepareStatement("insert into prescription(pat_id,medicine_id,doctors_id,publishDate) values(?,?,?,?)");
            ps.setInt(1, patientId);
            ps.setInt(2, medicineId);
            ps.setInt(3, doctorId);
            ps.setDate(4, date);
            int rs = ps.executeUpdate();
            SuccessLabel.setText("Successfully Added");
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    @FXML
    private void HistoryBtnHandler(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("History.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MakePrescriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void BackBtnHandler(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/finalproject/doctorPanel.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MakePrescriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
