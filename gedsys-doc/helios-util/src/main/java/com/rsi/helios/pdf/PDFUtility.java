/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsi.helios.pdf;

import java.io.File;
import java.util.ArrayList;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author robert
 */
public class PDFUtility {

    private PDFMergerUtility PDFMerger = new PDFMergerUtility();
    private ArrayList<File> files = new ArrayList<>();

    private void Merge(String filePath) {
        try {
            PDDocument document = PDDocument.load(files.get(0));
            for (File file : files) {
                PDFMerger.addSource(file);
            }
            PDFMerger.setDestinationFileName(filePath);
            PDFMerger.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
        } catch (Exception e) {
            
        }

    }

    public void Merge(ArrayList<File> files, String destinationDocumentPath) {
        this.files = files;
        this.Merge(destinationDocumentPath);
    }

}
