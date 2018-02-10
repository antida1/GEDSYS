/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean.pdoc;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.entities.Constancia;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.ConstanciaJpaController;
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
        this.constancia.setEstado(2);
        cJpa.edit(this.constancia);
    }

    public void firmar() {
        //TODO: Recuperar consecutivo de documento.
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.constancia.setModificadoPor(usuario);
        this.constancia.setFechaFirma(new Date());
        this.constancia.setEstado(3);
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