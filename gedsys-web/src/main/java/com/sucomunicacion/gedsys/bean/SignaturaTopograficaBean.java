/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.bean;

import com.sucomunicacion.gedsys.entities.SignaturaTopografica;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.model.SignaturaTopograficaJpaController;
import com.sucomunicacion.gedsys.model.exceptions.NonexistentEntityException;
import com.sucomunicacion.gedsys.utils.JpaUtils;
import com.sucomunicacion.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
@ManagedBean 
@ViewScoped
public class SignaturaTopograficaBean extends BaseBean implements Serializable {

    
    private SignaturaTopografica signaturaTopografica = new SignaturaTopografica();
    private List<SignaturaTopografica> signaturasTopograficas;
    private String accion;

    public SignaturaTopografica getSignaturaTopografica() {
        return signaturaTopografica;
    }

    public void setSignaturaTopografica(SignaturaTopografica signaturaTopografica) {
        this.signaturaTopografica = signaturaTopografica;
    }

    public List<SignaturaTopografica> getSignaturasTopograficas() {
        return signaturasTopograficas;
    }

    public void setSignaturasTopograficas(List<SignaturaTopografica> signaturasTopograficas) {
        this.signaturasTopograficas = signaturasTopograficas;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
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

    public void crear() {
        SignaturaTopograficaJpaController sJpa;
        try {
            EntityManagerFactory emf =  JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SignaturaTopograficaJpaController(emf);
            
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            
            this.signaturaTopografica.setFechaCracion(new Date());
            this.signaturaTopografica.setCreadoPor(usuario);
            sJpa.create(signaturaTopografica);
            this.listar();
            
        } catch (Exception e) {
            Logger.getLogger(SignaturaTopograficaBean.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }

    public void modificar() {
        SignaturaTopograficaJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SignaturaTopograficaJpaController(emf);
            this.signaturaTopografica.setFechaCracion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            sJpa.edit(signaturaTopografica);
            this.listar();
        } catch (Exception e) {
            Logger.getLogger(SignaturaTopograficaBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void eliminar(){
        SignaturaTopograficaJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SignaturaTopograficaJpaController(emf);
            sJpa.destroy(signaturaTopografica.getId());
            this.listar();
        } catch (NonexistentEntityException e) {
            Logger.getLogger(SignaturaTopograficaBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void listar() {
        SignaturaTopograficaJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SignaturaTopograficaJpaController(emf);
            signaturasTopograficas = sJpa.findSignaturaTopograficaEntities();
        } catch (Exception e) {
            Logger.getLogger(SignaturaTopograficaBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void  getSignaturaTopograficaById(SignaturaTopografica sig) {
        SignaturaTopograficaJpaController sJpa;
        SignaturaTopografica sigTemp;
        try {
            EntityManagerFactory emf  = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SignaturaTopograficaJpaController(emf);
            sigTemp = sJpa.findSignaturaTopografica(sig.getId());
            if(sigTemp != null){
                this.signaturaTopografica = sigTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            Logger.getLogger(SignaturaTopograficaBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void limpiar() {
        this.signaturaTopografica = null;
    }
    
}
