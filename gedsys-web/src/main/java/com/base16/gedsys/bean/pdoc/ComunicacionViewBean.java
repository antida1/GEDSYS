/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean.pdoc;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.entities.Certificado;
import com.base16.gedsys.entities.Circular;
import com.base16.gedsys.entities.Comunicacion;
import com.base16.gedsys.entities.Comunicacioncc;
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
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.jsoup.Jsoup;
import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.common.navigation.TextNavigation;
import org.odftoolkit.simple.common.navigation.TextSelection;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author lina
 */
@ManagedBean
@RequestScoped
public class ComunicacionViewBean extends BaseBean implements Serializable {

    private StreamedContent content;
    private String filePath = "";

    @PostConstruct
    public void init() {

    }

    public void showDocument(Comunicacion comunicacion) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "comunicacionb.odt");
            TextNavigation searchFecha;
            TextNavigation consecutivo;
            TextNavigation destinatario;
            TextNavigation abajoRemitente;
            TextNavigation asunto;
            TextNavigation contenido;
            TextNavigation despedida;
            TextNavigation remitente;
            TextNavigation anexos;
            TextNavigation copia;
            TextNavigation proyecto;
            TextNavigation elaboro;

            searchFecha = new TextNavigation("@fecha", odt);
            while (searchFecha.hasNext()) {
                DateFormat df = new SimpleDateFormat();
                TextSelection item = (TextSelection) searchFecha.nextSelection();
                item.replaceWith(DateTimeUtils.getFormattedTime(comunicacion.getFecha(), "EEEEE d")+" de "
                + DateTimeUtils.getFormattedTime(comunicacion.getFecha(), "MMMM") + " de "
                +DateTimeUtils.getFormattedTime(comunicacion.getFecha(), "yyyy"));
            }

            consecutivo = new TextNavigation("@consecutivo", odt);
            while (consecutivo.hasNext()) {
                TextSelection item = (TextSelection) consecutivo.nextSelection();
                if(comunicacion.getConsecutivo() == null || comunicacion.getConsecutivo()==""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(comunicacion.getConsecutivo());
                }
            }

            destinatario = new TextNavigation("@destinatario", odt);
            while (destinatario.hasNext()) {
                TextSelection item = (TextSelection) destinatario.nextSelection();
                item.replaceWith(comunicacion.getDestinatario().getNombres() + " " + comunicacion.getDestinatario().getApelidos() + " " );
            }

            asunto = new TextNavigation("@asunto", odt);
            while (asunto.hasNext()) {
                TextSelection item = (TextSelection) asunto.nextSelection();
                item.replaceWith(comunicacion.getAsunto());
            }

            contenido = new TextNavigation("@contenido", odt);
            while (contenido.hasNext()) {
                TextSelection item = (TextSelection) contenido.nextSelection();
                item.replaceWith(Jsoup.parse(comunicacion.getContenido()).text());
            }

            despedida = new TextNavigation("@despedida", odt);
            while (despedida.hasNext()) {
                TextSelection item = (TextSelection) despedida.nextSelection();
                item.replaceWith(Jsoup.parse(comunicacion.getDespedida()).text());
            }
            
            remitente = new TextNavigation("@remitente", odt);
            while (remitente.hasNext()) {
                TextSelection item = (TextSelection) remitente.nextSelection();
                item.replaceWith(comunicacion.getRemitente().getNombres() + " " + comunicacion.getRemitente().getApelidos());
            }

            abajoRemitente = new TextNavigation("@abajoRemitente", odt);
            while (abajoRemitente.hasNext()) {
                TextSelection item = (TextSelection) abajoRemitente.nextSelection();
                item.replaceWith(comunicacion.getRemitente().getNombres() + " " + comunicacion.getRemitente().getApelidos() + " " );
            }
            
             anexos = new TextNavigation("@anexos", odt);
            while (anexos.hasNext()) {
                TextSelection item = (TextSelection) anexos.nextSelection();
                item.replaceWith(Jsoup.parse(comunicacion.getDescripcionAnexos()).text());
            }
            
            copia = new TextNavigation("@copia", odt);
            while (copia.hasNext()) {
                TextSelection item = (TextSelection) copia.nextSelection();
                String copias = "";
                for(Comunicacioncc comunicacioncc : comunicacion.getComunicacionccList()){
                    if(comunicacioncc != null){
                        if(!"".equals(comunicacioncc.getConCopiaA().getCargo().getNombre()) || comunicacioncc.getConCopiaA().getCargo().getNombre() != null){
                            copias += comunicacioncc.getConCopiaA().getNombres() + "" + comunicacioncc.getConCopiaA().getApelidos() + ", " + comunicacioncc.getConCopiaA().getCargo().getNombre()+ "\n";
                        }else{
                            copias += comunicacioncc.getConCopiaA().getNombres() + "" + comunicacioncc.getConCopiaA().getApelidos()+ "\n";
                        }
                    }else{
                        copias = "";
                    }
                }
                item.replaceWith(copias);
            }
            
            proyecto = new TextNavigation("@proyecto", odt);
            while (proyecto.hasNext()) {
                TextSelection item = (TextSelection) proyecto.nextSelection();
                item.replaceWith(comunicacion.getCreadoPor().getApelidos()+ " " + comunicacion.getCreadoPor().getNombres());
            }
            
            elaboro = new TextNavigation("@elaboro", odt);
            while (elaboro.hasNext()) {
                TextSelection item = (TextSelection) elaboro.nextSelection();
                item.replaceWith(comunicacion.getCreadoPor().getApelidos()+ " " + comunicacion.getCreadoPor().getNombres());
            }

            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Comunicaciones" + File.separatorChar + "comunicacion" + comunicacion.getId().toString() + ".odt");
            odt.close();

            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            //IContext context =  report.createContext();
            //context.put("name", "world");
            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Comunicaciones" + File.separatorChar + "comunicacion" + comunicacion.getId().toString() + ".odt"));
            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Comunicaciones" + File.separatorChar + "comunicacion" + comunicacion.getId().toString() + ".pdf"));
            converter.convert(in, out, options);
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Comunicaciones" + File.separatorChar + "comunicacion" + comunicacion.getId() + ".pdf";
            SessionUtils.getSession().setAttribute("filePathComunicacion", this.filePath);
            RequestContext.getCurrentInstance().execute("PF('denVisorComunicacion').show()");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", e.getMessage()));
            Logger.getLogger(ActaBean.class.getName()).log(Level.SEVERE, null, e);
    }
   }
    
    public void showDocumentFinal(Comunicacion comunicacion) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "comunicacion.odt");
            TextNavigation searchFecha;
            TextNavigation consecutivo;
            TextNavigation destinatario;
            TextNavigation abajoRemitente;
            TextNavigation asunto;
            TextNavigation contenido;
            TextNavigation despedida;
            TextNavigation firma;
            TextNavigation remitente;
            TextNavigation anexos;
            TextNavigation copia;
            TextNavigation proyecto;
            TextNavigation elaboro;

            searchFecha = new TextNavigation("@fecha", odt);
            while (searchFecha.hasNext()) {
                DateFormat df = new SimpleDateFormat();
                TextSelection item = (TextSelection) searchFecha.nextSelection();
                item.replaceWith(DateTimeUtils.getFormattedTime(comunicacion.getFecha(), "EEEEE d")+" de "
                + DateTimeUtils.getFormattedTime(comunicacion.getFecha(), "MMMM") + " de "
                +DateTimeUtils.getFormattedTime(comunicacion.getFecha(), "yyyy"));
            }

            consecutivo = new TextNavigation("@consecutivo", odt);
            while (consecutivo.hasNext()) {
                TextSelection item = (TextSelection) consecutivo.nextSelection();
                if(comunicacion.getConsecutivo() == null || comunicacion.getConsecutivo()==""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(comunicacion.getConsecutivo());
                }
            }

            destinatario = new TextNavigation("@destinatario", odt);
            while (destinatario.hasNext()) {
                TextSelection item = (TextSelection) destinatario.nextSelection();
                item.replaceWith(comunicacion.getDestinatario().getNombres() + " " + comunicacion.getDestinatario().getApelidos() + " " );
            }

            asunto = new TextNavigation("@asunto", odt);
            while (asunto.hasNext()) {
                TextSelection item = (TextSelection) asunto.nextSelection();
                item.replaceWith(comunicacion.getAsunto());
            }

            contenido = new TextNavigation("@contenido", odt);
            while (contenido.hasNext()) {
                TextSelection item = (TextSelection) contenido.nextSelection();
                item.replaceWith(Jsoup.parse(comunicacion.getContenido()).text());
            }

            despedida = new TextNavigation("@despedida", odt);
            while (despedida.hasNext()) {
                TextSelection item = (TextSelection) despedida.nextSelection();
                item.replaceWith(Jsoup.parse(comunicacion.getDespedida()).text());
            }
            
            remitente = new TextNavigation("@remitente", odt);
            while (remitente.hasNext()) {
                TextSelection item = (TextSelection) remitente.nextSelection();
                item.replaceWith(comunicacion.getRemitente().getNombres() + " " + comunicacion.getRemitente().getApelidos());
            }
            
            firma = new TextNavigation("@firma", odt);
            while (firma.hasNext()) {
                TextSelection item = (TextSelection) firma.nextSelection();
                if(comunicacion.getRemitente().getFirma() != null){
                    File f = new File(this.getDocumenstSavePath() + File.separatorChar + "firmas" + File.separatorChar +comunicacion.getRemitente().getFirma());
                    URI u = f.toURI();
                    item.replaceWith(u);
                } else {
                    item.replaceWith(" ");
                }
            }
            
            abajoRemitente = new TextNavigation("@abajoRemitente", odt);
            while (abajoRemitente.hasNext()) {
                TextSelection item = (TextSelection) abajoRemitente.nextSelection();
                item.replaceWith(comunicacion.getRemitente().getNombres() + " " + comunicacion.getRemitente().getApelidos() + " " );
            }
            
             anexos = new TextNavigation("@anexos", odt);
            while (anexos.hasNext()) {
                TextSelection item = (TextSelection) anexos.nextSelection();
                item.replaceWith(Jsoup.parse(comunicacion.getDescripcionAnexos()).text());
            }
            
            copia = new TextNavigation("@copia", odt);
            while (copia.hasNext()) {
                TextSelection item = (TextSelection) copia.nextSelection();
                String copias = "";
                for(Comunicacioncc comunicacioncc : comunicacion.getComunicacionccList()){
                    if(comunicacioncc != null){
                        if(!"".equals(comunicacioncc.getConCopiaA().getCargo().getNombre()) || comunicacioncc.getConCopiaA().getCargo().getNombre() != null){
                            copias += comunicacioncc.getConCopiaA().getNombres() + "" + comunicacioncc.getConCopiaA().getApelidos() + ", " + comunicacioncc.getConCopiaA().getCargo().getNombre()+ "\n";
                        }else{
                            copias += comunicacioncc.getConCopiaA().getNombres() + "" + comunicacioncc.getConCopiaA().getApelidos()+ "\n";
                        }
                    }else{
                        copias = "";
                    }
                }
                item.replaceWith(copias);
            }
            
            proyecto = new TextNavigation("@proyecto", odt);
            while (proyecto.hasNext()) {
                TextSelection item = (TextSelection) proyecto.nextSelection();
                item.replaceWith(comunicacion.getCreadoPor().getApelidos()+ " " + comunicacion.getCreadoPor().getNombres());
            }
            
            elaboro = new TextNavigation("@elaboro", odt);
            while (elaboro.hasNext()) {
                TextSelection item = (TextSelection) elaboro.nextSelection();
                item.replaceWith(comunicacion.getCreadoPor().getApelidos()+ " " + comunicacion.getCreadoPor().getNombres());
            }

            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Comunicaciones" + File.separatorChar + "comunicacion" + comunicacion.getId().toString() + ".odt");
            odt.close();

            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            //IContext context =  report.createContext();
            //context.put("name", "world");
            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Comunicaciones" + File.separatorChar + "comunicacion" + comunicacion.getId().toString() + ".odt"));
            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Comunicaciones" + File.separatorChar + "comunicacion" + comunicacion.getId().toString() + ".pdf"));
            converter.convert(in, out, options);
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Comunicaciones" + File.separatorChar + "comunicacion" + comunicacion.getId() + ".pdf";
            SessionUtils.getSession().setAttribute("filePathComunicacion", this.filePath);
            RequestContext.getCurrentInstance().execute("PF('denVisorComunicacionFinal').show()");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", e.getMessage()));
            Logger.getLogger(ActaBean.class.getName()).log(Level.SEVERE, null, e);
    }
   }
    private void loadDocument() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.filePath = SessionUtils.getSession().getAttribute("filePathComunicacion").toString();
            if (!filePath.isEmpty()) {
                File tempFile = new File(filePath);
                if (tempFile.exists()) {
                    this.content = new DefaultStreamedContent(new FileInputStream(tempFile), new MimetypesFileTypeMap().getContentType(tempFile));
                }
            }
        } catch (FileNotFoundException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Comunicacion", e.getMessage()));
        }
    }

    public StreamedContent getContent() {
        this.loadDocument();
        return content;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
