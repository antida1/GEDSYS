package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
@StaticMetamodel(ClaseDocumento.class)
public class ClaseDocumento_ {

    public static volatile SingularAttribute<ClaseDocumento, String> descripcion;
    public static volatile SingularAttribute<ClaseDocumento, Usuario> creadoPor;
    public static volatile SingularAttribute<ClaseDocumento, String> codigo;
    public static volatile SingularAttribute<ClaseDocumento, Date> fechaModificacion;
    public static volatile CollectionAttribute<ClaseDocumento, Documento> documentoCollection;
    public static volatile SingularAttribute<ClaseDocumento, Integer> borrado;
    public static volatile SingularAttribute<ClaseDocumento, Date> fechaCreacion;
    public static volatile SingularAttribute<ClaseDocumento, Usuario> modificadoPor;
    public static volatile SingularAttribute<ClaseDocumento, Integer> id;
    public static volatile SingularAttribute<ClaseDocumento, String> nombre;

}
