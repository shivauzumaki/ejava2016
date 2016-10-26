/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest;

import com.business.AppointmentBean;
import com.business.PersonBean;
import com.model.People;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 *
 * @author Vishnu
 */
@RequestScoped
@Path("/people")
public class PeopleResource {
    
    @EJB private PersonBean personBean;
    
    private ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();

    @POST
    @Consumes(value = "application/x-www-form-urlencoded")
    public void add(@Suspended final AsyncResponse asyncResponse, final MultivaluedMap<String, String> form) {
        executorService.submit(new Runnable() {
            public void run() {
                asyncResponse.resume(doAdd(form));
            }
        });
    }

    private Response doAdd(MultivaluedMap<String, String> form) {
        String name = form.getFirst("name");
        String email = form.getFirst("email");
        People people = new People();
        people.setName(name);
        people.setEmail(email);
        personBean.addPerson(people);
        
        JsonObject json = Json.createObjectBuilder()
                .add("name", people.getName())
                .add("email", people.getEmail())
                .build();
        
        return Response.status(Response.Status.CREATED).entity(json).build();
    }

    @GET
    @Produces(value = "application/json")
    public void getPeople(@Suspended final AsyncResponse asyncResponse, @QueryParam(value = "email") final String email) {
        executorService.submit(new Runnable() {
            public void run() {
                asyncResponse.resume(doGetPeople(email));
            }
        });
    }

    private Response doGetPeople(@QueryParam("email") String email) {
        
        Optional<People> people = personBean.findByEmail(email);
        
        if(!people.isPresent()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        People ppl = people.get();
        
        JsonObject json = Json.createObjectBuilder()
                .add("pid", ppl.getPid())
                .add("name", ppl.getName())
                .add("email", ppl.getEmail())
                .build();
        
        return Response.ok(json).build();
    }
    
}
