package com.base16.gedsys.entities;

import com.base16.gedsys.entities.SeccionSubSeccion;
import com.base16.gedsys.entities.SubSerie;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-05T18:57:32")
@StaticMetamodel(Serie.class)
public class Serie_ { 

    public static volatile SingularAttribute<Serie, Usuario> creadoPor;
    public static volatile SingularAttribute<Serie, String> codigo;
    public static volatile SingularAttribute<Serie, Date> fechaModificacion;
    public static volatile SingularAttribute<Serie, Boolean> borrado;
    public static volatile CollectionAttribute<Serie, SubSerie> subSerieCollection;
    public static volatile SingularAttribute<Serie, SeccionSubSeccion> seccionSubseccion;
    public static volatile SingularAttribute<Serie, Date> fechaCreacion;
    public static volatile SingularAttribute<Serie, Usuario> modificadoPor;
    public static volatile SingularAttribute<Serie, Integer> id;
    public static volatile SingularAttribute<Serie, String> nombre;

}