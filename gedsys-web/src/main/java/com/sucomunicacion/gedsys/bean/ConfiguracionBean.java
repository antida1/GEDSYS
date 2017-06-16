/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.bean;

import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.entities.Configuracion;
import com.sucomunicacion.gedsys.model.ConfiguracionJpaController;
import com.sucomunicacion.gedsys.utils.JpaUtils;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.servlet.ServletContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.component.message.Message;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author rober
 */
@Named(value = "configuracionBean")
@SessionScoped
public class ConfiguracionBean extends BaseBean implements Serializable {

    /**
     * Creates a new instance of ConfiguracionBean
     */
    private String pathResource;
    private String pathData;
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
    
    public ConfiguracionBean() {
    }
    
    public void uploadLogo(){
        if(logoFile != null){
            try {
                FacesContext context = FacesContext.getCurrentInstance();
                ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
                String path = servletContext.getRealPath("/resources/images");
                String fileName = FilenameUtils.getBaseName(logoFile.getFileName());
                String extension = FilenameUtils.getExtension(logoFile.getFileName());
                File[] roots = File.listRoots();
                //Directorio por defecto para la carga de imagenes.
                Path folder = Paths.get(roots[0].getPath() + File.separatorChar+ "gedsys" + File.separatorChar + fileName+ "." + extension);
                Path file = Files.createFile(folder);
                try (InputStream input = logoFile.getInputstream()){
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
    
    public void guardar(){
        try {

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



            uploadLogo();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
        }

    }

    private void saveRegister(String name, String value) throws Exception{
        ConfiguracionJpaController confJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            EntityManager em =  emf.createEntityManager();
            confJpa = new ConfiguracionJpaController(emf);

            
            Configuracion conf = confJpa.findConfigurationByName("logo");
            if(conf !=null){
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
