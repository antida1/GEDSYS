/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.entities.TipoDocumental;
import com.base16.gedsys.entities.UnidadDocumental;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.TipoDocumentalJpaController;
import com.base16.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TipoDocumentalBean extends BaseBean implements Serializable {

    private static final long SerialVersionUID = 1L;
    
    private TipoDocumental tipoDocumental = new TipoDocumental();
    private List<TipoDocumental> tiposDocumentales;
    private String accion;

    public TipoDocumental getTipoDocumental() {
        return tipoDocumental;
    }

    public void setTipoDocumental(TipoDocumental tipoDocumental) {
        this.tipoDocumental = tipoDocumental;
    }

    public List<TipoDocumental> getTiposDocumentales() {
        return tiposDocumentales;
    }

    public void setTiposDocumentales(List<TipoDocumental> tiposDocumentales) {
        this.tiposDocumentales = tiposDocumentales;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public TipoDocumentalBean() {
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
        TipoDocumentalJpaController tpJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            tpJpa = new TipoDocumentalJpaController(emf);
            this.tipoDocumental.setFechaCreacion(new Date());
            this.tipoDocumental.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.tipoDocumental.setCreadoPor(usuario);
            tpJpa.create(tipoDocumental);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void modificar() throws Exception {
        TipoDocumentalJpaController tpJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            tpJpa = new TipoDocumentalJpaController(emf);
            this.tipoDocumental.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.tipoDocumental.setModificadoPor(usuario);
            tpJpa.edit(tipoDocumental);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminar(TipoDocumental TipoDocumental) throws Exception{
        TipoDocumentalJpaController tpJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            tpJpa = new TipoDocumentalJpaController(emf);
            tpJpa.destroy(TipoDocumental.getId());
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void listar() throws Exception {
        TipoDocumentalJpaController tpJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            tpJpa = new TipoDocumentalJpaController(emf);
            tiposDocumentales = tpJpa.findTipoDocumentalEntities();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void getTipoDocumentalById(TipoDocumental unidadDocumetal) throws Exception {
        TipoDocumentalJpaController tpJpa;
        TipoDocumental tdTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            tpJpa = new TipoDocumentalJpaController(emf);
            tdTemp = tpJpa.findTipoDocumental(unidadDocumetal.getId());
            if(tdTemp !=null){
                this.tipoDocumental = tdTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void getTipoDocumentalByUnidadDocumental( UnidadDocumental unidadDocumental){
        TipoDocumentalJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new TipoDocumentalJpaController(emf);
            tiposDocumentales = sJpa.findUnidadDocumentalBySubSerie(unidadDocumental);
        } catch (Exception e) {
             Logger.getLogger(SeccionSubSeccionBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
