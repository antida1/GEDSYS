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
@Table(name = "Documento", catalog = "gedsys", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d")
    , @NamedQuery(name = "Documento.findById", query = "SELECT d FROM Documento d WHERE d.id = :id")
    , @NamedQuery(name = "Documento.findByConsecutivo", query = "SELECT d FROM Documento d WHERE d.consecutivo = :consecutivo")
    , @NamedQuery(name = "Documento.findByAsunto", query = "SELECT d FROM Documento d WHERE d.asunto = :asunto")
    , @NamedQuery(name = "Documento.findByRutaArchivo", query = "SELECT d FROM Documento d WHERE d.rutaArchivo = :rutaArchivo")
    , @NamedQuery(name = "Documento.findByRequiereRespuesta", query = "SELECT d FROM Documento d WHERE d.requiereRespuesta = :requiereRespuesta")
    , @NamedQuery(name = "Documento.findByDireccion", query = "SELECT d FROM Documento d WHERE d.direccion = :direccion")
    , @NamedQuery(name = "Documento.findByFechaDocumento", query = "SELECT d FROM Documento d WHERE d.fechaDocumento = :fechaDocumento")
    , @NamedQuery(name = "Documento.findByRemitente", query = "SELECT d FROM Documento d WHERE d.remitente = :remitente")
    , @NamedQuery(name = "Documento.findByDestinatario", query = "SELECT d FROM Documento d WHERE d.destinatario = :destinatario")
    , @NamedQuery(name = "Documento.findByCodigoPostal", query = "SELECT d FROM Documento d WHERE d.codigoPostal = :codigoPostal")
    , @NamedQuery(name = "Documento.findByAnexos", query = "SELECT d FROM Documento d WHERE d.anexos = :anexos")
    , @NamedQuery(name = "Documento.findByFolios", query = "SELECT d FROM Documento d WHERE d.folios = :folios")
    , @NamedQuery(name = "Documento.findByLibros", query = "SELECT d FROM Documento d WHERE d.libros = :libros")
    , @NamedQuery(name = "Documento.findByMedioEnvio", query = "SELECT d FROM Documento d WHERE d.medioEnvio = :medioEnvio")
    , @NamedQuery(name = "Documento.findByFechaCreacion", query = "SELECT d FROM Documento d WHERE d.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Documento.findByFechaModificacion", query = "SELECT d FROM Documento d WHERE d.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Documento.findByEstado", query = "SELECT d FROM Documento d WHERE d.estado = :estado")
    , @NamedQuery(name = "Documento.findByMimeType", query = "SELECT d FROM Documento d WHERE d.mimeType = :mimeType")
    , @NamedQuery(name = "Documento.findByNombreDocumento", query = "SELECT d FROM Documento d WHERE d.nombreDocumento = :nombreDocumento")
    , @NamedQuery(name = "Documento.findByPathFile", query = "SELECT d FROM Documento d WHERE d.pathFile = :pathFile")
    , @NamedQuery(name = "Documento.findByExtension", query = "SELECT d FROM Documento d WHERE d.extension = :extension")
    , @NamedQuery(name = "Documento.findByFolioNro", query = "SELECT d FROM Documento d WHERE d.folioNro = :folioNro")})
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Consecutivo")
    private String consecutivo;
    @Column(name = "Asunto")
    private String asunto;
    @Column(name = "RutaArchivo")
    private String rutaArchivo;
    @Column(name = "RequiereRespuesta")
    private Boolean requiereRespuesta;
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "FechaDocumento")
    @Temporal(TemporalType.DATE)
    private Date fechaDocumento;
    @Column(name = "Remitente")
    private String remitente;
    @Column(name = "Destinatario")
    private Integer destinatario;
    @Column(name = "CodigoPostal")
    private String codigoPostal;
    @Column(name = "Anexos")
    private Boolean anexos;
    @Column(name = "Folios")
    private Integer folios;
    @Column(name = "Libros")
    private Integer libros;
    @Column(name = "MedioEnvio")
    private String medioEnvio;
    @Lob
    @Column(name = "Detalle")
    private String detalle;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Estado")
    private Integer estado;
    @Column(name = "MimeType")
    private String mimeType;
    @Column(name = "NombreDocumento")
    private String nombreDocumento;
    @Column(name = "PathFile")
    private String pathFile;
    @Column(name = "Extension")
    private String extension;
    @Column(name = "FolioNro")
    private Integer folioNro;
    @OneToMany(mappedBy = "documento")
    private Collection<Anexo> anexoCollection;
    @OneToMany(mappedBy = "anexo")
    private Collection<Anexo> anexoCollection1;
    @OneToMany(mappedBy = "documento")
    private Collection<ProcesoDocumental> procesoDocumentalCollection;
    @JoinColumn(name = "Autor", referencedColumnName = "Id")
    @ManyToOne
    private Autor autor;
    @JoinColumn(name = "ClaseDocumento", referencedColumnName = "id")
    @ManyToOne
    private ClaseDocumento claseDocumento;
    @JoinColumn(name = "Transportador", referencedColumnName = "Id")
    @ManyToOne
    private Transportador transportador;
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    @JoinColumn(name = "Corregimiento", referencedColumnName = "Id")
    @ManyToOne
    private Corregimiento corregimiento;
    @OneToMany(mappedBy = "documentoRelacionado")
    private Collection<Documento> documentoCollection;
    @JoinColumn(name = "DocumentoRelacionado", referencedColumnName = "Id")
    @ManyToOne
    private Documento documentoRelacionado;
    @JoinColumn(name = "UnidadDocumental", referencedColumnName = "Id")
    @ManyToOne
    private UnidadDocumental unidadDocumental;
    @JoinColumn(name = "TipoDocumental", referencedColumnName = "Id")
    @ManyToOne
    private TipoDocumental tipoDocumental;
    @JoinColumn(name = "Entidad", referencedColumnName = "id")
    @ManyToOne
    private Entidad entidad;
    @JoinColumn(name = "Municipio", referencedColumnName = "Id")
    @ManyToOne
    private Municipio municipio;
    @JoinColumn(name = "TipoDocumento", referencedColumnName = "Id")
    @ManyToOne
    private TipoDocumento tipoDocumento;
    @JoinColumn(name = "Session", referencedColumnName = "Id")
    @ManyToOne
    private Seccion session;
    @JoinColumn(name = "Serie", referencedColumnName = "Id")
    @ManyToOne
    private Serie serie;
    @JoinColumn(name = "SignaturaTopografica", referencedColumnName = "Id")
    @ManyToOne
    private SignaturaTopografica signaturaTopografica;
    @JoinColumn(name = "SubSeccion", referencedColumnName = "Id")
    @ManyToOne
    private SubSeccion subSeccion;
    @JoinColumn(name = "SubSerie", referencedColumnName = "Id")
    @ManyToOne
    private SubSerie subSerie;
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;

    public Documento() {
    }

    public Documento(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public Boolean getRequiereRespuesta() {
        return requiereRespuesta;
    }

    public void setRequiereRespuesta(Boolean requiereRespuesta) {
        this.requiereRespuesta = requiereRespuesta;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public Integer getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Integer destinatario) {
        this.destinatario = destinatario;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Boolean getAnexos() {
        return anexos;
    }

    public void setAnexos(Boolean anexos) {
        this.anexos = anexos;
    }

    public Integer getFolios() {
        return folios;
    }

    public void setFolios(Integer folios) {
        this.folios = folios;
    }

    public Integer getLibros() {
        return libros;
    }

    public void setLibros(Integer libros) {
        this.libros = libros;
    }

    public String getMedioEnvio() {
        return medioEnvio;
    }

    public void setMedioEnvio(String medioEnvio) {
        this.medioEnvio = medioEnvio;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Integer getFolioNro() {
        return folioNro;
    }

    public void setFolioNro(Integer folioNro) {
        this.folioNro = folioNro;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Anexo> getAnexoCollection() {
        return anexoCollection;
    }

    public void setAnexoCollection(Collection<Anexo> anexoCollection) {
        this.anexoCollection = anexoCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Anexo> getAnexoCollection1() {
        return anexoCollection1;
    }

    public void setAnexoCollection1(Collection<Anexo> anexoCollection1) {
        this.anexoCollection1 = anexoCollection1;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ProcesoDocumental> getProcesoDocumentalCollection() {
        return procesoDocumentalCollection;
    }

    public void setProcesoDocumentalCollection(Collection<ProcesoDocumental> procesoDocumentalCollection) {
        this.procesoDocumentalCollection = procesoDocumentalCollection;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public ClaseDocumento getClaseDocumento() {
        return claseDocumento;
    }

    public void setClaseDocumento(ClaseDocumento claseDocumento) {
        this.claseDocumento = claseDocumento;
    }

    public Transportador getTransportador() {
        return transportador;
    }

    public void setTransportador(Transportador transportador) {
        this.transportador = transportador;
    }

    public Usuario getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Usuario creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Corregimiento getCorregimiento() {
        return corregimiento;
    }

    public void setCorregimiento(Corregimiento corregimiento) {
        this.corregimiento = corregimiento;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Documento> getDocumentoCollection() {
        return documentoCollection;
    }

    public void setDocumentoCollection(Collection<Documento> documentoCollection) {
        this.documentoCollection = documentoCollection;
    }

    public Documento getDocumentoRelacionado() {
        return documentoRelacionado;
    }

    public void setDocumentoRelacionado(Documento documentoRelacionado) {
        this.documentoRelacionado = documentoRelacionado;
    }

    public UnidadDocumental getUnidadDocumental() {
        return unidadDocumental;
    }

    public void setUnidadDocumental(UnidadDocumental unidadDocumental) {
        this.unidadDocumental = unidadDocumental;
    }

    public TipoDocumental getTipoDocumental() {
        return tipoDocumental;
    }

    public void setTipoDocumental(TipoDocumental tipoDocumental) {
        this.tipoDocumental = tipoDocumental;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Seccion getSession() {
        return session;
    }

    public void setSession(Seccion session) {
        this.session = session;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public SignaturaTopografica getSignaturaTopografica() {
        return signaturaTopografica;
    }

    public void setSignaturaTopografica(SignaturaTopografica signaturaTopografica) {
        this.signaturaTopografica = signaturaTopografica;
    }

    public SubSeccion getSubSeccion() {
        return subSeccion;
    }

    public void setSubSeccion(SubSeccion subSeccion) {
        this.subSeccion = subSeccion;
    }

    public SubSerie getSubSerie() {
        return subSerie;
    }

    public void setSubSerie(SubSerie subSerie) {
        this.subSerie = subSerie;
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
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sucomunicacion.gedsys.entities.Documento[ id=" + id + " ]";
    }
    
}
