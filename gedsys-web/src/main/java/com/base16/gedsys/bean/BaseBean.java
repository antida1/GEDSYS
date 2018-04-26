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
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author rober
 * @author Modificación Lina David
 */
public class BaseBean {

    String configFilePath = "";
    String documenstSavePath = "";
    String appName = "";
    String nombreEntidad = "";
    Boolean encriptFiles;
    
    public BaseBean() {
        try {
            configFilePath = WebConfiguration.getInstance().getConfigFilePath();
            documenstSavePath = WebConfiguration.getInstance().getProperty("PathData");
            encriptFiles = Boolean.parseBoolean(WebConfiguration.getInstance().getProperty("protectFile"));
            nombreEntidad = WebConfiguration.getInstance().getProperty("nombreEntidad");
        } catch (NamingException ex) {
            Logger.getLogger(BaseBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getConfigFilePath() {
        return configFilePath;
    }

    public void addMessage(FacesMessage message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, message);
    }

    public Usuario getCurrentUser() {
        return (Usuario) SessionUtils.getUsuario();
    }

    public String getDocumenstSavePath() {
        return documenstSavePath;
    }

    public String getAppName() {
        try {
            InitialContext ic = new InitialContext();
            this.appName = (String) ic.lookup("java:app/AppName");
        } catch (NamingException ex) {
            Logger.getLogger(BaseBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return appName;
    }

    public Boolean getEncriptFiles() {
        return encriptFiles;
    }

}
