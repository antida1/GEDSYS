/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.entities.ConsecutivosUsuario;
import com.base16.gedsys.entities.DestinatariosDoc;
import com.base16.gedsys.entities.DiaFestivo;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Municipio;
import com.base16.gedsys.entities.TipoDocumento;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.Entidad;
import com.base16.gedsys.entities.Notificacion;
import com.base16.gedsys.entities.SeccionSubSeccion;
import com.base16.gedsys.entities.Serie;
import com.base16.gedsys.entities.SignaturaTopografica;
import com.base16.gedsys.entities.SubSerie;
import com.base16.gedsys.entities.TipoDocumental;
import com.base16.gedsys.entities.Transportador;
import com.base16.gedsys.entities.UnidadDocumental;
import com.base16.gedsys.model.ConsecutivoJpaController;
import com.base16.gedsys.model.ConsecutivosUsuarioJpaController;
import com.base16.gedsys.model.DestinatariosDocJpaController;
import com.base16.gedsys.model.DocumentoJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import com.base16.utils.DateTimeUtils;
import com.base16.utils.Mensajeria;
import com.base16.utils.UploadDocument;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Lina David
 */
@ManagedBean
@ViewScoped
public class CargaBean extends BaseBean implements Serializable {

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
    private int estado;
    private int MunicipioId;
    private UploadedFile documentFile;
    private UploadedFile respuestaFile;
    
    private TreeNode selectedNodeSignatura = new DefaultTreeNode();
    private TipoDocumental tipoDocumental = new TipoDocumental();

    private SeccionSubSeccion seccionSubSeccion;
    private Serie serie;
    private SubSerie subSerie;
    private UnidadDocumental unidadDocumental;

    private List<SeccionSubSeccion> seccionesSubSecciones;
    private List<Serie> series;
    private List<SubSerie> subSeries;
    private List<UnidadDocumental> unidadesDocumentales;
    private List<TipoDocumental> tiposDocumentales;


    public CargaBean() {     
        
        this.documento.setFechaDocumento(new Date());
        this.init();
    }

