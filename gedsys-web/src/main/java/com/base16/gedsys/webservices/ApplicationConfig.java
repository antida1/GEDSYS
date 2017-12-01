/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.webservices;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author rober
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.base16.gedsys.webservices.AuthenticationFilter.class);
        resources.add(com.base16.gedsys.webservices.AuthenticationResource.class);
        resources.add(com.base16.gedsys.webservices.AuthenticationsResource.class);
        resources.add(com.base16.gedsys.webservices.DocumentoResource.class);
        resources.add(com.base16.gedsys.webservices.DocumentosResource.class);
        resources.add(com.base16.gedsys.webservices.DownloadResource.class);
        resources.add(com.base16.gedsys.webservices.NotifiacionResource.class);
        resources.add(com.base16.gedsys.webservices.NotifiacionesResource.class);
        resources.add(com.base16.gedsys.webservices.RegistroMovilResource.class);
        resources.add(com.base16.gedsys.webservices.RegistroMovilsResource.class);
    }
    
}
