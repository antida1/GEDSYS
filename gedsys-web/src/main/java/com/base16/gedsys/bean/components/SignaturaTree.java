/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean.components;

import com.base16.gedsys.bean.BaseBean;
import com.base16.gedsys.bean.SignaturaTopograficaBean;
import com.base16.gedsys.entities.SignaturaTopografica;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.entities.Usuariosignaturas;
import com.base16.gedsys.model.UsuariosignaturasJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManagerFactory;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author ASUS PC
 */
@ManagedBean
@ViewScoped
public class SignaturaTree extends BaseBean implements Serializable {

    /**
     * Creates a new instance of SignaturaTree
     */
    public SignaturaTree() {
        loadSignaturas();
    }

    private TreeNode root;

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

    public void loadRootByCurrentUserAccess() {
        loadSignaturas();
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        List<Usuariosignaturas> usuariosSignaturas = null;
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        UsuariosignaturasJpaController usJpa = new UsuariosignaturasJpaController(emf);
        usuariosSignaturas = usJpa.findUsuariosignaturasEntitiesByUsuario(usuario);

        for (TreeNode treeNode : root.getChildren()) {
            processTreenode(treeNode, usuariosSignaturas);
            treeNode.setSelectable(false);
        }
    }

    private void processTreenode(TreeNode node, List<Usuariosignaturas> usuariosSignaturas) {
        for (TreeNode treeNode : node.getChildren()) {
            for (Usuariosignaturas usuariosSignatura : usuariosSignaturas) {
                if (treeNode.toString().equals(usuariosSignatura.getSignatura().getNombre())) {
                    treeNode.setSelectable(true);
                    //treeNode.setExpanded(true);
                } else {
                    treeNode.setSelectable(false);
                    //treeNode.setExpanded(true);
                    if (node.isLeaf()) {
                        TreeNode parent = treeNode.getParent();
                        parent.getChildren().remove(treeNode);
                    }
                }
            }
            processTreenode(treeNode, usuariosSignaturas);
        }
    }

    public void loadCheckedByCurrentUser(Usuario usuario) {
        loadSignaturas();
        List<Usuariosignaturas> usuariosSignaturas = null;
        EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
        UsuariosignaturasJpaController usJpa = new UsuariosignaturasJpaController(emf);
        usuariosSignaturas = usJpa.findUsuariosignaturasEntitiesByUsuario(usuario);

        for (TreeNode treeNode : root.getChildren()) {
            processTreenodeCheked(treeNode, usuariosSignaturas);
        }
    }

    private void processTreenodeCheked(TreeNode node, List<Usuariosignaturas> usuariosSignaturas) {
        for (TreeNode treeNode : node.getChildren()) {
            for (Usuariosignaturas usuariosSignatura : usuariosSignaturas) {
                if (treeNode.toString().equals(usuariosSignatura.getSignatura().getNombre())) {
                    treeNode.setSelected(true);
                }
                treeNode.setExpanded(true);
            }
            processTreenodeCheked(treeNode, usuariosSignaturas);
        }
    }

}
