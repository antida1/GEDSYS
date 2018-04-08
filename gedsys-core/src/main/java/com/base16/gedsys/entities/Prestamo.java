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
@Table(name = "prestamo", catalog = "gedsys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p")
    , @NamedQuery(name = "Prestamo.findById", query = "SELECT p FROM Prestamo p WHERE p.id = :id")
    , @NamedQuery(name = "Prestamo.findByFechaInicial", query = "SELECT p FROM Prestamo p WHERE p.fechaInicial = :fechaInicial")
    , @NamedQuery(name = "Prestamo.findByFechaVencimiento", query = "SELECT p FROM Prestamo p WHERE p.fechaVencimiento = :fechaVencimiento")
    , @NamedQuery(name = "Prestamo.findByNombreDocumento", query = "SELECT p FROM Prestamo p WHERE p.nombreDocumento = :nombreDocumento")
    , @NamedQuery(name = "Prestamo.findByTipoDocumento", query = "SELECT p FROM Prestamo p WHERE p.tipoDocumento = :tipoDocumento")
    , @NamedQuery(name = "Prestamo.findByPrestadoA", query = "SELECT p FROM Prestamo p WHERE p.prestadoA = :prestadoA")
    , @NamedQuery(name = "Prestamo.findByFechaDevolucion", query = "SELECT p FROM Prestamo p WHERE p.fechaDevolucion = :fechaDevolucion")
    , @NamedQuery(name = "Prestamo.findByQuienDevuelve", query = "SELECT p FROM Prestamo p WHERE p.quienDevuelve = :quienDevuelve")
    , @NamedQuery(name = "Prestamo.findByQueinRecibe", query = "SELECT p FROM Prestamo p WHERE p.queinRecibe = :queinRecibe")
    , @NamedQuery(name = "Prestamo.findByEstado", query = "SELECT p FROM Prestamo p WHERE p.estado = :estado")
    , @NamedQuery(name = "Prestamo.findByFechaCreacion", query = "SELECT p FROM Prestamo p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Prestamo.findByCreadoPor", query = "SELECT p FROM Prestamo p WHERE p.creadoPor = :creadoPor")
    , @NamedQuery(name = "Prestamo.findByDocumento", query = "SELECT p FROM Prestamo p WHERE p.documento = :documento")
    , @NamedQuery(name = "Prestamo.findByFechaModificacion", query = "SELECT p FROM Prestamo p WHERE p.fechaModificacion = :fechaModificacion")})
public class Prestamo implements Serializable {

   

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "FechaInicial")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicial;
    @Column(name = "FechaVencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @Column(name = "NombreDocumento")
    private String nombreDocumento;
    @Column(name = "TipoDocumento")
    private String tipoDocumento;
    @Column(name = "QuienDevuelve")
    private String quienDevuelve;
    @Column(name = "QueinRecibe")
    private String queinRecibe;
    @Column(name = "Estado")
    private int estado;
    @Column(name = "EstadoAnterior")
    private Integer estadoAnterior;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "PrestadoA", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario prestadoA;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    private Usuario modificadoPor;
    @JoinColumn(name = "Documento", referencedColumnName = "Id")
    private Documento documento;
    @Lob
    @Column(name = "Observaciones")
    private String observaciones;
    @Lob
    @Column(name = "Firma")
    private String firma;
    @Column(name = "FechaDevolucion")
    @Temporal(TemporalType.DATE)
    private Date fechaDevolucion;

    public Prestamo() {
        
    }

    public Prestamo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicial() {
        return new Date();
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getQuienDevuelve() {
        return quienDevuelve;
    }

    public void setQuienDevuelve(String quienDevuelve) {
        this.quienDevuelve = quienDevuelve;
    }

    public String getQueinRecibe() {
        return queinRecibe;
    }

    public void setQueinRecibe(String queinRecibe) {
        this.queinRecibe = queinRecibe;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public Usuario getPrestadoA() {
        return prestadoA;
    }

    public void setPrestadoA(Usuario prestadoA) {
        this.prestadoA = prestadoA;
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

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.base16.gedsys.entities.Prestamo[ id=" + id + " ]";
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Integer getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(Integer estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

}
