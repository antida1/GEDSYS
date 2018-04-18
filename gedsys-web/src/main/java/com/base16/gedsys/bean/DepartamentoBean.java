/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.entities.Departamento;
import com.base16.gedsys.entities.Pais;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.DepartamentoJpaController;
import com.base16.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManagerFactory;
import org.primefaces.context.RequestContext;

/**
 *
 * @author rober
 */
@ViewScoped
@Named
public class DepartamentoBean extends BaseBean implements Serializable {

    private static final long SerialVersionUID = 1L;

    private Departamento departamento = new Departamento();
    private List<Departamento> departamentos;
    private String accion;

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

    public DepartamentoBean() {
    }

    public void procesar() {
        try {
            switch (accion) {
                case "Crear":
                    crear();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamentos", "¡Se ha creado el departamento exitosamente!"));
                    break;
                case "Modificar":
                    modificar();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamentos", "¡Se ha modificado el departamento exitosamente!"));
                    break;
            }
            RequestContext.getCurrentInstance().execute("PF('departamentoDialog').hide()");
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Departamentos", e.getMessage()));
            Logger.getLogger(DepartamentoBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void crear() throws Exception {
        DepartamentoJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new DepartamentoJpaController(emf);
            this.departamento.setFechaCreacion(new Date());
            this.departamento.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.departamento.setCreadoPor(usuario);
            ssJpa.create(departamento);
            this.listar();
        } catch (Exception e) {
            Logger.getLogger(DepartamentoBean.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
    }

    private void modificar() throws Exception {
        DepartamentoJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new DepartamentoJpaController(emf);
            this.departamento.setFechaCreacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.departamento.setModificadoPor(usuario);
            ssJpa.edit(departamento);
            this.listar();
        } catch (Exception e) {
            Logger.getLogger(DepartamentoBean.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
    }

    public void eliminar(Departamento departamento) throws Exception {
        DepartamentoJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new DepartamentoJpaController(emf);
            ssJpa.destroy(departamento.getId());
            this.listar();
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamentos", "¡Se ha eliminado el departamento exitosamente!"));

        } catch (Exception e) {
            Logger.getLogger(DepartamentoBean.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
    }

    public void listar() throws Exception {
        DepartamentoJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new DepartamentoJpaController(emf);
            departamentos = ssJpa.findDepartamentoEntities();
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Departamentos", e.getMessage()));
            Logger.getLogger(DepartamentoBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void getDepartamentoById(Departamento departamento) throws Exception {
        DepartamentoJpaController ssJpa;
        Departamento departamentoTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new DepartamentoJpaController(emf);
            departamentoTemp = ssJpa.findDepartamento(departamento.getId());
            if (departamentoTemp != null) {
                this.departamento = departamentoTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Departamentos", e.getMessage()));
            Logger.getLogger(DepartamentoBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void getDepartamentosByPais(Pais pais) throws Exception {
        DepartamentoJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new DepartamentoJpaController(emf);
            departamentos = ssJpa.findDepartamentosByPais(pais);
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Departamentos", e.getMessage()));
            Logger.getLogger(DepartamentoBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void limpiar(){
        this.departamento = new Departamento();
    }

}
