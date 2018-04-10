/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.entities;

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
 * @author robert
 */
@Entity
@Table(name = "planillaEnvioDocumento", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanillaEnvioDocumento.findAll", query = "SELECT p FROM PlanillaEnvioDocumento p")
    , @NamedQuery(name = "PlanillaEnvioDocumento.findById", query = "SELECT p FROM PlanillaEnvioDocumento p WHERE p.id = :id")
    , @NamedQuery(name = "PlanillaEnvioDocumento.findByFechaCreacion", query = "SELECT p FROM PlanillaEnvioDocumento p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "PlanillaEnvioDocumento.findByFechaModificacion", query = "SELECT p FROM PlanillaEnvioDocumento p WHERE p.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "PlanillaEnvioDocumento.findByTransportador", query = "SELECT p FROM PlanillaEnvioDocumento p WHERE p.transportador = :transportador")
    , @NamedQuery(name = "PlanillaEnvioDocumento.findByEstado", query = "SELECT p FROM PlanillaEnvioDocumento p WHERE p.estado = :estado")})
public class PlanillaEnvioDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Transportador")
    private String transportador;
    @Column(name = "Estado")
    private Integer estado;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;
    @JoinColumn(name = "Documento", referencedColumnName = "Id")
    @ManyToOne
    private Documento documento;
    @JoinColumn(name = "PlanillaEnvio", referencedColumnName = "Id")
    @ManyToOne
    private PlanillaEnvio planillaEnvio;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;

    public PlanillaEnvioDocumento() {
    }

    public PlanillaEnvioDocumento(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getTransportador() {
        return transportador;
    }

    public void setTransportador(String transportador) {
        this.transportador = transportador;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Usuario getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(Usuario modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public PlanillaEnvio getPlanillaEnvio() {
        return planillaEnvio;
    }

    public void setPlanillaEnvio(PlanillaEnvio planillaEnvio) {
        this.planillaEnvio = planillaEnvio;
    }

    public Usuario getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Usuario creadoPor) {
        this.creadoPor = creadoPor;
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
        if (!(object instanceof PlanillaEnvioDocumento)) {
            return false;
        }
        PlanillaEnvioDocumento other = (PlanillaEnvioDocumento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.base16.gedsys.entities.PlanillaEnvioDocumento[ id=" + id + " ]";
    }
    
}
