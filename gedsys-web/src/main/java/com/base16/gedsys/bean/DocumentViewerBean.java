/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.web.utils.SessionUtils;
import com.base16.utils.CryptoUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rober
 */
@ManagedBean
@RequestScoped
public class DocumentViewerBean extends BaseBean implements Serializable {

    private StreamedContent content;
    private String filePath = "";
    
    @PostConstruct
    public void init() {

    }
    public void showDocument(Documento doc){
        this.filePath = this.getDocumenstSavePath() + File.separatorChar + doc.getNombreDocumento();
        SessionUtils.getSession().setAttribute("filePath", this.filePath);
        RequestContext.getCurrentInstance().execute("PF('denVisor').show()");
    }
    
    private void loadDocument(){
        try {
            this.filePath =  SessionUtils.getSession().getAttribute("filePath").toString();
            if (!filePath.isEmpty()) {
                File tempFile = new File(filePath);
                if (tempFile.exists()) {
                    if (this.getEncriptFiles() == true) {
                        File outputFile = new File(this.documenstSavePath + File.separatorChar + this.getCurrentUser().getNombres());
                        CryptoUtils.decrypt("Mary has one cat", tempFile, outputFile);
                        this.content = new DefaultStreamedContent(new FileInputStream(outputFile), new MimetypesFileTypeMap().getContentType(outputFile));
                    } else {
                        this.content = new DefaultStreamedContent(new FileInputStream(tempFile), new MimetypesFileTypeMap().getContentType(tempFile));
                    }
                }
            }
        } catch (Exception e) {
            //this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido una excepcion al obtener el documento", e.getMessage()));
        }
    }

    public StreamedContent getContent() {
        this.loadDocument();
        return content;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
