/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Cargo;
import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.CargoJpaController;
import com.base16.gedsys.model.ConsecutivoJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
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
import org.primefaces.context.RequestContext;

/**
 *
 * @author rober
 * @author Modificación Lina David
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
        this.limpiar();
        this.accion = accion;
    }

    public void procesar() {
        try {
            switch (accion) {
                case "Crear":
                    crear();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Consecutivos", "¡Se ha creado el consecutivo exitosamente!"));

                    break;
                case "Modificar":
                    modificar();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Consecutivos", "¡Se ha modificado el consecutivo exitosamente!"));
                    break;
            }
            RequestContext.getCurrentInstance().execute("PF('consecutivoDialog').hide()");
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Consecutivos", e.getMessage()));
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, null, e);
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
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
            throw e;
        }
    }

    private void modificar() throws Exception {
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
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
            throw e;
        }
    }

    public void eliminar(Consecutivo consecutivo) {
        ConsecutivoJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ConsecutivoJpaController(emf);
            cJpa.destroy(consecutivo.getId());
            this.listar();
            this.addMessage( new FacesMessage(FacesMessage.SEVERITY_INFO, "Consecutivos", "¡Se ha eliminado el consecutivo exitosamente!"));
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Consecutivos", e.getMessage()));
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
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Consecutivos", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void getConsecutivoById(Consecutivo consecutivo) {
        ConsecutivoJpaController cJpa;
        Consecutivo consecutivoTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ConsecutivoJpaController(emf);
            consecutivoTemp = cJpa.findConsecutivo(consecutivo.getId());
            if (consecutivoTemp != null) {
                this.consecutivo = consecutivoTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            this.addMessage( new FacesMessage(FacesMessage.SEVERITY_ERROR, "Consecutivos", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void limpiar(){
        this.consecutivo = new Consecutivo();
    }
}
