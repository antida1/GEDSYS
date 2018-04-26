/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Documento;
import com.base16.gedsys.entities.PlanillaEnvio;
import com.base16.gedsys.entities.PlanillaEnvioDocumento;
import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.DocumentoJpaController;
import com.base16.gedsys.model.EntidadJpaController;
import com.base16.gedsys.model.PlanillaEnvioDocumentoJpaController;
import com.base16.gedsys.model.PlanillaEnvioJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.list.AbstractLinkedList;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.context.RequestContext;

/**
 *
 * @author rober
 * @author Modificación Lina David
 */
@ManagedBean
@ViewScoped
public class PlanillaEnvioBean extends BaseBean implements Serializable {

    private List<Documento> documentosSeleccionados;
    private List<Documento> documentos;

    private PlanillaEnvio planillaEnvio;
    private List<PlanillaEnvio> planillasEnvios;
    private String accion;

    public List<Documento> getDocumentosSeleccionados() {
        return documentosSeleccionados;
    }

    public void setDocumentosSeleccionados(List<Documento> documentosSeleccionados) {
        this.documentosSeleccionados = documentosSeleccionados;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public PlanillaEnvio getPlanillaEnvio() {
        return planillaEnvio;
    }

    public void setPlanillaEnvio(PlanillaEnvio planillaEnvio) {
        this.planillaEnvio = planillaEnvio;
    }

    public List<PlanillaEnvio> getPlanillasEnvios() {
        return planillasEnvios;
    }

    public void setPlanillasEnvios(List<PlanillaEnvio> planillasEnvios) {
        this.planillasEnvios = planillasEnvios;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }

    public void procesar() {
        try {
            switch (accion) {
                case "Crear":
                    if (documentosSeleccionados == null) {
                        this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Planilla de Envio", "Seleccione los documentos a adjuntar en la planilla"));
                        break;
                    }
                    crear();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Planilla de Envio", "Planilla de Envio Creada"));
                    break;
                case "Modificar":
                    modificar();
                    this.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Planilla de Envio", "Planilla de Envio Modificada"));
                    break;
            }
            RequestContext.getCurrentInstance().execute("PF('planillaEnvioDialog').hide()");
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Planilla de Envio", e.getMessage()));
        }
    }

    private void crear() throws Exception {
        PlanillaEnvioJpaController pJpa;
        PlanillaEnvioDocumentoJpaController peJpa;
        DocumentoJpaController dJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            pJpa = new PlanillaEnvioJpaController(emf);
            peJpa = new PlanillaEnvioDocumentoJpaController(emf);
            dJpa = new DocumentoJpaController(emf);
            Usuario usuario = (Usuario) SessionUtils.getUsuario();

            this.planillaEnvio.setFechaCreacion(new Date());
            this.planillaEnvio.setFechaModificacion(new Date());
            this.planillaEnvio.setCreadoPor(usuario);
            this.planillaEnvio.setModificadoPor(usuario);
            List<PlanillaEnvioDocumento> planillaEnvioDocumentoList = new ArrayList<>();
            for (Documento documentosSeleccionado : documentosSeleccionados) {
                PlanillaEnvioDocumento ped = new PlanillaEnvioDocumento();
                ped.setDocumento(documentosSeleccionado);
                ped.setEstado(1);
                ped.setFechaCreacion(new Date());
                peJpa.create(ped);
                documentosSeleccionado.setEstado(9); // Cambia el estado a por archivar
                dJpa.edit(documentosSeleccionado);
                planillaEnvioDocumentoList.add(ped);
            }
            this.planillaEnvio.setPlanillaEnvioDocumentoList(planillaEnvioDocumentoList);
            pJpa.create(planillaEnvio);
            this.documentosSeleccionados =  null;
            this.listar();
        } catch (Exception e) {
            Logger.getLogger(EntidadBean.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
    }

    private void modificar() throws Exception {
        PlanillaEnvioJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PlanillaEnvioJpaController(emf);
            this.planillaEnvio.setFechaModificacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            this.planillaEnvio.setModificadoPor(usuario);
            sJpa.edit(planillaEnvio);
            this.listar();
        } catch (Exception e) {
            Logger.getLogger(EntidadBean.class.getName()).log(Level.SEVERE, null, e);
            throw e;
        }
    }

    private void eliminar(PlanillaEnvio planillaEnvio) {

    }

    public void listar() {
        PlanillaEnvioJpaController sJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            sJpa = new PlanillaEnvioJpaController(emf);
            planillasEnvios = sJpa.findPlanillaEnvioEntities();
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Entidad", e.getMessage()));
            Logger.getLogger(EntidadBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void getPlanillaEnvioById(PlanillaEnvio planillaEnvio) {
        PlanillaEnvioJpaController pJpa;
        PlanillaEnvio planillaTemp;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            pJpa = new PlanillaEnvioJpaController(emf);
            planillaTemp = pJpa.findPlanillaEnvio(planillaEnvio.getId());
            if (planillaTemp != null) {
                this.planillaEnvio = planillaTemp;
                this.accion = "Modificar";
            }
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Entidad", e.getMessage()));
            Logger.getLogger(EntidadBean.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    private void limpiar() {
        this.planillaEnvio = new PlanillaEnvio();
    }

    public void generarPlanilla(PlanillaEnvio planilla) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();

        HSSFRow row;

        row = sheet.createRow((short) 0);
        row.createCell((short) 0).setCellValue("Radicado");
        row.createCell((short) 1).setCellValue("Fecha del Documento");
        row.createCell((short) 2).setCellValue("Asunto");
        row.createCell((short) 3).setCellValue("Destinarario");
        row.createCell((short) 4).setCellValue("Remitente");
        //row.createCell((short) 5).setCellValue("Detalle");
        row.createCell((short) 5).setCellValue("Transportador");
        row.createCell((short) 6).setCellValue("Medio de Envio");
        row.createCell((short) 7).setCellValue("Firma");
        short i = 1;
        for (PlanillaEnvioDocumento planillaEnvioDocumento : planilla.getPlanillaEnvioDocumentoList()) {
            row = sheet.createRow((short) i);
            row.createCell((short) 0).setCellValue(planillaEnvioDocumento.getDocumento().getRadicadoEnvio());
            row.createCell((short) 1).setCellValue(planillaEnvioDocumento.getDocumento().getFechaDocumento());
            row.createCell((short) 2).setCellValue(planillaEnvioDocumento.getDocumento().getAsunto());
            row.createCell((short) 3).setCellValue(planillaEnvioDocumento.getDocumento().getRemitenteExteno());
            row.createCell((short) 4).setCellValue(planillaEnvioDocumento.getDocumento().getDestinatario().getNombres());
            //row.createCell((short) 5).setCellValue(planillaEnvioDocumento.getDocumento().getDetalle());
            row.createCell((short) 5).setCellValue(planillaEnvioDocumento.getDocumento().getTransportador().getNombre());
            row.createCell((short) 6).setCellValue(planillaEnvioDocumento.getDocumento().getMedioEnvio().getNombre());
            row.createCell((short) 7).setCellValue("");
            i++;
        }
       
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=test.xls");

        try {
            ServletOutputStream out = response.getOutputStream();

            wb.write(out);
            out.flush();
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            
        }

        FacesContext faces = FacesContext.getCurrentInstance();
        faces.responseComplete();
    }

}
