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
@Table(name = "acta", catalog = "gedsys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acta.findAll", query = "SELECT a FROM Acta a")
    , @NamedQuery(name = "Acta.findById", query = "SELECT a FROM Acta a WHERE a.id = :id")
    , @NamedQuery(name = "Acta.findByConsecutivo", query = "SELECT a FROM Acta a WHERE a.consecutivo = :consecutivo")
    , @NamedQuery(name = "Acta.findByFecha", query = "SELECT a FROM Acta a WHERE a.fecha = :fecha")
    , @NamedQuery(name = "Acta.findByLugar", query = "SELECT a FROM Acta a WHERE a.lugar = :lugar")
    , @NamedQuery(name = "Acta.findByFechaCreacion", query = "SELECT a FROM Acta a WHERE a.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Acta.findByFechaModificacion", query = "SELECT a FROM Acta a WHERE a.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Acta.findByFechaFirma", query = "SELECT a FROM Acta a WHERE a.fechaFirma = :fechaFirma")
    , @NamedQuery(name = "Acta.findByEstado", query = "SELECT a FROM Acta a WHERE a.estado = :estado")})
public class Acta implements Serializable {

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
    @Column(name = "Lugar")
    private String lugar;
    @Lob
    @Column(name = "Orden")
    private String orden;
    @Lob
    @Column(name = "Desarrollo")
    private String desarrollo;
    @Lob
    @Column(name = "Convocatoria")
    private String convocatoria;
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
    private Integer estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acta")
    private List<Actaausente> actaausenteList;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario modificadoPor;
    @JoinColumn(name = "Presidente", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario presidente;
    @JoinColumn(name = "Secretaria", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario secretaria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acta")
    private List<Actaasistente> actaasistenteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acta")
    private List<Actainvitado> actainvitadoList;
    @Column(name = "HoraInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicio;
    @Column(name = "HoraFinalizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFinalizacion;

    public Acta() {
    }

    public Acta(Integer id) {
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

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getDesarrollo() {
        return desarrollo;
    }

    public void setDesarrollo(String desarrollo) {
        this.desarrollo = desarrollo;
    }

    public String getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @XmlTransient
    @JsonIgnore
    public List<Actaausente> getActaausenteList() {
        return actaausenteList;
    }

    public void setActaausenteList(List<Actaausente> actaausenteList) {
        this.actaausenteList = actaausenteList;
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

    public Usuario getPresidente() {
        return presidente;
    }

    public void setPresidente(Usuario presidente) {
        this.presidente = presidente;
    }

    public Usuario getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(Usuario secretaria) {
        this.secretaria = secretaria;
    }

    @XmlTransient
    @JsonIgnore
    public List<Actaasistente> getActaasistenteList() {
        return actaasistenteList;
    }

    public void setActaasistenteList(List<Actaasistente> actaasistenteList) {
        this.actaasistenteList = actaasistenteList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Actainvitado> getActainvitadoList() {
        return actainvitadoList;
    }

    public void setActainvitadoList(List<Actainvitado> actainvitadoList) {
        this.actainvitadoList = actainvitadoList;
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
        if (!(object instanceof Acta)) {
            return false;
        }
        Acta other = (Acta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.base16.gedsys.entities.Acta[ id=" + id + " ]";
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFinalizacion() {
        return horaFinalizacion;
    }

    public void setHoraFinalizacion(Date horaFinalizacion) {
        this.horaFinalizacion = horaFinalizacion;
    }

}
