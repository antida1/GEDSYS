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
import com.base16.gedsys.web.utils.SessionUtils;
import com.base16.gedsys.web.utils.WebConfiguration;
import com.base16.utils.CryptoException;
import com.base16.utils.CryptoUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
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
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;

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
    public byte[] downloadPdfFile(@QueryParam("id") Long id) {
        byte[] data = null;
        try {
            //TODO> pendiente decifrar archivo y tomar la ubicacion del mismo.
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            DocumentoJpaController dJpa = new DocumentoJpaController(emf);
            Documento documento = dJpa.findDocumento(id);
            if (documento.getNombreDocumento() != null) {
                String filePath = this.getDocumenstSavePath() + File.separatorChar + documento.getNombreDocumento();

                if (!filePath.isEmpty()) {
                    File tempFile = new File(filePath);
                    if (tempFile.exists()) {
                        if (this.getEncriptFiles() == true) {
                            UUID guid = UUID.randomUUID();
                            File outputFile = new File(this.getDocumenstSavePath() + File.separatorChar + guid.toString());
                            CryptoUtils.decrypt("Mary has one cat", tempFile, outputFile);
                            FileInputStream fis = new FileInputStream(outputFile);
                            data = IOUtils.toByteArray(fis);
                        } else {
                            FileInputStream fis = new FileInputStream(tempFile);
                            data = IOUtils.toByteArray(fis);
                        }
                    }
                }
            } else {
                File tempFile = new File(this.getDocumenstSavePath() + File.separatorChar + "noexiste.pdf");
                FileInputStream fis = new FileInputStream(tempFile);
                data = IOUtils.toByteArray(fis);
            }

        } catch (IOException | CryptoException ex) {
            Logger.getLogger(DownloadResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}
