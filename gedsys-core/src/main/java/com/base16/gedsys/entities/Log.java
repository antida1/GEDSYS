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
@Table(name = "log", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l")
    , @NamedQuery(name = "Log.findById", query = "SELECT l FROM Log l WHERE l.id = :id")
    , @NamedQuery(name = "Log.findByBorrado", query = "SELECT l FROM Log l WHERE l.borrado = :borrado")
    , @NamedQuery(name = "Log.findByCampo", query = "SELECT l FROM Log l WHERE l.campo = :campo")
    , @NamedQuery(name = "Log.findByCreadoPor", query = "SELECT l FROM Log l WHERE l.creadoPor = :creadoPor")
    , @NamedQuery(name = "Log.findByFechaCreacion", query = "SELECT l FROM Log l WHERE l.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Log.findByFechaModificacion", query = "SELECT l FROM Log l WHERE l.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Log.findByModificadoPor", query = "SELECT l FROM Log l WHERE l.modificadoPor = :modificadoPor")
    , @NamedQuery(name = "Log.findByModulo", query = "SELECT l FROM Log l WHERE l.modulo = :modulo")
    , @NamedQuery(name = "Log.findByValorActual", query = "SELECT l FROM Log l WHERE l.valorActual = :valorActual")
    , @NamedQuery(name = "Log.findByValorAnterior", query = "SELECT l FROM Log l WHERE l.valorAnterior = :valorAnterior")})
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Borrado")
    private Boolean borrado;
    @Column(name = "Campo")
    private String campo;
    @Column(name = "CreadoPor")
    private Integer creadoPor;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "ModificadoPor")
    private Integer modificadoPor;
    @Column(name = "Modulo")
    private Integer modulo;
    @Column(name = "ValorActual")
    private String valorActual;
    @Column(name = "ValorAnterior")
    private String valorAnterior;

    public Log() {
    }

    public Log(Integer id) {
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

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public Integer getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Integer creadoPor) {
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

    public Integer getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(Integer modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Integer getModulo() {
        return modulo;
    }

    public void setModulo(Integer modulo) {
        this.modulo = modulo;
    }

    public String getValorActual() {
        return valorActual;
    }

    public void setValorActual(String valorActual) {
        this.valorActual = valorActual;
    }

    public String getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(String valorAnterior) {
        this.valorAnterior = valorAnterior;
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
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.Log[ id=" + id + " ]";
    }
    
}
