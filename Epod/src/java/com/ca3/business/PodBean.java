/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca3.business;

import com.ca3.model.Pod;
import java.sql.Blob;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vishnu
 */
@Stateless
public class PodBean {
    
    
    @PersistenceContext private EntityManager em;
    
    
    public void save(Pod pod){
        
        em.persist(pod);
    }
    
    public List<Pod> getPods(){
        
        System.out.println("Size >> "+ em.createNamedQuery("Pod.findAll", Pod.class).getResultList().size());
        return em.createNamedQuery("Pod.findAll", Pod.class).getResultList();
    }
    
    public void update(int podId, String note, byte[] image, Date date) {
        
        Pod pod = em.find(Pod.class, podId);
        
        pod.setImage(image);
        pod.setNote(note);
        pod.setDeliveryDate(date);
        
        em.persist(pod);
    }
}
