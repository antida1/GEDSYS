package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.Departamento;
import com.sucomunicacion.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
@StaticMetamodel(Pais.class)
public class Pais_ {

    public static volatile SingularAttribute<Pais, Usuario> creadoPor;
    public static volatile SingularAttribute<Pais, String> codigo;
    public static volatile CollectionAttribute<Pais, Departamento> departamentoCollection;
    public static volatile SingularAttribute<Pais, Date> fechaModificacion;
    public static volatile SingularAttribute<Pais, Boolean> borrado;
    public static volatile SingularAttribute<Pais, Date> fechaCreacion;
    public static volatile SingularAttribute<Pais, Usuario> modificadoPor;
    public static volatile SingularAttribute<Pais, Integer> id;
    public static volatile SingularAttribute<Pais, String> nombre;

}
