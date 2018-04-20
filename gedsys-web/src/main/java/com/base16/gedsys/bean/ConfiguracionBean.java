/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Acl;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.Configuracion;
import com.base16.gedsys.entities.Modulo;
import com.base16.gedsys.model.ConfiguracionJpaController;
import com.base16.gedsys.system.AclBean;
import com.base16.gedsys.system.ModuloBean;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.WebConfiguration;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author rober
 * @author Modificación Lina David
 */
@Named(value = "configuracionBean")
@SessionScoped
public class ConfiguracionBean extends BaseBean implements Serializable {

    /**
     * Creates a new instance of ConfiguracionBean
     */
    private String pathResource;
    private String pathData = "";
    private Boolean encriptFiles;

    private String companyName;
    private String direccion;
    private String telefono;
    private String razonSocial;

    private String mailAccount;
    private String mailPassword;
    private String mailPort;
    private String mailServer;
    private Boolean mailSSLTLS;
    private String mail;
    private UploadedFile logoFile;

    private String driver;
    private String urlConnection;
    private String usuarioDataBase;
    private String passwordDataBase;

    private Usuario usuario = new Usuario();

    private String licenseMode;
    private String licenseNumber;

    public String getPathResource() {
        return pathResource;
    }

    public void setPathResource(String pathResource) {
        this.pathResource = pathResource;
    }

    public String getPathData() {
        return pathData;
    }

    public void setPathData(String pathData) {
        this.pathData = pathData;
    }

    public Boolean getEncriptFiles() {
        return encriptFiles;
    }

