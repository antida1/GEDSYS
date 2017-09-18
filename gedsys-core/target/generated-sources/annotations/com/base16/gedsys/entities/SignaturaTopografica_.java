package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.SignaturaTopografica;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-18T10:40:12")
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
    public static volatile SingularAttribute<SignaturaTopografica, Date> fechaCracion;
    public static volatile SingularAttribute<SignaturaTopografica, String> nombre;
    public static volatile SingularAttribute<SignaturaTopografica, Integer> nivel;

}