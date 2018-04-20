/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean.pdoc;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.entities.Acta;
import com.base16.gedsys.entities.Actaasistente;
import com.base16.gedsys.entities.Actaausente;
import com.base16.gedsys.entities.Actainvitado;
import com.base16.gedsys.entities.Carta;
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
 * @author rober
 */
@ManagedBean
@RequestScoped
public class CartaViewBean extends BaseBean implements Serializable {

    private StreamedContent content;
    private String filePath = "";

    @PostConstruct
    public void init() {

    }

    public void showDocument(Carta carta) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "cartab.odt");
            TextNavigation searchConsecutivo;
            TextNavigation searchFecha;
            TextNavigation tratamiento;
            TextNavigation destinatario;
            TextNavigation cargo;
            TextNavigation empresa;
            TextNavigation direccion;
            TextNavigation ciudad;
            TextNavigation asunto;
            TextNavigation contenido;
            TextNavigation despedida;
            TextNavigation firma;
            TextNavigation remitente;

            searchConsecutivo = new TextNavigation("@consecutivo", odt);
            while (searchConsecutivo.hasNext()) {
                TextSelection item = (TextSelection) searchConsecutivo.nextSelection();
                 if(carta.getConsecutivo() == null || carta.getConsecutivo() == ""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(carta.getConsecutivo());
                }
                
            }

            searchFecha = new TextNavigation("@fecha", odt);
            while (searchFecha.hasNext()) {
                DateFormat df = new SimpleDateFormat();
                TextSelection item = (TextSelection) searchFecha.nextSelection();
                item.replaceWith(DateTimeUtils.getFormattedTime(carta.getFecha(), "EEEEE d")+" de "
                + DateTimeUtils.getFormattedTime(carta.getFecha(), "MMMM") + " de "
                +DateTimeUtils.getFormattedTime(carta.getFecha(), "yyyy"));
            }

            tratamiento = new TextNavigation("@tratamiento", odt);
            while (tratamiento.hasNext()) {
                TextSelection item = (TextSelection) tratamiento.nextSelection();
                item.replaceWith(carta.getTratamiento());
            }

            destinatario = new TextNavigation("@destinatario", odt);
            while (destinatario.hasNext()) {
                TextSelection item = (TextSelection) destinatario.nextSelection();
                item.replaceWith(carta.getDestinatario());
            }

            cargo = new TextNavigation("@cargo", odt);
            while (cargo.hasNext()) {
                TextSelection item = (TextSelection) cargo.nextSelection();
                if(carta.getCargo() == null || carta.getCargo() == ""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(carta.getCargo());
                }                 
            }

            empresa = new TextNavigation("@empresa", odt);
            while (empresa.hasNext()) {
                TextSelection item = (TextSelection) empresa.nextSelection();
                if(carta.getEmpresa() == null || carta.getEmpresa() == ""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(carta.getEmpresa());
                }                
            }

            direccion = new TextNavigation("@direccion", odt);
            while (direccion.hasNext()) {
                TextSelection item = (TextSelection) direccion.nextSelection();
                if(carta.getDireccion() == null || carta.getDireccion() == ""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(carta.getDireccion());
                }               
            }

            ciudad = new TextNavigation("@ciudad", odt);
            while (ciudad.hasNext()) {
                TextSelection item = (TextSelection) ciudad.nextSelection();
                if(carta.getCiudad() == null){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(carta.getCiudad().getNombre());
                }  
            }            

            asunto = new TextNavigation("@asunto", odt);
            while (asunto.hasNext()) {
                TextSelection item = (TextSelection) asunto.nextSelection();
                item.replaceWith(carta.getAsunto());
            }

            contenido = new TextNavigation("@contenido", odt);
            while (contenido.hasNext()) {
                TextSelection item = (TextSelection) contenido.nextSelection();
                item.replaceWith(Jsoup.parse(carta.getContenido()).text());
            }

            despedida = new TextNavigation("@despedida", odt);
            while (despedida.hasNext()) {
                TextSelection item = (TextSelection) despedida.nextSelection();
                item.replaceWith(Jsoup.parse(carta.getDespedida()).text());
            }
            
            firma = new TextNavigation("@firma", odt);
            while (firma.hasNext()) {
                TextSelection item = (TextSelection) firma.nextSelection();
                item.replaceWith(" ");               
            }

            remitente = new TextNavigation("@remitente", odt);
            while (remitente.hasNext()) {
                TextSelection item = (TextSelection) remitente.nextSelection();
                if(carta.getRemitente().getCargo() != null){
                    item.replaceWith(carta.getRemitente().getNombres() + " " + carta.getRemitente().getApelidos() + " " + carta.getRemitente().getCargo().getNombre());
                } else {
                    item.replaceWith(carta.getRemitente().getNombres() + " " + carta.getRemitente().getApelidos());
                }
            }

            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "carta" + carta.getId().toString() + ".odt");
            odt.close();

            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            //IContext context =  report.createContext();
            //context.put("name", "world");
            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "carta" + carta.getId().toString() + ".odt"));
            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "carta" + carta.getId().toString() + ".pdf"));
            converter.convert(in, out, options);
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "carta" + carta.getId() + ".pdf";
            SessionUtils.getSession().setAttribute("filePathCarta", this.filePath);
            RequestContext.getCurrentInstance().execute("PF('denVisorCarta').show()");
        } catch (Exception e) {            
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Carta", "Debé guardar la carta para poder visualizarla"));
            Logger.getLogger(CartaViewBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void showDocumentFinal(Carta carta) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "carta.odt");
            TextNavigation searchConsecutivo;
            TextNavigation searchFecha;
            TextNavigation tratamiento;
            TextNavigation destinatario;
            TextNavigation cargo;
            TextNavigation empresa;
            TextNavigation direccion;
            TextNavigation ciudad;
            TextNavigation asunto;
            TextNavigation contenido;
            TextNavigation despedida;
            TextNavigation firma;
            TextNavigation remitente;

            searchConsecutivo = new TextNavigation("@consecutivo", odt);
            while (searchConsecutivo.hasNext()) {
                TextSelection item = (TextSelection) searchConsecutivo.nextSelection();
                 if(carta.getConsecutivo() == null || carta.getConsecutivo() == ""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(carta.getConsecutivo());
                }
                
            }

            searchFecha = new TextNavigation("@fecha", odt);
            while (searchFecha.hasNext()) {
                DateFormat df = new SimpleDateFormat();
                TextSelection item = (TextSelection) searchFecha.nextSelection();
                item.replaceWith(DateTimeUtils.getFormattedTime(carta.getFecha(), "EEEEE d")+" de "
                + DateTimeUtils.getFormattedTime(carta.getFecha(), "MMMM") + " de "
                +DateTimeUtils.getFormattedTime(carta.getFecha(), "yyyy"));
            }

            tratamiento = new TextNavigation("@tratamiento", odt);
            while (tratamiento.hasNext()) {
                TextSelection item = (TextSelection) tratamiento.nextSelection();
                item.replaceWith(carta.getTratamiento());
            }

            destinatario = new TextNavigation("@destinatario", odt);
            while (destinatario.hasNext()) {
                TextSelection item = (TextSelection) destinatario.nextSelection();
                item.replaceWith(carta.getDestinatario());
            }

            cargo = new TextNavigation("@cargo", odt);
            while (cargo.hasNext()) {
                TextSelection item = (TextSelection) cargo.nextSelection();
                if(carta.getCargo() == null || carta.getCargo() == ""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(carta.getCargo());
                }                 
            }

            empresa = new TextNavigation("@empresa", odt);
            while (empresa.hasNext()) {
                TextSelection item = (TextSelection) empresa.nextSelection();
                if(carta.getEmpresa() == null || carta.getEmpresa() == ""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(carta.getEmpresa());
                }                
            }

            direccion = new TextNavigation("@direccion", odt);
            while (direccion.hasNext()) {
                TextSelection item = (TextSelection) direccion.nextSelection();
                if(carta.getDireccion() == null || carta.getDireccion() == ""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(carta.getDireccion());
                }               
            }

            ciudad = new TextNavigation("@ciudad", odt);
            while (ciudad.hasNext()) {
                TextSelection item = (TextSelection) ciudad.nextSelection();
                if(carta.getCiudad() == null){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(carta.getCiudad().getNombre());
                }  
            }            

            asunto = new TextNavigation("@asunto", odt);
            while (asunto.hasNext()) {
                TextSelection item = (TextSelection) asunto.nextSelection();
                item.replaceWith(carta.getAsunto());
            }

            contenido = new TextNavigation("@contenido", odt);
            while (contenido.hasNext()) {
                TextSelection item = (TextSelection) contenido.nextSelection();
                item.replaceWith(Jsoup.parse(carta.getContenido()).text());
            }

            despedida = new TextNavigation("@despedida", odt);
            while (despedida.hasNext()) {
                TextSelection item = (TextSelection) despedida.nextSelection();
                item.replaceWith(Jsoup.parse(carta.getDespedida()).text());
            }
            
            firma = new TextNavigation("@firma", odt);
            while (firma.hasNext()) {
                TextSelection item = (TextSelection) firma.nextSelection();
                if(carta.getRemitente().getFirma() != null){
                    File f = new File(this.getDocumenstSavePath() + File.separatorChar + "firmas" + File.separatorChar +carta.getRemitente().getFirma());
                    URI u = f.toURI();
                    item.replaceWith(u);
                } else {
                    item.replaceWith(" ");
                }
            }

            remitente = new TextNavigation("@remitente", odt);
            while (remitente.hasNext()) {
                TextSelection item = (TextSelection) remitente.nextSelection();
                if(carta.getRemitente().getCargo() != null){
                    item.replaceWith(carta.getRemitente().getNombres() + " " + carta.getRemitente().getApelidos() + "\n " + carta.getRemitente().getCargo().getNombre());
                } else {
                    item.replaceWith(carta.getRemitente().getNombres() + " " + carta.getRemitente().getApelidos());
                }
            }

            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "carta" + carta.getId().toString() + ".odt");
            odt.close();

            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            //IContext context =  report.createContext();
            //context.put("name", "world");
            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "carta" + carta.getId().toString() + ".odt"));
            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "carta" + carta.getId().toString() + ".pdf"));
            converter.convert(in, out, options);
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "carta" + carta.getId() + ".pdf";
            SessionUtils.getSession().setAttribute("filePathCarta", this.filePath);
            RequestContext.getCurrentInstance().execute("PF('denVisorCartaFinal').show()");
        } catch (Exception e) {            
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Carta", "Debé guardar la carta para poder visualizarla"));
            Logger.getLogger(CartaViewBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void showDocumentFinalImprimir(Carta carta) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "carta.odt");
            TextNavigation searchConsecutivo;
            TextNavigation searchFecha;
            TextNavigation tratamiento;
            TextNavigation destinatario;
            TextNavigation cargo;
            TextNavigation empresa;
            TextNavigation direccion;
            TextNavigation ciudad;
            TextNavigation asunto;
            TextNavigation contenido;
            TextNavigation despedida;
            TextNavigation firma;
            TextNavigation remitente;

            searchConsecutivo = new TextNavigation("@consecutivo", odt);
            while (searchConsecutivo.hasNext()) {
                TextSelection item = (TextSelection) searchConsecutivo.nextSelection();
                 if(carta.getConsecutivo() == null || carta.getConsecutivo() == ""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(carta.getConsecutivo());
                }
                
            }

            searchFecha = new TextNavigation("@fecha", odt);
            while (searchFecha.hasNext()) {
                DateFormat df = new SimpleDateFormat();
                TextSelection item = (TextSelection) searchFecha.nextSelection();
                item.replaceWith(DateTimeUtils.getFormattedTime(carta.getFecha(), "EEEEE d")+" de "
                + DateTimeUtils.getFormattedTime(carta.getFecha(), "MMMM") + " de "
                +DateTimeUtils.getFormattedTime(carta.getFecha(), "yyyy"));
            }

            tratamiento = new TextNavigation("@tratamiento", odt);
            while (tratamiento.hasNext()) {
                TextSelection item = (TextSelection) tratamiento.nextSelection();
                item.replaceWith(carta.getTratamiento());
            }

            destinatario = new TextNavigation("@destinatario", odt);
            while (destinatario.hasNext()) {
                TextSelection item = (TextSelection) destinatario.nextSelection();
                item.replaceWith(carta.getDestinatario());
            }

            cargo = new TextNavigation("@cargo", odt);
            while (cargo.hasNext()) {
                TextSelection item = (TextSelection) cargo.nextSelection();
                if(carta.getCargo() == null || carta.getCargo() == ""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(carta.getCargo());
                }                 
            }

            empresa = new TextNavigation("@empresa", odt);
            while (empresa.hasNext()) {
                TextSelection item = (TextSelection) empresa.nextSelection();
                if(carta.getEmpresa() == null || carta.getEmpresa() == ""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(carta.getEmpresa());
                }                
            }

            direccion = new TextNavigation("@direccion", odt);
            while (direccion.hasNext()) {
                TextSelection item = (TextSelection) direccion.nextSelection();
                if(carta.getDireccion() == null || carta.getDireccion() == ""){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(carta.getDireccion());
                }               
            }

            ciudad = new TextNavigation("@ciudad", odt);
            while (ciudad.hasNext()) {
                TextSelection item = (TextSelection) ciudad.nextSelection();
                if(carta.getCiudad() == null){
                    item.replaceWith(" ");
                }else{
                    item.replaceWith(carta.getCiudad().getNombre());
                }  
            }            

            asunto = new TextNavigation("@asunto", odt);
            while (asunto.hasNext()) {
                TextSelection item = (TextSelection) asunto.nextSelection();
                item.replaceWith(carta.getAsunto());
            }

            contenido = new TextNavigation("@contenido", odt);
            while (contenido.hasNext()) {
                TextSelection item = (TextSelection) contenido.nextSelection();
                item.replaceWith(Jsoup.parse(carta.getContenido()).text());
            }

            despedida = new TextNavigation("@despedida", odt);
            while (despedida.hasNext()) {
                TextSelection item = (TextSelection) despedida.nextSelection();
                item.replaceWith(Jsoup.parse(carta.getDespedida()).text());
            }
            
            firma = new TextNavigation("@firma", odt);
            while (firma.hasNext()) {
                TextSelection item = (TextSelection) firma.nextSelection();
                item.replaceWith(" ");               
            }

            remitente = new TextNavigation("@remitente", odt);
            while (remitente.hasNext()) {
                TextSelection item = (TextSelection) remitente.nextSelection();
                if(carta.getRemitente().getCargo() != null){
                    item.replaceWith(carta.getRemitente().getNombres() + " " + carta.getRemitente().getApelidos() + "\n " + carta.getRemitente().getCargo().getNombre());
                } else {
                    item.replaceWith(carta.getRemitente().getNombres() + " " + carta.getRemitente().getApelidos());
                }
            }

            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "carta" + carta.getId().toString() + ".odt");
            odt.close();

            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            //IContext context =  report.createContext();
            //context.put("name", "world");
            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "carta" + carta.getId().toString() + ".odt"));
            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "carta" + carta.getId().toString() + ".pdf"));
            converter.convert(in, out, options);
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "carta" + carta.getId() + ".pdf";
            SessionUtils.getSession().setAttribute("filePathCarta", this.filePath);
            RequestContext.getCurrentInstance().execute("PF('denVisorCartaFinal').show()");
        } catch (Exception e) {            
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Carta", "Debé guardar la carta para poder visualizarla"));
            Logger.getLogger(CartaViewBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void loadDocument() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.filePath = SessionUtils.getSession().getAttribute("filePathCarta").toString();
            if (!filePath.isEmpty()) {
                File tempFile = new File(filePath);
                if (tempFile.exists()) {
                    this.content = new DefaultStreamedContent(new FileInputStream(tempFile), new MimetypesFileTypeMap().getContentType(tempFile));
                }
            }
        } catch (FileNotFoundException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Carta", e.getMessage()));
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
