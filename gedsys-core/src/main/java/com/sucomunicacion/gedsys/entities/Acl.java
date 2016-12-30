/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
 */
@Entity
@Table(name = "ACL", catalog = "gedsys", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acl.findAll", query = "SELECT a FROM Acl a")
    , @NamedQuery(name = "Acl.findById", query = "SELECT a FROM Acl a WHERE a.id = :id")
    , @NamedQuery(name = "Acl.findByGroupId", query = "SELECT a FROM Acl a WHERE a.groupId = :groupId")
    , @NamedQuery(name = "Acl.findByCanRead", query = "SELECT a FROM Acl a WHERE a.canRead = :canRead")
    , @NamedQuery(name = "Acl.findByCanCreate", query = "SELECT a FROM Acl a WHERE a.canCreate = :canCreate")
    , @NamedQuery(name = "Acl.findByCanUpdate", query = "SELECT a FROM Acl a WHERE a.canUpdate = :canUpdate")
    , @NamedQuery(name = "Acl.findByCanDelete", query = "SELECT a FROM Acl a WHERE a.canDelete = :canDelete")
    , @NamedQuery(name = "Acl.findByCanExport", query = "SELECT a FROM Acl a WHERE a.canExport = :canExport")
    , @NamedQuery(name = "Acl.findByCanGeneratePDF", query = "SELECT a FROM Acl a WHERE a.canGeneratePDF = :canGeneratePDF")})
public class Acl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Column(name = "GroupId", length = 36)
    private String groupId;
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
    @JoinColumn(name = "ModuleId", referencedColumnName = "Id")
    @ManyToOne
    private Modulo moduleId;

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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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

    public Modulo getModuleId() {
        return moduleId;
    }

    public void setModuleId(Modulo moduleId) {
        this.moduleId = moduleId;
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
