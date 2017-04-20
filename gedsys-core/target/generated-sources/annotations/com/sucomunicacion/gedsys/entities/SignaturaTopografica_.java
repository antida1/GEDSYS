package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.SignaturaTopografica;
import com.sucomunicacion.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
@StaticMetamodel(SignaturaTopografica.class)
public class SignaturaTopografica_ {

    public static volatile SingularAttribute<SignaturaTopografica, Usuario> creadoPor;
    public static volatile SingularAttribute<SignaturaTopografica, String> codigo;
    public static volatile SingularAttribute<SignaturaTopografica, Date> fechaModificacion;
    public static volatile CollectionAttribute<SignaturaTopografica, Documento> documentoCollection;
    public static volatile SingularAttribute<SignaturaTopografica, Boolean> borrado;
    public static volatile CollectionAttribute<SignaturaTopografica, SignaturaTopografica> signaturaTopograficaCollection;
    public static volatile SingularAttribute<SignaturaTopografica, SignaturaTopografica> dependeDe;
    public static volatile SingularAttribute<SignaturaTopografica, Usuario> modificadoPor;
    public static volatile SingularAttribute<SignaturaTopografica, Long> id;
    public static volatile SingularAttribute<SignaturaTopografica, String> nombre;
    public static volatile SingularAttribute<SignaturaTopografica, Date> fechaCracion;
    public static volatile SingularAttribute<SignaturaTopografica, Integer> nivel;

}
