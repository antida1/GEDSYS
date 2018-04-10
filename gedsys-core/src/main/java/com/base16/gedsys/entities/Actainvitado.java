/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rober
 */
@Entity
@Table(name = "actainvitado", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actainvitado.findAll", query = "SELECT a FROM Actainvitado a")
    , @NamedQuery(name = "Actainvitado.findById", query = "SELECT a FROM Actainvitado a WHERE a.id = :id")})
public class Actainvitado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @JoinColumn(name = "Acta", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Acta acta;
    @JoinColumn(name = "Invitado", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario invitado;

    public Actainvitado() {
    }

    public Actainvitado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Acta getActa() {
        return acta;
    }

    public void setActa(Acta acta) {
        this.acta = acta;
    }

    public Usuario getInvitado() {
        return invitado;
    }

    public void setInvitado(Usuario invitado) {
        this.invitado = invitado;
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
        if (!(object instanceof Actainvitado)) {
            return false;
        }
        Actainvitado other = (Actainvitado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.base16.gedsys.entities.Actainvitado[ id=" + id + " ]";
    }
    
}
