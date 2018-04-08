/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.ConsecutivosUsuario;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.TipoDocumento;
import com.base16.gedsys.model.ConsecutivosUsuarioJpaController;
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
public class ConsecutivosUsuarioBean extends BaseBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private ConsecutivosUsuario tipoConsecutivo= new ConsecutivosUsuario();
    private List<ConsecutivosUsuario> Tipo;
    private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

    public ConsecutivosUsuario getTipoConsecutivo() {
        return tipoConsecutivo;
    }

    public void setTipoConsecutivo(ConsecutivosUsuario tipoConsecutivo) {
        this.tipoConsecutivo = tipoConsecutivo;
    }

    public List<ConsecutivosUsuario> getTipo() {
        return Tipo;
    }

    public void setTipo(List<ConsecutivosUsuario> Tipo) {
        this.Tipo = Tipo;
    }
    
    public void procesar(){
        try {
            switch(accion){
                case "Crear":
                    crear();                    
                    break;                
            }            
        } catch (Exception e) {
            this.addMessage( new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipo Consecutivo", e.getMessage()));
            Logger.getLogger(EntidadBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
       
    private void crear() throws Exception{
        ConsecutivosUsuarioJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new ConsecutivosUsuarioJpaController(emf);
            
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            
            this.tipoConsecutivo.setFechaCreacion(new Date());
            this.tipoConsecutivo.setCreadoPor(usuario);
            sJpa.create(tipoConsecutivo);
            this.listar();
            this.addMessage( new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo Consecutivo", "¡Tipo Consecutivo creado exitoxamente!"));
        } catch (Exception e) {
            throw e;
        }
    }
    
//    private void modificar() throws Exception{
//        TipoDocumentoJpaController sJpa;
//        try {
//            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
//            sJpa = new TipoDocumentoJpaController(emf);
//            
//            this.tipoDocumento.setFechaModificacion(new Date());
//            Usuario usuario = (Usuario) SessionUtils.getUsuario();
//            this.tipoDocumento.setModificadoPor(usuario);
//            sJpa.edit(tipoDocumento);
//            this.listar();
//            this.addMessage( new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo Documento", "¡Tipo Documento modificado exitoxamente!"));
//        } catch (Exception e) {
//            throw e;
//        }
//    }
    
    public void eliminar(TipoDocumento tipoDocumento){
        TipoDocumentoJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new TipoDocumentoJpaController(emf);
            sJpa.destroy(tipoDocumento.getId());
            this.listar();
            this.addMessage( new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo Consecutivo", "Tipo Consecutivo Eliminado"));
        } catch (Exception e) {
             this.addMessage( new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipo Consecutivo", e.getMessage()));
            Logger.getLogger(EntidadBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void listar(){
        ConsecutivosUsuarioJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new ConsecutivosUsuarioJpaController(emf);
            Tipo = sJpa.findConsecutivosUsuarioEntities();
        } catch (Exception e) {
            
        }
    }
    
    public void getConsecutivosUsuarioById(ConsecutivosUsuario tipoConsecutivo){
        ConsecutivosUsuarioJpaController sJpa;
        ConsecutivosUsuario tipoConsecutivoTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new ConsecutivosUsuarioJpaController(emf);
            tipoConsecutivoTemp = sJpa.findConsecutivosUsuario(tipoConsecutivo.getId());
            if(tipoConsecutivoTemp != null){
                this.tipoConsecutivo = tipoConsecutivoTemp;
            }
        } catch (Exception e) {
            
        }
    }
    
    public void limpiar(){
        this.tipoConsecutivo.setId(0);
        this.tipoConsecutivo.setConsecutivo("");
    }
}
