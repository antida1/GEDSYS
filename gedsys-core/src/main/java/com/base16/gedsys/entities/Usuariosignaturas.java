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
@Table(name = "usuariosignaturas", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuariosignaturas.findAll", query = "SELECT u FROM Usuariosignaturas u")
    , @NamedQuery(name = "Usuariosignaturas.findById", query = "SELECT u FROM Usuariosignaturas u WHERE u.id = :id")
    , @NamedQuery(name = "Usuariosignaturas.findByFechaCreacion", query = "SELECT u FROM Usuariosignaturas u WHERE u.fechaCreacion = :fechaCreacion")
        , @NamedQuery(name = "Usuariosignaturas.findByUsuario", query = "SELECT u FROM Usuariosignaturas u WHERE u.usuario = :usuario")
    , @NamedQuery(name = "Usuariosignaturas.findByFechaModificacion", query = "SELECT u FROM Usuariosignaturas u WHERE u.fechaModificacion = :fechaModificacion")})
public class Usuariosignaturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadorPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadorPor;
    @JoinColumn(name = "Signatura", referencedColumnName = "Id")
    @ManyToOne
    private SignaturaTopografica signatura;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id")
    @ManyToOne
    private Usuario usuario;

    public Usuariosignaturas() {
    }

    public Usuariosignaturas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Usuario getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Usuario creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Usuario getModificadorPor() {
        return modificadorPor;
    }

    public void setModificadorPor(Usuario modificadorPor) {
        this.modificadorPor = modificadorPor;
    }

    public SignaturaTopografica getSignatura() {
        return signatura;
    }

    public void setSignatura(SignaturaTopografica signatura) {
        this.signatura = signatura;
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
        if (!(object instanceof Usuariosignaturas)) {
            return false;
        }
        Usuariosignaturas other = (Usuariosignaturas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.base16.gedsys.entities.Usuariosignaturas[ id=" + id + " ]";
    }
    
}
