/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OperationPharmacist;

import classes.medicine;
import finalproject.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bob
 */
public class DeleteController implements Initializable {

    @FXML
    private TableView<medicine> tableview;
    @FXML
    private TableColumn<medicine, Integer> idtablecolumn;
    @FXML
    private TableColumn<medicine, String> nametablecolumn;
    @FXML
    private TableColumn<medicine, Double> dosetablecolumn;
    @FXML
    private TableColumn<medicine, Integer> quantitytablecolumn;
    @FXML
    private Label successDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DBConnection.getConnection();
        ObservableList<medicine> medicineList = DBConnection.selectAllMedicines();
        idtablecolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nametablecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dosetablecolumn.setCellValueFactory(new PropertyValueFactory<>("dose"));
        quantitytablecolumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tableview.setItems(medicineList);
    }

    @FXML
    private void BackBtnHandler(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/finalproject/pharmPanel.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void DeletebtnHandler(ActionEvent event) throws SQLException {
        if (validateFields()) {
            medicine mdc = tableview.getSelectionModel().getSelectedItem();
            PreparedStatement delete = DBConnection.connection.prepareStatement("delete from medicine where id=" + mdc.getId());
            int rs = delete.executeUpdate();
            successDelete.setText("Deleted Successfully...");
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