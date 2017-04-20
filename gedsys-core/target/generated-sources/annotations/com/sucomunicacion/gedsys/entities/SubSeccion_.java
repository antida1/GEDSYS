package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.Seccion;
import com.sucomunicacion.gedsys.entities.Serie;
import com.sucomunicacion.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
@StaticMetamodel(SubSeccion.class)
public class SubSeccion_ {

    public static volatile SingularAttribute<SubSeccion, Usuario> creadoPor;
    public static volatile SingularAttribute<SubSeccion, Seccion> seccion;
    public static volatile SingularAttribute<SubSeccion, Date> fechaModificacion;
    public static volatile CollectionAttribute<SubSeccion, Serie> serieCollection;
    public static volatile CollectionAttribute<SubSeccion, Documento> documentoCollection;
    public static volatile SingularAttribute<SubSeccion, Boolean> borrado;
    public static volatile SingularAttribute<SubSeccion, Date> fechaCreacion;
    public static volatile SingularAttribute<SubSeccion, Usuario> modificadoPor;
    public static volatile SingularAttribute<SubSeccion, Integer> id;
    public static volatile SingularAttribute<SubSeccion, String> nombre;

}
