/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author rober
 */
@Entity
@Table(name = "tipodocumental", catalog = "gedsys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumental.findAll", query = "SELECT t FROM TipoDocumental t")
    , @NamedQuery(name = "TipoDocumental.findById", query = "SELECT t FROM TipoDocumental t WHERE t.id = :id")
    , @NamedQuery(name = "TipoDocumental.findByBorrado", query = "SELECT t FROM TipoDocumental t WHERE t.borrado = :borrado")
    , @NamedQuery(name = "TipoDocumental.findByDocumentoExterno", query = "SELECT t FROM TipoDocumental t WHERE t.documentoExterno = :documentoExterno")
    , @NamedQuery(name = "TipoDocumental.findByEstampadoCronologico", query = "SELECT t FROM TipoDocumental t WHERE t.estampadoCronologico = :estampadoCronologico")
    , @NamedQuery(name = "TipoDocumental.findByFechaCreacion", query = "SELECT t FROM TipoDocumental t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TipoDocumental.findByFechaModificacion", query = "SELECT t FROM TipoDocumental t WHERE t.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "TipoDocumental.findByNombre", query = "SELECT t FROM TipoDocumental t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoDocumental.findByRequiereRespuesta", query = "SELECT t FROM TipoDocumental t WHERE t.requiereRespuesta = :requiereRespuesta")
    , @NamedQuery(name = "TipoDocumental.findByTiempoRespuesta", query = "SELECT t FROM TipoDocumental t WHERE t.tiempoRespuesta = :tiempoRespuesta")
    , @NamedQuery(name = "TipoDocumental.findByTipoCalendario", query = "SELECT t FROM TipoDocumental t WHERE t.tipoCalendario = :tipoCalendario")})
public class TipoDocumental implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Borrado")
    private Boolean borrado;
    @Column(name = "DocumentoExterno")
    private Boolean documentoExterno;
    @Column(name = "EstampadoCronologico")
    private String estampadoCronologico;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "RequiereRespuesta")
    private Boolean requiereRespuesta;
    @Column(name = "TiempoRespuesta")
    private Integer tiempoRespuesta;
    @Column(name = "TipoCalendario")
    private String tipoCalendario;
    @OneToMany(mappedBy = "tipoDocumental")
    private Collection<Documento> documentoCollection;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;
    @JoinColumn(name = "UnidadDocumental", referencedColumnName = "Id")
    @ManyToOne
    private UnidadDocumental unidadDocumental;

    public TipoDocumental() {
    }

    public TipoDocumental(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public Boolean getDocumentoExterno() {
        return documentoExterno;
    }

    public void setDocumentoExterno(Boolean documentoExterno) {
        this.documentoExterno = documentoExterno;
    }

    public String getEstampadoCronologico() {
        return estampadoCronologico;
    }

    public void setEstampadoCronologico(String estampadoCronologico) {
        this.estampadoCronologico = estampadoCronologico;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getRequiereRespuesta() {
        return requiereRespuesta;
    }

    public void setRequiereRespuesta(Boolean requiereRespuesta) {
        this.requiereRespuesta = requiereRespuesta;
    }

    public Integer getTiempoRespuesta() {
        return tiempoRespuesta;
    }

    public void setTiempoRespuesta(Integer tiempoRespuesta) {
        this.tiempoRespuesta = tiempoRespuesta;
    }

    public String getTipoCalendario() {
        return tipoCalendario;
    }

    public void setTipoCalendario(String tipoCalendario) {
        this.tipoCalendario = tipoCalendario;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Documento> getDocumentoCollection() {
        return documentoCollection;
    }

    public void setDocumentoCollection(Collection<Documento> documentoCollection) {
        this.documentoCollection = documentoCollection;
    }

    public Usuario getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Usuario creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Usuario getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(Usuario modificadoPor) {
        this.modificadoPor = modificadoPor;
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
        if (!(object instanceof TipoDocumental)) {
            return false;
        }
        TipoDocumental other = (TipoDocumental) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.TipoDocumental[ id=" + id + " ]";
    }
    
}
