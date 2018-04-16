/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "monitoresproceso", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonitoresProceso.findAll", query = "SELECT m FROM MonitoresProceso m")
    , @NamedQuery(name = "MonitoresProceso.findById", query = "SELECT m FROM MonitoresProceso m WHERE m.id = :id")
    , @NamedQuery(name = "MonitoresProceso.findByBorrado", query = "SELECT m FROM MonitoresProceso m WHERE m.borrado = :borrado")
    , @NamedQuery(name = "MonitoresProceso.findByFechaCreacion", query = "SELECT m FROM MonitoresProceso m WHERE m.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "MonitoresProceso.findByFechaModificacion", query = "SELECT m FROM MonitoresProceso m WHERE m.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "MonitoresProceso.findByMonitor", query = "SELECT m FROM MonitoresProceso m WHERE m.monitor = :monitor")})
public class MonitoresProceso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Borrado")
    private Boolean borrado;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Monitor")
    private BigInteger monitor;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;
    @JoinColumn(name = "Proceso", referencedColumnName = "Id")
    @ManyToOne
    private ProcesoNegocio proceso;

    public MonitoresProceso() {
    }

    public MonitoresProceso(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public BigInteger getMonitor() {
        return monitor;
    }

    public void setMonitor(BigInteger monitor) {
        this.monitor = monitor;
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

    public ProcesoNegocio getProceso() {
        return proceso;
    }

    public void setProceso(ProcesoNegocio proceso) {
        this.proceso = proceso;
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
        if (!(object instanceof MonitoresProceso)) {
            return false;
        }
        MonitoresProceso other = (MonitoresProceso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.MonitoresProceso[ id=" + id + " ]";
    }
    
}
