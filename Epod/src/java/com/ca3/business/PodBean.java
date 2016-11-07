/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca3.business;

import com.ca3.model.Pod;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 *
 * @author Vishnu
 */
@Stateless
public class PodBean implements Serializable{
    
    private static final String URL = "http://10.10.0.50:8080/epod/upload";
    @PersistenceContext private EntityManager em;
    
    
    public void save(Pod pod){
        
        em.persist(pod);
    }
    
    public List<Pod> getPods(){
        
        return em.createNamedQuery("Pod.findAll", Pod.class).getResultList();
    }
    
    public void update(int podId, String note, byte[] image, Date date) {
        
        Pod pod = em.find(Pod.class, podId);
        
        pod.setImage(image);
        pod.setNote(note);
        pod.setDeliveryDate(date);
        
        em.persist(pod);
    }

    public void updateNew(String ackId, int podId) {
        
        Pod pod = em.find(Pod.class, podId);
        pod.setAckId(ackId);

        em.merge(pod);
    }

    
    public void getAcknowlodgement() {
        
        try{
            
            List<Pod> podList = em.createNamedQuery("Pod.findAll", Pod.class).getResultList();
        
            for(Pod p : podList){
            
                if(p.getAckId()== null || p.getAckId().equals("")){
                    Client client = ClientBuilder.newBuilder()
                            .register(MultiPartFeature.class).build();
                    WebTarget webTarget = client.target(URL);


                    BodyPart img = new BodyPart(p.getImage(), MediaType.APPLICATION_OCTET_STREAM_TYPE);
                    img.setContentDisposition(FormDataContentDisposition.name("image").fileName("image.png").build());

                    MultiPart formData = new FormDataMultiPart()
                        .field("podId", p.getPodId(), MediaType.TEXT_PLAIN_TYPE)
                        .field("note", p.getNote(), MediaType.TEXT_PLAIN_TYPE)
                        .field("teamId", "1856edd8")
                        .field("callback", "http://10.10.24.227:8080/epod/callback")
                        .bodyPart(img);
                    formData.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

                    Invocation.Builder invo = webTarget.request();

                  //  Response response = webTarget.request(MediaType.APPLICATION_JSON_TYPE)
                 //       .post(Entity.entity(multiPart, multiPart.getMediaType()));

                    Response callResp = invo.post(Entity.entity(formData, formData.getMediaType()));

                    //System.out.println(callResp.getStatus());
                }
            
            }
        }catch(Exception e){
            
        }
    }
}
