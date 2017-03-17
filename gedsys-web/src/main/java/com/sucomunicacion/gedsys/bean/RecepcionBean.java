/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.bean;

import com.sucomunicacion.gedsys.entities.Departamento;
import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.Municipio;
import com.sucomunicacion.gedsys.entities.Pais;
import com.sucomunicacion.gedsys.entities.TipoDocumento;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.model.DocumentoJpaController;
import com.sucomunicacion.gedsys.utils.JpaUtils;
import com.sucomunicacion.gedsys.web.utils.SessionUtils;
import com.sucomunicacion.utils.UploadDocument;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import org.primefaces.event.FileUploadEvent;
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
    private List<Pais> paises;
    private List<Departamento> depatamentos;
    private List<Municipio> municipios;
    private List<Documento> documentos;
    private List<Usuario> usuarios;
    private List<TipoDocumento> tipoDocumentos;

    
    private String accion;
    
    private int PaisId;
    private int DepartamentoId;
    private int MunicipioId;
    
    private UploadedFile documentFile;
    private List<UploadedFile> foliosFile = new  LinkedList<>();
 
    
    @PostConstruct
    public void init(){
        try {
            PaisBean pb = new PaisBean();
            pb.listar();
            this.paises = pb.getPaises();
            
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

    public List<Departamento> getDepatamentos() {
        return depatamentos;
    }

    public void setDepatamentos(List<Departamento> Depatamentos) {
        this.depatamentos = Depatamentos;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> Municipios) {
        this.municipios = Municipios;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public int getPaisId() {
        return PaisId;
    }

    public void setPaisId(int PaisId) {
        this.PaisId = PaisId;
    }

    public int getDepartamentoId() {
        return DepartamentoId;
    }

    public void setDepartamentoId(int DepartamentoId) {
        this.DepartamentoId = DepartamentoId;
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
    
    public void addFiliosToDoc(FileUploadEvent event){
        foliosFile.add(event.getFile());
    }
    
    public List<UploadedFile> getFoliosFile() {
        return foliosFile;
    }

    public void setFoliosFile(List<UploadedFile> foliosFile) {
        this.foliosFile = foliosFile;
    }
    
    public void onPaisChange(){
        try {
            if( PaisId != 0){
                DepartamentoBean db = new DepartamentoBean();
                Pais p  = new Pais();
                p.setId(PaisId);
                db.getDepartamentosByPais(p);
                depatamentos = db.getDepartamentos();
            } else
            {
                depatamentos = null;
            }
        } catch (Exception e) {
            
        }
    }
    
    public void onDepartamentoChange(){
        try {
            if(DepartamentoId != 0){
                MunicipioBean mb = new MunicipioBean();
                Departamento d = new Departamento();
                d.setId(DepartamentoId);
                mb.getMunicipiosByDepartamento(d);
                municipios = mb.getMunicipios();
            }
        } catch (Exception e) {
        }
    }
    
    /**
     *
     * @throws Exception
     */
    public void radicar() throws Exception{
         DocumentoJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new DocumentoJpaController(emf);
            
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.documento.setFechaCreacion(new Date());
            this.documento.setFechaModificacion(new Date());
            this.documento.setCreadoPor(usuario);
            UploadDocument uDoc = new UploadDocument();
            uDoc.upload(documentFile);
            
            for (UploadedFile next : foliosFile) {
                UploadDocument uFile = new UploadDocument();
                uFile.upload(next);
            }
            this.documento.setRutaArchivo(uDoc.getFileName(documentFile));
            sJpa.create(documento);
        } catch (Exception e) {
            throw e;
        }
    }
    
}
