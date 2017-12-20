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

    public void showDocument(Carta carta ) {
        try {

            TextDocument odt = (TextDocument) TextDocument.loadDocument(this.getDocumenstSavePath() + File.separatorChar + "Formatos" + File.separatorChar + "carta.odt");
            TextNavigation searchFecha;
            TextNavigation tratamiento;
            TextNavigation destinatario;
            TextNavigation cargo;
            TextNavigation asunto;
            TextNavigation contenido;
            TextNavigation despedida;
            TextNavigation remitente;

            searchFecha = new TextNavigation("@fecha", odt);
            while (searchFecha.hasNext()) {
                DateFormat df = new SimpleDateFormat();
                TextSelection item = (TextSelection) searchFecha.nextSelection();
                item.replaceWith(DateTimeUtils.getFormattedTime(carta.getFecha(), "dd-MM-yyyy"));
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
                item.replaceWith(carta.getCargo());
            }

            asunto = new TextNavigation("@asunto", odt);
            while (asunto.hasNext()) {
                TextSelection item = (TextSelection) asunto.nextSelection();
                item.replaceWith(carta.getAsunto());
            }

            contenido = new TextNavigation("@contenido", odt);
            while (contenido.hasNext()) {
                TextSelection item = (TextSelection) contenido.nextSelection();
                item.replaceWith(carta.getContenido());
            }

            despedida = new TextNavigation("@despedida", odt);
            while (despedida.hasNext()) {
                TextSelection item = (TextSelection) despedida.nextSelection();
                item.replaceWith(carta.getContenido());
            }

            remitente = new TextNavigation("@remitente", odt);
            while (remitente.hasNext()) {
                TextSelection item = (TextSelection) remitente.nextSelection();
                item.replaceWith(carta.getRemitente().getNombres() + " " + carta.getRemitente().getApelidos() + " " + carta.getRemitente().getCargo().getNombre());
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(CartaViewBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void loadDocument() {
        try {
            this.filePath = SessionUtils.getSession().getAttribute("filePathCarta").toString();
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
