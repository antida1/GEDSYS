/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.webservices;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.entities.Devices;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.fcm.PushFCMNotification;
import com.base16.gedsys.model.DevicesJpaController;
import com.base16.gedsys.model.UsuarioJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.utils.Mensajeria;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author rober
 */
@Path("/RegistroMovil")
public class RegistroMovilsResource extends BaseBean {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RegistroMovilsResource
     */
    public RegistroMovilsResource() {
    }

    /**
     * Retrieves representation of an instance of com.base16.gedsys.webservices.RegistroMovilsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
       JSONObject doc =  null;
        try {
            doc = new  JSONObject();
            doc.put("id", "0001235485");
            
        } catch (Exception e) {
        }
        return doc.toString();
    }

    /**
     * POST method for creating an instance of RegistroMovilResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String content) {
        try {
            JSONObject token = new JSONObject(content);
            String sToken = token.getString("token");
            String sEmailUsuario = token.getString("email");
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            UsuarioJpaController uJpa = new UsuarioJpaController(emf);
            Usuario usuario = uJpa.findByEmail(sEmailUsuario);
            Devices device = new Devices();
            device.setEmail(sEmailUsuario);
            device.setUsuario(usuario);
            device.setName(usuario.getNombres());
            device.setGcmRegid(sToken);
            DevicesJpaController dJpa = new DevicesJpaController(emf);
            dJpa.create(device);
            PushFCMNotification.PushFCMNotification(sToken, "GEDSYS", "Registro Movil satisfactorio");
            return Response.status(201).entity(content).build();
        } catch (Exception ex) {
            Logger.getLogger(RegistroMovilsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(201).entity(content).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public RegistroMovilResource getRegistroMovilResource(@PathParam("id") String id) {
        return RegistroMovilResource.getInstance(id);
    }
}
