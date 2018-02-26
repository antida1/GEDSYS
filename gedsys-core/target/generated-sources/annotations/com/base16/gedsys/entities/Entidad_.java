package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-26T11:00:53")
@StaticMetamodel(Entidad.class)
public class Entidad_ { 

    public static volatile SingularAttribute<Entidad, Date> fechaModificacion;
    public static volatile CollectionAttribute<Entidad, Documento> documentoCollection;
    public static volatile SingularAttribute<Entidad, String> direccion;
    public static volatile SingularAttribute<Entidad, Usuario> modificadoPor;
    public static volatile SingularAttribute<Entidad, String> nombre;
    public static volatile SingularAttribute<Entidad, Usuario> creadoPor;
    public static volatile SingularAttribute<Entidad, String> tipoDocumento;
    public static volatile SingularAttribute<Entidad, Boolean> borrado;
    public static volatile SingularAttribute<Entidad, Date> fechaCreacion;
    public static volatile SingularAttribute<Entidad, Integer> id;
    public static volatile SingularAttribute<Entidad, String> numeroDocumento;
    public static volatile SingularAttribute<Entidad, String> telefono;
    public static volatile SingularAttribute<Entidad, String> email;

}