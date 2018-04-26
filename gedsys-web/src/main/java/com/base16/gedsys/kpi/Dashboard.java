/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.kpi;

import com.base16.gedsys.bean.BaseBean;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author rober
 */

@ManagedBean
@ViewScoped
public class Dashboard extends BaseBean implements Serializable {

    /**
     * Creates a new instance of Dashboard
     */
    private int totalDocumentos = 0;
    private double porcentUltimaSemana = 0;

    private int tiempoPromRespuesta = 0;
    private double porcentUltimoMes = 0;

    private int documentosExternos = 0;
    private double porcentDEUltimoMes = 0;

    private int documentosInternos = 0;
    private double porcenDItUltimoMes = 0;

    private int documentosRadicado = 0;
    private double porcentDRUltimoMes = 0;

    private int documentosGestionado = 0;
    private double porcentDGUltimoMes = 0;

    public Dashboard() {
   
    }

    public int getTotalDocumentos() {
        return totalDocumentos;
    }

    public void setTotalDocumentos(int totalDocumentos) {
        this.totalDocumentos = totalDocumentos;
    }

    public double getPorcentUltimaSemana() {
        return porcentUltimaSemana;
    }

    public void setPorcentUltimaSemana(double porcentUltimaSemana) {
        this.porcentUltimaSemana = porcentUltimaSemana;
    }

    public int getTiempoPromRespuesta() {
        return tiempoPromRespuesta;
    }

    public void setTiempoPromRespuesta(int tiempoPromRespuesta) {
        this.tiempoPromRespuesta = tiempoPromRespuesta;
    }

    public double getPorcentUltimoMes() {
        return porcentUltimoMes;
    }

    public void setPorcentUltimoMes(double porcentUltimoMes) {
        this.porcentUltimoMes = porcentUltimoMes;
    }

    public int getDocumentosExternos() {
        return documentosExternos;
    }

    public void setDocumentosExternos(int documentosExternos) {
        this.documentosExternos = documentosExternos;
    }

    public double getPorcentDEUltimoMes() {
        return porcentDEUltimoMes;
    }

    public void setPorcentDEUltimoMes(double porcentDEUltimoMes) {
        this.porcentDEUltimoMes = porcentDEUltimoMes;
    }

    public int getDocumentosInternos() {
        return documentosInternos;
    }

    public void setDocumentosInternos(int documentosInternos) {
        this.documentosInternos = documentosInternos;
    }

    public double getPorcenDItUltimoMes() {
        return porcenDItUltimoMes;
    }

    public void setPorcenDItUltimoMes(double porcenDItUltimoMes) {
        this.porcenDItUltimoMes = porcenDItUltimoMes;
    }

    public int getDocumentosRadicado() {
        return documentosRadicado;
    }

    public void setDocumentosRadicado(int documentosRadicado) {
        this.documentosRadicado = documentosRadicado;
    }

    public double getPorcentDRUltimoMes() {
        return porcentDRUltimoMes;
    }

    public void setPorcentDRUltimoMes(double porcentDRUltimoMes) {
        this.porcentDRUltimoMes = porcentDRUltimoMes;
    }

    public int getDocumentosGestionado() {
        return documentosGestionado;
    }

    public void setDocumentosGestionado(int documentosGestionado) {
        this.documentosGestionado = documentosGestionado;
    }

    public double getPorcentDGUltimoMes() {
        return porcentDGUltimoMes;
    }

    public void setPorcentDGUltimoMes(double porcentDGUltimoMes) {
        this.porcentDGUltimoMes = porcentDGUltimoMes;
    }

}
