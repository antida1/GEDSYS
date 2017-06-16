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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "usuario", catalog = "gedsys", schema = "")
@XmlRootElement
@NamedQueries({
     @NamedQuery(name = "Usuario.autheticate", query = "SELECT u FROM Usuario u WHERE u.usuario = :usuario and u.clave = :clave")
    , @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id")
    , @NamedQuery(name = "Usuario.findByApelidos", query = "SELECT u FROM Usuario u WHERE u.apelidos = :apelidos")
    , @NamedQuery(name = "Usuario.findByCelular", query = "SELECT u FROM Usuario u WHERE u.celular = :celular")
    , @NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = :clave")
    , @NamedQuery(name = "Usuario.findByCreadoPor", query = "SELECT u FROM Usuario u WHERE u.creadoPor = :creadoPor")
    , @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email")
    , @NamedQuery(name = "Usuario.findByFechaCreacion", query = "SELECT u FROM Usuario u WHERE u.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Usuario.findByFechaModificacion", query = "SELECT u FROM Usuario u WHERE u.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Usuario.findByFoto", query = "SELECT u FROM Usuario u WHERE u.foto = :foto")
    , @NamedQuery(name = "Usuario.findByModificadoPor", query = "SELECT u FROM Usuario u WHERE u.modificadoPor = :modificadoPor")
    , @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres")
    , @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono")
    , @NamedQuery(name = "Usuario.findByUsuario", query = "SELECT u FROM Usuario u WHERE u.usuario = :usuario")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Apelidos")
    private String apelidos;
    @Column(name = "Celular")
    private String celular;
    @Column(name = "Clave")
    private String clave;
    @Column(name = "CreadoPor")
    private Integer creadoPor;
    @Column(name = "Email")
    private String email;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Foto")
    private String foto;
    @Column(name = "ModificadoPor")
    private Integer modificadoPor;
    @Column(name = "Nombres")
    private String nombres;
    @Column(name = "Telefono")
    private String telefono;
    @Column(name = "Usuario")
    private String usuario;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<ClaseDocumento> claseDocumentoCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<ClaseDocumento> claseDocumentoCollection1;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<Grupo> grupoCollection;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<Documento> documentoCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<Documento> documentoCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<Acl> aclCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<Acl> aclCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<SeccionSubSeccion> seccionSubSeccionCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<SeccionSubSeccion> seccionSubSeccionCollection1;
    @OneToMany(mappedBy = "usuario")
    private Collection<Preferencias> preferenciasCollection;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<PlantillaDocumental> plantillaDocumentalCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<PlantillaDocumental> plantillaDocumentalCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<Cargo> cargoCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<Cargo> cargoCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<TipoDocumento> tipoDocumentoCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<TipoDocumento> tipoDocumentoCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<SubSerie> subSerieCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<SubSerie> subSerieCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<Municipio> municipioCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<Municipio> municipioCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<ProcesoNegocio> procesoNegocioCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<ProcesoNegocio> procesoNegocioCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<Corregimiento> corregimientoCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<Corregimiento> corregimientoCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<GrupoUsuario> grupoUsuarioCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<GrupoUsuario> grupoUsuarioCollection1;
    @OneToMany(mappedBy = "usuario")
    private Collection<GrupoUsuario> grupoUsuarioCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creadoPor")
    private Collection<DestinatariosDoc> destinatariosDocCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modificadoPor")
    private Collection<DestinatariosDoc> destinatariosDocCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destinatarioId")
    private Collection<DestinatariosDoc> destinatariosDocCollection2;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<Autor> autorCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<Autor> autorCollection1;
    @OneToMany(mappedBy = "responsable")
    private Collection<Notificacion> notificacionCollection;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<Pais> paisCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<Pais> paisCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<Consecutivo> consecutivoCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<Consecutivo> consecutivoCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<SignaturaTopografica> signaturaTopograficaCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<SignaturaTopografica> signaturaTopograficaCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<TipoDocumental> tipoDocumentalCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<TipoDocumental> tipoDocumentalCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<Transportador> transportadorCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<Transportador> transportadorCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<ProcesoDocumental> procesodocumentalCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<ProcesoDocumental> procesodocumentalCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<ProcesoTipoDocumento> procesoTipoDocumentoCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<ProcesoTipoDocumento> procesoTipoDocumentoCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<Entidad> entidadCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<Entidad> entidadCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<UnidadDocumental> unidadDocumentalCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<UnidadDocumental> unidadDocumentalCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<Serie> serieCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<Serie> serieCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<Departamento> departamentoCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<Departamento> departamentoCollection1;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<MonitoresProceso> monitoresProcesoCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<MonitoresProceso> monitoresProcesoCollection1;
    @JoinColumn(name = "Cargo", referencedColumnName = "Id")
    @ManyToOne
    private Cargo cargo;
    @OneToMany(mappedBy = "creadoPor")
    private Collection<Modulo> moduloCollection;
    @OneToMany(mappedBy = "modificadoPor")
    private Collection<Modulo> moduloCollection1;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApelidos() {
        return apelidos;
    }

    public void setApelidos(String apelidos) {
        this.apelidos = apelidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Integer creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(Integer modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ClaseDocumento> getClaseDocumentoCollection() {
        return claseDocumentoCollection;
    }

    public void setClaseDocumentoCollection(Collection<ClaseDocumento> claseDocumentoCollection) {
        this.claseDocumentoCollection = claseDocumentoCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ClaseDocumento> getClaseDocumentoCollection1() {
        return claseDocumentoCollection1;
    }

    public void setClaseDocumentoCollection1(Collection<ClaseDocumento> claseDocumentoCollection1) {
        this.claseDocumentoCollection1 = claseDocumentoCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Grupo> getGrupoCollection() {
        return grupoCollection;
    }

    public void setGrupoCollection(Collection<Grupo> grupoCollection) {
        this.grupoCollection = grupoCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Documento> getDocumentoCollection() {
        return documentoCollection;
    }

    public void setDocumentoCollection(Collection<Documento> documentoCollection) {
        this.documentoCollection = documentoCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Documento> getDocumentoCollection1() {
        return documentoCollection1;
    }

    public void setDocumentoCollection1(Collection<Documento> documentoCollection1) {
        this.documentoCollection1 = documentoCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Acl> getAclCollection() {
        return aclCollection;
    }

    public void setAclCollection(Collection<Acl> aclCollection) {
        this.aclCollection = aclCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Acl> getAclCollection1() {
        return aclCollection1;
    }

    public void setAclCollection1(Collection<Acl> aclCollection1) {
        this.aclCollection1 = aclCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<SeccionSubSeccion> getSeccionSubSeccionCollection() {
        return seccionSubSeccionCollection;
    }

    public void setSeccionSubSeccionCollection(Collection<SeccionSubSeccion> seccionSubSeccionCollection) {
        this.seccionSubSeccionCollection = seccionSubSeccionCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<SeccionSubSeccion> getSeccionSubSeccionCollection1() {
        return seccionSubSeccionCollection1;
    }

    public void setSeccionSubSeccionCollection1(Collection<SeccionSubSeccion> seccionSubSeccionCollection1) {
        this.seccionSubSeccionCollection1 = seccionSubSeccionCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Preferencias> getPreferenciasCollection() {
        return preferenciasCollection;
    }

    public void setPreferenciasCollection(Collection<Preferencias> preferenciasCollection) {
        this.preferenciasCollection = preferenciasCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<PlantillaDocumental> getPlantillaDocumentalCollection() {
        return plantillaDocumentalCollection;
    }

    public void setPlantillaDocumentalCollection(Collection<PlantillaDocumental> plantillaDocumentalCollection) {
        this.plantillaDocumentalCollection = plantillaDocumentalCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<PlantillaDocumental> getPlantillaDocumentalCollection1() {
        return plantillaDocumentalCollection1;
    }

    public void setPlantillaDocumentalCollection1(Collection<PlantillaDocumental> plantillaDocumentalCollection1) {
        this.plantillaDocumentalCollection1 = plantillaDocumentalCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Cargo> getCargoCollection() {
        return cargoCollection;
    }

    public void setCargoCollection(Collection<Cargo> cargoCollection) {
        this.cargoCollection = cargoCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Cargo> getCargoCollection1() {
        return cargoCollection1;
    }

    public void setCargoCollection1(Collection<Cargo> cargoCollection1) {
        this.cargoCollection1 = cargoCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<TipoDocumento> getTipoDocumentoCollection() {
        return tipoDocumentoCollection;
    }

    public void setTipoDocumentoCollection(Collection<TipoDocumento> tipoDocumentoCollection) {
        this.tipoDocumentoCollection = tipoDocumentoCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<TipoDocumento> getTipoDocumentoCollection1() {
        return tipoDocumentoCollection1;
    }

    public void setTipoDocumentoCollection1(Collection<TipoDocumento> tipoDocumentoCollection1) {
        this.tipoDocumentoCollection1 = tipoDocumentoCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<SubSerie> getSubSerieCollection() {
        return subSerieCollection;
    }

    public void setSubSerieCollection(Collection<SubSerie> subSerieCollection) {
        this.subSerieCollection = subSerieCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<SubSerie> getSubSerieCollection1() {
        return subSerieCollection1;
    }

    public void setSubSerieCollection1(Collection<SubSerie> subSerieCollection1) {
        this.subSerieCollection1 = subSerieCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Municipio> getMunicipioCollection() {
        return municipioCollection;
    }

    public void setMunicipioCollection(Collection<Municipio> municipioCollection) {
        this.municipioCollection = municipioCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Municipio> getMunicipioCollection1() {
        return municipioCollection1;
    }

    public void setMunicipioCollection1(Collection<Municipio> municipioCollection1) {
        this.municipioCollection1 = municipioCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ProcesoNegocio> getProcesoNegocioCollection() {
        return procesoNegocioCollection;
    }

    public void setProcesoNegocioCollection(Collection<ProcesoNegocio> procesoNegocioCollection) {
        this.procesoNegocioCollection = procesoNegocioCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ProcesoNegocio> getProcesoNegocioCollection1() {
        return procesoNegocioCollection1;
    }

    public void setProcesoNegocioCollection1(Collection<ProcesoNegocio> procesoNegocioCollection1) {
        this.procesoNegocioCollection1 = procesoNegocioCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Corregimiento> getCorregimientoCollection() {
        return corregimientoCollection;
    }

    public void setCorregimientoCollection(Collection<Corregimiento> corregimientoCollection) {
        this.corregimientoCollection = corregimientoCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Corregimiento> getCorregimientoCollection1() {
        return corregimientoCollection1;
    }

    public void setCorregimientoCollection1(Collection<Corregimiento> corregimientoCollection1) {
        this.corregimientoCollection1 = corregimientoCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<GrupoUsuario> getGrupoUsuarioCollection() {
        return grupoUsuarioCollection;
    }

    public void setGrupoUsuarioCollection(Collection<GrupoUsuario> grupoUsuarioCollection) {
        this.grupoUsuarioCollection = grupoUsuarioCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<GrupoUsuario> getGrupoUsuarioCollection1() {
        return grupoUsuarioCollection1;
    }

    public void setGrupoUsuarioCollection1(Collection<GrupoUsuario> grupoUsuarioCollection1) {
        this.grupoUsuarioCollection1 = grupoUsuarioCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<GrupoUsuario> getGrupoUsuarioCollection2() {
        return grupoUsuarioCollection2;
    }

    public void setGrupoUsuarioCollection2(Collection<GrupoUsuario> grupoUsuarioCollection2) {
        this.grupoUsuarioCollection2 = grupoUsuarioCollection2;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<DestinatariosDoc> getDestinatariosDocCollection() {
        return destinatariosDocCollection;
    }

    public void setDestinatariosDocCollection(Collection<DestinatariosDoc> destinatariosDocCollection) {
        this.destinatariosDocCollection = destinatariosDocCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<DestinatariosDoc> getDestinatariosDocCollection1() {
        return destinatariosDocCollection1;
    }

    public void setDestinatariosDocCollection1(Collection<DestinatariosDoc> destinatariosDocCollection1) {
        this.destinatariosDocCollection1 = destinatariosDocCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<DestinatariosDoc> getDestinatariosDocCollection2() {
        return destinatariosDocCollection2;
    }

    public void setDestinatariosDocCollection2(Collection<DestinatariosDoc> destinatariosDocCollection2) {
        this.destinatariosDocCollection2 = destinatariosDocCollection2;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Autor> getAutorCollection() {
        return autorCollection;
    }

    public void setAutorCollection(Collection<Autor> autorCollection) {
        this.autorCollection = autorCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Autor> getAutorCollection1() {
        return autorCollection1;
    }

    public void setAutorCollection1(Collection<Autor> autorCollection1) {
        this.autorCollection1 = autorCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Notificacion> getNotificacionCollection() {
        return notificacionCollection;
    }

    public void setNotificacionCollection(Collection<Notificacion> notificacionCollection) {
        this.notificacionCollection = notificacionCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Pais> getPaisCollection() {
        return paisCollection;
    }

    public void setPaisCollection(Collection<Pais> paisCollection) {
        this.paisCollection = paisCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Pais> getPaisCollection1() {
        return paisCollection1;
    }

    public void setPaisCollection1(Collection<Pais> paisCollection1) {
        this.paisCollection1 = paisCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Consecutivo> getConsecutivoCollection() {
        return consecutivoCollection;
    }

    public void setConsecutivoCollection(Collection<Consecutivo> consecutivoCollection) {
        this.consecutivoCollection = consecutivoCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Consecutivo> getConsecutivoCollection1() {
        return consecutivoCollection1;
    }

    public void setConsecutivoCollection1(Collection<Consecutivo> consecutivoCollection1) {
        this.consecutivoCollection1 = consecutivoCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<SignaturaTopografica> getSignaturaTopograficaCollection() {
        return signaturaTopograficaCollection;
    }

    public void setSignaturaTopograficaCollection(Collection<SignaturaTopografica> signaturaTopograficaCollection) {
        this.signaturaTopograficaCollection = signaturaTopograficaCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<SignaturaTopografica> getSignaturaTopograficaCollection1() {
        return signaturaTopograficaCollection1;
    }

    public void setSignaturaTopograficaCollection1(Collection<SignaturaTopografica> signaturaTopograficaCollection1) {
        this.signaturaTopograficaCollection1 = signaturaTopograficaCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<TipoDocumental> getTipoDocumentalCollection() {
        return tipoDocumentalCollection;
    }

    public void setTipoDocumentalCollection(Collection<TipoDocumental> tipoDocumentalCollection) {
        this.tipoDocumentalCollection = tipoDocumentalCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<TipoDocumental> getTipoDocumentalCollection1() {
        return tipoDocumentalCollection1;
    }

    public void setTipoDocumentalCollection1(Collection<TipoDocumental> tipoDocumentalCollection1) {
        this.tipoDocumentalCollection1 = tipoDocumentalCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Transportador> getTransportadorCollection() {
        return transportadorCollection;
    }

    public void setTransportadorCollection(Collection<Transportador> transportadorCollection) {
        this.transportadorCollection = transportadorCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Transportador> getTransportadorCollection1() {
        return transportadorCollection1;
    }

    public void setTransportadorCollection1(Collection<Transportador> transportadorCollection1) {
        this.transportadorCollection1 = transportadorCollection1;
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
    public Collection<ProcesoDocumental> getProcesodocumentalCollection1() {
        return procesodocumentalCollection1;
    }

    public void setProcesodocumentalCollection1(Collection<ProcesoDocumental> procesodocumentalCollection1) {
        this.procesodocumentalCollection1 = procesodocumentalCollection1;
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
    public Collection<ProcesoTipoDocumento> getProcesoTipoDocumentoCollection1() {
        return procesoTipoDocumentoCollection1;
    }

    public void setProcesoTipoDocumentoCollection1(Collection<ProcesoTipoDocumento> procesoTipoDocumentoCollection1) {
        this.procesoTipoDocumentoCollection1 = procesoTipoDocumentoCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Entidad> getEntidadCollection() {
        return entidadCollection;
    }

    public void setEntidadCollection(Collection<Entidad> entidadCollection) {
        this.entidadCollection = entidadCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Entidad> getEntidadCollection1() {
        return entidadCollection1;
    }

    public void setEntidadCollection1(Collection<Entidad> entidadCollection1) {
        this.entidadCollection1 = entidadCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<UnidadDocumental> getUnidadDocumentalCollection() {
        return unidadDocumentalCollection;
    }

    public void setUnidadDocumentalCollection(Collection<UnidadDocumental> unidadDocumentalCollection) {
        this.unidadDocumentalCollection = unidadDocumentalCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<UnidadDocumental> getUnidadDocumentalCollection1() {
        return unidadDocumentalCollection1;
    }

    public void setUnidadDocumentalCollection1(Collection<UnidadDocumental> unidadDocumentalCollection1) {
        this.unidadDocumentalCollection1 = unidadDocumentalCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Serie> getSerieCollection() {
        return serieCollection;
    }

    public void setSerieCollection(Collection<Serie> serieCollection) {
        this.serieCollection = serieCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Serie> getSerieCollection1() {
        return serieCollection1;
    }

    public void setSerieCollection1(Collection<Serie> serieCollection1) {
        this.serieCollection1 = serieCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Departamento> getDepartamentoCollection() {
        return departamentoCollection;
    }

    public void setDepartamentoCollection(Collection<Departamento> departamentoCollection) {
        this.departamentoCollection = departamentoCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Departamento> getDepartamentoCollection1() {
        return departamentoCollection1;
    }

    public void setDepartamentoCollection1(Collection<Departamento> departamentoCollection1) {
        this.departamentoCollection1 = departamentoCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<MonitoresProceso> getMonitoresProcesoCollection() {
        return monitoresProcesoCollection;
    }

    public void setMonitoresProcesoCollection(Collection<MonitoresProceso> monitoresProcesoCollection) {
        this.monitoresProcesoCollection = monitoresProcesoCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<MonitoresProceso> getMonitoresProcesoCollection1() {
        return monitoresProcesoCollection1;
    }

    public void setMonitoresProcesoCollection1(Collection<MonitoresProceso> monitoresProcesoCollection1) {
        this.monitoresProcesoCollection1 = monitoresProcesoCollection1;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Modulo> getModuloCollection() {
        return moduloCollection;
    }

    public void setModuloCollection(Collection<Modulo> moduloCollection) {
        this.moduloCollection = moduloCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Modulo> getModuloCollection1() {
        return moduloCollection1;
    }

    public void setModuloCollection1(Collection<Modulo> moduloCollection1) {
        this.moduloCollection1 = moduloCollection1;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.Usuario[ id=" + id + " ]";
    }
    
}
