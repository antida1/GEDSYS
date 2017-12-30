/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Mediorecepcion;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.entities.Mediorecepcion;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.MediorecepcionJpaController;
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
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class MediosRepEnvBean extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Mediorecepcion medioRecepcion = new Mediorecepcion();
    private List<Mediorecepcion> Mediorecepciones;
    private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

    public List<Mediorecepcion> getMediorecepciones() {
        return Mediorecepciones;
    }

    public void setMediorecepciones(List<Mediorecepcion> Mediorecepciones) {
        this.Mediorecepciones = Mediorecepciones;
    }

    public Mediorecepcion getMediorecepcion() {
        return medioRecepcion;
    }

    public void setMediorecepcion(Mediorecepcion MedioRecepcion) {
        this.medioRecepcion = MedioRecepcion;
    }

    public void procesar() {
        try {
            switch (accion) {
                case "Crear":
                    crear();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Medios de Recepcion - Envio", "Medio Recepcion creado!"));
                    break;
                case "Modificar":
                    modificar();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Medios de Recepcion - Envio", "Medio Recepcion modificado!"));
                    break;
            }
            RequestContext.getCurrentInstance().execute("PF('medioRecepcionDialog').hide()");
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Medios de Recepcion - Envio", e.getMessage()));
            Logger.getLogger(MediosRepEnvBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void crear() throws Exception {
        MediorecepcionJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new MediorecepcionJpaController(emf);

            Usuario usuario = (Usuario) SessionUtils.getUsuario();

            this.medioRecepcion.setFechaCreacion(new Date());
            this.medioRecepcion.setFechaModificacion(new Date());
            this.medioRecepcion.setCreadoPor(usuario);
            sJpa.create(medioRecepcion);
            this.listar();
        } catch (Exception e) {
            Logger.getLogger(MediosRepEnvBean.class.getName()).log(Level.SEVERE, e.getMessage());
            throw e;
        }
    }

    private void modificar() throws Exception {
        MediorecepcionJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new MediorecepcionJpaController(emf);
            this.medioRecepcion.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.medioRecepcion.setModificadoPor(usuario);
            sJpa.edit(medioRecepcion);
            this.listar();
        } catch (Exception e) {
            Logger.getLogger(MediosRepEnvBean.class.getName()).log(Level.SEVERE, e.getMessage());
            throw e;
        }
    }

    public void eliminar(Mediorecepcion MedioRecepcion) {
        MediorecepcionJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new MediorecepcionJpaController(emf);
            sJpa.destroy(MedioRecepcion.getId());
            this.listar();
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Medios de Recepcion - Envio", "Medio Eliminado!"));

        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cargo", e.getMessage()));
            Logger.getLogger(MediosRepEnvBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listar() {
        MediorecepcionJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new MediorecepcionJpaController(emf);
            Mediorecepciones = sJpa.findMediorecepcionEntities();
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cargo", e.getMessage()));
            Logger.getLogger(MediosRepEnvBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void getMediorecepcionById(Mediorecepcion MedioRecepcion) {
        MediorecepcionJpaController sJpa;
        Mediorecepcion MedioRecepcionTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new MediorecepcionJpaController(emf);
            MedioRecepcionTemp = sJpa.findMediorecepcion(MedioRecepcion.getId());
            if (MedioRecepcionTemp != null) {
                this.medioRecepcion = MedioRecepcionTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cargo", e.getMessage()));
            Logger.getLogger(MediosRepEnvBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void limpiar() {
        this.medioRecepcion = new Mediorecepcion();
    }
}
