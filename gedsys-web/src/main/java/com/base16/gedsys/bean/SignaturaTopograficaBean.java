/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.SignaturaTopografica;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.SignaturaTopograficaJpaController;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import org.primefaces.event.NodeSelectEvent;

/**
 *
 * @author rober
 */ 
@ViewScoped
@Named
public class SignaturaTopograficaBean extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private SignaturaTopografica signaturaTopografica = new SignaturaTopografica();
    private List<SignaturaTopografica> signaturasTopograficas = new LinkedList<>();
    private String accion;
    private SignaturaTopografica selectNode;

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
        //this.limpiar();
        this.accion = accion;
    }
    
    public SignaturaTopografica getSignaturaSelected(){
        return this.selectNode;
    }
    
    public SignaturaTopograficaBean() {
        
    }
    
    public void procesar(){
        try {
            switch(accion){
                case "Crear":
                    crear();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Signatura Topográfica", "¡Signatura Topográfica creada!"));
                    limpiar();
                    break;
                case "Modificar":
                    modificar();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Signatura Topográfica", "¡Signatura Topográfica modificada!"));
                    limpiar();
                    break;
            }
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Signatura Topográfica", e.getMessage()));
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void crear() {
        SignaturaTopograficaJpaController sJpa;
        try {
            EntityManagerFactory emf =  JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SignaturaTopograficaJpaController(emf);
            
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.signaturaTopografica.setDependeDe(selectNode);
            this.signaturaTopografica.setFechaCracion(new Date());
            this.signaturaTopografica.setCreadoPor(usuario);
            sJpa.create(signaturaTopografica);
            this.listar();
        } catch (Exception e) {
            Logger.getLogger(SignaturaTopograficaBean.class.getName()).log(Level.SEVERE, null, e);
        }
   
    }

    private void modificar() {
        SignaturaTopograficaJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SignaturaTopograficaJpaController(emf);
            this.signaturaTopografica.setFechaCracion(new Date());
            //this.signaturaTopografica.setDependeDe(selectNode);
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.signaturaTopografica.setFechaModificacion(new Date());
            this.signaturaTopografica.setModificadoPor(usuario);
            sJpa.edit(signaturaTopografica);
            this.listar();
        } catch (Exception e) {
            Logger.getLogger(SignaturaTopograficaBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void eliminar(SignaturaTopografica signaturaTopografica){
        SignaturaTopograficaJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SignaturaTopograficaJpaController(emf);
            sJpa.destroy(signaturaTopografica.getId());
            this.listar();
            this.addMessage( new FacesMessage(FacesMessage.SEVERITY_INFO, "Signatura Topográfica", "¡Signatura Topográfica Eliminada!"));
        } catch (NonexistentEntityException e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Signatura Topográfica", e.getMessage()));
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
    
    public void getSignaturaTopograficaByDependencia( SignaturaTopografica sig){
        SignaturaTopograficaJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SignaturaTopograficaJpaController(emf);
            signaturasTopograficas = sJpa.findSignaturaTopograficaByDependencia(sig);
        } catch (Exception e) {
             Logger.getLogger(SignaturaTopograficaBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void getSignaturaTopograficaRoots(){
        SignaturaTopograficaJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new SignaturaTopograficaJpaController(emf);
            signaturasTopograficas = sJpa.findSignaturaTopograficaRoots();
        } catch (Exception e) {
             Logger.getLogger(SignaturaTopograficaBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void getSignaturaTopogrficaByUsuario(Usuario usuario){
        
    }
    
     public void selectDependencia(NodeSelectEvent event) {
        selectNode = (SignaturaTopografica) event.getTreeNode().getData();
    }
    
    public void limpiar() {
        this.selectNode = null;
        this.signaturaTopografica = null;
        this.signaturaTopografica =  new SignaturaTopografica();
    }
    
}
