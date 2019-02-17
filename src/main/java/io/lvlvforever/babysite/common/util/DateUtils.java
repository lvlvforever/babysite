package io.lvlvforever.babysite.common.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by lvlvforever on 2019/2/17.
 */
public class DateUtils {
    public static final String DEFAULT_PATTEN = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_PATTEN);

    public static Long parseDateToTimestamp(String date, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static Long parseDateToTimestampWithDefaultPattern(String date) {
        return parseDateToTimestamp(date, DEFAULT_PATTEN);
    }

    public static String parseDateToDefaultPattern(Date date) {
        if (date == null) {
            return "";
        }
        Instant instant = date.toInstant();
        return instant.atZone(ZoneId.systemDefault()).format(DEFAULT_FORMATTER);

    }

}
