/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.bean;

import com.sucomunicacion.gedsys.utils.JpaUtils;
import com.sucomunicacion.gedsys.entities.Municipios;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.model.MunicipiosJpaController;
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
public class MunicipioBean extends BaseBean implements Serializable {

    private static final long SerialVersionUID = 1L;
    
    private Municipios municipio = new Municipios();
    private List<Municipios> municipios;
    private String accion;

    public Municipios getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipios municipio) {
        this.municipio = municipio;
    }

    public List<Municipios> getMunicipios() {
        return municipios;
    }

    public void setMunicipioes(List<Municipios> municipios) {
        this.municipios = municipios;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public MunicipioBean() {
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
        MunicipiosJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new MunicipiosJpaController(emf);
            this.municipio.setFechaCreacion(new Date());
            this.municipio.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.municipio.setCreadoPor(usuario.getNombres() + " " + usuario.getApelidos());
            ssJpa.create(municipio);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void modificar() throws Exception {
        MunicipiosJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new MunicipiosJpaController(emf);
            this.municipio.setFechaCreacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.municipio.setModificadoPor(usuario.getNombres() + " " + usuario.getApelidos());
            ssJpa.edit(municipio);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminar(Municipios municipio) throws Exception{
        MunicipiosJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new MunicipiosJpaController(emf);
            ssJpa.destroy(municipio.getId());
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void listar() throws Exception {
        MunicipiosJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new MunicipiosJpaController(emf);
            municipios = ssJpa.findMunicipiosEntities();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void getMunicipioById(Municipios municipio) throws Exception {
        MunicipiosJpaController ssJpa;
        Municipios municipioTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new MunicipiosJpaController(emf);
            municipioTemp = ssJpa.findMunicipios(municipio.getId());
            if(municipioTemp !=null){
                this.municipio = municipioTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
}
