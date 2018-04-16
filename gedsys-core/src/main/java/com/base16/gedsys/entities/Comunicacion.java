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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
 * @author rober
 */
@Entity
@Table(name = "comunicacion", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comunicacion.findAll", query = "SELECT c FROM Comunicacion c"),
    @NamedQuery(name = "Comunicacion.findById", query = "SELECT c FROM Comunicacion c WHERE c.id = :id"),
    @NamedQuery(name = "Comunicacion.findByFecha", query = "SELECT c FROM Comunicacion c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Comunicacion.findByAsunto", query = "SELECT c FROM Comunicacion c WHERE c.asunto = :asunto"),
    @NamedQuery(name = "Comunicacion.findByDescripcionAnexos", query = "SELECT c FROM Comunicacion c WHERE c.descripcionAnexos = :descripcionAnexos"),
    @NamedQuery(name = "Comunicacion.findByFechaCreacion", query = "SELECT c FROM Comunicacion c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Comunicacion.findByFechaModificacion", query = "SELECT c FROM Comunicacion c WHERE c.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "Comunicacion.findByFechaFirma", query = "SELECT c FROM Comunicacion c WHERE c.fechaFirma = :fechaFirma"),
    @NamedQuery(name = "Comunicacion.findByEstadoYUsuario", query = "SELECT c FROM Comunicacion c WHERE c.estado = :estado AND c.remitente = :usuario"),
    @NamedQuery(name = "Comunicacion.findByEstado", query = "SELECT c FROM Comunicacion c WHERE c.estado = :estado")})
public class Comunicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "Asunto")
    private String asunto;
    @Column(name = "Consecutivo")
    private String consecutivo;
    @Lob
    @Column(name = "Contenido")
    private String contenido;
    @Column(name = "DescripcionAnexos")
    private String descripcionAnexos;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "FechaFirma")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFirma;
    @Column(name = "Estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comunicacion")
    private List<Comunicacioncc> comunicacionccList;
    @JoinColumn(name = "Destinatario", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario destinatario;
    @JoinColumn(name = "Remitente", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario remitente;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario modificadoPor;

    public Comunicacion() {
    }

    public Comunicacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getDescripcionAnexos() {
        return descripcionAnexos;
    }

    public void setDescripcionAnexos(String descripcionAnexos) {
        this.descripcionAnexos = descripcionAnexos;
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

    public Date getFechaFirma() {
        return fechaFirma;
    }

    public void setFechaFirma(Date fechaFirma) {
        this.fechaFirma = fechaFirma;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    @XmlTransient
    @JsonIgnore
    public List<Comunicacioncc> getComunicacionccList() {
        return comunicacionccList;
    }

    public void setComunicacionccList(List<Comunicacioncc> comunicacionccList) {
        this.comunicacionccList = comunicacionccList;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public Usuario getRemitente() {
        return remitente;
    }

    public void setRemitente(Usuario remitente) {
        this.remitente = remitente;
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
        if (!(object instanceof Comunicacion)) {
            return false;
        }
        Comunicacion other = (Comunicacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.base16.gedsys.entities.Comunicacion[ id=" + id + " ]";
    }

}
