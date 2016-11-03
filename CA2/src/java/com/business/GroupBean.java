/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import com.model.Group;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vishnu
 */
@Stateless
public class GroupBean {
    
    @PersistenceContext EntityManager em;
    
    public void addGroup(Group group){
    
        em.persist(group);
        System.out.println(">>> Added group");
        
    }
}
