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
@Table(name = "ProcesoTipoDocumento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoTipoDocumento.findAll", query = "SELECT p FROM ProcesoTipoDocumento p")
    , @NamedQuery(name = "ProcesoTipoDocumento.findById", query = "SELECT p FROM ProcesoTipoDocumento p WHERE p.id = :id")
    , @NamedQuery(name = "ProcesoTipoDocumento.findByFechaCreacion", query = "SELECT p FROM ProcesoTipoDocumento p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "ProcesoTipoDocumento.findByFechaModificacion", query = "SELECT p FROM ProcesoTipoDocumento p WHERE p.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "ProcesoTipoDocumento.findByBorrado", query = "SELECT p FROM ProcesoTipoDocumento p WHERE p.borrado = :borrado")})
public class ProcesoTipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Borrado")
    private Boolean borrado;
    @JoinColumn(name = "Proceso", referencedColumnName = "Id")
    @ManyToOne
    private ProcesoNegocio proceso;
    @JoinColumn(name = "TipoDocumento", referencedColumnName = "Id")
    @ManyToOne
    private TipoDocumento tipoDocumento;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;

    public ProcesoTipoDocumento() {
    }

    public ProcesoTipoDocumento(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ProcesoNegocio getProceso() {
        return proceso;
    }

    public void setProceso(ProcesoNegocio proceso) {
        this.proceso = proceso;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesoTipoDocumento)) {
            return false;
        }
        ProcesoTipoDocumento other = (ProcesoTipoDocumento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.ProcesoTipoDocumento[ id=" + id + " ]";
    }
    
}
