/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.bean;

import com.sucomunicacion.gedsys.utils.JpaUtils;
import com.sucomunicacion.gedsys.entities.Departamentos;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.model.DepartamentosJpaController;
import com.sucomunicacion.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
public class DepartamentoBean implements Serializable {

    private static final long SerialVersionUID = 1L;
    
    private Departamentos departamento = new Departamentos();
    private List<Departamentos> departamentos;
    private String accion;

    public Departamentos getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamentos departamento) {
        this.departamento = departamento;
    }

    public List<Departamentos> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentoes(List<Departamentos> departamentos) {
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
        DepartamentosJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory();
            ssJpa = new DepartamentosJpaController(emf);
            this.departamento.setFechaCreacion(new Date());
            this.departamento.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.departamento.setCreadoPor(usuario.getNombres() + " " + usuario.getApelidos());
            ssJpa.create(departamento);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void modificar() throws Exception {
        DepartamentosJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory();
            ssJpa = new DepartamentosJpaController(emf);
            this.departamento.setFechaCreacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.departamento.setModificadoPor(usuario.getNombres() + " " + usuario.getApelidos());
            ssJpa.edit(departamento);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminar(Departamentos departamento) throws Exception{
        DepartamentosJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory();
            ssJpa = new DepartamentosJpaController(emf);
            ssJpa.destroy(departamento.getId());
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void listar() throws Exception {
        DepartamentosJpaController ssJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory();
            ssJpa = new DepartamentosJpaController(emf);
            departamentos = ssJpa.findDepartamentosEntities();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void getDepartamentoById(Departamentos departamento) throws Exception {
        DepartamentosJpaController ssJpa;
        Departamentos departamentoTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory();
            ssJpa = new DepartamentosJpaController(emf);
            departamentoTemp = ssJpa.findDepartamentos(departamento.getId());
            if(departamentoTemp !=null){
                this.departamento = departamentoTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
}
