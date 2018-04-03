package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Acl;
import com.base16.gedsys.entities.CamposPlantilla;
import com.base16.gedsys.entities.Modulo;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-03T16:17:07")
@StaticMetamodel(Modulo.class)
public class Modulo_ { 

    public static volatile SingularAttribute<Modulo, String> descripcion;
    public static volatile SingularAttribute<Modulo, Date> fechaModificacion;
    public static volatile SingularAttribute<Modulo, String> moduloIcon;
    public static volatile SingularAttribute<Modulo, Usuario> modificadoPor;
    public static volatile SingularAttribute<Modulo, String> nombre;
    public static volatile SingularAttribute<Modulo, Usuario> creadoPor;
    public static volatile CollectionAttribute<Modulo, Modulo> moduloCollection;
    public static volatile CollectionAttribute<Modulo, Acl> aclCollection;
    public static volatile SingularAttribute<Modulo, Boolean> oculto;
    public static volatile CollectionAttribute<Modulo, CamposPlantilla> camposPlantillaCollection;
    public static volatile SingularAttribute<Modulo, Boolean> borrado;
    public static volatile SingularAttribute<Modulo, Modulo> dependeDe;
    public static volatile SingularAttribute<Modulo, Date> fechaCreacion;
    public static volatile SingularAttribute<Modulo, Integer> id;
    public static volatile SingularAttribute<Modulo, String> urlModulo;

}