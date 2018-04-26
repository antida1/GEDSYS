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
import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.entities.DestinatariosDoc;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.ActaJpaController;
import com.base16.gedsys.model.ActaasistenteJpaController;
import com.base16.gedsys.model.ActaausenteJpaController;
import com.base16.gedsys.model.ActainvitadoJpaController;
import com.base16.gedsys.model.CargoJpaController;
import com.base16.gedsys.model.CartaJpaController;
import com.base16.gedsys.model.ConsecutivoJpaController;
import com.base16.gedsys.model.DestinatariosDocJpaController;
import com.base16.gedsys.model.DocumentoJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import com.base16.utils.DateTimeUtils;
import com.base16.utils.UploadDocument;
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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.common.navigation.TextNavigation;
import org.odftoolkit.simple.common.navigation.TextSelection;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rober
 * @author Modificación Lina David
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

    private Documento documentoRelacionado;

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
            Actaasistente asis = new Actaasistente();
            asis.setAsistente(asistente);
            asis.setActa(acta);
            asisJpa.create(asis);
            actaasistenteList.add(asis);
        }

        this.acta.setActaasistenteList(actaasistenteList);

        List<Actainvitado> actainvitadosList = new ArrayList<>();
        for (Usuario invitado : invitados) {
            Actainvitado invit = new Actainvitado();
            invit.setInvitado(invitado);
            invit.setActa(acta);
            invitJpa.create(invit);
            actainvitadosList.add(invit);
        }
        this.acta.setActainvitadoList(actainvitadosList);

        List<Actaausente> actaausenteList = new ArrayList<>();
        for (Usuario ausente : ausentes) {
            Actaausente ausen = new Actaausente();
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
        this.acta.setEstado(1);
        aJpa.edit(this.acta);
    }

    public void firmar() {
        FacesContext context = FacesContext.getCurrentInstance();
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        EntityManager em = emf.createEntityManager();
        try {
            ConsecutivoJpaController cJpa;
            cJpa = new ConsecutivoJpaController(emf);

            ActaJpaController caJpa;
            caJpa = new ActaJpaController(emf);

            em.getTransaction().begin();
            Consecutivo consec = cJpa.findConsecutivoByTipoConsecutivo("acta");
            Integer intConsec = Integer.parseInt(consec.getConsecutivo());
            intConsec++;
            consec.setConsecutivo(intConsec.toString());
            em.merge(consec);
            em.flush();
            em.getTransaction().commit();

            //Transacción
            em.getTransaction().begin();

            DestinatariosDocJpaController djpa = new DestinatariosDocJpaController(emf);

            SimpleDateFormat sdfDateRadicado = new SimpleDateFormat("yyyyMMdd");
            Date hoy = new Date();
            String strHoy = sdfDateRadicado.format(hoy);
            String radicado = consec.getPrefijo() + strHoy + consec.getConsecutivo() + consec.getSufijo();

            this.acta.setConsecutivo(radicado);
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.acta.setModificadoPor(usuario);
            this.acta.setFechaFirma(new Date());
            this.acta.setEstado(0);
            caJpa.edit(this.acta);            
            ActaViewBean cvb = new ActaViewBean();
            cvb.showDocumentFinal(this.acta);

            // TODO: Crear el nuevo documento acta
            Documento documento = new Documento();
            UploadDocument uDoc = new UploadDocument();
            File file = new File(cvb.getFilePath());
            uDoc.upload(file, this.getDocumenstSavePath());

            // TODO: Crea nuevo registro de documento
            documento.setRutaArchivo(uDoc.getFileName(file));
            documento.setNombreDocumento(uDoc.getUuid().toString());
            documento.setRemitenteExteno(this.acta.getPresidente().getNombres() + " " + this.acta.getPresidente().getApelidos());
            documento.setDestinatario(this.acta.getPresidente());
            documento.setAsunto(this.acta.getConvocatoria());
            documento.setFechaDocumento(this.acta.getFecha());
            documento.setFechaCreacion(new Date());
            documento.setDetalle(this.acta.getDesarrollo());
            documento.setDireccion(this.acta.getLugar());
            documento.setConsecutivo(this.acta.getConsecutivo());
            documento.setEstado(9);

            // Crea la colección de asistentes para llenar la colección de destinatarios del documento
            Collection<DestinatariosDoc> destinatariosDocCollection = new ArrayList<>();
            for (Actaasistente actaAsistente : this.acta.getActaasistenteList()) {
                DestinatariosDoc destinatariosDoc = new DestinatariosDoc();
                destinatariosDoc.setCreadoPor(usuario);
                destinatariosDoc.setDestinatarioId(actaAsistente.getAsistente());
                destinatariosDoc.setFechaCreacion(new Date());
                djpa.create(destinatariosDoc);
                destinatariosDocCollection.add(destinatariosDoc);
            }
            documento.setDestinatariosDocCollection(destinatariosDocCollection);

            DocumentoJpaController djc = new DocumentoJpaController(emf);
            djc.create(documento);
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Acta", "¡Documento Firmado exitosamente!"));            
          
            em.getTransaction().commit();            
            // TODO: Modificar el documento padre, mover a por archivar.
            if (this.documentoRelacionado != null) {
                this.documentoRelacionado.setDocumentoRelacionado(documento);
                this.documentoRelacionado.setEstado(3);
                djc.edit(this.documentoRelacionado);
            } 

        } catch (Exception ex) {
            Logger.getLogger(CartaBean.class.getName()).log(Level.SEVERE, null, ex);
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Acta", "No existe el consecutivo para actas en la Entidad Consecutivo"));
            em.getTransaction().rollback();
        }

        //TODO: Recuperar consecutivo de documento.
//        Usuario usuario = (Usuario) SessionUtils.getUsuario();
//        this.acta.setModificadoPor(usuario);
//        this.acta.setFechaFirma(new Date());
//        this.acta.setEstado(3);
    }
    
    public void limpiar() throws IOException{
        this.acta = null;
        this.acta = new Acta();
        this.acta.setFecha(new Date());
        Documento documento = new Documento();
        FacesContext contex = FacesContext.getCurrentInstance();
        contex.getExternalContext().redirect("../../index.xhtml");
    }
    
    public void firmarActa(Acta acta) {
        this.acta = acta;
        RequestContext.getCurrentInstance().execute("PF('denFirmarActa').show()");
    }
    public void editarActa(Acta acta) {
        this.acta = acta;
        this.accion = "editar";
        RequestContext.getCurrentInstance().execute("PF('denEditarActa').show()");
    }
    public void imprimir() {
        FacesContext context = FacesContext.getCurrentInstance();
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        EntityManager em = emf.createEntityManager();
        try {
            ConsecutivoJpaController cJpa;
            cJpa = new ConsecutivoJpaController(emf);

            ActaJpaController caJpa;
            caJpa = new ActaJpaController(emf);

            em.getTransaction().begin();
            Consecutivo consec = cJpa.findConsecutivoByTipoConsecutivo("acta");
            Integer intConsec = Integer.parseInt(consec.getConsecutivo());
            intConsec++;
            consec.setConsecutivo(intConsec.toString());
            em.merge(consec);
            em.flush();
            em.getTransaction().commit();

            SimpleDateFormat sdfDateRadicado = new SimpleDateFormat("yyyyMMdd");
            Date hoy = new Date();
            String strHoy = sdfDateRadicado.format(hoy);
            String radicado = consec.getPrefijo() + strHoy + consec.getConsecutivo() + consec.getSufijo();

            this.acta.setConsecutivo(radicado);
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.acta.setModificadoPor(usuario);
            this.acta.setFechaFirma(new Date());
            this.acta.setEstado(0);
            caJpa.edit(this.acta);            
            ActaViewBean cvb = new ActaViewBean();
            cvb.showDocumentFinalImprimir(this.acta);
            
            // TODO: Crear el nuevo documento carta
            Documento documento = new Documento();
            UploadDocument uDoc = new UploadDocument();
            File file = new File(cvb.getFilePath());
            uDoc.upload(file, this.getDocumenstSavePath());
            
            // TODO: Crea nuevo registro de documento
            documento.setRutaArchivo(uDoc.getFileName(file));
            documento.setNombreDocumento(uDoc.getUuid().toString());
            documento.setConsecutivo(this.acta.getConsecutivo());
            documento.setRemitenteExteno("");
            documento.setDestinatario(this.acta.getPresidente());
            documento.setAsunto(this.acta.getOrden());
            documento.setFechaDocumento(this.acta.getFecha());
            documento.setFechaCreacion(new Date());
            documento.setDetalle(this.acta.getDesarrollo());
            documento.setDireccion("");
            documento.setEstado(8);
            DocumentoJpaController djc = new DocumentoJpaController(emf);
            djc.create(documento);
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Acta", "¡Documento generado correctamente!"));

        } catch (Exception ex) {
            Logger.getLogger(CartaBean.class.getName()).log(Level.SEVERE, null, ex);
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Acta", "No existe el consecutivo para actas en la Entidad Consecutivo"));
            em.getTransaction().rollback();
        }
    }

    private void loadDocument() {
        try {
            this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + this.acta.getId() + ".pdf";
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
