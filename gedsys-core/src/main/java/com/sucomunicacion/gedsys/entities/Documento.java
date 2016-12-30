/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
 */
@Entity
@Table(name = "Documento", catalog = "gedsys", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d")
    , @NamedQuery(name = "Documento.findById", query = "SELECT d FROM Documento d WHERE d.id = :id")
    , @NamedQuery(name = "Documento.findByAsunto", query = "SELECT d FROM Documento d WHERE d.asunto = :asunto")
    , @NamedQuery(name = "Documento.findByRutaArchivo", query = "SELECT d FROM Documento d WHERE d.rutaArchivo = :rutaArchivo")
    , @NamedQuery(name = "Documento.findByRequiereRespuesta", query = "SELECT d FROM Documento d WHERE d.requiereRespuesta = :requiereRespuesta")
    , @NamedQuery(name = "Documento.findByFechaCreacion", query = "SELECT d FROM Documento d WHERE d.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Documento.findByFechaModificacion", query = "SELECT d FROM Documento d WHERE d.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Documento.findByCreadoPor", query = "SELECT d FROM Documento d WHERE d.creadoPor = :creadoPor")
    , @NamedQuery(name = "Documento.findByModificadoPor", query = "SELECT d FROM Documento d WHERE d.modificadoPor = :modificadoPor")})
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Column(name = "Asunto", length = 50)
    private String asunto;
    @Column(name = "RutaArchivo", length = 255)
    private String rutaArchivo;
    @Column(name = "RequiereRespuesta")
    private Boolean requiereRespuesta;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "CreadoPor", length = 36)
    private String creadoPor;
    @Column(name = "ModificadoPor", length = 36)
    private String modificadoPor;
    @OneToMany(mappedBy = "documento")
    private List<Anexo> anexoList;
    @JoinColumn(name = "Session", referencedColumnName = "Id")
    @ManyToOne
    private Seccion session;
    @JoinColumn(name = "Serie", referencedColumnName = "Id")
    @ManyToOne
    private Serie serie;
    @JoinColumn(name = "SubSeccion", referencedColumnName = "Id")
    @ManyToOne
    private SubSeccion subSeccion;
    @JoinColumn(name = "SubSerie", referencedColumnName = "Id")
    @ManyToOne
    private SubSerie subSerie;
    @JoinColumn(name = "TipoDocumental", referencedColumnName = "Id")
    @ManyToOne
    private TipoDocumental tipoDocumental;
    @JoinColumn(name = "UnidadDocumental", referencedColumnName = "Id")
    @ManyToOne
    private UnidadDocumental unidadDocumental;

    public Documento() {
    }

    public Documento(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public Boolean getRequiereRespuesta() {
        return requiereRespuesta;
    }

    public void setRequiereRespuesta(Boolean requiereRespuesta) {
        this.requiereRespuesta = requiereRespuesta;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    @XmlTransient
    public List<Anexo> getAnexoList() {
        return anexoList;
    }

    public void setAnexoList(List<Anexo> anexoList) {
        this.anexoList = anexoList;
    }

    public Seccion getSession() {
        return session;
    }

    public void setSession(Seccion session) {
        this.session = session;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public SubSeccion getSubSeccion() {
        return subSeccion;
    }

    public void setSubSeccion(SubSeccion subSeccion) {
        this.subSeccion = subSeccion;
    }

    public SubSerie getSubSerie() {
        return subSerie;
    }

    public void setSubSerie(SubSerie subSerie) {
        this.subSerie = subSerie;
    }

    public TipoDocumental getTipoDocumental() {
        return tipoDocumental;
    }

    public void setTipoDocumental(TipoDocumental tipoDocumental) {
        this.tipoDocumental = tipoDocumental;
    }

    public UnidadDocumental getUnidadDocumental() {
        return unidadDocumental;
    }

    public void setUnidadDocumental(UnidadDocumental unidadDocumental) {
        this.unidadDocumental = unidadDocumental;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.Documento[ id=" + id + " ]";
    }
    
}
