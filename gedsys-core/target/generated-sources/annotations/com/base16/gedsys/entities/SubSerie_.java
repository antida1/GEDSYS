package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Serie;
import com.base16.gedsys.entities.UnidadDocumental;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-13T08:21:24")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-13T08:55:04")
>>>>>>> origin/Gedsys-Lina
@StaticMetamodel(SubSerie.class)
public class SubSerie_ { 

    public static volatile SingularAttribute<SubSerie, Usuario> creadoPor;
    public static volatile SingularAttribute<SubSerie, String> codigo;
    public static volatile SingularAttribute<SubSerie, Date> fechaModificacion;
    public static volatile SingularAttribute<SubSerie, Boolean> borrado;
    public static volatile SingularAttribute<SubSerie, Serie> serie;
    public static volatile SingularAttribute<SubSerie, Date> fechaCreacion;
    public static volatile SingularAttribute<SubSerie, Usuario> modificadoPor;
    public static volatile SingularAttribute<SubSerie, Integer> id;
    public static volatile CollectionAttribute<SubSerie, UnidadDocumental> unidadDocumentalCollection;
    public static volatile SingularAttribute<SubSerie, String> nombre;

}
