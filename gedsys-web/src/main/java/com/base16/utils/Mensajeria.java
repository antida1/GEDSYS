/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.utils;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.bean.RadicadoBean;
import com.base16.gedsys.entities.Devices;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.fcm.PushFCMNotification;
import com.base16.gedsys.model.DevicesJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
public class Mensajeria extends BaseBean {
    
    public void send(Usuario usuario, String titulo, String Mensaje) {
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            DevicesJpaController dJpa = new DevicesJpaController(emf);
            List<Devices> devices = dJpa.findDevicesByUsuario(usuario);
            for (Devices device : devices) {
                PushFCMNotification.PushFCMNotification( device.getGcmRegid() , titulo, Mensaje );
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error!", e.getMessage()));
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
}