/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean.pdoc;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Informe;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.fcm.PushFCMNotification;
import com.base16.gedsys.model.ConsecutivoJpaController;
import com.base16.gedsys.model.ConstanciaJpaController;
import com.base16.gedsys.model.DocumentoJpaController;
import com.base16.gedsys.model.InformeJpaController;
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
@Named(value = "informeBean")
@ViewScoped
public class InformeBean extends BaseBean implements Serializable {

    /**
     * Creates a new instance of InformeBean
     */
    private static final long SerialVersionUID = 1L;
    private Informe informe = new Informe();
    private List<Informe> informes;
    private String accion;
    
    private Documento documentoRelacionado;

    private StreamedContent content;
    private String filePath = "";

    public InformeBean() {
        this.accion = "Crear";
        this.informe.setFecha(new Date());
    }

    public Informe getInforme() {
        return informe;
    }

    public void setInforme(Informe informe) {
        this.informe = informe;
    }

    public List<Informe> getInformes() {
        return informes;
    }

    public void setInformes(List<Informe> informes) {
        this.informes = informes;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public void firmarInforme(Informe informe) {
        this.informe = informe;
        RequestContext.getCurrentInstance().execute("PF('denFirmarInforme').show()");
    }
    public void editarInforme(Informe informe) {
        this.informe = informe;
        this.accion = "editar";
        RequestContext.getCurrentInstance().execute("PF('denEditarInforme').show()");
    }
    
    public void procesar() {
        try {
            switch (accion) {
                case "Crear":
                    crear();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "GEDSYS", "¡Informe creado exitosamente!"));
                    break;
                case "editar":
                    editar();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "GEDSYS", "¡Informe modificado exitosamente!"));
                    break;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", e.getMessage()));
            Logger.getLogger(InformeBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    private void crear() throws Exception {
        InformeJpaController cJpa;
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        cJpa = new InformeJpaController(emf);

        this.informe.setFechaCreacion(new Date());
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.informe.setCreadoPor(usuario);
        this.informe.setModificadoPor(usuario);
        this.informe.setFechaModificacion(new Date());
        this.informe.setEstado(1);
        cJpa.create(this.informe);
    }

    private void editar() throws Exception {
        InformeJpaController cJpa;
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        cJpa = new InformeJpaController(emf);
        this.informe.setFechaCreacion(new Date());
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.informe.setModificadoPor(usuario);
        this.informe.setEstado(1);
        cJpa.edit(this.informe);
    }

    public void firmar() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            EntityManager em = emf.createEntityManager();
            ConsecutivoJpaController cJpa;
            cJpa = new ConsecutivoJpaController(emf);

            InformeJpaController caJpa;
            caJpa = new InformeJpaController(emf);

            em.getTransaction().begin();
            Consecutivo consec = cJpa.findConsecutivoByTipoConsecutivo("informe");
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

            this.informe.setConsecutivo(radicado);
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.informe.setModificadoPor(usuario);
            this.informe.setFechaFirma(new Date());
            this.informe.setEstado(0);
            caJpa.edit(this.informe);            
            InformeViewBean cvb = new InformeViewBean();
            cvb.showDocumentFinal(this.informe);

            // TODO: Crear el nuevo documento carta
            Documento documento = new Documento();
            UploadDocument uDoc = new UploadDocument();
            File file = new File(cvb.getFilePath());
            uDoc.upload(file, this.getDocumenstSavePath());
            
            // TODO: Crea nuevo registro de documento
            documento.setRutaArchivo(uDoc.getFileName(file));
            documento.setNombreDocumento(uDoc.getUuid().toString());
            documento.setRemitenteExteno("");
            documento.setDestinatario(this.informe.getRemitente());
            documento.setAsunto(this.informe.getObjetivo());
            documento.setFechaDocumento(this.informe.getFecha());
            documento.setFechaCreacion(new Date());
            documento.setDetalle(this.informe.getConclusiones());
            documento.setDireccion("");
            documento.setEstado(9);
            documento.setConsecutivo(this.informe.getConsecutivo());
            DocumentoJpaController djc = new DocumentoJpaController(emf);
            djc.create(documento);
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe", "¡Documento Firmado exitosamente!"));

            // TODO: Modificar el documento padre, mover a por archivar.
            if (this.documentoRelacionado != null) {
                this.documentoRelacionado.setDocumentoRelacionado(documento);
                this.documentoRelacionado.setEstado(3);
                djc.edit(this.documentoRelacionado);
            }

        } catch (Exception ex) {
            Logger.getLogger(CircularBean.class.getName()).log(Level.SEVERE, null, ex);
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Informe", "¡No existe el consecutivo para informes internas en la Entidad Consecutivo!"));
        }
        