    //@PostConstruct
    public void init() {
        try {
            MunicipioBean mb = new MunicipioBean();
            mb.listar();
            this.municipios = mb.getMunicipios();

            UsuarioBean ub = new UsuarioBean();
            ub.listar();
            this.usuarios = ub.getUsuarios();

        } catch (Exception ex) {
            Logger.getLogger(CargaBean.class.getName()).log(Level.SEVERE, null, ex);
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
   
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }    

    public UploadedFile getRespuestaFile() {
        return respuestaFile;
    }

    public void setRespuestaFile(UploadedFile respuestaFile) {
        this.respuestaFile = respuestaFile;
    }

    public TreeNode getSelectedNodeSignatura() {
        return selectedNodeSignatura;
    }

    public void setSelectedNodeSignatura(TreeNode selectedNodeSignatura) {
        this.selectedNodeSignatura = selectedNodeSignatura;
    }

    public TipoDocumental getTipoDocumental() {
        return tipoDocumental;
    }

    public void setTipoDocumental(TipoDocumental tipoDocumental) {
        this.tipoDocumental = tipoDocumental;
    }

    public SeccionSubSeccion getSeccionSubSeccion() {
        return seccionSubSeccion;
    }

    public void setSeccionSubSeccion(SeccionSubSeccion seccionSubSeccion) {
        this.seccionSubSeccion = seccionSubSeccion;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public SubSerie getSubSerie() {
        return subSerie;
    }

    public void setSubSerie(SubSerie subSerie) {
        this.subSerie = subSerie;
    }

    public UnidadDocumental getUnidadDocumental() {
        return unidadDocumental;
    }

    public void setUnidadDocumental(UnidadDocumental unidadDocumental) {
        this.unidadDocumental = unidadDocumental;
    }

    public List<SeccionSubSeccion> getSeccionesSubSecciones() {
        return seccionesSubSecciones;
    }

    public void setSeccionesSubSecciones(List<SeccionSubSeccion> seccionesSubSecciones) {
        this.seccionesSubSecciones = seccionesSubSecciones;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    public List<SubSerie> getSubSeries() {
        return subSeries;
    }

    public void setSubSeries(List<SubSerie> subSeries) {
        this.subSeries = subSeries;
    }

    public List<UnidadDocumental> getUnidadesDocumentales() {
        return unidadesDocumentales;
    }

    public void setUnidadesDocumentales(List<UnidadDocumental> unidadesDocumentales) {
        this.unidadesDocumentales = unidadesDocumentales;
    }

    public List<TipoDocumental> getTiposDocumentales() {
        return tiposDocumentales;
    }

    public void setTiposDocumentales(List<TipoDocumental> tiposDocumentales) {
        this.tiposDocumentales = tiposDocumentales;
    }
    
    
    
    public void radicar() {
        DocumentoJpaController sJpa;
        DestinatariosDocJpaController dJpa;
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.documentFile.getContents().length <= 0) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Radicación Manual", "¡Por favor adjunte el documento!"));        
        } else {
            if(this.documento.getEstado() == 2){
                if(this.respuestaFile.getContents().length <= 0){
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Radicación Manual", "¡Por favor adjunte la respuesta!"));
                }else{
                    UploadDocument uResp = new UploadDocument();
                    //Donde se guarda la respuesta del documento -- Preguntar a Robert mañana
                }
            }else{
                if(this.documento.getEstado() == 5){
                    if (this.selectedNodeSignatura != null) {
                        if (documento.getSignaturaTopografica() == null) {
                            try {
                                EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
                                sJpa = new DocumentoJpaController(emf);
                                SignaturaTopografica signatura = (SignaturaTopografica) this.selectedNodeSignatura.getData();
                                documento.setSignaturaTopografica(signatura);
                                documento.setTipoDocumental(this.tipoDocumental);
                                sJpa.edit(documento);
                                this.addMessage(new FacesMessage("Archivar documentos", "Documento archivado correctamente"));
                            } catch (Exception e) {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
                                Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
                            }
                        } else {
                            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivar documentos", "El documento ya ha sido archivado"));
                        }
                    } else {
                        this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Archivar", "No es posible archivar el documento, seleccione la signatura topografica!"));
                    }
                }
            }                   
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            EntityManager em = emf.createEntityManager();
            try {
                em.getTransaction().begin();
                sJpa = new DocumentoJpaController(emf);
                dJpa = new DestinatariosDocJpaController(emf);

                Usuario usuario = (Usuario) SessionUtils.getUsuario();
                this.documento.setFechaCreacion(new Date());
                this.documento.setFechaModificacion(new Date());
                this.documento.setFechaRecepcion(new Date());
                this.documento.setCreadoPor(usuario);
                this.documento.setRequiereRespuesta(this.documento.getTipoDocumento().getRequiereRespuesta());

                if (this.documento.getTipoDocumento().getRequiereRespuesta()) {
                    if (this.documento.getTipoDocumento().getTipoCalendario() == "habil") {
                        List<Date> dates = new ArrayList<>();
                        DiaFestivoBean diaFestivoBean = new DiaFestivoBean();
                        diaFestivoBean.listar();
                        for (DiaFestivo diaFestivo : diaFestivoBean.getDiaFestivos()) {
                            dates.add(diaFestivo.getDiaFestivo());
                        }
                        Date fechaRespuesta;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date dateWithoutTime = sdf.parse(sdf.format(new Date()));
                        fechaRespuesta = DateTimeUtils.fechaDeRespuesta(DateTimeUtils.toCalendar(dateWithoutTime), this.documento.getTipoDocumento().getDiasRespuesta(), dates);
                        this.documento.setFechaVencimiento(fechaRespuesta);
                    } else {
                        List<Date> dates = new ArrayList<>();
                        Date fechaRespuesta;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date dateWithoutTime = sdf.parse(sdf.format(new Date()));
                        fechaRespuesta = DateTimeUtils.fechaDeRespuesta(DateTimeUtils.toCalendar(dateWithoutTime), this.documento.getTipoDocumento().getDiasRespuesta(), dates);
                        this.documento.setFechaVencimiento(fechaRespuesta);
                    }
                    ScheduleView scheduleNotify = new ScheduleView();

                    Notificacion notificacion = new Notificacion();
                    notificacion.setAsunto(this.documento.getAsunto());
                    notificacion.setFechaCreacion(new Date());
                    notificacion.setCreadorPor(usuario.getId());
                    notificacion.setFechaInicio(this.documento.getFechaVencimiento());
                    notificacion.setFechaFinalizacion(this.documento.getFechaVencimiento());

                    //TODO: Verificar las preferencias del Usuario
                    notificacion.setNotificacionCorreo(true);
                    notificacion.setNotificacionPopup(true);
                    notificacion.setNotificacionPush(true);

                    ScheduleBeanEvent sbEvent = new ScheduleBeanEvent(this.documento.getAsunto(), this.documento.getFechaVencimiento(), this.documento.getFechaVencimiento(), notificacion);
                    scheduleNotify.setEvent(sbEvent);
                    scheduleNotify.addEvent(null);
                }
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
                this.documento.setRemitenteExteno(this.documento.getRemitente());
                sJpa.create(documento);

                //TODO: Verificar preferencias del usuario para envio de Mensajes PUSH.
                Mensajeria mensajeria = new Mensajeria();
                mensajeria.send(this.documento.getDestinatario(), "Nuevo documento recibido", this.documento.getAsunto());

                em.getTransaction().commit();
                //TODO: Pendiente registrar notificacion en base de datos acorde a la fecha
                this.limpiar();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Radicación Manual", "¡Documento Almacenado exitoxamente!"));

            } catch (Exception e) {
                Logger.getLogger(CargaBean.class.getName()).log(Level.SEVERE, e.getMessage());
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al Radicar el documento", e.getMessage()));
                em.getTransaction().rollback();
            }
        }
    }
    

    public void onEntidadChange() {
        this.documento.setDireccion(this.documento.getEntidad().getDireccion());
    }
    
    public void onEstadoChange() {
        if(this.documento.getEstado() !=null && !this.documento.getEstado().equals("")){
            estado = this.documento.getEstado();
        }else{
            estado = 0;
        }
        
    }

    public void limpiar() {
        this.documento = null;
        this.documento = new Documento();
        this.documento.setFechaDocumento(new Date());        
    }
}
