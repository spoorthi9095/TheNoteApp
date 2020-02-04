package com.spoorthi.noteapp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NoteAppUtils {

    /**
     * @param calendar
     * @return date in dd/MM/yyyy format
     */
    public static String convertDateToString(Calendar calendar, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
        Date date = calendar.getTime();
        return dateFormat.format(date);
    }

}