        //TODO: Recuperar consecutivo de documento.
//        Usuario usuario = (Usuario) SessionUtils.getUsuario();
//        this.informe.setModificadoPor(usuario);
//        this.informe.setFechaFirma(new Date());
//        this.informe.setEstado(3);
    }
    
     public void limpiar() throws IOException{
        this.informe = null;
        this.informe = new Informe();
        this.informe.setFecha(new Date());
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

            InformeJpaController caJpa;
            caJpa = new InformeJpaController(emf);

            em.getTransaction().begin();
            Consecutivo consec = cJpa.findConsecutivoByTipoConsecutivo("informe");
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

            this.informe.setConsecutivo(radicado);
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.informe.setModificadoPor(usuario);
            this.informe.setFechaFirma(new Date());
            this.informe.setEstado(0);
            caJpa.edit(this.informe);            
            InformeViewBean cvb = new InformeViewBean();
            cvb.showDocumentFinalImprimir(this.informe);
            
            // TODO: Crear el nuevo documento carta
            Documento documento = new Documento();
            UploadDocument uDoc = new UploadDocument();
            File file = new File(cvb.getFilePath());
            uDoc.upload(file, this.getDocumenstSavePath());
            
            // TODO: Crea nuevo registro de documento
            documento.setRutaArchivo(uDoc.getFileName(file));
            documento.setNombreDocumento(uDoc.getUuid().toString());
            documento.setRemitenteExteno("");
            documento.setConsecutivo(this.informe.getConsecutivo());
            documento.setDestinatario(this.informe.getRemitente());
            documento.setAsunto(this.informe.getObjetivo());
            documento.setFechaDocumento(this.informe.getFecha());
            documento.setFechaCreacion(new Date());
            documento.setDetalle(this.informe.getConclusiones());
            documento.setDireccion("");
            documento.setEstado(8);
            DocumentoJpaController djc = new DocumentoJpaController(emf);
            djc.create(documento);
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe", "¡Documento generado correctamente!"));            

        } catch (Exception ex) {
            Logger.getLogger(CartaBean.class.getName()).log(Level.SEVERE, null, ex);
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Informe", "No existe el consecutivo para informes en la Entidad Consecutivo"));
            em.getTransaction().rollback();
        }
    }


//    public void previsualizar() {
//        try {
//
//            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "informe.odt");
//            TextNavigation searchFecha;
//            TextNavigation consecutivo;
//            TextNavigation cargo;
//            TextNavigation objetivo;
//            TextNavigation conclusiones;
//            TextNavigation remitente;
//
//            searchFecha = new TextNavigation("@fecha", odt);
//            while (searchFecha.hasNext()) {
//                DateFormat df = new SimpleDateFormat();
//                TextSelection item = (TextSelection) searchFecha.nextSelection();
//                item.replaceWith(DateTimeUtils.getFormattedTime(this.informe.getFecha(), "dd-mm-yyyy"));
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
//                item.replaceWith(this.informe.getRemitente().getCargo().getNombre());
//            }
//
//            objetivo = new TextNavigation("@objetivo", odt);
//            while (objetivo.hasNext()) {
//                TextSelection item = (TextSelection) objetivo.nextSelection();
//                item.replaceWith(this.informe.getObjetivo());
//            }
//            
//            conclusiones = new TextNavigation("@conclusiones", odt);
//            while (conclusiones.hasNext()) {
//                TextSelection item = (TextSelection) conclusiones.nextSelection();
//                item.replaceWith(this.informe.getConclusiones());
//            }
//
//            remitente = new TextNavigation("@remitente", odt);
//            while (remitente.hasNext()) {
//                TextSelection item = (TextSelection) remitente.nextSelection();
//                item.replaceWith(this.informe.getRemitente().getNombres() + " " + this.informe.getRemitente().getApelidos());
//            }
//            
//            cargo = new TextNavigation("@cargo", odt);
//            while (cargo.hasNext()) {
//                TextSelection item = (TextSelection) cargo.nextSelection();
//                item.replaceWith(this.informe.getRemitente().getCargo().getNombre());
//            }
//
//            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Informes" + File.separatorChar + "informe" + this.informe.getId().toString() + ".odt");
//            odt.close();
//
//            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
//            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
//            //IContext context =  report.createContext();
//            //context.put("name", "world");
//            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
//            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);
//
//            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Informes" + File.separatorChar + "informe" + this.informe.getId().toString() + ".odt"));
//            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Informes" + File.separatorChar + "informe" + this.informe.getId().toString() + ".pdf"));
//            converter.convert(in, out, options);
//
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
//            Logger.getLogger(ActaBean.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }

    private void loadDocument() {
        try {
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Informes" + File.separatorChar + "informe" + this.informe.getId().toString() + ".pdf";
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

    public List<Informe> getInformesByResponsable() {
        InformeJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new InformeJpaController(emf);
            informes = cJpa.findInformeEntities();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(InformeBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        return informes;
    }

}
