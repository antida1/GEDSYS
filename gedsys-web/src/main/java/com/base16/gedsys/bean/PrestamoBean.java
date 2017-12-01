/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Prestamo;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.PrestamoJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
@Named(value = "prestamoBean")
@ViewScoped
public class PrestamoBean extends BaseBean implements Serializable {

    /**
     * Creates a new instance of PrestamoBean
     */
    private static final long serialVersionUID = 1L;
    
    private Prestamo prestamo= new Prestamo();
    private List<Prestamo> Prestamos;
    private String accion;
    
    public PrestamoBean() {
    }
    
    

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public List<Prestamo> getPrestamos() {
        return Prestamos;
    }

    public void setPrestamos(List<Prestamo> Prestamos) {
        this.Prestamos = Prestamos;
    }
     
    public void procesar(){
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
    
    private void crear() throws Exception{
        PrestamoJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PrestamoJpaController(emf);
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.prestamo.setFechaCreacion(new Date());
            this.prestamo.setFechaModificacion(new Date());
            this.prestamo.setCreadoPor(usuario);
            sJpa.create(prestamo);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void modificar() throws Exception{
        PrestamoJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PrestamoJpaController(emf);
            this.prestamo.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.prestamo.setModificadoPor(usuario);
            sJpa.edit(prestamo);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminar(Prestamo prestamo){
        PrestamoJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PrestamoJpaController(emf);
            sJpa.destroy(prestamo.getId());
            this.listar();
        } catch (Exception e) {
            
        }
    }
    
    public void listar(){
        PrestamoJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PrestamoJpaController(emf);
            Prestamos = sJpa.findPrestamoEntities();
        } catch (Exception e) {
            
        }
    }
    
    public void getPrestamoById(Prestamo prestamo){
        PrestamoJpaController sJpa;
        Prestamo prestamoTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PrestamoJpaController(emf);
            prestamoTemp = sJpa.findPrestamo(prestamo.getId());
            if(prestamoTemp != null){
                this.prestamo = prestamoTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            
        }
    }
    
    public void limpiar(){
        this.prestamo = new Prestamo();
        this.prestamo.setCreadoPor(null);
        this.prestamo.setId(0);
    }
}
