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
@Table(name = "unidaddocumental", catalog = "gedsys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadDocumental.findAll", query = "SELECT u FROM UnidadDocumental u")
    , @NamedQuery(name = "UnidadDocumental.findById", query = "SELECT u FROM UnidadDocumental u WHERE u.id = :id")
    , @NamedQuery(name = "UnidadDocumental.findByBorrado", query = "SELECT u FROM UnidadDocumental u WHERE u.borrado = :borrado")
    , @NamedQuery(name = "UnidadDocumental.findByFechaCreacion", query = "SELECT u FROM UnidadDocumental u WHERE u.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "UnidadDocumental.findByFechaModificacion", query = "SELECT u FROM UnidadDocumental u WHERE u.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "UnidadDocumental.findByNombre", query = "SELECT u FROM UnidadDocumental u WHERE u.nombre = :nombre")})
public class UnidadDocumental implements Serializable {

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
    @OneToMany(mappedBy = "unidadDocumental")
    private Collection<TipoDocumental> tipoDocumentalCollection;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;
    @JoinColumn(name = "SubSerie", referencedColumnName = "Id")
    @ManyToOne
    private SubSerie subSerie;

    public UnidadDocumental() {
    }

    public UnidadDocumental(Integer id) {
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
    public Collection<TipoDocumental> getTipoDocumentalCollection() {
        return tipoDocumentalCollection;
    }

    public void setTipoDocumentalCollection(Collection<TipoDocumental> tipoDocumentalCollection) {
        this.tipoDocumentalCollection = tipoDocumentalCollection;
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

    public SubSerie getSubSerie() {
        return subSerie;
    }

    public void setSubSerie(SubSerie subSerie) {
        this.subSerie = subSerie;
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
        if (!(object instanceof UnidadDocumental)) {
            return false;
        }
        UnidadDocumental other = (UnidadDocumental) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.UnidadDocumental[ id=" + id + " ]";
    }
    
}
