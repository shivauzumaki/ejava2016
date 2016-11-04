/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import com.model.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vishnu
 */
@Stateless
public class UserBean {
    
    @PersistenceContext
    EntityManager em;
    
    public User registerUser(User user){
        
        User duplicateUser = em.find(User.class, user.getUsername());
        
        if(duplicateUser==null){
            em.persist(user);
            return user;
        }
        
        return null;
    }
    
}
