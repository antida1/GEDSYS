/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.messages;

import com.base16.gedsys.entities.Usuario;
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
    Map<Usuario, String>  channels = new HashMap<Usuario, String>();
    
    public void addChannel(Usuario user, String channel ){
        channels.put(user, channel);
    }
    
    public String getChannel(Usuario user){
        return channels.get(user);
    }
    
}
