/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.SignaturaTopografica;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author rober
 * @author Modificación Lina David
 */
@Named(value = "documentoInfoViewBean")
@ViewScoped
public class DocumentoInfoViewBean extends BaseBean implements Serializable {

    private Documento documento;

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    /**
     * Creates a new instance of DocumentoInfoViewBean
     */
    public DocumentoInfoViewBean() {
    }

    public void loadDocumento(Documento doc) {
        this.documento = doc;
        RequestContext.getCurrentInstance().execute("PF('denInfoDoc').show()");
    }
    
    public String getSingaturaTopografica(){
        if(this.documento.getSignaturaTopografica() !=  null){
        return processSignaturaTopgrafica(this.documento.getSignaturaTopografica());
        } else {
            return "";
        }
    }
    
    private String processSignaturaTopgrafica(SignaturaTopografica signatura){
        String result = "";
        if ( signatura.getDependeDe() != null){
            result = " >> " + processSignaturaTopgrafica(signatura.getDependeDe());
        }
        return signatura.getNombre() + result;
    }

}
