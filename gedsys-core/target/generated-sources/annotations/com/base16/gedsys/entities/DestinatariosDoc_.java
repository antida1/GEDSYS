package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-23T10:01:25")
@StaticMetamodel(DestinatariosDoc.class)
public class DestinatariosDoc_ { 

    public static volatile SingularAttribute<DestinatariosDoc, Short> principal;
    public static volatile SingularAttribute<DestinatariosDoc, Usuario> creadoPor;
    public static volatile SingularAttribute<DestinatariosDoc, Usuario> destinatarioId;
    public static volatile SingularAttribute<DestinatariosDoc, Date> fechaModificacion;
    public static volatile SingularAttribute<DestinatariosDoc, Date> fechaCreacion;
    public static volatile SingularAttribute<DestinatariosDoc, Usuario> modificadoPor;
    public static volatile SingularAttribute<DestinatariosDoc, Long> id;
    public static volatile SingularAttribute<DestinatariosDoc, Documento> documentoId;

}