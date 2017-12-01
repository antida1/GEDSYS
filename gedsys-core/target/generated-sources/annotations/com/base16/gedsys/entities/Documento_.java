package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Autor;
import com.base16.gedsys.entities.ClaseDocumento;
import com.base16.gedsys.entities.Corregimiento;
import com.base16.gedsys.entities.DestinatariosDoc;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Entidad;
import com.base16.gedsys.entities.Municipio;
import com.base16.gedsys.entities.ProcesoDocumental;
import com.base16.gedsys.entities.SignaturaTopografica;
import com.base16.gedsys.entities.TipoDocumental;
import com.base16.gedsys.entities.TipoDocumento;
import com.base16.gedsys.entities.Transportador;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-01T14:33:19")
@StaticMetamodel(Documento.class)
public class Documento_ { 

    public static volatile SingularAttribute<Documento, Boolean> requiereRespuesta;
    public static volatile SingularAttribute<Documento, Integer> estado;
    public static volatile SingularAttribute<Documento, String> extension;
    public static volatile SingularAttribute<Documento, Boolean> anexos;
    public static volatile SingularAttribute<Documento, TipoDocumental> tipoDocumental;
    public static volatile SingularAttribute<Documento, String> remitente;
    public static volatile SingularAttribute<Documento, String> mimeType;
    public static volatile SingularAttribute<Documento, SignaturaTopografica> signaturaTopografica;
    public static volatile SingularAttribute<Documento, String> pathFile;
    public static volatile SingularAttribute<Documento, String> clase;
    public static volatile SingularAttribute<Documento, String> folioNro;
    public static volatile SingularAttribute<Documento, Usuario> creadoPor;
    public static volatile SingularAttribute<Documento, String> medioEnvio;
    public static volatile SingularAttribute<Documento, String> nombreDocumento;
    public static volatile SingularAttribute<Documento, String> asunto;
    public static volatile SingularAttribute<Documento, Date> fechaDocumento;
    public static volatile SingularAttribute<Documento, Long> id;
    public static volatile CollectionAttribute<Documento, DestinatariosDoc> destinatariosDocCollection;
    public static volatile SingularAttribute<Documento, String> rutaArchivo;
    public static volatile SingularAttribute<Documento, Date> fechaModificacion;
    public static volatile SingularAttribute<Documento, String> codigoPostal;
    public static volatile CollectionAttribute<Documento, Documento> documentoCollection;
    public static volatile SingularAttribute<Documento, Municipio> municipio;
    public static volatile SingularAttribute<Documento, Corregimiento> corregimiento;
    public static volatile SingularAttribute<Documento, String> direccion;
    public static volatile SingularAttribute<Documento, String> remitenteExteno;
    public static volatile SingularAttribute<Documento, Usuario> modificadoPor;
    public static volatile SingularAttribute<Documento, ClaseDocumento> claseDocumento;
    public static volatile SingularAttribute<Documento, Autor> autor;
    public static volatile SingularAttribute<Documento, String> detalle;
    public static volatile SingularAttribute<Documento, Usuario> destinatario;
    public static volatile SingularAttribute<Documento, String> consecutivo;
    public static volatile SingularAttribute<Documento, String> libros;
    public static volatile SingularAttribute<Documento, TipoDocumento> tipoDocumento;
    public static volatile SingularAttribute<Documento, Transportador> transportador;
    public static volatile SingularAttribute<Documento, Date> fechaEnvio;
    public static volatile SingularAttribute<Documento, String> folios;
    public static volatile SingularAttribute<Documento, Entidad> entidad;
    public static volatile SingularAttribute<Documento, Date> fechaCreacion;
    public static volatile SingularAttribute<Documento, Date> fechaRecepcion;
    public static volatile SingularAttribute<Documento, Documento> documentoRelacionado;
    public static volatile CollectionAttribute<Documento, ProcesoDocumental> procesodocumentalCollection;

}