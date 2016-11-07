/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca3.rest;

import com.ca3.business.DeliveryBean;
import com.ca3.business.PodBean;
import com.ca3.model.Pod;
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
public class DeliveryResource {

    @EJB
    private DeliveryBean deliveryBean;
    @EJB private PodBean podBean;

    @GET
    @Produces("application/json")
    public Response getItems() {
        
        
        List<Pod> podList= podBean.getPods();
        
        JsonArrayBuilder podArray=Json.createArrayBuilder();
        
        podList.stream().forEach((pod) -> {
            JsonObjectBuilder podobj = Json.createObjectBuilder();
            podobj.add("teamId", "1856edd8")
                    .add("podId",pod.getPodId())
                    .add("address", pod.getDelivery().getAddress())
                    .add("name", pod.getDelivery().getName())
                    .add("phone", pod.getDelivery().getPhone());
            podArray.add(podobj);
        });
        System.out.println("EXited");
        //System.out.println("Returning from AppointmentResource !!!!");
        return Response.ok(podArray.build()).build();

//        System.out.println("Entered !!!!!");
//        
//        List<Pod> podList = podBean.getPods();
//        System.out.println("SIZE  >>" + podList.size());
//        JsonArrayBuilder podArray = Json.createArrayBuilder();
//        //for(Pod pod : podList) {
//            podList.stream().forEach((pod) -> {
//            podArray.add(Json.createObjectBuilder().add("teamId", "1856edd8")
//                    .add("podId", pod.getPodId())
//                    .add("address", pod.getDelivery().getAddress())
//                    .add("name", pod.getDelivery().getName())
//                    .add("phone", pod.getDelivery().getPhone())
//                    .build());
//            });
//            //System.out.println("POD ID" + pod.getPodId());
//            System.out.println("Array >>> "+podArray);
//        //}
//        System.out.println(">>> "+podArray.build());
//        System.out.println("Exiting !!!");
//        //return (Response.ok(podArray.build()).build());
//        return (Response.status(Response.Status.OK).entity(podArray.build()).build());
    }

}
