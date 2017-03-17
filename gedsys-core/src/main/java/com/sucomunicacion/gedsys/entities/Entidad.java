/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.entities;

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
@Table(name = "Entidad", catalog = "gedsys", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entidad.findAll", query = "SELECT e FROM Entidad e")
    , @NamedQuery(name = "Entidad.findById", query = "SELECT e FROM Entidad e WHERE e.id = :id")
    , @NamedQuery(name = "Entidad.findByNumeroDocumento", query = "SELECT e FROM Entidad e WHERE e.numeroDocumento = :numeroDocumento")
    , @NamedQuery(name = "Entidad.findByTipoDocumento", query = "SELECT e FROM Entidad e WHERE e.tipoDocumento = :tipoDocumento")
    , @NamedQuery(name = "Entidad.findByNombre", query = "SELECT e FROM Entidad e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Entidad.findByDireccion", query = "SELECT e FROM Entidad e WHERE e.direccion = :direccion")
    , @NamedQuery(name = "Entidad.findByTelefono", query = "SELECT e FROM Entidad e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Entidad.findByEmail", query = "SELECT e FROM Entidad e WHERE e.email = :email")
    , @NamedQuery(name = "Entidad.findByFechaCreacion", query = "SELECT e FROM Entidad e WHERE e.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Entidad.findByFechaModificacion", query = "SELECT e FROM Entidad e WHERE e.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Entidad.findByBorrado", query = "SELECT e FROM Entidad e WHERE e.borrado = :borrado")})
public class Entidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "NumeroDocumento")
    private String numeroDocumento;
    @Column(name = "TipoDocumento")
    private String tipoDocumento;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "Telefono")
    private String telefono;
    @Column(name = "Email")
    private String email;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Borrado")
    private Boolean borrado;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;
    @OneToMany(mappedBy = "entidad")
    private Collection<Documento> documentoCollection;

    public Entidad() {
    }

    public Entidad(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
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
    public Collection<Documento> getDocumentoCollection() {
        return documentoCollection;
    }

    public void setDocumentoCollection(Collection<Documento> documentoCollection) {
        this.documentoCollection = documentoCollection;
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
        if (!(object instanceof Entidad)) {
            return false;
        }
        Entidad other = (Entidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.Entidad[ id=" + id + " ]";
    }
    
}
