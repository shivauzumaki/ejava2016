/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca3.servlet;

import com.ca3.business.PodBean;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Vishnu
 */
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    
    @EJB PodBean podBean;
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        
        String podId = new String(readPart(req.getPart("podId")));
        String note = new String(readPart(req.getPart("note")));
        byte[] image = readPart(req.getPart("image"));
        String date = new String(readPart(req.getPart("time")));
        podBean.update(Integer.parseInt(podId), note, image, new Date(Long.parseLong(date)));
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
