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
import com.base16.gedsys.entities.Comentario;
import com.base16.gedsys.entities.Comunicacion;
import com.base16.gedsys.entities.Constancia;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Informe;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.ActaJpaController;
import com.base16.gedsys.model.CartaJpaController;
import com.base16.gedsys.model.CertificadoJpaController;
import com.base16.gedsys.model.CircularJpaController;
import com.base16.gedsys.model.ComentarioJpaController;
import com.base16.gedsys.model.ComunicacionJpaController;
import com.base16.gedsys.model.ConstanciaJpaController;
import com.base16.gedsys.model.InformeJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManagerFactory;
import org.primefaces.context.RequestContext;

/**
 *
 * @author robert
 */
@ManagedBean
@ViewScoped
public class ComentarioBean extends BaseBean {

    /**
     * Creates a new instance of CommentBean
     */
    public ComentarioBean() {
        
    }
     private List<Comentario> comentarios;

    private String comentario;
    private Documento documento;
    private int tipoDocumento;
    private int documentoPrdId;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getDocumentoPrdId() {
        return documentoPrdId;
    }

    public void setDocumentoPrdId(int documentoPrdId) {
        this.documentoPrdId = documentoPrdId;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public void loadDocument(Documento doc) {
        RequestContext.getCurrentInstance().execute("PF('denComentar').show()");

        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.configFilePath);
        ComentarioJpaController cjpa = new ComentarioJpaController(emf);
        this.documento = doc;
        this.comentarios = cjpa.findComentarioByDocumento(doc);
    }

    public void loadDocumentPrd(int TipoDocumento, int IdDocumentoProduccion) {
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.configFilePath);
        switch (TipoDocumento) {
            case 1: //Acta
                Acta acta;
                ActaJpaController actaJpac = new ActaJpaController(emf);
                acta = actaJpac.findActa(IdDocumentoProduccion);
                break;
            case 2: //Carta
                Carta carta;
                CartaJpaController cartaJpac = new CartaJpaController(emf);
                carta = cartaJpac.findCarta(IdDocumentoProduccion);
                break;
            case 3: //Certificado
                Certificado certificado;
                CertificadoJpaController certJpac = new CertificadoJpaController(emf);
                certificado = certJpac.findCertificado(IdDocumentoProduccion);
                break;
            case 4: //Circular
                Circular circular;
                CircularJpaController cirJpac = new CircularJpaController(emf);
                circular = cirJpac.findCircular(IdDocumentoProduccion);
                break;
            case 5: //Comunicacion Interna
                Comunicacion comunicacion;
                ComunicacionJpaController comJpac = new ComunicacionJpaController(emf);
                comunicacion = comJpac.findComunicacion(IdDocumentoProduccion);
                break;
            case 6://Constancia
                Constancia constancia;
                ConstanciaJpaController consJpac = new ConstanciaJpaController(emf);
                constancia = consJpac.findConstancia(IdDocumentoProduccion);
                break;
            case 7: //Informe
                Informe informe;
                InformeJpaController infoJpac = new InformeJpaController(emf);
                informe = infoJpac.findInforme(IdDocumentoProduccion);
                break;
            default:

        }
        this.documentoPrdId = IdDocumentoProduccion;
        this.tipoDocumento = TipoDocumento;

        ComentarioJpaController cjpa = new ComentarioJpaController(emf);
        this.comentarios = cjpa.findByTipoComProd(TipoDocumento, IdDocumentoProduccion);

        ComentarioJpaController comentarioJpac = new ComentarioJpaController(emf);
        comentarios = comentarioJpac.findByTipoComProd(TipoDocumento, IdDocumentoProduccion);
        RequestContext.getCurrentInstance().execute("PF('denComentar').show()");

    }

    public int coutComents(Documento documento) {
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.configFilePath);
        ComentarioJpaController cjpa = new ComentarioJpaController(emf);
        this.comentarios = cjpa.findComentarioByDocumento(documento);
        if (this.comentarios != null) {
            return this.comentarios.size();
        }
        return 0;
    }

    public int coutComentsByTipo(int TipoDocumento, int IdDocumentoProduccion) {
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.configFilePath);
        ComentarioJpaController cjpa = new ComentarioJpaController(emf);
        this.comentarios = cjpa.findByTipoComProd(TipoDocumento, IdDocumentoProduccion);
        if (this.comentarios != null) {
            return this.comentarios.size();
        }
        return 0;
    }

    public void addComent() {
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.configFilePath);
        ComentarioJpaController cjpa = new ComentarioJpaController(emf);
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        Comentario coment = new Comentario();
        coment.setComentario(this.comentario);
        coment.setFechaCreacion(new Date());
        coment.setCreadoPor(usuario);
        coment.setModificadoPor(usuario);
        coment.setFechaModificacion(new Date());

        if (documento != null) {
            coment.setDocumento(documento);
        } else {
            coment.setTipoComentario(this.tipoDocumento);
            coment.setIdDocProduccion(this.documentoPrdId);
        }
        cjpa.create(coment);
        this.comentario ="";
        this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Comentatio", "Comentario Creado"));
        RequestContext.getCurrentInstance().execute("PF('denComentar').hide()");
    }   
}

