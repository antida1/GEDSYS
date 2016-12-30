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
@Table(name = "Serie", catalog = "gedsys", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Serie.findAll", query = "SELECT s FROM Serie s")
    , @NamedQuery(name = "Serie.findById", query = "SELECT s FROM Serie s WHERE s.id = :id")
    , @NamedQuery(name = "Serie.findByNombre", query = "SELECT s FROM Serie s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "Serie.findByFechaCreacion", query = "SELECT s FROM Serie s WHERE s.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Serie.findByFechaModificacion", query = "SELECT s FROM Serie s WHERE s.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Serie.findByCreadoPor", query = "SELECT s FROM Serie s WHERE s.creadoPor = :creadoPor")
    , @NamedQuery(name = "Serie.findByModificadoPor", query = "SELECT s FROM Serie s WHERE s.modificadoPor = :modificadoPor")
    , @NamedQuery(name = "Serie.findByBorrado", query = "SELECT s FROM Serie s WHERE s.borrado = :borrado")})
public class Serie implements Serializable {

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
    @OneToMany(mappedBy = "serie")
    private List<SubSerie> subSerieList;
    @OneToMany(mappedBy = "serie")
    private List<Documento> documentoList;
    @JoinColumn(name = "SubSeccion", referencedColumnName = "Id")
    @ManyToOne
    private SubSeccion subSeccion;

    public Serie() {
    }

    public Serie(Integer id) {
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

    @XmlTransient
    public List<SubSerie> getSubSerieList() {
        return subSerieList;
    }

    public void setSubSerieList(List<SubSerie> subSerieList) {
        this.subSerieList = subSerieList;
    }

    @XmlTransient
    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    public SubSeccion getSubSeccion() {
        return subSeccion;
    }

    public void setSubSeccion(SubSeccion subSeccion) {
        this.subSeccion = subSeccion;
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
        if (!(object instanceof Serie)) {
            return false;
        }
        Serie other = (Serie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.Serie[ id=" + id + " ]";
    }
    
}
