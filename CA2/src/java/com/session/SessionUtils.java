/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.session;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vishnu
 */
public class SessionUtils {
    
    public static HttpSession getSession(){
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }
    
    public static ExternalContext getContext() {
        
        return FacesContext.getCurrentInstance().getExternalContext();
    }
    
}
