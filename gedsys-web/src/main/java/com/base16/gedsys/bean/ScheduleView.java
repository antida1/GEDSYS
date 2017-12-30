/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean;

import com.base16.gedsys.entities.Usuario;
import com.base16.gedsys.model.NotificacionJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.SessionUtils;
import com.base16.gedsys.entities.Notificacion;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManagerFactory;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean
@ViewScoped
public class ScheduleView extends BaseBean implements Serializable {

    private ScheduleModel eventModel;

    private ScheduleModel lazyEventModel;

    private ScheduleBeanEvent event = new ScheduleBeanEvent();
    private List<Notificacion> notificaciones;

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        Usuario usuario = (Usuario) SessionUtils.getUsuario();
        NotificacionJpaController nJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            nJpa = new NotificacionJpaController(emf);
            notificaciones = nJpa.findNotificacionByResponsable(usuario);
        } catch (Exception e) {
            this.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Agenda", e.getMessage()));
        }
        for (Notificacion notificacion : notificaciones) {
            eventModel.addEvent(new ScheduleBeanEvent(notificacion.getAsunto(), notificacion.getFechaInicio(), notificacion.getFechaFinalizacion(), notificacion));
        }
    }

    private void listar() {

    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);    //set random day of month

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    private Date previousDay8Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 8);
        return t.getTime();
    }

    private Date previousDay11Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date today1Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 1);

        return t.getTime();
    }

    private Date theDayAfter3Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    private Date today6Pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.HOUR, 6);

        return t.getTime();
    }

    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);

        return t.getTime();
    }

    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    public ScheduleBeanEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleBeanEvent event) {
        this.event = event;
    }

    public void addEvent(ActionEvent actionEvent) {
        if (event.getId() == null) {
            eventModel.addEvent(event);
            createEvent();
        } else {
            eventModel.updateEvent(event);
            updateEvent();
        }
        event = new ScheduleBeanEvent();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleBeanEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new ScheduleBeanEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject(), (Notificacion) new Notificacion());
    }

    public Boolean delete() {
        deleteEvent();
        return eventModel.deleteEvent(event);
    }

    private void createEvent() {
        NotificacionJpaController nJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            nJpa = new NotificacionJpaController(emf);
            event.getNotificacion().setFechaCreacion(new Date());
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            event.getNotificacion().setAsunto(event.getTitle());
            event.getNotificacion().setCreadorPor(usuario.getId());
            event.getNotificacion().setFechaInicio(event.getStartDate());
            event.getNotificacion().setFechaFinalizacion(event.getEndDate());
            nJpa.create(event.getNotificacion());

        } catch (Exception e) {
            Logger.getLogger(CargoBean.class
                    .getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    private void updateEvent() {
        NotificacionJpaController nJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            nJpa = new NotificacionJpaController(emf);
            Usuario usuario = (Usuario) SessionUtils.getUsuario();
            event.getNotificacion().setAsunto(event.getTitle());
            event.getNotificacion().setFechaInicio(event.getStartDate());
            event.getNotificacion().setFechaFinalizacion(event.getEndDate());
            event.getNotificacion().setModificadoPor(usuario.getId());
            nJpa.edit(event.getNotificacion());

        } catch (Exception e) {
            Logger.getLogger(CargoBean.class
                    .getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    private void deleteEvent() {
        NotificacionJpaController nJpa;
        try {
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(this.getConfigFilePath());
            nJpa = new NotificacionJpaController(emf);
            nJpa.destroy(event.getNotificacion().getId());

        } catch (Exception e) {
            Logger.getLogger(CargoBean.class
                    .getName()).log(Level.SEVERE, e.getMessage());
        }
    }

}
