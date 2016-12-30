/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
 */
@Entity
@Table(name = "Corregimientos", catalog = "gedsys", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Corregimientos.findAll", query = "SELECT c FROM Corregimientos c")
    , @NamedQuery(name = "Corregimientos.findById", query = "SELECT c FROM Corregimientos c WHERE c.id = :id")
    , @NamedQuery(name = "Corregimientos.findByCodigo", query = "SELECT c FROM Corregimientos c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "Corregimientos.findByNombre", query = "SELECT c FROM Corregimientos c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Corregimientos.findByFechaCreacion", query = "SELECT c FROM Corregimientos c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Corregimientos.findByFechaModificacion", query = "SELECT c FROM Corregimientos c WHERE c.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Corregimientos.findByCreadoPor", query = "SELECT c FROM Corregimientos c WHERE c.creadoPor = :creadoPor")
    , @NamedQuery(name = "Corregimientos.findByModificadoPor", query = "SELECT c FROM Corregimientos c WHERE c.modificadoPor = :modificadoPor")
    , @NamedQuery(name = "Corregimientos.findByBorrado", query = "SELECT c FROM Corregimientos c WHERE c.borrado = :borrado")})
public class Corregimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Column(name = "Codigo", length = 50)
    private String codigo;
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
    @JoinColumn(name = "Municipio", referencedColumnName = "Id")
    @ManyToOne
    private Municipios municipio;

    public Corregimientos() {
    }

    public Corregimientos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Municipios getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipios municipio) {
        this.municipio = municipio;
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
        if (!(object instanceof Corregimientos)) {
            return false;
        }
        Corregimientos other = (Corregimientos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.Corregimientos[ id=" + id + " ]";
    }
    
}
