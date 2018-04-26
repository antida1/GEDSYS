/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.webservices;

import com.base16.gedsys.bean.BaseBean;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author rober
 */
@Path("Images")
public class ImagesResource extends BaseBean {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ImagesResource
     */
    public ImagesResource() {
    }

    /**
     * Retrieves representation of an instance of com.base16.gedsys.bean.ImagesResource
     * @return an instance of byte[]
     */
    @GET
    @Produces({"image/*"})
    public InputStream getImageFile(@QueryParam("name") String image) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(this.getDocumenstSavePath() + File.separatorChar + "images" + File.separatorChar + image));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImagesResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fis;
    }
}
