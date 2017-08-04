/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.SubSerie;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.entities.UnidadDocumental;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.UnidadDocumentalJpaController;
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
public class UnidadDocumentalBean extends BaseBean implements Serializable {

    private static final long SerialVersionUID = 1L;
    
    private UnidadDocumental unidadDocumental = new UnidadDocumental();
    private List<UnidadDocumental> UnidadesDocumentales;
    private String accion;

    public UnidadDocumental getUnidadDocumental() {
        return unidadDocumental;
    }

    public void setUnidadDocumental(UnidadDocumental unidadDocumental) {
        this.unidadDocumental = unidadDocumental;
    }

    public List<UnidadDocumental> getUnidadesDocumentales() {
        return UnidadesDocumentales;
    }

    public void setUnidadesDocumentales(List<UnidadDocumental> unidadesDocumentales) {
        this.UnidadesDocumentales = unidadesDocumentales;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public UnidadDocumentalBean() {
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
        UnidadDocumentalJpaController udJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            udJpa = new UnidadDocumentalJpaController(emf);
            this.unidadDocumental.setFechaCreacion(new Date());
            this.unidadDocumental.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.unidadDocumental.setCreadoPor(usuario);
            udJpa.create(unidadDocumental);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void modificar() throws Exception {
        UnidadDocumentalJpaController udJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            udJpa = new UnidadDocumentalJpaController(emf);
            this.unidadDocumental.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.unidadDocumental.setModificadoPor(usuario);
            udJpa.edit(unidadDocumental);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminar(UnidadDocumental unidadDocumental) throws Exception{
        UnidadDocumentalJpaController udJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            udJpa = new UnidadDocumentalJpaController(emf);
            udJpa.destroy(unidadDocumental.getId());
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void listar() throws Exception {
        UnidadDocumentalJpaController udJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            udJpa = new UnidadDocumentalJpaController(emf);
            UnidadesDocumentales = udJpa.findUnidadDocumentalEntities();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void getUnidadDocumentalById(UnidadDocumental unidadDocumetal) throws Exception {
        UnidadDocumentalJpaController udJpa;
        UnidadDocumental udTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            udJpa = new UnidadDocumentalJpaController(emf);
            udTemp = udJpa.findUnidadDocumental(unidadDocumetal.getId());
            if(udTemp !=null){
                this.unidadDocumental = udTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
     public void getUnidadDocumentalBySubSerie( SubSerie subSerie){
        UnidadDocumentalJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new UnidadDocumentalJpaController(emf);
            UnidadesDocumentales = sJpa.findUnidadDocumentalBySubSerie(subSerie);
        } catch (Exception e) {
             Logger.getLogger(SeccionSubSeccionBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
