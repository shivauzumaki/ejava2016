/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.business.NoteBean;
import com.model.Note;
import com.session.SessionUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Vishnu
 */
@Named
@RequestScoped
public class NoteView {
    
    private Note note;
    private List<String> categories;
    @EJB private NoteBean noteBean;
    private List<Note> noteList;
    
    @PostConstruct
    public void init() {
        note = new Note();
        String[] catogoriesArray = {"Social", "For Sale", "Jobs","Tuition"};
        categories = new ArrayList<>();
        categories = Arrays.asList(catogoriesArray);
    }

    public List<String> getCategories() {
        return categories;
    }

    public Note getNote() {
        return note;
    }
    public void setNote(Note note) {
        this.note = note;
    }

    public List<Note> getNoteList() {
        return noteList;
    }
  
    public void addNote(){
        
        System.out.println("Session >>>>>>> "+ SessionUtils.getUserId());
        System.out.println("Adding note\n "+note.getTitle());
        note.setUserid(SessionUtils.getUserId());
        noteBean.addNote(note);
        
    }
    
    public String getNotes() {
        
        this.noteList = new ArrayList<>();
        
        this.noteList = noteBean.getNotes(SessionUtils.getUserId());
        System.out.println("My notes list "+ noteList.size());
        return ("displayMyNotes?faces-redirect=true");
    }
    
      
    public String back(){
       return ("main");
    }
}
