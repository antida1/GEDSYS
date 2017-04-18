/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.bean;

import com.sucomunicacion.gedsys.entities.Cargo;
import com.sucomunicacion.gedsys.entities.Consecutivo;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.model.CargoJpaController;
import com.sucomunicacion.gedsys.model.ConsecutivoJpaController;
import com.sucomunicacion.gedsys.utils.JpaUtils;
import com.sucomunicacion.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
@Named(value = "consecutivoBean")
@ViewScoped
public class ConsecutivoBean extends BaseBean implements Serializable {

    private static final long SerialVersionUID = 1L;
    
    /**
     * Creates a new instance of ConsecutivoBean
     */
    public ConsecutivoBean() {
    }
    
        
    private Consecutivo consecutivo = new Consecutivo();
    private List<Consecutivo> consecutivos;
    private String accion;

    public Consecutivo getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Consecutivo consecutivo) {
        this.consecutivo = consecutivo;
    }

    public List<Consecutivo> getConsecutivos() {
        return consecutivos;
    }

    public void setConsecutivos(List<Consecutivo> consecutivos) {
        this.consecutivos = consecutivos;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public void procesar() {
        try {
            switch(accion){
                case "Crear":
                    crear();
                    break;
                case "Modificar":
                    modificar();
                    break;
            }
        } catch (Exception e) {
        }
    }
    
    private void crear() {
        ConsecutivoJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ConsecutivoJpaController(emf);
            this.consecutivo.setFechaCreacion(new Date());
            this.consecutivo.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.consecutivo.setCreadoPor(usuario);
            cJpa.create(consecutivo);
            this.listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR , "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    private void modificar() {
        ConsecutivoJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ConsecutivoJpaController(emf);
            this.consecutivo.setFechaCreacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.consecutivo.setModificadoPor(usuario);
            cJpa.edit(consecutivo);
            this.listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR , "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void eliminar(Consecutivo consecutivo) {
        ConsecutivoJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ConsecutivoJpaController(emf);
            cJpa.destroy(consecutivo.getId());
            this.listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR , "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void listar() {
        ConsecutivoJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ConsecutivoJpaController(emf);
            consecutivos = cJpa.findConsecutivoEntities();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR , "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void getConsecutivoById(Consecutivo consecutivo)  {
        ConsecutivoJpaController cJpa;
        Consecutivo consecutivoTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ConsecutivoJpaController(emf);
            consecutivoTemp = cJpa.findConsecutivo(consecutivo.getId());
            if(consecutivoTemp !=null){
                this.consecutivo = consecutivoTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR , "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
}
