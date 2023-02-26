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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WajdAlAsttal
 */
public class SearchController implements Initializable {

    @FXML
    private TextField searchtxtfield;
    @FXML
    private Label Found;
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
    private TableColumn<Patient, Date> datetablecolumn;
    @FXML
    private TableColumn<Patient, Integer> doctoridtablecolumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DBConnection.getConnection();
        idtablecolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nametablecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        agetablecolumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        gendertablecolumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        imagetablecolumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        problemtablecolumn.setCellValueFactory(new PropertyValueFactory<>("problem"));
        datetablecolumn.setCellValueFactory(new PropertyValueFactory<>("entranceDate"));
        doctoridtablecolumn.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
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
    private void SearchbtnHandler(ActionEvent event) {
        try {
            PreparedStatement selectbooks
                    = DBConnection.connection.prepareStatement("select * from patient where name='" + searchtxtfield.getText() + "' OR id= '"+searchtxtfield.getText()+"'");
            ResultSet rs = selectbooks.executeQuery();
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
                    Patient myUser = new Patient(id, name, age, gender, image, problem, date, doctorid);
                    tableview.getItems().add(myUser);
                    Found.setText("Founded");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
