/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author rober
 */
public class UploadDocument {
    public void upload(UploadedFile file ){
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
        String path = servletContext.getRealPath("/resources/images");
        upload(file, path);
    }
    
    public void upload(UploadedFile file, String path){
        if(file != null){
            try {
                String fileName =  FilenameUtils.getBaseName(file.getFileName());
                String extension = FilenameUtils.getExtension(file.getFileName());
                Path folder = Paths.get(path + File.separatorChar + fileName + "." + extension  );
                Path pathFile = Files.createFile(folder);
                
                try ( InputStream input = file.getInputstream() ) {   
                    Files.copy(input, pathFile, StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                }
            } catch (IOException ex) {
                Logger.getLogger(UploadDocument.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public String getFileName(UploadedFile file){
        if(file != null){
            String fileName =  FilenameUtils.getBaseName(file.getFileName());
            String extension = FilenameUtils.getExtension(file.getFileName());
            return fileName + "." + extension;
        }
        return "";
    }
}
