/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca3.servlet;

import com.ca3.business.PodBean;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.Path;
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
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    
    @EJB PodBean podBean;
    private static final String URL = "http://10.10.0.50:8080/epod/upload";
    
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        
        String podId = new String(readPart(req.getPart("podId")));
        String note = new String(readPart(req.getPart("note")));
        byte[] image = readPart(req.getPart("image"));
        String date = new String(readPart(req.getPart("time")));
        podBean.update(Integer.parseInt(podId), note, image, new Date(Long.parseLong(date)));
        getAcknowlodgement(podId, note, image);
    }
    
    @Schedule(second="*/2")
    private void getAcknowlodgement(String podId, String note, byte[] image) throws FileNotFoundException, IOException {
        
        Client client = ClientBuilder.newBuilder()
            .register(MultiPartFeature.class).build();
        WebTarget webTarget = client.target(URL);
  
        
        BodyPart img = new BodyPart(image, MediaType.APPLICATION_OCTET_STREAM_TYPE);
        img.setContentDisposition(FormDataContentDisposition.name("image").fileName("image.png").build());

        MultiPart formData = new FormDataMultiPart()
            .field("podId", podId, MediaType.TEXT_PLAIN_TYPE)
            .field("note", note, MediaType.TEXT_PLAIN_TYPE)
            .field("teamId", "1856edd8")
            .field("callback", "http://10.10.24.227:8080/epod/callback")
            .bodyPart(img);
        formData.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
        
        Invocation.Builder invo = webTarget.request();
                
      //  Response response = webTarget.request(MediaType.APPLICATION_JSON_TYPE)
     //       .post(Entity.entity(multiPart, multiPart.getMediaType()));
     
        Response callResp = invo.post(Entity.entity(formData, formData.getMediaType()));
        
        System.out.println(callResp.getStatus());
    }
    
    private byte[] readPart(Part p) throws IOException { 
        byte[] buffer = new byte[1024 * 8]; 
        int sz = 0; 

        try (InputStream is = p.getInputStream()) { 
            BufferedInputStream bis = new BufferedInputStream(is); 

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) { 
                while (-1 != (sz = bis.read(buffer))) baos.write(buffer, 0, sz); 
                buffer = baos.toByteArray(); 
            } 
        }
        return (buffer); 
    }
   
}
