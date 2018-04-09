/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean.pdoc;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.entities.Constancia;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.ActaJpaController;
import com.base16.gedsys.model.ComunicacionJpaController;
import com.base16.gedsys.model.ConsecutivoJpaController;
import com.base16.gedsys.model.ConstanciaJpaController;
import com.base16.gedsys.model.DocumentoJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import com.base16.utils.DateTimeUtils;
import com.base16.utils.UploadDocument;
import fr.opensagres.xdocreport.converter.ConverterRegistry;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.IConverter;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.document.DocumentKind;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
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
import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.common.navigation.TextNavigation;
import org.odftoolkit.simple.common.navigation.TextSelection;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rober
 */
@Named(value = "constanciaBean")
@ViewScoped
public class ConstanciaBean extends BaseBean implements Serializable{

    /**
     * Creates a new instance of ConstanciaBean
     */
    private static final long SerialVersionUID = 1L;
    private Constancia constancia = new Constancia();
    private List<Constancia> constancias;
    private String accion;
    
    private Documento documentoRelacionado;

    private StreamedContent content;
    private String filePath = "";
    
    public ConstanciaBean() {
        this.accion = "Crear";
        this.constancia.setFecha(new Date());
    }

    public Constancia getConstancia() {
        return constancia;
    }

    public void setConstancia(Constancia constancia) {
        this.constancia = constancia;
    }

    public List<Constancia> getConstancias() {
        return constancias;
    }

    public void setConstancias(List<Constancia> constancias) {
        this.constancias = constancias;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public void firmarConstancia(Constancia constancia) {
        this.constancia = constancia;
        RequestContext.getCurrentInstance().execute("PF('denFirmarConstancia').show()");
    }
    
    public void editarConstancia(Constancia constancia) {
        this.constancia = constancia;
        this.accion = "editar";
        RequestContext.getCurrentInstance().execute("PF('denEditarConstancia').show()");
    }
    
    public void procesar() {
        try {
            switch (accion) {
                case "Crear":
                    crear();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "GEDSYS", "¡Constancia creado exitosamente!"));
                    break;
                case "editar":
                    editar();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "GEDSYS", "¡Constancia modificado exitosamente!"));
                    break;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", e.getMessage()));
            Logger.getLogger(ActaBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    private void crear() throws Exception {
        ConstanciaJpaController cJpa;
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        cJpa = new ConstanciaJpaController(emf);

        this.constancia.setFechaCreacion(new Date());
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.constancia.setCreadoPor(usuario);
        this.constancia.setModificadoPor(usuario);
        this.constancia.setFechaModificacion(new Date());
        this.constancia.setEstado(1);
        cJpa.create(this.constancia);
    }

    private void editar() throws Exception {
        ConstanciaJpaController cJpa;
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        cJpa = new ConstanciaJpaController(emf);
        this.constancia.setFechaCreacion(new Date());
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.constancia.setModificadoPor(usuario);
        this.constancia.setEstado(1);
        cJpa.edit(this.constancia);
    }

    public void firmar() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            EntityManager em = emf.createEntityManager();
            ConsecutivoJpaController cJpa;
            cJpa = new ConsecutivoJpaController(emf);

            ConstanciaJpaController caJpa;
            caJpa = new ConstanciaJpaController(emf);

            em.getTransaction().begin();
            Consecutivo consec = cJpa.findConsecutivoByTipoConsecutivo("constancia");
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

            this.constancia.setConsecutivo(radicado);
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.constancia.setModificadoPor(usuario);
            this.constancia.setFechaFirma(new Date());
            this.constancia.setEstado(0);
            caJpa.edit(this.constancia);            
            ConstanciaViewBean cvb = new ConstanciaViewBean();
            cvb.showDocumentFinal(this.constancia);

            // TODO: Crear el nuevo documento constancia
            Documento documento = new Documento();
            UploadDocument uDoc = new UploadDocument();
            File file = new File(cvb.getFilePath());
            uDoc.upload(file, this.getDocumenstSavePath());
            
            // TODO: Crea nuevo registro de documento
            documento.setRutaArchivo(uDoc.getFileName(file));
            documento.setNombreDocumento(uDoc.getUuid().toString());
            documento.setRemitenteExteno("");
            documento.setDestinatario(this.constancia.getRemitente());
            documento.setAsunto(this.constancia.getContenido());
            documento.setFechaDocumento(this.constancia.getFecha());
            documento.setFechaCreacion(new Date());
            documento.setDetalle(this.constancia.getContenido());
            documento.setDireccion("");
            documento.setEstado(9);
            documento.setConsecutivo(this.constancia.getConsecutivo());
            DocumentoJpaController djc = new DocumentoJpaController(emf);
            djc.create(documento);
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Constancia", "¡Documento Firmado exitosamente!"));

            // TODO: Modificar el documento padre, mover a por archivar.
            if (this.documentoRelacionado != null) {
                this.documentoRelacionado.setDocumentoRelacionado(documento);
                this.documentoRelacionado.setEstado(3);
                djc.edit(this.documentoRelacionado);
            }

        } catch (Exception ex) {
            Logger.getLogger(CircularBean.class.getName()).log(Level.SEVERE, null, ex);
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Constancia", "¡No existe el consecutivo para constancias internas en la Entidad Consecutivo!"));
        }
        //TODO: Recuperar consecutivo de documento.
//        Usuario usuario = (Usuario) SessionUtils.getUsuario();
//        this.constancia.setModificadoPor(usuario);
//        this.constancia.setFechaFirma(new Date());
//        this.constancia.setEstado(3);
    }
    
    public void limpiar() throws IOException{
        this.constancia = null;
        this.constancia = new Constancia();
        this.constancia.setFecha(new Date());
        Documento documento = new Documento();
        FacesContext contex = FacesContext.getCurrentInstance();
        contex.getExternalContext().redirect("../../index.xhtml");
    }
    
    public void imprimir() {
        FacesContext context = FacesContext.getCurrentInstance();
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        EntityManager em = emf.createEntityManager();
        try {
            ConsecutivoJpaController cJpa;
            cJpa = new ConsecutivoJpaController(emf);

            ConstanciaJpaController caJpa;
            caJpa = new ConstanciaJpaController(emf);

            em.getTransaction().begin();
            Consecutivo consec = cJpa.findConsecutivoByTipoConsecutivo("constancia");
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

            this.constancia.setConsecutivo(radicado);
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.constancia.setModificadoPor(usuario);
            this.constancia.setFechaFirma(new Date());
            this.constancia.setEstado(0);
            caJpa.edit(this.constancia);
            ConstanciaViewBean cvb = new ConstanciaViewBean();
            cvb.showDocumentFinal(this.constancia);
            
            // TODO: Crear el nuevo documento carta
            Documento documento = new Documento();
            UploadDocument uDoc = new UploadDocument();
            File file = new File(cvb.getFilePath());
            uDoc.upload(file, this.getDocumenstSavePath());
            
            // TODO: Crea nuevo registro de documento
            documento.setRutaArchivo(uDoc.getFileName(file));
            documento.setNombreDocumento(uDoc.getUuid().toString());
            documento.setRemitenteExteno("");
            documento.setDestinatario(this.constancia.getRemitente());
            documento.setAsunto(this.constancia.getContenido());
            documento.setFechaDocumento(this.constancia.getFecha());
            documento.setFechaCreacion(new Date());
            documento.setDetalle(this.constancia.getContenido());
            documento.setDireccion("");
            documento.setEstado(8);
            DocumentoJpaController djc = new DocumentoJpaController(emf);
            djc.create(documento);
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Constancia", "¡Documento generado correctamente!"));
            
        } catch (Exception ex) {
            Logger.getLogger(CartaBean.class.getName()).log(Level.SEVERE, null, ex);
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Constancia", "No existe el consecutivo para constancias en la Entidad Consecutivo"));
            em.getTransaction().rollback();
        }
    }


