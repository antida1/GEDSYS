/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.web.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author rober
 */
public class WebConfiguration {
    public static String configFilePath(){
        String path;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        path = externalContext.getRealPath("/WEB-INF/config.properties");
        return path;
    }
}
