/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.bean;

import com.sucomunicacion.gedsys.utils.JpaUtils;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.model.UsuarioJpaController;
import com.sucomunicacion.gedsys.security.Authentication;
import com.sucomunicacion.gedsys.web.utils.SessionUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author rober
 */
@ManagedBean
@ViewScoped
public class UsuarioBean extends BaseBean implements Serializable {

    private static final long SerialVersionUID = 1L;
    
    private Usuario usuario = new Usuario();
    private List<Usuario> usuarios;
    private String accion;
    private UploadedFile photoFile;

    public UploadedFile getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(UploadedFile photoFile) {
        this.photoFile = photoFile;
    }
            
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public UsuarioBean() {
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
        UsuarioJpaController usrJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            usrJpa = new UsuarioJpaController(emf);
            //this.usuario.setFechaCreacion(new Date());
            //this.usuario.setFechaModificacion(new Date());
            //Usuario usuario = (Usuario) SessionUtils.getUsuario();
            //this.usuario.setCreadoPor(usuario.getNombres() + " " + usuario.getApelidos());
            this.usuario.setClave(Authentication.md5(this.usuario.getClave()));
            this.usuario.setFoto(this.getPhotoName());
            uploadPhoto();
            usrJpa.create(usuario);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void modificar() throws Exception {
        UsuarioJpaController usrJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            usrJpa = new UsuarioJpaController(emf);
            //this.usuario.setFechaModificacion(new Date());
            //Usuario usuario = (Usuario) SessionUtils.getUsuario();
            //this.usuario.setModificadoPor(usuario.getNombres() + " " + usuario.getApelidos());
            uploadPhoto();
            this.usuario.setFoto(this.getPhotoName());
            usrJpa.edit(usuario);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminar(Usuario usuario) throws Exception{
        UsuarioJpaController usrJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            usrJpa = new UsuarioJpaController(emf);
            usrJpa.destroy(usuario.getId());
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void listar() throws Exception {
        UsuarioJpaController usrJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            usrJpa = new UsuarioJpaController(emf);
            usuarios = usrJpa.findUsuarioEntities();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void getUsuarioById(Usuario unidadDocumetal) throws Exception {
        UsuarioJpaController usrJpa;
        Usuario usrTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            usrJpa = new UsuarioJpaController(emf);
            usrTemp = usrJpa.findUsuario(unidadDocumetal.getId());
            if(usrTemp !=null){
                this.usuario = usrTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
    private void uploadPhoto(){
        if(photoFile != null){
            try {
                FacesContext context = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
                String path = servletContext.getRealPath("/resources/images");
                String fileName =  FilenameUtils.getBaseName(photoFile.getFileName());
                String extension = FilenameUtils.getExtension(photoFile.getFileName());
                Path folder = Paths.get(path + File.separatorChar + fileName + "." + extension  );
                Path file = Files.createFile(folder);
                
                try ( InputStream input = photoFile.getInputstream() ) {
                    
                    Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                }
            } catch (IOException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private String getPhotoName(){
        if(photoFile != null){
            String fileName =  FilenameUtils.getBaseName(photoFile.getFileName());
            String extension = FilenameUtils.getExtension(photoFile.getFileName());
            return fileName + "." + extension;
        }
        return "";
    }
    
    
}
