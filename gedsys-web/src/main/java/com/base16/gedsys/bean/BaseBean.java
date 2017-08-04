/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.web.utils.SessionUtils;
import com.base16.gedsys.web.utils.WebConfiguration;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author rober
 */
public class BaseBean {
    
    String configFilePath = "";
    String documenstSavePath = "";

   
    
    public BaseBean() {
        configFilePath = WebConfiguration.getInstance().getConfigFilePath();
        documenstSavePath = WebConfiguration.getInstance().getProperty("PathData");
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
