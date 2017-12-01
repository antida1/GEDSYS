/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base16.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author rober
 */
public class DateTimeUtils {

    public static String getFormattedTime(Date time, String format) {
        if (time == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(time);
    } 
}
