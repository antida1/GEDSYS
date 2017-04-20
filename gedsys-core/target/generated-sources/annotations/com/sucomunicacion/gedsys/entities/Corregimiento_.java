package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.Municipio;
import com.sucomunicacion.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
@StaticMetamodel(Corregimiento.class)
public class Corregimiento_ {

    public static volatile SingularAttribute<Corregimiento, Usuario> creadoPor;
    public static volatile SingularAttribute<Corregimiento, String> codigo;
    public static volatile SingularAttribute<Corregimiento, Date> fechaModificacion;
    public static volatile SingularAttribute<Corregimiento, Municipio> municipio;
    public static volatile CollectionAttribute<Corregimiento, Documento> documentoCollection;
    public static volatile SingularAttribute<Corregimiento, Boolean> borrado;
    public static volatile SingularAttribute<Corregimiento, Date> fechaCreacion;
    public static volatile SingularAttribute<Corregimiento, Usuario> modificadoPor;
    public static volatile SingularAttribute<Corregimiento, Integer> id;
    public static volatile SingularAttribute<Corregimiento, String> nombre;

}
