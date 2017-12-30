/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.entities.DiaFestivo;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.DiaFestivoJpaController;
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
public class DiaFestivoBean extends BaseBean implements Serializable {

    private static final long SerialVersionUID = 1L;

    private DiaFestivo diaFestivo = new DiaFestivo();
    private List<DiaFestivo> diaFestivos;
    private String accion;

    public DiaFestivo getDiaFestivo() {
        return diaFestivo;
    }

    public void setDiaFestivo(DiaFestivo diaFestivo) {
        this.diaFestivo = diaFestivo;
    }

    public List<DiaFestivo> getDiaFestivos() {
        return diaFestivos;
    }

    public void setDiaFestivoes(List<DiaFestivo> diaFestivos) {
        this.diaFestivos = diaFestivos;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

    public DiaFestivoBean() {
    }

    public void procesar() {
        try {
            switch (accion) {
                case "Crear":
                    crear();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dia Festivo", "Dia Festivo creado!"));
                    break;
                case "Modificar":
                    modificar();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dia Festivo", "Dia Festivo Modificado!"));
                    break;
            }
            RequestContext.getCurrentInstance().execute("PF('diaDialog').hide()");
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dia Festivo", e.getMessage()));
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void crear() throws Exception {
        DiaFestivoJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new DiaFestivoJpaController(emf);
            this.diaFestivo.setFechaCreacion(new Date());
            this.diaFestivo.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.diaFestivo.setCreadoPor(usuario.getId());
            ssJpa.create(diaFestivo);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    private void modificar() throws Exception {
        DiaFestivoJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new DiaFestivoJpaController(emf);
            this.diaFestivo.setFechaCreacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.diaFestivo.setModificadoPor(usuario.getId());
            ssJpa.edit(diaFestivo);
            this.listar();
        } catch (Exception e) {
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, e.getMessage());
            throw e;
        }
    }

    public void eliminar(DiaFestivo diaFestivo) throws Exception {
        DiaFestivoJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new DiaFestivoJpaController(emf);
            ssJpa.destroy(diaFestivo.getId());
            this.listar();
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dia Festivo", "Dia Festivo Eliminado!"));

        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dia Festivo", e.getMessage()));
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listar() throws Exception {
        DiaFestivoJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new DiaFestivoJpaController(emf);
            diaFestivos = ssJpa.findDiaFestivoEntities();
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dia Festivo", e.getMessage()));
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void getDiaFestivoById(DiaFestivo diaFestivo) throws Exception {
        DiaFestivoJpaController ssJpa;
        DiaFestivo diaFestivoTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new DiaFestivoJpaController(emf);
            diaFestivoTemp = ssJpa.findDiaFestivo(diaFestivo.getId());
            if (diaFestivoTemp != null) {
                this.diaFestivo = diaFestivoTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dia Festivo", e.getMessage()));
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void limpiar() {
        this.diaFestivo = null;
        this.diaFestivo = new DiaFestivo();
    }
}
