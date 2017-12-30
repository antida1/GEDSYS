/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Notificacion;
import java.util.Date;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;

/**
 *
 * @author rober
 */
public class ScheduleBeanEvent extends DefaultScheduleEvent {
    private Notificacion notificacion;

    public Notificacion getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(Notificacion notificacion) {
        this.notificacion = notificacion;
    }
    
    public ScheduleBeanEvent(){
        this.notificacion = new Notificacion();
    }
    public ScheduleBeanEvent( String descripcion, Date startDate, Date endDate, Notificacion notificacion){
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setTitle(descripcion);
        this.notificacion = notificacion;
    }
    
    
}
