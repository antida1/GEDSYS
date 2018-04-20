package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Grupo;
import com.base16.gedsys.entities.Modulo;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-20T13:28:05")
@StaticMetamodel(Acl.class)
public class Acl_ { 

    public static volatile SingularAttribute<Acl, Boolean> canExport;
    public static volatile SingularAttribute<Acl, Boolean> canUpdate;
    public static volatile SingularAttribute<Acl, Date> fechaModificacion;
    public static volatile SingularAttribute<Acl, Boolean> canRead;
    public static volatile SingularAttribute<Acl, Grupo> grupo;
    public static volatile SingularAttribute<Acl, Usuario> modificadoPor;
    public static volatile SingularAttribute<Acl, Boolean> canCreate;
    public static volatile SingularAttribute<Acl, Boolean> canGeneratePDF;
    public static volatile SingularAttribute<Acl, Usuario> creadoPor;
    public static volatile SingularAttribute<Acl, Date> fechaCreacion;
    public static volatile SingularAttribute<Acl, Boolean> canDelete;
    public static volatile SingularAttribute<Acl, Integer> id;
    public static volatile SingularAttribute<Acl, Modulo> modulo;

}