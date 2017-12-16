/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.web.utils.SessionUtils;
import com.base16.gedsys.web.utils.WebConfiguration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

/**
 *
 * @author rober
 */
public class BaseBean {
    
    String configFilePath = "";
    String documenstSavePath = "";

   
    
    public BaseBean() {
        try {
            configFilePath = WebConfiguration.getInstance().getConfigFilePath();
            documenstSavePath = WebConfiguration.getInstance().getProperty("PathData");
        } catch (NamingException ex) {
            Logger.getLogger(BaseBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  
    
    public String getConfigFilePath() {
        return configFilePath;
    }
    
    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public Usuario getCurrentUser(){
        return (Usuario) SessionUtils.getUsuario();
    }
    
     public String getDocumenstSavePath() {
        return documenstSavePath;
    }
}
