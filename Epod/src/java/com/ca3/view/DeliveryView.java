/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca3.view;

import com.ca3.business.DeliveryBean;
import com.ca3.model.Delivery;
import java.util.Date;
import java.util.UUID;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TemporalType;
import static javax.persistence.TemporalType.DATE;

/**
 *
 * @author Vishnu
 */
@Named
@RequestScoped
public class DeliveryView {
    
    @EJB private DeliveryBean deliveryBean;
    
    private int packageId;
    private Delivery delivery;
    private String name;
    private String address;
    private String phone;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void create(){  
        
        delivery = new Delivery();
        delivery.setName(name);
        delivery.setPhone(phone);
        delivery.setCreateDate(new Date());
        delivery.setAddress(address);
        deliveryBean.save(delivery);
    }
}
