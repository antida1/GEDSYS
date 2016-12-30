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
@Table(name = "DiaFestivo", catalog = "gedsys", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiaFestivo.findAll", query = "SELECT d FROM DiaFestivo d")
    , @NamedQuery(name = "DiaFestivo.findById", query = "SELECT d FROM DiaFestivo d WHERE d.id = :id")
    , @NamedQuery(name = "DiaFestivo.findByDiaFestivo", query = "SELECT d FROM DiaFestivo d WHERE d.diaFestivo = :diaFestivo")
    , @NamedQuery(name = "DiaFestivo.findByFechaCreacion", query = "SELECT d FROM DiaFestivo d WHERE d.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "DiaFestivo.findByFechaModificacion", query = "SELECT d FROM DiaFestivo d WHERE d.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "DiaFestivo.findByCreadoPor", query = "SELECT d FROM DiaFestivo d WHERE d.creadoPor = :creadoPor")
    , @NamedQuery(name = "DiaFestivo.findByModificadoPor", query = "SELECT d FROM DiaFestivo d WHERE d.modificadoPor = :modificadoPor")
    , @NamedQuery(name = "DiaFestivo.findByBorrado", query = "SELECT d FROM DiaFestivo d WHERE d.borrado = :borrado")})
public class DiaFestivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Column(name = "DiaFestivo")
    @Temporal(TemporalType.DATE)
    private Date diaFestivo;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "CreadoPor")
    private Integer creadoPor;
    @Column(name = "ModificadoPor")
    private Integer modificadoPor;
    @Column(name = "Borrado")
    private Boolean borrado;

    public DiaFestivo() {
    }

    public DiaFestivo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDiaFestivo() {
        return diaFestivo;
    }

    public void setDiaFestivo(Date diaFestivo) {
        this.diaFestivo = diaFestivo;
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

    public Integer getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Integer creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Integer getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(Integer modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
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
        if (!(object instanceof DiaFestivo)) {
            return false;
        }
        DiaFestivo other = (DiaFestivo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.DiaFestivo[ id=" + id + " ]";
    }
    
}
