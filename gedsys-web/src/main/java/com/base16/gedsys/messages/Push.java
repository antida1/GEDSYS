/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.messages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author robert
 */
@ServerEndpoint("/push")
public class Push {
    
   
    private static final Map<String, Session> SESSIONS = new HashMap<String, Session>();
    
    @OnOpen
    public void onOpen(Session session){
        Map<String, List<String>> params = session.getRequestParameterMap();        
        if (params.get("uuid") != null ) {
            SESSIONS.put(params.get("uuid").toString(), session);
        }
    }
 
    @OnClose 
    public void onClose(Session session){
        Map<String, List<String>> params = session.getRequestParameterMap(); 
        SESSIONS.remove(params.get("uuid"));
    }
    
    public static void sendAll(String text){
        synchronized (SESSIONS) {
            
            for (Map.Entry<String, Session> entry : SESSIONS.entrySet()) {
                String key = entry.getKey();
                Session session = entry.getValue();
                if(session.isOpen()){
                    session.getAsyncRemote().sendText(text);
                }
            }
        }
    }
}
