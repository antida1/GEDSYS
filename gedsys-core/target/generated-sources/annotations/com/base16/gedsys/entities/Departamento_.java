package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Municipio;
import com.base16.gedsys.entities.Pais;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-05T11:51:58")
@StaticMetamodel(Departamento.class)
public class Departamento_ { 

    public static volatile CollectionAttribute<Departamento, Municipio> municipioCollection;
    public static volatile SingularAttribute<Departamento, Usuario> creadoPor;
    public static volatile SingularAttribute<Departamento, String> codigo;
    public static volatile SingularAttribute<Departamento, Date> fechaModificacion;
    public static volatile SingularAttribute<Departamento, Boolean> borrado;
    public static volatile SingularAttribute<Departamento, String> name;
    public static volatile SingularAttribute<Departamento, Date> fechaCreacion;
    public static volatile SingularAttribute<Departamento, Usuario> modificadoPor;
    public static volatile SingularAttribute<Departamento, Integer> id;
    public static volatile SingularAttribute<Departamento, Pais> pais;

}