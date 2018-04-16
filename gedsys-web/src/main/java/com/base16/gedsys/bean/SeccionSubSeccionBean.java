/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.entities.SeccionSubSeccion;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.SeccionSubSeccionJpaController;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
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
import org.primefaces.event.NodeSelectEvent;


@ManagedBean
@ViewScoped
public class SeccionSubSeccionBean extends BaseBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private SeccionSubSeccion seccionSubSeccion= new SeccionSubSeccion();
    private List<SeccionSubSeccion> seccionSubSecciones;
    private SeccionSubSeccion selectNode;
    private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }
    
    public List<SeccionSubSeccion> getSecciones() {
        return seccionSubSecciones;
    }

    public void setSecciones(List<SeccionSubSeccion> Secciones) {
        this.seccionSubSecciones = Secciones;
    }
    
    public SeccionSubSeccion getSeccion() {
        return seccionSubSeccion;
    }

    public void setSeccionSubSeccion(SeccionSubSeccion seccion) {
        this.seccionSubSeccion = seccion;
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
        SeccionSubSeccionJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SeccionSubSeccionJpaController(emf);
            
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            
            this.seccionSubSeccion.setFechaCreacion(new Date());
            this.seccionSubSeccion.setFechaModificacion(new Date());
            this.seccionSubSeccion.setCreadoPor(usuario);
            sJpa.create(seccionSubSeccion);
            this.listar();
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Secciones", "¡Se ha creado la sección correctamente!" ));
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void modificar() throws Exception{
        SeccionSubSeccionJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SeccionSubSeccionJpaController(emf);
            this.seccionSubSeccion.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.seccionSubSeccion.setModificadoPor(usuario);
            sJpa.edit(seccionSubSeccion);
            this.listar();
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Secciones", "¡Se ha modificado la sección correctamente!" ));
        } catch (Exception e) {
            Logger.getLogger(SeccionSubSeccionBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void eliminar(SeccionSubSeccion seccion){
       SeccionSubSeccionJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SeccionSubSeccionJpaController(emf);
            sJpa.destroy(seccion.getId());
            this.listar();
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Secciones", "¡Se ha eliminado la sección correctamente!" ));
        } catch (NonexistentEntityException e) {
            Logger.getLogger(SeccionSubSeccionBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void listar(){
        SeccionSubSeccionJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SeccionSubSeccionJpaController(emf);
            seccionSubSecciones = sJpa.findSeccionSubSeccionEntities();
        } catch (Exception e) {
            Logger.getLogger(SeccionSubSeccionBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void getSessionById(SeccionSubSeccion sec){
        SeccionSubSeccionJpaController sJpa;
        SeccionSubSeccion secTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SeccionSubSeccionJpaController(emf);
            secTemp = sJpa.findSeccionSubSeccion(sec.getId());
            if(secTemp != null){
                this.seccionSubSeccion = secTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            
        }
    }
    
    public void getSeccionSubSeccionByDependencia( SeccionSubSeccion sec){
        SeccionSubSeccionJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SeccionSubSeccionJpaController(emf);
            seccionSubSecciones = sJpa.findSeccionSubSeccionByDependencia(sec);
        } catch (Exception e) {
             Logger.getLogger(SeccionSubSeccionBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void getSeccionSubSeccionRoots(){
        SeccionSubSeccionJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SeccionSubSeccionJpaController(emf);
            seccionSubSecciones = sJpa.findSeccionSubSeccionRoots();
        } catch (Exception e) {
             Logger.getLogger(SeccionSubSeccionBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
     public void selectDependencia(NodeSelectEvent event) {
        selectNode = (SeccionSubSeccion) event.getTreeNode().getData();
    }
    
    public void limpiar(){
        //this.seccionSubSeccion = null;
        this.seccionSubSeccion.setCodigo("");
        this.seccionSubSeccion.setNombre("");
        this.seccionSubSeccion.setCreadoPor(null);
        this.seccionSubSeccion.setId(0);
    }
            
}
    