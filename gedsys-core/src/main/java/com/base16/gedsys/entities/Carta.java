/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "carta", catalog = "gedsys", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carta.findAll", query = "SELECT c FROM Carta c"),
    @NamedQuery(name = "Carta.findById", query = "SELECT c FROM Carta c WHERE c.id = :id"),
    @NamedQuery(name = "Carta.findByFecha", query = "SELECT c FROM Carta c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Carta.findByConsecutivo", query = "SELECT c FROM Carta c WHERE c.consecutivo = :consecutivo"),
    @NamedQuery(name = "Carta.findByTratamiento", query = "SELECT c FROM Carta c WHERE c.tratamiento = :tratamiento"),
    @NamedQuery(name = "Carta.findByDestinatario", query = "SELECT c FROM Carta c WHERE c.destinatario = :destinatario"),
    @NamedQuery(name = "Carta.findByCargo", query = "SELECT c FROM Carta c WHERE c.cargo = :cargo"),
    @NamedQuery(name = "Carta.findByAsunto", query = "SELECT c FROM Carta c WHERE c.asunto = :asunto"),
    @NamedQuery(name = "Carta.findByFechaCreacion", query = "SELECT c FROM Carta c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Carta.findByFechaModificacion", query = "SELECT c FROM Carta c WHERE c.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "Carta.findByFechaFirma", query = "SELECT c FROM Carta c WHERE c.fechaFirma = :fechaFirma"),
    @NamedQuery(name = "Carta.findByEstado", query = "SELECT c FROM Carta c WHERE c.estado = :estado"),
    @NamedQuery(name = "Carta.findByDocumentoPadre", query = "SELECT c FROM Carta c WHERE c.documentoPadre = :documentoPadre")})
public class Carta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "Consecutivo")
    private String consecutivo;
    @Column(name = "Tratamiento")
    private String tratamiento;
    @Column(name = "Destinatario")
    private String destinatario;
    @Column(name = "Cargo")
    private String cargo;
    @Column(name = "Empresa")
    private String empresa;
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "Asunto")
    private String asunto;
    @Lob
    @Column(name = "Contenido")
    private String contenido;
    @Lob
    @Column(name = "Despedida")
    private String despedida;
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
    @JoinColumn(name = "Remitente", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario remitente;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Usuario modificadoPor;
    @JoinColumn(name = "Ciudad", referencedColumnName = "Id")
    @ManyToOne
    private Municipio ciudad;
    @JoinColumn(name = "DocumentoPadre", referencedColumnName = "Id")
    @ManyToOne
    private Documento documentoPadre;

    public Carta() {
    }

    public Carta(Integer id) {
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

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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

    public String getDespedida() {
        return despedida;
    }

    public void setDespedida(String despedida) {
        this.despedida = despedida;
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
        if (!(object instanceof Carta)) {
            return false;
        }
        Carta other = (Carta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.base16.gedsys.entities.Carta[ id=" + id + " ]";
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Municipio getCiudad() {
        return ciudad;
    }

    public void setCiudad(Municipio ciudad) {
        this.ciudad = ciudad;
    }

    public Documento getDocumentoPadre() {
        return documentoPadre;
    }

    public void setDocumentoPadre(Documento documentoPadre) {
        this.documentoPadre = documentoPadre;
    }

}
