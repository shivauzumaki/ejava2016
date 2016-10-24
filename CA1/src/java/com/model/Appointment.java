/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author Vishnu
 */
public class Appointment {
    
    private Integer appointmentID;
    private String description;
    private long appointmentDate;
    private String personId;

    public Integer getAppointmentID() {
        return appointmentID;
    }
    public void setAppointmentID(Integer appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public long getAppointmentDate() {
        return appointmentDate;
    }
    public void setAppointmentDate(long appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getPersonId() {
        return personId;
    }
    public void setPersonId(String personId) {
        this.personId = personId;
    }
    
    
    
}
