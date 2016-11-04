/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.business.NoteBean;
import com.model.Note;
import com.session.SessionUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Vishnu
 */
@Named
@SessionScoped
public class MainView implements Serializable{
    
    @EJB private NoteBean noteBean;
    private List<Note> noteList;
    
    public String createNote() {
        
        return ("createnote?faces-redirect=true");
    }

    public List<Note> getNoteList() {
        return noteList;
    }
    
    public String viewNotes() {
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        
        this.noteList = new ArrayList<>();
        this.noteList = noteBean.getIndividualNotes(ec.getRemoteUser());
        return ("displayMyNotes?faces-redirect=true");
    }
}
