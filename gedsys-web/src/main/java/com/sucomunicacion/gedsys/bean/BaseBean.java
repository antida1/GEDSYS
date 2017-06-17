/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.bean;

import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.web.utils.SessionUtils;
import com.sucomunicacion.gedsys.web.utils.WebConfiguration;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author rober
 */
public class BaseBean {
    
    String configFilePath = "";
   
    public BaseBean() {
        configFilePath = WebConfiguration.configFilePath();
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
}
