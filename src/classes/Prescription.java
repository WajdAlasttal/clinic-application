/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Date;

/**
 *
 * @author WajdAlAsttal
 */
public class Prescription {
    int pat_id;
    int medicine_id;
    int doctors_id;
    Date publishDate;

    public Prescription() {
    }

    public Prescription(int pat_id, int medicine_id, int doctors_id, Date publishDate) {
        this.pat_id = pat_id;
        this.medicine_id = medicine_id;
        this.doctors_id = doctors_id;
        this.publishDate = publishDate;
    }
    
    

    public int getPat_id() {
        return pat_id;
    }

    public void setPat_id(int pat_id) {
        this.pat_id = pat_id;
    }

    public int getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(int medicine_id) {
        this.medicine_id = medicine_id;
    }

    public int getDoctors_id() {
        return doctors_id;
    }

    public void setDoctors_id(int doctors_id) {
        this.doctors_id = doctors_id;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
