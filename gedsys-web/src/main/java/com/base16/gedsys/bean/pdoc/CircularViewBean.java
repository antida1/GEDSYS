/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean.pdoc;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.entities.Certificado;
import com.base16.gedsys.entities.Circular;
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
public class CircularViewBean extends BaseBean implements Serializable {

    private StreamedContent content;
    private String filePath = "";

    @PostConstruct
    public void init() {

    }

    public void showDocument(Circular circular) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "circularb.odt");
            TextNavigation searchFecha;
            TextNavigation consecutivo;
            TextNavigation grupo_destinatario;
            TextNavigation cargo_remitente;
            TextNavigation asunto;
            TextNavigation contenido;
            TextNavigation despedida;
            TextNavigation remitente;
            TextNavigation anexos;
            TextNavigation copia;

            searchFecha = new TextNavigation("@fecha", odt);
            while (searchFecha.hasNext()) {
                DateFormat df = new SimpleDateFormat();
                TextSelection item = (TextSelection) searchFecha.nextSelection();
                item.replaceWith(DateTimeUtils.getFormattedTime(circular.getFecha(), "EEEEE d")+" de "
                + DateTimeUtils.getFormattedTime(circular.getFecha(), "MMMM") + " de "
                +DateTimeUtils.getFormattedTime(circular.getFecha(), "yyyy"));
            }

            consecutivo = new TextNavigation("@consecutivo", odt);
            while (consecutivo.hasNext()) {
                TextSelection item = (TextSelection) consecutivo.nextSelection();
                if(circular.getConsecutivo() == null || circular.getConsecutivo()==""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(circular.getConsecutivo());
                }
            }

            grupo_destinatario = new TextNavigation("@grupo_destinatario", odt);
            while (grupo_destinatario.hasNext()) {
                TextSelection item = (TextSelection) grupo_destinatario.nextSelection();
                item.replaceWith(circular.getGrupoDestinatario());
            }

            asunto = new TextNavigation("@asunto", odt);
            while (asunto.hasNext()) {
                TextSelection item = (TextSelection) asunto.nextSelection();
                item.replaceWith(circular.getAsunto());
            }

            contenido = new TextNavigation("@contenido", odt);
            while (contenido.hasNext()) {
                TextSelection item = (TextSelection) contenido.nextSelection();
                item.replaceWith(Jsoup.parse(circular.getContenido()).text());
            }

            despedida = new TextNavigation("@despedida", odt);
            while (contenido.hasNext()) {
                TextSelection item = (TextSelection) despedida.nextSelection();
                item.replaceWith("");
            }

            remitente = new TextNavigation("@remitente", odt);
            while (remitente.hasNext()) {
                TextSelection item = (TextSelection) remitente.nextSelection();
                item.replaceWith(circular.getRemitente().getNombres() + " " + circular.getRemitente().getApelidos());
            }

            cargo_remitente = new TextNavigation("@cargo_remitente", odt);
            while (cargo_remitente.hasNext()) {
                TextSelection item = (TextSelection) cargo_remitente.nextSelection();
                if(circular.getRemitente().getCargo() == null){
                    item.replaceWith("");
                }else{
                    item.replaceWith(circular.getRemitente().getCargo().getNombre());
                }
            }
            
             anexos = new TextNavigation("@anexos", odt);
            while (anexos.hasNext()) {
                TextSelection item = (TextSelection) anexos.nextSelection();
                item.replaceWith("Anexos:" + Jsoup.parse(circular.getAnexos()).text());
            }
            
             copia = new TextNavigation("@copia", odt);
            while (copia.hasNext()) {
                TextSelection item = (TextSelection) copia.nextSelection();
                item.replaceWith("");
            }

            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Circulares" + File.separatorChar + "circular" + circular.getId().toString() + ".odt");
            odt.close();

            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            //IContext context =  report.createContext();
            //context.put("name", "world");
            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Circulares" + File.separatorChar + "circular" + circular.getId().toString() + ".odt"));
            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Circulares" + File.separatorChar + "circular" +circular.getId().toString() + ".pdf"));
            converter.convert(in, out, options);
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Circulares" + File.separatorChar + "circular" + circular.getId() + ".pdf";
            SessionUtils.getSession().setAttribute("filePathCircular", this.filePath);
            RequestContext.getCurrentInstance().execute("PF('denVisorCircular').show()");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", e.getMessage()));
            Logger.getLogger(ActaBean.class.getName()).log(Level.SEVERE, null, e);
    }
   }
    
    public void showDocumentFinal(Circular circular) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "circular.odt");
            TextNavigation searchFecha;
            TextNavigation consecutivo;
            TextNavigation grupo_destinatario;
            TextNavigation cargo_remitente;
            TextNavigation asunto;
            TextNavigation contenido;
            TextNavigation despedida;
            TextNavigation firma;
            TextNavigation remitente;
            TextNavigation anexos;
            TextNavigation copia;

            searchFecha = new TextNavigation("@fecha", odt);
            while (searchFecha.hasNext()) {
                DateFormat df = new SimpleDateFormat();
                TextSelection item = (TextSelection) searchFecha.nextSelection();
                item.replaceWith(DateTimeUtils.getFormattedTime(circular.getFecha(), "EEEEE d")+" de "
                + DateTimeUtils.getFormattedTime(circular.getFecha(), "MMMM") + " de "
                +DateTimeUtils.getFormattedTime(circular.getFecha(), "yyyy"));
            }

            consecutivo = new TextNavigation("@consecutivo", odt);
            while (consecutivo.hasNext()) {
                TextSelection item = (TextSelection) consecutivo.nextSelection();
                if(circular.getConsecutivo() == null || circular.getConsecutivo()==""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(circular.getConsecutivo());
                }
            }

            grupo_destinatario = new TextNavigation("@grupo_destinatario", odt);
            while (grupo_destinatario.hasNext()) {
                TextSelection item = (TextSelection) grupo_destinatario.nextSelection();
                item.replaceWith(circular.getGrupoDestinatario());
            }

            asunto = new TextNavigation("@asunto", odt);
            while (asunto.hasNext()) {
                TextSelection item = (TextSelection) asunto.nextSelection();
                item.replaceWith(circular.getAsunto());
            }

            contenido = new TextNavigation("@contenido", odt);
            while (contenido.hasNext()) {
                TextSelection item = (TextSelection) contenido.nextSelection();
                item.replaceWith(Jsoup.parse(circular.getContenido()).text());
            }

            despedida = new TextNavigation("@despedida", odt);
            while (contenido.hasNext()) {
                TextSelection item = (TextSelection) despedida.nextSelection();
                item.replaceWith("");
            }
            
            firma = new TextNavigation("@firma", odt);
            while (firma.hasNext()) {
                TextSelection item = (TextSelection) firma.nextSelection();
                if(circular.getRemitente().getFirma() != null){
                    File f = new File(this.getDocumenstSavePath() + File.separatorChar + "firmas" + File.separatorChar +circular.getRemitente().getFirma());
                    URI u = f.toURI();
                    item.replaceWith(u);
                } else {
                    item.replaceWith(" ");
                }
            }

            remitente = new TextNavigation("@remitente", odt);
            while (remitente.hasNext()) {
                TextSelection item = (TextSelection) remitente.nextSelection();
                item.replaceWith(circular.getRemitente().getNombres() + " " + circular.getRemitente().getApelidos());
            }

            cargo_remitente = new TextNavigation("@cargo_remitente", odt);
            while (cargo_remitente.hasNext()) {
                TextSelection item = (TextSelection) cargo_remitente.nextSelection();
                if(circular.getRemitente().getCargo() == null){
                    item.replaceWith("");
                }else{
                    item.replaceWith(circular.getRemitente().getCargo().getNombre());
                }
            }
            
             anexos = new TextNavigation("@anexos", odt);
            while (anexos.hasNext()) {
                TextSelection item = (TextSelection) anexos.nextSelection();
                item.replaceWith("Anexos:" + Jsoup.parse(circular.getAnexos()).text());
            }
            
             copia = new TextNavigation("@copia", odt);
            while (copia.hasNext()) {
                TextSelection item = (TextSelection) copia.nextSelection();
                item.replaceWith("");
            }

            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Circulares" + File.separatorChar + "circular" + circular.getId().toString() + ".odt");
            odt.close();

            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            //IContext context =  report.createContext();
            //context.put("name", "world");
            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Circulares" + File.separatorChar + "circular" + circular.getId().toString() + ".odt"));
            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Circulares" + File.separatorChar + "circular" +circular.getId().toString() + ".pdf"));
            converter.convert(in, out, options);
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Circulares" + File.separatorChar + "circular" + circular.getId() + ".pdf";
            SessionUtils.getSession().setAttribute("filePathCircular", this.filePath);
            RequestContext.getCurrentInstance().execute("PF('denVisorCircularFinal').show()");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", e.getMessage()));
            Logger.getLogger(ActaBean.class.getName()).log(Level.SEVERE, null, e);
    }
   }
    public void showDocumentFinalImprimir(Circular circular) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "circular.odt");
            TextNavigation searchFecha;
            TextNavigation consecutivo;
            TextNavigation grupo_destinatario;
            TextNavigation cargo_remitente;
            TextNavigation asunto;
            TextNavigation contenido;
            TextNavigation despedida;
            TextNavigation firma;
            TextNavigation remitente;
            TextNavigation anexos;
            TextNavigation copia;

            searchFecha = new TextNavigation("@fecha", odt);
            while (searchFecha.hasNext()) {
                DateFormat df = new SimpleDateFormat();
                TextSelection item = (TextSelection) searchFecha.nextSelection();
                item.replaceWith(DateTimeUtils.getFormattedTime(circular.getFecha(), "EEEEE d")+" de "
                + DateTimeUtils.getFormattedTime(circular.getFecha(), "MMMM") + " de "
                +DateTimeUtils.getFormattedTime(circular.getFecha(), "yyyy"));
            }

            consecutivo = new TextNavigation("@consecutivo", odt);
            while (consecutivo.hasNext()) {
                TextSelection item = (TextSelection) consecutivo.nextSelection();
                if(circular.getConsecutivo() == null || circular.getConsecutivo()==""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(circular.getConsecutivo());
                }
            }

            grupo_destinatario = new TextNavigation("@grupo_destinatario", odt);
            while (grupo_destinatario.hasNext()) {
                TextSelection item = (TextSelection) grupo_destinatario.nextSelection();
                item.replaceWith(circular.getGrupoDestinatario());
            }

            asunto = new TextNavigation("@asunto", odt);
            while (asunto.hasNext()) {
                TextSelection item = (TextSelection) asunto.nextSelection();
                item.replaceWith(circular.getAsunto());
            }

            contenido = new TextNavigation("@contenido", odt);
            while (contenido.hasNext()) {
                TextSelection item = (TextSelection) contenido.nextSelection();
                item.replaceWith(Jsoup.parse(circular.getContenido()).text());
            }

            despedida = new TextNavigation("@despedida", odt);
            while (contenido.hasNext()) {
                TextSelection item = (TextSelection) despedida.nextSelection();
                item.replaceWith("");
            }
            
            firma = new TextNavigation("@firma", odt);
            while (firma.hasNext()) {
                TextSelection item = (TextSelection) firma.nextSelection();               
                item.replaceWith(" ");                
            }

            remitente = new TextNavigation("@remitente", odt);
            while (remitente.hasNext()) {
                TextSelection item = (TextSelection) remitente.nextSelection();
                item.replaceWith(circular.getRemitente().getNombres() + " " + circular.getRemitente().getApelidos());
            }

            cargo_remitente = new TextNavigation("@cargo_remitente", odt);
            while (cargo_remitente.hasNext()) {
                TextSelection item = (TextSelection) cargo_remitente.nextSelection();
                if(circular.getRemitente().getCargo() == null){
                    item.replaceWith("");
                }else{
                    item.replaceWith(circular.getRemitente().getCargo().getNombre());
                }
            }
            
             anexos = new TextNavigation("@anexos", odt);
            while (anexos.hasNext()) {
                TextSelection item = (TextSelection) anexos.nextSelection();
                item.replaceWith("Anexos:" + Jsoup.parse(circular.getAnexos()).text());
            }
            
             copia = new TextNavigation("@copia", odt);
            while (copia.hasNext()) {
                TextSelection item = (TextSelection) copia.nextSelection();
                item.replaceWith("");
            }

            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Circulares" + File.separatorChar + "circular" + circular.getId().toString() + ".odt");
            odt.close();

            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            //IContext context =  report.createContext();
            //context.put("name", "world");
            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Circulares" + File.separatorChar + "circular" + circular.getId().toString() + ".odt"));
            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Circulares" + File.separatorChar + "circular" +circular.getId().toString() + ".pdf"));
            converter.convert(in, out, options);
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Circulares" + File.separatorChar + "circular" + circular.getId() + ".pdf";
            SessionUtils.getSession().setAttribute("filePathCircular", this.filePath);
            RequestContext.getCurrentInstance().execute("PF('denVisorCircularFinal').show()");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", e.getMessage()));
            Logger.getLogger(ActaBean.class.getName()).log(Level.SEVERE, null, e);
    }
   }

    private void loadDocument() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.filePath = SessionUtils.getSession().getAttribute("filePathCircular").toString();
            if (!filePath.isEmpty()) {
                File tempFile = new File(filePath);
                if (tempFile.exists()) {
                    this.content = new DefaultStreamedContent(new FileInputStream(tempFile), new MimetypesFileTypeMap().getContentType(tempFile));
                }
            }
        } catch (FileNotFoundException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Circular", e.getMessage()));
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
