/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
 */
@Entity
@Table(name = "SignaturaTopografica", catalog = "gedsys", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SignaturaTopografica.findAll", query = "SELECT s FROM SignaturaTopografica s")
    , @NamedQuery(name = "SignaturaTopografica.findById", query = "SELECT s FROM SignaturaTopografica s WHERE s.id = :id")
    , @NamedQuery(name = "SignaturaTopografica.findByNombre", query = "SELECT s FROM SignaturaTopografica s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SignaturaTopografica.findByFechaCracion", query = "SELECT s FROM SignaturaTopografica s WHERE s.fechaCracion = :fechaCracion")
    , @NamedQuery(name = "SignaturaTopografica.findByFechaModificacion", query = "SELECT s FROM SignaturaTopografica s WHERE s.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "SignaturaTopografica.findByCreadoPor", query = "SELECT s FROM SignaturaTopografica s WHERE s.creadoPor = :creadoPor")
    , @NamedQuery(name = "SignaturaTopografica.findByModificadoPor", query = "SELECT s FROM SignaturaTopografica s WHERE s.modificadoPor = :modificadoPor")
    , @NamedQuery(name = "SignaturaTopografica.findByBorrado", query = "SELECT s FROM SignaturaTopografica s WHERE s.borrado = :borrado")
    , @NamedQuery(name = "SignaturaTopografica.findByNivel", query = "SELECT s FROM SignaturaTopografica s WHERE s.nivel = :nivel")})
public class SignaturaTopografica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Column(name = "Nombre", length = 50)
    private String nombre;
    @Column(name = "FechaCracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCracion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "CreadoPor", length = 36)
    private String creadoPor;
    @Column(name = "ModificadoPor", length = 36)
    private String modificadoPor;
    @Column(name = "Borrado")
    private Boolean borrado;
    @Column(name = "Nivel")
    private Integer nivel;
    @OneToMany(mappedBy = "dependeDe")
    private List<SignaturaTopografica> signaturaTopograficaList;
    @JoinColumn(name = "DependeDe", referencedColumnName = "Id")
    @ManyToOne
    private SignaturaTopografica dependeDe;

    public SignaturaTopografica() {
    }

    public SignaturaTopografica(Integer id) {
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

    public Date getFechaCracion() {
        return fechaCracion;
    }

    public void setFechaCracion(Date fechaCracion) {
        this.fechaCracion = fechaCracion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    @XmlTransient
    public List<SignaturaTopografica> getSignaturaTopograficaList() {
        return signaturaTopograficaList;
    }

    public void setSignaturaTopograficaList(List<SignaturaTopografica> signaturaTopograficaList) {
        this.signaturaTopograficaList = signaturaTopograficaList;
    }

    public SignaturaTopografica getDependeDe() {
        return dependeDe;
    }

    public void setDependeDe(SignaturaTopografica dependeDe) {
        this.dependeDe = dependeDe;
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
        if (!(object instanceof SignaturaTopografica)) {
            return false;
        }
        SignaturaTopografica other = (SignaturaTopografica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.SignaturaTopografica[ id=" + id + " ]";
    }
    
}
