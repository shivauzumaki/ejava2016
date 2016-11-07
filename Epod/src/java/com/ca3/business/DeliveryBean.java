/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca3.business;

import com.ca3.model.Delivery;
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
public class DeliveryBean {
    
    private static final String getItems = "select d from Delivery d";
    
    @PersistenceContext private EntityManager em;
    
    public void save(Delivery delivery) {
		em.persist(delivery);
	} 
    
    public List<Delivery> getItems(){
        
        TypedQuery<Delivery> deliveries = em.createQuery(getItems, Delivery.class);
        return deliveries.getResultList();
    }
            
}
