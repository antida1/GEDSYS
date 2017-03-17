/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.bean;

import com.sucomunicacion.gedsys.utils.JpaUtils;
import com.sucomunicacion.gedsys.entities.SubSeccion;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.model.SubSeccionJpaController;
import com.sucomunicacion.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
@ManagedBean
@ViewScoped
public class SubSeccionBean extends BaseBean implements Serializable {

    private static final long SerialVersionUID = 1L;
    
    private SubSeccion subseccion = new SubSeccion();
    private List<SubSeccion> subsecciones;
    private String accion;

    public SubSeccion getSubseccion() {
        return subseccion;
    }

    public void setSubSeccion(SubSeccion subseccion) {
        this.subseccion = subseccion;
    }

    public List<SubSeccion> getSubsecciones() {
        return subsecciones;
    }

    public void setSubsecciones(List<SubSeccion> subsecciones) {
        this.subsecciones = subsecciones;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public SubSeccionBean() {
    }
    
    public void procesar() {
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
    
    private void crear() throws Exception {
        SubSeccionJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new SubSeccionJpaController(emf);
            this.subseccion.setFechaCreacion(new Date());
            this.subseccion.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.subseccion.setCreadoPor(usuario);
            ssJpa.create(subseccion);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void modificar() throws Exception {
        SubSeccionJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new SubSeccionJpaController(emf);
            this.subseccion.setFechaCreacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.subseccion.setModificadoPor(usuario);
            ssJpa.edit(subseccion);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminar(SubSeccion subseccion) throws Exception{
        SubSeccionJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new SubSeccionJpaController(emf);
            ssJpa.destroy(subseccion.getId());
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void listar() throws Exception {
        SubSeccionJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new SubSeccionJpaController(emf);
            subsecciones = ssJpa.findSubSeccionEntities();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void getSubSeccionById(SubSeccion subsec) throws Exception {
        SubSeccionJpaController ssJpa;
        SubSeccion subsecTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new SubSeccionJpaController(emf);
            subsecTemp = ssJpa.findSubSeccion(subsec.getId());
            if(subsecTemp !=null){
                this.subseccion = subsecTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
}
