/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean.pdoc;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.bean.ConsecutivoBean;
import com.base16.gedsys.entities.Carta;
import com.base16.gedsys.entities.Certificado;
import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.CartaJpaController;
import com.base16.gedsys.model.CertificadoJpaController;
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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rober
 */
@Named(value = "certificadoBean")
@ViewScoped
public class CertificadoBean extends BaseBean implements Serializable {

    /**
     * Creates a new instance of CertificadoBean
     */
    private static final long SerialVersionUID = 1L;
    private Certificado certificado = new Certificado();
    private List<Certificado> certificados;
    private String accion;
    
    private Documento documentoRelacionado;
    
    private StreamedContent content;
    private String filePath = "";
    
    public CertificadoBean() {
        this.accion = "Crear";
        this.certificado.setFecha(new Date());
    }

    public Certificado getCertificado() {
        return certificado;
    }

    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }

    public List<Certificado> getCertificados() {
        return certificados;
    }

    public void setCertificados(List<Certificado> certificados) {
        this.certificados = certificados;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    
    
    public void procesar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            switch (accion) {
                case "Crear":
                    crear();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "GEDSYS", "Certificado creado exitosamente"));
                    break;
                case "editar":
                    editar();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "GEDSYS", "Certificado actualizado exitosamente"));
                    break;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(CertificadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
     private void crear() throws Exception {
        CertificadoJpaController cJpa;
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        cJpa = new CertificadoJpaController(emf);

        this.certificado.setFechaCreacion(new Date());
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.certificado.setCreadoPor(usuario);
        this.certificado.setModificadoPor(usuario);
        this.certificado.setFechaModificacion(new Date());
        this.certificado.setEstado(1);
        cJpa.create(this.certificado);

    }   

    private void editar() throws Exception {
        CertificadoJpaController cJpa;
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        cJpa = new CertificadoJpaController(emf);
        this.certificado.setFechaCreacion(new Date());
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.certificado.setModificadoPor(usuario);
        this.certificado.setEstado(2);
        cJpa.edit(this.certificado);
    }

    public void firmar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            EntityManager em = emf.createEntityManager();
            ConsecutivoJpaController cJpa;
            cJpa = new ConsecutivoJpaController(emf);

            CertificadoJpaController caJpa;
            caJpa = new CertificadoJpaController(emf);

            em.getTransaction().begin();
            Consecutivo consec = cJpa.findConsecutivoByTipoConsecutivo("certificado");
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

            this.certificado.setConsecutivo(radicado);
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.certificado.setModificadoPor(usuario);
            this.certificado.setFechaFirma(new Date());
            this.certificado.setEstado(3);
            caJpa.edit(this.certificado);            
            CertificadoViewBean cvb = new CertificadoViewBean();
            cvb.showDocument(this.certificado);

            // TODO: Crear el nuevo documento carta
            Documento documento = new Documento();
            UploadDocument uDoc = new UploadDocument();
            File file = new File(cvb.getFilePath());
            uDoc.upload(file, this.getDocumenstSavePath());
            
            // TODO: Crea nuevo registro de documento
            documento.setRutaArchivo(uDoc.getFileName(file));
            documento.setNombreDocumento(uDoc.getUuid().toString());
            documento.setRemitenteExteno("");
            documento.setDestinatario(this.certificado.getRemitente());
            documento.setAsunto(this.certificado.getContenido());
            documento.setFechaDocumento(this.certificado.getFecha());
            documento.setFechaCreacion(new Date());
            documento.setDetalle(this.certificado.getContenido());
            documento.setDireccion("");
            documento.setEstado(8);
            DocumentoJpaController djc = new DocumentoJpaController(emf);
            djc.create(documento);
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Certificado", "¡Documento Firmado exitosamente!"));

            // TODO: Modificar el documento padre, mover a por archivar.
            if (this.documentoRelacionado != null) {
                this.documentoRelacionado.setDocumentoRelacionado(documento);
                this.documentoRelacionado.setEstado(3);
                djc.edit(this.documentoRelacionado);
            }

        } catch (Exception ex) {
            Logger.getLogger(CartaBean.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Certificado", ex.getMessage()));
        }
                //TODO: Recuperar consecutivo de documento.
//        Usuario usuario = (Usuario) SessionUtils.getUsuario();
//        this.certificado.setModificadoPor(usuario);
//        this.certificado.setFechaFirma(new Date());
//        this.certificado.setEstado(3);
    }     
        

   

//    public void previsualizar() {
//        try {
//
//            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "certificado.odt");
//            TextNavigation searchFecha;
//            TextNavigation consecutivo;
//            TextNavigation destinatario;
//            TextNavigation cargo;
//            TextNavigation asunto;
//            TextNavigation contenido;
//            TextNavigation despedida;
//            TextNavigation remitente;
//
//            searchFecha = new TextNavigation("@fecha", odt);
//            while (searchFecha.hasNext()) {
//                DateFormat df = new SimpleDateFormat();
//                TextSelection item = (TextSelection) searchFecha.nextSelection();
//                item.replaceWith(DateTimeUtils.getFormattedTime(this.certificado.getFecha(), "dd-mm-yyyy"));
//            }
//
//            consecutivo = new TextNavigation("@consecutivo", odt);
//            while (consecutivo.hasNext()) {
//                TextSelection item = (TextSelection) consecutivo.nextSelection();
//                item.replaceWith(this.certificado.getConsecutivo());
//            }
//            
//            cargo = new TextNavigation("@cargo", odt);
//            while (cargo.hasNext()) {
//                TextSelection item = (TextSelection) cargo.nextSelection();
//                item.replaceWith(this.certificado.getRemitente().getCargo().getNombre());
//            }
//
//
//            contenido = new TextNavigation("@contenido", odt);
//            while (contenido.hasNext()) {
//                TextSelection item = (TextSelection) contenido.nextSelection();
//                item.replaceWith(this.certificado.getContenido());
//            }
//
//            remitente = new TextNavigation("@remitente", odt);
//            while (remitente.hasNext()) {
//                TextSelection item = (TextSelection) remitente.nextSelection();
//                item.replaceWith(this.certificado.getRemitente().getNombres() + " " + this.certificado.getRemitente().getApelidos() + " " + this.certificado.getRemitente().getCargo());
//            }
//
//            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "certificado" + this.certificado.getId().toString() + ".odt");
//            odt.close();
//            
//            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
//            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
//            
//            //IContext context =  report.createContext();
//            //context.put("name", "world");
//            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
//            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);
//            
//            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Certificados" + File.separatorChar + "certificado" + this.certificado.getId().toString() + ".odt"));
//            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Certificados" + File.separatorChar + "certificado" + this.certificado.getId().toString() + ".pdf"));
//            converter.convert(in, out, options);
//            
//            
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
//            Logger.getLogger(ActaBean.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }
    
    private void loadDocument(){
        try {
            this.filePath =  this.getDocumenstSavePath() + File.separatorChar + "Certificados" + File.separatorChar + "certificado" + this.certificado.getId().toString() + ".pdf";
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

    public List<Certificado> getCertificadosByResponsable() {
        CertificadoJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CertificadoJpaController(emf);
            certificados = cJpa.findCertificadoEntities();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        return certificados;
    }
    
}
