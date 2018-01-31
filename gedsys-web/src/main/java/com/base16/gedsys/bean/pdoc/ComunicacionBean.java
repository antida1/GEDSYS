/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean.pdoc;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.bean.ConsecutivoBean;
import com.base16.gedsys.entities.Comunicacion;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.ComunicacionJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import com.base16.utils.DateTimeUtils;
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

    public void firmar() {
        //TODO: Recuperar consecutivo de documento.
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.comunicacion.setModificadoPor(usuario);
        this.comunicacion.setFechaFirma(new Date().toString());
        this.comunicacion.setEstado("3");
    }

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
