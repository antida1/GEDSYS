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
@Table(name = "preferencias", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preferencias.findAll", query = "SELECT p FROM Preferencias p")
    , @NamedQuery(name = "Preferencias.findById", query = "SELECT p FROM Preferencias p WHERE p.id = :id")
    , @NamedQuery(name = "Preferencias.findByBorrado", query = "SELECT p FROM Preferencias p WHERE p.borrado = :borrado")
    , @NamedQuery(name = "Preferencias.findByCreadoPor", query = "SELECT p FROM Preferencias p WHERE p.creadoPor = :creadoPor")
    , @NamedQuery(name = "Preferencias.findByFechaCreacion", query = "SELECT p FROM Preferencias p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Preferencias.findByFechaModificacion", query = "SELECT p FROM Preferencias p WHERE p.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Preferencias.findByModificadoPor", query = "SELECT p FROM Preferencias p WHERE p.modificadoPor = :modificadoPor")
    , @NamedQuery(name = "Preferencias.findByNombre", query = "SELECT p FROM Preferencias p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Preferencias.findByValor", query = "SELECT p FROM Preferencias p WHERE p.valor = :valor")})
public class Preferencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Borrado")
    private Boolean borrado;
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
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Valor")
    private String valor;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id")
    @ManyToOne
    private Usuario usuario;

    public Preferencias() {
    }

    public Preferencias(Integer id) {
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof Preferencias)) {
            return false;
        }
        Preferencias other = (Preferencias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.Preferencias[ id=" + id + " ]";
    }
    
}
