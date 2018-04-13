/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Grupo;
import com.base16.gedsys.entities.GrupoUsuario;
import com.base16.gedsys.entities.SignaturaTopografica;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.Usuariosignaturas;
import com.base16.gedsys.model.GrupoUsuarioJpaController;
import com.base16.gedsys.model.UsuarioJpaController;
import com.base16.gedsys.model.UsuariosignaturasJpaController;
import com.base16.gedsys.model.exceptions.NonexistentEntityException;
import com.base16.gedsys.security.Authentication;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.TreeNode;
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
    private Grupo grupo = new Grupo();
    private List<Usuario> usuarios;
    private String accion;
    private UploadedFile photoFile;
    private UploadedFile firmaFile;
    private String password;

    private TreeNode[] selectedSignaturas;
    private TreeNode[] signaturas;

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
        this.limpiar();
        this.accion = accion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UploadedFile getFirmaFile() {
        return firmaFile;
    }

    public void setFirmaFile(UploadedFile firmaFile) {
        this.firmaFile = firmaFile;
    }

    public TreeNode[] getSelectedSignaturas() {
        return selectedSignaturas;
    }

    public void setSelectedSignaturas(TreeNode[] selectedSignaturas) {
        this.selectedSignaturas = selectedSignaturas;
    }

    public TreeNode[] getSignaturas() {
        return signaturas;
    }

    public void setSignaturas(TreeNode[] signaturas) {
        this.signaturas = signaturas;
    }

    public UsuarioBean() {

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
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Uauarios", "Ha ocurrido un error: " + e.getMessage()));
        }
    }

    private void crear() throws Exception {
        UsuarioJpaController usrJpa;
        GrupoUsuarioJpaController guJpa;
        UsuariosignaturasJpaController usJpa;

        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            usrJpa = new UsuarioJpaController(emf);
            guJpa = new GrupoUsuarioJpaController(emf);
            usJpa = new UsuariosignaturasJpaController(emf);
            //this.usuario.setFechaCreacion(new Date());
            //this.usuario.setFechaModificacion(new Date());
            //Usuario usuario = (Usuario) SessionUtils.getUsuario();
            //this.usuario.setCreadoPor(usuario.getNombres() + " " + usuario.getApelidos());
            this.usuario.setClave(Authentication.md5(this.password));
            if (this.getPhotoName().equals("user.png")) {
                this.usuario.setFoto("user.png");
            } else {
                this.usuario.setFoto(this.getPhotoName());
            }
            uploadPhoto();
            uploadFirma();
            GrupoUsuario grupoUsuario = new GrupoUsuario();
            grupoUsuario.setGrupo(grupo);
            guJpa.create(grupoUsuario);
            Collection<GrupoUsuario> grupoUsuarioCollection = new ArrayList<>();
            grupoUsuarioCollection.add(grupoUsuario);

            this.usuario.setGrupoUsuarioCollection2(grupoUsuarioCollection);
            usrJpa.create(usuario);
            emf.getCache().evictAll();

            //Asociar las Asignaturas del usuario.}
            //TODO: Convertir en funcion
            for (TreeNode selectedSignatura : selectedSignaturas) {
                SignaturaTopografica signatura = (SignaturaTopografica) selectedSignatura.getData();
                Usuariosignaturas usuarioSignatura = new Usuariosignaturas();
                usuarioSignatura.setSignatura(signatura);
                usuarioSignatura.setUsuario(this.usuario);
                usJpa.create(usuarioSignatura);
            }

            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    private void modificar() throws Exception {
        UsuarioJpaController usrJpa;
        GrupoUsuarioJpaController guJpa;
        UsuariosignaturasJpaController usJpa;

        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            usrJpa = new UsuarioJpaController(emf);
            guJpa = new GrupoUsuarioJpaController(emf);
            usJpa = new UsuariosignaturasJpaController(emf);
            //this.usuario.setFechaModificacion(new Date());
            //Usuario usuario = (Usuario) SessionUtils.getUsuario();
            //this.usuario.setModificadoPor(usuario.getNombres() + " " + usuario.getApelidos());

            for (GrupoUsuario grupoUsuario : this.usuario.getGrupoUsuarioCollection2()) {
                guJpa.destroy(grupoUsuario.getId());
            }

            GrupoUsuario grupoUsuario = new GrupoUsuario();
            grupoUsuario.setGrupo(grupo);
            guJpa.create(grupoUsuario);
            Collection<GrupoUsuario> grupoUsuarioCollection = new ArrayList<>();
            grupoUsuarioCollection.add(grupoUsuario);
            this.usuario.setFoto(this.getPhotoName());
            if (firmaFile.getContents().length > 0) {
                this.usuario.setFirma(FilenameUtils.getName(firmaFile.getFileName()));
                uploadFirma();
            }
            uploadPhoto();
            if (this.getPhotoName().equals("user.png")) {
                this.usuario.setFoto(this.usuario.getFoto());
            } else {
                this.usuario.setFoto(this.getPhotoName());
            }

            this.usuario.setGrupoUsuarioCollection2(grupoUsuarioCollection);
            usrJpa.edit(usuario);

            //Eliminar las Signaturas por usuario
            //Recrear las signaturas asignadas al ausuario.
            for (TreeNode selectedSignatura : selectedSignaturas) {
                SignaturaTopografica signatura = (SignaturaTopografica) selectedSignatura.getData();
                Usuariosignaturas usuarioSignatura = new Usuariosignaturas();
                usuarioSignatura.setSignatura(signatura);
                usuarioSignatura.setUsuario(this.usuario);
                usJpa.create(usuarioSignatura);
            }

            emf.getCache().evictAll();
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(Usuario usuario) throws Exception {
        UsuarioJpaController usrJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            usrJpa = new UsuarioJpaController(emf);
            usrJpa.destroy(usuario.getId());
            this.listar();
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuarios", "Usuario Eliminada"));
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

    public void getUsuarioById(Usuario usuario) throws Exception {
        UsuarioJpaController usrJpa;
        Usuario usrTemp;
        this.grupo = new Grupo();
        this.usuario = new Usuario();
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            usrJpa = new UsuarioJpaController(emf);
            usrTemp = usrJpa.findUsuario(usuario.getId());
            if (usrTemp != null) {
                this.usuario = usrTemp;
                this.password = usrTemp.getClave();
                for (GrupoUsuario grupoUsuario : usrTemp.getGrupoUsuarioCollection2()) {
                    this.grupo = grupoUsuario.getGrupo();
                    break;
                }
                //TODO: Cargar Signaturas topograficas.

                this.accion = "Modificar";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void uploadFirma() {
        if (firmaFile != null) {
            try {
                FacesContext context = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
                //String path = servletContext.getRealPath("/resources/images");
                String fileName = FilenameUtils.getBaseName(firmaFile.getFileName());
                String extension = FilenameUtils.getExtension(firmaFile.getFileName());
                Path folder = Paths.get(this.getDocumenstSavePath() + File.separatorChar + "firmas" + File.separatorChar + fileName + "." + extension);
                Path file = Files.createFile(folder);
                try (InputStream input = firmaFile.getInputstream()) {
                    Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                    Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, e);
                }

            } catch (IOException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void uploadPhoto() {
        if (photoFile != null) {
            try {
                FacesContext context = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
                //String path = servletContext.getRealPath("/resources/images");
                String fileName = FilenameUtils.getBaseName(photoFile.getFileName());
                String extension = FilenameUtils.getExtension(photoFile.getFileName());

                Path folder = Paths.get(this.getDocumenstSavePath() + File.separatorChar + "images" + File.separatorChar + fileName + "." + extension);
                Path file = Files.createFile(folder);
                try (InputStream input = photoFile.getInputstream()) {

                    Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                }

            } catch (IOException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private String getPhotoName() {
        if (photoFile != null) {
            String fileName = FilenameUtils.getBaseName(photoFile.getFileName());
            String extension = FilenameUtils.getExtension(photoFile.getFileName());
            if (fileName.equals("")) {
                return "user.png";
            }
            return fileName + "." + extension;
        }
        return "user.png";
    }

    public void resetPassword() {
        try {
            UsuarioJpaController usrJpa;
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            usrJpa = new UsuarioJpaController(emf);
            this.usuario.setClave(Authentication.md5(this.password));
            usrJpa.edit(this.usuario);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void limpiar() {
        this.usuario = null;
        this.grupo = null;

        this.usuario = new Usuario();
        this.grupo = new Grupo();
    }

}
