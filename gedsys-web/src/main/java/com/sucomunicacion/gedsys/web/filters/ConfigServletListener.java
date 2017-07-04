/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.web.filters;

import java.io.File;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author rober
 */
@WebListener
public class ConfigServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.Initialize();
    }

    private void Initialize() {

        File[] roots = File.listRoots();

        String userDir = new File(roots[0].getAbsolutePath()).getAbsolutePath();
        String rootDir = userDir.substring(0, userDir.indexOf(File.separator) + 1);

        File fConfigPath = new File(rootDir + File.separatorChar + "gedsys");
        File fConfig = new File(rootDir + File.separatorChar + "gedsys" + File.separatorChar + "config.properties");

        if (fConfigPath.exists() && fConfigPath.isDirectory()) {
            if (!fConfig.exists()) {
                createConfigFile(fConfig.getAbsolutePath());
            }
        } else {
            if (createConfigDir(fConfigPath.getAbsolutePath())) {
                createConfigFile(fConfig.getAbsolutePath());
            }
        }
    }

    private boolean createConfigFile(String rootDir) {
        boolean result = false;
        try {
            result = new File(rootDir).createNewFile();
        } catch (Exception e) {

        }
        return result;
    }

    private boolean createConfigDir(String rootDir) {
        return new File(rootDir).mkdir();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
