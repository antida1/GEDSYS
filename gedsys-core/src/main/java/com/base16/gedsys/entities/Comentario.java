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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rober
 */
@Entity
@Table(name = "comentario", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comentario.findAll", query = "SELECT c FROM Comentario c")
    , @NamedQuery(name = "Comentario.findById", query = "SELECT c FROM Comentario c WHERE c.id = :id")
    , @NamedQuery(name = "Comentario.findByFechaCreacion", query = "SELECT c FROM Comentario c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Comentario.findByFechaModificacion", query = "SELECT c FROM Comentario c WHERE c.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Comentario.findByCreadoPor", query = "SELECT c FROM Comentario c WHERE c.creadoPor = :creadoPor")
    , @NamedQuery(name = "Comentario.findByDocumento", query = "SELECT c FROM Comentario c WHERE c.documento = :documento")
    , @NamedQuery(name = "Comentario.findByTipoComProd", query = "SELECT c FROM Comentario c WHERE c.tipoComentario = :tipoComentario and c.idDocProduccion = :idDocProduccion")
    , @NamedQuery(name = "Comentario.findByModificadoPor", query = "SELECT c FROM Comentario c WHERE c.modificadoPor = :modificadoPor")})

public class Comentario implements Serializable {



    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Lob
    @Column(name = "Comentario")
    private String comentario;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "Documento", referencedColumnName = "Id")
    @ManyToOne
    private Documento documento;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;
    @Column(name = "TipoComentario")
    private Integer tipoComentario;
    @Column(name = "IdDocProduccion")
    private Integer idDocProduccion;

    
    public Comentario() {
    }

    public Comentario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
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
        if (!(object instanceof Comentario)) {
            return false;
        }
        Comentario other = (Comentario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.base16.gedsys.entities.Comentario[ id=" + id + " ]";
    }

    public Integer getTipoComentario() {
        return tipoComentario;
    }

    public void setTipoComentario(Integer tipoComentario) {
        this.tipoComentario = tipoComentario;
    }

    public Integer getIdDocProduccion() {
        return idDocProduccion;
    }

    public void setIdDocProduccion(Integer idDocProduccion) {
        this.idDocProduccion = idDocProduccion;
    }

}