    public void setEncriptFiles(Boolean encriptFiles) {
        this.encriptFiles = encriptFiles;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getMailAccount() {
        return mailAccount;
    }

    public void setMailAccount(String mailAccount) {
        this.mailAccount = mailAccount;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    public String getMailPort() {
        return mailPort;
    }

    public void setMailPort(String mailPort) {
        this.mailPort = mailPort;
    }

    public String getMailServer() {
        return mailServer;
    }

    public void setMailServer(String mailServer) {
        this.mailServer = mailServer;
    }

    public Boolean getMailSSLTLS() {
        return mailSSLTLS;
    }

    public void setMailSSLTLS(Boolean mailSSLTLS) {
        this.mailSSLTLS = mailSSLTLS;
    }

    public void setLogoFile(UploadedFile logoFile) {
        this.logoFile = logoFile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrlConnection() {
        return urlConnection;
    }

    public void setUrlConnection(String urlConnection) {
        this.urlConnection = urlConnection;
    }

    public String getUsuarioDataBase() {
        return usuarioDataBase;
    }

    public void setUsuarioDataBase(String usuarioDataBase) {
        this.usuarioDataBase = usuarioDataBase;
    }

    public String getPasswordDataBase() {
        return passwordDataBase;
    }

    public void setPasswordDataBase(String passwordDataBase) {
        this.passwordDataBase = passwordDataBase;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getLicenseMode() {
        return licenseMode;
    }

    public void setLicenseMode(String licenseMode) {
        this.licenseMode = licenseMode;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public ConfiguracionBean() {
    }

    public void uploadLogo() {
        if (logoFile != null) {
            try {
                FacesContext context = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
                String path = servletContext.getRealPath("/resources/images");
                String fileName = FilenameUtils.getBaseName(logoFile.getFileName());
                String extension = FilenameUtils.getExtension(logoFile.getFileName());
                File[] roots = File.listRoots();
                //Directorio por defecto para la carga de imagenes.
                Path folder = Paths.get(roots[0].getPath() + File.separatorChar + "gedsys" + File.separatorChar + fileName + "." + extension);
                Path file = Files.createFile(folder);
                try (InputStream input = logoFile.getInputstream()) {
                    Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
                    Logger.getLogger(ConfiguracionBean.class.getName()).log(Level.SEVERE, e.getMessage());
                }
            } catch (IOException ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
                Logger.getLogger(ConfiguracionBean.class.getName()).log(Level.SEVERE, ex.getMessage());
                //Logger.getLogger(ConfiguracionBean.class.getName());
            }
        }
    }

    public void guardar() {
        try {
            /*
            //Config Manager Resources and Docs.
            saveRegister("logo", logoFile.getFileName());
            saveRegister("pathResource", logoFile.getFileName());
            saveRegister("pathData", logoFile.getFileName());
            saveRegister("encriptFiles", logoFile.getFileName());
            //Company
            saveRegister("companyName", logoFile.getFileName());
            saveRegister("direccion", logoFile.getFileName());
            saveRegister("telefono", logoFile.getFileName());
            saveRegister("razonSocial", logoFile.getFileName());
            //SMTP Account
            saveRegister("mailAccount", logoFile.getFileName());
            saveRegister("mailPassword", logoFile.getFileName());
            saveRegister("mailPort", logoFile.getFileName());
            saveRegister("mailServer", logoFile.getFileName());
            saveRegister("mailSSLTLS", logoFile.getFileName());
             */

            uploadLogo();
            saveConfig();
            createSchema();
            createAdmin();
            initializeModules();

            FacesContext fContext = FacesContext.getCurrentInstance();
            ExternalContext extContext = fContext.getExternalContext();
            extContext.redirect(extContext.getRequestContextPath() + "/faces/login.xhtml");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
        }

    }

    private void createAdmin() {
        //Insertar Crear Usuario
        UsuarioBean ub = new UsuarioBean();
        ub.setAccion("Crear");
        ub.setUsuario(usuario);
        ub.procesar();
    }

    private void createSchema() {
        WebConfiguration wc;
        try {
            wc = WebConfiguration.getInstance();
            String path = wc.getConfigFilePath();
            JpaUtils.createSchema(path);
        } catch (NamingException ex) {
            Logger.getLogger(ConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void saveConfig() {
        try {
            WebConfiguration wc = WebConfiguration.getInstance();
            //Data Base Configuration
            wc.setProperty("jdbc_driver", this.getDriver());
            wc.setProperty("jdbc_url", this.getUrlConnection());
            wc.setProperty("jdbc_user", this.getUsuarioDataBase());
            wc.setProperty("jdbc_password", this.getPasswordDataBase());
            //Installation Lock
            wc.setProperty("installationLock", "true");
            //Email Settings
            wc.setProperty("mailAccount", this.getMailAccount());
            wc.setProperty("mailPassword", this.getMailPassword());
            wc.setProperty("mailPort", this.getMailPort());
            wc.setProperty("mailServer", this.getMailServer());
            wc.setProperty("mailSSLTLS", this.getMailSSLTLS().toString());
            //License
            //wc.setProperty("licenseMode", this.getLicenseMode());
            //wc.setProperty("licenseNumber", this.getLicenseNumber());
            //Data File
            wc.setProperty("PathData", this.getPathData());
            wc.setProperty("protectFile", "false");
            wc.save();
        } catch (NamingException ex) {
            Logger.getLogger(ConfiguracionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initializeModules() {

        //Insertar Modulos
        String[] modules = {"Entidades", "Empresas de Mensajeria", "Tipos de Documento",
            "Paises", "Departamentos", "Municipios", "Consecutivos",
            "Signatura Topografica", "Plantilla Documental", "Dias Festivos",
            "Usuarios", "Cargos", "ACL", "Grupos", "Modulos", "Secciones", "Serie",
            "SubSerie", "Unidad Documental", "Tipo Documental", "Creacion de Documentos",
            "Recepcion de Documentos", "Gestion de Documentos", "Control de Prestamos"};

        ModuloBean mb = new ModuloBean();
        for (String nombre : modules) {
            Modulo modulo = new Modulo();
            modulo.setNombre(nombre);
            mb.setAccion("Crear");
            mb.setModulo(modulo);
            mb.procesar();
        }

        //Insertar ACL
        List<Modulo> modulos = mb.getModulos();
        AclBean ab = new AclBean();
        for (Modulo modulo : modulos) {
            Acl acl = new Acl();
            acl.setModulo(modulo);
            ab.setAcl(acl);
            ab.setAccion("Crear");
            ab.procesar();
        }
    }

    private void saveRegister(String name, String value) throws Exception {
        ConfiguracionJpaController confJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            EntityManager em = emf.createEntityManager();
            confJpa = new ConfiguracionJpaController(emf);

            Configuracion conf = confJpa.findConfigurationByName("logo");
            if (conf != null) {
                conf.setNombre(name);
                conf.setValor(value);
                confJpa.edit(conf);
            } else {
                conf.setNombre(name);
                conf.setValor(value);
                confJpa.create(conf);
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
