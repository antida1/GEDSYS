/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.entities;

import java.io.Serializable;
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
 * @author robert
 */
@Entity
@Table(name = "planillaEnvio", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanillaEnvio.findAll", query = "SELECT p FROM PlanillaEnvio p")
    , @NamedQuery(name = "PlanillaEnvio.findById", query = "SELECT p FROM PlanillaEnvio p WHERE p.id = :id")
    , @NamedQuery(name = "PlanillaEnvio.findByNombre", query = "SELECT p FROM PlanillaEnvio p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "PlanillaEnvio.findByFechaEnvio", query = "SELECT p FROM PlanillaEnvio p WHERE p.fechaEnvio = :fechaEnvio")
    , @NamedQuery(name = "PlanillaEnvio.findByFechaCreacion", query = "SELECT p FROM PlanillaEnvio p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "PlanillaEnvio.findByFechaModificacion", query = "SELECT p FROM PlanillaEnvio p WHERE p.fechaModificacion = :fechaModificacion")})
public class PlanillaEnvio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "FechaEnvio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;
    @OneToMany(mappedBy = "planillaEnvio")
    private List<PlanillaEnvioDocumento> planillaEnvioDocumentoList;

    public PlanillaEnvio() {
    }

    public PlanillaEnvio(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
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

    @XmlTransient
    @JsonIgnore
    public List<PlanillaEnvioDocumento> getPlanillaEnvioDocumentoList() {
        return planillaEnvioDocumentoList;
    }

    public void setPlanillaEnvioDocumentoList(List<PlanillaEnvioDocumento> planillaEnvioDocumentoList) {
        this.planillaEnvioDocumentoList = planillaEnvioDocumentoList;
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
        if (!(object instanceof PlanillaEnvio)) {
            return false;
        }
        PlanillaEnvio other = (PlanillaEnvio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.base16.gedsys.entities.PlanillaEnvio[ id=" + id + " ]";
    }
    
}
