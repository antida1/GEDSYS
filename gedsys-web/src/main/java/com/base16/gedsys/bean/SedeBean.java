/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.entities.Sede;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.SedeJpaController;
import com.base16.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;

@ManagedBean
@ViewScoped
public class SedeBean extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Sede sede = new Sede();
    private List<Sede> Sedes;
    private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

    public List<Sede> getSedes() {
        return Sedes;
    }

    public void setSedes(List<Sede> Sedes) {
        this.Sedes = Sedes;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public void procesar() {
       
        try {
            switch (accion) {
                case "Crear":
                    crear();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sede", "Sede creada exitoxamente!"));
                    break;
                case "Modificar":
                    modificar();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sede", "Sede modificada exitoxamente!"));
                    break;
            }
        } catch (Exception e) {
        }
    }

    private void crear() throws Exception {
        SedeJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SedeJpaController(emf);

            Usuario usuario = (Usuario) SessionUtils.getUsuario();

            this.sede.setFechaCreacion(new Date());
            this.sede.setFechaModificacion(new Date());
            this.sede.setCreadoPor(usuario);
            sJpa.create(sede);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    private void modificar() throws Exception {
        SedeJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SedeJpaController(emf);
            this.sede.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.sede.setModificadoPor(usuario);
            sJpa.edit(sede);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(Sede sede) {
        SedeJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SedeJpaController(emf);
            sJpa.destroy(sede.getId());
            this.listar();
        } catch (Exception e) {

        }
    }

    public void listar() {
        SedeJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SedeJpaController(emf);
            Sedes = sJpa.findSedeEntities();
        } catch (Exception e) {

        }
    }

    public void getSedeById(Sede sede) {
        SedeJpaController sJpa;
        Sede sedeTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SedeJpaController(emf);
            sedeTemp = sJpa.findSede(sede.getId());
            if (sedeTemp != null) {
                this.sede = sedeTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sede", "Sede modificada exitoxamente!"));
        }
    }

    public void limpiar() {
        this.sede.setCodigo("");
        this.sede.setNombre("");
        this.sede.setCreadoPor(null);
        this.sede.setId(0);
    }
}
