/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.bean;

import com.sucomunicacion.gedsys.entities.Consecutivo;
import com.sucomunicacion.gedsys.images.RadicadoImage;
import com.sucomunicacion.gedsys.model.ConsecutivoJpaController;
import com.sucomunicacion.gedsys.utils.JpaUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rober
 */
@ManagedBean
public class RadicadoBean extends BaseBean implements Serializable {

    @PostConstruct
    public void init() {

    }

    private StreamedContent radicadoImg;

    public void setRadicadoImg(StreamedContent radicadoImg) {
        this.radicadoImg = radicadoImg;
    }

    public StreamedContent getRadicadoImg() {
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(configFilePath);
        EntityManager em = emf.createEntityManager();
        ConsecutivoJpaController cJpa;
        try {
            em.getTransaction().begin();

            cJpa = new ConsecutivoJpaController(emf);
            Consecutivo consec = cJpa.findConsecutivoByTipoConsecutivo("Externo");

            Integer intConsec = Integer.parseInt(consec.getConsecutivo());
            intConsec++;

            consec.setConsecutivo(intConsec.toString());
            
            em.merge(consec);
            em.flush();
           
            RadicadoImage ri = new RadicadoImage();
            SimpleDateFormat sdfDateRadicado = new SimpleDateFormat("yyyyMMdd");
            Date hoy = new Date();
            String strHoy = sdfDateRadicado.format(hoy);

            String name = consec.getPrefijo() + strHoy + consec.getConsecutivo() + consec.getPrefijo();
            String fileName = ri.Generar(name, configFilePath);

            //TODO: Cambiar la ruta para que tome la ruta de la configuración
            File file = new File("F:" + File.separatorChar + fileName);
            if (file.canRead()) {
                FileInputStream fi = new FileInputStream(file);
                this.radicadoImg = new DefaultStreamedContent(fi, null, file.getName());
            }
            em.getTransaction().commit();
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_FATAL  ,"Error!", e.getMessage()));
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        } 
        return radicadoImg;
    }
}
