package com.jdk.java8;

import org.junit.Test;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeExample {

    @Test
    public void test() {
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
    }

    @Test
    public void test1() {
        LocalDateTime localDateTime = LocalDateTime.of(2021, 6, 1, 10, 30);
        System.out.println(localDateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = localDateTime.format(formatter);
        System.out.println(format);

        LocalDateTime parse = LocalDateTime.parse("2021-01-01 12:00:00", formatter);
        System.out.println(parse);
    }

}
