/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean.pdoc;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.bean.ConsecutivoBean;
import com.base16.gedsys.entities.Carta;
import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.CartaJpaController;
import com.base16.gedsys.model.ConsecutivoJpaController;
import com.base16.gedsys.model.DocumentoJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import com.base16.utils.UploadDocument;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.NativeUploadedFile;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.UploadedFileWrapper;

/**
 *
 * @author rober
 */
@Named(value = "cartaBean")
@ViewScoped
public class CartaBean extends BaseBean implements Serializable {

    /**
     * Creates a new instance of CartaBean
     */
    private static final long SerialVersionUID = 1L;
    private Carta carta = new Carta();
    private List<Carta> cartas;
    private String accion;

    private Documento documentoRelacionado;

    private StreamedContent content;
    private String filePath = "";

    public CartaBean() {
        this.accion = "Crear";
        this.carta.setFecha(new Date());
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Documento getDocumentoRelacionado() {
        return documentoRelacionado;
    }
    
    public void firmarCarta(Carta carta) {
        this.carta = carta;
        RequestContext.getCurrentInstance().execute("PF('denFirmarCarta').show()");
    }
    public void editarCarta(Carta carta) {
        this.carta = carta;
        this.accion = "editar";
        RequestContext.getCurrentInstance().execute("PF('denEditarCarta').show()");
    }

    public void setDocumentoRelacionado(Documento documentoRelacionado) {
        this.documentoRelacionado = documentoRelacionado;
        if (documentoRelacionado != null) {
            CartaJpaController cJpa;
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CartaJpaController(emf);
            this.carta = cJpa.findCartaByDocumentoPadre(documentoRelacionado);
            if (this.carta != null) {
                this.accion = "editar";
                this.carta.setDestinatario(documentoRelacionado.getRemitente());
            } else {
                this.carta = new Carta();
                this.carta.setDestinatario(documentoRelacionado.getRemitente());
                this.accion = "Crear";
            }
        }
    }

    public void loadDocumento(Documento doc) {
        this.setDocumentoRelacionado(doc);
        RequestContext.getCurrentInstance().execute("PF('denResponder').show()");
    }

    public void procesar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            switch (accion) {
                case "Crear":
                    crear();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Carta", "Documento creado exitosamente"));
                    break;
                case "editar":
                    editar();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Carta", "Documento actualizado exitosamente"));
                    break;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(CartaBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void limpiar() throws IOException{
        this.carta = null;
        this.carta = new Carta();
        this.carta.setFecha(new Date());
        Documento documento = new Documento();
        FacesContext contex = FacesContext.getCurrentInstance();
        contex.getExternalContext().redirect("../../index.xhtml");
    }

    private void crear() throws Exception {
        CartaJpaController cJpa;
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        cJpa = new CartaJpaController(emf);

        this.carta.setFechaCreacion(new Date());
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.carta.setCreadoPor(usuario);
        this.carta.setModificadoPor(usuario);
        this.carta.setFechaModificacion(new Date());
        this.carta.setEstado("1");
        if (this.documentoRelacionado != null) {
            this.carta.setDocumentoPadre(this.documentoRelacionado);
        }
        cJpa.create(this.carta);

    }

    private void editar() throws Exception {
        CartaJpaController cJpa;
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        cJpa = new CartaJpaController(emf);
        this.carta.setFechaCreacion(new Date());
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.carta.setModificadoPor(usuario);
        this.carta.setEstado("1");
        cJpa.edit(this.carta);

    }

    public void firmar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            EntityManager em = emf.createEntityManager();
            ConsecutivoJpaController cJpa;
            cJpa = new ConsecutivoJpaController(emf);

            CartaJpaController caJpa;
            caJpa = new CartaJpaController(emf);

            em.getTransaction().begin();
            Consecutivo consec = cJpa.findConsecutivoByTipoConsecutivo("carta");
            Integer intConsec = Integer.parseInt(consec.getConsecutivo());
            intConsec++;
            consec.setConsecutivo(intConsec.toString());
            em.merge(consec);
            em.flush();
            em.getTransaction().commit();

            SimpleDateFormat sdfDateRadicado = new SimpleDateFormat("yyyyMMdd");
            Date hoy = new Date();
            String strHoy = sdfDateRadicado.format(hoy);
            String radicado = consec.getPrefijo() + strHoy + consec.getConsecutivo() + consec.getSufijo();

            this.carta.setConsecutivo(radicado);
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.carta.setModificadoPor(usuario);
            this.carta.setFechaFirma(new Date());
            this.carta.setEstado("0");
            caJpa.edit(this.carta);                       
            CartaViewBean cvb = new CartaViewBean();            
            cvb.showDocumentFinal(this.carta); 

            // TODO: Crear el nuevo documento carta
            Documento documento = new Documento();
            UploadDocument uDoc = new UploadDocument();
            File file = new File(cvb.getFilePath());
            uDoc.upload(file, this.getDocumenstSavePath());
            
            // TODO: Crea nuevo registro de documento
            documento.setRutaArchivo(uDoc.getFileName(file));
            documento.setNombreDocumento(uDoc.getUuid().toString());
            documento.setRemitenteExteno(this.carta.getDestinatario());
            documento.setDestinatario(this.carta.getRemitente());
            documento.setAsunto(this.carta.getAsunto());
            documento.setFechaDocumento(this.carta.getFecha());
            documento.setFechaCreacion(new Date());
            documento.setDetalle(this.carta.getAsunto());
            documento.setDireccion(this.carta.getDireccion());
            documento.setEstado(9);
            DocumentoJpaController djc = new DocumentoJpaController(emf);
            djc.create(documento);   
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Carta", "¡Documento Firmado exitosamente!"));
            
            

            // TODO: Modificar el documento padre, mover a por archivar.
            if (this.documentoRelacionado != null) {
                this.documentoRelacionado.setDocumentoRelacionado(documento);
                this.documentoRelacionado.setEstado(3);
                djc.edit(this.documentoRelacionado);
            }

        } catch (Exception ex) {
            Logger.getLogger(CartaBean.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Carta", ex.getMessage()));
        }
    }
     public void imprimir() {
        FacesContext context = FacesContext.getCurrentInstance();
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        EntityManager em = emf.createEntityManager();
        try {
            ConsecutivoJpaController cJpa;
            cJpa = new ConsecutivoJpaController(emf);

            CartaJpaController caJpa;
            caJpa = new CartaJpaController(emf);

            em.getTransaction().begin();
            Consecutivo consec = cJpa.findConsecutivoByTipoConsecutivo("carta");
            Integer intConsec = Integer.parseInt(consec.getConsecutivo());
            intConsec++;
            consec.setConsecutivo(intConsec.toString());
            em.merge(consec);
            em.flush();
            em.getTransaction().commit();

            SimpleDateFormat sdfDateRadicado = new SimpleDateFormat("yyyyMMdd");
            Date hoy = new Date();
            String strHoy = sdfDateRadicado.format(hoy);
            String radicado = consec.getPrefijo() + strHoy + consec.getConsecutivo() + consec.getSufijo();

            this.carta.setConsecutivo(radicado);
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.carta.setModificadoPor(usuario);
            this.carta.setFechaFirma(new Date());
            this.carta.setEstado("0");
            caJpa.edit(this.carta);            
            CartaViewBean cvb = new CartaViewBean();
            cvb.showDocumentFinal(this.carta);
            
            // TODO: Crear el nuevo documento carta
            Documento documento = new Documento();
            UploadDocument uDoc = new UploadDocument();
            File file = new File(cvb.getFilePath());
            uDoc.upload(file, this.getDocumenstSavePath());
            
            // TODO: Crea nuevo registro de documento
            documento.setRutaArchivo(uDoc.getFileName(file));
            documento.setNombreDocumento(uDoc.getUuid().toString());
            documento.setRemitenteExteno("");
            documento.setDestinatario(this.carta.getCreadoPor());
            documento.setAsunto(this.carta.getAsunto());
            documento.setFechaDocumento(this.carta.getFecha());
            documento.setFechaCreacion(new Date());
            documento.setDetalle(this.carta.getContenido());
            documento.setDireccion(this.carta.getDireccion());            
            documento.setEstado(8);
            DocumentoJpaController djc = new DocumentoJpaController(emf);
            djc.create(documento);
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Carta", "¡Documento generado correctamente!"));
            
            

        } catch (Exception ex) {
            Logger.getLogger(CartaBean.class.getName()).log(Level.SEVERE, null, ex);
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Carta", "No existe el consecutivo para cartas en la Entidad Consecutivo"));
            em.getTransaction().rollback();
        }
    }

    private void loadDocument() {
        try {
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "carta" + this.carta.getId().toString() + ".pdf";
            if (!filePath.isEmpty()) {
                File tempFile = new File(filePath);
                if (tempFile.exists()) {
                    this.content = new DefaultStreamedContent(new FileInputStream(tempFile), new MimetypesFileTypeMap().getContentType(tempFile));
                }
            }
        } catch (FileNotFoundException e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido una excepcion al obtener el documento", e.getMessage()));
        }
    }

    public StreamedContent getContent() {
        this.loadDocument();
        return content;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

    public void enviar() {

    }

    public List<Carta> getCartasByResponsable() {
        CartaJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CartaJpaController(emf);
            cartas = cJpa.findCartaEntities();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        return cartas;
    }

}
