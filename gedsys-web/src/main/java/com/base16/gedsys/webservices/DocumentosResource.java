/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.webservices;

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
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author rober
 */
@Path("/documentos")
public class DocumentosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DocumentosResource
     */
    public DocumentosResource() {
    }

    /**
     * Retrieves representation of an instance of com.base16.gedsys.servicios.DocumentosResource
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
     * POST method for creating an instance of DocumentoResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String content) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public DocumentoResource getDocumentoResource(@PathParam("id") String id) {
        return DocumentoResource.getInstance(id);
    }
}