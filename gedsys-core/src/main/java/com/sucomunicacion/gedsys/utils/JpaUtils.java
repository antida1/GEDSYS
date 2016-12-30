/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Robert Mejia
 */
public class JpaUtils {
    
    private static final EntityManagerFactory emf;
    
    static{
        try {
            emf = Persistence.createEntityManagerFactory("com.sucomunicacion_gedsys-core_jar_1.0-SNAPSHOTPU");
        } catch (Throwable t) {
            throw new ExceptionInInitializerError();
        }
    }
    
    public static EntityManagerFactory getEntityManagerFactory(){
        return emf;
    }
    
}
