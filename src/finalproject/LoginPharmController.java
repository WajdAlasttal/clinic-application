/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WajdAlAsttal
 */
public class LoginPharmController implements Initializable {

    @FXML
    private Label errorLabel;
    @FXML
    private TextField idtxtfield;
    @FXML
    private TextField passtxtfield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DBConnection.getConnection();
    }    

    @FXML
    private void loginbtnHandler(ActionEvent event) throws IOException {
        int id = Integer.parseInt(idtxtfield.getText());
        String password = passtxtfield.getText();
        if (idtxtfield.getText().isEmpty() && password == null) {
            errorLabel.setText("id and password is empty");
        } else {
            try {
                PreparedStatement ps = DBConnection.connection.prepareStatement("select id, password from Pharmacist where id=? and password=?");
                ps.setInt(1, Integer.parseInt(idtxtfield.getText()));
                ps.setString(2, passtxtfield.getText());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Parent root = FXMLLoader.load(getClass().getResource("pharmPanel.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    errorLabel.setText("Failed to login");
                    idtxtfield.setText("");
                    passtxtfield.setText("");
                }

            } catch (SQLException ex) {
                ex.getMessage();
            }
        }

  
    }
    
}
