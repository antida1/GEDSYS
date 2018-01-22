/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.system;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.entities.Acl;
import com.base16.gedsys.entities.GrupoUsuario;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.Modulo;
import com.base16.gedsys.web.utils.SessionUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.lang.reflect.Field;
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

    public boolean hasAccess(String moduleName) {
        if (isAdmin()) {
            return true;
        } else {
            return verifyAcl(this.getCurrentUser(), moduleName, "getCanRead");
        }
    }
    
    public boolean canCreate(String moduleName) {
        if (isAdmin()) {
            return true;
        } else {
             return verifyAcl(this.getCurrentUser(), moduleName, "getCanCreate");
        }
    }

    public boolean canUpdate(String moduleName) {
        if (isAdmin()) {
            return true;
        } else {
             return verifyAcl(this.getCurrentUser(), moduleName, "getCanUpdate");
        }
    }

    public boolean canDelete(String moduleName) {
        if (isAdmin()) {
            return true;
        } else {
            return verifyAcl(this.getCurrentUser(), moduleName, "getCanDelete");
        }
    }

    public boolean canExport(String moduleName) {
        if (isAdmin()) {
            return true;
        } else {
             return verifyAcl(this.getCurrentUser(), moduleName, "getCanExport");
        }
    }

    public boolean canGeneratePdf(String moduleName) {
        if (isAdmin()) {
            return true;
        } else {
            return verifyAcl(this.getCurrentUser(), moduleName, "getCanGeneratePDF");
        }
    }

    private boolean isAdmin() {
        return this.getCurrentUser().getIsAdmin();
    }

    private boolean verifyAcl(Usuario usuario, String moduloName, String stringMethod) {
        Boolean result = false;
        try {
            AclBean aclBean = new AclBean();
            Acl acl = new Acl();
            Collection<GrupoUsuario> grupoUsuarioCollection = usuario.getGrupoUsuarioCollection2();
            for (GrupoUsuario grupoUsuario : grupoUsuarioCollection) {
                ModuloBean moduloBean = new ModuloBean();
                Modulo modulo = moduloBean.getModuloByNombre(moduloName);
                acl = aclBean.getAclByModuloGrupo(modulo, grupoUsuario.getGrupo());
                if (acl != null) {
                    break;
                }
            }
            
             for (Method method : Acl.class.getMethods()){
                 if(method.getName().equals(stringMethod)){
                     result = (Boolean) method.invoke(acl);
                     break;
                 }
                 
             }
            
            //Method method;
            //method = acl.getClass().getMethod(stringMethod, Acl.class);
            //result = (Boolean) value; //method.invoke(acl, new Object[]{});
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException ex) {
            Logger.getLogger(AutorizationBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (InvocationTargetException ex) {
            Logger.getLogger(AutorizationBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        if(result == null){
            result =false;
        }
        return result;
    }
}
