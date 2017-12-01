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
import com.base16.gedsys.model.DocumentoJpaController;
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
    private List<Documento> enviados;
    private List<Documento> prestamo;
    private List<Documento> porVencer;
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

    public void listarDocumentosRecibidos() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            documentos = dJpa.findDocumentoByDestinatario(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarDocumentosEnviados() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            documentos = dJpa.findDocumentoByDestinatario(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarDocumentosPrestados() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            documentos = dJpa.findDocumentoByDestinatario(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarDocumentosPorVencer() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            documentos = dJpa.findDocumentoByDestinatario(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarActasPorFirmar() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            documentos = dJpa.findDocumentoByDestinatario(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarCartasPorFirmar() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            documentos = dJpa.findDocumentoByDestinatario(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarCertificadosPorFirmar() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            documentos = dJpa.findDocumentoByDestinatario(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarCircularesPorFirmar() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            documentos = dJpa.findDocumentoByDestinatario(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarComunicadosPorFirmar() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            documentos = dJpa.findDocumentoByDestinatario(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarConstanciaPorFirmar() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            documentos = dJpa.findDocumentoByDestinatario(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarInformesPorFirmar() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            documentos = dJpa.findDocumentoByDestinatario(this.getCurrentUser());
        } catch (Exception e) {
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listar() {
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            documentos = dJpa.findDocumentoEntities();
        } catch (Exception e) {
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void listarPorDestinatario(Usuario destinatario) {         
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            dJpa = new DocumentoJpaController(emf);
            if(destinatario != null){
                documentos = dJpa.findDocumentoByDestinatario(destinatario);
            } else{
                documentos = dJpa.findDocumentoByDestinatario(this.getCurrentUser());
            }
            
        } catch (Exception e) {
            Logger.getLogger(RadicadoBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

}
