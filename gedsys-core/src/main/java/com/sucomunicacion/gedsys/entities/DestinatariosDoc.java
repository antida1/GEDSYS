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
@Table(name = "destinatariosdoc", catalog = "gedsys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DestinatariosDoc.findAll", query = "SELECT d FROM DestinatariosDoc d")
    , @NamedQuery(name = "DestinatariosDoc.findById", query = "SELECT d FROM DestinatariosDoc d WHERE d.id = :id")
    , @NamedQuery(name = "DestinatariosDoc.findByFechaCreacion", query = "SELECT d FROM DestinatariosDoc d WHERE d.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "DestinatariosDoc.findByFechaModificacion", query = "SELECT d FROM DestinatariosDoc d WHERE d.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "DestinatariosDoc.findByPrincipal", query = "SELECT d FROM DestinatariosDoc d WHERE d.principal = :principal")})
public class DestinatariosDoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Principal")
    private Short principal;
    @JoinColumn(name = "Documento_Id", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Documento documentoId;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario modificadoPor;
    @JoinColumn(name = "DestinatarioId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario destinatarioId;

    public DestinatariosDoc() {
    }

    public DestinatariosDoc(Long id) {
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

    public Short getPrincipal() {
        return principal;
    }

    public void setPrincipal(Short principal) {
        this.principal = principal;
    }

    public Documento getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(Documento documentoId) {
        this.documentoId = documentoId;
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

    public Usuario getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(Usuario destinatarioId) {
        this.destinatarioId = destinatarioId;
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
        if (!(object instanceof DestinatariosDoc)) {
            return false;
        }
        DestinatariosDoc other = (DestinatariosDoc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.DestinatariosDoc[ id=" + id + " ]";
    }
    
}
