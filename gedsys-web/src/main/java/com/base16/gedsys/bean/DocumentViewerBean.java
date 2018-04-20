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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rober
 * @author Modificación Lina David
 */
@ManagedBean
@RequestScoped
public class DocumentViewerBean extends BaseBean implements Serializable {

    private StreamedContent content;
    private String filePath = "";
    private String filePathGuia = "";
    private String filePathComprobante = "";

    
    @PostConstruct
    public void init() {

    } 

    public void showDocument(Documento doc) {
        SessionUtils.getSession().setAttribute("filePathComprobante", "");
        SessionUtils.getSession().setAttribute("filePathGuia", "");

        this.filePath = this.getDocumenstSavePath() + File.separatorChar + doc.getNombreDocumento();
        SessionUtils.getSession().setAttribute("filePath", this.filePath);
        RequestContext.getCurrentInstance().execute("PF('denVisor').show()");
        if (doc.getComprobante() != null) {
            SessionUtils.getSession().setAttribute("filePathComprobante", this.getDocumenstSavePath() + File.separatorChar + doc.getComprobante());
        }
        if (doc.getGuia() != null) {
            SessionUtils.getSession().setAttribute("filePathGuia", this.getDocumenstSavePath() + File.separatorChar + doc.getGuia());
        }
    }

    private void loadDocument() {
        try {
            this.filePath = SessionUtils.getSession().getAttribute("filePath").toString();
            this.filePathComprobante = SessionUtils.getSession().getAttribute("filePathComprobante").toString();
            this.filePathGuia = SessionUtils.getSession().getAttribute("filePathGuia").toString();

            if (!filePath.isEmpty()) {
                File tempFile = new File(filePath);
                if (tempFile.exists()) {
                    if (this.getEncriptFiles() == true) {
                        File outputFile = new File(this.documenstSavePath + File.separatorChar + this.getCurrentUser().getNombres());
                        CryptoUtils.decrypt("Mary has one cat", tempFile, outputFile);
                        PDFMergerUtility PDFMerger = new PDFMergerUtility();
                        PDFMerger.addSource(outputFile);
                        if (!this.filePathGuia.isEmpty()) {
                            File tempFileG = new File(filePathGuia);
                            File outputFileG = new File(this.documenstSavePath + File.separatorChar + this.getCurrentUser().getNombres()+ "G");
                            CryptoUtils.decrypt("Mary has one cat", tempFileG, outputFileG);
                            PDFMerger.addSource(outputFileG);

                        }
                        if (!this.filePathComprobante.isEmpty()) {
                            File tempFileC = new File(filePathComprobante);
                            File outputFileC = new File(this.documenstSavePath + File.separatorChar + this.getCurrentUser().getNombres() + "C");
                            CryptoUtils.decrypt("Mary has one cat", tempFileC, outputFileC);
                            PDFMerger.addSource(outputFileC);
                        }
                        PDFMerger.setDestinationFileName(this.documenstSavePath + File.separatorChar + this.getCurrentUser().getNombres());
                        PDFMerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
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
