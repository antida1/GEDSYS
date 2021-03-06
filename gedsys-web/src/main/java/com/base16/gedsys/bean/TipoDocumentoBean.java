/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.TipoDocumento;
import com.base16.gedsys.model.TipoDocumentoJpaController;
import com.base16.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import org.primefaces.context.RequestContext;


@ManagedBean
@ViewScoped
public class TipoDocumentoBean extends BaseBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private TipoDocumento tipoDocumento= new TipoDocumento();
    private List<TipoDocumento> TipoDocumentos;
    private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }
    public List<TipoDocumento> getTipoDocumentos() {
        return TipoDocumentos;
    }

    public void setTipoDocumentos(List<TipoDocumento> TipoDocumentos) {
        this.TipoDocumentos = TipoDocumentos;
    }
    
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    public void procesar(){
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
            this.addMessage( new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipos de Documentos", e.getMessage()));
            Logger.getLogger(EntidadBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
       
    private void crear() throws Exception{
        TipoDocumentoJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new TipoDocumentoJpaController(emf);
            
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            
            this.tipoDocumento.setFechaCreacion(new Date());
            this.tipoDocumento.setFechaModificacion(new Date());
            this.tipoDocumento.setCreadoPor(usuario);
            sJpa.create(tipoDocumento);
            this.listar();
            this.addMessage( new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipos de Documentos", "¡Se ha creado el tipo de documento exitoxamente!"));
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void modificar() throws Exception{
        TipoDocumentoJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new TipoDocumentoJpaController(emf);
            
            this.tipoDocumento.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.tipoDocumento.setModificadoPor(usuario);
            sJpa.edit(tipoDocumento);
            this.listar();
            this.addMessage( new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipos de Documentos", "¡Se ha modificado el tipo de documento exitoxamente!"));
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminar(TipoDocumento tipoDocumento){
        TipoDocumentoJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new TipoDocumentoJpaController(emf);
            sJpa.destroy(tipoDocumento.getId());
            this.listar();
            this.addMessage( new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipos de Documentos", "¡Se ha eliminado el tipo de documento exitosamente!"));
        } catch (Exception e) {
             this.addMessage( new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipos de Documentos", e.getMessage()));
            Logger.getLogger(EntidadBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void listar(){
        TipoDocumentoJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new TipoDocumentoJpaController(emf);
            TipoDocumentos = sJpa.findTipoDocumentoEntities();
        } catch (Exception e) {
            
        }
    }
    
    public void getTipoDocumentoById(TipoDocumento tipoDocumento){
        TipoDocumentoJpaController sJpa;
        TipoDocumento tipoDocumentoTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new TipoDocumentoJpaController(emf);
            tipoDocumentoTemp = sJpa.findTipoDocumento(tipoDocumento.getId());
            if(tipoDocumentoTemp != null){
                this.tipoDocumento = tipoDocumentoTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            
        }
    }
    
    public void limpiar(){
        this.tipoDocumento.setId(0);
        this.tipoDocumento.setNombre("");
    }
}
