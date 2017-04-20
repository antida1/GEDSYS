package com.sucomunicacion.gedsys.entities;

import com.sucomunicacion.gedsys.entities.Consecutivo;
import com.sucomunicacion.gedsys.entities.Documento;
import com.sucomunicacion.gedsys.entities.PlantillaDocumental;
import com.sucomunicacion.gedsys.entities.ProcesoTipoDocumento;
import com.sucomunicacion.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-18T14:21:26")
@StaticMetamodel(TipoDocumento.class)
public class TipoDocumento_ {

    public static volatile SingularAttribute<TipoDocumento, Usuario> creadoPor;
    public static volatile SingularAttribute<TipoDocumento, Date> fechaModificacion;
    public static volatile CollectionAttribute<TipoDocumento, PlantillaDocumental> plantillaDocumentalCollection;
    public static volatile CollectionAttribute<TipoDocumento, Consecutivo> consecutivoCollection;
    public static volatile CollectionAttribute<TipoDocumento, ProcesoTipoDocumento> procesoTipoDocumentoCollection;
    public static volatile CollectionAttribute<TipoDocumento, Documento> documentoCollection;
    public static volatile SingularAttribute<TipoDocumento, Boolean> borrado;
    public static volatile SingularAttribute<TipoDocumento, Date> fechaCreacion;
    public static volatile SingularAttribute<TipoDocumento, Usuario> modificadoPor;
    public static volatile SingularAttribute<TipoDocumento, Integer> id;
    public static volatile SingularAttribute<TipoDocumento, String> nombre;

}
