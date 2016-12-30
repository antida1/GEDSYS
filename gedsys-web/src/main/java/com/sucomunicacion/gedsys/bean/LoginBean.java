/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucomunicacion.gedsys.bean;

import com.sucomunicacion.gedsys.entities.Usuario;
import com.sucomunicacion.gedsys.model.UsuarioJpaController;
import com.sucomunicacion.gedsys.security.Authentication;
import com.sucomunicacion.gedsys.utils.JpaUtils;
import com.sucomunicacion.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author rober
 */
@ManagedBean(name = "login")
@SessionScoped
public class LoginBean implements Serializable {
    
    private static final long serialVersionUID = 1094801825228386363L;
    private String username;
    private String clave;
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    private boolean logeado;
    
    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    public void iniciarSesion() {
       
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory();
        UsuarioJpaController uJpa = new UsuarioJpaController(emf);
        String password = Authentication.md5(this.clave);
        usuario = uJpa.autheticate(this.username, password);
        if( usuario != null ){
            logeado = true;
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("usuario", usuario);
            msg = new FacesMessage("Inicio de sesion completado", "Usted esta logeado");
        } 
        else {
            logeado = false;
            msg= new FacesMessage( FacesMessage.SEVERITY_WARN, "Acceso denegado", "Credeciales no válidas  - Use admin y admin");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("isLogin", logeado);
        if(logeado){
            context.addCallbackParam("view", "faces/index.xhtml");
        }

    }
    
    public void cerrarSession(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        session.invalidate();
        logeado = false;
    }
}
