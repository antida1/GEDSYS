/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.webservices;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.UsuarioJpaController;
import com.base16.gedsys.security.Authentication;
import com.base16.gedsys.utils.JpaUtils;
import com.google.gson.JsonObject;
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
@Path("/Authentication")
public class AuthenticationsResource extends BaseBean {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AuthenticationsResource
     */
    public AuthenticationsResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.base16.gedsys.webservices.AuthenticationsResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * POST method for creating an instance of AuthenticationResource
     *
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String data) throws JSONException {
        String result = "";
        //JSONObject ret = null;
       try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            JSONObject content = new JSONObject(data);
            UsuarioJpaController uJpa = new UsuarioJpaController(emf);
            String email = content.getString("email");
            String password = Authentication.md5(content.getString("password"));
            Usuario usuario = uJpa.autheticate(email, password);
            if(usuario != null){
            JsonObject object = new JsonObject();
            object.addProperty("nombre", usuario.getNombres() );
            object.addProperty("apellido", usuario.getApelidos() );
            object.addProperty("email",usuario.getEmail());
            object.addProperty("foto", usuario.getFoto());
            result = object.toString();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Usuario not found: " + content.get("email")).build();
            }
            
        } catch (Exception ex) {
            Logger.getLogger(AuthenticationResource.class.getName()).log(Level.SEVERE, null, ex);
            JSONObject object = new JSONObject("error");
            object.accumulate("error", ex.getMessage());
            result = object.toString();
        }
        return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public AuthenticationResource getAuthenticationResource(@PathParam("id") String id) {
        return AuthenticationResource.getInstance(id);
    }
}
