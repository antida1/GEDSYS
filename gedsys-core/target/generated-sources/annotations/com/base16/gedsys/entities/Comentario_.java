package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Documento;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-13T08:55:03")
@StaticMetamodel(Comentario.class)
public class Comentario_ { 

    public static volatile SingularAttribute<Comentario, Integer> creadoPor;
    public static volatile SingularAttribute<Comentario, Date> fechaModificacion;
    public static volatile SingularAttribute<Comentario, Date> fechaCreacion;
    public static volatile SingularAttribute<Comentario, Integer> modificadoPor;
    public static volatile SingularAttribute<Comentario, Documento> documento;
    public static volatile SingularAttribute<Comentario, Integer> id;
    public static volatile SingularAttribute<Comentario, String> comentario;

}