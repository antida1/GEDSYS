package com.base16.gedsys.entities;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-26T11:00:53")
@StaticMetamodel(Autor.class)
public class Autor_ { 

    public static volatile SingularAttribute<Autor, String> apellidos;
    public static volatile SingularAttribute<Autor, Usuario> creadoPor;
    public static volatile SingularAttribute<Autor, Date> fechaModificacion;
    public static volatile CollectionAttribute<Autor, Documento> documentoCollection;
    public static volatile SingularAttribute<Autor, Date> fechaCreacion;
    public static volatile SingularAttribute<Autor, Usuario> modificadoPor;
    public static volatile SingularAttribute<Autor, Long> id;
    public static volatile SingularAttribute<Autor, String> nombres;

}