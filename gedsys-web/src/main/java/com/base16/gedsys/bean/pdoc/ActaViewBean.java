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
import com.base16.gedsys.model.ActaJpaController;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import org.apache.commons.lang.StringEscapeUtils;
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
public class ActaViewBean extends BaseBean implements Serializable {

    private StreamedContent content;
    private String filePath = "";

    @PostConstruct
    public void init() {

    }

    public void showDocument(Acta acta) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "actab.odt");

            TextNavigation searchConsecutivo;
            TextNavigation searchFecha;
            TextNavigation searchHoraInicio;
            TextNavigation searchHoraFinal;
            TextNavigation searchLugar;
            TextNavigation searchAsistentes;
            TextNavigation searchInvitados;
            TextNavigation searchAusentes;
            TextNavigation searchOrden;
            TextNavigation searchDesarrollo;
            TextNavigation searchConvocatoria;
            TextNavigation searchPresidente;
            TextNavigation searchSecretaria;

            searchConsecutivo = new TextNavigation("@consecutivo", odt);
            while (searchConsecutivo.hasNext()) {
                TextSelection item = (TextSelection) searchConsecutivo.nextSelection();
                if (acta.getConsecutivo() == null || acta.getConsecutivo()== "") {
                    item.replaceWith(" ");
                } else {
                    item.replaceWith(acta.getConsecutivo());
                }
            }

            searchFecha = new TextNavigation("@fecha", odt);
            while (searchFecha.hasNext()) {
                DateFormat df = new SimpleDateFormat();
                TextSelection item = (TextSelection) searchFecha.nextSelection();
                item.replaceWith(DateTimeUtils.getFormattedTime(acta.getFecha(), "dd-MM-yyyy"));
            }

            searchHoraInicio = new TextNavigation("@hora_inicio", odt);
            while (searchHoraInicio.hasNext()) {
                TextSelection item = (TextSelection) searchHoraInicio.nextSelection();
                item.replaceWith(DateTimeUtils.getFormattedTime(acta.getFecha(), "hh-mm a"));
            }

            searchHoraFinal = new TextNavigation("@hora_final", odt);
            while (searchHoraFinal.hasNext()) {
                TextSelection item = (TextSelection) searchHoraFinal.nextSelection();
                item.replaceWith(DateTimeUtils.getFormattedTime(acta.getFecha(), "hh-mm a"));
            }

            searchLugar = new TextNavigation("@lugar", odt);
            while (searchLugar.hasNext()) {
                TextSelection item = (TextSelection) searchLugar.nextSelection();
                item.replaceWith(acta.getLugar());
            }

            searchAsistentes = new TextNavigation("@asistentes", odt);
            while (searchAsistentes.hasNext()) {
                TextSelection item = (TextSelection) searchAsistentes.nextSelection();
                String sAsistentes = "";
                for (Actaasistente actaAsistente : acta.getActaasistenteList()) {
                    sAsistentes += actaAsistente.getAsistente().getNombres() + " " + actaAsistente.getAsistente().getApelidos() + "\n";
                }
                item.replaceWith(sAsistentes);
            }

            searchInvitados = new TextNavigation("@invitados", odt);
            while (searchInvitados.hasNext()) {
                TextSelection item = (TextSelection) searchInvitados.nextSelection();
                String sInvitados = "";
                for (Actainvitado actaInvitado : acta.getActainvitadoList()) {
                    sInvitados += actaInvitado.getInvitado().getNombres() + " " + actaInvitado.getInvitado().getApelidos() + "\n";
                }
                item.replaceWith(sInvitados);
            }

            searchAusentes = new TextNavigation("@ausentes", odt);
            while (searchAusentes.hasNext()) {
                TextSelection item = (TextSelection) searchAusentes.nextSelection();
                String sAusentes = "";
                for (Actaausente actaausente : acta.getActaausenteList()) {
                    sAusentes += actaausente.getAusente().getNombres() + " " + actaausente.getAusente().getApelidos() + "\n";
                }
                item.replaceWith(sAusentes);
            }

            searchOrden = new TextNavigation("@orden", odt);
            while (searchOrden.hasNext()) {
                TextSelection item = (TextSelection) searchOrden.nextSelection();
                item.replaceWith(Jsoup.parse(acta.getOrden()).text());
            }

            searchDesarrollo = new TextNavigation("@desarrollo", odt);
            while (searchDesarrollo.hasNext()) {
                TextSelection item = (TextSelection) searchDesarrollo.nextSelection();
                item.replaceWith(Jsoup.parse(acta.getDesarrollo()).text());
            }

            searchConvocatoria = new TextNavigation("@convocatoria", odt);
            while (searchConvocatoria.hasNext()) {
                TextSelection item = (TextSelection) searchConvocatoria.nextSelection();
                item.replaceWith(Jsoup.parse(acta.getConvocatoria()).text());
            }

            searchPresidente = new TextNavigation("@presidente", odt);
            while (searchPresidente.hasNext()) {
                TextSelection item = (TextSelection) searchPresidente.nextSelection();
                item.replaceWith(acta.getPresidente().getNombres() + " " + acta.getPresidente().getApelidos() + " ");
            }

            searchSecretaria = new TextNavigation("@secretaria", odt);
            while (searchSecretaria.hasNext()) {
                TextSelection item = (TextSelection) searchSecretaria.nextSelection();
                item.replaceWith(acta.getSecretaria().getNombres() + " " + acta.getSecretaria().getApelidos() + " ");
            }

            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + acta.getId() + ".odt");
            odt.close();

            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            //IContext context =  report.createContext();
            //context.put("name", "world");
            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + acta.getId() + ".odt"));
            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + acta.getId() + ".pdf"));
            converter.convert(in, out, options);
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + acta.getId() + ".pdf";
            SessionUtils.getSession().setAttribute("filePathActa", this.filePath);
            RequestContext.getCurrentInstance().execute("PF('denVisorActa').show()");
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acta", e.getMessage()));
            Logger.getLogger(ActaViewBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void showDocumentFinal(Acta acta) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "acta.odt");

            TextNavigation searchConsecutivo;
            TextNavigation searchFecha;
            TextNavigation searchHoraInicio;
            TextNavigation searchHoraFinal;
            TextNavigation searchLugar;
            TextNavigation searchAsistentes;
            TextNavigation searchInvitados;
            TextNavigation searchAusentes;
            TextNavigation searchOrden;
            TextNavigation searchDesarrollo;
            TextNavigation searchConvocatoria;
            TextNavigation searchPresidente;
            TextNavigation searchSecretaria;

            searchConsecutivo = new TextNavigation("@consecutivo", odt);
            while (searchConsecutivo.hasNext()) {
                TextSelection item = (TextSelection) searchConsecutivo.nextSelection();
                if (acta.getConsecutivo() == null || acta.getConsecutivo()== "") {
                    item.replaceWith(" ");
                } else {
                    item.replaceWith(acta.getConsecutivo());
                }
            }

            searchFecha = new TextNavigation("@fecha", odt);
            while (searchFecha.hasNext()) {
                DateFormat df = new SimpleDateFormat();
                TextSelection item = (TextSelection) searchFecha.nextSelection();
                item.replaceWith(DateTimeUtils.getFormattedTime(acta.getFecha(), "dd-MM-yyyy"));
            }

            searchHoraInicio = new TextNavigation("@hora_inicio", odt);
            while (searchHoraInicio.hasNext()) {
                TextSelection item = (TextSelection) searchHoraInicio.nextSelection();
                item.replaceWith(DateTimeUtils.getFormattedTime(acta.getFecha(), "hh-mm a"));
            }

            searchHoraFinal = new TextNavigation("@hora_final", odt);
            while (searchHoraFinal.hasNext()) {
                TextSelection item = (TextSelection) searchHoraFinal.nextSelection();
                item.replaceWith(DateTimeUtils.getFormattedTime(acta.getFecha(), "hh-mm a"));
            }

            searchLugar = new TextNavigation("@lugar", odt);
            while (searchLugar.hasNext()) {
                TextSelection item = (TextSelection) searchLugar.nextSelection();
                item.replaceWith(acta.getLugar());
            }

            searchAsistentes = new TextNavigation("@asistentes", odt);
            while (searchAsistentes.hasNext()) {
                TextSelection item = (TextSelection) searchAsistentes.nextSelection();
                String sAsistentes = "";
                for (Actaasistente actaAsistente : acta.getActaasistenteList()) {
                    sAsistentes += actaAsistente.getAsistente().getNombres() + " " + actaAsistente.getAsistente().getApelidos() + "\n";
                }
                item.replaceWith(sAsistentes);
            }

            searchInvitados = new TextNavigation("@invitados", odt);
            while (searchInvitados.hasNext()) {
                TextSelection item = (TextSelection) searchInvitados.nextSelection();
                String sInvitados = "";
                for (Actainvitado actaInvitado : acta.getActainvitadoList()) {
                    sInvitados += actaInvitado.getInvitado().getNombres() + " " + actaInvitado.getInvitado().getApelidos() + "\n";
                }
                item.replaceWith(sInvitados);
            }

            searchAusentes = new TextNavigation("@ausentes", odt);
            while (searchAusentes.hasNext()) {
                TextSelection item = (TextSelection) searchAusentes.nextSelection();
                String sAusentes = "";
                for (Actaausente actaausente : acta.getActaausenteList()) {
                    sAusentes += actaausente.getAusente().getNombres() + " " + actaausente.getAusente().getApelidos() + "\n";
                }
                item.replaceWith(sAusentes);
            }

            searchOrden = new TextNavigation("@orden", odt);
            while (searchOrden.hasNext()) {
                TextSelection item = (TextSelection) searchOrden.nextSelection();
                item.replaceWith(Jsoup.parse(acta.getOrden()).text());
            }

            searchDesarrollo = new TextNavigation("@desarrollo", odt);
            while (searchDesarrollo.hasNext()) {
                TextSelection item = (TextSelection) searchDesarrollo.nextSelection();
                item.replaceWith(Jsoup.parse(acta.getDesarrollo()).text());
            }

            searchConvocatoria = new TextNavigation("@convocatoria", odt);
            while (searchConvocatoria.hasNext()) {
                TextSelection item = (TextSelection) searchConvocatoria.nextSelection();
                item.replaceWith(Jsoup.parse(acta.getConvocatoria()).text());
            }

            searchPresidente = new TextNavigation("@presidente", odt);
            while (searchPresidente.hasNext()) {
                TextSelection item = (TextSelection) searchPresidente.nextSelection();
                item.replaceWith(acta.getPresidente().getNombres() + " " + acta.getPresidente().getApelidos() + " ");
            }

            searchSecretaria = new TextNavigation("@secretaria", odt);
            while (searchSecretaria.hasNext()) {
                TextSelection item = (TextSelection) searchSecretaria.nextSelection();
                item.replaceWith(acta.getSecretaria().getNombres() + " " + acta.getSecretaria().getApelidos() + " ");
            }

            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + acta.getId() + ".odt");
            odt.close();

            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            //IContext context =  report.createContext();
            //context.put("name", "world");
            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + acta.getId() + ".odt"));
            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + acta.getId() + ".pdf"));
            converter.convert(in, out, options);
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + acta.getId() + ".pdf";
            SessionUtils.getSession().setAttribute("filePathActa", this.filePath);
            RequestContext.getCurrentInstance().execute("PF('denVisorActaFinal').show()");
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acta", e.getMessage()));
            Logger.getLogger(ActaViewBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void loadDocument() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.filePath = SessionUtils.getSession().getAttribute("filePathActa").toString();
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
