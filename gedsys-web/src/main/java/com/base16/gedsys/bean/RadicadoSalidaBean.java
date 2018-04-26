/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.images.RadicadoImage;
import com.base16.gedsys.model.ConsecutivoJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
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
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rober
 * @author Modificación Lina David
 */
@ManagedBean
@RequestScoped
public class RadicadoSalidaBean extends BaseBean implements Serializable {
    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

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
        if (this.radicadoImg == null) {
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
            Consecutivo consec = cJpa.findConsecutivoByTipoConsecutivo("envio");
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
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            String UsuarioName = usuario.getNombres() + " " + usuario.getApelidos();
            String name = consec.getPrefijo() + strHoy +"-" + consec.getConsecutivo() + consec.getSufijo();
            String nombreEntidad = this.nombreEntidad;
            this.radicado = name;

            this.fileName = ri.Generar(nombreEntidad, name, this.documenstSavePath, this.documenstSavePath + File.separatorChar, UsuarioName);

            File file = new File(this.documenstSavePath + File.separatorChar + this.fileName);
            if (file.canRead()) {
                FileInputStream fi = new FileInputStream(file);
                this.radicadoImg = new DefaultStreamedContent(fi, null, file.getName());
            }

        } catch (FileNotFoundException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error!", e.getMessage()));
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
}
