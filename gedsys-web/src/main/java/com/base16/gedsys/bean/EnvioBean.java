/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Municipio;
import com.base16.gedsys.entities.TipoDocumento;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.Entidad;
import com.base16.gedsys.entities.Transportador;
import com.base16.gedsys.model.ConsecutivoJpaController;
import com.base16.gedsys.model.DocumentoJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import com.base16.utils.UploadDocument;
import java.io.Serializable;
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
import org.primefaces.model.UploadedFile;

/**
 *
 * @author rober
 */
@ManagedBean
@ViewScoped
public class EnvioBean extends BaseBean implements Serializable {

    private static final long SerialVersionUID = 1L;
    
    private Documento documento = new Documento();
    private List<Municipio> municipios;
    private List<Documento> documentos;
    private List<Usuario> usuarios;
    private List<TipoDocumento> tipoDocumentos;
    private List<Entidad> entidades;
    private List<Transportador> transportadores;

    private String accion; 
    private int MunicipioId;
    private UploadedFile documentFile;
    
    @PostConstruct
    public void init(){
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
    
    public void generarConsectivo(){
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(configFilePath);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {     
            ConsecutivoJpaController cJpa;
            cJpa = new ConsecutivoJpaController(emf);
            Consecutivo consec = cJpa.findConsecutivoByTipoConsecutivo("envio");
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE,"No hay consecutivos para envio");
            Integer intConsec = Integer.parseInt(consec.getConsecutivo());
            intConsec++;
            consec.setConsecutivo(intConsec.toString());
            em.merge(consec);
            em.flush();
            em.getTransaction().commit();
            this.documento.setConsecutivo(consec.getConsecutivo());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Envio de Documentos",  "Consecutivo Generado Exitosamente"));
        } catch ( Exception e) {
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Envio de Documentos", e.getMessage()  ));
            em.getTransaction().rollback();
        }
        
    }
    
    public void radicar() {
        DocumentoJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new DocumentoJpaController(emf);
            
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.documento.setFechaCreacion(new Date());
            this.documento.setFechaModificacion(new Date());
            this.documento.setCreadoPor(usuario);
            UploadDocument uDoc = new UploadDocument();
            uDoc.upload(documentFile, this.documenstSavePath);
           
            this.documento.setRutaArchivo(uDoc.getFileName(documentFile));
            sJpa.create(documento);
            this.limpiar();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Recepción de Documentos",  "Documento Almacenado exitoxamente!"));
        } catch (Exception e) {
            Logger.getLogger(RecepcionBean.class.getName()).log(Level.SEVERE, e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Error al Radicar el documento",  e.getMessage()));
        }
    }
    
    public void limpiar(){
        this.documento = null;
        this.documento = new Documento();
        
    }
}
