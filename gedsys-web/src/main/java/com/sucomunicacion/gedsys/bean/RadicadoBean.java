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
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rober
 */
@ManagedBean
@RequestScoped
public class RadicadoBean extends BaseBean implements Serializable {

    private String fileName;
    private StreamedContent radicadoImg;

    @PostConstruct
    public void init() {

    }

    public void setRadicadoImg(StreamedContent radicadoImg) {
        this.radicadoImg = radicadoImg;
    }

    public StreamedContent getRadicadoImg() {
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(configFilePath);
            EntityManager em = emf.createEntityManager();
            ConsecutivoJpaController cJpa;

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

            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            String path = servletContext.getRealPath("/resources/images");

            String name = consec.getPrefijo() + strHoy + consec.getConsecutivo() + consec.getSufijo();
            fileName = ri.Generar(name, path);

            em.getTransaction().commit();

            File file = new File("F:" + File.separatorChar + this.fileName);
            if (file.canRead()) {
                FileInputStream fi = new FileInputStream(file);
                this.radicadoImg = new DefaultStreamedContent(fi, null, file.getName());
            }
        } catch (FileNotFoundException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error!", e.getMessage()));
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        return radicadoImg;
    }

    public void generar() {

    }
}
