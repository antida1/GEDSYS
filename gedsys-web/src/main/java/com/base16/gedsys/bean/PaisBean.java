/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.entities.Pais;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.PaisJpaController;
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

@ManagedBean
@ViewScoped
public class PaisBean extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Pais pais = new Pais();
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

    public void procesar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            switch (accion) {
                case "Crear":
                    crear();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Países", "¡Se ha creado el país exitoxamente!"));
                    break;
                case "Modificar":
                    modificar();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Países", "¡Se ha modificado el país exitoxamente!"));
                    break;
            }
        } catch (Exception e) {
            this.addMessage( new FacesMessage(FacesMessage.SEVERITY_ERROR, "Países", e.getMessage()));
            Logger.getLogger(EntidadBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void crear() throws Exception {
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

    private void modificar() throws Exception {
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

    public void eliminar(Pais pais) {
        PaisJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PaisJpaController(emf);
            sJpa.destroy(pais.getId());
            this.listar();
            this.addMessage( new FacesMessage(FacesMessage.SEVERITY_INFO, "Países", "¡Se ha eliminado el país exitosamente!"));
        } catch (Exception e) {
            this.addMessage( new FacesMessage(FacesMessage.SEVERITY_ERROR, "País", e.getMessage()));
            Logger.getLogger(EntidadBean.class.getName()).log(Level.SEVERE, e.getMessage());

        }
    }

    public void listar() {
        PaisJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PaisJpaController(emf);
            Paises = sJpa.findPaisEntities();
        } catch (Exception e) {

        }
    }

    public void getPaisById(Pais pais) {
        PaisJpaController sJpa;
        Pais paisTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PaisJpaController(emf);
            paisTemp = sJpa.findPais(pais.getId());
            if (paisTemp != null) {
                this.pais = paisTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {

        }
    }

    public void limpiar() {
        this.pais.setCodigo("");
        this.pais.setNombre("");
        this.pais.setCreadoPor(null);
        this.pais.setId(0);
    }
}
