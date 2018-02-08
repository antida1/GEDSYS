package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Consecutivo;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.PlantillaDocumental;
import com.base16.gedsys.entities.ProcesoTipoDocumento;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-08T14:14:18")
@StaticMetamodel(TipoDocumento.class)
public class TipoDocumento_ { 

    public static volatile SingularAttribute<TipoDocumento, String> tipoComunicacion;
    public static volatile SingularAttribute<TipoDocumento, Short> requiereRespuesta;
    public static volatile SingularAttribute<TipoDocumento, Date> fechaModificacion;
    public static volatile CollectionAttribute<TipoDocumento, PlantillaDocumental> plantillaDocumentalCollection;
    public static volatile CollectionAttribute<TipoDocumento, Consecutivo> consecutivoCollection;
    public static volatile CollectionAttribute<TipoDocumento, ProcesoTipoDocumento> procesoTipoDocumentoCollection;
    public static volatile SingularAttribute<TipoDocumento, String> tipoCalendario;
    public static volatile CollectionAttribute<TipoDocumento, Documento> documentoCollection;
    public static volatile SingularAttribute<TipoDocumento, Usuario> modificadoPor;
    public static volatile SingularAttribute<TipoDocumento, String> nombre;
    public static volatile SingularAttribute<TipoDocumento, Usuario> creadoPor;
    public static volatile SingularAttribute<TipoDocumento, Short> esPQRSF;
    public static volatile SingularAttribute<TipoDocumento, Boolean> borrado;
    public static volatile SingularAttribute<TipoDocumento, Integer> diasRespuesta;
    public static volatile SingularAttribute<TipoDocumento, Date> fechaCreacion;
    public static volatile SingularAttribute<TipoDocumento, Integer> id;

}