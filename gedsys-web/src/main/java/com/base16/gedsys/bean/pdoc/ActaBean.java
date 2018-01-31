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
import com.base16.gedsys.entities.Cargo;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.ActaJpaController;
import com.base16.gedsys.model.ActaasistenteJpaController;
import com.base16.gedsys.model.ActaausenteJpaController;
import com.base16.gedsys.model.ActainvitadoJpaController;
import com.base16.gedsys.model.CargoJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import com.base16.utils.DateTimeUtils;
import fr.opensagres.xdocreport.converter.ConverterRegistry;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.IConverter;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.document.DocumentKind;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
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
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.common.navigation.TextNavigation;
import org.odftoolkit.simple.common.navigation.TextSelection;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rober
 */
@Named(value = "actaBean")
@ViewScoped
public class ActaBean extends BaseBean implements Serializable {

    /**
     * Creates a new instance of ActaBean
     */
    private static final long SerialVersionUID = 1L;
    private Acta acta = new Acta();
    private List<Acta> actas;
    private List<Usuario> asistentes;
    private List<Usuario> invitados;
    private List<Usuario> ausentes;
    private String accion;
    
    private StreamedContent content;
    private String filePath = "";

    public ActaBean() {
        this.accion = "Crear";
        this.acta.setFecha(new Date());
    }

    public Acta getActa() {
        return acta;
    }

    public void setActa(Acta acta) {
        this.acta = acta;
    }

    public List<Acta> getActas() {
        return actas;
    }

    public void setActas(List<Acta> actas) {
        this.actas = actas;
    }

    public List<Usuario> getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(List<Usuario> asistentes) {
        this.asistentes = asistentes;
    }

    public List<Usuario> getInvitados() {
        return invitados;
    }

    public void setInvitados(List<Usuario> invitados) {
        this.invitados = invitados;
    }

    public List<Usuario> getAusentes() {
        return ausentes;
    }

    public void setAusentes(List<Usuario> ausentes) {
        this.ausentes = ausentes;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void procesar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            switch (accion) {
                case "Crear":
                    crear();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Acta", "¡Documento creado exitosamente!"));
                    break;
                case "editar":
                    editar();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Acta", "¡Documento modificado exitosamente!"));
                    break;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", e.getMessage()));
            Logger.getLogger(ActaBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    private void crear() throws Exception {
        ActaJpaController aJpa;
        ActaasistenteJpaController asisJpa;
        ActainvitadoJpaController invitJpa;
        ActaausenteJpaController ausJpa;
        
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        aJpa = new ActaJpaController(emf);
        asisJpa = new ActaasistenteJpaController(emf);
        invitJpa = new ActainvitadoJpaController(emf);
        ausJpa = new ActaausenteJpaController(emf);
        
        this.acta.setFechaCreacion(new Date());
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.acta.setCreadoPor(usuario);
        this.acta.setModificadoPor(usuario);
        this.acta.setFechaModificacion(new Date());
        this.acta.setEstado(1);
        aJpa.create(this.acta);
        
        List<Actaasistente> actaasistenteList = new ArrayList<>();
        for (Usuario asistente : asistentes) {
            Actaasistente asis =new Actaasistente();
            asis.setAsistente(asistente);
            asis.setActa(acta);
            asisJpa.create(asis);
            actaasistenteList.add(asis);
        }
        
        this.acta.setActaasistenteList(actaasistenteList);
        
        List<Actainvitado> actainvitadosList = new ArrayList<>();
        for (Usuario invitado : invitados) {
            Actainvitado invit =new Actainvitado();
            invit.setInvitado(invitado);
            invit.setActa(acta);
            invitJpa.create(invit);
            actainvitadosList.add(invit);
        }
        this.acta.setActainvitadoList(actainvitadosList);
        
        List<Actaausente> actaausenteList = new ArrayList<>();
        for (Usuario ausente : ausentes) {
            Actaausente ausen =new Actaausente();
            ausen.setAusente(ausente);
            ausen.setActa(acta);
            ausJpa.create(ausen);
            actaausenteList.add(ausen);
        }
        this.acta.setActaausenteList(actaausenteList);
        
    }

    private void editar() throws Exception {
        ActaJpaController aJpa;
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        aJpa = new ActaJpaController(emf);
        this.acta.setFechaCreacion(new Date());
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.acta.setModificadoPor(usuario);
        this.acta.setEstado(2);
        aJpa.edit(this.acta);
    }

    public void firmar() {
        //TODO: Recuperar consecutivo de documento.
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        this.acta.setModificadoPor(usuario);
        this.acta.setFechaFirma(new Date());
        this.acta.setEstado(3);
    }
    
    private void loadDocument(){
        try {
            this.filePath =  this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".pdf";
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

    public List<Acta> getActasByResponsable() {
        ActaJpaController aJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            aJpa = new ActaJpaController(emf);
            actas = aJpa.findActaEntities();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        return actas;
    }
    
}
