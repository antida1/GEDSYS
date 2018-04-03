/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean.pdoc;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.bean.ConsecutivoBean;
import com.base16.gedsys.entities.Comunicacion;
import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.ActaJpaController;
import com.base16.gedsys.model.CircularJpaController;
import com.base16.gedsys.model.ComunicacionJpaController;
import com.base16.gedsys.model.ConsecutivoJpaController;
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
@Named(value = "comunicacionBean")
@ViewScoped
public class ComunicacionBean extends BaseBean implements Serializable {

    /**
     * Creates a new instance of ComunicacionBean
     */
    private static final long SerialVersionUID = 1L;
    private Comunicacion comunicacion = new Comunicacion();
    private List<Comunicacion> comunicaciones;
    private String accion;
    
    private Documento documentoRelacionado;

    private StreamedContent content;
    private String filePath = "";
    
    public ComunicacionBean() {
        this.accion = "Crear";
        this.comunicacion.setFecha(new Date());
    }

    public Comunicacion getComunicacion() {
        return comunicacion;
    }

    public void setComunicacion(Comunicacion comunicacion) {
        this.comunicacion = comunicacion;
    }

    public List<Comunicacion> getComunicaciones() {
        return comunicaciones;
    }

    public void setComunicaciones(List<Comunicacion> comunicaciones) {
        this.comunicaciones = comunicaciones;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public void firmarActa(Comunicacion comunicacion) {
        this.comunicacion = comunicacion;
        RequestContext.getCurrentInstance().execute("PF('denFirmarComunicacion').show()");
    }
       
    public void procesar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            switch (accion) {
                case "Crear":
                    crear();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "GEDSYS", "¡Comunicación interna creada exitosamente!"));
                    break;
                case "editar":
                    editar();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "GEDSYS", "¡Comunicación interna modificada exitosamente!"));
                    break;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", e.getMessage()));
            Logger.getLogger(ComunicacionBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    private void crear() throws Exception {
        ComunicacionJpaController cJpa;
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        cJpa = new ComunicacionJpaController(emf);

        this.comunicacion.setFechaCreacion(new Date());
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.comunicacion.setCreadoPor(usuario);
        this.comunicacion.setModificadoPor(usuario);
        this.comunicacion.setFechaModificacion(new Date());
        this.comunicacion.setEstado("1");
        cJpa.create(this.comunicacion);
    }

    private void editar() throws Exception {
        ComunicacionJpaController cJpa;
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        cJpa = new ComunicacionJpaController(emf);
        this.comunicacion.setFechaCreacion(new Date());
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.comunicacion.setModificadoPor(usuario);
        this.comunicacion.setEstado("2");
        cJpa.edit(this.comunicacion);
    }
    public void limpiar() throws IOException{
        this.comunicacion = null;
        this.comunicacion = new Comunicacion();
        this.comunicacion.setFecha(new Date());
        Documento documento = new Documento();
        FacesContext contex = FacesContext.getCurrentInstance();
        contex.getExternalContext().redirect("../../index.xhtml");
    }

    public void firmar() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            EntityManager em = emf.createEntityManager();
            ConsecutivoJpaController cJpa;
            cJpa = new ConsecutivoJpaController(emf);

            ComunicacionJpaController caJpa;
            caJpa = new ComunicacionJpaController(emf);

            em.getTransaction().begin();
            Consecutivo consec = cJpa.findConsecutivoByTipoConsecutivo("comunicacion");
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

            this.comunicacion.setConsecutivo(radicado);
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.comunicacion.setModificadoPor(usuario);
            this.comunicacion.setFechaFirma(new Date());
            this.comunicacion.setEstado("0");
            caJpa.edit(this.comunicacion);            
            ComunicacionViewBean cvb = new ComunicacionViewBean();
            cvb.showDocumentFinal(this.comunicacion);

            // TODO: Crear el nuevo documento carta
            Documento documento = new Documento();
            UploadDocument uDoc = new UploadDocument();
            File file = new File(cvb.getFilePath());
            uDoc.upload(file, this.getDocumenstSavePath());
            
            // TODO: Crea nuevo registro de documento
            documento.setRutaArchivo(uDoc.getFileName(file));
            documento.setNombreDocumento(uDoc.getUuid().toString());
            documento.setRemitenteExteno(this.comunicacion.getDestinatario().getNombres() + " " + this.comunicacion.getDestinatario().getApelidos());
            documento.setDestinatario(this.comunicacion.getRemitente());
            documento.setAsunto(this.comunicacion.getAsunto());
            documento.setFechaDocumento(this.comunicacion.getFecha());
            documento.setFechaCreacion(new Date());
            documento.setDetalle(this.comunicacion.getContenido());
            documento.setDireccion(this.comunicacion.getDestinatario().getEmail());
            documento.setEstado(9);  
            DocumentoJpaController djc = new DocumentoJpaController(emf);
            djc.create(documento);
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Comunicación Interna", "¡Documento Firmado exitosamente!"));

            // TODO: Modificar el documento padre, mover a por archivar.
            if (this.documentoRelacionado != null) {
                this.documentoRelacionado.setDocumentoRelacionado(documento);
                this.documentoRelacionado.setEstado(3);
                djc.edit(this.documentoRelacionado);
            }

        } catch (Exception ex) {
            Logger.getLogger(CircularBean.class.getName()).log(Level.SEVERE, null, ex);
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Comunicación Interna", "¡No existe el consecutivo para comunicaciones internas en la Entidad Consecutivo!"));
          
        }
        
        //TODO: Recuperar consecutivo de documento.
