/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author rober
 */
public class Configuration {
    
    Properties prop;

    public Configuration(String path) {
        this.prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(path);
            prop.load(input);
        } catch (IOException ioe) {
        }
        finally{
            if(input != null){
                try {
                    input.close();
                } catch (IOException e) {
                    
                }
            }
        }
    }
    
    public Properties getProp() {
        return prop;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }
}
