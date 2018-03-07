/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Comentario;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Prestamo;
import com.base16.gedsys.entities.SignaturaTopografica;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.ComentarioJpaController;
import com.base16.gedsys.model.DocumentoJpaController;
import com.base16.gedsys.model.PrestamoJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManagerFactory;
import org.primefaces.context.RequestContext;

/**
 *
 * @author rober
 */
@Named(value = "reintegrarBean")
@ViewScoped
public class ReintegrarBean extends BaseBean implements Serializable {

    /**
     * Creates a new instance of RedireccionarBean
     */
    private static final long SerialVersionUID = 1L;
    private Documento documento;
    private Prestamo prestamo;
    private String mensaje;

    public ReintegrarBean() {   
        
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }
    
    

    public void loadDocumento(Documento doc) {
        this.documento = doc;        
        RequestContext.getCurrentInstance().execute("PF('denReintegrar').show()");
    }
    public void loadPrestamo(Prestamo pres) {
        this.prestamo = pres;        
        RequestContext.getCurrentInstance().execute("PF('denReintegrar').show()");
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public void guadarDocumento() {
        FacesContext context = FacesContext.getCurrentInstance();
        DocumentoJpaController dJpa;
        ComentarioJpaController cJpa;
        PrestamoJpaController sJpa;
        Usuario usuario = (Usuario) SessionUtils.getUsuario();        
        
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            cJpa = new ComentarioJpaController(emf);
            sJpa = new PrestamoJpaController(emf);
            
            Comentario comentario = new Comentario();
            comentario.setComentario(mensaje);           
            comentario.setFechaCreacion(new Date());
            comentario.setDocumento(this.prestamo.getDocumento());
            comentario.setCreadoPor(usuario);
            comentario.setModificadoPor(usuario);
            comentario.setFechaModificacion(new Date());
            cJpa.create(comentario);
            
            //buscar docuemnto
            documento = dJpa.findByConsecutivo(this.prestamo.getDocumento().getConsecutivo());
            
            if(documento.getEstado().equals(4) && this.prestamo != null){
                documento.setEstado(prestamo.getEstadoAnterior());
                prestamo.setEstado(0);                
                sJpa.edit(prestamo);
                dJpa.edit(documento);
            }else{
                if(documento.getEstado().equals(6) &&this.prestamo != null){                
                documento.setEstado(this.prestamo.getEstadoAnterior());
                prestamo.setEstado(0);               
                sJpa.edit(prestamo);
                dJpa.edit(documento);
                }                
            }  
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reintegro de documentos", "Documento reintegrado exitosamente"));
            RequestContext.getCurrentInstance().execute("PF('denReintegrar').hide()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

}