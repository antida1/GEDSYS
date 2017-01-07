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

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
 */
@Entity
@Table(name = "Municipios", catalog = "gedsys", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Municipios.findAll", query = "SELECT m FROM Municipios m")
    , @NamedQuery(name = "Municipios.findById", query = "SELECT m FROM Municipios m WHERE m.id = :id")
    , @NamedQuery(name = "Municipios.findByCodigo", query = "SELECT m FROM Municipios m WHERE m.codigo = :codigo")
    , @NamedQuery(name = "Municipios.findByNombre", query = "SELECT m FROM Municipios m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "Municipios.findByFechaCreacion", query = "SELECT m FROM Municipios m WHERE m.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Municipios.findByFechaModificacion", query = "SELECT m FROM Municipios m WHERE m.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Municipios.findByCreadoPor", query = "SELECT m FROM Municipios m WHERE m.creadoPor = :creadoPor")
    , @NamedQuery(name = "Municipios.findByModificadoPor", query = "SELECT m FROM Municipios m WHERE m.modificadoPor = :modificadoPor")
    , @NamedQuery(name = "Municipios.findByBorrado", query = "SELECT m FROM Municipios m WHERE m.borrado = :borrado")})
public class Municipios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Column(name = "Codigo", length = 50)
    private String codigo;
    @Column(name = "Nombre", length = 100)
    private String nombre;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "CreadoPor", length = 36)
    private String creadoPor;
    @Column(name = "ModificadoPor", length = 36)
    private String modificadoPor;
    @Column(name = "Borrado")
    private Boolean borrado;
    @OneToMany(mappedBy = "municipio")
    private List<Corregimientos> corregimientosList;
    @JoinColumn(name = "Departamento", referencedColumnName = "Id")
    @ManyToOne
    private Departamentos departamento;

    public Municipios() {
    }

    public Municipios(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @XmlTransient
    public List<Corregimientos> getCorregimientosList() {
        return corregimientosList;
    }

    public void setCorregimientosList(List<Corregimientos> corregimientosList) {
        this.corregimientosList = corregimientosList;
    }

    public Departamentos getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamentos departamento) {
        this.departamento = departamento;
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
        if (!(object instanceof Municipios)) {
            return false;
        }
        Municipios other = (Municipios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.Municipios[ id=" + id + " ]";
    }
    
}
