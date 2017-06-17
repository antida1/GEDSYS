/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.system;

import com.sucomunicacion.gedsys.bean.BaseBean;
import com.sucomunicacion.gedsys.bean.ConsecutivoBean;
import com.sucomunicacion.gedsys.entities.Modulo;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.model.ModuloJpaController;
import com.sucomunicacion.gedsys.utils.JpaUtils;
import com.sucomunicacion.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
@ManagedBean
@ViewScoped
public class ModuloBean extends BaseBean implements Serializable {

    private static final long SerialVersionUID = 1L;
    
    private Modulo modulo = new Modulo();
    private List<Modulo> modulos;
    private String accion;

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }
    
    public ModuloBean() {
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
        ModuloJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ModuloJpaController(emf);
            this.modulo.setFechaCreacion(new Date());
            this.modulo.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.modulo.setCreadoPor(usuario);
            cJpa.create(modulo);
            this.listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR , "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    private void modificar() {
        ModuloJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ModuloJpaController(emf);
            this.modulo.setFechaCreacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.modulo.setModificadoPor(usuario);
            cJpa.edit(modulo);
            this.listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR , "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void eliminar(Modulo modulo) {
        ModuloJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ModuloJpaController(emf);
            cJpa.destroy(modulo.getId());
            this.listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR , "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void listar() {
        ModuloJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ModuloJpaController(emf);
            modulos = cJpa.findModuloEntities();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR , "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void getModuloById(Modulo modulo) {
        ModuloJpaController cJpa;
        Modulo moduloTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ModuloJpaController(emf);
            moduloTemp = cJpa.findModulo(modulo.getId());
            if(moduloTemp !=null){
                this.modulo = moduloTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR , "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void limpiar(){
        this.modulo = null;
        this.modulo = new Modulo();
    }
    
}
