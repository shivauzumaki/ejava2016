

package com.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Vishnu
 */
@Entity
@Table(name="appointment")
public class Appointment implements Serializable {
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int appt_id;
    private String description;
    private Date appt_date;
    
    @JoinColumn(name="pid", referencedColumnName="pid")
    @ManyToOne
    private People people;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public People getPeople() {
        return people;
    }
    public void setPeople(People people) {
        this.people = people;
    }

    public int getAppt_id() {
        return appt_id;
    }

    public void setAppt_id(int appt_id) {
        this.appt_id = appt_id;
    }

    public Date getAppt_date() {
        return appt_date;
    }

    public void setAppt_date(Date appt_date) {
        this.appt_date = appt_date;
    }
    
    
}
