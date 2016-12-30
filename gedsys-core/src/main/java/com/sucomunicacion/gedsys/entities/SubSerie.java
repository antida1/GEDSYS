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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
 */
@Entity
@Table(name = "SubSerie", catalog = "gedsys", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubSerie.findAll", query = "SELECT s FROM SubSerie s")
    , @NamedQuery(name = "SubSerie.findById", query = "SELECT s FROM SubSerie s WHERE s.id = :id")
    , @NamedQuery(name = "SubSerie.findByBorrado", query = "SELECT s FROM SubSerie s WHERE s.borrado = :borrado")
    , @NamedQuery(name = "SubSerie.findByCreadoPor", query = "SELECT s FROM SubSerie s WHERE s.creadoPor = :creadoPor")
    , @NamedQuery(name = "SubSerie.findByFechaCreacion", query = "SELECT s FROM SubSerie s WHERE s.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "SubSerie.findByFechaModificacion", query = "SELECT s FROM SubSerie s WHERE s.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "SubSerie.findByModificadoPor", query = "SELECT s FROM SubSerie s WHERE s.modificadoPor = :modificadoPor")
    , @NamedQuery(name = "SubSerie.findByNombre", query = "SELECT s FROM SubSerie s WHERE s.nombre = :nombre")})
public class SubSerie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Column(name = "Borrado")
    private Boolean borrado;
    @Column(name = "CreadoPor", length = 36)
    private String creadoPor;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "ModificadoPor", length = 36)
    private String modificadoPor;
    @Column(name = "Nombre", length = 50)
    private String nombre;
    @JoinColumn(name = "Serie", referencedColumnName = "Id")
    @ManyToOne
    private Serie serie;
    @OneToMany(mappedBy = "subSerie")
    private List<UnidadDocumental> unidadDocumentalList;

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

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
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

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @XmlTransient
    @JsonIgnore
    public List<UnidadDocumental> getUnidadDocumentalList() {
        return unidadDocumentalList;
    }

    public void setUnidadDocumentalList(List<UnidadDocumental> unidadDocumentalList) {
        this.unidadDocumentalList = unidadDocumentalList;
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
