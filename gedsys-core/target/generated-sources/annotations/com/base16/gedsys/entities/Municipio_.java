package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Carta;
import com.base16.gedsys.entities.Corregimiento;
import com.base16.gedsys.entities.Departamento;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-19T16:44:07")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-20T17:33:31")
>>>>>>> origin/master
@StaticMetamodel(Municipio.class)
public class Municipio_ { 

    public static volatile SingularAttribute<Municipio, Usuario> creadoPor;
    public static volatile SingularAttribute<Municipio, String> codigo;
    public static volatile ListAttribute<Municipio, Carta> cartaList;
    public static volatile SingularAttribute<Municipio, Date> fechaModificacion;
    public static volatile CollectionAttribute<Municipio, Documento> documentoCollection;
    public static volatile SingularAttribute<Municipio, Boolean> borrado;
    public static volatile SingularAttribute<Municipio, Usuario> modificadoPor;
    public static volatile SingularAttribute<Municipio, Date> fechaCreacion;
    public static volatile SingularAttribute<Municipio, Departamento> departamento;
    public static volatile SingularAttribute<Municipio, Integer> id;
    public static volatile SingularAttribute<Municipio, String> nombre;
    public static volatile CollectionAttribute<Municipio, Corregimiento> corregimientoCollection;

}
