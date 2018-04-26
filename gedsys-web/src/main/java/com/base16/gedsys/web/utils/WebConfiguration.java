/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.web.utils;

import com.base16.gedsys.config.Configuration;
import java.io.File;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author rober
 */
public class WebConfiguration {
    
    private static WebConfiguration instance = null;
    private static Configuration config = null;
    private static String configFilePath;
    private static Properties prop;
    protected WebConfiguration(){
    }
    
    public static WebConfiguration getInstance() throws NamingException{
        if(instance == null){
            InitialContext ic = new InitialContext();
            instance = new WebConfiguration();
            File[] roots = File.listRoots();
            String userDir = new File(roots[0].getAbsolutePath()).getAbsolutePath();
            String rootDir = userDir.substring(0, userDir.indexOf(File.separator) + 1);
            String appName = (String) ic.lookup("java:app/AppName");
            String moduleName = (String) ic.lookup("java:module/ModuleName");
            File fConfigPath = new File(rootDir + File.separatorChar + appName);
            File fConfig = new File(rootDir + File.separatorChar + appName + File.separatorChar + "config.properties");
            configFilePath = fConfig.getAbsolutePath();
            config =  new Configuration(fConfig.getAbsolutePath());
            
            config.load();
            Properties properties = config.getProp();
            if(properties.getProperty("installationLock") == null){
                properties.setProperty("installationLock", "false");
            }
            config.setProp(properties);
            config.save();
        }
        return instance;
    }

    public String getConfigFilePath() {
        return configFilePath;
    }
    
    public String getProperty(String property){
        config.load();
        prop = config.getProp();
        return prop.getProperty(property);
    }
    
    public void setProperty(String key, String value){
        prop.setProperty(key, value);
    }
    
    public void save(){
        config.setProp(prop);
        config.save();
    }

}
