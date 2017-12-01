/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.webservices;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.UsuarioJpaController;
import com.base16.gedsys.utils.JpaUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author rober
 */
public class AuthenticationResource extends BaseBean {

    private String id;

    /**
     * Creates a new instance of AuthenticationResource
     */
    private AuthenticationResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the AuthenticationResource
     */
    public static AuthenticationResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of AuthenticationResource class.
        return new AuthenticationResource(id);
    }

    /**
     * Retrieves representation of an instance of com.base16.gedsys.webservices.AuthenticationResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AuthenticationResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Usuario putJson(JSONObject content) {
        throw new UnsupportedOperationException();
    }

    /**
     * DELETE method for resource AuthenticationResource
     */
    @DELETE
    public void delete() {
    }
}
