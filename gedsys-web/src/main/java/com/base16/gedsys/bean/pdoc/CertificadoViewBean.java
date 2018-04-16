/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean.pdoc;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.entities.Certificado;
import com.base16.gedsys.web.utils.SessionUtils;
import com.base16.utils.DateTimeUtils;
import com.lowagie.text.Image;
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
public class CertificadoViewBean extends BaseBean implements Serializable {

    private StreamedContent content;
    private String filePath = "";

    @PostConstruct
    public void init() {

    }

    public void showDocument(Certificado certificado) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "certificadob.odt");
            TextNavigation searchConsecutivo;
            TextNavigation searchFecha;            
            TextNavigation cargo;
            TextNavigation contenido;
            TextNavigation remitente;

            searchConsecutivo = new TextNavigation("@consecutivo", odt);
            while (searchConsecutivo.hasNext()) {
                TextSelection item = (TextSelection) searchConsecutivo.nextSelection();
                if(certificado.getConsecutivo() == null || certificado.getConsecutivo() == ""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(certificado.getConsecutivo());
                }
            }

            searchFecha = new TextNavigation("@fecha", odt);
            while (searchFecha.hasNext()) {
                DateFormat df = new SimpleDateFormat();
                TextSelection item = (TextSelection) searchFecha.nextSelection();
                item.replaceWith(DateTimeUtils.getFormattedTime(certificado.getFecha(), "EEEEE d")+" de "
                + DateTimeUtils.getFormattedTime(certificado.getFecha(), "MMMM") + " de "
                +DateTimeUtils.getFormattedTime(certificado.getFecha(), "yyyy"));
            }

            cargo = new TextNavigation("@cargo", odt);
            while (cargo.hasNext()) {
                TextSelection item = (TextSelection) cargo.nextSelection();
                if(certificado.getRemitente().getCargo() != null){
                    item.replaceWith(certificado.getRemitente().getCargo().getNombre());
                }else{
                    item.replaceWith(" ");
                }
            }            

            contenido = new TextNavigation("@contenido", odt);
            while (contenido.hasNext()) {
                TextSelection item = (TextSelection) contenido.nextSelection();
                item.replaceWith(Jsoup.parse(certificado.getContenido()).text());
            }

            remitente = new TextNavigation("@remitente", odt);
            while (remitente.hasNext()) {
                TextSelection item = (TextSelection) remitente.nextSelection();
                if(certificado.getRemitente().getCargo() != null){
                    item.replaceWith(certificado.getRemitente().getNombres() + " " + certificado.getRemitente().getApelidos());
                } else {
                    item.replaceWith(certificado.getRemitente().getNombres() + " " + certificado.getRemitente().getApelidos());
                }
            }

            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Certificados" + File.separatorChar + "certificado" + certificado.getId().toString() + ".odt");
            odt.close();

            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            //IContext context =  report.createContext();
            //context.put("name", "world");
            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Certificados" + File.separatorChar + "certificado" + certificado.getId().toString() + ".odt"));
            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Certificados" + File.separatorChar + "certificado" + certificado.getId().toString() + ".pdf"));
            converter.convert(in, out, options);
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Certificados" + File.separatorChar + "certificado" + certificado.getId() + ".pdf";
            SessionUtils.getSession().setAttribute("filePathCertificado", this.filePath);
            RequestContext.getCurrentInstance().execute("PF('denVisorCertificado').show()");
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Certificado", e.getMessage()));
            Logger.getLogger(CertificadoViewBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void showDocumentFinal(Certificado certificado) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "certificado.odt");
            TextNavigation searchConsecutivo;
            TextNavigation searchFecha;            
            TextNavigation cargo;
            TextNavigation contenido;
            TextNavigation remitente;
            TextNavigation firma;

            searchConsecutivo = new TextNavigation("@consecutivo", odt);
            while (searchConsecutivo.hasNext()) {
                TextSelection item = (TextSelection) searchConsecutivo.nextSelection();
                if(certificado.getConsecutivo() == null || certificado.getConsecutivo() == ""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(certificado.getConsecutivo());
                }
            }

            searchFecha = new TextNavigation("@fecha", odt);
            while (searchFecha.hasNext()) {
                DateFormat df = new SimpleDateFormat();
                TextSelection item = (TextSelection) searchFecha.nextSelection();
                item.replaceWith(DateTimeUtils.getFormattedTime(certificado.getFecha(), "EEEEE d")+" de "
                + DateTimeUtils.getFormattedTime(certificado.getFecha(), "MMMM") + " de "
                +DateTimeUtils.getFormattedTime(certificado.getFecha(), "yyyy"));
            }

            cargo = new TextNavigation("@cargo", odt);
            while (cargo.hasNext()) {
                TextSelection item = (TextSelection) cargo.nextSelection();
                if(certificado.getRemitente().getCargo() != null){
                    item.replaceWith(certificado.getRemitente().getCargo().getNombre());
                }else{
                    item.replaceWith(" ");
                }
            }            

            contenido = new TextNavigation("@contenido", odt);
            while (contenido.hasNext()) {
                TextSelection item = (TextSelection) contenido.nextSelection();
                item.replaceWith(Jsoup.parse(certificado.getContenido()).text());
            }
            
            firma = new TextNavigation("@firma", odt);
            while (firma.hasNext()) {
                TextSelection item = (TextSelection) firma.nextSelection();
                if(certificado.getRemitente().getFirma() != null){
                    item.replaceWith(certificado.getRemitente().getFirma());
                } else {
                    item.replaceWith(" ");
                }
            }

            remitente = new TextNavigation("@remitente", odt);
            while (remitente.hasNext()) {
                TextSelection item = (TextSelection) remitente.nextSelection();
                if(certificado.getRemitente().getCargo() != null){
                    item.replaceWith(certificado.getRemitente().getNombres() + " " + certificado.getRemitente().getApelidos());
                } else {
                    item.replaceWith(certificado.getRemitente().getNombres() + " " + certificado.getRemitente().getApelidos());
                }
            }

            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Certificados" + File.separatorChar + "certificado" + certificado.getId().toString() + ".odt");
            odt.close();

            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            //IContext context =  report.createContext();
            //context.put("name", "world");
            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Certificados" + File.separatorChar + "certificado" + certificado.getId().toString() + ".odt"));
            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Certificados" + File.separatorChar + "certificado" + certificado.getId().toString() + ".pdf"));
            converter.convert(in, out, options);
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Certificados" + File.separatorChar + "certificado" + certificado.getId() + ".pdf";
            SessionUtils.getSession().setAttribute("filePathCertificado", this.filePath);
            RequestContext.getCurrentInstance().execute("PF('denVisorCertificadoFinal').show()");
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Certificado", e.getMessage()));
            Logger.getLogger(CertificadoViewBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void loadDocument() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.filePath = SessionUtils.getSession().getAttribute("filePathCertificado").toString();
            if (!filePath.isEmpty()) {
                File tempFile = new File(filePath);
                if (tempFile.exists()) {
                    this.content = new DefaultStreamedContent(new FileInputStream(tempFile), new MimetypesFileTypeMap().getContentType(tempFile));
                }
            }
        } catch (FileNotFoundException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Certificado", e.getMessage()));
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
