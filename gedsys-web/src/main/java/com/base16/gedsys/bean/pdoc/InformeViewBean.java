/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean.pdoc;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.entities.Certificado;
import com.base16.gedsys.entities.Constancia;
import com.base16.gedsys.entities.Informe;
import com.base16.gedsys.entities.Informecc;
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
public class InformeViewBean extends BaseBean implements Serializable {

    private StreamedContent content;
    private String filePath = "";

    @PostConstruct
    public void init() {

    }

    public void showDocument(Informe informe) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "informeb.odt");
            TextNavigation searchFecha;
            TextNavigation consecutivo;
            TextNavigation cargo;
            TextNavigation objetivo;
            TextNavigation conclusiones;
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
                item.replaceWith(DateTimeUtils.getFormattedTime(informe.getFecha(), "EEEEE d")+" de "
                + DateTimeUtils.getFormattedTime(informe.getFecha(), "MMMM") + " de "
                +DateTimeUtils.getFormattedTime(informe.getFecha(), "yyyy"));
            }

            consecutivo = new TextNavigation("@consecutivo", odt);
           while (consecutivo.hasNext()) {
                TextSelection item = (TextSelection) consecutivo.nextSelection();
                if(informe.getConsecutivo() == null || informe.getConsecutivo() == ""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(informe.getConsecutivo());
                }
            }

            cargo = new TextNavigation("@cargo", odt);
            while (cargo.hasNext()) {
                TextSelection item = (TextSelection) cargo.nextSelection();
                if(informe.getRemitente().getCargo() != null){
                    item.replaceWith(informe.getRemitente().getCargo().getNombre());
                }else{
                    item.replaceWith(" ");
                }
            }

            objetivo = new TextNavigation("@objetivo", odt);
            while (objetivo.hasNext()) {
                TextSelection item = (TextSelection) objetivo.nextSelection();
                item.replaceWith(Jsoup.parse(informe.getObjetivo()).text());
            }
            
            conclusiones = new TextNavigation("@conclusiones", odt);
            while (conclusiones.hasNext()) {
                TextSelection item = (TextSelection) conclusiones.nextSelection();
                item.replaceWith(Jsoup.parse(informe.getConclusiones()).text());
            }
            
            firma = new TextNavigation("@firma", odt);
            while (firma.hasNext()) {
                TextSelection item = (TextSelection) firma.nextSelection();
                if(informe.getRemitente().getFirma() != null){
                    File f = new File(this.getDocumenstSavePath() + File.separatorChar + "firmas" + File.separatorChar +informe.getRemitente().getFirma());
                    URI u = f.toURI();
                    item.replaceWith(u);
                } else {
                    item.replaceWith(" ");
                }
            }

            remitente = new TextNavigation("@remitente", odt);
            while (remitente.hasNext()) {
                TextSelection item = (TextSelection) remitente.nextSelection();               
                item.replaceWith(informe.getRemitente().getNombres() + " " + informe.getRemitente().getApelidos());
            } 
            
            anexos = new TextNavigation("@anexos", odt);
            while (anexos.hasNext()) {
                TextSelection item = (TextSelection) anexos.nextSelection();
                item.replaceWith(informe.getAnexos());
            }
            
            copia = new TextNavigation("@copia", odt);
            while (copia.hasNext()) {
                TextSelection item = (TextSelection) copia.nextSelection();
                String copias = "";
                for(Informecc informecc : informe.getInformeccList()){
                    if(informecc != null){
                        if(!"".equals(informecc.getConCopiaA().getCargo().getNombre()) || informecc.getConCopiaA().getCargo().getNombre() != null){
                            copias += informecc.getConCopiaA().getNombres() + "" + informecc.getConCopiaA().getApelidos() + ", " + informecc.getConCopiaA().getCargo().getNombre() + "\n";
                        }else{
                            copias += informecc.getConCopiaA().getNombres() + "" + informecc.getConCopiaA().getApelidos() + "\n";
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
                item.replaceWith(informe.getCreadoPor().getApelidos()+ " " + informe.getCreadoPor().getNombres());
            }
            
            elaboro = new TextNavigation("@elaboro", odt);
            while (elaboro.hasNext()) {
                TextSelection item = (TextSelection) elaboro.nextSelection();
                item.replaceWith(informe.getCreadoPor().getApelidos()+ " " + informe.getCreadoPor().getNombres());
            }
           

            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Informes" + File.separatorChar + "informe" + informe.getId().toString() + ".odt");
            odt.close();

            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            //IContext context =  report.createContext();
            //context.put("name", "world");
            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Informes" + File.separatorChar + "informe" + informe.getId().toString() + ".odt"));
            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Informes" + File.separatorChar + "informe" + informe.getId().toString() + ".pdf"));
            converter.convert(in, out, options);
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Informes" + File.separatorChar + "informe" + informe.getId() + ".pdf";
            SessionUtils.getSession().setAttribute("filePathInforme", this.filePath);
            RequestContext.getCurrentInstance().execute("PF('denVisorInforme').show()");
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe", e.getMessage()));
            Logger.getLogger(InformeViewBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void showDocumentFinal(Informe informe) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "informe.odt");
            TextNavigation searchFecha;
            TextNavigation consecutivo;
            TextNavigation cargo;
            TextNavigation objetivo;
            TextNavigation conclusiones;
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
                item.replaceWith(DateTimeUtils.getFormattedTime(informe.getFecha(), "EEEEE d")+" de "
                + DateTimeUtils.getFormattedTime(informe.getFecha(), "MMMM") + " de "
                +DateTimeUtils.getFormattedTime(informe.getFecha(), "yyyy"));
            }

            consecutivo = new TextNavigation("@consecutivo", odt);
           while (consecutivo.hasNext()) {
                TextSelection item = (TextSelection) consecutivo.nextSelection();
                if(informe.getConsecutivo() == null || informe.getConsecutivo() == ""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(informe.getConsecutivo());
                }
            }

            cargo = new TextNavigation("@cargo", odt);
            while (cargo.hasNext()) {
                TextSelection item = (TextSelection) cargo.nextSelection();
                if(informe.getRemitente().getCargo() != null){
                    item.replaceWith(informe.getRemitente().getCargo().getNombre());
                }else{
                    item.replaceWith(" ");
                }
            }

            objetivo = new TextNavigation("@objetivo", odt);
            while (objetivo.hasNext()) {
                TextSelection item = (TextSelection) objetivo.nextSelection();
                item.replaceWith(Jsoup.parse(informe.getObjetivo()).text());
            }
            
            conclusiones = new TextNavigation("@conclusiones", odt);
            while (conclusiones.hasNext()) {
                TextSelection item = (TextSelection) conclusiones.nextSelection();
                item.replaceWith(Jsoup.parse(informe.getConclusiones()).text());
            }
            
            firma = new TextNavigation("@firma", odt);
            while (firma.hasNext()) {
                TextSelection item = (TextSelection) firma.nextSelection();
                if(informe.getRemitente().getFirma() != null){
                    File f = new File(this.getDocumenstSavePath() + File.separatorChar + "firmas" + File.separatorChar +informe.getRemitente().getFirma());
                    URI u = f.toURI();
                    item.replaceWith(u);
                } else {
                    item.replaceWith(" ");
                }
            }

             remitente = new TextNavigation("@remitente", odt);
            while (remitente.hasNext()) {
                TextSelection item = (TextSelection) remitente.nextSelection();                
                item.replaceWith(informe.getRemitente().getNombres() + " " + informe.getRemitente().getApelidos());
            }           
            
            anexos = new TextNavigation("@anexos", odt);
            while (anexos.hasNext()) {
                TextSelection item = (TextSelection) anexos.nextSelection();
                item.replaceWith(informe.getAnexos());
            }
            
            copia = new TextNavigation("@copia", odt);
            while (copia.hasNext()) {
                TextSelection item = (TextSelection) copia.nextSelection();
                String copias = "";
                for(Informecc informecc : informe.getInformeccList()){
                    if(informecc != null){
                        if(!"".equals(informecc.getConCopiaA().getCargo().getNombre()) || informecc.getConCopiaA().getCargo().getNombre() != null){
                            copias += informecc.getConCopiaA().getNombres() + "" + informecc.getConCopiaA().getApelidos() + ", " + informecc.getConCopiaA().getCargo().getNombre() + "\n";
                        }else{
                            copias += informecc.getConCopiaA().getNombres() + "" + informecc.getConCopiaA().getApelidos() + "\n";
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
                item.replaceWith(informe.getCreadoPor().getApelidos()+ " " + informe.getCreadoPor().getNombres());
            }
            
            elaboro = new TextNavigation("@elaboro", odt);
            while (elaboro.hasNext()) {
                TextSelection item = (TextSelection) elaboro.nextSelection();
                item.replaceWith(informe.getCreadoPor().getApelidos()+ " " + informe.getCreadoPor().getNombres());
            }

            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Informes" + File.separatorChar + "informe" + informe.getId().toString() + ".odt");
            odt.close();

            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            //IContext context =  report.createContext();
            //context.put("name", "world");
            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Informes" + File.separatorChar + "informe" + informe.getId().toString() + ".odt"));
            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Informes" + File.separatorChar + "informe" + informe.getId().toString() + ".pdf"));
            converter.convert(in, out, options);
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Informes" + File.separatorChar + "informe" + informe.getId() + ".pdf";
            SessionUtils.getSession().setAttribute("filePathInforme", this.filePath);
            RequestContext.getCurrentInstance().execute("PF('denVisorInformeFinal').show()");
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe", e.getMessage()));
            Logger.getLogger(InformeViewBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void showDocumentFinalImprimir(Informe informe) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "informe.odt");
            TextNavigation searchFecha;
            TextNavigation consecutivo;
            TextNavigation cargo;
            TextNavigation objetivo;
            TextNavigation conclusiones;
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
                item.replaceWith(DateTimeUtils.getFormattedTime(informe.getFecha(), "EEEEE d")+" de "
                + DateTimeUtils.getFormattedTime(informe.getFecha(), "MMMM") + " de "
                +DateTimeUtils.getFormattedTime(informe.getFecha(), "yyyy"));
            }

            consecutivo = new TextNavigation("@consecutivo", odt);
           while (consecutivo.hasNext()) {
                TextSelection item = (TextSelection) consecutivo.nextSelection();
                if(informe.getConsecutivo() == null || informe.getConsecutivo() == ""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(informe.getConsecutivo());
                }
            }

            cargo = new TextNavigation("@cargo", odt);
            while (cargo.hasNext()) {
                TextSelection item = (TextSelection) cargo.nextSelection();
                if(informe.getRemitente().getCargo() != null){
                    item.replaceWith(informe.getRemitente().getCargo().getNombre());
                }else{
                    item.replaceWith(" ");
                }
            }

            objetivo = new TextNavigation("@objetivo", odt);
            while (objetivo.hasNext()) {
                TextSelection item = (TextSelection) objetivo.nextSelection();
                item.replaceWith(Jsoup.parse(informe.getObjetivo()).text());
            }
            
            conclusiones = new TextNavigation("@conclusiones", odt);
            while (conclusiones.hasNext()) {
                TextSelection item = (TextSelection) conclusiones.nextSelection();
                item.replaceWith(Jsoup.parse(informe.getConclusiones()).text());
            }
            
            firma = new TextNavigation("@firma", odt);
            while (firma.hasNext()) {
                TextSelection item = (TextSelection) firma.nextSelection();               
                item.replaceWith(" ");
               
            }

             remitente = new TextNavigation("@remitente", odt);
            while (remitente.hasNext()) {
                TextSelection item = (TextSelection) remitente.nextSelection();                
                item.replaceWith(informe.getRemitente().getNombres() + " " + informe.getRemitente().getApelidos());
            }
             
            anexos = new TextNavigation("@anexos", odt);
            while (anexos.hasNext()) {
                TextSelection item = (TextSelection) anexos.nextSelection();
                item.replaceWith(informe.getAnexos());
            }
            
           copia = new TextNavigation("@copia", odt);
            while (copia.hasNext()) {
                TextSelection item = (TextSelection) copia.nextSelection();
                String copias = "";
                for(Informecc informecc : informe.getInformeccList()){
                    if(informecc != null){
                        if(!"".equals(informecc.getConCopiaA().getCargo().getNombre()) || informecc.getConCopiaA().getCargo().getNombre() != null){
                            copias += informecc.getConCopiaA().getNombres() + "" + informecc.getConCopiaA().getApelidos() + ", " + informecc.getConCopiaA().getCargo().getNombre() + "\n";
                        }else{
                            copias += informecc.getConCopiaA().getNombres() + "" + informecc.getConCopiaA().getApelidos() + "\n";
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
                item.replaceWith(informe.getCreadoPor().getApelidos()+ " " + informe.getCreadoPor().getNombres());
            }
            
            elaboro = new TextNavigation("@elaboro", odt);
            while (elaboro.hasNext()) {
                TextSelection item = (TextSelection) elaboro.nextSelection();
                item.replaceWith(informe.getCreadoPor().getApelidos()+ " " + informe.getCreadoPor().getNombres());
            }

            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Informes" + File.separatorChar + "informe" + informe.getId().toString() + ".odt");
            odt.close();

            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            //IContext context =  report.createContext();
            //context.put("name", "world");
            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Informes" + File.separatorChar + "informe" + informe.getId().toString() + ".odt"));
            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Informes" + File.separatorChar + "informe" + informe.getId().toString() + ".pdf"));
            converter.convert(in, out, options);
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Informes" + File.separatorChar + "informe" + informe.getId() + ".pdf";
            SessionUtils.getSession().setAttribute("filePathInforme", this.filePath);
            RequestContext.getCurrentInstance().execute("PF('denVisorInformeFinal').show()");
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informe", e.getMessage()));
            Logger.getLogger(InformeViewBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void loadDocument() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.filePath = SessionUtils.getSession().getAttribute("filePathInforme").toString();
            if (!filePath.isEmpty()) {
                File tempFile = new File(filePath);
                if (tempFile.exists()) {
                    this.content = new DefaultStreamedContent(new FileInputStream(tempFile), new MimetypesFileTypeMap().getContentType(tempFile));
                }
            }
        } catch (FileNotFoundException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe", e.getMessage()));
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
