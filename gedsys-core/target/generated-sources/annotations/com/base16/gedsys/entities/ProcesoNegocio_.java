package com.base16.gedsys.entities;

import com.base16.gedsys.entities.MonitoresProceso;
import com.base16.gedsys.entities.ProcesoDocumental;
import com.base16.gedsys.entities.ProcesoNegocio;
import com.base16.gedsys.entities.ProcesoTipoDocumento;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-03T16:17:07")
@StaticMetamodel(ProcesoNegocio.class)
public class ProcesoNegocio_ { 

    public static volatile SingularAttribute<ProcesoNegocio, String> descripcion;
    public static volatile SingularAttribute<ProcesoNegocio, Date> fechaModificacion;
    public static volatile SingularAttribute<ProcesoNegocio, String> nombreProceso;
    public static volatile SingularAttribute<ProcesoNegocio, Boolean> notificarPorCorreo;
    public static volatile SingularAttribute<ProcesoNegocio, ProcesoNegocio> siguienteProceso;
    public static volatile SingularAttribute<ProcesoNegocio, Integer> unidadMedida;
    public static volatile CollectionAttribute<ProcesoNegocio, ProcesoTipoDocumento> procesoTipoDocumentoCollection;
    public static volatile SingularAttribute<ProcesoNegocio, Integer> plazo;
    public static volatile SingularAttribute<ProcesoNegocio, Usuario> modificadoPor;
    public static volatile SingularAttribute<ProcesoNegocio, Boolean> conteoDias;
    public static volatile SingularAttribute<ProcesoNegocio, Integer> destinatario;
    public static volatile SingularAttribute<ProcesoNegocio, Usuario> creadoPor;
    public static volatile SingularAttribute<ProcesoNegocio, Boolean> notificarPorPopup;
    public static volatile SingularAttribute<ProcesoNegocio, Boolean> notificarPorSMS;
    public static volatile SingularAttribute<ProcesoNegocio, Boolean> notificarPorPush;
    public static volatile SingularAttribute<ProcesoNegocio, Boolean> borrado;
    public static volatile CollectionAttribute<ProcesoNegocio, ProcesoNegocio> procesoNegocioCollection;
    public static volatile SingularAttribute<ProcesoNegocio, Date> fechaCreacion;
    public static volatile SingularAttribute<ProcesoNegocio, Long> id;
    public static volatile CollectionAttribute<ProcesoNegocio, MonitoresProceso> monitoresProcesoCollection;
    public static volatile CollectionAttribute<ProcesoNegocio, ProcesoDocumental> procesodocumentalCollection;

}