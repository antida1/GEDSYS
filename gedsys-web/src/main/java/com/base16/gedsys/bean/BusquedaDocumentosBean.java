/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.TipoDocumento;
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
@Named(value = "busquedaDocumentosBean")
@ViewScoped
public class BusquedaDocumentosBean extends BaseBean implements Serializable {

    private List<Documento> documentos;
    private String asunto;
    private Date startDate;
    private Date endDate;
    private TipoDocumento tipoDocumento;
    private String radicado;
    
    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
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

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getRadicado() {
        return radicado;
    }

    public void setRadicado(String radicado) {
        this.radicado = radicado;
    }
        
    /**
     * Creates a new instance of BusquedaDocumentosBean
     */
    public BusquedaDocumentosBean() {
    }
    
    public void buscar(){
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            documentos = dJpa.findDocumentos(this.getCurrentUser(), this.radicado, this.asunto, this.startDate, this.endDate, this.tipoDocumento);
            if(documentos == null){
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Búsqueda de documentos", "¡El documento solicitado no existe!"));
            }
        } catch (Exception e) {
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void limpiar(){
        this.radicado = "";
        this.asunto = "";
        this.startDate = new Date();
        this.endDate = new Date();
        this.tipoDocumento = new TipoDocumento();
    }
    
}
