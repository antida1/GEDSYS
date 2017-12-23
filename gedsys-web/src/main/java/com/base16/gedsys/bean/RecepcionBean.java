/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.entities.DestinatariosDoc;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Municipio;
import com.base16.gedsys.entities.TipoDocumento;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.Entidad;
import com.base16.gedsys.entities.Transportador;
import com.base16.gedsys.model.ConsecutivoJpaController;
import com.base16.gedsys.model.DestinatariosDocJpaController;
import com.base16.gedsys.model.DocumentoJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import com.base16.utils.Mensajeria;
import com.base16.utils.UploadDocument;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author rober
 */
@ManagedBean
@ViewScoped
public class RecepcionBean extends BaseBean implements Serializable {

    private static final long SerialVersionUID = 1L;

    private Documento documento = new Documento();
    private List<Municipio> municipios;
    private List<Documento> documentos;
    private List<Usuario> usuarios;
    private List<TipoDocumento> tipoDocumentos;
    private List<Entidad> entidades;
    private List<Transportador> transportadores;
    private List<Usuario> destinatarios;

    private String accion;
    private int MunicipioId;
    private UploadedFile documentFile;

    private Boolean canSave;
    private Boolean canGenRad;
    private Boolean canPrint;

    public RecepcionBean() {
        this.canGenRad = false;
        this.canPrint = true;
        this.canSave = true;
    }