//    public void previsualizar() {
//        try {
//
//            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "constancia.odt");
//            TextNavigation searchFecha;
//            TextNavigation consecutivo;
//            TextNavigation cargo;
//            TextNavigation firma_remitente;
//            TextNavigation asunto;
//            TextNavigation contenido;
//            TextNavigation remitente;
//            TextNavigation anexos;
//            TextNavigation copia;
//
//            searchFecha = new TextNavigation("@fecha", odt);
//            while (searchFecha.hasNext()) {
//                DateFormat df = new SimpleDateFormat();
//                TextSelection item = (TextSelection) searchFecha.nextSelection();
//                item.replaceWith(DateTimeUtils.getFormattedTime(this.constancia.getFecha(), "dd-mm-yyyy"));
//            }
//
//            consecutivo = new TextNavigation("@consecutivo", odt);
//            while (consecutivo.hasNext()) {
//                TextSelection item = (TextSelection) consecutivo.nextSelection();
//                item.replaceWith("");
//            }
//
//            cargo = new TextNavigation("@cargo", odt);
//            while (cargo.hasNext()) {
//                TextSelection item = (TextSelection) cargo.nextSelection();
//                item.replaceWith(this.constancia.getRemitente().getCargo().getNombre());
//            }
//            
//            contenido = new TextNavigation("@contenido", odt);
//            while (contenido.hasNext()) {
//                TextSelection item = (TextSelection) contenido.nextSelection();
//                item.replaceWith(this.constancia.getContenido());
//            }
//
//
//            remitente = new TextNavigation("@remitente", odt);
//            while (remitente.hasNext()) {
//                TextSelection item = (TextSelection) remitente.nextSelection();
//                item.replaceWith(this.constancia.getRemitente().getNombres() + " " + this.constancia.getRemitente().getApelidos());
//            }
//
//            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "constancia" + this.constancia.getId().toString() + ".odt");
//            odt.close();
//
//            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
//            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
//            //IContext context =  report.createContext();
//            //context.put("name", "world");
//            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
//            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);
//
//            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Constanciaes" + File.separatorChar + "constancia" + this.constancia.getId().toString() + ".odt"));
//            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Constanciaes" + File.separatorChar + "constancia" + this.constancia.getId().toString() + ".pdf"));
//            converter.convert(in, out, options);
//
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
//            Logger.getLogger(ActaBean.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }

    private void loadDocument() {
        try {
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Constanciaes" + File.separatorChar + "constancia" + this.constancia.getId().toString() + ".pdf";
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

    public List<Constancia> getConstanciasByResponsable() {
        ConstanciaJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ConstanciaJpaController(emf);
            constancias = cJpa.findConstanciaEntities();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", e.getMessage()));
            Logger.getLogger(ConstanciaBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        return constancias;
    }
    
}
