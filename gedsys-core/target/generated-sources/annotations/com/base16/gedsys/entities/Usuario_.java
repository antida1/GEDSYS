package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Acl;
import com.base16.gedsys.entities.Acta;
import com.base16.gedsys.entities.Actaasistente;
import com.base16.gedsys.entities.Actaausente;
import com.base16.gedsys.entities.Actainvitado;
import com.base16.gedsys.entities.Autor;
import com.base16.gedsys.entities.Campos;
import com.base16.gedsys.entities.Cargo;
import com.base16.gedsys.entities.Carta;
import com.base16.gedsys.entities.Certificado;
import com.base16.gedsys.entities.Circular;
import com.base16.gedsys.entities.Circularcc;
import com.base16.gedsys.entities.ClaseDocumento;
import com.base16.gedsys.entities.Comunicacion;
import com.base16.gedsys.entities.Comunicacioncc;
import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.entities.Constancia;
import com.base16.gedsys.entities.Corregimiento;
import com.base16.gedsys.entities.Departamento;
import com.base16.gedsys.entities.DestinatariosDoc;
import com.base16.gedsys.entities.Devices;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Entidad;
import com.base16.gedsys.entities.Grupo;
import com.base16.gedsys.entities.GrupoUsuario;
import com.base16.gedsys.entities.Informe;
import com.base16.gedsys.entities.Mediorecepcion;
import com.base16.gedsys.entities.Modulo;
import com.base16.gedsys.entities.MonitoresProceso;
import com.base16.gedsys.entities.Municipio;
import com.base16.gedsys.entities.Notificacion;
import com.base16.gedsys.entities.Pais;
import com.base16.gedsys.entities.PlantillaDocumental;
import com.base16.gedsys.entities.Preferencias;
import com.base16.gedsys.entities.Prestamo;
import com.base16.gedsys.entities.ProcesoDocumental;
import com.base16.gedsys.entities.ProcesoNegocio;
import com.base16.gedsys.entities.ProcesoTipoDocumento;
import com.base16.gedsys.entities.SeccionSubSeccion;
import com.base16.gedsys.entities.Sede;
import com.base16.gedsys.entities.Serie;
import com.base16.gedsys.entities.SignaturaTopografica;
import com.base16.gedsys.entities.SubSerie;
import com.base16.gedsys.entities.TipoDocumental;
import com.base16.gedsys.entities.TipoDocumento;
import com.base16.gedsys.entities.Transportador;
import com.base16.gedsys.entities.UnidadDocumental;
import com.base16.gedsys.entities.Usuariosignaturas;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-20T17:33:31")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile CollectionAttribute<Usuario, Cargo> cargoCollection1;
    public static volatile ListAttribute<Usuario, Comunicacion> comunicacionList;
    public static volatile CollectionAttribute<Usuario, PlantillaDocumental> plantillaDocumentalCollection;
    public static volatile CollectionAttribute<Usuario, Pais> paisCollection1;
    public static volatile ListAttribute<Usuario, Devices> devicesList;
    public static volatile CollectionAttribute<Usuario, SubSerie> subSerieCollection1;
    public static volatile CollectionAttribute<Usuario, Acl> aclCollection;
    public static volatile CollectionAttribute<Usuario, Municipio> municipioCollection1;
    public static volatile SingularAttribute<Usuario, Integer> id;
    public static volatile SingularAttribute<Usuario, String> telefono;
    public static volatile SingularAttribute<Usuario, Cargo> cargo;
    public static volatile CollectionAttribute<Usuario, MonitoresProceso> monitoresProcesoCollection;
    public static volatile ListAttribute<Usuario, Actaausente> actaausenteList;
    public static volatile CollectionAttribute<Usuario, Campos> camposCollection1;
    public static volatile ListAttribute<Usuario, Comunicacion> comunicacionList3;
    public static volatile ListAttribute<Usuario, Comunicacion> comunicacionList2;
    public static volatile CollectionAttribute<Usuario, Modulo> moduloCollection1;
    public static volatile ListAttribute<Usuario, Comunicacion> comunicacionList1;
    public static volatile CollectionAttribute<Usuario, ClaseDocumento> claseDocumentoCollection;
    public static volatile SingularAttribute<Usuario, Boolean> isAdmin;
    public static volatile CollectionAttribute<Usuario, MonitoresProceso> monitoresProcesoCollection1;
    public static volatile SingularAttribute<Usuario, String> firma;
    public static volatile CollectionAttribute<Usuario, Grupo> grupoCollection;
    public static volatile SingularAttribute<Usuario, String> foto;
    public static volatile CollectionAttribute<Usuario, ClaseDocumento> claseDocumentoCollection1;
    public static volatile CollectionAttribute<Usuario, Preferencias> preferenciasCollection;
    public static volatile ListAttribute<Usuario, Comunicacioncc> comunicacionccList;
    public static volatile SingularAttribute<Usuario, String> usuario;
    public static volatile CollectionAttribute<Usuario, Consecutivo> consecutivoCollection1;
    public static volatile ListAttribute<Usuario, Usuariosignaturas> usuariosignaturasList2;
    public static volatile CollectionAttribute<Usuario, TipoDocumento> tipoDocumentoCollection1;
    public static volatile ListAttribute<Usuario, Usuariosignaturas> usuariosignaturasList1;
    public static volatile ListAttribute<Usuario, Carta> cartaList1;
    public static volatile ListAttribute<Usuario, Carta> cartaList2;
    public static volatile CollectionAttribute<Usuario, SignaturaTopografica> signaturaTopograficaCollection1;
    public static volatile ListAttribute<Usuario, Constancia> constanciaList1;
    public static volatile ListAttribute<Usuario, Constancia> constanciaList2;
    public static volatile SingularAttribute<Usuario, String> nombres;
    public static volatile CollectionAttribute<Usuario, Municipio> municipioCollection;
    public static volatile CollectionAttribute<Usuario, GrupoUsuario> grupoUsuarioCollection;
    public static volatile CollectionAttribute<Usuario, Departamento> departamentoCollection1;
    public static volatile CollectionAttribute<Usuario, Autor> autorCollection1;
    public static volatile CollectionAttribute<Usuario, Modulo> moduloCollection;
    public static volatile ListAttribute<Usuario, Prestamo> prestamoList1;
    public static volatile ListAttribute<Usuario, Prestamo> prestamoList2;
    public static volatile SingularAttribute<Usuario, String> email;
    public static volatile CollectionAttribute<Usuario, DestinatariosDoc> destinatariosDocCollection;
    public static volatile CollectionAttribute<Usuario, DestinatariosDoc> destinatariosDocCollection2;
    public static volatile SingularAttribute<Usuario, String> clave;
    public static volatile CollectionAttribute<Usuario, DestinatariosDoc> destinatariosDocCollection1;
    public static volatile ListAttribute<Usuario, Constancia> constanciaList;
    public static volatile CollectionAttribute<Usuario, Autor> autorCollection;
    public static volatile CollectionAttribute<Usuario, Consecutivo> consecutivoCollection;
    public static volatile ListAttribute<Usuario, Certificado> certificadoList;
    public static volatile CollectionAttribute<Usuario, Documento> documentoCollection;
    public static volatile CollectionAttribute<Usuario, SignaturaTopografica> signaturaTopograficaCollection;
    public static volatile SingularAttribute<Usuario, Integer> modificadoPor;
    public static volatile CollectionAttribute<Usuario, UnidadDocumental> unidadDocumentalCollection;
    public static volatile CollectionAttribute<Usuario, Corregimiento> corregimientoCollection1;
    public static volatile CollectionAttribute<Usuario, Notificacion> notificacionCollection;
    public static volatile CollectionAttribute<Usuario, ProcesoTipoDocumento> procesoTipoDocumentoCollection1;
    public static volatile ListAttribute<Usuario, Circular> circularList;
    public static volatile ListAttribute<Usuario, Informe> informeList1;
    public static volatile CollectionAttribute<Usuario, ProcesoNegocio> procesoNegocioCollection1;
    public static volatile ListAttribute<Usuario, Acta> actaList;
    public static volatile CollectionAttribute<Usuario, ProcesoTipoDocumento> procesoTipoDocumentoCollection;
    public static volatile ListAttribute<Usuario, Mediorecepcion> mediorecepcionList1;
    public static volatile ListAttribute<Usuario, Informe> informeList2;
    public static volatile SingularAttribute<Usuario, Integer> creadoPor;
    public static volatile CollectionAttribute<Usuario, Documento> documentoCollection1;
    public static volatile CollectionAttribute<Usuario, Entidad> entidadCollection1;
    public static volatile ListAttribute<Usuario, Usuariosignaturas> usuariosignaturasList;
    public static volatile CollectionAttribute<Usuario, Entidad> entidadCollection;
    public static volatile CollectionAttribute<Usuario, Pais> paisCollection;
    public static volatile ListAttribute<Usuario, Certificado> certificadoList1;
    public static volatile CollectionAttribute<Usuario, ProcesoNegocio> procesoNegocioCollection;
    public static volatile CollectionAttribute<Usuario, GrupoUsuario> grupoUsuarioCollection1;
    public static volatile CollectionAttribute<Usuario, ProcesoDocumental> procesodocumentalCollection1;
    public static volatile ListAttribute<Usuario, Certificado> certificadoList2;
    public static volatile CollectionAttribute<Usuario, GrupoUsuario> grupoUsuarioCollection2;
    public static volatile CollectionAttribute<Usuario, Cargo> cargoCollection;
    public static volatile CollectionAttribute<Usuario, Corregimiento> corregimientoCollection;
    public static volatile ListAttribute<Usuario, Mediorecepcion> mediorecepcionList;
    public static volatile ListAttribute<Usuario, Circularcc> circularccList;
    public static volatile SingularAttribute<Usuario, Date> fechaModificacion;
    public static volatile CollectionAttribute<Usuario, TipoDocumental> tipoDocumentalCollection1;
    public static volatile ListAttribute<Usuario, Informe> informeList;
    public static volatile CollectionAttribute<Usuario, TipoDocumento> tipoDocumentoCollection;
    public static volatile CollectionAttribute<Usuario, Serie> serieCollection1;
    public static volatile CollectionAttribute<Usuario, Serie> serieCollection;
    public static volatile CollectionAttribute<Usuario, Campos> camposCollection;
    public static volatile ListAttribute<Usuario, Acta> actaList3;
    public static volatile CollectionAttribute<Usuario, PlantillaDocumental> plantillaDocumentalCollection1;
    public static volatile ListAttribute<Usuario, Acta> actaList2;
    public static volatile ListAttribute<Usuario, Acta> actaList1;
    public static volatile CollectionAttribute<Usuario, ProcesoDocumental> procesodocumentalCollection;
    public static volatile ListAttribute<Usuario, Carta> cartaList;
    public static volatile ListAttribute<Usuario, Circular> circularList2;
    public static volatile ListAttribute<Usuario, Sede> sedeList;
    public static volatile ListAttribute<Usuario, Circular> circularList1;
    public static volatile CollectionAttribute<Usuario, SubSerie> subSerieCollection;
    public static volatile CollectionAttribute<Usuario, Departamento> departamentoCollection;
    public static volatile CollectionAttribute<Usuario, SeccionSubSeccion> seccionSubSeccionCollection;
    public static volatile SingularAttribute<Usuario, String> celular;
    public static volatile SingularAttribute<Usuario, String> apelidos;
    public static volatile CollectionAttribute<Usuario, SeccionSubSeccion> seccionSubSeccionCollection1;
    public static volatile CollectionAttribute<Usuario, Transportador> transportadorCollection1;
    public static volatile ListAttribute<Usuario, Actaasistente> actaasistenteList;
    public static volatile CollectionAttribute<Usuario, Transportador> transportadorCollection;
    public static volatile ListAttribute<Usuario, Actainvitado> actainvitadoList;
    public static volatile CollectionAttribute<Usuario, TipoDocumental> tipoDocumentalCollection;
    public static volatile ListAttribute<Usuario, Prestamo> prestamoList;
    public static volatile CollectionAttribute<Usuario, UnidadDocumental> unidadDocumentalCollection1;
    public static volatile SingularAttribute<Usuario, Date> fechaCreacion;
    public static volatile CollectionAttribute<Usuario, Acl> aclCollection1;
    public static volatile ListAttribute<Usuario, Sede> sedeList1;

}