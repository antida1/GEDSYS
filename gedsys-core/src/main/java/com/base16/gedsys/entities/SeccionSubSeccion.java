/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author rober
 */
@Entity
@Table(name = "seccionsubseccion", catalog = "gedsys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeccionSubSeccion.findAll", query = "SELECT s FROM SeccionSubSeccion s")
    , @NamedQuery(name = "SeccionSubSeccion.findById", query = "SELECT s FROM SeccionSubSeccion s WHERE s.id = :id")
    , @NamedQuery(name = "SeccionSubSeccion.findByBorrado", query = "SELECT s FROM SeccionSubSeccion s WHERE s.borrado = :borrado")
    , @NamedQuery(name = "SeccionSubSeccion.findByCodigo", query = "SELECT s FROM SeccionSubSeccion s WHERE s.codigo = :codigo")
    , @NamedQuery(name = "SeccionSubSeccion.findByFechaCreacion", query = "SELECT s FROM SeccionSubSeccion s WHERE s.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "SeccionSubSeccion.findByFechaModificacion", query = "SELECT s FROM SeccionSubSeccion s WHERE s.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "SeccionSubSeccion.findByNombre", query = "SELECT s FROM SeccionSubSeccion s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SeccionSubSeccion.findByDependeDe", query = "SELECT s FROM SeccionSubSeccion s WHERE s.dependeDe = :dependeDe")
    , @NamedQuery(name = "SeccionSubSeccion.findRoots", query = "SELECT s FROM SeccionSubSeccion s WHERE s.dependeDe is null")})
public class SeccionSubSeccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Borrado")
    private Boolean borrado;
    @Column(name = "Codigo")
    private String codigo;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Nombre")
    private String nombre;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;
    @OneToMany(mappedBy = "dependeDe")
    private Collection<SeccionSubSeccion> seccionSubSeccionCollection;
    @JoinColumn(name = "DependeDe", referencedColumnName = "Id")
    @ManyToOne
    private SeccionSubSeccion dependeDe;
    
    @JoinColumn(name = "Responsable", referencedColumnName = "Id")
    @ManyToOne
    private Usuario responsable;

    public SeccionSubSeccion() {
    }

    public SeccionSubSeccion(Integer id) {
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @XmlTransient
    @JsonIgnore
    public Collection<SeccionSubSeccion> getSeccionSubSeccionCollection() {
        return seccionSubSeccionCollection;
    }

    public void setSeccionSubSeccionCollection(Collection<SeccionSubSeccion> seccionSubSeccionCollection) {
        this.seccionSubSeccionCollection = seccionSubSeccionCollection;
    }

    public SeccionSubSeccion getDependeDe() {
        return dependeDe;
    }

    public void setDependeDe(SeccionSubSeccion dependeDe) {
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
        if (!(object instanceof SeccionSubSeccion)) {
            return false;
        }
        SeccionSubSeccion other = (SeccionSubSeccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    public Usuario getResponsable() {
        return responsable;
    }

    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }
    
}
