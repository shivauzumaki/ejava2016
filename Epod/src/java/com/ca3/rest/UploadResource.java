///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.ca3.rest;
//
//import javax.enterprise.context.RequestScoped;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.MultivaluedMap;
//
///**
// *
// * @author Vishnu
// */
//@RequestScoped
//@Path("/upload")
//public class UploadResource {
//    
//    @POST
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    public String upload(MultivaluedMap<String, String> form){
//        
//        String note = form.getFirst("note");
//        
//        System.out.println("REached "+ note);
//        return "ok";
//    }
//}
