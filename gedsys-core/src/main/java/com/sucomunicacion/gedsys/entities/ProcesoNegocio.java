/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.entities;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "procesonegocio", catalog = "gedsys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoNegocio.findAll", query = "SELECT p FROM ProcesoNegocio p")
    , @NamedQuery(name = "ProcesoNegocio.findById", query = "SELECT p FROM ProcesoNegocio p WHERE p.id = :id")
    , @NamedQuery(name = "ProcesoNegocio.findByBorrado", query = "SELECT p FROM ProcesoNegocio p WHERE p.borrado = :borrado")
    , @NamedQuery(name = "ProcesoNegocio.findByConteoDias", query = "SELECT p FROM ProcesoNegocio p WHERE p.conteoDias = :conteoDias")
    , @NamedQuery(name = "ProcesoNegocio.findByDestinatario", query = "SELECT p FROM ProcesoNegocio p WHERE p.destinatario = :destinatario")
    , @NamedQuery(name = "ProcesoNegocio.findByFechaCreacion", query = "SELECT p FROM ProcesoNegocio p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "ProcesoNegocio.findByFechaModificacion", query = "SELECT p FROM ProcesoNegocio p WHERE p.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "ProcesoNegocio.findByNombreProceso", query = "SELECT p FROM ProcesoNegocio p WHERE p.nombreProceso = :nombreProceso")
    , @NamedQuery(name = "ProcesoNegocio.findByNotificarPorCorreo", query = "SELECT p FROM ProcesoNegocio p WHERE p.notificarPorCorreo = :notificarPorCorreo")
    , @NamedQuery(name = "ProcesoNegocio.findByNotificarPorPopup", query = "SELECT p FROM ProcesoNegocio p WHERE p.notificarPorPopup = :notificarPorPopup")
    , @NamedQuery(name = "ProcesoNegocio.findByNotificarPorPush", query = "SELECT p FROM ProcesoNegocio p WHERE p.notificarPorPush = :notificarPorPush")
    , @NamedQuery(name = "ProcesoNegocio.findByNotificarPorSMS", query = "SELECT p FROM ProcesoNegocio p WHERE p.notificarPorSMS = :notificarPorSMS")
    , @NamedQuery(name = "ProcesoNegocio.findByPlazo", query = "SELECT p FROM ProcesoNegocio p WHERE p.plazo = :plazo")
    , @NamedQuery(name = "ProcesoNegocio.findByUnidadMedida", query = "SELECT p FROM ProcesoNegocio p WHERE p.unidadMedida = :unidadMedida")})
public class ProcesoNegocio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Borrado")
    private Boolean borrado;
    @Column(name = "ConteoDias")
    private Boolean conteoDias;
    @Lob
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "Destinatario")
    private Integer destinatario;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "NombreProceso")
    private String nombreProceso;
    @Column(name = "NotificarPorCorreo")
    private Boolean notificarPorCorreo;
    @Column(name = "NotificarPorPopup")
    private Boolean notificarPorPopup;
    @Column(name = "NotificarPorPush")
    private Boolean notificarPorPush;
    @Column(name = "NotificarPorSMS")
    private Boolean notificarPorSMS;
    @Column(name = "Plazo")
    private Integer plazo;
    @Column(name = "UnidadMedida")
    private Integer unidadMedida;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;
    @OneToMany(mappedBy = "siguienteProceso")
    private Collection<ProcesoNegocio> procesoNegocioCollection;
    @JoinColumn(name = "SiguienteProceso", referencedColumnName = "Id")
    @ManyToOne
    private ProcesoNegocio siguienteProceso;
    @OneToMany(mappedBy = "proceso")
    private Collection<ProcesoDocumental> procesodocumentalCollection;
    @OneToMany(mappedBy = "proceso")
    private Collection<ProcesoTipoDocumento> procesoTipoDocumentoCollection;
    @OneToMany(mappedBy = "proceso")
    private Collection<MonitoresProceso> monitoresProcesoCollection;

    public ProcesoNegocio() {
    }

    public ProcesoNegocio(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public Boolean getConteoDias() {
        return conteoDias;
    }

    public void setConteoDias(Boolean conteoDias) {
        this.conteoDias = conteoDias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Integer destinatario) {
        this.destinatario = destinatario;
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

    public String getNombreProceso() {
        return nombreProceso;
    }

    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }

    public Boolean getNotificarPorCorreo() {
        return notificarPorCorreo;
    }

    public void setNotificarPorCorreo(Boolean notificarPorCorreo) {
        this.notificarPorCorreo = notificarPorCorreo;
    }

    public Boolean getNotificarPorPopup() {
        return notificarPorPopup;
    }

    public void setNotificarPorPopup(Boolean notificarPorPopup) {
        this.notificarPorPopup = notificarPorPopup;
    }

    public Boolean getNotificarPorPush() {
        return notificarPorPush;
    }

    public void setNotificarPorPush(Boolean notificarPorPush) {
        this.notificarPorPush = notificarPorPush;
    }

    public Boolean getNotificarPorSMS() {
        return notificarPorSMS;
    }

    public void setNotificarPorSMS(Boolean notificarPorSMS) {
        this.notificarPorSMS = notificarPorSMS;
    }

    public Integer getPlazo() {
        return plazo;
    }

    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }

    public Integer getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(Integer unidadMedida) {
        this.unidadMedida = unidadMedida;
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
    public Collection<ProcesoNegocio> getProcesoNegocioCollection() {
        return procesoNegocioCollection;
    }

    public void setProcesoNegocioCollection(Collection<ProcesoNegocio> procesoNegocioCollection) {
        this.procesoNegocioCollection = procesoNegocioCollection;
    }

    public ProcesoNegocio getSiguienteProceso() {
        return siguienteProceso;
    }

    public void setSiguienteProceso(ProcesoNegocio siguienteProceso) {
        this.siguienteProceso = siguienteProceso;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ProcesoDocumental> getProcesodocumentalCollection() {
        return procesodocumentalCollection;
    }

    public void setProcesodocumentalCollection(Collection<ProcesoDocumental> procesodocumentalCollection) {
        this.procesodocumentalCollection = procesodocumentalCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ProcesoTipoDocumento> getProcesoTipoDocumentoCollection() {
        return procesoTipoDocumentoCollection;
    }

    public void setProcesoTipoDocumentoCollection(Collection<ProcesoTipoDocumento> procesoTipoDocumentoCollection) {
        this.procesoTipoDocumentoCollection = procesoTipoDocumentoCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<MonitoresProceso> getMonitoresProcesoCollection() {
        return monitoresProcesoCollection;
    }

    public void setMonitoresProcesoCollection(Collection<MonitoresProceso> monitoresProcesoCollection) {
        this.monitoresProcesoCollection = monitoresProcesoCollection;
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
        if (!(object instanceof ProcesoNegocio)) {
            return false;
        }
        ProcesoNegocio other = (ProcesoNegocio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.ProcesoNegocio[ id=" + id + " ]";
    }
    
}
