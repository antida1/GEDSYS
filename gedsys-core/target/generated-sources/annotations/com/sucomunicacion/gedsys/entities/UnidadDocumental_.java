package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.SubSerie;
import com.sucomunicacion.gedsys.entities.TipoDocumental;
import com.sucomunicacion.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
@StaticMetamodel(UnidadDocumental.class)
public class UnidadDocumental_ {

    public static volatile SingularAttribute<UnidadDocumental, SubSerie> subSerie;
    public static volatile SingularAttribute<UnidadDocumental, Usuario> creadoPor;
    public static volatile SingularAttribute<UnidadDocumental, Date> fechaModificacion;
    public static volatile CollectionAttribute<UnidadDocumental, Documento> documentoCollection;
    public static volatile SingularAttribute<UnidadDocumental, Boolean> borrado;
    public static volatile SingularAttribute<UnidadDocumental, Date> fechaCreacion;
    public static volatile SingularAttribute<UnidadDocumental, Usuario> modificadoPor;
    public static volatile SingularAttribute<UnidadDocumental, Integer> id;
    public static volatile SingularAttribute<UnidadDocumental, String> nombre;
    public static volatile CollectionAttribute<UnidadDocumental, TipoDocumental> tipoDocumentalCollection;

}
