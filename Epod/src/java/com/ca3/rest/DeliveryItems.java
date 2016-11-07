/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca3.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Vishnu
 */
@RequestScoped
@Path("/items")
public class DeliveryItems {
    
    @GET
    @Produces("appication/json")
    public Response getItems(){
        
        
        return
    }
    
}
