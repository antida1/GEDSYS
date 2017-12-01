/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author rober
 */
public class RegistroMovilResource {

    private String id;

    /**
     * Creates a new instance of RegistroMovilResource
     */
    private RegistroMovilResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the RegistroMovilResource
     */
    public static RegistroMovilResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of RegistroMovilResource class.
        return new RegistroMovilResource(id);
    }

    /**
     * Retrieves representation of an instance of com.base16.gedsys.webservices.RegistroMovilResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of RegistroMovilResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    /**
     * DELETE method for resource RegistroMovilResource
     */
    @DELETE
    public void delete() {
    }
}
