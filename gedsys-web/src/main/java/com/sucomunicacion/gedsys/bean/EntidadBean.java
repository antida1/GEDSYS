/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.bean;

import com.sucomunicacion.gedsys.utils.JpaUtils;
import com.sucomunicacion.gedsys.entities.Entidad;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.model.EntidadJpaController;
import com.sucomunicacion.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;


@ManagedBean
@ViewScoped
public class EntidadBean extends BaseBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Entidad entidad= new Entidad();
    private List<Entidad> Entidades;
    private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }
    public List<Entidad> getEntidades() {
        return Entidades;
    }

    public void setEntidades(List<Entidad> Entidades) {
        this.Entidades = Entidades;
    }
    
    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
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
        EntidadJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new EntidadJpaController(emf);
            
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            
            this.entidad.setFechaCreacion(new Date());
            this.entidad.setFechaModificacion(new Date());
            this.entidad.setCreadoPor(usuario.getNombres() + " " + usuario.getApelidos());
            sJpa.create(entidad);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void modificar() throws Exception{
        EntidadJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new EntidadJpaController(emf);
            this.entidad.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.entidad.setModificadoPor(usuario.getNombres() + " " + usuario.getApelidos());
            sJpa.edit(entidad);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminar(Entidad entidad){
        EntidadJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new EntidadJpaController(emf);
            sJpa.destroy(entidad.getId());
            this.listar();
        } catch (Exception e) {
            
        }
    }
    
    public void listar(){
        EntidadJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new EntidadJpaController(emf);
            Entidades = sJpa.findEntidadEntities();
        } catch (Exception e) {
            
        }
    }
    
    public void getEntidadById(Entidad entidad){
        EntidadJpaController sJpa;
        Entidad entidadTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new EntidadJpaController(emf);
            entidadTemp = sJpa.findEntidad(entidad.getId());
            if(entidadTemp != null){
                this.entidad = entidadTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            
        }
    }
    
    public void limpiar(){
        this.entidad.setNombre("");
        this.entidad.setCreadoPor("");
        this.entidad.setId(0);
    }
}