//        Usuario usuario = (Usuario) SessionUtils.getUsuario();
//        this.comunicacion.setModificadoPor(usuario);
//        this.comunicacion.setFechaFirma(new Date().toString());
//        this.comunicacion.setEstado("3");
    }
    
//    public void imprimir() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
//        EntityManager em = emf.createEntityManager();
//        try {
//            ConsecutivoJpaController cJpa;
//            cJpa = new ConsecutivoJpaController(emf);
//
//            ComunicacionJpaController caJpa;
//            caJpa = new ComunicacionJpaController(emf);
//
//            em.getTransaction().begin();
//            Consecutivo consec = cJpa.findConsecutivoByTipoConsecutivo("comunicacion");
//            Integer intConsec = Integer.parseInt(consec.getConsecutivo());
//            intConsec++;
//            consec.setConsecutivo(intConsec.toString());
//            em.merge(consec);
//            em.flush();
//            em.getTransaction().commit();
//
//            SimpleDateFormat sdfDateRadicado = new SimpleDateFormat("yyyyMMdd");
//            Date hoy = new Date();
//            String strHoy = sdfDateRadicado.format(hoy);
//            String radicado = consec.getPrefijo() + strHoy + consec.getConsecutivo() + consec.getSufijo();
//
//            this.comunicacion.setConsecutivo(radicado);
//            Usuario usuario = (Usuario) SessionUtils.getUsuario();
//            this.comunicacion.setModificadoPor(usuario);
//            this.comunicacion.setFechaFirma(new Date());
//            this.comunicacion.setEstado("3");
//            caJpa.edit(this.comunicacion);
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Comunicacion", "¡Documento generado correctamente!"));
//            ComunicacionViewBean cvb = new ComunicacionViewBean();
//            cvb.showDocument(this.comunicacion);
//            
//
//        } catch (Exception ex) {
//            Logger.getLogger(CartaBean.class.getName()).log(Level.SEVERE, null, ex);
//            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Comunicacion", "No existe el consecutivo para comunicaciones en la Entidad Consecutivo"));
//            em.getTransaction().rollback();
//        }
//    }


//    public void previsualizar() {
//        try {
//
//            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "comunicacion.odt");
//            TextNavigation searchFecha;
//            TextNavigation consecutivo;
//            TextNavigation destinatario;
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
//                item.replaceWith(DateTimeUtils.getFormattedTime(this.comunicacion.getFecha(), "dd-mm-yyyy"));
//            }
//
//            consecutivo = new TextNavigation("@consecutivo", odt);
//            while (consecutivo.hasNext()) {
//                TextSelection item = (TextSelection) consecutivo.nextSelection();
//                item.replaceWith("");
//            }
//
//            destinatario = new TextNavigation("@destinatario", odt);
//            while (destinatario.hasNext()) {
//                TextSelection item = (TextSelection) destinatario.nextSelection();
//                item.replaceWith(this.comunicacion.getDestinatario().getNombres() + " " +this.comunicacion.getDestinatario().getApelidos() + " " + this.comunicacion.getDestinatario().getCargo().getNombre() );
//            }
//
//            asunto = new TextNavigation("@asunto", odt);
//            while (asunto.hasNext()) {
//                TextSelection item = (TextSelection) asunto.nextSelection();
//                item.replaceWith(this.comunicacion.getAsunto());
//            }
//
//            contenido = new TextNavigation("@contenido", odt);
//            while (contenido.hasNext()) {
//                TextSelection item = (TextSelection) contenido.nextSelection();
//                item.replaceWith(this.comunicacion.getContenido());
//            }
//
//
//            remitente = new TextNavigation("@remitente", odt);
//            while (remitente.hasNext()) {
//                TextSelection item = (TextSelection) remitente.nextSelection();
//                item.replaceWith(this.comunicacion.getRemitente().getNombres() + " " + this.comunicacion.getRemitente().getApelidos());
//            }
//
//            firma_remitente = new TextNavigation("@firma_remitente", odt);
//            while (firma_remitente.hasNext()) {
//                TextSelection item = (TextSelection) firma_remitente.nextSelection();
//                item.replaceWith(this.comunicacion.getRemitente().getNombres() + " " + this.comunicacion.getRemitente().getApelidos() + " " + this.comunicacion.getRemitente().getCargo().getNombre());
//            }
//            
//             anexos = new TextNavigation("@anexos", odt);
//            while (anexos.hasNext()) {
//                TextSelection item = (TextSelection) anexos.nextSelection();
//                item.replaceWith("");
//            }
//            
//             copia = new TextNavigation("@copia", odt);
//            while (copia.hasNext()) {
//                TextSelection item = (TextSelection) copia.nextSelection();
//                item.replaceWith("");
//            }
//
//            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "comunicacion" + this.comunicacion.getId().toString() + ".odt");
//            odt.close();
//
//            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
//            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
//            //IContext context =  report.createContext();
//            //context.put("name", "world");
//            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
//            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);
//
//            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Comunicaciones" + File.separatorChar + "comunicacion" + this.comunicacion.getId().toString() + ".odt"));
//            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Comunicaciones" + File.separatorChar + "comunicacion" + this.comunicacion.getId().toString() + ".pdf"));
//            converter.convert(in, out, options);
//
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
//            Logger.getLogger(ActaBean.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }

    private void loadDocument() {
        try {
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Comunicaciones" + File.separatorChar + "comunicacion" + this.comunicacion.getId().toString() + ".pdf";
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

    public List<Comunicacion> getActasByResponsable() {
        ComunicacionJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ComunicacionJpaController(emf);
            comunicaciones = cJpa.findComunicacionEntities();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        return comunicaciones;
    }
    
}
