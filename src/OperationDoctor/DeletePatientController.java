/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OperationDoctor;

import classes.Patient;
import finalproject.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WajdAlAsttal
 */
public class DeletePatientController implements Initializable {

    @FXML
    private TableView<Patient> tableview;
    @FXML
    private TableColumn<Patient, Integer> idtablecolumn;
    @FXML
    private TableColumn<Patient, String> nametablecolumn;
    @FXML
    private TableColumn<Patient, Integer> agetablecolumn;
    @FXML
    private TableColumn<Patient, String> gendertablecolumn;
    @FXML
    private TableColumn<Patient, String> imagetablecolumn;
    @FXML
    private TableColumn<Patient, String> problemtablecolumn;
    @FXML
    private TableColumn<Patient,Date> datetablecolumn;
    @FXML
    private TableColumn<Patient, Integer> doctoridtablecolumn;
    @FXML
    private Label successDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DBConnection.getConnection();
        ObservableList<Patient> PatientList= DBConnection.selectAllPatients();
        //add list to the tableview
        idtablecolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nametablecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        agetablecolumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        gendertablecolumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        imagetablecolumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        problemtablecolumn.setCellValueFactory(new PropertyValueFactory<>("problem"));
        datetablecolumn.setCellValueFactory(new PropertyValueFactory<>("entranceDate"));
        doctoridtablecolumn.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        tableview.setItems(PatientList);

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
            ex.getMessage();
        }
    }

    @FXML
    private void DeletebtnHandler(ActionEvent event){
        if(validateFields()){
        try {
                Patient pat = tableview.getSelectionModel().getSelectedItem();
                PreparedStatement delete
                        = DBConnection.connection.prepareStatement("delete from patient where id=" + pat.getId());
                int rs = delete.executeUpdate();
                successDelete.setText("Deleted Successfully...");
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
        }
        private boolean validateFields(){
        if (tableview.getSelectionModel().getSelectedItem()!=null) {
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate fields");
            alert.setHeaderText(null);
            alert.setContentText("Please select a row to delete");
            alert.show();
            return false;
        }
    }
        
        
    }
    

