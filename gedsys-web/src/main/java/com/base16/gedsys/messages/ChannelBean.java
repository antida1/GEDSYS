/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.messages;

import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author rober
 */
@ManagedBean
@ApplicationScoped
public class ChannelBean {
    Map<String, String>  channels = new HashMap<String, String>();
    
    public void addChannel(String user, String channel ){
        channels.put(user, channel);
    }
    
    public String getChannel(String user){
        return channels.get(user);
    }
    
}
