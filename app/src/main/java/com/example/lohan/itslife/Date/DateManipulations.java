package com.example.lohan.itslife.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lohan on 16-10-2016.
 */

public class DateManipulations {
    public static Date getCurrentDate(){
        return new Date();
    }
    public static Calendar getCalendarFromDateString(String d){
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(d);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c;
        }
        catch (Exception e){
            return null;
        }
    }
    public static String getStringFromDate(Date date){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String reportDate = df.format(date);
        return reportDate;
    }
}
