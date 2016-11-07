/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca3.rest;

import com.ca3.business.DeliveryBean;
import com.ca3.model.Delivery;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author Vishnu
 */
@RequestScoped
@Path("/items")
public class DeliveryItems {
    
    @EJB private DeliveryBean deliveryBean;
    
    @GET
    @Produces("appication/json")
    public Response getItems(){
        
        List<Delivery> deliveryList = deliveryBean.getItems();
//        
//        JsonObjectBuilder deliveryObject = Json.createObjectBuilder();
//        JsonArrayBuilder arrayBuilder=Json.createArrayBuilder();
//        deliveryObject.stream().forEach((app) -> {
//            arrayBuilder.add(Json.createObjectBuilder()
//                    .add("appointmentId",app.getAppt_id())
//                    .add("dateTime", app.getAppt_date().getTime())
//                    .add("description", app.getDescription())
//                    .add("personId", app.getPeople().getPid())
//                    .build()
//            );
//        });
        return null;
    }
    
}
