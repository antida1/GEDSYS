/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.system;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.bean.ConsecutivoBean;
import com.base16.gedsys.entities.Acl;
import com.base16.gedsys.entities.Grupo;
import com.base16.gedsys.entities.Modulo;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.AclJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
@ManagedBean
@SessionScoped
public class AclBean extends BaseBean implements Serializable {

    private static final long SerialVersionUID = 1L;
    private Acl acl = new Acl();
    private List<Acl> acls;
    private String accion;
    private Grupo grupo;

    /**
     * Creates a new instance of AclBean
     */
    public AclBean() {
    }

    public Acl getAcl() {
        return acl;
    }

    public void setAcl(Acl acl) {
        this.acl = acl;
    }

    public List<Acl> getAcls() {
        return acls;
    }

    public void setAcls(List<Acl> acls) {
        this.acls = acls;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void procesar() {
        try {
            switch (accion) {
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

    private void crear() {
        AclJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new AclJpaController(emf);
            this.acl.setFechaCreacion(new Date());
            this.acl.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.acl.setCreadoPor(usuario);
            cJpa.create(acl);
            this.listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    private void modificar() {
        AclJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new AclJpaController(emf);
            this.acl.setFechaCreacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.acl.setModificadoPor(usuario);
            cJpa.edit(acl);
             this.grupo =  acl.getGrupo();
            this.onGroupChange();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void eliminar(Acl acl) {
        AclJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new AclJpaController(emf);
            cJpa.destroy(acl.getId());
            this.grupo =  acl.getGrupo();
            this.onGroupChange();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listar() {
        AclJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new AclJpaController(emf);
            acls = cJpa.findAclEntities();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void getAclById(Acl acl) {
        AclJpaController cJpa;
        Acl aclTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new AclJpaController(emf);
            aclTemp = cJpa.findAcl(acl.getId());
            if (aclTemp != null) {
                this.acl = aclTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void onGroupChange() {
        AclJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new AclJpaController(emf);
            acls = cJpa.findAclByGrupo(this.grupo);
            if (acls.isEmpty()) {
                populateAcl(this.grupo);
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
            Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void getAclByModuloGrupo(Modulo modulo, Grupo grupo) {
        AclJpaController aJpa;
        try {

        } catch (Exception e) {
        }
    }

    public void populateAcl(Grupo grupo) {
        List<Modulo> modulos;
        ModuloBean moduloBean = new ModuloBean();
        moduloBean.listar();;
        modulos = moduloBean.getModulos();
        AclJpaController cJpa;
        for (Modulo modulo : modulos) {
            try {
                EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
                cJpa = new AclJpaController(emf);
                Acl _acl;
                _acl = new Acl();
                _acl.setGrupo(grupo);
                _acl.setModulo(modulo);
                _acl.setFechaCreacion(new Date());
                _acl.setFechaModificacion(new Date());
                Usuario usuario = (Usuario) SessionUtils.getUsuario();
                _acl.setCreadoPor(usuario);
                _acl.setCanCreate(Boolean.FALSE);
                _acl.setCanDelete(Boolean.FALSE);
                _acl.setCanExport(Boolean.FALSE);
                _acl.setCanGeneratePDF(Boolean.FALSE);
                _acl.setCanRead(Boolean.FALSE);
                _acl.setCanUpdate(Boolean.FALSE);
                cJpa.create(_acl);
                acls.add(acl);
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
                Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
            }
        }
    }
}
