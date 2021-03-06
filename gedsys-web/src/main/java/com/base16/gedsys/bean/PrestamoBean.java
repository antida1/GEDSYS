/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Prestamo;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.DocumentoJpaController;
import com.base16.gedsys.model.PrestamoJpaController;
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
@Named(value = "prestamoBean")
@ViewScoped
public class PrestamoBean extends BaseBean implements Serializable {

    /**
     * Creates a new instance of PrestamoBean
     */
    private static final long serialVersionUID = 1L;

    private Prestamo prestamo = new Prestamo();
    private List<Prestamo> Prestamos;
    private String accion;
    private Documento documento;

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public PrestamoBean() {
    }

    public void loadDocumento(Documento doc) {
        this.documento = doc;
        this.setAccion("Crear");
        RequestContext.getCurrentInstance().execute("PF('denPrestamo').show()");
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public List<Prestamo> getPrestamos() {
        return Prestamos;
    }

    public void setPrestamos(List<Prestamo> Prestamos) {
        this.Prestamos = Prestamos;
    }

    public void procesar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            switch (accion) {
                case "Crear":
                    crear();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Prestamo de Documentos", "¡Prestamo Creado Correctamente!"));
                    break;
                case "Modificar":
                    modificar();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Prestamo de Documentos", "¡Prestamo actualizado correctamente!"));
                    break;
            }
            RequestContext.getCurrentInstance().execute("PF('denPrestamo').hide()");
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Prestamo de documentos", e.getMessage()));
        }
    }

    private void crear() throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        PrestamoJpaController sJpa;
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PrestamoJpaController(emf);
            dJpa = new DocumentoJpaController(emf);
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.prestamo.setFechaCreacion(new Date());
            this.prestamo.setCreadoPor(usuario);
            this.prestamo.setDocumento(this.documento);           
            
            if(null != this.documento.getEstado())switch (this.documento.getEstado()) {
                case 1:
                    this.prestamo.setEstadoAnterior(1);
                    this.documento.setEstado(4);
                    this.prestamo.setEstado(1);
                    dJpa.edit(this.documento);
                    sJpa.create(prestamo);
                    this.listar();
                    break;
                case 2:
                    this.prestamo.setEstadoAnterior(2);
                    this.documento.setEstado(4);
                    this.prestamo.setEstado(1);
                    dJpa.edit(this.documento);
                    sJpa.create(prestamo);
                    this.listar();
                    break;
                case 3:
                    this.prestamo.setEstadoAnterior(3);
                    this.documento.setEstado(4);
                    this.prestamo.setEstado(1);
                    dJpa.edit(this.documento);
                    sJpa.create(prestamo);
                    this.listar();
                    break;
                case 4:                    
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Prestamo de Documentos", "¡No se puede prestar el documento, El docuemnto ya se encuentra previamente prestado!"));
                    break;
                case 5:
                    this.prestamo.setEstadoAnterior(5);
                    this.documento.setEstado(6);
                    this.prestamo.setEstado(1);
                    dJpa.edit(this.documento);
                    sJpa.create(prestamo);
                    this.listar();
                    break;
                case 6:
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Prestamo de Documentos", "¡No se puede prestar el documento, El docuemnto ya se encuentra previamente prestado!"));
                    break;
                case 7:
                    this.prestamo.setEstadoAnterior(7);
                    this.documento.setEstado(6);
                    this.prestamo.setEstado(1);
                    dJpa.edit(this.documento);
                    sJpa.create(prestamo);
                    this.listar();
                    break;
                case 8:
                    this.prestamo.setEstadoAnterior(8);
                    this.documento.setEstado(6);
                    this.prestamo.setEstado(1);
                    dJpa.edit(this.documento);
                    sJpa.create(prestamo);
                    this.listar();
                    break;
                case 9:
                    this.prestamo.setEstadoAnterior(9);
                    this.documento.setEstado(4);
                    this.prestamo.setEstado(1);
                    dJpa.edit(this.documento);
                    sJpa.create(prestamo);
                    this.listar();
                    break;                                
                default:
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Prestamo de Documentos", "¡No se puede prestar el documento!"));
                    break;
            }else{
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Prestamo de Documentos", "¡No se puede prestar el documento!"));
            }  
            
        } catch (Exception e) {
            throw e;
        }
    }

    private void modificar() throws Exception {
        PrestamoJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PrestamoJpaController(emf);
            this.prestamo.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.prestamo.setModificadoPor(usuario);
            sJpa.edit(prestamo);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(Prestamo prestamo) {
        FacesContext context = FacesContext.getCurrentInstance();
        PrestamoJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PrestamoJpaController(emf);
            sJpa.destroy(prestamo.getId());
            this.listar();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Prestamo de documentos", e.getMessage()));
        }
    }

    public void listar() {
        FacesContext context = FacesContext.getCurrentInstance();
        PrestamoJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PrestamoJpaController(emf);
            Prestamos = sJpa.findByEstado(1);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Prestamo de documentos", e.getMessage()));
        }
    }
    
     public void listarByUsuario() {        
        PrestamoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new PrestamoJpaController(emf);
            Prestamos = dJpa.findByCreadoPor(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void getPrestamoById(Prestamo prestamo) {
        FacesContext context = FacesContext.getCurrentInstance();
        PrestamoJpaController sJpa;
        Prestamo prestamoTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PrestamoJpaController(emf);
            prestamoTemp = sJpa.findPrestamo(prestamo.getId());
            if (prestamoTemp != null) {
                this.prestamo = prestamoTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Prestamo de documentos", e.getMessage()));
        }
    }

    public void limpiar() {
        this.prestamo = new Prestamo();
    }
}
