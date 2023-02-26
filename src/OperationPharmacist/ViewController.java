/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OperationPharmacist;

import classes.medicine;
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
public class ViewController implements Initializable {

    @FXML
    private Label Found;
    @FXML
    private TableColumn<medicine, Integer> tcID;
    @FXML
    private TableColumn<medicine, String> tcName;
    @FXML
    private TableView<medicine> tableView;
    @FXML
    private TableColumn<medicine, Double> tcDose;
    @FXML
    private TableColumn<medicine, Integer> tcQuantity;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DBConnection.getConnection();
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcDose.setCellValueFactory(new PropertyValueFactory<>("dose"));
        tcQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
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
    private void ViewbtnHandler(ActionEvent event) throws SQLException {
        PreparedStatement selectMedicine = DBConnection.connection.prepareStatement("select * from medicine");
        ResultSet rs = selectMedicine.executeQuery();
        while (rs.next()) {
            if (true) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double dose = rs.getDouble("dose");
                int quantity = rs.getInt("quantity");
                medicine medicine = new medicine(id, name, dose, quantity);
                tableView.getItems().add(medicine);
            }
        }
    }
    
}
