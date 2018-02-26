package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.UnidadDocumental;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-26T11:00:53")
@StaticMetamodel(TipoDocumental.class)
public class TipoDocumental_ { 

    public static volatile SingularAttribute<TipoDocumental, Boolean> documentoExterno;
    public static volatile SingularAttribute<TipoDocumental, Boolean> requiereRespuesta;
    public static volatile SingularAttribute<TipoDocumental, UnidadDocumental> unidadDocumental;
    public static volatile SingularAttribute<TipoDocumental, Date> fechaModificacion;
    public static volatile SingularAttribute<TipoDocumental, String> tipoCalendario;
    public static volatile CollectionAttribute<TipoDocumental, Documento> documentoCollection;
    public static volatile SingularAttribute<TipoDocumental, Integer> tiempoRespuesta;
    public static volatile SingularAttribute<TipoDocumental, Usuario> modificadoPor;
    public static volatile SingularAttribute<TipoDocumental, String> nombre;
    public static volatile SingularAttribute<TipoDocumental, Usuario> creadoPor;
    public static volatile SingularAttribute<TipoDocumental, Boolean> borrado;
    public static volatile SingularAttribute<TipoDocumental, Date> fechaCreacion;
    public static volatile SingularAttribute<TipoDocumental, Integer> id;
    public static volatile SingularAttribute<TipoDocumental, String> estampadoCronologico;

}