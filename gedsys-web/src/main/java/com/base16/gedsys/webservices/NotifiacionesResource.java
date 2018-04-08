/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.webservices;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.entities.Notificacion;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.NotificacionJpaController;
import com.base16.gedsys.model.UsuarioJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.utils.DateTimeUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author rober
 */
@Path("/Notificaciones")
public class NotifiacionesResource extends BaseBean {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of NotifiacionsResource
     */
    public NotifiacionesResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.base16.gedsys.webservices.NotifiacionsResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@QueryParam("useremail") String email) {
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());

        UsuarioJpaController uJpa = new UsuarioJpaController(emf);
        Usuario usuario = uJpa.findByEmail(email);

        NotificacionJpaController nJpa = new NotificacionJpaController(emf);
        List<Notificacion> notificaciones = nJpa.findNotificacionEntities();

        JsonArray notifys = new JsonArray();
        for (Notificacion notificacion : notificaciones) {
            JsonObject object = new JsonObject();
            object.addProperty("asunto", notificacion.getAsunto());
            object.addProperty("fechaInicio", DateTimeUtils.getFormattedTime(notificacion.getFechaInicio(), "yyyy-MM-dd HH:mm:ss.SSS"));
            object.addProperty("fechaFinalizacion", DateTimeUtils.getFormattedTime(notificacion.getFechaFinalizacion(), "yyyy-MM-dd HH:mm:ss.SSS"));
            notifys.add(object);
        }
        if (notifys.isJsonNull()) {
            return "[]";
        }
        //NotificacionBean notificacionBean = new NotificacionBean();
        //notificacionBean.listarPorResponsable(usuario);
        //List<Notificacion> notificaciones =  notificacionBean.getNotificaciones();
        return notifys.toString();
    }

    /**
     * POST method for creating an instance of NotifiacionResource
     *
     * @param email
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
    public NotifiacionResource getNotifiacionResource(@PathParam("id") String id) {
        return NotifiacionResource.getInstance(id);
    }

}
