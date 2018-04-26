/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.utils;

import com.base16.gedsys.bean.BaseBean;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author rober
 */
public class UploadDocument extends BaseBean {

    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void upload(UploadedFile file) {
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
        String path = servletContext.getRealPath("/resources/images");
        upload(file, path);
    }

    public void upload(UploadedFile file, String path) {
        if (file != null) {
            try {
                this.uuid = UUID.randomUUID();
                String fileName = FilenameUtils.getBaseName(file.getFileName());
                String extension = FilenameUtils.getExtension(file.getFileName());
                Path folder = Paths.get(path + File.separatorChar + this.uuid.toString());
                Path pathFile = Files.createFile(folder);
                try (InputStream input = file.getInputstream()) {
                    Files.copy(input, pathFile, StandardCopyOption.REPLACE_EXISTING);
         
                    if (this.getEncriptFiles() == true) {
                        File inputFile = pathFile.toFile();
                        File outputFile = new File(path + File.separatorChar + this.uuid.toString());
                        CryptoUtils.encrypt("Mary has one cat", inputFile, outputFile);
                    } else {

                    }
                } catch (Exception e) {
                    Logger.getLogger(UploadDocument.class.getName()).log(Level.SEVERE, null, e);
                }
            } catch (IOException ex) {
                Logger.getLogger(UploadDocument.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void upload(File file, String path){
        if (file != null) {
            try {
                this.uuid = UUID.randomUUID();
                String fileName = FilenameUtils.getBaseName(file.getName());
                String extension = FilenameUtils.getExtension(file.getName());
                Path folder = Paths.get(path + File.separatorChar + this.uuid.toString());
                Path pathFile = Files.createFile(folder);
                
                try (InputStream input = new FileInputStream(file)) {
                    Files.copy(input, pathFile, StandardCopyOption.REPLACE_EXISTING);
         
                    if (this.getEncriptFiles() == true) {
                        File inputFile = pathFile.toFile();
                        File outputFile = new File(path + File.separatorChar + this.uuid.toString());
                        CryptoUtils.encrypt("Mary has one cat", inputFile, outputFile);
                    } else {

                    }
                } catch (Exception e) {
                    Logger.getLogger(UploadDocument.class.getName()).log(Level.SEVERE, null, e);
                }
            } catch (IOException ex) {
                Logger.getLogger(UploadDocument.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getFileName(UploadedFile file) {
        if (file != null) {
            String fileName = FilenameUtils.getBaseName(file.getFileName());
            String extension = FilenameUtils.getExtension(file.getFileName());
            return fileName + "." + extension;
        }
        return "";
    }
    
    public String getFileName(File file) {
        if (file != null) {
            String fileName = FilenameUtils.getBaseName(file.getName());
            String extension = FilenameUtils.getExtension(file.getName());
            return fileName + "." + extension;
        }
        return "";
    }
}
