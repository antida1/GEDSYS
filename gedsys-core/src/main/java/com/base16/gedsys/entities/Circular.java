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
@Table(name = "circular", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Circular.findAll", query = "SELECT c FROM Circular c")
    , @NamedQuery(name = "Circular.findById", query = "SELECT c FROM Circular c WHERE c.id = :id")
    , @NamedQuery(name = "Circular.findByConsecutivo", query = "SELECT c FROM Circular c WHERE c.consecutivo = :consecutivo")
    , @NamedQuery(name = "Circular.findByFecha", query = "SELECT c FROM Circular c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "Circular.findByGrupoDestinatario", query = "SELECT c FROM Circular c WHERE c.grupoDestinatario = :grupoDestinatario")
    , @NamedQuery(name = "Circular.findByAsunto", query = "SELECT c FROM Circular c WHERE c.asunto = :asunto")
    , @NamedQuery(name = "Circular.findByFechaCreacion", query = "SELECT c FROM Circular c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Circular.findByFechaModifiacion", query = "SELECT c FROM Circular c WHERE c.fechaModifiacion = :fechaModifiacion")
    , @NamedQuery(name = "Circular.findByFechaFirma", query = "SELECT c FROM Circular c WHERE c.fechaFirma = :fechaFirma")
    , @NamedQuery(name = "Circular.findByEstadoYUsuario", query = "SELECT c FROM Circular c WHERE c.estado = :estado AND c.remitente = :usuario")
    , @NamedQuery(name = "Circular.findByEstado", query = "SELECT c FROM Circular c WHERE c.estado = :estado")})
public class Circular implements Serializable {

    @Column(name = "Despedida")
    private String despedida;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Consecutivo")
    private String consecutivo;
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "GrupoDestinatario")
    private String grupoDestinatario;
    @Column(name = "Asunto")
    private String asunto;
    @Lob
    @Column(name = "Contenido")
    private String contenido;
    @Lob
    @Column(name = "Anexos")
    private String anexos;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModifiacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModifiacion;
    @Column(name = "FechaFirma")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFirma;
    @Column(name = "Estado")
    private Integer estado;
    @JoinColumn(name = "Remitente", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario remitente;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario modificadoPor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "circular")
    private List<Circularcc> circularccList;

    public Circular() {
    }

    public Circular(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getGrupoDestinatario() {
        return grupoDestinatario;
    }

    public void setGrupoDestinatario(String grupoDestinatario) {
        this.grupoDestinatario = grupoDestinatario;
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

    public String getAnexos() {
        return anexos;
    }

    public void setAnexos(String anexos) {
        this.anexos = anexos;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModifiacion() {
        return fechaModifiacion;
    }

    public void setFechaModifiacion(Date fechaModifiacion) {
        this.fechaModifiacion = fechaModifiacion;
    }

    public Date getFechaFirma() {
        return fechaFirma;
    }

    public void setFechaFirma(Date fechaFirma) {
        this.fechaFirma = fechaFirma;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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

    @XmlTransient
    @JsonIgnore
    public List<Circularcc> getCircularccList() {
        return circularccList;
    }

    public void setCircularccList(List<Circularcc> circularccList) {
        this.circularccList = circularccList;
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
        if (!(object instanceof Circular)) {
            return false;
        }
        Circular other = (Circular) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.base16.gedsys.entities.Circular[ id=" + id + " ]";
    }

    public String getDespedida() {
        return despedida;
    }

    public void setDespedida(String despedida) {
        this.despedida = despedida;
    }
    
}
