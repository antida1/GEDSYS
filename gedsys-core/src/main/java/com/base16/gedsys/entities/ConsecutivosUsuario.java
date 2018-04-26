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
 * @author robert
 */
@Entity
@Table(name = "consecutivosUsuario", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsecutivosUsuario.findAll", query = "SELECT c FROM ConsecutivosUsuario c")
    , @NamedQuery(name = "ConsecutivosUsuario.findById", query = "SELECT c FROM ConsecutivosUsuario c WHERE c.id = :id")
    , @NamedQuery(name = "ConsecutivosUsuario.findByConsecutivo", query = "SELECT c FROM ConsecutivosUsuario c WHERE c.consecutivo = :consecutivo")
    , @NamedQuery(name = "ConsecutivosUsuario.findByTipo", query = "SELECT c FROM ConsecutivosUsuario c WHERE c.tipo = :tipo")
    , @NamedQuery(name = "ConsecutivosUsuario.findByFechaCreacion", query = "SELECT c FROM ConsecutivosUsuario c WHERE c.fechaCreacion = :fechaCreacion")})
public class ConsecutivosUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Consecutivo")
    private String consecutivo;
    @Column(name = "Tipo")
    private String tipo;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;

    public ConsecutivosUsuario() {
    }

    public ConsecutivosUsuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Usuario creadoPor) {
        this.creadoPor = creadoPor;
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
        if (!(object instanceof ConsecutivosUsuario)) {
            return false;
        }
        ConsecutivosUsuario other = (ConsecutivosUsuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.base16.gedsys.entities.ConsecutivosUsuario[ id=" + id + " ]";
    }
    
}
