/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.security;

import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.utils.JpaUtils;
import java.security.MessageDigest;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Robert Alexis Mejia <rmejia@base16.co>
 */
public class Authentication {
    public static final String md5(final String toEncryp){
        try {
            final MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(toEncryp.getBytes());
            final byte[] bytes = digest.digest();
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(String.format("%02X", bytes[i]));
            }
            return sb.toString().toLowerCase();
        } catch (Exception e) {
            return "";
        }
    }
}
