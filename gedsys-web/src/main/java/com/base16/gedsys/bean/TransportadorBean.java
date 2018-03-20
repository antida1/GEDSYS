/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.Transportador;
import com.base16.gedsys.model.TransportadorJpaController;
import com.base16.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import org.primefaces.context.RequestContext;


@ManagedBean
@ViewScoped
public class TransportadorBean extends BaseBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Transportador transportador= new Transportador();
    private List<Transportador> Transportadores;
    private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }
    public List<Transportador> getTransportadores() {
        return Transportadores;
    }

    public void setTransportadores(List<Transportador> Transportadores) {
        this.Transportadores = Transportadores;
    }
    
    public Transportador getTransportador() {
        return transportador;
    }

    public void setTransportador(Transportador transportador) {
        this.transportador = transportador;
    }
    
    public void procesar(){
        try {
            switch(accion){
                case "Crear":
                    crear();
                    this.addMessage( new FacesMessage(FacesMessage.SEVERITY_INFO, "Transportador", "¡Transportador creado exitoxamente!"));
                    break;
                case "Modificar":
                    modificar();
                    this.addMessage( new FacesMessage(FacesMessage.SEVERITY_INFO, "Transportador", "¡Transportador modificado exitoxamente!"));
                    break;
            }
            RequestContext.getCurrentInstance().execute("PF('transportadorDialog').hide()");
        } catch (Exception e) {
            this.addMessage( new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transportador", e.getMessage()));
            Logger.getLogger(EntidadBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void crear() throws Exception{
        TransportadorJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new TransportadorJpaController(emf);
            
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            
            this.transportador.setFechaCreacion(new Date());
            this.transportador.setFechaModificacion(new Date());
            this.transportador.setCreadoPor(usuario);
            sJpa.create(transportador);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void modificar() throws Exception{
        TransportadorJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new TransportadorJpaController(emf);
            this.transportador.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.transportador.setModificadoPor(usuario);
            sJpa.edit(transportador);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminar(Transportador transportador){
        TransportadorJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new TransportadorJpaController(emf);
            sJpa.destroy(transportador.getId());
            this.listar();
            this.addMessage( new FacesMessage(FacesMessage.SEVERITY_INFO, "Transportador", "Transportador Eliminado"));
        } catch (Exception e) {
            this.addMessage( new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transportador", e.getMessage()));
            Logger.getLogger(EntidadBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
    public void listar(){
        TransportadorJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new TransportadorJpaController(emf);
            Transportadores = sJpa.findTransportadorEntities();
        } catch (Exception e) {
            
        }
    }
    
    public void getTransportadorById(Transportador transportador){
        TransportadorJpaController sJpa;
        Transportador transportadorTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new TransportadorJpaController(emf);
            transportadorTemp = sJpa.findTransportador(transportador.getId());
            if(transportadorTemp != null){
                this.transportador = transportadorTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            
        }
    }
    
    public void limpiar(){
        this.transportador = new Transportador();
    }
}
