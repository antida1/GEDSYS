/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.entities;

import java.io.Serializable;
import java.util.Collection;
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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author rober
 */
@Entity
@Table(name = "signaturatopografica", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SignaturaTopografica.findAll", query = "SELECT s FROM SignaturaTopografica s")
    , @NamedQuery(name = "SignaturaTopografica.findById", query = "SELECT s FROM SignaturaTopografica s WHERE s.id = :id")
    , @NamedQuery(name = "SignaturaTopografica.findByBorrado", query = "SELECT s FROM SignaturaTopografica s WHERE s.borrado = :borrado")
    , @NamedQuery(name = "SignaturaTopografica.findByCodigo", query = "SELECT s FROM SignaturaTopografica s WHERE s.codigo = :codigo")
    , @NamedQuery(name = "SignaturaTopografica.findByFechaCracion", query = "SELECT s FROM SignaturaTopografica s WHERE s.fechaCracion = :fechaCracion")
    , @NamedQuery(name = "SignaturaTopografica.findByFechaModificacion", query = "SELECT s FROM SignaturaTopografica s WHERE s.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "SignaturaTopografica.findByNivel", query = "SELECT s FROM SignaturaTopografica s WHERE s.nivel = :nivel")
    , @NamedQuery(name = "SignaturaTopografica.findByNombre", query = "SELECT s FROM SignaturaTopografica s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "SignaturaTopografica.findByDependeDe", query = "SELECT s FROM SignaturaTopografica s WHERE s.dependeDe = :dependeDe")
    , @NamedQuery(name = "SignaturaTopografica.findRoots", query = "SELECT s FROM SignaturaTopografica s WHERE s.dependeDe is null")})

public class SignaturaTopografica implements Serializable {

    @OneToMany(mappedBy = "signatura")
    private List<Usuariosignaturas> usuariosignaturasList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Borrado")
    private Boolean borrado;
    @Column(name = "Codigo")
    private String codigo;
    @Column(name = "FechaCracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCracion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Nivel")
    private Integer nivel;
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(mappedBy = "signaturaTopografica")
    private Collection<Documento> documentoCollection;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @OneToMany(mappedBy = "dependeDe")
    private Collection<SignaturaTopografica> signaturaTopograficaCollection;
    @JoinColumn(name = "DependeDe", referencedColumnName = "Id")
    @ManyToOne
    private SignaturaTopografica dependeDe;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;

    public SignaturaTopografica() {
    }

    public SignaturaTopografica(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Documento> getDocumentoCollection() {
        return documentoCollection;
    }

    public void setDocumentoCollection(Collection<Documento> documentoCollection) {
        this.documentoCollection = documentoCollection;
    }

    public Usuario getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Usuario creadoPor) {
        this.creadoPor = creadoPor;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<SignaturaTopografica> getSignaturaTopograficaCollection() {
        return signaturaTopograficaCollection;
    }

    public void setSignaturaTopograficaCollection(Collection<SignaturaTopografica> signaturaTopograficaCollection) {
        this.signaturaTopograficaCollection = signaturaTopograficaCollection;
    }

    public SignaturaTopografica getDependeDe() {
        return dependeDe;
    }

    public void setDependeDe(SignaturaTopografica dependeDe) {
        this.dependeDe = dependeDe;
    }

    public Usuario getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(Usuario modificadoPor) {
        this.modificadoPor = modificadoPor;
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
        return this.nombre;
    }

    @XmlTransient
    @JsonIgnore
    public List<Usuariosignaturas> getUsuariosignaturasList() {
        return usuariosignaturasList;
    }

    public void setUsuariosignaturasList(List<Usuariosignaturas> usuariosignaturasList) {
        this.usuariosignaturasList = usuariosignaturasList;
    }

}
