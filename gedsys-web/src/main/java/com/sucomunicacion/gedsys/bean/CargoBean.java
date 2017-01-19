/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.bean;

import com.sucomunicacion.gedsys.entities.Cargo;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.model.CargoJpaController;
import com.sucomunicacion.gedsys.utils.JpaUtils;
import com.sucomunicacion.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;

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
        this.accion = accion;
    }
    
    public CargoBean() {
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
        CargoJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CargoJpaController(emf);
            this.cargo.setFechaCreacion(new Date());
            this.cargo.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.cargo.setCreadoPor(usuario.getNombres() + " " + usuario.getApelidos());
            cJpa.create(cargo);
            this.listar();
        } catch (Exception e) {
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
            this.cargo.setModificadoPor(usuario.getNombres() + " " + usuario.getApelidos());
            cJpa.edit(cargo);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminar(Cargo cargo) throws Exception{
        CargoJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CargoJpaController(emf);
            cJpa.destroy(cargo.getId());
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void listar() throws Exception {
        CargoJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CargoJpaController(emf);
            cargos = cJpa.findCargoEntities();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void getCargoById(Cargo cargo) throws Exception {
        CargoJpaController cJpa;
        Cargo cargoTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CargoJpaController(emf);
            cargoTemp = cJpa.findCargo(cargo.getId());
            if(cargoTemp !=null){
                this.cargo = cargoTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
}
