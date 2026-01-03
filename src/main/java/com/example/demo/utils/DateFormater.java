package com.example.demo.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormater {
    
    public String formater(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return now.format(newFormat);
    }
}
