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
@Table(name = "procesodocumental", catalog = "gedsys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoDocumental.findAll", query = "SELECT p FROM ProcesoDocumental p")
    , @NamedQuery(name = "ProcesoDocumental.findById", query = "SELECT p FROM ProcesoDocumental p WHERE p.id = :id")
    , @NamedQuery(name = "ProcesoDocumental.findByBorrado", query = "SELECT p FROM ProcesoDocumental p WHERE p.borrado = :borrado")
    , @NamedQuery(name = "ProcesoDocumental.findByEstado", query = "SELECT p FROM ProcesoDocumental p WHERE p.estado = :estado")
    , @NamedQuery(name = "ProcesoDocumental.findByEstadoProceso", query = "SELECT p FROM ProcesoDocumental p WHERE p.estadoProceso = :estadoProceso")
    , @NamedQuery(name = "ProcesoDocumental.findByFechaCreacion", query = "SELECT p FROM ProcesoDocumental p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "ProcesoDocumental.findByFechaFinalizacionEstimada", query = "SELECT p FROM ProcesoDocumental p WHERE p.fechaFinalizacionEstimada = :fechaFinalizacionEstimada")
    , @NamedQuery(name = "ProcesoDocumental.findByFechaFinalizacionReal", query = "SELECT p FROM ProcesoDocumental p WHERE p.fechaFinalizacionReal = :fechaFinalizacionReal")
    , @NamedQuery(name = "ProcesoDocumental.findByFechaInicioEstimada", query = "SELECT p FROM ProcesoDocumental p WHERE p.fechaInicioEstimada = :fechaInicioEstimada")
    , @NamedQuery(name = "ProcesoDocumental.findByFechaInicioReal", query = "SELECT p FROM ProcesoDocumental p WHERE p.fechaInicioReal = :fechaInicioReal")
    , @NamedQuery(name = "ProcesoDocumental.findByFechaModificacion", query = "SELECT p FROM ProcesoDocumental p WHERE p.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "ProcesoDocumental.findBySalidaProceso", query = "SELECT p FROM ProcesoDocumental p WHERE p.salidaProceso = :salidaProceso")})
public class ProcesoDocumental implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Borrado")
    private Boolean borrado;
    @Column(name = "Estado")
    private Integer estado;
    @Column(name = "EstadoProceso")
    private Integer estadoProceso;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaFinalizacionEstimada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinalizacionEstimada;
    @Column(name = "FechaFinalizacionReal")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinalizacionReal;
    @Column(name = "FechaInicioEstimada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioEstimada;
    @Column(name = "FechaInicioReal")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioReal;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "SalidaProceso")
    private Integer salidaProceso;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @JoinColumn(name = "Documento", referencedColumnName = "Id")
    @ManyToOne
    private Documento documento;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;
    @JoinColumn(name = "Proceso", referencedColumnName = "Id")
    @ManyToOne
    private ProcesoNegocio proceso;

    public ProcesoDocumental() {
    }

    public ProcesoDocumental(Integer id) {
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(Integer estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaFinalizacionEstimada() {
        return fechaFinalizacionEstimada;
    }

    public void setFechaFinalizacionEstimada(Date fechaFinalizacionEstimada) {
        this.fechaFinalizacionEstimada = fechaFinalizacionEstimada;
    }

    public Date getFechaFinalizacionReal() {
        return fechaFinalizacionReal;
    }

    public void setFechaFinalizacionReal(Date fechaFinalizacionReal) {
        this.fechaFinalizacionReal = fechaFinalizacionReal;
    }

    public Date getFechaInicioEstimada() {
        return fechaInicioEstimada;
    }

    public void setFechaInicioEstimada(Date fechaInicioEstimada) {
        this.fechaInicioEstimada = fechaInicioEstimada;
    }

    public Date getFechaInicioReal() {
        return fechaInicioReal;
    }

    public void setFechaInicioReal(Date fechaInicioReal) {
        this.fechaInicioReal = fechaInicioReal;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getSalidaProceso() {
        return salidaProceso;
    }

    public void setSalidaProceso(Integer salidaProceso) {
        this.salidaProceso = salidaProceso;
    }

    public Usuario getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Usuario creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
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
        if (!(object instanceof ProcesoDocumental)) {
            return false;
        }
        ProcesoDocumental other = (ProcesoDocumental) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.ProcesoDocumental[ id=" + id + " ]";
    }
    
}
