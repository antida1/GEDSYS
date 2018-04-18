/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.system;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.bean.CargoBean;
import com.base16.gedsys.entities.Grupo;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.GrupoJpaController;
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
import javax.persistence.EntityManagerFactory;
import org.primefaces.context.RequestContext;

/**
 *
 * @author rober
 */
@ManagedBean
@ViewScoped
public class GrupoBean extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Grupo grupo = new Grupo();
    private List<Grupo> Grupos;
    private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

    public List<Grupo> getGrupos() {
        return Grupos;
    }

    public void setGrupos(List<Grupo> Grupos) {
        this.Grupos = Grupos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public void procesar() {
        try {
            switch (accion) {
                case "Crear":
                    crear();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupos", "¡Se ha creado el grupo exitosamente!"));
                    break;
                case "Modificar":
                    modificar();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupos", "¡Se ha modificado el grupo exitosamente!"));
                    break;
            }
            RequestContext.getCurrentInstance().execute("PF('grupoDialog').hide()");
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Grupo", e.getMessage()));
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void crear() throws Exception {
        GrupoJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new GrupoJpaController(emf);

            Usuario usuario = (Usuario) SessionUtils.getUsuario();

            this.grupo.setFechaCreacion(new Date());
            this.grupo.setFechaModificacion(new Date());
            this.grupo.setCreadoPor(usuario.getId());
            sJpa.create(grupo);
            this.listar();
        } catch (Exception e) {
            Logger.getLogger(GrupoBean.class.getName()).log(Level.SEVERE, e.getMessage());
            throw e;
        }
    }

    private void modificar() throws Exception {
        GrupoJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new GrupoJpaController(emf);
            this.grupo.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.grupo.setModificadoPor(usuario);
            sJpa.edit(grupo);
            this.listar();
        } catch (Exception e) {
            Logger.getLogger(GrupoBean.class.getName()).log(Level.SEVERE, e.getMessage());
            throw e;
        }
    }

    public void eliminar(Grupo grupo) {
        GrupoJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new GrupoJpaController(emf);
            sJpa.destroy(grupo.getId());
            this.listar();
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupos", "¡Se ha eliminado el grupo exitosamente!"));
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Grupos", e.getMessage()));
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, e.getMessage());

        }
    }

    public void listar() {
        GrupoJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new GrupoJpaController(emf);
            Grupos = sJpa.findGrupoEntities();
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Grupos", e.getMessage()));
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, e.getMessage());

        }
    }

    public void getGrupoById(Grupo grupo) {
        GrupoJpaController sJpa;
        Grupo paisTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new GrupoJpaController(emf);
            paisTemp = sJpa.findGrupo(grupo.getId());
            if (paisTemp != null) {
                this.grupo = paisTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Grupos", e.getMessage()));
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, e.getMessage());

        }
    }

    public void getGrupoByNombre(String nombre) {
        GrupoJpaController gJpa;
        Grupo grupoTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            gJpa = new GrupoJpaController(emf);
            grupoTemp = gJpa.findGrupoByNombre(nombre);
            if (grupoTemp != null) {
                this.grupo = grupoTemp;
            }
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Grupos", e.getMessage()));
            Logger.getLogger(CargoBean.class.getName()).log(Level.SEVERE, e.getMessage());

        }
    }

    public void limpiar() {
        this.grupo = null;
        this.grupo = new Grupo();
    }

}
