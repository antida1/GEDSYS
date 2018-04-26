/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.jobs;

import com.base16.gedsys.entities.Notificacion;
import com.base16.gedsys.messages.Push;
import com.base16.gedsys.model.NotificacionJpaController;
import com.base16.gedsys.utils.JpaUtils;
import com.base16.gedsys.web.utils.WebConfiguration;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rober
 */
@Singleton
@Startup
public class BackgroudJobManager {

    @Schedule(hour = "0", minute = "0", second = "0", persistent = false)
    public void someDailyJob() {
        // Do your job here which should run every start of day.
    }

    @Schedule(hour = "*/1", minute = "0", second = "0", persistent = false)
    public void someHourlyJob() {
        // Do your job here which should run every hour of day.
        Logger.getLogger(BackgroudJobManager.class.getName()).log(Level.SEVERE, "Job Run Every hour of day");
    }

    @Schedule(hour = "*", minute = "*/15", second = "0", persistent = false)
    public void someQuarterlyJob() {
        // Do your job here which should run every 15 minute of hour.
        //PushFCMNotification.PushFCMNotification(sToken, "Mensaje de Prueba", "Hola Desde Gedsys");
        Logger.getLogger(BackgroudJobManager.class.getName()).log(Level.SEVERE, "Job Run Every 15 Minutes");
    }

    @Schedule(hour = "*", minute = "*/1", second = "0", persistent = false)
    public void someMinuteJob() {
        try {
            // Do your job here which should run every 15 minute of hour.
            //PushFCMNotification.PushFCMNotification(sToken, "Mensaje de Prueba", "Hola Desde Gedsys");
            //Push.sendAll("Mensaje desde el Background Job Manager for all Connected Users.");
            EntityManagerFactory emf = JpaUtils.getEntityManagerFactory(WebConfiguration.getInstance().getConfigFilePath());
            NotificacionJpaController nJpa = new NotificacionJpaController(emf);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date();
           
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.HOUR, 23);
            
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(date);
            calendar2.set(Calendar.MILLISECOND, 0);
            calendar2.set(Calendar.SECOND, 0);
            calendar2.set(Calendar.MINUTE, 0);
            calendar2.set(Calendar.HOUR, 0);
            
            List<Notificacion> notificaiones = new ArrayList();
            notificaiones = nJpa.findNotificacionByDateTime(calendar2.getTime(), calendar.getTime());
            for (Notificacion notificacion : notificaiones) {
                
                long different = notificacion.getFechaNotificacion().getTime() - new Date().getTime();
                long secondsInMilli = 1000;
		long minutesInMilli = secondsInMilli * 60;
		long hoursInMilli = minutesInMilli * 60;
		long daysInMilli = hoursInMilli * 24;
                long elapsedDays = different / daysInMilli;
                              
		different = different % daysInMilli;
		
		long elapsedHours = different / hoursInMilli;
		different = different % hoursInMilli;
		
		long elapsedMinutes = different / minutesInMilli;
                
                if( elapsedDays == 0  && elapsedHours == 0 && elapsedMinutes == 0){ 
                    Push.sendToUser(notificacion.getAsunto(), notificacion.getResponsable().getId().toString());
                }
            }
            Logger.getLogger(BackgroudJobManager.class.getName()).log(Level.INFO, "Job Run Every 1 Minutes");
        } catch (NamingException ex) {
            Logger.getLogger(BackgroudJobManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
