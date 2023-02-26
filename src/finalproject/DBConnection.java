/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import classes.Patient;
import classes.Prescription;
import classes.medicine;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author WajdAlAsttal
 */
public class DBConnection {
    public static Connection connection;
   
   public static void getConnection(){
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           connection
                    = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospital?serverTimezone=UTC",
                                    "root", "");
           System.out.println("Connected to database...");
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   
   public static ObservableList<Patient> selectAllPatients(){
        ObservableList<Patient> allPatient=FXCollections.observableArrayList();
      if( connection==null ){
         getConnection();
                  }
        try {
            PreparedStatement selectpatient=
                    connection.prepareStatement("select * from patient");
        ResultSet rs= selectpatient.executeQuery();
            while (rs.next()) {                
              int id= rs.getInt("id");
              String name= rs.getString("name");
              int age= rs.getInt("age");
              String gender= rs.getString("gender");
              String image= rs.getString("image");
              String problem =rs.getString("problem");
              Date date =rs.getDate("entranceDate");
              int doctorid = rs.getInt("doctor_id");
              Patient myUser=new Patient(id,name,age,gender,image,problem,date,doctorid);
              allPatient.add(myUser);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return allPatient;
    }
   
   public static ObservableList<Prescription> selectAllPrescription(){
        ObservableList<Prescription> allPrescriptions=FXCollections.observableArrayList();
      if( connection==null ){
         getConnection();
                  }
        try {
            PreparedStatement selectPatients=
                    connection.prepareStatement("select * from prescription");
        ResultSet rs= selectPatients.executeQuery();
            while (rs.next()) {                
              int pat_id= rs.getInt("pat_id");
              Date date =rs.getDate("publishDate");
              int medicine_id=rs.getInt("medicine_id");
              int doctorid = rs.getInt("doctors_id");
              Prescription prescription=new Prescription(pat_id, doctorid, doctorid, date);
              allPrescriptions.add(prescription);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return allPrescriptions;
    }
   
   
   
       public static ObservableList<medicine> selectAllMedicines() {
        ObservableList<medicine> allMedicines = FXCollections.observableArrayList();
        if (connection == null) {
            getConnection();
        }
        try {
            PreparedStatement selectMedicines = connection.prepareStatement("select * from medicine");
            ResultSet rs = selectMedicines.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double dose = rs.getDouble("dose");
                int quantity = rs.getInt("quantity");
                medicine myUser = new medicine(id, name, dose, quantity);
                allMedicines.add(myUser);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return allMedicines;
    }
}
