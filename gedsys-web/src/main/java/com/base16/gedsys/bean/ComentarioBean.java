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
import java.io.File;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManagerFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author robert
 */
@Named(value = "comentarioBean")
@ViewScoped
public class ComentarioBean extends BaseBean implements Serializable {

    
    private StreamedContent content;
    private String filePath = "";
    private List<Comentario> comentarios;
    /**
     * Creates a new instance of ComentarioBean
     */
    public ComentarioBean() {
    }

    
    @PostConstruct
    public void init() {

    }

    public void loadDocument(Documento doc) {
        this.filePath = this.getDocumenstSavePath() + File.separatorChar + doc.getNombreDocumento();
        SessionUtils.getSession().setAttribute("filePath", this.filePath);
        RequestContext.getCurrentInstance().execute("PF('denVisor').show()");
    }

    public void loadDocument(int TipoDocumento, int IdDocumentoProduccion) {
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.filePath);
        switch (TipoDocumento) {
            case 1: //Acta
                Acta acta;
                ActaJpaController actaJpac = new ActaJpaController(emf);
                acta = actaJpac.findActa(IdDocumentoProduccion);
                this.filePath = this.getDocumenstSavePath() + File.separatorChar + "Actas" + File.separatorChar + "acta" + acta.getId() + ".pdf";
                SessionUtils.getSession().setAttribute("filePathActa", this.filePath);
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
       
        ComentarioJpaController comentarioJpac = new ComentarioJpaController(emf);
        comentarios = comentarioJpac.findByTipoComProd(TipoDocumento, IdDocumentoProduccion);
        
        
    }

}
