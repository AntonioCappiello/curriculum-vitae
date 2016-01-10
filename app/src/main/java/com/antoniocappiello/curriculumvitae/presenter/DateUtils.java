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
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }
}
