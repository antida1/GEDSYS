package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-02T16:46:33")
@StaticMetamodel(Mediorecepcion.class)
public class Mediorecepcion_ { 

    public static volatile SingularAttribute<Mediorecepcion, Usuario> creadoPor;
    public static volatile SingularAttribute<Mediorecepcion, String> codigo;
    public static volatile SingularAttribute<Mediorecepcion, Short> estado;
    public static volatile ListAttribute<Mediorecepcion, Documento> documentoList;
    public static volatile SingularAttribute<Mediorecepcion, Date> fechaModificacion;
    public static volatile SingularAttribute<Mediorecepcion, Date> fechaCreacion;
    public static volatile SingularAttribute<Mediorecepcion, Usuario> modificadoPor;
    public static volatile SingularAttribute<Mediorecepcion, Integer> id;
    public static volatile SingularAttribute<Mediorecepcion, String> nombre;

}