/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Cargo;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.fcm.PushFCMNotification;
import com.base16.gedsys.model.CargoJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import org.primefaces.context.RequestContext;

/**
 *
 * @author rober
 */
@ManagedBean
@ViewScoped
public class CargoBean extends BaseBean implements Serializable {

    private static final long SerialVersionUID = 1L;

    private Cargo cargo = new Cargo();
    private List<Cargo> cargos;
    private String accion;

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

    public CargoBean() {
    }

    public void procesar() {
        try {
            switch (accion) {
                case "Crear":
                    crear();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargo", "Cargo creado!"));
                    break;
                case "Modificar":
                    modificar();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargo", "Cargo Modificado!"));
                    break;
            }
            RequestContext.getCurrentInstance().execute("PF('cargoDialog').hide()");
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cargo", e.getMessage()));
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void crear() {
        CargoJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CargoJpaController(emf);
            this.cargo.setFechaCreacion(new Date());
            this.cargo.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.cargo.setCreadoPor(usuario);
            cJpa.create(cargo);
            this.listar();
        } catch (Exception e) {
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, e.getMessage());
            throw e;
        }
    }

    private void modificar() throws Exception {
        CargoJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CargoJpaController(emf);
            this.cargo.setFechaCreacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.cargo.setModificadoPor(usuario);
            cJpa.edit(cargo);
            this.listar();
        } catch (Exception e) {
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, e.getMessage());
            throw e;
        }
    }

    public void eliminar(Cargo cargo) {
        CargoJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CargoJpaController(emf);
            cJpa.destroy(cargo.getId());
            this.listar();
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargo", "Cargo Eliminado!"));

        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cargo", e.getMessage()));
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listar() {
        CargoJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CargoJpaController(emf);
            cargos = cJpa.findCargoEntities();
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cargo", e.getMessage()));
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void getCargoById(Cargo cargo) {
        FacesContext context = FacesContext.getCurrentInstance();
        CargoJpaController cJpa;
        Cargo cargoTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CargoJpaController(emf);
            cargoTemp = cJpa.findCargo(cargo.getId());
            if (cargoTemp != null) {
                this.cargo = cargoTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cargo", e.getMessage()));
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void limpiar() {
        this.cargo = null;
        this.cargo = new Cargo();
    }
}
