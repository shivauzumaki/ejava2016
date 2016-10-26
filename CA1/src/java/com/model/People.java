package com.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Vishnu
 */
@Entity
@Table(name="people")
public class People implements Serializable {
    
    @Id
    private String pid;
    private String name;
    private String email;
    
    @OneToMany(mappedBy = "people")
    private List<Appointment> appointment;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<Appointment> getAppointment() {
        return appointment;
    }
    public void setAppointment(List<Appointment> appointment) {
        this.appointment = appointment;
    }
    
    
}
