/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.DestinatariosDoc;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.DestinatariosDocJpaController;
import com.base16.gedsys.model.DocumentoJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import org.primefaces.context.RequestContext;


/**
 *
 * @author rober
 */
@ManagedBean
@ViewScoped
public class CompartirBean extends BaseBean implements Serializable {

    /**
     * Creates a new instance of compartirConBean
     */
    private static final long SerialVersionUID = 1L;
    private Documento documento;
    private List<Usuario> destinatarios;

    public CompartirBean() {
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public List<Usuario> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<Usuario> destinatarios) {
        this.destinatarios = destinatarios;
    }
    
    public void loadDocumento(Documento doc) {
        this.documento = doc;
        RequestContext.getCurrentInstance().execute("PF('denCompartir').show()");
    }

    public void guadarDocumento() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            dJpa = new DocumentoJpaController(emf);
            //SignaturaTopografica signatura = (SignaturaTopografica) this.selectedNodeSignatura.getData();
            //documento.setSignaturaTopografica(signatura);
            //documento.setTipoDocumental(this.tipoDocumental);
            //dJpa.edit(documento);
            DestinatariosDocJpaController desJpa;
            desJpa = new DestinatariosDocJpaController(emf);
            List<DestinatariosDoc> destinatariosDocCollection = new ArrayList<>();
            for (Usuario dest : destinatarios) {
                //if (dest.getId() != this.documento.getDestinatario().getId()) {
                    DestinatariosDoc destinatarioDoc = new DestinatariosDoc();
                    destinatarioDoc.setCreadoPor(usuario);
                    destinatarioDoc.setDestinatarioId(dest);
                    destinatarioDoc.setDocumentoId(this.getDocumento());
                    desJpa.create(destinatarioDoc);
                    destinatariosDocCollection.add(destinatarioDoc);
                //}
            }
           // this.documento.setDestinatariosDocCollection(destinatariosDocCollection);
            //dJpa.edit(this.documento);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo de documentos", "Documento archivado correctamente"));
            this.addMessage(new FacesMessage("Archivo de documentos", "Documento archivado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

}
