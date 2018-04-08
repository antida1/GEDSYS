/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author rober
 */
public class Configuration {

    Properties prop;
    String path = "";

    public Configuration(String path) {
        this.path = path;
        this.prop = new Properties();
        loadProperties();
    }

    public Properties getProp() {
        return prop;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }

    public void load() {
        loadProperties();
    }

    private void loadProperties() {
        InputStream input = null;
        try {
            input = new FileInputStream(this.path);
            prop.load(input);
        } catch (IOException ioe) {
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {

                }
            }
        }
    }

    public void save() {
        try {
            File f = new File(this.path);
            OutputStream out = new FileOutputStream(f);
            prop.store(out, "This is an optional header comment string");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
