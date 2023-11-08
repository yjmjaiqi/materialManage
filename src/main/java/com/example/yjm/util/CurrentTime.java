package com.example.yjm.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentTime {
    public static String getTime(){
        // 获取当前时间
        LocalDateTime currentTime = LocalDateTime.now();

        // 使用默认格式化
        System.out.println("Current Time: " + currentTime);

        // 使用自定义格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        System.out.println("Formatted Time: " + formattedTime);
        return formattedTime;
    }
    public static String format(String timestamp){

        // 解析ISO 8601格式的时间字符串为Instant对象
        Instant instant = Instant.parse(timestamp);

        // 定义要输出的日期时间格式
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.of("UTC")); // 选择时区

        // 格式化Instant对象为指定格式的日期时间字符串
        String formattedTime = outputFormatter.format(instant);
        return formattedTime;
    }

    public static void main(String[] args) {
        String iso8601Time = "2023-10-27T04:15:56.000Z";

        // 解析ISO 8601格式的时间字符串为Instant对象
        Instant instant = Instant.parse(iso8601Time);

        // 定义要输出的日期时间格式
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.of("UTC")); // 选择时区

        // 格式化Instant对象为指定格式的日期时间字符串
        String formattedTime = outputFormatter.format(instant);

        System.out.println("原始时间字符串: " + iso8601Time);
        System.out.println("格式化后的时间: " + formattedTime);

    }
}
