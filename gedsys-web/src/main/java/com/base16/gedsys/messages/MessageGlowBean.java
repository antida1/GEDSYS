/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.messages;

import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.apache.commons.lang.StringEscapeUtils;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import org.primefaces.push.PushContext;

/**
 *
 * @author rober
 */
@ManagedBean
@SessionScoped
public class MessageGlowBean {
    
    private String channel;
    
    @ManagedProperty(value = "#{channelsBean}")
    private ChannelBean channels;
    
    private String sendMessageUser;
    
    private String user;
    
    @PostConstruct
    public void doPostConstruction() {
        channel = "/" + UUID.randomUUID().toString();
        channels.addChannel(user, channel);
    }
    
    public void  send(String sMessage){
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(channels.getChannel(sendMessageUser), StringEscapeUtils.escapeHtml(sMessage));
        
    }
}
