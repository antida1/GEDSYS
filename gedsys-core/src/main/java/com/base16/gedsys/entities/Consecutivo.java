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
@Table(name = "consecutivo", catalog = "gedsys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consecutivo.findAll", query = "SELECT c FROM Consecutivo c")
    , @NamedQuery(name = "Consecutivo.findById", query = "SELECT c FROM Consecutivo c WHERE c.id = :id")
    , @NamedQuery(name = "Consecutivo.findByBorrado", query = "SELECT c FROM Consecutivo c WHERE c.borrado = :borrado")
    , @NamedQuery(name = "Consecutivo.findByConsecutivo", query = "SELECT c FROM Consecutivo c WHERE c.consecutivo = :consecutivo")
    , @NamedQuery(name = "Consecutivo.findByFechaCreacion", query = "SELECT c FROM Consecutivo c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Consecutivo.findByFechaModificacion", query = "SELECT c FROM Consecutivo c WHERE c.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Consecutivo.findByPrefijo", query = "SELECT c FROM Consecutivo c WHERE c.prefijo = :prefijo")
    , @NamedQuery(name = "Consecutivo.findBySufijo", query = "SELECT c FROM Consecutivo c WHERE c.sufijo = :sufijo")
    , @NamedQuery(name = "Consecutivo.findByTipoConsecutivo", query = "SELECT c FROM Consecutivo c WHERE c.tipoConsecutivo = :tipoConsecutivo")})
public class Consecutivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Borrado")
    private Boolean borrado;
    @Column(name = "Consecutivo")
    private String consecutivo;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Prefijo")
    private String prefijo;
    @Column(name = "Sufijo")
    private String sufijo;
    @Column(name = "TipoConsecutivo")
    private String tipoConsecutivo;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;
    @JoinColumn(name = "TipoDocumento", referencedColumnName = "Id")
    @ManyToOne
    private TipoDocumento tipoDocumento;

    public Consecutivo() {
    }

    public Consecutivo(Integer id) {
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

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
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

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getSufijo() {
        return sufijo;
    }

    public void setSufijo(String sufijo) {
        this.sufijo = sufijo;
    }

    public String getTipoConsecutivo() {
        return tipoConsecutivo;
    }

    public void setTipoConsecutivo(String tipoConsecutivo) {
        this.tipoConsecutivo = tipoConsecutivo;
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

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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
        if (!(object instanceof Consecutivo)) {
            return false;
        }
        Consecutivo other = (Consecutivo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.Consecutivo[ id=" + id + " ]";
    }
    
}
