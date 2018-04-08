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
import javax.persistence.CascadeType;
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
@Table(name = "modulo", catalog = "gedsys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modulo.findAll", query = "SELECT m FROM Modulo m")
    , @NamedQuery(name = "Modulo.findById", query = "SELECT m FROM Modulo m WHERE m.id = :id")
    , @NamedQuery(name = "Modulo.findByBorrado", query = "SELECT m FROM Modulo m WHERE m.borrado = :borrado")
    , @NamedQuery(name = "Modulo.findByDescripcion", query = "SELECT m FROM Modulo m WHERE m.descripcion = :descripcion")
    , @NamedQuery(name = "Modulo.findByFechaCreacion", query = "SELECT m FROM Modulo m WHERE m.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Modulo.findByFechaModificacion", query = "SELECT m FROM Modulo m WHERE m.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Modulo.findByNombre", query = "SELECT m FROM Modulo m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "Modulo.findByOculto", query = "SELECT m FROM Modulo m WHERE m.oculto = :oculto")
    , @NamedQuery(name = "Modulo.findByDependeDe", query = "SELECT m FROM Modulo m WHERE m.dependeDe = :dependeDe")
    , @NamedQuery(name = "Modulo.findRoots", query = "SELECT m FROM Modulo m WHERE m.dependeDe is null")})
public class Modulo implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Borrado")
    private Boolean borrado;
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Oculto")
    private Boolean oculto;
    @OneToMany(mappedBy = "modulo")
    private Collection<Acl> aclCollection;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;
    @OneToMany(mappedBy = "dependeDe")
    private Collection<Modulo> moduloCollection;
    @JoinColumn(name = "DependeDe", referencedColumnName = "Id")
    @ManyToOne
    private Modulo dependeDe;
    @Column(name = "UrlModulo")
    private String urlModulo;
    @Column(name = "ModuloIcon")
    private String moduloIcon;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modulo")
    private Collection<CamposPlantilla> camposPlantillaCollection;
    
    public Modulo() {
    }

    public Modulo(Integer id) {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Boolean getOculto() {
        return oculto;
    }

    public void setOculto(Boolean oculto) {
        this.oculto = oculto;
    }

    public String getUrlModulo() {
        return urlModulo;
    }

    public void setUrlModulo(String urlModulo) {
        this.urlModulo = urlModulo;
    }

    public String getModuloIcon() {
        return moduloIcon;
    }

    public void setModuloIcon(String moduloIcon) {
        this.moduloIcon = moduloIcon;
    }

    
    
    @XmlTransient
    @JsonIgnore
    public Collection<Acl> getAclCollection() {
        return aclCollection;
    }

    public void setAclCollection(Collection<Acl> aclCollection) {
        this.aclCollection = aclCollection;
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
    public Collection<Modulo> getModuloCollection() {
        return moduloCollection;
    }

    public void setModuloCollection(Collection<Modulo> moduloCollection) {
        this.moduloCollection = moduloCollection;
    }

    public Modulo getDependeDe() {
        return dependeDe;
    }

    public void setDependeDe(Modulo dependeDe) {
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
        if (!(object instanceof Modulo)) {
            return false;
        }
        Modulo other = (Modulo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.Modulo[ id=" + id + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public Collection<CamposPlantilla> getCamposPlantillaCollection() {
        return camposPlantillaCollection;
    }

    public void setCamposPlantillaCollection(Collection<CamposPlantilla> camposPlantillaCollection) {
        this.camposPlantillaCollection = camposPlantillaCollection;
    }
    
}
