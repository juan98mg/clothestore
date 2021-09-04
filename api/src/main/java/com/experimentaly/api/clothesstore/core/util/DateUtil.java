package com.experimentaly.api.clothesstore.core.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {


    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static LocalDateTime stringToDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

        // convert String to LocalDate
        return LocalDateTime.parse(date, formatter);
    }

    private DateUtil() {}
}
