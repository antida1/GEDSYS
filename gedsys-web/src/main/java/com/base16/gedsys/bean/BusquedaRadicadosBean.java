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

    private List<Consecutivo> consecutivos;
    private String consecutivo;
    private Date startDate;
    private Date endDate;
    private ConsecutivosUsuario tipo;
    
    public List<Consecutivo> getConsecutivos() {
        return consecutivos;
    }

    public void setConsecutivos(List<Consecutivo> consecutivos) {
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

    /**
     * Creates a new instance of BusquedaRadicadosBean
     */
    public BusquedaRadicadosBean() {
    }
    
    public void buscar(){
        ConsecutivoJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ConsecutivoJpaController(emf);
            consecutivos = cJpa.findConsecutivos(this.getCurrentUser(), this.consecutivo, this.startDate, this.endDate, this.tipo);
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
        this.endDate = new Date();
        this.tipo = new ConsecutivosUsuario();
    }
    
}
