/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean.components;

import com.base16.gedsys.bean.SignaturaTopograficaBean;
import com.base16.gedsys.entities.SignaturaTopografica;
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
public class SignaturaTree implements Serializable {

    /**
     * Creates a new instance of SignaturaTree
     */
    public SignaturaTree() {
    }
    
    private TreeNode root;
    
    @PostConstruct
    public void init() {

        loadSignaturas();
    }

    private void loadSignaturas() {
        root = new DefaultTreeNode("Ciclo Vital", null);
        root.setExpanded(true);
        SignaturaTopografica sig = new SignaturaTopografica();
        SignaturaTopograficaBean signaturaBean = new SignaturaTopograficaBean();
        signaturaBean.getSignaturaTopograficaRoots();
        List<SignaturaTopografica> signaturas = signaturaBean.getSignaturasTopograficas();

        for (SignaturaTopografica signatura : signaturas) {
            TreeNode newNode = new DefaultTreeNode(signatura);
            newNode.setExpanded(true);
            root.getChildren().add(newNode);
            FillRootNodes(newNode, signatura);
        }
    }

    private void FillRootNodes(TreeNode root, SignaturaTopografica sig) {
        SignaturaTopograficaBean signaturaBean = new SignaturaTopograficaBean();
        signaturaBean.getSignaturaTopograficaByDependencia(sig);
        List<SignaturaTopografica> signaturas = signaturaBean.getSignaturasTopograficas();

        for (SignaturaTopografica signatura : signaturas) {
            TreeNode newNode = new DefaultTreeNode(signatura);
            newNode.setRowKey(signatura.getId().toString());
                root.getChildren().add(newNode);
            FillRootNodes(newNode, signatura);
        }
    }

    public TreeNode getRoot() {
        return root;
    }

   

}
