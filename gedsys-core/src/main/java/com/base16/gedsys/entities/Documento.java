/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author rober
 */
@Entity
@Table(name = "documento", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d")
    , @NamedQuery(name = "Documento.findById", query = "SELECT d FROM Documento d WHERE d.id = :id")
    , @NamedQuery(name = "Documento.findByAnexos", query = "SELECT d FROM Documento d WHERE d.anexos = :anexos")
    , @NamedQuery(name = "Documento.findByAsunto", query = "SELECT d FROM Documento d WHERE d.asunto = :asunto")
    , @NamedQuery(name = "Documento.findByCodigoPostal", query = "SELECT d FROM Documento d WHERE d.codigoPostal = :codigoPostal")
    , @NamedQuery(name = "Documento.findByConsecutivo", query = "SELECT d FROM Documento d WHERE d.consecutivo = :consecutivo")
    , @NamedQuery(name = "Documento.findByCompartidos", query = "SELECT d FROM Documento d JOIN d.destinatariosDocCollection c WHERE c.destinatarioId = :destinatario")
    , @NamedQuery(name = "Documento.findEntrantes", query = "SELECT d FROM Documento d WHERE d.destinatario = :destinatario and (d.estado = 1 or d.estado = 7)")
    , @NamedQuery(name = "Documento.findEntrantesInternos", query = "SELECT d FROM Documento d WHERE d.destinatarioInterno = :destinatarioInterno and d.estado = 9")
    , @NamedQuery(name = "Documento.findPorEnviar", query = "SELECT d FROM Documento d WHERE (d.estado = 2 or d.estado = 8 )")
    , @NamedQuery(name = "Documento.findEnviados", query = "SELECT d FROM Documento d WHERE d.destinatario = :destinatario and d.estado = 9")
    , @NamedQuery(name = "Documento.findEnPrestamo", query = "SELECT d FROM Documento d WHERE d.destinatario = :destinatario and (d.estado = 4 or d.estado = 6)")
    , @NamedQuery(name = "Documento.findSinArchivar", query = "SELECT d FROM Documento d WHERE d.destinatario = :destinatario and d.estado = 3 or d.estado = 9")
    , @NamedQuery(name = "Documento.findPorVencer", query = "SELECT d FROM Documento d WHERE d.destinatario = :destinatario and d.estado = 1 and d.requiereRespuesta = true ")
    , @NamedQuery(name = "Documento.findByDireccion", query = "SELECT d FROM Documento d WHERE d.direccion = :direccion")
    , @NamedQuery(name = "Documento.findByEstado", query = "SELECT d FROM Documento d WHERE d.estado = :estado")
    , @NamedQuery(name = "Documento.findByExtension", query = "SELECT d FROM Documento d WHERE d.extension = :extension")
    , @NamedQuery(name = "Documento.findByFechaCreacion", query = "SELECT d FROM Documento d WHERE d.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Documento.findByFechaDocumento", query = "SELECT d FROM Documento d WHERE d.fechaDocumento = :fechaDocumento")
    , @NamedQuery(name = "Documento.findByFechaModificacion", query = "SELECT d FROM Documento d WHERE d.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Documento.findByFolioNro", query = "SELECT d FROM Documento d WHERE d.folioNro = :folioNro")
    , @NamedQuery(name = "Documento.findByFolios", query = "SELECT d FROM Documento d WHERE d.folios = :folios")
    , @NamedQuery(name = "Documento.findByLibros", query = "SELECT d FROM Documento d WHERE d.libros = :libros")
    , @NamedQuery(name = "Documento.findByMedioEnvio", query = "SELECT d FROM Documento d WHERE d.medioEnvio = :medioEnvio")
    , @NamedQuery(name = "Documento.findByMimeType", query = "SELECT d FROM Documento d WHERE d.mimeType = :mimeType")
    , @NamedQuery(name = "Documento.findByNombreDocumento", query = "SELECT d FROM Documento d WHERE d.nombreDocumento = :nombreDocumento")
    , @NamedQuery(name = "Documento.findByPathFile", query = "SELECT d FROM Documento d WHERE d.pathFile = :pathFile")
    , @NamedQuery(name = "Documento.findByRemitente", query = "SELECT d FROM Documento d WHERE d.remitente = :remitente")
    , @NamedQuery(name = "Documento.findByRequiereRespuesta", query = "SELECT d FROM Documento d WHERE d.requiereRespuesta = :requiereRespuesta")
    , @NamedQuery(name = "Documento.findRadicados", query = "SELECT d FROM Documento d WHERE d.creadoPor = :creadoPor")
    //, @NamedQuery(name = "Documento.findDocumentos", query = "SELECT d FROM Documento d WHERE d.destinatario = :destinatario and d.consecutivo like :consecutivo and d.asunto like :asunto and d.fechaDocumento between :startDate and :endDate and d.tipoDocumento like :tipoDocumento ")    
    //, @NamedQuery(name = "Documento.findDocumentos", query = "SELECT d FROM Documento d WHERE d.destinatario = :destinatario and d.consecutivo like :consecutivo and d.asunto like :asunto")
    , @NamedQuery(name = "Documento.findByRutaArchivo", query = "SELECT d FROM Documento d WHERE d.rutaArchivo = :rutaArchivo")})

