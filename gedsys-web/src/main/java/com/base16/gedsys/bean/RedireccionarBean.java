/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.SignaturaTopografica;
import com.base16.gedsys.model.DocumentoJpaController;
import com.base16.gedsys.utils.JpaUtils;
import java.io.Serializable;
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
 */
@Named(value = "redireccionarBean")
@ViewScoped
public class RedireccionarBean extends BaseBean implements Serializable{

    
    /**
     * Creates a new instance of RedireccionarBean
     */
    private static final long SerialVersionUID = 1L;
    private Documento documento;
    
    public RedireccionarBean() {
    }  
      
    public void loadDocumento(Documento doc) {
        this.documento = doc;
        RequestContext.getCurrentInstance().execute("PF('denArchivar').show()");
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
       
    public void guadarDocumento() {
        
            DocumentoJpaController dJpa;
            try {
                EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
                dJpa = new DocumentoJpaController(emf);
                dJpa.edit(documento);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_INFO ,"Archivo de documentos", "Documento archivado correctamente"));
                this.addMessage(new FacesMessage("Redireción de documentos", "Documento redireccionado exitosamente"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
                Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
            }
        
    }
    
}
