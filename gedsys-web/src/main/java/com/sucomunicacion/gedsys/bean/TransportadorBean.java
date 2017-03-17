/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.bean;

import com.sucomunicacion.gedsys.utils.JpaUtils;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.entities.Transportador;
import com.sucomunicacion.gedsys.model.TransportadorJpaController;
import com.sucomunicacion.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;


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
                    break;
                case "Modificar":
                    modificar();
                    break;
            }
        } catch (Exception e) {
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
        } catch (Exception e) {
            
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
        this.transportador.setNombre("");
        this.transportador.setCreadoPor(null);
        this.transportador.setId(0);
    }
}
