/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OperationDoctor;

import classes.Prescription;
import finalproject.DBConnection;
import static finalproject.DBConnection.connection;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WajdAlAsttal
 */
public class HistoryController implements Initializable {

    @FXML
    private Label SuccessLabel;
    @FXML
    private TableView<Prescription> tableView;
    @FXML
    private TableColumn<Prescription, Integer> patTableCol;
    @FXML
    private TableColumn<Prescription, Integer> medTableCol;
    @FXML
    private TableColumn<Prescription, Integer> DocTableCol;
    @FXML
    private TableColumn<Prescription, Date> dateTableCol;
    @FXML
    private ComboBox<Integer> patIdCombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DBConnection.getConnection();
        PreparedStatement selectPatients;
        try {
            selectPatients = connection.prepareStatement("select id from patient");
            ResultSet rs = selectPatients.executeQuery();
            while (rs.next()) {
                int patientId = rs.getInt("id");
                patIdCombo.getItems().add(patientId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }

        patTableCol.setCellValueFactory(new PropertyValueFactory<>("pat_id"));
        medTableCol.setCellValueFactory(new PropertyValueFactory<>("medicine_id"));
        DocTableCol.setCellValueFactory(new PropertyValueFactory<>("doctors_id"));
        dateTableCol.setCellValueFactory(new PropertyValueFactory<>("publishDate"));

    }

    @FXML
    private void HistoryBtnHandler(ActionEvent event) throws SQLException {
        int selected = patIdCombo.getSelectionModel().getSelectedItem();
        PreparedStatement selectPatients = DBConnection.connection.prepareStatement("select * from prescription where pat_id LIKE '%" + selected + "%'");
        ResultSet rs = selectPatients.executeQuery();
        while (rs.next()) {
            int pat_id = rs.getInt("pat_id");
            Date date = rs.getDate("publishDate");
            int medicine_id = rs.getInt("medicine_id");
            int doctorid = rs.getInt("doctors_id");
            Prescription prescription = new Prescription(pat_id, doctorid, doctorid, date);
            tableView.getItems().add(prescription);
            SuccessLabel.setText("Done");
        }
    

    }

    @FXML
    private void BackBtnHandler(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("prescription.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

}
