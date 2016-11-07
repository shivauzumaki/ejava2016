/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ca3.Listener;

import javax.annotation.Resource;
import javax.ejb.TimerService;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Vishnu
 */
public class AcknowledgementListener implements ServletContextListener{

    @Resource TimerService timerService;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
    
}
