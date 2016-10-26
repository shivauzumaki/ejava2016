package com.business;

import com.model.Appointment;
import com.model.People;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Vishnu
 */
@Stateless
public class AppointmentBean {
    
    private static final String getAppointmentQuery = "select p.appointment from People p where p.email = :email";
    
    @PersistenceContext EntityManager em;
    
    public List<Appointment> getAllAppointment(String email) {
        
        TypedQuery<Appointment> query = em.createQuery(getAppointmentQuery, Appointment.class);
        query.setParameter("email", email);
        return (query.getResultList());
        
    }
    
}
