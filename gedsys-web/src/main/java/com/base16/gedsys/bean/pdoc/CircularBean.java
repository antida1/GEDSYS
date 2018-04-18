/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean.pdoc;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.bean.ConsecutivoBean;
import com.base16.gedsys.entities.Carta;
import com.base16.gedsys.entities.Circular;
import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.ActaJpaController;
import com.base16.gedsys.model.CartaJpaController;
import com.base16.gedsys.model.CircularJpaController;
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
@Named(value = "circularBean")
@ViewScoped
public class CircularBean extends BaseBean implements Serializable {

    /**
     * Creates a new instance of CircularBean
     */
    private static final long SerialVersionUID = 1L;
    private Circular circular = new Circular();
    private List<Circular> circulares;
    private String accion;
    
    private Documento documentoRelacionado;

    private StreamedContent content;
    private String filePath = "";

    public CircularBean() {
        this.accion = "Crear";
        this.circular.setFecha(new Date());
        
    }

    public Circular getCircular() {
        return circular;
    }

    public void setCircular(Circular circular) {
        this.circular = circular;
    }

    public List<Circular> getCirculares() {
        return circulares;
    }

    public void setCirculares(List<Circular> circulares) {
        this.circulares = circulares;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public void firmarCircular(Circular circular) {
        this.circular = circular;
        RequestContext.getCurrentInstance().execute("PF('denFirmarCircular').show()");
    }
    public void editarCircular(Circular circular) {
        this.circular = circular;
        this.accion = "editar";
        RequestContext.getCurrentInstance().execute("PF('denEditarCircular').show()");
    }

    public void procesar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            switch (accion) {
                case "Crear":
                    crear();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "GEDSYS", "¡Circular creado exitosamente!"));
                    break;
                case "editar":
                    editar();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "GEDSYS", "¡Circular modificado exitosamente!"));
                    break;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", e.getMessage()));
            Logger.getLogger(CircularBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    private void crear() throws Exception {
        CircularJpaController cJpa;
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        cJpa = new CircularJpaController(emf);

        this.circular.setFechaCreacion(new Date());
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.circular.setCreadoPor(usuario);
        this.circular.setModificadoPor(usuario);
        this.circular.setFechaModifiacion(new Date());
        this.circular.setEstado(1);
        cJpa.create(this.circular);
    }

    private void editar() throws Exception {
        CircularJpaController cJpa;
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        cJpa = new CircularJpaController(emf);
        this.circular.setFechaCreacion(new Date());
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.circular.setModificadoPor(usuario);
        this.circular.setEstado(1);
        cJpa.edit(this.circular);
    }

    public void firmar() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            EntityManager em = emf.createEntityManager();
            ConsecutivoJpaController cJpa;
            cJpa = new ConsecutivoJpaController(emf);

            CircularJpaController caJpa;
            caJpa = new CircularJpaController(emf);

            em.getTransaction().begin();
            Consecutivo consec = cJpa.findConsecutivoByTipoConsecutivo("circular");
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

            this.circular.setConsecutivo(radicado);
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.circular.setModificadoPor(usuario);
            this.circular.setFechaFirma(new Date());
            this.circular.setEstado(0);
            caJpa.edit(this.circular);            
            CircularViewBean cvb = new CircularViewBean();
            cvb.showDocumentFinal(this.circular);

            // TODO: Crear el nuevo documento carta
            Documento documento = new Documento();
            UploadDocument uDoc = new UploadDocument();
            File file = new File(cvb.getFilePath());
            uDoc.upload(file, this.getDocumenstSavePath());
            
            // TODO: Crea nuevo registro de documento
            documento.setRutaArchivo(uDoc.getFileName(file));
            documento.setNombreDocumento(uDoc.getUuid().toString());
            documento.setRemitenteExteno(this.circular.getGrupoDestinatario());
            documento.setDestinatario(this.circular.getRemitente());
            documento.setAsunto(this.circular.getAsunto());
            documento.setFechaDocumento(this.circular.getFecha());
            documento.setFechaCreacion(new Date());
            documento.setDetalle(this.circular.getAsunto());
            documento.setDireccion("");
            documento.setEstado(9);
            documento.setConsecutivo(this.circular.getConsecutivo());
            DocumentoJpaController djc = new DocumentoJpaController(emf);
            djc.create(documento);
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Circular", "¡Documento Firmado exitosamente!"));

            // TODO: Modificar el documento padre, mover a por archivar.
            if (this.documentoRelacionado != null) {
                this.documentoRelacionado.setDocumentoRelacionado(documento);
                this.documentoRelacionado.setEstado(3);
                djc.edit(this.documentoRelacionado);
            }

        } catch (Exception ex) {
            Logger.getLogger(CircularBean.class.getName()).log(Level.SEVERE, null, ex);
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Circular", "¡No existe el consecutivo para circulares en la Entidad Consecutivo!"));
        }
        
        //TODO: Recuperar consecutivo de documento.
