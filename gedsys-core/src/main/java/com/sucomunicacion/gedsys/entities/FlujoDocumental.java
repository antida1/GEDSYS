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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
 */
@Entity
@Table(name = "FlujoDocumental", catalog = "gedsys", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FlujoDocumental.findAll", query = "SELECT f FROM FlujoDocumental f")
    , @NamedQuery(name = "FlujoDocumental.findById", query = "SELECT f FROM FlujoDocumental f WHERE f.id = :id")
    , @NamedQuery(name = "FlujoDocumental.findByOrigen", query = "SELECT f FROM FlujoDocumental f WHERE f.origen = :origen")
    , @NamedQuery(name = "FlujoDocumental.findByDestino", query = "SELECT f FROM FlujoDocumental f WHERE f.destino = :destino")
    , @NamedQuery(name = "FlujoDocumental.findByAsunto", query = "SELECT f FROM FlujoDocumental f WHERE f.asunto = :asunto")
    , @NamedQuery(name = "FlujoDocumental.findByEnviarComoCopia", query = "SELECT f FROM FlujoDocumental f WHERE f.enviarComoCopia = :enviarComoCopia")
    , @NamedQuery(name = "FlujoDocumental.findByFechaCreacion", query = "SELECT f FROM FlujoDocumental f WHERE f.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "FlujoDocumental.findByFechaModificacion", query = "SELECT f FROM FlujoDocumental f WHERE f.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "FlujoDocumental.findByCreadoPor", query = "SELECT f FROM FlujoDocumental f WHERE f.creadoPor = :creadoPor")
    , @NamedQuery(name = "FlujoDocumental.findByModificadoPor", query = "SELECT f FROM FlujoDocumental f WHERE f.modificadoPor = :modificadoPor")
    , @NamedQuery(name = "FlujoDocumental.findByBorrado", query = "SELECT f FROM FlujoDocumental f WHERE f.borrado = :borrado")})
public class FlujoDocumental implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Column(name = "Origen", length = 36)
    private String origen;
    @Column(name = "Destino", length = 50)
    private String destino;
    @Column(name = "Asunto", length = 255)
    private String asunto;
    @Column(name = "EnviarComoCopia")
    private Boolean enviarComoCopia;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "CreadoPor", length = 36)
    private String creadoPor;
    @Column(name = "ModificadoPor", length = 36)
    private String modificadoPor;
    @Column(name = "Borrado")
    private Boolean borrado;

    public FlujoDocumental() {
    }

    public FlujoDocumental(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Boolean getEnviarComoCopia() {
        return enviarComoCopia;
    }

    public void setEnviarComoCopia(Boolean enviarComoCopia) {
        this.enviarComoCopia = enviarComoCopia;
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

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FlujoDocumental)) {
            return false;
        }
        FlujoDocumental other = (FlujoDocumental) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.FlujoDocumental[ id=" + id + " ]";
    }
    
}
