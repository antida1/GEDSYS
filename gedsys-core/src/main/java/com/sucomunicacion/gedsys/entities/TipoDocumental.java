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

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
 */
@Entity
@Table(name = "TipoDocumental", catalog = "gedsys", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumental.findAll", query = "SELECT t FROM TipoDocumental t")
    , @NamedQuery(name = "TipoDocumental.findById", query = "SELECT t FROM TipoDocumental t WHERE t.id = :id")
    , @NamedQuery(name = "TipoDocumental.findByNombre", query = "SELECT t FROM TipoDocumental t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoDocumental.findByFechaCreacion", query = "SELECT t FROM TipoDocumental t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TipoDocumental.findByFechaModificacion", query = "SELECT t FROM TipoDocumental t WHERE t.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "TipoDocumental.findByCreadoPor", query = "SELECT t FROM TipoDocumental t WHERE t.creadoPor = :creadoPor")
    , @NamedQuery(name = "TipoDocumental.findByModificadoPor", query = "SELECT t FROM TipoDocumental t WHERE t.modificadoPor = :modificadoPor")
    , @NamedQuery(name = "TipoDocumental.findByBorrado", query = "SELECT t FROM TipoDocumental t WHERE t.borrado = :borrado")
    , @NamedQuery(name = "TipoDocumental.findByRequiereRespuesta", query = "SELECT t FROM TipoDocumental t WHERE t.requiereRespuesta = :requiereRespuesta")
    , @NamedQuery(name = "TipoDocumental.findByTiempoRespuesta", query = "SELECT t FROM TipoDocumental t WHERE t.tiempoRespuesta = :tiempoRespuesta")
    , @NamedQuery(name = "TipoDocumental.findByTipoCalendario", query = "SELECT t FROM TipoDocumental t WHERE t.tipoCalendario = :tipoCalendario")
    , @NamedQuery(name = "TipoDocumental.findByEstampadoCronologico", query = "SELECT t FROM TipoDocumental t WHERE t.estampadoCronologico = :estampadoCronologico")
    , @NamedQuery(name = "TipoDocumental.findByDocumentoExterno", query = "SELECT t FROM TipoDocumental t WHERE t.documentoExterno = :documentoExterno")})
public class TipoDocumental implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Column(name = "Nombre", length = 50)
    private String nombre;
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
    @Column(name = "Borrado")
    private Boolean borrado;
    @Column(name = "RequiereRespuesta")
    private Boolean requiereRespuesta;
    @Column(name = "TiempoRespuesta")
    private Integer tiempoRespuesta;
    @Column(name = "TipoCalendario", length = 1)
    private String tipoCalendario;
    @Column(name = "EstampadoCronologico", length = 1)
    private String estampadoCronologico;
    @Column(name = "DocumentoExterno")
    private Boolean documentoExterno;
    @OneToMany(mappedBy = "tipoDocumental")
    private List<Documento> documentoList;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
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

    public String getEstampadoCronologico() {
        return estampadoCronologico;
    }

    public void setEstampadoCronologico(String estampadoCronologico) {
        this.estampadoCronologico = estampadoCronologico;
    }

    public Boolean getDocumentoExterno() {
        return documentoExterno;
    }

    public void setDocumentoExterno(Boolean documentoExterno) {
        this.documentoExterno = documentoExterno;
    }

    @XmlTransient
    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
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
