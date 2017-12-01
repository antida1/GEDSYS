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
import java.io.IOException;
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
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
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
    public JSONObject postJson(String data) {
        Usuario usuario = null;
        JSONObject ret = null;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            JSONObject content = new JSONObject(data);
            UsuarioJpaController uJpa = new UsuarioJpaController(emf);
            String email = content.getString("email");
            String password = Authentication.md5(content.getString("password"));
            usuario = uJpa.autheticate(email, password);
            
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(usuario);
            ret = new JSONObject(json);
        } catch (JSONException | IOException ex) {
            Logger.getLogger(AuthenticationResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public AuthenticationResource getAuthenticationResource(@PathParam("id") String id) {
        return AuthenticationResource.getInstance(id);
    }
}
