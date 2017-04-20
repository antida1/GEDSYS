package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.Corregimiento;
import com.sucomunicacion.gedsys.entities.Departamento;
import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
@StaticMetamodel(Municipio.class)
public class Municipio_ {

    public static volatile SingularAttribute<Municipio, Usuario> creadoPor;
    public static volatile SingularAttribute<Municipio, String> codigo;
    public static volatile SingularAttribute<Municipio, Date> fechaModificacion;
    public static volatile CollectionAttribute<Municipio, Documento> documentoCollection;
    public static volatile SingularAttribute<Municipio, Boolean> borrado;
    public static volatile SingularAttribute<Municipio, Date> fechaCreacion;
    public static volatile SingularAttribute<Municipio, Departamento> departamento;
    public static volatile SingularAttribute<Municipio, Usuario> modificadoPor;
    public static volatile SingularAttribute<Municipio, Integer> id;
    public static volatile SingularAttribute<Municipio, String> nombre;
    public static volatile CollectionAttribute<Municipio, Corregimiento> corregimientoCollection;

}
