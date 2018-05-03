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
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Informe;
import com.base16.gedsys.entities.Prestamo;
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
import com.base16.gedsys.model.PrestamoJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import com.base16.utils.Mensajeria;
import com.base16.utils.UploadDocument;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author rober
 * @author Modificación Lina David
 */
@ManagedBean
@ViewScoped
public class DocumentosBean extends BaseBean implements Serializable {

    private List<Documento> documentos;
    private List<Documento> recibidos;
    private List<Documento> enviados;
    private List<Documento> prestamo;
    private List<Prestamo> prestado;
    private List<Documento> porVencer;
    private List<Documento> radicados;
    private List<Documento> sinArchivar;
    private List<Documento> porEnviar;
    
    private List<Documento> seleccionadosPorEnviar;
    
    
    private List<Acta> actasPorFirmar;
    private List<Carta> cartaPorFirmar;
    private List<Certificado> certificadoPorFirmar;
    private List<Circular> circularPorFirmar;
    private List<Comunicacion> comunicadoPorFirmar;
    private List<Constancia> constanciaPorFirmar;
    private List<Informe> informePorFirmar;
    
    private UploadedFile documentFile;
    private UploadedFile documentFileComprobante;

    /**
     * Creates a new instance of DocumentosBean
     */
    public DocumentosBean() {
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public List<Documento> getEnviados() {
        return enviados;
    }

    public void setEnviados(List<Documento> enviados) {
        this.enviados = enviados;
    }

    public List<Documento> getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(List<Documento> prestamo) {
        this.prestamo = prestamo;
    }

    public List<Acta> getActasPorFirmar() {
        return actasPorFirmar;
    }

    public void setActasPorFirmar(List<Acta> actasPorFirmar) {
        this.actasPorFirmar = actasPorFirmar;
    }

    public List<Carta> getCartaPorFirmar() {
        return cartaPorFirmar;
    }

    public void setCartaPorFirmar(List<Carta> cartaPorFirmar) {
        this.cartaPorFirmar = cartaPorFirmar;
    }

    public List<Certificado> getCertificadoPorFirmar() {
        return certificadoPorFirmar;
    }

    public void setCertificadoPorFirmar(List<Certificado> certificadoPorFirmar) {
        this.certificadoPorFirmar = certificadoPorFirmar;
    }

    public List<Circular> getCircularPorFirmar() {
        return circularPorFirmar;
    }

    public void setCircularPorFirmar(List<Circular> circularPorFirmar) {
        this.circularPorFirmar = circularPorFirmar;
    }

    public List<Comunicacion> getComunicadoPorFirmar() {
        return comunicadoPorFirmar;
    }

    public void setComunicadoPorFirmar(List<Comunicacion> comunicadoPorFirmar) {
        this.comunicadoPorFirmar = comunicadoPorFirmar;
    }

    public List<Constancia> getConstanciaPorFirmar() {
        return constanciaPorFirmar;
    }

    public void setConstanciaPorFirmar(List<Constancia> constanciaPorFirmar) {
        this.constanciaPorFirmar = constanciaPorFirmar;
    }

    public List<Informe> getInformePorFirmar() {
        return informePorFirmar;
    }

    public void setInformePorFirmar(List<Informe> informePorFirmar) {
        this.informePorFirmar = informePorFirmar;
    }

    public List<Documento> getPorVencer() {
        return porVencer;
    }

    public void setPorVencer(List<Documento> porVencer) {
        this.porVencer = porVencer;
    }

    public List<Documento> getRecibidos() {
        return recibidos;
    }

    public void setRecibidos(List<Documento> recibidos) {
        this.recibidos = recibidos;
    }

    public List<Documento> getRadicados() {
        return radicados;
    }

    public void setRadicados(List<Documento> radicados) {
        this.radicados = radicados;
    }

    public List<Documento> getSinArchivar() {
        return sinArchivar;
    }

    public void setSinArchivar(List<Documento> sinArchivar) {
        this.sinArchivar = sinArchivar;
    }

    public List<Documento> getPorEnviar() {
        return porEnviar;
    }

    public void setPorEnviar(List<Documento> porEnviar) {
        this.porEnviar = porEnviar;
    }
    
    public UploadedFile getDocumentFile() {
        return documentFile;
    }

    public void setDocumentFile(UploadedFile documentFile) {
        this.documentFile = documentFile;
    }

    public UploadedFile getDocumentFileComprobante() {
        return documentFileComprobante;
    }

    public void setDocumentFileComprobante(UploadedFile documentFileComprobante) {
        this.documentFileComprobante = documentFileComprobante;       
    }

    public List<Documento> getSeleccionadosPorEnviar() {
        return seleccionadosPorEnviar;
    }

    public void setSeleccionadosPorEnviar(List<Documento> seleccionadosPorEnviar) {
        this.seleccionadosPorEnviar = seleccionadosPorEnviar;
        RequestContext.getCurrentInstance().execute("PF('denEnvio').show()");
    }

    public List<Prestamo> getPrestado() {
        return prestado;
    }

    public void setPrestado(List<Prestamo> prestado) {
        this.prestado = prestado;
    }
    
    public void cargarGuia(FileUploadEvent event){             
        FacesContext context = FacesContext.getCurrentInstance();
        DocumentoJpaController dJpa;
        Documento documento;
        String consecutivo = (String) event.getComponent().getAttributes().get("consecutivo");
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            documento = dJpa.findByConsecutivo(consecutivo);
            if(documento != null){
               if (event.getFile().getContents().length <= 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Adjuntar guía", "¡Por favor adjunte la guía!"));
                }else {
                   EntityManager em = emf.createEntityManager();
                    try {
                        em.getTransaction().begin(); 
                        dJpa = new DocumentoJpaController(emf);              

                        Usuario usuario = (Usuario) SessionUtils.getUsuario();
                        documento.setModificadoPor(usuario);
                        UploadDocument uDoc = new UploadDocument();
                        uDoc.upload(event.getFile(), this.documenstSavePath);
                        documento.setRutaGuia(event.getFile().getFileName());
                        documento.setGuia(uDoc.getUuid().toString());
                 
                        dJpa.edit(documento);
                        Mensajeria mensajeria = new Mensajeria();
                        mensajeria.send(usuario, "Nuevo documento recibido", documento.getGuia());
                
                        em.getTransaction().commit();              
                
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "GEDSYS", "Guía Almacenada exitoxamente!"));
                    }catch (Exception e) {
                        Logger.getLogger(RecepcionBean.class.getName()).log(Level.SEVERE, e.getMessage());
                       context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Error al cargar la guía!", e.getMessage()));
                       em.getTransaction().rollback();
                   }
               }
          }           
           
       } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
       }
    }
    
     public void cargarComprobante(FileUploadEvent event){             
        FacesContext context = FacesContext.getCurrentInstance();
        DocumentoJpaController dJpa;
        Documento documento;
        String consecutivo = (String) event.getComponent().getAttributes().get("consecutivo");
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            documento = dJpa.findByConsecutivo(consecutivo);
            if(documento != null){
               if (event.getFile().getContents().length <= 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Adjuntar comprobante", "¡Por favor adjunte la guía!"));
                }else {
                   EntityManager em = emf.createEntityManager();
                    try {
                        em.getTransaction().begin(); 
                        dJpa = new DocumentoJpaController(emf);              

                        Usuario usuario = (Usuario) SessionUtils.getUsuario();
                        documento.setModificadoPor(usuario);
                        UploadDocument uDoc = new UploadDocument();
                        uDoc.upload(event.getFile(), this.documenstSavePath);
                        documento.setRutaComprobante(event.getFile().getFileName());
                        documento.setComprobante(uDoc.getUuid().toString());
                 
                        dJpa.edit(documento);
                        Mensajeria mensajeria = new Mensajeria();
                        mensajeria.send(usuario, "Nuevo documento recibido", documento.getComprobante());
                
                        em.getTransaction().commit();              
                
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "GEDSYS", "Comprobante Almacenado exitoxamente!"));
                    }catch (Exception e) {
                        Logger.getLogger(RecepcionBean.class.getName()).log(Level.SEVERE, e.getMessage());
                       context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Error al cargar la comprobante!", e.getMessage()));
                       em.getTransaction().rollback();
                   }
               }
          }           
           
       } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
       }
    }
              
    private void listarDocumentosRadicados() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
           radicados = dJpa.findRadicados(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

   public void listarDocumentosRecibidos() {
       DocumentoJpaController dJpa;
      try {
           EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
           dJpa = new DocumentoJpaController(emf);
           recibidos = dJpa.findEntrantes(this.getCurrentUser());
       } catch (Exception e) {
           Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
      }
    }

    public void listarDocumentosEnviados() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            enviados = dJpa.findEnviados(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarDocumentosEnPrestamo() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            prestamo = dJpa.findEnPrestamo(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void listarDocumentosPrestados(){
        PrestamoJpaController pJpa;
        try{
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            pJpa = new  PrestamoJpaController(emf);
            prestado = pJpa.findPrestados(this.getCurrentUser());
        }catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void listarCompartidos(Usuario destinatario) {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            if (destinatario != null) {
                documentos = dJpa.findByCompartidos(destinatario);
            } else {
                documentos = dJpa.findByCompartidos(this.getCurrentUser());
            }

        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarDocumentosPorVencer() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            porVencer = dJpa.findPorVencer(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
       public void listarDocumentosSinArchivar() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            sinArchivar = dJpa.findSinArchivar(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarActasPorFirmar() {
        ActaJpaController aJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            aJpa = new ActaJpaController(emf);
            actasPorFirmar = aJpa.findByEstadoYUsuario(1,this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
      public void listarDocumentosPorEnviar() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            porEnviar = dJpa.findPorEnviar();
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarCartasPorFirmar() {
        CartaJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CartaJpaController(emf);
            cartaPorFirmar = cJpa.findByEstadoYUsuario("1",this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarCertificadosPorFirmar() {
        CertificadoJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CertificadoJpaController(emf);
            certificadoPorFirmar = cJpa.findByEstadoYUsuario(1,this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarCircularesPorFirmar() {
        CircularJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CircularJpaController(emf);
            circularPorFirmar = cJpa.findByEstadoYUsuario(1,this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarComunicadosPorFirmar() {
        ComunicacionJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ComunicacionJpaController(emf);
            comunicadoPorFirmar = cJpa.findByEstadoYUsuario("1",this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarConstanciaPorFirmar() {
        ConstanciaJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ConstanciaJpaController(emf);
            constanciaPorFirmar = cJpa.findByEstadoYUsuario(1,this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarInformesPorFirmar() {
        InformeJpaController iJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            iJpa = new InformeJpaController(emf);
            informePorFirmar = iJpa.findByEstadoYUsuario(1,this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listar() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            documentos = dJpa.findDocumentoEntities();
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    

}