    @PostConstruct
    public void init() {
        try {

            MunicipioBean mb = new MunicipioBean();
            mb.listar();
            this.municipios = mb.getMunicipios();

            UsuarioBean ub = new UsuarioBean();
            ub.listar();
            this.usuarios = ub.getUsuarios();

        } catch (Exception ex) {
            Logger.getLogger(RecepcionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> Municipios) {
        this.municipios = Municipios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public int getMunicipioId() {
        return MunicipioId;
    }

    public void setMunicipioId(int MunicipioId) {
        this.MunicipioId = MunicipioId;
    }

    public List<TipoDocumento> getTipoDocumentos() {
        return tipoDocumentos;
    }

    public void setTipoDocumentos(List<TipoDocumento> tipoDocumentos) {
        this.tipoDocumentos = tipoDocumentos;
    }

    public UploadedFile getDocumentFile() {
        return documentFile;
    }

    public void setDocumentFile(UploadedFile documentFile) {
        this.documentFile = documentFile;
    }

    public List<Entidad> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<Entidad> entidades) {
        this.entidades = entidades;
    }

    public List<Transportador> getTransportadores() {
        return transportadores;
    }

    public void setTransportadores(List<Transportador> transportadores) {
        this.transportadores = transportadores;
    }

    public List<Usuario> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<Usuario> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public Boolean getCanSave() {
        return canSave;
    }

    public void setCanSave(Boolean canSave) {
        this.canSave = canSave;
    }

    public Boolean getCanGenRad() {
        return canGenRad;
    }

    public void setCanGenRad(Boolean canGenRad) {
        this.canGenRad = canGenRad;
    }

    public Boolean getCanPrint() {
        return canPrint;
    }

    public void setCanPrint(Boolean canPrint) {
        this.canPrint = canPrint;
    }

    public void generarConsectivo() {
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(configFilePath);
            EntityManager em = emf.createEntityManager();
            ConsecutivoJpaController cJpa;

            em.getTransaction().begin();
            cJpa = new ConsecutivoJpaController(emf);
            Consecutivo consec = cJpa.findConsecutivoByTipoConsecutivo("recepcion");
            Integer intConsec = Integer.parseInt(consec.getConsecutivo());
            intConsec++;
            consec.setConsecutivo(intConsec.toString());
            em.merge(consec);
            em.flush();
            em.getTransaction().commit();
            this.documento.setConsecutivo(consec.getConsecutivo());
            RequestContext.getCurrentInstance().execute("PF('genRad').jq.attr('disabled', 'true').addClass('ui-state-disabled');");
            RequestContext.getCurrentInstance().execute("PF('saveDoc').jq.removeAttr('disabled').removeClass('ui-state-disabled');");
            RequestContext.getCurrentInstance().execute("PF('printRad').jq.removeAttr('disabled').removeClass('ui-state-disabled');");
            this.canGenRad = true;
            this.canPrint = false;
            this.canSave = false;

        } catch (NumberFormatException e) {
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Redicacion de Documentos", e.getMessage()));
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Redicacion de Documentos", "Consecutivo Generado Exitosamente"));
    }

    public void radicar() {
        DocumentoJpaController sJpa;
        DestinatariosDocJpaController dJpa;
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.documentFile.getContents().length <= 0) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Recepción de Documentos", "Por favor adjunte el documento!"));
            RequestContext.getCurrentInstance().execute("PF('genRad').jq.attr('disabled', 'true').addClass('ui-state-disabled');");
            RequestContext.getCurrentInstance().execute("PF('saveDoc').jq.removeAttr('disabled').removeClass('ui-state-disabled');");
            RequestContext.getCurrentInstance().execute("PF('printRad').jq.removeAttr('disabled').removeClass('ui-state-disabled');");
            this.canGenRad = true;
            this.canPrint = false;
            this.canSave = false;
        } else {
            try {
                EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
                sJpa = new DocumentoJpaController(emf);
                dJpa = new DestinatariosDocJpaController(emf);

                Usuario usuario = (Usuario) SessionUtils.getUsuario();
                this.documento.setFechaCreacion(new Date());
                this.documento.setFechaModificacion(new Date());
                this.documento.setCreadoPor(usuario);
                UploadDocument uDoc = new UploadDocument();
                uDoc.upload(documentFile, this.documenstSavePath);
                this.documento.setRutaArchivo(uDoc.getFileName(documentFile));
                this.documento.setNombreDocumento(uDoc.getUuid().toString());
                List<DestinatariosDoc> destinatariosDocCollection = new ArrayList<>();

                DestinatariosDoc destinatarioPrincipal = new DestinatariosDoc();
                destinatarioPrincipal.setCreadoPor(usuario);
                destinatarioPrincipal.setDestinatarioId(this.documento.getDestinatario());
                dJpa.create(destinatarioPrincipal);
                destinatariosDocCollection.add(destinatarioPrincipal);

                for (Usuario dest : destinatarios) {
                    if (dest.getId() != this.documento.getDestinatario().getId()) {
                        DestinatariosDoc destinatarioDoc = new DestinatariosDoc();
                        destinatarioDoc.setCreadoPor(usuario);
                        destinatarioDoc.setDestinatarioId(dest);
                        dJpa.create(destinatarioDoc);
                        destinatariosDocCollection.add(destinatarioDoc);
                    }
                }

                this.documento.setDestinatariosDocCollection(destinatariosDocCollection);
                sJpa.create(documento);
                Mensajeria mensajeria = new Mensajeria();
                mensajeria.send(usuario, "Nuevo documento recibido", this.documento.getAsunto());
                
                //TODO: Pendiente registrar notificacion en base de datos acorde a la fecha
                
                this.limpiar();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Recepción de Documentos", "Documento Almacenado exitoxamente!"));

            } catch (Exception e) {
                Logger.getLogger(RecepcionBean.class.getName()).log(Level.SEVERE, e.getMessage());
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al Radicar el documento", e.getMessage()));
            }
        }
    }

    public void limpiar() {
        this.documento = null;
        this.documento = new Documento();
        RequestContext.getCurrentInstance().execute("PF('genRad').jq.removeAttr('disabled').removeClass('ui-state-disabled');");
        RequestContext.getCurrentInstance().execute("PF('saveDoc').jq.attr('disabled', 'true').addClass('ui-state-disabled');");
        RequestContext.getCurrentInstance().execute("PF('printRad').jq.attr('disabled', 'true').addClass('ui-state-disabled');");
        this.canGenRad = false;
        this.canPrint = true;
        this.canSave = true;
    }
}
