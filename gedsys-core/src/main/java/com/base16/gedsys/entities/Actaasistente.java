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
@Table(name = "actaasistente", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actaasistente.findAll", query = "SELECT a FROM Actaasistente a")
    , @NamedQuery(name = "Actaasistente.findById", query = "SELECT a FROM Actaasistente a WHERE a.id = :id")})
public class Actaasistente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @JoinColumn(name = "Acta", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Acta acta;
    @JoinColumn(name = "Asistente", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario asistente;

    public Actaasistente() {
    }

    public Actaasistente(Integer id) {
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

    public Usuario getAsistente() {
        return asistente;
    }

    public void setAsistente(Usuario asistente) {
        this.asistente = asistente;
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
        if (!(object instanceof Actaasistente)) {
            return false;
        }
        Actaasistente other = (Actaasistente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.base16.gedsys.entities.Actaasistente[ id=" + id + " ]";
    }
    
}
