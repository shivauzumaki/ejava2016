/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca3.business;

import com.ca3.model.Pod;
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
        
        return em.createNamedQuery("Pod.findAll", Pod.class).getResultList();
    }
    
    public void update(int podId, String note, byte[] image, Date date) {
        
        Pod pod = em.find(Pod.class, podId);
        
        pod.setImage(image);
        pod.setNote(note);
        pod.setDeliveryDate(date);
        
        em.persist(pod);
    }

    public void updateNew(String ackId, int podId) {
        
        Pod pod = em.find(Pod.class, podId);
        pod.setAckId(ackId);

        em.merge(pod);
    }
}
