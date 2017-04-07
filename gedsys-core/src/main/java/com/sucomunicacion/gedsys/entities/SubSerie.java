/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.entities;

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
@Table(name = "SubSerie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubSerie.findAll", query = "SELECT s FROM SubSerie s")
    , @NamedQuery(name = "SubSerie.findById", query = "SELECT s FROM SubSerie s WHERE s.id = :id")
    , @NamedQuery(name = "SubSerie.findByNombre", query = "SELECT s FROM SubSerie s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SubSerie.findByFechaCreacion", query = "SELECT s FROM SubSerie s WHERE s.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "SubSerie.findByFechaModificacion", query = "SELECT s FROM SubSerie s WHERE s.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "SubSerie.findByBorrado", query = "SELECT s FROM SubSerie s WHERE s.borrado = :borrado")})
public class SubSerie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Borrado")
    private Boolean borrado;
    @JoinColumn(name = "Serie", referencedColumnName = "Id")
    @ManyToOne
    private Serie serie;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;
    @OneToMany(mappedBy = "subSerie")
    private Collection<UnidadDocumental> unidadDocumentalCollection;
    @OneToMany(mappedBy = "subSerie")
    private Collection<Documento> documentoCollection;

    public SubSerie() {
    }

    public SubSerie(Integer id) {
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

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
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
    public Collection<UnidadDocumental> getUnidadDocumentalCollection() {
        return unidadDocumentalCollection;
    }

    public void setUnidadDocumentalCollection(Collection<UnidadDocumental> unidadDocumentalCollection) {
        this.unidadDocumentalCollection = unidadDocumentalCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Documento> getDocumentoCollection() {
        return documentoCollection;
    }

    public void setDocumentoCollection(Collection<Documento> documentoCollection) {
        this.documentoCollection = documentoCollection;
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
        if (!(object instanceof SubSerie)) {
            return false;
        }
        SubSerie other = (SubSerie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.SubSerie[ id=" + id + " ]";
    }
    
}
