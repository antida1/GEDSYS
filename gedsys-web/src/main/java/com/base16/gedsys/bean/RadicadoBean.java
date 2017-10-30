/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.fcm.PushFCMNotification;
import com.base16.gedsys.images.RadicadoImage;
import com.base16.gedsys.model.ConsecutivoJpaController;
import com.base16.gedsys.utils.JpaUtils;
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
    private String radicado;
    
    @PostConstruct
    public void init() {

    }

    public void setRadicadoImg(StreamedContent radicadoImg) {
        this.radicadoImg = radicadoImg;
    }

    public StreamedContent getRadicadoImg() {
        if(this.radicadoImg == null){
            this.generar();
        }
        return radicadoImg;
    }

    public String getRadicado() {
        return radicado;
    }

    public void setRadicado(String radicado) {
        this.radicado = radicado;
    }

    private void generar() {
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(configFilePath);
            EntityManager em = emf.createEntityManager();
            ConsecutivoJpaController cJpa;

            em.getTransaction().begin();
            cJpa = new ConsecutivoJpaController(emf);
            Consecutivo consec = cJpa.findConsecutivoByTipoConsecutivo("recepcion");
            Integer intConsec = Integer.parseInt(consec.getConsecutivo());
            intConsec++;
            /*
            consec.setConsecutivo(intConsec.toString());
            em.merge(consec);
            em.flush();
            */
            
            RadicadoImage ri = new RadicadoImage();
            SimpleDateFormat sdfDateRadicado = new SimpleDateFormat("yyyyMMdd");
            Date hoy = new Date();
            String strHoy = sdfDateRadicado.format(hoy);

            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();

            String name = consec.getPrefijo() + strHoy + consec.getConsecutivo() + consec.getSufijo();
            this.radicado = name;
            fileName = ri.Generar(name, this.documenstSavePath,  this.documenstSavePath + File.separatorChar);

            File file = new File(this.documenstSavePath+ File.separatorChar + this.fileName);
            if (file.canRead()) {
                FileInputStream fi = new FileInputStream(file);
                this.radicadoImg = new DefaultStreamedContent(fi, null, file.getName());
            }
            try {
                PushFCMNotification.PushFCMNotification("eLpabhs4Wbk:APA91bENwU-1iqJAfCbSNZXvO_PDZGAvmnJfjCNslRfxlol9SPmWdyQOWK_JKKZvPnRps8ME9HTzuN2jqRzUqPd8ZkSuZCG6y-dDoJexa4CrUzjwnlgVT4fp_9gal0FtK0NXDWjVVywm", "Nuevo Documento Radicado" + consec.toString(), "");
                PushFCMNotification.PushFCMNotification("da3jxzE6haA:APA91bFUbH6pr8VN8RDGBj2D0Qeuv2t6Qo90G2VqGKNYQ8PbniQ8klcYrLN2h9hDJTRDub-FuAldWdQg7PX4omTuOaPEglWB4toXcvqyVpHNiTwDzVbPWHDHvDXfCFRUjkVqX7z1HmrQ", "Nuevo Documento Radicado" + consec.toString(), "" );
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error!", e.getMessage()));
                Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
            }
            
            
        } catch (FileNotFoundException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error!", e.getMessage()));
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
}
