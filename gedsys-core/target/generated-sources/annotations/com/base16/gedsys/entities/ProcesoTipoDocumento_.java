package com.base16.gedsys.entities;

import com.base16.gedsys.entities.ProcesoNegocio;
import com.base16.gedsys.entities.TipoDocumento;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-24T23:48:11")
@StaticMetamodel(ProcesoTipoDocumento.class)
public class ProcesoTipoDocumento_ { 

    public static volatile SingularAttribute<ProcesoTipoDocumento, Usuario> creadoPor;
    public static volatile SingularAttribute<ProcesoTipoDocumento, TipoDocumento> tipoDocumento;
    public static volatile SingularAttribute<ProcesoTipoDocumento, Date> fechaModificacion;
    public static volatile SingularAttribute<ProcesoTipoDocumento, ProcesoNegocio> proceso;
    public static volatile SingularAttribute<ProcesoTipoDocumento, Boolean> borrado;
    public static volatile SingularAttribute<ProcesoTipoDocumento, Date> fechaCreacion;
    public static volatile SingularAttribute<ProcesoTipoDocumento, Usuario> modificadoPor;
    public static volatile SingularAttribute<ProcesoTipoDocumento, Long> id;

}