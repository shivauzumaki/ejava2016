/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Vishnu
 */
@Entity
@Table(name="groups")
public class Group implements Serializable {
    
    @Id
    private String userid;
    @Id
    private String groupid;

    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGroupid() {
        return groupid;
    }
    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }
}
