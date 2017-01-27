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
@Table(name = "Transportador", catalog = "gedsys", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transportador.findAll", query = "SELECT t FROM Transportador t")
    , @NamedQuery(name = "Transportador.findById", query = "SELECT t FROM Transportador t WHERE t.id = :id")
    , @NamedQuery(name = "Transportador.findByTipoDocumento", query = "SELECT t FROM Transportador t WHERE t.tipoDocumento = :tipoDocumento")
    , @NamedQuery(name = "Transportador.findByNumeroDocumento", query = "SELECT t FROM Transportador t WHERE t.numeroDocumento = :numeroDocumento")
    , @NamedQuery(name = "Transportador.findByNombre", query = "SELECT t FROM Transportador t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Transportador.findByTelefono", query = "SELECT t FROM Transportador t WHERE t.telefono = :telefono")
    , @NamedQuery(name = "Transportador.findByFax", query = "SELECT t FROM Transportador t WHERE t.fax = :fax")
    , @NamedQuery(name = "Transportador.findByEmail", query = "SELECT t FROM Transportador t WHERE t.email = :email")
    , @NamedQuery(name = "Transportador.findByCelular", query = "SELECT t FROM Transportador t WHERE t.celular = :celular")
    , @NamedQuery(name = "Transportador.findByFechaCreacion", query = "SELECT t FROM Transportador t WHERE t.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Transportador.findByFechaModificacion", query = "SELECT t FROM Transportador t WHERE t.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Transportador.findByCreadoPor", query = "SELECT t FROM Transportador t WHERE t.creadoPor = :creadoPor")
    , @NamedQuery(name = "Transportador.findByModificadoPor", query = "SELECT t FROM Transportador t WHERE t.modificadoPor = :modificadoPor")
    , @NamedQuery(name = "Transportador.findByBorrado", query = "SELECT t FROM Transportador t WHERE t.borrado = :borrado")})
public class Transportador implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "TipoDocumento")
    private String tipoDocumento;
    @Column(name = "NumeroDocumento")
    private String numeroDocumento;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Telefono")
    private String telefono;
    @Column(name = "Fax")
    private String fax;
    @Column(name = "Email")
    private String email;
    @Column(name = "Celular")
    private String celular;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "CreadoPor")
    private String creadoPor;
    @Column(name = "ModificadoPor")
    private String modificadoPor;
    @Column(name = "Borrado")
    private Boolean borrado;
    @OneToMany(mappedBy = "transportador")
    private List<Documento> documentoList;

    public Transportador() {
    }

    public Transportador(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
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
    @JsonIgnore
    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
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
        if (!(object instanceof Transportador)) {
            return false;
        }
        Transportador other = (Transportador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.Transportador[ id=" + id + " ]";
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }
    
}
