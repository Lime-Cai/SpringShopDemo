package com.example.springdemo.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTools {

    public static String dateFormat(LocalDateTime localDateTime){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(dateTimeFormatter);
    }
}
