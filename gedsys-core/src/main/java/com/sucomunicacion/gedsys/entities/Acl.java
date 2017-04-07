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
@Table(name = "ACL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acl.findAll", query = "SELECT a FROM Acl a")
    , @NamedQuery(name = "Acl.findById", query = "SELECT a FROM Acl a WHERE a.id = :id")
    , @NamedQuery(name = "Acl.findByCanRead", query = "SELECT a FROM Acl a WHERE a.canRead = :canRead")
    , @NamedQuery(name = "Acl.findByCanCreate", query = "SELECT a FROM Acl a WHERE a.canCreate = :canCreate")
    , @NamedQuery(name = "Acl.findByCanUpdate", query = "SELECT a FROM Acl a WHERE a.canUpdate = :canUpdate")
    , @NamedQuery(name = "Acl.findByCanDelete", query = "SELECT a FROM Acl a WHERE a.canDelete = :canDelete")
    , @NamedQuery(name = "Acl.findByCanExport", query = "SELECT a FROM Acl a WHERE a.canExport = :canExport")
    , @NamedQuery(name = "Acl.findByCanGeneratePDF", query = "SELECT a FROM Acl a WHERE a.canGeneratePDF = :canGeneratePDF")
    , @NamedQuery(name = "Acl.findByFechaCreacion", query = "SELECT a FROM Acl a WHERE a.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Acl.findByFechaModificacion", query = "SELECT a FROM Acl a WHERE a.fechaModificacion = :fechaModificacion")})
public class Acl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "CanRead")
    private Boolean canRead;
    @Column(name = "CanCreate")
    private Boolean canCreate;
    @Column(name = "CanUpdate")
    private Boolean canUpdate;
    @Column(name = "CanDelete")
    private Boolean canDelete;
    @Column(name = "CanExport")
    private Boolean canExport;
    @Column(name = "CanGeneratePDF")
    private Boolean canGeneratePDF;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "Grupo", referencedColumnName = "Id")
    @ManyToOne
    private Grupo grupo;
    @JoinColumn(name = "Modulo", referencedColumnName = "Id")
    @ManyToOne
    private Modulo modulo;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;

    public Acl() {
    }

    public Acl(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getCanRead() {
        return canRead;
    }

    public void setCanRead(Boolean canRead) {
        this.canRead = canRead;
    }

    public Boolean getCanCreate() {
        return canCreate;
    }

    public void setCanCreate(Boolean canCreate) {
        this.canCreate = canCreate;
    }

    public Boolean getCanUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(Boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public Boolean getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
    }

    public Boolean getCanExport() {
        return canExport;
    }

    public void setCanExport(Boolean canExport) {
        this.canExport = canExport;
    }

    public Boolean getCanGeneratePDF() {
        return canGeneratePDF;
    }

    public void setCanGeneratePDF(Boolean canGeneratePDF) {
        this.canGeneratePDF = canGeneratePDF;
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

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acl)) {
            return false;
        }
        Acl other = (Acl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.Acl[ id=" + id + " ]";
    }
    
}
