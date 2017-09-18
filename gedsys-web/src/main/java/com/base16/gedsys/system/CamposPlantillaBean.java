/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.system;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.bean.ConsecutivoBean;
import com.base16.gedsys.config.CamposPlantillaJpaController;
import com.base16.gedsys.config.exceptions.NonexistentEntityException;
import com.base16.gedsys.entities.CamposPlantilla;
import com.base16.gedsys.entities.Modulo;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.ModuloJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
@ManagedBean
@ViewScoped
public class CamposPlantillaBean extends BaseBean implements Serializable {

    private static final long SerialVersionUID = 1L;

    private CamposPlantilla campoPlantilla = new CamposPlantilla();
    private List<CamposPlantilla> camposPlantilla;
    private String accion;

    public CamposPlantilla getCampoPlantilla() {
        return campoPlantilla;
    }

    public void setCampoPlantilla(CamposPlantilla campoPlantilla) {
        this.campoPlantilla = campoPlantilla;
    }

    public List<CamposPlantilla> getCamposPlantilla() {
        return camposPlantilla;
    }

    public void setCamposPlantilla(List<CamposPlantilla> camposPlantilla) {
        this.camposPlantilla = camposPlantilla;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

    public void procesar() {
        try {
            switch (accion) {
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
        CamposPlantillaJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CamposPlantillaJpaController(emf);
            this.campoPlantilla.setFechaCreacion(new Date());
            this.campoPlantilla.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.campoPlantilla.setCreadoPor(usuario.getId());
            cJpa.create(campoPlantilla);
            this.listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    private void modificar() {
       CamposPlantillaJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CamposPlantillaJpaController(emf);
            this.campoPlantilla.setFechaCreacion(new Date());
            this.campoPlantilla.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.campoPlantilla.setModificadoPor(usuario.getId());
            cJpa.edit(campoPlantilla);
            this.listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void eliminar(CamposPlantilla campoPlantilla) {
        CamposPlantillaJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CamposPlantillaJpaController(emf);
            cJpa.destroy(campoPlantilla.getId());
            this.listar();
        } catch (NonexistentEntityException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR , "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void limpiar() {
        this.campoPlantilla = null;
        this.campoPlantilla = new CamposPlantilla();
        
    }

    public void listar() {
        CamposPlantillaJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CamposPlantillaJpaController(emf);
            camposPlantilla = cJpa.findCamposPlantillaEntities();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR , "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void getCampoPlantillaById(CamposPlantilla campoPlantilla){
        CamposPlantillaJpaController cJpa;
        CamposPlantilla campoPTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CamposPlantillaJpaController(emf);
            campoPTemp = cJpa.findCamposPlantilla(campoPlantilla.getId());
            if(campoPTemp !=null){
                this.campoPlantilla = campoPTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_ERROR , "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

}
