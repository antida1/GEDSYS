package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.SubSeccion;
import com.sucomunicacion.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
@StaticMetamodel(Seccion.class)
public class Seccion_ {

    public static volatile SingularAttribute<Seccion, Usuario> creadoPor;
    public static volatile SingularAttribute<Seccion, String> codigo;
    public static volatile SingularAttribute<Seccion, Date> fechaModificacion;
    public static volatile CollectionAttribute<Seccion, Documento> documentoCollection;
    public static volatile SingularAttribute<Seccion, Boolean> borrado;
    public static volatile SingularAttribute<Seccion, Date> fechaCreacion;
    public static volatile SingularAttribute<Seccion, Usuario> modificadoPor;
    public static volatile SingularAttribute<Seccion, Integer> id;
    public static volatile SingularAttribute<Seccion, String> nombre;
    public static volatile CollectionAttribute<Seccion, SubSeccion> subSeccionCollection;

}
