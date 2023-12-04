package com.chatapp.server.Utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtility {

    private final static String pattern = "HH:mm" ;
    public static String convertDateToString (LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }
}
