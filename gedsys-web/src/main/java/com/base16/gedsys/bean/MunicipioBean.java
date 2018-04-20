/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Departamento;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.entities.Municipio;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.MunicipioJpaController;
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

/**
 *
 * @author rober
 * @author Modificación Lina David
 */
@ManagedBean
@ViewScoped
public class MunicipioBean extends BaseBean implements Serializable {

    private static final long SerialVersionUID = 1L;
    
    private Municipio municipio = new Municipio();
    private List<Municipio> municipios;
    private String accion;

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipioes(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }
    
    public MunicipioBean() {
    }
    
    public void procesar() {
        try {
            switch(accion){
                case "Crear":
                    crear();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Municipios", "¡Se ha creado el municipio exitosamente!"));
                    break;
                case "Modificar":
                    modificar();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Municipios", "¡Se ha modificado el municipio exitosamente!"));
                    break;
            }
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Municipios", e.getMessage()));
            Logger.getLogger(DepartamentoBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void crear() throws Exception {
        MunicipioJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new MunicipioJpaController(emf);
            this.municipio.setFechaCreacion(new Date());
            this.municipio.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.municipio.setCreadoPor(usuario);
            ssJpa.create(municipio);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void modificar() throws Exception {
        MunicipioJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new MunicipioJpaController(emf);
            this.municipio.setFechaCreacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.municipio.setModificadoPor(usuario);
            ssJpa.edit(municipio);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminar(Municipio municipio) throws Exception{
        MunicipioJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new MunicipioJpaController(emf);
            ssJpa.destroy(municipio.getId());
            this.listar();
              this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Municipios", "¡Se ha eliminado el municipio exitosamente!"));
        } catch (Exception e) {
            Logger.getLogger(MunicipioBean.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
    }
    
    public void listar() throws Exception {
        MunicipioJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new MunicipioJpaController(emf);
            municipios = ssJpa.findMunicipioEntities();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void getMunicipioById(Municipio municipio) throws Exception {
        MunicipioJpaController ssJpa;
        Municipio municipioTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new MunicipioJpaController(emf);
            municipioTemp = ssJpa.findMunicipio(municipio.getId());
            if(municipioTemp !=null){
                this.municipio = municipioTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    public void getMunicipiosByDepartamento(Departamento departamento) throws Exception {
        MunicipioJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new MunicipioJpaController(emf);
            municipios = ssJpa.findMunicipiosByDepartamento(departamento);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void limpiar(){
        this.municipio = new Municipio();
    }
}
