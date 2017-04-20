package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.Acl;
import com.sucomunicacion.gedsys.entities.Anexo;
import com.sucomunicacion.gedsys.entities.Autor;
import com.sucomunicacion.gedsys.entities.Cargo;
import com.sucomunicacion.gedsys.entities.ClaseDocumento;
import com.sucomunicacion.gedsys.entities.Consecutivo;
import com.sucomunicacion.gedsys.entities.Corregimiento;
import com.sucomunicacion.gedsys.entities.Departamento;
import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.Entidad;
import com.sucomunicacion.gedsys.entities.Grupo;
import com.sucomunicacion.gedsys.entities.GrupoUsuario;
import com.sucomunicacion.gedsys.entities.Modulo;
import com.sucomunicacion.gedsys.entities.MonitoresProceso;
import com.sucomunicacion.gedsys.entities.Municipio;
import com.sucomunicacion.gedsys.entities.Notificacion;
import com.sucomunicacion.gedsys.entities.Pais;
import com.sucomunicacion.gedsys.entities.PlantillaDocumental;
import com.sucomunicacion.gedsys.entities.Preferencias;
import com.sucomunicacion.gedsys.entities.ProcesoDocumental;
import com.sucomunicacion.gedsys.entities.ProcesoNegocio;
import com.sucomunicacion.gedsys.entities.ProcesoTipoDocumento;
import com.sucomunicacion.gedsys.entities.Seccion;
import com.sucomunicacion.gedsys.entities.Serie;
import com.sucomunicacion.gedsys.entities.SignaturaTopografica;
import com.sucomunicacion.gedsys.entities.SubSeccion;
import com.sucomunicacion.gedsys.entities.SubSerie;
import com.sucomunicacion.gedsys.entities.TipoDocumental;
import com.sucomunicacion.gedsys.entities.TipoDocumento;
import com.sucomunicacion.gedsys.entities.Transportador;
import com.sucomunicacion.gedsys.entities.UnidadDocumental;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
@StaticMetamodel(Usuario.class)
public class Usuario_ {

