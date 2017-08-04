    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean.components;

import com.base16.gedsys.bean.SeccionSubSeccionBean;
import com.base16.gedsys.bean.SerieBean;
import com.base16.gedsys.bean.SubSerieBean;
import com.base16.gedsys.bean.TipoDocumentalBean;
import com.base16.gedsys.bean.UnidadDocumentalBean;
import com.base16.gedsys.entities.SeccionSubSeccion;
import com.base16.gedsys.entities.Serie;
import com.base16.gedsys.entities.SubSerie;
import com.base16.gedsys.entities.TipoDocumental;
import com.base16.gedsys.entities.UnidadDocumental;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author ASUS PC
 */
@ManagedBean
@ViewScoped
public class TRDTree implements Serializable {

    /**
     * Creates a new instance of SignaturaTree
     */
    public TRDTree() {
    }
    
    private TreeNode root;
    
    @PostConstruct
    public void init() {

        loadSeccionSubSeccion();
    }

    private void loadSeccionSubSeccion() {
        root = new DefaultTreeNode("Distribución", null);
        //root.setExpanded(true);
        SeccionSubSeccion secSubSeccion = new SeccionSubSeccion();
        SeccionSubSeccionBean secSubSeccionBean = new SeccionSubSeccionBean();
        secSubSeccionBean.getSeccionSubSeccionRoots();
        List<SeccionSubSeccion> seccionSubSecciones = secSubSeccionBean.getSecciones();
        
        for (SeccionSubSeccion seccionSubSeccion : seccionSubSecciones) {
            TreeNode newNode = new DefaultTreeNode(seccionSubSeccion);
            //newNode.setExpanded(true);
            newNode.setSelectable(false);
            root.getChildren().add(newNode);
            FillNodesSerie(newNode, seccionSubSeccion);
            FillRootNodesSeccionSubSeccion(newNode, seccionSubSeccion);
        }
    }

    private void FillRootNodesSeccionSubSeccion(TreeNode root, SeccionSubSeccion seccionSubSeccion) {
        SeccionSubSeccionBean seccionSubSeccionBean = new SeccionSubSeccionBean();
        seccionSubSeccionBean.getSeccionSubSeccionByDependencia(seccionSubSeccion);
        List<SeccionSubSeccion> secciones = seccionSubSeccionBean.getSecciones();

        for (SeccionSubSeccion seccion : secciones) {
            TreeNode newNode = new DefaultTreeNode(seccion);
            newNode.setRowKey(seccion.getId().toString());
            newNode.setSelectable(false);
            root.getChildren().add(newNode);
            FillNodesSerie(newNode, seccion);
            FillRootNodesSeccionSubSeccion(newNode, seccion);
        }
    }
    
    private void FillNodesSerie(TreeNode root, SeccionSubSeccion seccionSubSeccion){
        SerieBean serieBean = new SerieBean();
        serieBean.getSerieBySeccionSubSeccion(seccionSubSeccion);
        List<Serie> series = serieBean.getSeries();
        
        for(Serie serie: series){
            TreeNode newNode = new DefaultTreeNode(serie);
            newNode.setRowKey(serie.getId().toString());
            newNode.setSelectable(false);
            root.getChildren().add(newNode);
            FillNodeSubSerie(root, serie);
        }
    }
    
    private void FillNodeSubSerie(TreeNode root, Serie serie){
        SubSerieBean subSerieBean = new SubSerieBean();
        subSerieBean.getSubSerieBySerie(serie);
        List<SubSerie> subSeries =  subSerieBean.getSubSeries();
        
        for(SubSerie subSerie: subSeries) {
            TreeNode newNode = new DefaultTreeNode(subSerie);
            newNode.setRowKey(serie.getId().toString());
            newNode.setSelectable(false);
            root.getChildren().add(newNode);
            FillNodeUnidadDocumental(newNode, subSerie);
        }
    }  
    
    private void FillNodeUnidadDocumental(TreeNode root, SubSerie subSerie){
        UnidadDocumentalBean unidadDocumentalBean = new UnidadDocumentalBean();
        unidadDocumentalBean.getUnidadDocumentalBySubSerie(subSerie);
        List<UnidadDocumental> unidadesDocumentales = unidadDocumentalBean.getUnidadesDocumentales();
        
        for(UnidadDocumental unidadDocumental: unidadesDocumentales) {
            TreeNode newNode = new DefaultTreeNode(unidadDocumental);
            newNode.setRowKey(unidadDocumental.getId().toString());
            newNode.setSelectable(true);
            root.getChildren().add(newNode);
            //FillNodeTipoDocumental( newNode, unidadDocumental);
        }
    }
    
    private void FillNodeTipoDocumental(TreeNode root, UnidadDocumental unidadDocumental){
        TipoDocumentalBean tipoDocumentalBean = new TipoDocumentalBean();
        tipoDocumentalBean.getTipoDocumentalByUnidadDocumental(unidadDocumental);
        List<TipoDocumental> tiposDocumentales = tipoDocumentalBean.getTiposDocumentales();
        
        for(TipoDocumental tipoDocumental: tiposDocumentales){
            TreeNode newNode = new DefaultTreeNode(tipoDocumental);
            newNode.setSelectable(true);
            newNode.setRowKey(tipoDocumental.getId().toString());
            root.getChildren().add(newNode);
        }
    }

    public TreeNode getRoot() {
        return root;
    }
    
}
