/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import com.model.Note;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Vishnu
 */
@Stateless
public class NoteBean {
    
    private static final String getNotesSQL = "select u.notes from User u where u.username= :userid";
    private static final String getNoteByCategorySQL = 
                    "select n from Note n where n.category= :category";
    private static final String getAllNoteSQL = "select n from Note n";
    
    @PersistenceContext
    EntityManager em;
    
    public void addNote(Note note) {
        
        String uniqueID = UUID.randomUUID().toString().substring(0, 8);
        note.setNoteid(uniqueID);
        note.setDate(new Date());
        em.persist(note);
    }
    
    public List<Note> getIndividualNotes(String userid) {
        
        TypedQuery<Note> query = em.createQuery(getNotesSQL, Note.class);
        query.setParameter("userid", userid);
        return query.getResultList();
    }
    
    public List<Note> getNotes(String category){
        
        TypedQuery<Note> query; 
        System.out.println("CAtegory " + category);
        if(!category.equalsIgnoreCase("all")){
            query = em.createQuery(getNoteByCategorySQL, Note.class);
            query.setParameter("category", category);   
        }else{
            System.out.println("Query correct");
            query = em.createQuery(getAllNoteSQL, Note.class);
        }
        System.out.println("Query exit");
        return query.getResultList();
    }
}
