/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.CamposPlantilla;
import com.base16.gedsys.entities.Modulo;
import com.base16.gedsys.entities.PlantillaDocumental;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.CamposPlantillaJpaController;
import com.base16.gedsys.model.PlantillaDocumentalJpaController;
import com.base16.gedsys.system.ModuloBean;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */

@ManagedBean
@ViewScoped
public class PlantillaBean extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private PlantillaDocumental plantillaDocumental = new PlantillaDocumental();
    private List<PlantillaDocumental> plantillasDocumentales;
    private List<Modulo> modulos;
    private List<CamposPlantilla> camposPlantilla;

    private Modulo modulo;
    private String accion;

    @PostConstruct
    public void init() {
        try {
            ModuloBean mb = new ModuloBean();
            mb.listar();
            this.modulos = mb.getModulos();
        } catch (Exception ex) {
            Logger.getLogger(RecepcionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public PlantillaDocumental getPlantillaDocumental() {
        return plantillaDocumental;
    }

    public void setPlantillaDocumental(PlantillaDocumental plantillaDocumental) {
        this.plantillaDocumental = plantillaDocumental;
    }

    public List<PlantillaDocumental> getPlantillasDocumentales() {
        return plantillasDocumentales;
    }

    public void setPlantillasDocumentales(List<PlantillaDocumental> plantillasDocumentales) {
        this.plantillasDocumentales = plantillasDocumentales;
    }

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    public List<CamposPlantilla> getCamposPlantilla() {
        return camposPlantilla;
    }

    public void setModuloFields(List<CamposPlantilla> moduloFields) {
        this.camposPlantilla = moduloFields;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public void procesar() {
        try {
            switch (accion) {
                case "Crear":
                    crear();
                    break;
                case "Modificar":
                    modificar();
                    break;
            }
        } catch (Exception e) {
            Logger.getLogger(PlantillaBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void crear() {
        PlantillaDocumentalJpaController pJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            pJpa = new PlantillaDocumentalJpaController(emf);

            Usuario usuario = (Usuario) SessionUtils.getUsuario();

            this.plantillaDocumental.setFechaCreacion(new Date());
            this.plantillaDocumental.setCreadoPor(usuario);
            pJpa.create(plantillaDocumental);

        } catch (Exception e) {
            Logger.getLogger(PlantillaBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void modificar() {
        PlantillaDocumentalJpaController pJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            pJpa = new PlantillaDocumentalJpaController(emf);
            this.plantillaDocumental.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.plantillaDocumental.setModificadoPor(usuario);
            pJpa.edit(plantillaDocumental);
            this.listar();
        } catch (Exception e) {
            Logger.getLogger(PlantillaBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void eliminar(PlantillaDocumental plantilla) {

        PlantillaDocumentalJpaController pJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            pJpa = new PlantillaDocumentalJpaController(emf);
            pJpa.destroy(plantilla.getId());
            this.listar();
        } catch (Exception e) {
            Logger.getLogger(PlantillaBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void listar() {
        PlantillaDocumentalJpaController pJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            pJpa = new PlantillaDocumentalJpaController(emf);
            plantillasDocumentales = pJpa.findPlantillaDocumentalEntities();
        } catch (Exception e) {
            Logger.getLogger(PlantillaBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void getPlantillaById(PlantillaDocumental plantilla) {
        PlantillaDocumentalJpaController pJpa;
        PlantillaDocumental plantillaTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            pJpa = new PlantillaDocumentalJpaController(emf);
            plantillaTemp = pJpa.findPlantillaDocumental(plantilla.getId());
            if (plantillaTemp != null) {
                this.plantillaDocumental = plantillaTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
        }
    }

    public void onModuleChange() {
        if (modulo != null) {
            CamposPlantillaJpaController cpJpa;
            try {
                EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
                cpJpa = new CamposPlantillaJpaController(emf);
                camposPlantilla = cpJpa.findCamposPlantillaByModulo(modulo);
            } catch (Exception e) {
                throw e;
            }
        } else {
            camposPlantilla = new ArrayList<CamposPlantilla>();
        }
    }

    private void limpiar() {
        this.plantillaDocumental = null;
        this.plantillaDocumental = new PlantillaDocumental();
    }
}
