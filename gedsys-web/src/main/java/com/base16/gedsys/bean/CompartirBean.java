/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Acta;
import com.base16.gedsys.entities.Carta;
import com.base16.gedsys.entities.Certificado;
import com.base16.gedsys.entities.Circular;
import com.base16.gedsys.entities.Comunicacion;
import com.base16.gedsys.entities.Constancia;
import com.base16.gedsys.entities.DestinatariosDoc;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Informe;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.ActaJpaController;
import com.base16.gedsys.model.CartaJpaController;
import com.base16.gedsys.model.CertificadoJpaController;
import com.base16.gedsys.model.CircularJpaController;
import com.base16.gedsys.model.ComunicacionJpaController;
import com.base16.gedsys.model.ConstanciaJpaController;
import com.base16.gedsys.model.DestinatariosDocJpaController;
import com.base16.gedsys.model.DocumentoJpaController;
import com.base16.gedsys.model.InformeJpaController;
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
    private Acta acta;
    private Carta carta;
    private Certificado certificado;
    private Circular circular;
    private Comunicacion comunicacion;
    private Constancia  constancia;
    private Informe informe;
    private List<Usuario> destinatarios;

    public CompartirBean() {
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Acta getActa() {
        return acta;
    }

    public void setActa(Acta acta) {
        this.acta = acta;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public Certificado getCertificado() {
        return certificado;
    }

    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }

    public Circular getCircular() {
        return circular;
    }

    public void setCircular(Circular circular) {
        this.circular = circular;
    }

    public Comunicacion getComunicacion() {
        return comunicacion;
    }

    public void setComunicacion(Comunicacion comunicacion) {
        this.comunicacion = comunicacion;
    }

    public Constancia getConstancia() {
        return constancia;
    }

    public void setConstancia(Constancia constancia) {
        this.constancia = constancia;
    }

    public Informe getInforme() {
        return informe;
    }

    public void setInforme(Informe informe) {
        this.informe = informe;
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
    public void loadActa(Acta acta) {
        this.acta = acta;
        RequestContext.getCurrentInstance().execute("PF('denCompartirActa').show()");
    }
    
    public void loadCarta(Carta carta) {
        this.carta = carta;
        RequestContext.getCurrentInstance().execute("PF('denCompartirCarta').show()");
    }
    
    public void loadCertificado(Certificado cert) {
        this.certificado = cert;
        RequestContext.getCurrentInstance().execute("PF('denCompartirCertificado').show()");
    }
    
    public void loadCircular(Circular cir) {
        this.circular = cir;
        RequestContext.getCurrentInstance().execute("PF('denCompartirCircular').show()");
    }
    
    public void loadComunicacion(Comunicacion com) {
        this.comunicacion = com;
        RequestContext.getCurrentInstance().execute("PF('denCompartirComunicacion').show()");
    }
    public void loadConstancia(Constancia cons) {
        this.constancia = cons;
        RequestContext.getCurrentInstance().execute("PF('denCompartirConstancia').show()");
    }
    public void loadInforme(Informe inf) {
        this.informe = inf;
        RequestContext.getCurrentInstance().execute("PF('denCompartirInforme').show()");
    }
    public void guadarDocumento() {
        FacesContext context = FacesContext.getCurrentInstance();
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
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Compartir documentos", "Documento compartido"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo de documentos", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void guadarActa() {
        FacesContext context = FacesContext.getCurrentInstance();
        ActaJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            dJpa = new ActaJpaController(emf);
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
            //this.acta.setDestinatariosDocCollection(destinatariosDocCollection);
            dJpa.edit(this.acta);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Compartir documentos", "¡Acta compartida exitosamente!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo de documentos", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void guadarCarta() {
        FacesContext context = FacesContext.getCurrentInstance();
        CartaJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            dJpa = new CartaJpaController(emf);
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
            //this.carta.setDestinatariosDocCollection(destinatariosDocCollection);
            dJpa.edit(this.carta);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Compartir documentos", "¡Carta compartida exitosamente!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo de documentos", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void guadarCertificado() {
        FacesContext context = FacesContext.getCurrentInstance();
        CertificadoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            dJpa = new CertificadoJpaController(emf);
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
            //this.acta.setDestinatariosDocCollection(destinatariosDocCollection);
            dJpa.edit(this.certificado);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Compartir documentos", "¡Certificado compartido exitosamente!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo de documentos", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    public void guadarCircular() {
        FacesContext context = FacesContext.getCurrentInstance();
        CircularJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            dJpa = new CircularJpaController(emf);
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
            //this.acta.setDestinatariosDocCollection(destinatariosDocCollection);
            dJpa.edit(this.circular);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Compartir documentos", "¡Circular compartida exitosamente!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo de documentos", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void guadarComunicacion() {
        FacesContext context = FacesContext.getCurrentInstance();
        ComunicacionJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            dJpa = new ComunicacionJpaController(emf);
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
            //this.acta.setDestinatariosDocCollection(destinatariosDocCollection);
            dJpa.edit(this.comunicacion);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Compartir documentos", "¡Comunicación compartida exitosamente!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo de documentos", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void guadarConstancia() {
        FacesContext context = FacesContext.getCurrentInstance();
        ConstanciaJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            dJpa = new ConstanciaJpaController(emf);
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
            //this.acta.setDestinatariosDocCollection(destinatariosDocCollection);
            dJpa.edit(this.constancia);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Compartir documentos", "¡Constancia compartida exitosamente!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo de documentos", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void guadarInforme() {
        FacesContext context = FacesContext.getCurrentInstance();
        InformeJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            dJpa = new InformeJpaController(emf);
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
            //this.acta.setDestinatariosDocCollection(destinatariosDocCollection);
            dJpa.edit(this.informe);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Compartir documentos", "¡Informe compartido exitosamente!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo de documentos", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
}
