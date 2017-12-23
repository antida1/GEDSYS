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
@Table(name = "tipodocumento", catalog = "gedsys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t")
    , @NamedQuery(name = "TipoDocumento.findById", query = "SELECT t FROM TipoDocumento t WHERE t.id = :id")
    , @NamedQuery(name = "TipoDocumento.findByBorrado", query = "SELECT t FROM TipoDocumento t WHERE t.borrado = :borrado")
    , @NamedQuery(name = "TipoDocumento.findByFechaCreacion", query = "SELECT t FROM TipoDocumento t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "TipoDocumento.findByFechaModificacion", query = "SELECT t FROM TipoDocumento t WHERE t.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "TipoDocumento.findByNombre", query = "SELECT t FROM TipoDocumento t WHERE t.nombre = :nombre")})
public class TipoDocumento implements Serializable {

    @Column(name = "RequiereRespuesta")
    private Short requiereRespuesta;
    @Column(name = "EsPQRSF")
    private Short esPQRSF;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Borrado")
    private Boolean borrado;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(mappedBy = "tipoDocumento")
    private Collection<Documento> documentoCollection;
    @OneToMany(mappedBy = "tipoDocumento")
    private Collection<PlantillaDocumental> plantillaDocumentalCollection;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;
    @OneToMany(mappedBy = "tipoDocumento")
    private Collection<Consecutivo> consecutivoCollection;
    @OneToMany(mappedBy = "tipoDocumento")
    private Collection<ProcesoTipoDocumento> procesoTipoDocumentoCollection;
    @Column(name = "DiasRespuesta")
    private Integer diasRespuesta;
    @Column(name = "TipoCalendario")
    private String tipoCalendario;
    
    public TipoDocumento() {
    }

    public TipoDocumento(Integer id) {
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

    @XmlTransient
    @JsonIgnore
    public Collection<Documento> getDocumentoCollection() {
        return documentoCollection;
    }

    public void setDocumentoCollection(Collection<Documento> documentoCollection) {
        this.documentoCollection = documentoCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<PlantillaDocumental> getPlantillaDocumentalCollection() {
        return plantillaDocumentalCollection;
    }

    public void setPlantillaDocumentalCollection(Collection<PlantillaDocumental> plantillaDocumentalCollection) {
        this.plantillaDocumentalCollection = plantillaDocumentalCollection;
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

    @XmlTransient
    @JsonIgnore
    public Collection<Consecutivo> getConsecutivoCollection() {
        return consecutivoCollection;
    }

    public void setConsecutivoCollection(Collection<Consecutivo> consecutivoCollection) {
        this.consecutivoCollection = consecutivoCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ProcesoTipoDocumento> getProcesoTipoDocumentoCollection() {
        return procesoTipoDocumentoCollection;
    }

    public void setProcesoTipoDocumentoCollection(Collection<ProcesoTipoDocumento> procesoTipoDocumentoCollection) {
        this.procesoTipoDocumentoCollection = procesoTipoDocumentoCollection;
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
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.TipoDocumento[ id=" + id + " ]";
    }


    public Integer getDiasRespuesta() {
        return diasRespuesta;
    }

    public void setDiasRespuesta(Integer diasRespuesta) {
        this.diasRespuesta = diasRespuesta;
    }

    public String getTipoCalendario() {
        return tipoCalendario;
    }

    public void setTipoCalendario(String tipoCalendario) {
        this.tipoCalendario = tipoCalendario;
    }

    public Short getRequiereRespuesta() {
        return requiereRespuesta;
    }

    public void setRequiereRespuesta(Short requiereRespuesta) {
        this.requiereRespuesta = requiereRespuesta;
    }

    public Short getEsPQRSF() {
        return esPQRSF;
    }

    public void setEsPQRSF(Short esPQRSF) {
        this.esPQRSF = esPQRSF;
    }
    
}
