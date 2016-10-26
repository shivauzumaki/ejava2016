/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest;

import com.business.AppointmentBean;
import com.business.PersonBean;
import com.model.Appointment;
import java.util.List;
import java.util.concurrent.ExecutorService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

/**
 *
 * @author Vishnu
 */

@RequestScoped
@Path("/appointment")
public class AppointmentResource {
    
    @EJB private PersonBean personBean;
    @EJB private AppointmentBean appointmentBean;
    private ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();
    
    /**
     *
     * @param email
     * @param async
     * @return
     */
    @GET
    @Path("/{email}")
    public void getAppointment(@Suspended
    final AsyncResponse asyncResponse, @PathParam(value = "email")
    final String email) {
        
        executorService.submit(new Runnable() {
            public void run() {
                asyncResponse.resume(doGetAppointment(email));
            }
        });
        
    }

    private Response doGetAppointment(@PathParam("email") String email) {
        
        List<Appointment> appointments=appointmentBean.getAllAppointment(email);
        JsonArrayBuilder arrayBuilder=Json.createArrayBuilder();
        appointments.stream().forEach((app) -> {
            arrayBuilder.add(Json.createObjectBuilder()
                    .add("appointmentId",app.getAppt_id())
                    .add("dateTime", app.getAppt_date().getTime())
                    .add("description", app.getDescription())
                    .add("personId", app.getPeople().getPid())
                    .build()
            );
        });
        //System.out.println("Returning from AppointmentResource !!!!");
        return (Response.status(Response.Status.CREATED).entity(arrayBuilder.build()).build());
    }
    
}
