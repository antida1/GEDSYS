package com.base16.gedsys.entities;

import com.base16.gedsys.entities.ProcesoNegocio;
import com.base16.gedsys.entities.Usuario;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-19T16:44:06")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-20T17:33:31")
>>>>>>> origin/master
@StaticMetamodel(MonitoresProceso.class)
public class MonitoresProceso_ { 

    public static volatile SingularAttribute<MonitoresProceso, Usuario> creadoPor;
    public static volatile SingularAttribute<MonitoresProceso, Date> fechaModificacion;
    public static volatile SingularAttribute<MonitoresProceso, ProcesoNegocio> proceso;
    public static volatile SingularAttribute<MonitoresProceso, Boolean> borrado;
    public static volatile SingularAttribute<MonitoresProceso, Date> fechaCreacion;
    public static volatile SingularAttribute<MonitoresProceso, Usuario> modificadoPor;
    public static volatile SingularAttribute<MonitoresProceso, BigInteger> monitor;
    public static volatile SingularAttribute<MonitoresProceso, Long> id;

}
