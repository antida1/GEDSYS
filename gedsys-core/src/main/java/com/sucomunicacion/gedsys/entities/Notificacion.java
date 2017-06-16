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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rober
 */
@Entity
@Table(name = "notificacion", catalog = "gedsys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificacion.findAll", query = "SELECT n FROM Notificacion n")
    , @NamedQuery(name = "Notificacion.findById", query = "SELECT n FROM Notificacion n WHERE n.id = :id")
    , @NamedQuery(name = "Notificacion.findByAsunto", query = "SELECT n FROM Notificacion n WHERE n.asunto = :asunto")
    , @NamedQuery(name = "Notificacion.findByCreadorPor", query = "SELECT n FROM Notificacion n WHERE n.creadorPor = :creadorPor")
    , @NamedQuery(name = "Notificacion.findByDiasNotificacion", query = "SELECT n FROM Notificacion n WHERE n.diasNotificacion = :diasNotificacion")
    , @NamedQuery(name = "Notificacion.findByFechaCreacion", query = "SELECT n FROM Notificacion n WHERE n.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Notificacion.findByFechaModificacion", query = "SELECT n FROM Notificacion n WHERE n.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Notificacion.findByFechaNotificacion", query = "SELECT n FROM Notificacion n WHERE n.fechaNotificacion = :fechaNotificacion")
    , @NamedQuery(name = "Notificacion.findByMesesNotificacion", query = "SELECT n FROM Notificacion n WHERE n.mesesNotificacion = :mesesNotificacion")
    , @NamedQuery(name = "Notificacion.findByModificadoPor", query = "SELECT n FROM Notificacion n WHERE n.modificadoPor = :modificadoPor")
    , @NamedQuery(name = "Notificacion.findByNotificacionCorreo", query = "SELECT n FROM Notificacion n WHERE n.notificacionCorreo = :notificacionCorreo")
    , @NamedQuery(name = "Notificacion.findByNotificacionPeriodica", query = "SELECT n FROM Notificacion n WHERE n.notificacionPeriodica = :notificacionPeriodica")
    , @NamedQuery(name = "Notificacion.findByNotificacionPopup", query = "SELECT n FROM Notificacion n WHERE n.notificacionPopup = :notificacionPopup")
    , @NamedQuery(name = "Notificacion.findByNotificacionPush", query = "SELECT n FROM Notificacion n WHERE n.notificacionPush = :notificacionPush")
    , @NamedQuery(name = "Notificacion.findByPeridicidadNotificacion", query = "SELECT n FROM Notificacion n WHERE n.peridicidadNotificacion = :peridicidadNotificacion")
    , @NamedQuery(name = "Notificacion.findByTipoPeriodo", query = "SELECT n FROM Notificacion n WHERE n.tipoPeriodo = :tipoPeriodo")})
public class Notificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Asunto")
    private String asunto;
    @Column(name = "CreadorPor")
    private Integer creadorPor;
    @Lob
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "DiasNotificacion")
    private Integer diasNotificacion;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "FechaNotificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNotificacion;
    @Column(name = "MesesNotificacion")
    private Integer mesesNotificacion;
    @Column(name = "ModificadoPor")
    private Integer modificadoPor;
    @Column(name = "NotificacionCorreo")
    private Boolean notificacionCorreo;
    @Column(name = "NotificacionPeriodica")
    private Boolean notificacionPeriodica;
    @Column(name = "NotificacionPopup")
    private Boolean notificacionPopup;
    @Column(name = "NotificacionPush")
    private Boolean notificacionPush;
    @Column(name = "PeridicidadNotificacion")
    private Integer peridicidadNotificacion;
    @Column(name = "TipoPeriodo")
    private Integer tipoPeriodo;
    @JoinColumn(name = "Responsable", referencedColumnName = "Id")
    @ManyToOne
    private Usuario responsable;

    public Notificacion() {
    }

    public Notificacion(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Integer getCreadorPor() {
        return creadorPor;
    }

    public void setCreadorPor(Integer creadorPor) {
        this.creadorPor = creadorPor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDiasNotificacion() {
        return diasNotificacion;
    }

    public void setDiasNotificacion(Integer diasNotificacion) {
        this.diasNotificacion = diasNotificacion;
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

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Integer getMesesNotificacion() {
        return mesesNotificacion;
    }

    public void setMesesNotificacion(Integer mesesNotificacion) {
        this.mesesNotificacion = mesesNotificacion;
    }

    public Integer getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(Integer modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Boolean getNotificacionCorreo() {
        return notificacionCorreo;
    }

    public void setNotificacionCorreo(Boolean notificacionCorreo) {
        this.notificacionCorreo = notificacionCorreo;
    }

    public Boolean getNotificacionPeriodica() {
        return notificacionPeriodica;
    }

    public void setNotificacionPeriodica(Boolean notificacionPeriodica) {
        this.notificacionPeriodica = notificacionPeriodica;
    }

    public Boolean getNotificacionPopup() {
        return notificacionPopup;
    }

    public void setNotificacionPopup(Boolean notificacionPopup) {
        this.notificacionPopup = notificacionPopup;
    }

    public Boolean getNotificacionPush() {
        return notificacionPush;
    }

    public void setNotificacionPush(Boolean notificacionPush) {
        this.notificacionPush = notificacionPush;
    }

    public Integer getPeridicidadNotificacion() {
        return peridicidadNotificacion;
    }

    public void setPeridicidadNotificacion(Integer peridicidadNotificacion) {
        this.peridicidadNotificacion = peridicidadNotificacion;
    }

    public Integer getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(Integer tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    public Usuario getResponsable() {
        return responsable;
    }

    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
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
        if (!(object instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.Notificacion[ id=" + id + " ]";
    }
    
}
