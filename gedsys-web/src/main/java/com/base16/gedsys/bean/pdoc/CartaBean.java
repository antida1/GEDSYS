/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean.pdoc;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.bean.ConsecutivoBean;
import com.base16.gedsys.entities.Acta;
import com.base16.gedsys.entities.Actaasistente;
import com.base16.gedsys.entities.Actaausente;
import com.base16.gedsys.entities.Actainvitado;
import com.base16.gedsys.entities.Carta;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.ActaJpaController;
import com.base16.gedsys.model.ActaasistenteJpaController;
import com.base16.gedsys.model.ActaausenteJpaController;
import com.base16.gedsys.model.ActainvitadoJpaController;
import com.base16.gedsys.model.CartaJpaController;
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
import java.util.ArrayList;
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
@Named(value = "cartaBean")
@ViewScoped
public class CartaBean extends BaseBean implements Serializable {

    /**
     * Creates a new instance of CartaBean
     */
    
    private static final long SerialVersionUID = 1L;
    private Carta carta = new Carta();
    private List<Carta> cartas;
    private String accion;
    
    private StreamedContent content;
    private String filePath = "";
    
    public CartaBean() {
        this.accion="Crear";
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
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
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "GEDSYS", "Documento creado exitosamente!"));
                    break;
                case "editar":
                    editar();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "GEDSYS", "Documento modificado exitosamente!"));
                    break;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(ActaBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    private void crear() throws Exception {
        CartaJpaController cJpa;
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        cJpa = new CartaJpaController(emf);
        
        this.carta.setFechaCreacion(new Date());
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.carta.setCreadoPor(usuario);
        this.carta.setModificadoPor(usuario);
        this.carta.setFechaModificacion(new Date());
        this.carta.setEstado("1");
        cJpa.create(this.carta);
    }

    private void editar() throws Exception {
        CartaJpaController cJpa;
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        cJpa = new CartaJpaController(emf);
        this.carta.setFechaCreacion(new Date());
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.carta.setModificadoPor(usuario);
        this.carta.setEstado("2");
        cJpa.edit(this.carta);
    }

    public void firmar() {
        //TODO: Recuperar consecutivo de documento.
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.carta.setModificadoPor(usuario);
        this.carta.setFechaFirma(new Date());
        this.carta.setEstado("3");
    }

    public void previsualizar() {
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
                item.replaceWith(DateTimeUtils.getFormattedTime(this.carta.getFecha(), "dd-mm-yyyy"));
            }

            tratamiento = new TextNavigation("@tratamiento", odt);
            while (tratamiento.hasNext()) {
                TextSelection item = (TextSelection) tratamiento.nextSelection();
                item.replaceWith(this.carta.getTratamiento());
            }

            destinatario = new TextNavigation("@destinatario", odt);
            while (destinatario.hasNext()) {
                TextSelection item = (TextSelection) destinatario.nextSelection();
                item.replaceWith(this.carta.getDestinatario());
            }
            
            cargo = new TextNavigation("@cargo", odt);
            while (cargo.hasNext()) {
                TextSelection item = (TextSelection) cargo.nextSelection();
                item.replaceWith(this.carta.getCargo());
            }

            asunto = new TextNavigation("@asunto", odt);
            while (asunto.hasNext()) {
                TextSelection item = (TextSelection) asunto.nextSelection();
                item.replaceWith(this.carta.getAsunto());
            }

            contenido = new TextNavigation("@contenido", odt);
            while (contenido.hasNext()) {
                TextSelection item = (TextSelection) contenido.nextSelection();
                item.replaceWith(this.carta.getContenido());
            }

            despedida = new TextNavigation("@despedida", odt);
            while (despedida.hasNext()) {
                TextSelection item = (TextSelection) despedida.nextSelection();
                item.replaceWith(this.carta.getContenido());
            }

            remitente = new TextNavigation("@remitente", odt);
            while (remitente.hasNext()) {
                TextSelection item = (TextSelection) remitente.nextSelection();
                item.replaceWith(this.carta.getRemitente().getNombres() + " " + this.carta.getRemitente().getApelidos() + " " + this.carta.getRemitente().getCargo());
            }

            odt.save(this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "carta" + this.carta.getId().toString() + ".odt");
            odt.close();
            
            //InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".odt"));
            //IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            
            //IContext context =  report.createContext();
            //context.put("name", "world");
            Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
            IConverter converter = ConverterRegistry.getRegistry().getConverter(options);
            
            InputStream in = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "carta" + this.carta.getId().toString() + ".odt"));
            OutputStream out = new FileOutputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "carta" + this.carta.getId().toString() + ".pdf"));
            converter.convert(in, out, options);
            
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(ActaBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void loadDocument(){
        try {
            this.filePath =  this.getDocumenstSavePath() + File.separatorChar + "Cartas" + File.separatorChar + "carta" + this.carta.getId().toString() + ".pdf";
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

    public List<Carta> getActasByResponsable() {
        CartaJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CartaJpaController(emf);
            cartas = cJpa.findCartaEntities();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        return cartas;
    }
    
}
