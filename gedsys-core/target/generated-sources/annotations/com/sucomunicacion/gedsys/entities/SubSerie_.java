package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.Serie;
import com.sucomunicacion.gedsys.entities.UnidadDocumental;
import com.sucomunicacion.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
@StaticMetamodel(SubSerie.class)
public class SubSerie_ {

    public static volatile SingularAttribute<SubSerie, Usuario> creadoPor;
    public static volatile SingularAttribute<SubSerie, Date> fechaModificacion;
    public static volatile CollectionAttribute<SubSerie, Documento> documentoCollection;
    public static volatile SingularAttribute<SubSerie, Boolean> borrado;
    public static volatile SingularAttribute<SubSerie, Serie> serie;
    public static volatile SingularAttribute<SubSerie, Date> fechaCreacion;
    public static volatile SingularAttribute<SubSerie, Usuario> modificadoPor;
    public static volatile SingularAttribute<SubSerie, Integer> id;
    public static volatile CollectionAttribute<SubSerie, UnidadDocumental> unidadDocumentalCollection;
    public static volatile SingularAttribute<SubSerie, String> nombre;

}
