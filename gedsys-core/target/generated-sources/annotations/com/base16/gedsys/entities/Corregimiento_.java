package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Municipio;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-27T10:29:11")
@StaticMetamodel(Corregimiento.class)
public class Corregimiento_ { 

    public static volatile SingularAttribute<Corregimiento, Usuario> creadoPor;
    public static volatile SingularAttribute<Corregimiento, String> codigo;
    public static volatile SingularAttribute<Corregimiento, Date> fechaModificacion;
    public static volatile CollectionAttribute<Corregimiento, Documento> documentoCollection;
    public static volatile SingularAttribute<Corregimiento, Municipio> municipio;
    public static volatile SingularAttribute<Corregimiento, Boolean> borrado;
    public static volatile SingularAttribute<Corregimiento, Date> fechaCreacion;
    public static volatile SingularAttribute<Corregimiento, Usuario> modificadoPor;
    public static volatile SingularAttribute<Corregimiento, Integer> id;
    public static volatile SingularAttribute<Corregimiento, String> nombre;

}