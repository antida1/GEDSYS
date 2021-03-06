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
@Table(name = "plantilladocumental", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlantillaDocumental.findAll", query = "SELECT p FROM PlantillaDocumental p")
    , @NamedQuery(name = "PlantillaDocumental.findById", query = "SELECT p FROM PlantillaDocumental p WHERE p.id = :id")
    , @NamedQuery(name = "PlantillaDocumental.findByBorrado", query = "SELECT p FROM PlantillaDocumental p WHERE p.borrado = :borrado")
    , @NamedQuery(name = "PlantillaDocumental.findByEstado", query = "SELECT p FROM PlantillaDocumental p WHERE p.estado = :estado")
    , @NamedQuery(name = "PlantillaDocumental.findByFechaCreacion", query = "SELECT p FROM PlantillaDocumental p WHERE p.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "PlantillaDocumental.findByFechaModificacion", query = "SELECT p FROM PlantillaDocumental p WHERE p.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "PlantillaDocumental.findByNombre", query = "SELECT p FROM PlantillaDocumental p WHERE p.nombre = :nombre")})
public class PlantillaDocumental implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Borrado")
    private Boolean borrado;
    @Column(name = "Estado")
    private Boolean estado;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Nombre")
    private String nombre;
    @Lob
    @Column(name = "Cuerpo")
    private String cuerpo;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;
    @JoinColumn(name = "TipoDocumento", referencedColumnName = "Id")
    @ManyToOne
    private TipoDocumento tipoDocumento;

    @Lob
    @Column(name = "Encabezado")
    private String Encabezado;
    
    @Lob
    @Column(name = "Pie")
    private String Pie;
    
    
    public PlantillaDocumental() {
    }

    public PlantillaDocumental(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTexto() {
        return cuerpo;
    }

    public void setTexto(String texto) {
        this.cuerpo = texto;
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

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getEncabezado() {
        return Encabezado;
    }

    public void setEncabezado(String Encabezado) {
        this.Encabezado = Encabezado;
    }

    public String getPie() {
        return Pie;
    }

    public void setPie(String Pie) {
        this.Pie = Pie;
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
        if (!(object instanceof PlantillaDocumental)) {
            return false;
        }
        PlantillaDocumental other = (PlantillaDocumental) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.PlantillaDocumental[ id=" + id + " ]";
    }
    
}