//        Usuario usuario = (Usuario) SessionUtils.getUsuario();
//        this.circular.setModificadoPor(usuario);
//        this.circular.setFechaFirma(new Date());
//        this.circular.setEstado(3);
    }
    
    public void limpiar() throws IOException{
        this.circular = null;
        this.circular = new Circular();
        this.circular.setFecha(new Date());
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

            CircularJpaController caJpa;
            caJpa = new CircularJpaController(emf);

            em.getTransaction().begin();
            Consecutivo consec = cJpa.findConsecutivoByTipoConsecutivo("circular");
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

            this.circular.setConsecutivo(radicado);
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.circular.setModificadoPor(usuario);
            this.circular.setFechaFirma(new Date());
            this.circular.setEstado(0);
            caJpa.edit(this.circular);            
            CircularViewBean cvb = new CircularViewBean();
            cvb.showDocumentFinalImprimir(this.circular);
            
             // TODO: Crear el nuevo documento carta
            Documento documento = new Documento();
            UploadDocument uDoc = new UploadDocument();
            File file = new File(cvb.getFilePath());
            uDoc.upload(file, this.getDocumenstSavePath());
            
            // TODO: Crea nuevo registro de documento
            documento.setRutaArchivo(uDoc.getFileName(file));
            documento.setNombreDocumento(uDoc.getUuid().toString());
            documento.setConsecutivo(this.circular.getConsecutivo());
            documento.setRemitenteExteno(this.circular.getGrupoDestinatario());
            documento.setDestinatario(this.circular.getRemitente());
            documento.setAsunto(this.circular.getAsunto());
            documento.setFechaDocumento(this.circular.getFecha());
            documento.setFechaCreacion(new Date());
            documento.setDetalle(this.circular.getAsunto());
            documento.setDireccion("");
            documento.setEstado(8);
            DocumentoJpaController djc = new DocumentoJpaController(emf);
            djc.create(documento);
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Circular", "¡Documento generado correctamente!"));            

        } catch (Exception ex) {
            Logger.getLogger(CartaBean.class.getName()).log(Level.SEVERE, null, ex);
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Circular", "No existe el consecutivo para circulares en la Entidad Consecutivo"));
            em.getTransaction().rollback();
        }
    }

//    public void previsualizar() {
//        try {
//
//            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "circular.odt");
//            TextNavigation searchFecha;
//            TextNavigation consecutivo;
//            TextNavigation grupo_destinatario;
//            TextNavigation cargo_remitente;
//            TextNavigation asunto;
//            TextNavigation contenido;
//            TextNavigation despedida;
//            TextNavigation remitente;
//            TextNavigation anexos;
//            TextNavigation copia;
//
//            searchFecha = new TextNavigation("@fecha", odt);
//            while (searchFecha.hasNext()) {
//                DateFormat df = new SimpleDateFormat();
//                TextSelection item = (TextSelection) searchFecha.nextSelection();
//                item.replaceWith(DateTimeUtils.getFormattedTime(this.circular.getFecha(), "dd-mm-yyyy"));
//            }
//
//            consecutivo = new TextNavigation("@consecutivo", odt);
//            while (consecutivo.hasNext()) {
//                TextSelection item = (TextSelection) consecutivo.nextSelection();
//                item.replaceWith(this.circular.getConsecutivo());
//            }
//
//            grupo_destinatario = new TextNavigation("@grupo_destinatario", odt);
//            while (grupo_destinatario.hasNext()) {
//                TextSelection item = (TextSelection) grupo_destinatario.nextSelection();
//                item.replaceWith(this.circular.getGrupoDestinatario());
//            }
//
//            asunto = new TextNavigation("@asunto", odt);
//            while (asunto.hasNext()) {
//                TextSelection item = (TextSelection) asunto.nextSelection();
//                item.replaceWith(this.circular.getAsunto());
//            }
//
//            contenido = new TextNavigation("@contenido", odt);
//            while (contenido.hasNext()) {
//                TextSelection item = (TextSelection) contenido.nextSelection();
//                item.replaceWith(this.circular.getContenido());
//            }
//
//            despedida = new TextNavigation("@despedida", odt);
//            while (contenido.hasNext()) {
//                TextSelection item = (TextSelection) despedida.nextSelection();
//                item.replaceWith("");
//            }
//
//            remitente = new TextNavigation("@remitente", odt);
//            while (remitente.hasNext()) {
//                TextSelection item = (TextSelection) remitente.nextSelection();
//                item.replaceWith(this.circular.getRemitente().getNombres() + " " + this.circular.getRemitente().getApelidos());
//            }
//
//            cargo_remitente = new TextNavigation("@cargo_remitente", odt);
//            while (cargo_remitente.hasNext()) {
//                TextSelection item = (TextSelection) cargo_remitente.nextSelection();
//                item.replaceWith(this.circular.getRemitente().getCargo().getNombre());
//            }
//            
//             anexos = new TextNavigation("@anexos", odt);
//            while (anexos.hasNext()) {
//                TextSelection item = (TextSelection) anexos.nextSelection();
//                item.replaceWith("Anexos:" + this.circular.getAnexos());
//            }
//            
//             copia = new TextNavigation("@copia", odt);
//            while (copia.hasNext()) {
//                TextSelection item = (TextSelection) copia.nextSelection();
//                item.replaceWith("");
//            }
//
//            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "circular" + this.circular.getId().toString() + ".odt");
//            odt.close();
//
//            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
//            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
//            //IContext context =  report.createContext();
//            //context.put("name", "world");
//            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
//            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);
//
//            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Circulares" + File.separatorChar + "circular" + this.circular.getId().toString() + ".odt"));
//            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Circulares" + File.separatorChar + "circular" + this.circular.getId().toString() + ".pdf"));
//            converter.convert(in, out, options);
//
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
//            Logger.getLogger(ActaBean.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }

    private void loadDocument() {
        try {
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Circulares" + File.separatorChar + "circular" + this.circular.getId().toString() + ".pdf";
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

    public List<Circular> getCircularesByResponsable() {
        CircularJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CircularJpaController(emf);
            circulares = cJpa.findCircularEntities();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        return circulares;
    }

}
