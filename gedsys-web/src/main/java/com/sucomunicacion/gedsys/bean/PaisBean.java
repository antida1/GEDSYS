/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.bean;

import com.sucomunicacion.gedsys.utils.JpaUtils;
import com.sucomunicacion.gedsys.entities.Pais;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.model.PaisJpaController;
import com.sucomunicacion.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;


@ManagedBean
@ViewScoped
public class PaisBean extends BaseBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Pais pais= new Pais();
    private List<Pais> Paises;
    private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }
    public List<Pais> getPaises() {
        return Paises;
    }

    public void setPaises(List<Pais> Paises) {
        this.Paises = Paises;
    }
    
    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
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
        PaisJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PaisJpaController(emf);
            
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            
            this.pais.setFechaCreacion(new Date());
            this.pais.setFechaModificacion(new Date());
            this.pais.setCreadoPor(usuario);
            sJpa.create(pais);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void modificar() throws Exception{
        PaisJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PaisJpaController(emf);
            this.pais.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.pais.setModificadoPor(usuario);
            sJpa.edit(pais);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminar(Pais pais){
        PaisJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PaisJpaController(emf);
            sJpa.destroy(pais.getId());
            this.listar();
        } catch (Exception e) {
            
        }
    }
    
    public void listar(){
        PaisJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PaisJpaController(emf);
            Paises = sJpa.findPaisEntities();
        } catch (Exception e) {
            
        }
    }
    
    public void getPaisById(Pais pais){
        PaisJpaController sJpa;
        Pais paisTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PaisJpaController(emf);
            paisTemp = sJpa.findPais(pais.getId());
            if(paisTemp != null){
                this.pais = paisTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            
        }
    }
    
    public void limpiar(){
        this.pais.setCodigo("");
        this.pais.setNombre("");
        this.pais.setCreadoPor(null);
        this.pais.setId(0);
    }
}
