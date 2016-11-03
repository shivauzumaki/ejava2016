/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Vishnu
 */
@Entity
@Table(name="notes")
public class Note {
    
    @Id
    private String noteid;
    private String userid;
    private String title;
    private String category;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @ManyToOne
    @PrimaryKeyJoinColumn(name="userid", referencedColumnName = "userid")
    private User user;

    public String getNoteid() {
        return noteid;
    }
    public void setNoteid(String noteid) {
        this.noteid = noteid;
    }
    
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
 
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}