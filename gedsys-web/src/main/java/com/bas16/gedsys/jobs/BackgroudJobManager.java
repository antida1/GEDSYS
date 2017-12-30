/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bas16.gedsys.jobs;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

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
        Logger.getLogger(BackgroudJobManager.class.getName()).log(Level.SEVERE, "Job Run Every 5 Minutes");
    }
    
    @Schedule(hour = "*", minute = "*/1", second = "0", persistent = false)
    public void someMinuteJob() {
        // Do your job here which should run every 15 minute of hour.
        //PushFCMNotification.PushFCMNotification(sToken, "Mensaje de Prueba", "Hola Desde Gedsys");
        Logger.getLogger(BackgroudJobManager.class.getName()).log(Level.SEVERE, "Job Run Every 5 Minutes");
    }

}
