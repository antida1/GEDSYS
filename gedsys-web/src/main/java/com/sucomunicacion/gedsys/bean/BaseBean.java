/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.bean;

import com.sucomunicacion.gedsys.web.utils.WebConfiguration;

/**
 *
 * @author rober
 */
public class BaseBean {
    
    String configFilePath = "";

    public BaseBean() {
        configFilePath = WebConfiguration.configFilePath();
    }  
    
    public String getConfigFilePath() {
        
        
        return configFilePath;
    }
        
}
