/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.utils;

import com.base16.gedsys.config.Configuration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.eclipse.persistence.config.EntityManagerProperties;
import org.eclipse.persistence.config.PersistenceUnitProperties;

/**
 *
 * @author Robert Mejia
 */
public class JpaUtils {

    private static EntityManagerFactory emf;
    private static Map properties;

    public static EntityManagerFactory getEntityManagerFactory(String path) {
        if (emf == null) {
            try {
                //TODO: Cambiar la configuracion para que haga la lectura del web.xml del proyecto
                Configuration config = new Configuration(path);
                Properties prop = config.getProp();

                properties = new HashMap();
                properties.put(EntityManagerProperties.JDBC_DRIVER, prop.getProperty("jdbc_driver"));
                properties.put(EntityManagerProperties.JDBC_URL, prop.getProperty("jdbc_url"));
                properties.put(EntityManagerProperties.JDBC_USER, prop.getProperty("jdbc_user"));
                properties.put(EntityManagerProperties.JDBC_PASSWORD, prop.getProperty("jdbc_password"));
                emf = Persistence.createEntityManagerFactory("com.sucomunicacion_gedsys-core_jar_1.0-SNAPSHOTPU", properties);
            } catch (Throwable t) {
                 throw new ExceptionInInitializerError();
            }
        }
        return emf;
    }

    public static void createSchema(String path) {
        try {
            //TODO: Cambiar la configuracion para que haga la lectura del web.xml del proyecto
            Configuration config = new Configuration(path);
            Properties prop = config.getProp();

            properties = new HashMap();
            properties.put(EntityManagerProperties.JDBC_DRIVER, prop.getProperty("jdbc_driver"));
            properties.put(EntityManagerProperties.JDBC_URL, prop.getProperty("jdbc_url"));
            properties.put(EntityManagerProperties.JDBC_USER, prop.getProperty("jdbc_user"));
            properties.put(EntityManagerProperties.JDBC_PASSWORD, prop.getProperty("jdbc_password"));
            properties.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.DROP_AND_CREATE);
            properties.put(PersistenceUnitProperties.DDL_GENERATION_MODE, PersistenceUnitProperties.DDL_BOTH_GENERATION);
            EntityManager em = Persistence.createEntityManagerFactory("com.sucomunicacion_gedsys-core_jar_1.0-SNAPSHOTPU", properties).createEntityManager();
            em.close();
            //em.getEntityManagerFactory().close();
        } catch (Throwable t) {
            throw new ExceptionInInitializerError();
        }
    }

}
