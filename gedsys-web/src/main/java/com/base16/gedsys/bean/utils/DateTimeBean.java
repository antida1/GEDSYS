/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.gedsys.bean.utils;

import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author rober
 */
@Named(value = "dateTimeBean")
@RequestScoped
public class DateTimeBean {

    /**
     * Creates a new instance of DateTimeBean
     */
    public DateTimeBean() {
    }
    
    public long DaysBettwen( Date startDate, Date endDate ){
        if(startDate == null){
            startDate = new Date();
        }
        long diff = startDate.getTime() - endDate.getTime();
        return diff / (1000 * 60 * 60 *24);
    }
    
}
