/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.bean;

import com.sucomunicacion.gedsys.utils.JpaUtils;
import com.sucomunicacion.gedsys.entities.Seccion;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.model.SeccionJpaController;
import com.sucomunicacion.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;


@ManagedBean
@ViewScoped
public class SeccionBean extends BaseBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Seccion seccion= new Seccion();
    private List<Seccion> Secciones;
    private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }
    public List<Seccion> getSecciones() {
        return Secciones;
    }

    public void setSecciones(List<Seccion> Secciones) {
        this.Secciones = Secciones;
    }
    
    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
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
        SeccionJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SeccionJpaController(emf);
            
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            
            this.seccion.setFechaCreacion(new Date());
            this.seccion.setFechaModificacion(new Date());
            this.seccion.setCreadoPor(usuario);
            sJpa.create(seccion);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void modificar() throws Exception{
        SeccionJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SeccionJpaController(emf);
            this.seccion.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.seccion.setModificadoPor(usuario);
            sJpa.edit(seccion);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminar(Seccion seccion){
        SeccionJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SeccionJpaController(emf);
            sJpa.destroy(seccion.getId());
            this.listar();
        } catch (Exception e) {
            
        }
    }
    
    public void listar(){
        SeccionJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SeccionJpaController(emf);
            Secciones = sJpa.findSeccionEntities();
        } catch (Exception e) {
            
        }
    }
    
    public void getSessionById(Seccion sec){
        SeccionJpaController sJpa;
        Seccion secTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SeccionJpaController(emf);
            secTemp = sJpa.findSeccion(sec.getId());
            if(secTemp != null){
                this.seccion = secTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            
        }
    }
    
    public void limpiar(){
        this.seccion.setCodigo("");
        this.seccion.setNombre("");
        this.seccion.setCreadoPor(null);
        this.seccion.setId(0);
    }
            
}
