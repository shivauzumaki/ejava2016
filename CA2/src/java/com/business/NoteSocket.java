/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Vishnu
 */
@ApplicationScoped
@ServerEndpoint("/noteSocket")
public class NoteSocket {
    
//    @Resource(lookup = "concurrent/ThreadPool")
//	private ManagedScheduledExecutorService executor;
    
    private Set<Session> sessions = new HashSet<>();

	@OnOpen
	public void open(javax.websocket.Session session) {
		System.out.println(">>> new session: " + session.getId());
                sessions.add(session);
	}
        
        @OnClose
        public void close(javax.websocket.Session session) {
		System.out.println(">>>  session rmvd: " + session.getId());
                sessions.remove(session);
	}

	@OnMessage
	public void message(final javax.websocket.Session session, final String msg) {
		System.out.println(">>> message: " + msg);
                

//		executor.submit(new Runnable() {
//			@Override
//			public void run() {
				System.out.println(">>> in thread");
				final JsonObject message = Json.createObjectBuilder()
						.add("message", msg)
						.add("timestamp", (new Date()).toString())
						.build();

				for (javax.websocket.Session s: session.getOpenSessions())
					try {
						s.getBasicRemote().sendText(message.toString());
					} catch(IOException ex) {
						try { s.close(); } catch (IOException e) { }
					}
		//	}
		//});
		System.out.println(">>> exiting message");
	}

}