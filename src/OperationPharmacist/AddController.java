/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OperationPharmacist;

import finalproject.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bob
 */
public class AddController implements Initializable {

    @FXML
    private Button ButtonBack;
    @FXML
    private TextField textfieldID;
    @FXML
    private TextField textfieldName;
    @FXML
    private Button ButtonAdd;
    @FXML
    private Label successLabel;
    @FXML
    private Slider sliderQuantity;
    @FXML
    private Slider sliderDose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DBConnection.getConnection();
    }    

    @FXML
    private void ButtonBackHandel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/finalproject/pharmPanel.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ButtonAddHandel(ActionEvent event) throws SQLException {
        if (validateFields()){
            int id =Integer.parseInt(textfieldID.getText());
            String name = textfieldName.getText();
            double dose = sliderDose.getValue();
            int quantity = (int) sliderQuantity.getValue() ;
            PreparedStatement ps = DBConnection.connection.prepareStatement("insert into medicine(id,name,quantity,dose) values(?,?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, quantity);
            ps.setDouble(4, dose);
            int rs = ps.executeUpdate();
            successLabel.setText("Successfully Added...");
            textfieldID.setText("");
            textfieldName.setText("");
            sliderQuantity.setValue(20);
            sliderDose.setValue(1);
        } else {
            successLabel.setText("You must enter the data . . !");
        }
    }
        private boolean validateFields(){
        if (!(textfieldID.getText().isEmpty()) && !(textfieldName.getText().isEmpty()) && sliderDose.getValue()!=0 && sliderQuantity.getValue()!=0) {
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
