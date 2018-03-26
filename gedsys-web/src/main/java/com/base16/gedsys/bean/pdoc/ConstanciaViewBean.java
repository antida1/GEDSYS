/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean.pdoc;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.entities.Certificado;
import com.base16.gedsys.entities.Constancia;
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
public class ConstanciaViewBean extends BaseBean implements Serializable {

    private StreamedContent content;
    private String filePath = "";

    @PostConstruct
    public void init() {

    }

    public void showDocument(Constancia constancia) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "constancia.odt");
            TextNavigation searchConsecutivo;
            TextNavigation searchFecha;            
            TextNavigation cargo;
            TextNavigation contenido;
            TextNavigation remitente;

            searchConsecutivo = new TextNavigation("@consecutivo", odt);
            while (searchConsecutivo.hasNext()) {
                TextSelection item = (TextSelection) searchConsecutivo.nextSelection();
                if(constancia.getConsecutivo() == null || constancia.getConsecutivo() == ""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(constancia.getConsecutivo());
                }
            }

            searchFecha = new TextNavigation("@fecha", odt);
            while (searchFecha.hasNext()) {
                DateFormat df = new SimpleDateFormat();
                TextSelection item = (TextSelection) searchFecha.nextSelection();
                item.replaceWith(DateTimeUtils.getFormattedTime(constancia.getFecha(), "EEEEE d")+" de "
                + DateTimeUtils.getFormattedTime(constancia.getFecha(), "MMMM") + " de "
                +DateTimeUtils.getFormattedTime(constancia.getFecha(), "yyyy"));
            }

            cargo = new TextNavigation("@cargo", odt);
            while (cargo.hasNext()) {
                TextSelection item = (TextSelection) cargo.nextSelection();
                if(constancia.getRemitente().getCargo() != null){
                    item.replaceWith(constancia.getRemitente().getCargo().getNombre());
                }else{
                    item.replaceWith(" ");
                }
            }            

            contenido = new TextNavigation("@contenido", odt);
            while (contenido.hasNext()) {
                TextSelection item = (TextSelection) contenido.nextSelection();
                item.replaceWith(Jsoup.parse(constancia.getContenido()).text());
            }

            remitente = new TextNavigation("@remitente", odt);
            while (remitente.hasNext()) {
                TextSelection item = (TextSelection) remitente.nextSelection();
                if(constancia.getRemitente().getCargo() != null){
                    item.replaceWith(constancia.getRemitente().getNombres() + " " + constancia.getRemitente().getApelidos());
                } else {
                    item.replaceWith(constancia.getRemitente().getNombres() + " " + constancia.getRemitente().getApelidos());
                }
            }

            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Constancias" + File.separatorChar + "constancia" + constancia.getId().toString() + ".odt");
            odt.close();

            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            //IContext context =  report.createContext();
            //context.put("name", "world");
            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Constancias" + File.separatorChar + "constancia" + constancia.getId().toString() + ".odt"));
            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Constancias" + File.separatorChar + "constancia" + constancia.getId().toString() + ".pdf"));
            converter.convert(in, out, options);
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Constancias" + File.separatorChar + "constancia" + constancia.getId() + ".pdf";
            SessionUtils.getSession().setAttribute("filePathConstancia", this.filePath);
            RequestContext.getCurrentInstance().execute("PF('denVisorConstancia').show()");
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Constancia", e.getMessage()));
            Logger.getLogger(ConstanciaViewBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void loadDocument() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.filePath = SessionUtils.getSession().getAttribute("filePathConstancia").toString();
            if (!filePath.isEmpty()) {
                File tempFile = new File(filePath);
                if (tempFile.exists()) {
                    this.content = new DefaultStreamedContent(new FileInputStream(tempFile), new MimetypesFileTypeMap().getContentType(tempFile));
                }
            }
        } catch (FileNotFoundException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Constancia", e.getMessage()));
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
