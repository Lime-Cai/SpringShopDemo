package com.example.springdemo.controller.system;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class customizeDate {

    static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    static final ZoneId zoneId = ZoneId.systemDefault();


    public static String localToString(LocalDateTime localDateTime) {
        return localDateTime.format(dateTimeFormatter);
    }


}