    public static volatile CollectionAttribute<Usuario, Cargo> cargoCollection1;
    public static volatile CollectionAttribute<Usuario, ProcesoNegocio> procesoNegocioCollection1;
    public static volatile CollectionAttribute<Usuario, Pais> paisCollection1;
    public static volatile CollectionAttribute<Usuario, PlantillaDocumental> plantillaDocumentalCollection;
    public static volatile CollectionAttribute<Usuario, ProcesoTipoDocumento> procesoTipoDocumentoCollection;
    public static volatile CollectionAttribute<Usuario, Seccion> seccionCollection1;
    public static volatile CollectionAttribute<Usuario, SubSerie> subSerieCollection1;
    public static volatile SingularAttribute<Usuario, Integer> creadoPor;
    public static volatile CollectionAttribute<Usuario, Documento> documentoCollection1;
    public static volatile CollectionAttribute<Usuario, Entidad> entidadCollection1;
    public static volatile CollectionAttribute<Usuario, Acl> aclCollection;
    public static volatile CollectionAttribute<Usuario, Entidad> entidadCollection;
    public static volatile CollectionAttribute<Usuario, Pais> paisCollection;
    public static volatile CollectionAttribute<Usuario, ProcesoNegocio> procesoNegocioCollection;
    public static volatile CollectionAttribute<Usuario, GrupoUsuario> grupoUsuarioCollection1;
    public static volatile CollectionAttribute<Usuario, GrupoUsuario> grupoUsuarioCollection2;
    public static volatile CollectionAttribute<Usuario, Municipio> municipioCollection1;
    public static volatile CollectionAttribute<Usuario, ProcesoDocumental> procesoDocumentalCollection;
    public static volatile SingularAttribute<Usuario, Integer> id;
    public static volatile SingularAttribute<Usuario, String> telefono;
    public static volatile CollectionAttribute<Usuario, Cargo> cargoCollection;
    public static volatile SingularAttribute<Usuario, Cargo> cargo;
    public static volatile CollectionAttribute<Usuario, MonitoresProceso> monitoresProcesoCollection;
    public static volatile CollectionAttribute<Usuario, ProcesoDocumental> procesoDocumentalCollection1;
    public static volatile CollectionAttribute<Usuario, Corregimiento> corregimientoCollection;
    public static volatile CollectionAttribute<Usuario, Modulo> moduloCollection1;
    public static volatile CollectionAttribute<Usuario, ClaseDocumento> claseDocumentoCollection;
    public static volatile SingularAttribute<Usuario, Date> fechaModificacion;
    public static volatile CollectionAttribute<Usuario, TipoDocumental> tipoDocumentalCollection1;
    public static volatile CollectionAttribute<Usuario, MonitoresProceso> monitoresProcesoCollection1;
    public static volatile CollectionAttribute<Usuario, TipoDocumento> tipoDocumentoCollection;
    public static volatile CollectionAttribute<Usuario, Serie> serieCollection1;
    public static volatile CollectionAttribute<Usuario, Grupo> grupoCollection;
    public static volatile CollectionAttribute<Usuario, Serie> serieCollection;
    public static volatile SingularAttribute<Usuario, String> foto;
    public static volatile CollectionAttribute<Usuario, ClaseDocumento> claseDocumentoCollection1;
    public static volatile CollectionAttribute<Usuario, Preferencias> preferenciasCollection;
    public static volatile SingularAttribute<Usuario, String> usuario;
    public static volatile CollectionAttribute<Usuario, PlantillaDocumental> plantillaDocumentalCollection1;
    public static volatile CollectionAttribute<Usuario, SubSeccion> subSeccionCollection1;
    public static volatile CollectionAttribute<Usuario, Consecutivo> consecutivoCollection1;
    public static volatile CollectionAttribute<Usuario, TipoDocumento> tipoDocumentoCollection1;
    public static volatile CollectionAttribute<Usuario, SubSerie> subSerieCollection;
    public static volatile CollectionAttribute<Usuario, SignaturaTopografica> signaturaTopograficaCollection1;
    public static volatile CollectionAttribute<Usuario, SubSeccion> subSeccionCollection;
    public static volatile SingularAttribute<Usuario, String> nombres;
    public static volatile CollectionAttribute<Usuario, GrupoUsuario> grupoUsuarioCollection;
    public static volatile CollectionAttribute<Usuario, Departamento> departamentoCollection1;
    public static volatile CollectionAttribute<Usuario, Municipio> municipioCollection;
    public static volatile CollectionAttribute<Usuario, Autor> autorCollection1;
    public static volatile CollectionAttribute<Usuario, Modulo> moduloCollection;
    public static volatile CollectionAttribute<Usuario, Departamento> departamentoCollection;
    public static volatile CollectionAttribute<Usuario, Anexo> anexoCollection;
    public static volatile SingularAttribute<Usuario, String> celular;
    public static volatile SingularAttribute<Usuario, String> apelidos;
    public static volatile SingularAttribute<Usuario, String> email;
    public static volatile CollectionAttribute<Usuario, Seccion> seccionCollection;
    public static volatile SingularAttribute<Usuario, String> clave;
    public static volatile CollectionAttribute<Usuario, Transportador> transportadorCollection1;
    public static volatile CollectionAttribute<Usuario, Autor> autorCollection;
    public static volatile CollectionAttribute<Usuario, Consecutivo> consecutivoCollection;
    public static volatile CollectionAttribute<Usuario, Transportador> transportadorCollection;
    public static volatile CollectionAttribute<Usuario, Documento> documentoCollection;
    public static volatile CollectionAttribute<Usuario, SignaturaTopografica> signaturaTopograficaCollection;
    public static volatile SingularAttribute<Usuario, Integer> modificadoPor;
    public static volatile CollectionAttribute<Usuario, UnidadDocumental> unidadDocumentalCollection;
    public static volatile CollectionAttribute<Usuario, Anexo> anexoCollection1;
    public static volatile CollectionAttribute<Usuario, TipoDocumental> tipoDocumentalCollection;
    public static volatile CollectionAttribute<Usuario, Corregimiento> corregimientoCollection1;
    public static volatile CollectionAttribute<Usuario, UnidadDocumental> unidadDocumentalCollection1;
    public static volatile CollectionAttribute<Usuario, Notificacion> notificacionCollection;
    public static volatile SingularAttribute<Usuario, Date> fechaCreacion;
    public static volatile CollectionAttribute<Usuario, Acl> aclCollection1;
    public static volatile CollectionAttribute<Usuario, ProcesoTipoDocumento> procesoTipoDocumentoCollection1;

}
