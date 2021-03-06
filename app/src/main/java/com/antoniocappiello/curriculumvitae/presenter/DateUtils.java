/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/10/16 9:17 PM
 */

package com.antoniocappiello.curriculumvitae.presenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date dateFromYear(String dateAsString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.parse(dateAsString);
    }

    public static String yearFromDate(Date date) {
        if(date == null){
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }

    public static Date dateFromMonthAndYear(String dateAsString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("M-yyyy");
        return format.parse(dateAsString);
    }

    public static String monthAndYearFromDate(Date date) {
        if(date == null){
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("M-yyyy");
        return format.format(date);
    }
}
