/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.bean;

import com.sucomunicacion.gedsys.images.RadicadoImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rober
 */
@ManagedBean
public class RadicadoBean extends BaseBean implements Serializable {

    @PostConstruct
    public void init(){
        
    }
    
    private StreamedContent radicadoImg;

    public void setRadicadoImg(StreamedContent radicadoImg) {
        this.radicadoImg = radicadoImg;
    }

    public StreamedContent getRadicadoImg() {
        RadicadoImage ri = new RadicadoImage();
        Integer randomNum =  (int) (Math.random() * 99999);
        String name = "0000-" +   randomNum.toString();
        String fileName = ri.Generar(name, configFilePath);
        try {
            File file =  new File("F:"+ File.separatorChar + fileName);
                if(file.canRead()){
                FileInputStream fi = new FileInputStream(file);
                this.radicadoImg = new DefaultStreamedContent( fi, null, file.getName() );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return radicadoImg;
    }
     
}
