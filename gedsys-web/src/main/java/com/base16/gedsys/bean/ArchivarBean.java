/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.SeccionSubSeccion;
import com.base16.gedsys.entities.Serie;
import com.base16.gedsys.entities.SignaturaTopografica;
import com.base16.gedsys.entities.SubSerie;
import com.base16.gedsys.entities.TipoDocumental;
import com.base16.gedsys.entities.UnidadDocumental;
import com.base16.gedsys.model.DocumentoJpaController;
import com.base16.gedsys.utils.JpaUtils;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author rober
 * @author Modificación Lina David
 */
@ManagedBean
@ViewScoped
public class ArchivarBean extends BaseBean implements Serializable {

    /**
     * Creates a new instance of ArchivarBean
     */
    private static final long SerialVersionUID = 1L;
    private Documento documento;
    private TreeNode selectedNodeSignatura = new DefaultTreeNode();
    private TipoDocumental tipoDocumental = new TipoDocumental();

    private SeccionSubSeccion seccionSubSeccion;
    private Serie serie;
    private SubSerie subSerie;
    private UnidadDocumental unidadDocumental;

    private List<SeccionSubSeccion> seccionesSubSecciones;
    private List<Serie> series;
    private List<SubSerie> subSeries;
    private List<UnidadDocumental> unidadesDocumentales;
    private List<TipoDocumental> tiposDocumentales;

    public ArchivarBean() {
        init();

    }

    
    public void init() {
        SeccionSubSeccionBean ssb = new SeccionSubSeccionBean();
        ssb.listar();
        seccionesSubSecciones = ssb.getSecciones();
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public TreeNode getSelectedNodeSignatura() {
        return selectedNodeSignatura;
    }

    public void setSelectedNodeSignatura(TreeNode selectedNodeSignatura) {
        this.selectedNodeSignatura = selectedNodeSignatura;
    }

    public TipoDocumental getTipoDocumental() {
        return tipoDocumental;
    }

    public void setTipoDocumental(TipoDocumental tipoDocumental) {
        this.tipoDocumental = tipoDocumental;
    }

    public SeccionSubSeccion getSeccionSubSeccion() {
        return seccionSubSeccion;
    }

    public void setSeccionSubSeccion(SeccionSubSeccion seccionSubSeccion) {
        this.seccionSubSeccion = seccionSubSeccion;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public SubSerie getSubSerie() {
        return subSerie;
    }

    public void setSubSerie(SubSerie subSerie) {
        this.subSerie = subSerie;
    }

    public UnidadDocumental getUnidadDocumental() {
        return unidadDocumental;
    }

    public void setUnidadDocumental(UnidadDocumental unidadDocumental) {
        this.unidadDocumental = unidadDocumental;
    }

    public void loadDocumento(Documento doc) {
        this.documento = doc;
        RequestContext.getCurrentInstance().execute("PF('denArchivar').show()");
    }

    public List<SeccionSubSeccion> getSeccionesSubSecciones() {
        return seccionesSubSecciones;
    }

    public void setSeccionesSubSecciones(List<SeccionSubSeccion> seccionesSubSecciones) {
        this.seccionesSubSecciones = seccionesSubSecciones;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    public List<SubSerie> getSubSeries() {
        return subSeries;
    }

    public void setSubSeries(List<SubSerie> subSeries) {
        this.subSeries = subSeries;
    }

    public List<UnidadDocumental> getUnidadesDocumentales() {
        return unidadesDocumentales;
    }

    public void setUnidadesDocumentales(List<UnidadDocumental> UnidadesDocumentales) {
        this.unidadesDocumentales = UnidadesDocumentales;
    }

    public List<TipoDocumental> getTiposDocumentales() {
        return tiposDocumentales;
    }

    public void setTiposDocumentales(List<TipoDocumental> tiposDocumentales) {
        this.tiposDocumentales = tiposDocumentales;
    }

    public void guadarDocumento() {
        if (this.selectedNodeSignatura != null) {
            if (documento.getSignaturaTopografica() == null) {
                DocumentoJpaController dJpa;
                try {
                    EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
                    dJpa = new DocumentoJpaController(emf);
                    SignaturaTopografica signatura = (SignaturaTopografica) this.selectedNodeSignatura.getData();
                    documento.setSignaturaTopografica(signatura);
                    documento.setTipoDocumental(this.tipoDocumental);
                    documento.setEstado(10);
                    dJpa.edit(documento);
                    this.addMessage(new FacesMessage("Archivar documentos", "Documento archivado correctamente"));
                } catch (Exception e) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
                    Logger.getLogger(ConsecutivoBean.class.getName()).log(Level.SEVERE, e.getMessage());
                }
            } else {
                this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivar documentos", "El documento ya ha sido archivado"));
            }
        } else {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Archivar", "No es posible archivar el documento, seleccione la signatura topografica!"));
        }
    }

    public void onSeccionSubSeccionChange() {
        SerieBean sb = new SerieBean();
        sb.getSerieBySeccionSubSeccion(seccionSubSeccion);
        series = sb.getSeries();
    }

    public void onSerieChange() {
        SubSerieBean ssb = new SubSerieBean();
        ssb.getSubSerieBySerie(serie);
        subSeries = ssb.getSubSeries();
    }

    public void onSubSerieChange() {
        UnidadDocumentalBean udb = new UnidadDocumentalBean();
        udb.getUnidadDocumentalBySubSerie(subSerie);
        unidadesDocumentales = udb.getUnidadesDocumentales();
    }

    public void onUnidadDocChange() {
        TipoDocumentalBean tdb = new TipoDocumentalBean();
        tdb.getTipoDocumentalByUnidadDocumental(unidadDocumental);
        tiposDocumentales = tdb.getTiposDocumentales();
    }

}
