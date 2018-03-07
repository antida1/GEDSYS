/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.messages;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.entities.Notificacion;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.NotificacionJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author robert
 */
@Named(value = "messageBean")
@ViewScoped
public class MessageBean extends BaseBean implements Serializable {

    private List<Notificacion> notificaciones;
    private int couter;
       
    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public int getCouter() {
        return couter;
    }

    public void setCouter(int couter) {
        this.couter = couter;
    }
    
    

    /**
     * Creates a new instance of MessageBean
     */
    public MessageBean() {
    }

    public void refresh() {
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        NotificacionJpaController nJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            nJpa = new NotificacionJpaController(emf);
            this.notificaciones = nJpa.findNotificacionByResponsable(usuario);
            this.couter = this.notificaciones.size();
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Agenda", e.getMessage()));
        }

    }

}
