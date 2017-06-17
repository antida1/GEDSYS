/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.system;

import com.sucomunicacion.gedsys.bean.BaseBean;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author rober
 */
@Named
@RequestScoped
public class AutorizationBean extends BaseBean {

    /**
     * Creates a new instance of AutorizationBean
     */
    public AutorizationBean() {
    }
    
    public boolean hasAccess(String moduleName){
       if(isAdmin()){
           return true;
       } else {
          return false;
       }
    }
    
    public boolean canWrite(String moduleName){
        if(isAdmin()){
           return true;
       } else {
          return false; 
       }
    }
    
    public boolean canDelete(String moduleName){
        if(isAdmin()){
           return true;
       } else {
          return false; 
       }
    }
    
    public boolean canExport(String moduleName){
        if(isAdmin()){
           return true;
       } else {
          return false; 
       }
    }
    
    public boolean canGeneratePdf(String moduleName){
        if(isAdmin()){
           return true;
       } else {
          return false; 
       }
    }
    
    public boolean isAdmin(){
        return this.getCurrentUser().getIsAdmin();
    }
}
