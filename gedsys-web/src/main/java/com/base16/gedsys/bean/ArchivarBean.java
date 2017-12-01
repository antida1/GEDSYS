/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.SignaturaTopografica;
import com.base16.gedsys.entities.TipoDocumental;
import com.base16.gedsys.model.DocumentoJpaController;
import com.base16.gedsys.utils.JpaUtils;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author rober
 */
@ManagedBean
@ViewScoped
public class ArchivarBean extends BaseBean implements Serializable {

    /**
     * Creates a new instance of ArchivarBean
     */
    private static final long SerialVersionUID = 1L;
    private Documento documento;
    private TreeNode selectedNodeSignatura =  new DefaultTreeNode();
    private TipoDocumental tipoDocumental = new TipoDocumental();
    
    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public TreeNode getSelectedNodeSignatura() {
        return selectedNodeSignatura;
    }

    public void setSelectedNodeSignatura(TreeNode selectedNodeSignatura) {
        this.selectedNodeSignatura = selectedNodeSignatura;
    }

    public TipoDocumental getTipoDocumental() {
        return tipoDocumental;
    }

    public void setTipoDocumental(TipoDocumental tipoDocumental) {
        this.tipoDocumental = tipoDocumental;
    }
    
    

    public ArchivarBean() {
    
    }

    public void loadDocumento(Documento doc) {
        this.documento = doc;
        RequestContext.getCurrentInstance().execute("PF('denArchivar').show()");
    }

    public void guadarDocumento() {
        if (this.selectedNodeSignatura != null) {
            DocumentoJpaController dJpa;
            try {
                EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
                dJpa = new DocumentoJpaController(emf);
                SignaturaTopografica signatura = (SignaturaTopografica)  this.selectedNodeSignatura.getData();
                documento.setSignaturaTopografica(signatura);
                documento.setTipoDocumental(this.tipoDocumental);
                dJpa.edit(documento);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_INFO ,"Archivo de documentos", "Documento archivado correctamente"));
                this.addMessage(new FacesMessage("Archivo de documentos", "Documento archivado correctamente"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
                Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
            }
        }
    }
    
}
