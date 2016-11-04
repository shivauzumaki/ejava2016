/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.jms.Session;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Vishnu
 */
@ServerEndpoint("/noteSocket")
public class NoteSocket {
    
    private static final Set<Session> SESSIONS = ConcurrentHashMap.newKeySet();

    @OnOpen
    public void onOpen(Session session) {
        SESSIONS.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        SESSIONS.remove(session);
    }

//    public static void sendAll(String text) {
//        synchronized (SESSIONS) {
//            for (Session session : SESSIONS) {
//                if (session.) {
//                    session.getAsyncRemote().sendText(text);
//                }
//            }
//        }
//    }
}
