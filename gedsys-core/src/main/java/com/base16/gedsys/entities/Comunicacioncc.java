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
@Table(name = "comunicacioncc", catalog = "gedsys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comunicacioncc.findAll", query = "SELECT c FROM Comunicacioncc c")
    , @NamedQuery(name = "Comunicacioncc.findById", query = "SELECT c FROM Comunicacioncc c WHERE c.id = :id")})
public class Comunicacioncc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @JoinColumn(name = "Comunicacion", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Comunicacion comunicacion;
    @JoinColumn(name = "ConCopiaA", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario conCopiaA;

    public Comunicacioncc() {
    }

    public Comunicacioncc(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Comunicacion getComunicacion() {
        return comunicacion;
    }

    public void setComunicacion(Comunicacion comunicacion) {
        this.comunicacion = comunicacion;
    }

    public Usuario getConCopiaA() {
        return conCopiaA;
    }

    public void setConCopiaA(Usuario conCopiaA) {
        this.conCopiaA = conCopiaA;
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
        if (!(object instanceof Comunicacioncc)) {
            return false;
        }
        Comunicacioncc other = (Comunicacioncc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.base16.gedsys.entities.Comunicacioncc[ id=" + id + " ]";
    }
    
}
