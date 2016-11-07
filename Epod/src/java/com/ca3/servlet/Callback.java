/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca3.servlet;

import com.ca3.business.PodBean;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vishnu
 */
@WebServlet("/callback")
public class Callback extends HttpServlet {

    @EJB PodBean podBean;
    
    protected void doGet (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{

        String podId = req.getParameter("podId");
        String ackId = req.getParameter("ackId");
        
        try{
            podBean.updateNew(ackId, Integer.parseInt(podId));
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
            
        
    }
}
