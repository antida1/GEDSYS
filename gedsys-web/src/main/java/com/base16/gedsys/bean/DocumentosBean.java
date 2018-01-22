/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Acta;
import com.base16.gedsys.entities.Carta;
import com.base16.gedsys.entities.Certificado;
import com.base16.gedsys.entities.Circular;
import com.base16.gedsys.entities.Comunicacion;
import com.base16.gedsys.entities.Constancia;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Informe;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.ActaJpaController;
import com.base16.gedsys.model.CartaJpaController;
import com.base16.gedsys.model.CertificadoJpaController;
import com.base16.gedsys.model.CircularJpaController;
import com.base16.gedsys.model.ComunicacionJpaController;
import com.base16.gedsys.model.ConstanciaJpaController;
import com.base16.gedsys.model.DocumentoJpaController;
import com.base16.gedsys.model.InformeJpaController;
import com.base16.gedsys.utils.JpaUtils;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
@ManagedBean
@ViewScoped
public class DocumentosBean extends BaseBean implements Serializable {

    private List<Documento> documentos;
    private List<Documento> recibidos;
    private List<Documento> enviados;
    private List<Documento> prestamo;
    private List<Documento> porVencer;
    private List<Documento> radicados;
    private List<Documento> sinArchivar;
    
    
    private List<Acta> actasPorFirmar;
    private List<Carta> cartaPorFirmar;
    private List<Certificado> certificadoPorFirmar;
    private List<Circular> circularPorFirmar;
    private List<Comunicacion> comunicadoPorFirmar;
    private List<Constancia> constanciaPorFirmar;
    private List<Informe> informePorFirmar;

    /**
     * Creates a new instance of DocumentosBean
     */
    public DocumentosBean() {
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public List<Documento> getEnviados() {
        return enviados;
    }

    public void setEnviados(List<Documento> enviados) {
        this.enviados = enviados;
    }

    public List<Documento> getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(List<Documento> prestamo) {
        this.prestamo = prestamo;
    }

    public List<Acta> getActasPorFirmar() {
        return actasPorFirmar;
    }

    public void setActasPorFirmar(List<Acta> actasPorFirmar) {
        this.actasPorFirmar = actasPorFirmar;
    }

    public List<Carta> getCartaPorFirmar() {
        return cartaPorFirmar;
    }

    public void setCartaPorFirmar(List<Carta> cartaPorFirmar) {
        this.cartaPorFirmar = cartaPorFirmar;
    }

    public List<Certificado> getCertificadoPorFirmar() {
        return certificadoPorFirmar;
    }

    public void setCertificadoPorFirmar(List<Certificado> certificadoPorFirmar) {
        this.certificadoPorFirmar = certificadoPorFirmar;
    }

    public List<Circular> getCircularPorFirmar() {
        return circularPorFirmar;
    }

    public void setCircularPorFirmar(List<Circular> circularPorFirmar) {
        this.circularPorFirmar = circularPorFirmar;
    }

    public List<Comunicacion> getComunicadoPorFirmar() {
        return comunicadoPorFirmar;
    }

    public void setComunicadoPorFirmar(List<Comunicacion> comunicadoPorFirmar) {
        this.comunicadoPorFirmar = comunicadoPorFirmar;
    }

    public List<Constancia> getConstanciaPorFirmar() {
        return constanciaPorFirmar;
    }

    public void setConstanciaPorFirmar(List<Constancia> constanciaPorFirmar) {
        this.constanciaPorFirmar = constanciaPorFirmar;
    }

    public List<Informe> getInformePorFirmar() {
        return informePorFirmar;
    }

    public void setInformePorFirmar(List<Informe> informePorFirmar) {
        this.informePorFirmar = informePorFirmar;
    }

    public List<Documento> getPorVencer() {
        return porVencer;
    }

    public void setPorVencer(List<Documento> porVencer) {
        this.porVencer = porVencer;
    }

    public List<Documento> getRecibidos() {
        return recibidos;
    }

    public void setRecibidos(List<Documento> recibidos) {
        this.recibidos = recibidos;
    }

    public List<Documento> getRadicados() {
        return radicados;
    }

    public void setRadicados(List<Documento> radicados) {
        this.radicados = radicados;
    }

    public List<Documento> getSinArchivar() {
        return sinArchivar;
    }

    public void setSinArchivar(List<Documento> sinArchivar) {
        this.sinArchivar = sinArchivar;
    }
    
    private void listarDocumentosRadicados() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            radicados = dJpa.findRadicados(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarDocumentosRecibidos() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            recibidos = dJpa.findEntrantes(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarDocumentosEnviados() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            enviados = dJpa.findEnviados(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarDocumentosPrestados() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            prestamo = dJpa.findEnPrestamo(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarDocumentosPorVencer() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            porVencer = dJpa.findPorVencer(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
       public void listarDocumentosSinArchivar() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            sinArchivar = dJpa.findSinArchivar(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarActasPorFirmar() {
        ActaJpaController aJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            aJpa = new ActaJpaController(emf);
            actasPorFirmar = aJpa.findActaEntities();
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarCartasPorFirmar() {
        CartaJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CartaJpaController(emf);
            cartaPorFirmar = cJpa.findCartaEntities();
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarCertificadosPorFirmar() {
        CertificadoJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CertificadoJpaController(emf);
            certificadoPorFirmar = cJpa.findCertificadoEntities();
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarCircularesPorFirmar() {
        CircularJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new CircularJpaController(emf);
            circularPorFirmar = cJpa.findCircularEntities();
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarComunicadosPorFirmar() {
        ComunicacionJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ComunicacionJpaController(emf);
            comunicadoPorFirmar = cJpa.findComunicacionEntities();
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarConstanciaPorFirmar() {
        ConstanciaJpaController cJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            cJpa = new ConstanciaJpaController(emf);
            constanciaPorFirmar = cJpa.findConstanciaEntities();
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarInformesPorFirmar() {
        InformeJpaController iJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            iJpa = new InformeJpaController(emf);
            informePorFirmar = iJpa.findInformeEntities();
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listar() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            documentos = dJpa.findDocumentoEntities();
        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarCompartidos(Usuario destinatario) {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            if (destinatario != null) {
                documentos = dJpa.findByCompartidos(destinatario);
            } else {
                documentos = dJpa.findByCompartidos(this.getCurrentUser());
            }

        } catch (Exception e) {
            Logger.getLogger(DocumentosBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

}
