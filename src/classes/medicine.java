/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bob
 */
public class medicine {
    private int id;
    private String name;
    private double dose;
    private int quantity;

    public static ObservableList<medicine> medicines = FXCollections.observableArrayList();
    
    public medicine() {
    }

    public medicine(int id, String name, double dose, int quantity) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDose() {
        return dose;
    }

    public void setDose(double dose) {
        this.dose = dose;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    
}
