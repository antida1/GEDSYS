package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-05T11:51:58")
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