/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.webservices;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.model.DocumentoJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.WebConfiguration;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

/**
 * REST Web Service
 *
 * @author rober
 */
@Path("Download")
public class DownloadResource extends BaseBean {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DownloadResource
     */
    public DownloadResource() {
    }

    @GET
    @Path("/pdf")
    @Produces({"application/pdf"})
    public byte[] downloadPdfFile(@QueryParam("id") Long id)  {
        try {    
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            DocumentoJpaController dJpa = new DocumentoJpaController(emf);
            Documento documento = dJpa.findDocumento(id);
            java.nio.file.Path path = Paths.get(WebConfiguration.getInstance().getProperty("PathData")+ File.separatorChar + documento.getRutaArchivo());
            byte[] data = Files.readAllBytes(path);
            return data;

        } catch (IOException | NamingException ex) {
            Logger.getLogger(DownloadResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
