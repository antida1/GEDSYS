package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.SubSeccion;
import com.sucomunicacion.gedsys.entities.SubSerie;
import com.sucomunicacion.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
@StaticMetamodel(Serie.class)
public class Serie_ {

    public static volatile SingularAttribute<Serie, Usuario> creadoPor;
    public static volatile SingularAttribute<Serie, Date> fechaModificacion;
    public static volatile CollectionAttribute<Serie, Documento> documentoCollection;
    public static volatile SingularAttribute<Serie, Boolean> borrado;
    public static volatile CollectionAttribute<Serie, SubSerie> subSerieCollection;
    public static volatile SingularAttribute<Serie, Date> fechaCreacion;
    public static volatile SingularAttribute<Serie, Usuario> modificadoPor;
    public static volatile SingularAttribute<Serie, Integer> id;
    public static volatile SingularAttribute<Serie, String> nombre;
    public static volatile SingularAttribute<Serie, SubSeccion> subSeccion;

}
