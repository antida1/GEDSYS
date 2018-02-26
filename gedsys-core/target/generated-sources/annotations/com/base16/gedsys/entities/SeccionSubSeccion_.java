package com.base16.gedsys.entities;

import com.base16.gedsys.entities.SeccionSubSeccion;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-23T15:39:52")
@StaticMetamodel(SeccionSubSeccion.class)
public class SeccionSubSeccion_ { 

    public static volatile SingularAttribute<SeccionSubSeccion, Usuario> creadoPor;
    public static volatile SingularAttribute<SeccionSubSeccion, String> codigo;
    public static volatile SingularAttribute<SeccionSubSeccion, Date> fechaModificacion;
    public static volatile SingularAttribute<SeccionSubSeccion, Usuario> responsable;
    public static volatile CollectionAttribute<SeccionSubSeccion, SeccionSubSeccion> seccionSubSeccionCollection;
    public static volatile SingularAttribute<SeccionSubSeccion, Boolean> borrado;
    public static volatile SingularAttribute<SeccionSubSeccion, SeccionSubSeccion> dependeDe;
    public static volatile SingularAttribute<SeccionSubSeccion, Date> fechaCreacion;
    public static volatile SingularAttribute<SeccionSubSeccion, Usuario> modificadoPor;
    public static volatile SingularAttribute<SeccionSubSeccion, Integer> id;
    public static volatile SingularAttribute<SeccionSubSeccion, String> nombre;

}