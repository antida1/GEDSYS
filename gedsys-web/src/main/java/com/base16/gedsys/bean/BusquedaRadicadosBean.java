/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.entities.ConsecutivosUsuario;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.TipoDocumento;
import com.base16.gedsys.model.ConsecutivoJpaController;
import com.base16.gedsys.model.ConsecutivosUsuarioJpaController;
import com.base16.gedsys.model.DocumentoJpaController;
import com.base16.gedsys.utils.JpaUtils;
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
@Named(value = "busquedaRadicadosBean")
@ViewScoped
public class BusquedaRadicadosBean extends BaseBean implements Serializable {

    private List<ConsecutivosUsuario> consecutivos;
    private List<Consecutivo> tiposC;
    private String consecutivo;
    private Date startDate;
    private Date endDate;
    private String tipoConsecutivo;
    private ConsecutivosUsuario tipo;
    
    public List<ConsecutivosUsuario> getConsecutivos() {
        return consecutivos;
    }

    public void setConsecutivos(List<ConsecutivosUsuario> consecutivos) {
        this.consecutivos = consecutivos;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ConsecutivosUsuario getTipo() {
        return tipo;
    }

    public void setTipo(ConsecutivosUsuario tipo) {
        this.tipo = tipo;
    }

    public String getTipoConsecutivo() {
        return tipoConsecutivo;
    }

    public void setTipoConsecutivo(String tipoConsecutivo) {
        this.tipoConsecutivo = tipoConsecutivo;
    }

    public List<Consecutivo> getTiposC() {
        return tiposC;
    }

    public void setTiposC(List<Consecutivo> tiposC) {
        this.tiposC = tiposC;
    }
    
    /**
     * Creates a new instance of BusquedaRadicadosBean
     */
    public BusquedaRadicadosBean() {
    }
    
    public void listar(){
        ConsecutivoJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new ConsecutivoJpaController(emf);
            tiposC = sJpa.findConsecutivoEntities();
        } catch (Exception e) {
            
        }
    } 
    
    public void buscar(){
        ConsecutivosUsuarioJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ConsecutivosUsuarioJpaController(emf);
            consecutivos = cJpa.findConsecutivos(this.getCurrentUser(), this.consecutivo, this.startDate, this.endDate, this.tipoConsecutivo);
            if(consecutivos == null){
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Búsqueda de documentos", "¡El documento solicitado no existe!"));
            }
        } catch (Exception e) {
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
     public void limpiar(){
        this.consecutivo = "";        
        this.startDate = new Date();
        this.endDate = null;
        this.tipoConsecutivo = "";
    }
    
}
