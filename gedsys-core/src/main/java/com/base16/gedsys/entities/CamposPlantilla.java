/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rober
 */
@Entity
@Table(name = "camposplantilla", catalog = "gedsys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CamposPlantilla.findAll", query = "SELECT c FROM CamposPlantilla c")
    , @NamedQuery(name = "CamposPlantilla.findById", query = "SELECT c FROM CamposPlantilla c WHERE c.id = :id")
    , @NamedQuery(name = "CamposPlantilla.findByNombre", query = "SELECT c FROM CamposPlantilla c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "CamposPlantilla.findByCreadoPor", query = "SELECT c FROM CamposPlantilla c WHERE c.creadoPor = :creadoPor")
    , @NamedQuery(name = "CamposPlantilla.findByModificadoPro", query = "SELECT c FROM CamposPlantilla c WHERE c.modificadoPor = :modificadoPor")
    , @NamedQuery(name = "CamposPlantilla.findByFechaCreacion", query = "SELECT c FROM CamposPlantilla c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CamposPlantilla.findByFechaModificacion", query = "SELECT c FROM CamposPlantilla c WHERE c.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "CamposPlantilla.findByEstado", query = "SELECT c FROM CamposPlantilla c WHERE c.estado = :estado")})
public class CamposPlantilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "CreadoPor")
    private Integer creadoPor;
    @Column(name = "ModificadoPor")
    private Integer modificadoPor;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Estado")
    private Boolean estado;
    @JoinColumn(name = "Modulo", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Modulo modulo;

    public CamposPlantilla() {
    }

    public CamposPlantilla(Integer id) {
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

    public Integer getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Integer creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Integer getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(Integer modificadoPro) {
        this.modificadoPor = modificadoPro;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
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
        if (!(object instanceof CamposPlantilla)) {
            return false;
        }
        CamposPlantilla other = (CamposPlantilla) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.base16.gedsys.entities.CamposPlantilla[ id=" + id + " ]";
    }
    
}
