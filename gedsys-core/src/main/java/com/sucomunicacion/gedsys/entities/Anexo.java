/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.entities;

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
@Table(name = "Anexo", catalog = "gedsys", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anexo.findAll", query = "SELECT a FROM Anexo a")
    , @NamedQuery(name = "Anexo.findById", query = "SELECT a FROM Anexo a WHERE a.id = :id")
    , @NamedQuery(name = "Anexo.findByFechaCreacion", query = "SELECT a FROM Anexo a WHERE a.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Anexo.findByFechaModificacion", query = "SELECT a FROM Anexo a WHERE a.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Anexo.findByBorrado", query = "SELECT a FROM Anexo a WHERE a.borrado = :borrado")
    , @NamedQuery(name = "Anexo.findByFolioNro", query = "SELECT a FROM Anexo a WHERE a.folioNro = :folioNro")})
public class Anexo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Borrado")
    private Boolean borrado;
    @Column(name = "FolioNro")
    private Integer folioNro;
    @JoinColumn(name = "Documento", referencedColumnName = "Id")
    @ManyToOne
    private Documento documento;
    @JoinColumn(name = "Anexo", referencedColumnName = "Id")
    @ManyToOne
    private Documento anexo;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadorPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadorPor;

    public Anexo() {
    }

    public Anexo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public Integer getFolioNro() {
        return folioNro;
    }

    public void setFolioNro(Integer folioNro) {
        this.folioNro = folioNro;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Documento getAnexo() {
        return anexo;
    }

    public void setAnexo(Documento anexo) {
        this.anexo = anexo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anexo)) {
            return false;
        }
        Anexo other = (Anexo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.Anexo[ id=" + id + " ]";
    }
    
}
