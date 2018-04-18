/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Serie;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.entities.SubSerie;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.SubSerieJpaController;
import com.base16.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
@ManagedBean
@ViewScoped
public class SubSerieBean extends BaseBean implements Serializable {

    private static final long SerialVersionUID = 1L;
    
    private SubSerie serie = new SubSerie();
    private List<SubSerie> subseries;
    private String accion;

    public SubSerie getSubSerie() {
        return serie;
    }

    public void setSubSerie(SubSerie serie) {
        this.serie = serie;
    }

    public List<SubSerie> getSubSeries() {
        return subseries;
    }

    public void setSubSeries(List<SubSerie> subseries) {
        this.subseries = subseries;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public SubSerieBean() {
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
        SubSerieJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new SubSerieJpaController(emf);
            this.serie.setFechaCreacion(new Date());
            this.serie.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.serie.setCreadoPor(usuario);
            ssJpa.create(serie);
            this.listar();
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Subseries", "¡Se ha creado la subserie exitosamente!" ));
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void modificar() throws Exception {
        SubSerieJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new SubSerieJpaController(emf);
            this.serie.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.serie.setModificadoPor(usuario);
            ssJpa.edit(serie);
            this.listar();
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Subseries", "¡Se ha modificado la subserie exitosamente!" ));
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminar(SubSerie serie) throws Exception{
        SubSerieJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new SubSerieJpaController(emf);
            ssJpa.destroy(serie.getId());
            this.listar();
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Subseries", "¡Se ha eliminado la subserie exitosamente!" ));
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void listar() throws Exception {
        SubSerieJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new SubSerieJpaController(emf);
            subseries = ssJpa.findSubSerieEntities();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void getSubSerieById(SubSerie serie) throws Exception {
        SubSerieJpaController ssJpa;
        SubSerie serieTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new SubSerieJpaController(emf);
            serieTemp = ssJpa.findSubSerie(serie.getId());
            if(serieTemp !=null){
                this.serie = serieTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
     public void getSubSerieBySerie( Serie serie){
        SubSerieJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SubSerieJpaController(emf);
            subseries = sJpa.findSubSerieBySerie(serie);
        } catch (Exception e) {
             Logger.getLogger(SeccionSubSeccionBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
