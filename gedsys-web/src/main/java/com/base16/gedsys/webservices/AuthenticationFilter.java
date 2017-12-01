/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.webservices;

import com.sun.jersey.core.util.Base64;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.apache.log4j.varia.Roller;

/**
 *
 * @author rober
 */
@Provider
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter {
    
    @Context
    private ResourceInfo resourceInfo;
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED).entity("No puedes acceder a este recurso").build();
    private static final Response ACCESS_FORBIDEN = Response.status(Response.Status.FORBIDDEN).entity("Acceso bloqueado para todos los usuarios").build();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
       Method method = resourceInfo.getResourceMethod();
       if(!method.isAnnotationPresent(PermitAll.class)){
           
           if(method.isAnnotationPresent(DenyAll.class)){
               requestContext.abortWith(ACCESS_FORBIDEN);
               return;
           }
           
           //Get Request Headers
           final MultivaluedMap<String, String> headers = requestContext.getHeaders();
           
           //fetch authorization header
           final List<String> authorization = headers.get("AUTHORIZATION_PROPERTY");
           
           //If no authorization information present block access
           if(authorization == null || authorization.isEmpty()){
               //requestContext.abortWith(ACCESS_DENIED);
               //return;
           }
           
//           final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME +  " ", "");
//           final String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));
//           
//           final StringTokenizer tokenizer =  new StringTokenizer(usernameAndPassword, ":");
//           final String username = tokenizer.nextToken();
//           final String password = tokenizer.nextToken();
//           
//           System.out.println(username);
//           System.out.println(password);
//           
//           if(method.isAnnotationPresent(RolesAllowed.class)){
//               RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
//               Set<String> rolesSet =  new  HashSet<String>(Arrays.asList(rolesAnnotation.value()));
//               
//               if(!isUserAllowed(username, password, rolesSet)){
//                   //requestContext.abortWith(ACCESS_DENIED);
//                   //return;
//               }
//           }
           
       }
    }
    
    private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet){
        boolean isAllowed = true;
        
        if(username.equals("") && password.equals("")){
            String userRole = "ADMIN";
            if(rolesSet.contains(userRole)){
                isAllowed = true;
            }
        }
        return isAllowed;
    }
}
