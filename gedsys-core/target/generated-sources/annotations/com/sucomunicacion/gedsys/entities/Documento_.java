package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.Anexo;
import com.sucomunicacion.gedsys.entities.Autor;
import com.sucomunicacion.gedsys.entities.ClaseDocumento;
import com.sucomunicacion.gedsys.entities.Corregimiento;
import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.Entidad;
import com.sucomunicacion.gedsys.entities.Municipio;
import com.sucomunicacion.gedsys.entities.ProcesoDocumental;
import com.sucomunicacion.gedsys.entities.Seccion;
import com.sucomunicacion.gedsys.entities.Serie;
import com.sucomunicacion.gedsys.entities.SignaturaTopografica;
import com.sucomunicacion.gedsys.entities.SubSeccion;
import com.sucomunicacion.gedsys.entities.SubSerie;
import com.sucomunicacion.gedsys.entities.TipoDocumental;
import com.sucomunicacion.gedsys.entities.TipoDocumento;
import com.sucomunicacion.gedsys.entities.Transportador;
import com.sucomunicacion.gedsys.entities.UnidadDocumental;
import com.sucomunicacion.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
@StaticMetamodel(Documento.class)
public class Documento_ {

    public static volatile SingularAttribute<Documento, SubSerie> subSerie;
    public static volatile SingularAttribute<Documento, Boolean> requiereRespuesta;
    public static volatile SingularAttribute<Documento, UnidadDocumental> unidadDocumental;
    public static volatile SingularAttribute<Documento, Integer> estado;
    public static volatile SingularAttribute<Documento, String> extension;
    public static volatile SingularAttribute<Documento, Seccion> session;
    public static volatile SingularAttribute<Documento, Boolean> anexos;
    public static volatile SingularAttribute<Documento, TipoDocumental> tipoDocumental;
    public static volatile SingularAttribute<Documento, String> remitente;
    public static volatile SingularAttribute<Documento, String> mimeType;
    public static volatile SingularAttribute<Documento, SignaturaTopografica> signaturaTopografica;
    public static volatile SingularAttribute<Documento, String> pathFile;
    public static volatile SingularAttribute<Documento, Integer> folioNro;
    public static volatile SingularAttribute<Documento, Usuario> creadoPor;
    public static volatile SingularAttribute<Documento, String> medioEnvio;
    public static volatile CollectionAttribute<Documento, Anexo> anexoCollection;
    public static volatile SingularAttribute<Documento, String> nombreDocumento;
    public static volatile SingularAttribute<Documento, String> asunto;
    public static volatile SingularAttribute<Documento, Date> fechaDocumento;
    public static volatile CollectionAttribute<Documento, ProcesoDocumental> procesoDocumentalCollection;
    public static volatile SingularAttribute<Documento, Long> id;
    public static volatile SingularAttribute<Documento, String> rutaArchivo;
    public static volatile SingularAttribute<Documento, Date> fechaModificacion;
    public static volatile SingularAttribute<Documento, String> codigoPostal;
    public static volatile CollectionAttribute<Documento, Documento> documentoCollection;
    public static volatile SingularAttribute<Documento, Municipio> municipio;
    public static volatile SingularAttribute<Documento, Corregimiento> corregimiento;
    public static volatile SingularAttribute<Documento, String> direccion;
    public static volatile SingularAttribute<Documento, Usuario> modificadoPor;
    public static volatile SingularAttribute<Documento, ClaseDocumento> claseDocumento;
    public static volatile CollectionAttribute<Documento, Anexo> anexoCollection1;
    public static volatile SingularAttribute<Documento, Autor> autor;
    public static volatile SingularAttribute<Documento, SubSeccion> subSeccion;
    public static volatile SingularAttribute<Documento, Integer> destinatario;
    public static volatile SingularAttribute<Documento, String> detalle;
    public static volatile SingularAttribute<Documento, String> consecutivo;
    public static volatile SingularAttribute<Documento, Integer> libros;
    public static volatile SingularAttribute<Documento, Transportador> transportador;
    public static volatile SingularAttribute<Documento, TipoDocumento> tipoDocumento;
    public static volatile SingularAttribute<Documento, Integer> folios;
    public static volatile SingularAttribute<Documento, Entidad> entidad;
    public static volatile SingularAttribute<Documento, Serie> serie;
    public static volatile SingularAttribute<Documento, Date> fechaCreacion;
    public static volatile SingularAttribute<Documento, Documento> documentoRelacionado;

}
