/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author rober
 */
@ManagedBean
@ViewScoped
public class ProduccionDoccumentalBean {
    
    private int selectedItem;
    /**
     * Creates a new instance of ProduccionDoccumentalBean
     */
    public ProduccionDoccumentalBean() {
    }

    public int getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    
    
}