public class Documento implements Serializable {

    @OneToMany(mappedBy = "documento")
    private List<Prestamo> prestamoList;

    @OneToMany(mappedBy = "documento")
    private List<PlanillaEnvioDocumento> planillaEnvioDocumentoList;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Anexos")
    private Boolean anexos;
    @Column(name = "Asunto")
    private String asunto;
    @Column(name = "CodigoPostal")
    private String codigoPostal;
    @Column(name = "Consecutivo")
    private String consecutivo;
    @Lob
    @Column(name = "Detalle")
    private String detalle;
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "Estado")
    private Integer estado;
    @Column(name = "Extension")
    private String extension;
    @Column(name = "FechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FechaDocumento")
    @Temporal(TemporalType.DATE)
    private Date fechaDocumento;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "FolioNro")
    private String folioNro;
    @Column(name = "Folios")
    private String folios;
    @Column(name = "Libros")
    private String libros;
    @Column(name = "MimeType")
    private String mimeType;
    @Column(name = "NombreDocumento")
    private String nombreDocumento;
    @Column(name = "PathFile")
    private String pathFile;
    @Column(name = "Remitente")
    private String remitente;
    @Column(name = "RequiereRespuesta")
    private Boolean requiereRespuesta;
    @Column(name = "RutaArchivo")
    private String rutaArchivo;
    @Column(name = "FechaEnvio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;
    @Column(name = "FechaRecepcion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRecepcion;
    @Column(name = "RemitenteExteno")
    private String remitenteExteno;
    @Column(name = "Clase")
    private String clase;
    @Column(name = "FechaVencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @Column(name = "RutaGuia")
    private String rutaGuia;
    @Column(name = "Guia")
    private String guia;
     @Column(name = "RutaComprobante")
    private String rutaComprobante;
    @Column(name = "RadicadoEnvio")
    private String radicadoEnvio;
    
    @XmlTransient
    @JsonIgnore
    @JoinColumn(name = "Destinatario", referencedColumnName = "Id")
    @ManyToOne
    private Usuario destinatario;  
    
    @XmlTransient
    @JsonIgnore
    @JoinColumn(name = "DestinatarioInterno", referencedColumnName = "Id")
    @ManyToOne
    private Usuario destinatarioInterno;  
    
    @XmlTransient
    @JsonIgnore
    @JoinColumn(name = "CreadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario creadoPor;
    
    @XmlTransient
    @JsonIgnore
    @JoinColumn(name = "Autor", referencedColumnName = "Id")
    @ManyToOne
    private Autor autor;
    
    @XmlTransient
    @JsonIgnore
    @JoinColumn(name = "ClaseDocumento", referencedColumnName = "id")
    @ManyToOne
    private ClaseDocumento claseDocumento;
    
    @XmlTransient
    @JsonIgnore
    @JoinColumn(name = "Corregimiento", referencedColumnName = "Id")
    @ManyToOne
    private Corregimiento corregimiento;
    
    @XmlTransient
    @JsonIgnore
    @OneToMany(mappedBy = "documentoRelacionado")
    private Collection<Documento> documentoCollection;
    
    @XmlTransient
    @JsonIgnore
    @JoinColumn(name = "DocumentoRelacionado", referencedColumnName = "Id")
    @ManyToOne
    private Documento documentoRelacionado;
    
    @XmlTransient
    @JsonIgnore
    @JoinColumn(name = "Entidad", referencedColumnName = "id")
    @ManyToOne
    private Entidad entidad;
    
    @XmlTransient
    @JsonIgnore
    @JoinColumn(name = "ModificadoPor", referencedColumnName = "Id")
    @ManyToOne
    private Usuario modificadoPor;
    
    @XmlTransient
    @JsonIgnore
    @JoinColumn(name = "TipoDocumento", referencedColumnName = "Id")
    @ManyToOne
    private TipoDocumento tipoDocumento;
    
    @XmlTransient
    @JsonIgnore
    @JoinColumn(name = "Municipio", referencedColumnName = "Id")
    @ManyToOne
    private Municipio municipio;
    
    @XmlTransient
    @JsonIgnore
    @JoinColumn(name = "Transportador", referencedColumnName = "Id")
    @ManyToOne
    private Transportador transportador;
    
    @XmlTransient
    @JsonIgnore
    @JoinColumn(name = "SignaturaTopografica", referencedColumnName = "Id")
    @ManyToOne
    private SignaturaTopografica signaturaTopografica;
    
    @XmlTransient
    @JsonIgnore
    @JoinColumn(name = "TipoDocumental", referencedColumnName = "Id")
    @ManyToOne
    private TipoDocumental tipoDocumental;
    
    @XmlTransient
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documentoId")
    private Collection<DestinatariosDoc> destinatariosDocCollection;
    
    @XmlTransient
    @JsonIgnore
    @OneToMany(mappedBy = "documento")
    private Collection<ProcesoDocumental> procesodocumentalCollection;
    
    @XmlTransient
    @JsonIgnore
    @JoinColumn(name = "MedioEnvio", referencedColumnName = "Id")
    @ManyToOne
    private Mediorecepcion medioEnvio;
    
    @OneToMany(mappedBy = "documentoPadre")
    private List<Carta> cartaList;

    @Column(name = "Comprobante")
    private String comprobante;

    @XmlTransient
    @JsonIgnore
    @OneToMany(mappedBy = "documento")
    private List<Comentario> comentarioList;

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

    public Boolean getAnexos() {
        return anexos;
    }

    public void setAnexos(Boolean anexos) {
        this.anexos = anexos;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    @XmlTransient
    @JsonIgnore
    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public Date getFechaModificacion() {
        return new Date();
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getFolioNro() {
        return folioNro;
    }

    public void setFolioNro(String folioNro) {
        this.folioNro = folioNro;
    }

    public String getFolios() {
        return folios;
    }

    public void setFolios(String folios) {
        this.folios = folios;
    }

    public String getLibros() {
        return libros;
    }

    public void setLibros(String libros) {
        this.libros = libros;
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

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public Boolean getRequiereRespuesta() {
        return requiereRespuesta;
    }

    public void setRequiereRespuesta(Boolean requiereRespuesta) {
        this.requiereRespuesta = requiereRespuesta;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
      public String getRutaComprobante() {
        return rutaComprobante;
    }

    public void setRutaComprobante(String rutaComprobante) {
        this.rutaComprobante = rutaComprobante;
    }

    @XmlTransient    
    @JsonIgnore
    public Usuario getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Usuario creadoPor) {
        this.creadoPor = creadoPor;
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

    public Corregimiento getCorregimiento() {
        return corregimiento;
    }

    public void setCorregimiento(Corregimiento corregimiento) {
        this.corregimiento = corregimiento;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }
    public String getRutaGuia() {
        return rutaGuia;
    }

    public void setRutaGuia(String rutaGuia) {
        this.rutaGuia = rutaGuia;
    }
    public String getGuia() {
        return guia;
    }

    public void setGuia(String guia) {
        this.guia = guia;
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
    
    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

    @XmlTransient    
    @JsonIgnore
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

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Transportador getTransportador() {
        return transportador;
    }

    public void setTransportador(Transportador transportador) {
        this.transportador = transportador;
    }

    public SignaturaTopografica getSignaturaTopografica() {
        return signaturaTopografica;
    }

    public void setSignaturaTopografica(SignaturaTopografica signaturaTopografica) {
        this.signaturaTopografica = signaturaTopografica;
    }

    public TipoDocumental getTipoDocumental() {
        return tipoDocumental;
    }

    public void setTipoDocumental(TipoDocumental tipoDocumental) {
        this.tipoDocumental = tipoDocumental;
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
    public Collection<ProcesoDocumental> getProcesodocumentalCollection() {
        return procesodocumentalCollection;
    }

    public void setProcesodocumentalCollection(Collection<ProcesoDocumental> procesodocumentalCollection) {
        this.procesodocumentalCollection = procesodocumentalCollection;
    }

    public Usuario getDestinatarioInterno() {
        return destinatarioInterno;
    }

    public void setDestinatarioInterno(Usuario destinatarioInterno) {
        this.destinatarioInterno = destinatarioInterno;
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

    public String getRemitenteExteno() {
        return remitenteExteno;
    }

    public void setRemitenteExteno(String remitenteExteno) {
        this.remitenteExteno = remitenteExteno;
    }

    public Mediorecepcion getMedioEnvio() {
        return medioEnvio;
    }

    public void setMedioEnvio(Mediorecepcion medioEnvio) {
        this.medioEnvio = medioEnvio;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @XmlTransient
    @JsonIgnore
    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    @XmlTransient
    @JsonIgnore
    public List<Carta> getCartaList() {
        return cartaList;
    }

    public void setCartaList(List<Carta> cartaList) {
        this.cartaList = cartaList;
    }

    public String getRadicadoEnvio() {
        return radicadoEnvio;
    }

    public void setRadicadoEnvio(String radicadoEnvio) {
        this.radicadoEnvio = radicadoEnvio;
    }

    @XmlTransient
    @JsonIgnore
    public List<PlanillaEnvioDocumento> getPlanillaEnvioDocumentoList() {
        return planillaEnvioDocumentoList;
    }

    public void setPlanillaEnvioDocumentoList(List<PlanillaEnvioDocumento> planillaEnvioDocumentoList) {
        this.planillaEnvioDocumentoList = planillaEnvioDocumentoList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Prestamo> getPrestamoList() {
        return prestamoList;
    }

    public void setPrestamoList(List<Prestamo> prestamoList) {
        this.prestamoList = prestamoList;
    }

}
