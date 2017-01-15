/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.bean;

import com.sucomunicacion.gedsys.utils.JpaUtils;
import com.sucomunicacion.gedsys.entities.Serie;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.model.SerieJpaController;
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
public class SerieBean extends BaseBean implements Serializable {

    private static final long SerialVersionUID = 1L;
    
    private Serie serie = new Serie();
    private List<Serie> series;
    private String accion;

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public SerieBean() {
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
        SerieJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new SerieJpaController(emf);
            this.serie.setFechaCreacion(new Date());
            this.serie.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.serie.setCreadoPor(usuario.getNombres() + " " + usuario.getApelidos());
            ssJpa.create(serie);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void modificar() throws Exception {
        SerieJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new SerieJpaController(emf);
            this.serie.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.serie.setModificadoPor(usuario.getNombres() + " " + usuario.getApelidos());
            ssJpa.edit(serie);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminar(Serie serie) throws Exception{
        SerieJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new SerieJpaController(emf);
            ssJpa.destroy(serie.getId());
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void listar() throws Exception {
        SerieJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new SerieJpaController(emf);
            series = ssJpa.findSerieEntities();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void getSerieById(Serie serie) throws Exception {
        SerieJpaController ssJpa;
        Serie serieTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new SerieJpaController(emf);
            serieTemp = ssJpa.findSerie(serie.getId());
            if(serieTemp !=null){
                this.serie = serieTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
}
