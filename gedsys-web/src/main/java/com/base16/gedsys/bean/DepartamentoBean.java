/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.entities.Departamento;
import com.base16.gedsys.entities.Pais;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.DepartamentoJpaController;
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
@ViewScoped
@Named
public class DepartamentoBean extends BaseBean implements Serializable {

    private static final long SerialVersionUID = 1L;
    
    private Departamento departamento = new Departamento();
    private List<Departamento> departamentos;
    private String accion;

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public DepartamentoBean() {
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
        DepartamentoJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new DepartamentoJpaController(emf);
            this.departamento.setFechaCreacion(new Date());
            this.departamento.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.departamento.setCreadoPor(usuario);
            ssJpa.create(departamento);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void modificar() throws Exception {
        DepartamentoJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new DepartamentoJpaController(emf);
            this.departamento.setFechaCreacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.departamento.setModificadoPor(usuario);
            ssJpa.edit(departamento);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminar(Departamento departamento) throws Exception{
        DepartamentoJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new DepartamentoJpaController(emf);
            ssJpa.destroy(departamento.getId());
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void listar() throws Exception {
        DepartamentoJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new DepartamentoJpaController(emf);
            departamentos = ssJpa.findDepartamentoEntities();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void getDepartamentoById(Departamento departamento) throws Exception {
        DepartamentoJpaController ssJpa;
        Departamento departamentoTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new DepartamentoJpaController(emf);
            departamentoTemp = ssJpa.findDepartamento(departamento.getId());
            if(departamentoTemp !=null){
                this.departamento = departamentoTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void getDepartamentosByPais(Pais pais) throws Exception {
        DepartamentoJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            ssJpa = new DepartamentoJpaController(emf);
            departamentos = ssJpa.findDepartamentosByPais(pais);
        } catch (Exception e) {
            throw e;
        }
    }
    
}
